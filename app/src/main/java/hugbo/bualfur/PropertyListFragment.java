package hugbo.bualfur;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by egill on 13.3.2017.
 */

public class PropertyListFragment extends Fragment {
    private RecyclerView mPropertyRecyclerView;
    private PropertyAdapter mAdapter;
    private Button mSearchButton;
    private Spinner mZipCodeSpinner;
    private EditText mMinRentEditText;
    private EditText mMaxRentEditText;
    private Spinner mMinNumRoomsSpinner;
    private Spinner mMaxNumRoomsSpinner;
    private Spinner mMinSizeSpinner;
    private Spinner mMaxSizeSpinner;






    private ArrayList<Property> mItems = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_property_list, container, false);


        mPropertyRecyclerView = (RecyclerView) view.findViewById(R.id.property_recycler_view);
        mPropertyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSearchButton = (Button) view.findViewById(R.id.search_button);
        mZipCodeSpinner = (Spinner) view.findViewById(R.id.zip_code_spinner);
        mMinNumRoomsSpinner = (Spinner) view.findViewById(R.id.min_no_of_rooms_spinner);
        mMinRentEditText = (EditText) view.findViewById(R.id.rent_min_edit_text);
        mMaxNumRoomsSpinner = (Spinner) view.findViewById(R.id.max_no_of_rooms_spinner);
        mMaxRentEditText = (EditText) view.findViewById(R.id.rent_max_edit_text);
        mMinSizeSpinner = (Spinner) view.findViewById(R.id.min_size_spinner);
        mMaxSizeSpinner = (Spinner) view.findViewById(R.id.max_size_spinner);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TAG = "SearchButton";
                String minRent = mMinRentEditText.getText().toString();
                String maxRent = mMaxRentEditText.getText().toString();
                String zipcode = mZipCodeSpinner.getSelectedItem().toString();
                String minRooms = mMinNumRoomsSpinner.getSelectedItem().toString();
                String maxRooms = mMaxNumRoomsSpinner.getSelectedItem().toString();
                String minSize = mMinSizeSpinner.getSelectedItem().toString();
                String maxSize = mMaxSizeSpinner.getSelectedItem().toString();

                HashMap<String, String> searchData = new HashMap<String,String>();

                searchData.put("price_max", maxRent);
                searchData.put("price_min", minRent);
                searchData.put("property_type", "");
                searchData.put("rooms_max", maxRooms);
                searchData.put("rooms_min", minRooms);
                searchData.put("square_meters_max", maxSize);
                searchData.put("square_meters_min", minSize);
                searchData.put("zipcode", zipcode);

                JSONObject searchJson = new JSONObject(searchData);

                HashMap<String, JSONObject> tmp = new HashMap<String, JSONObject>();
                tmp.put("search", searchJson);
                searchJson = new JSONObject(tmp);

                PropertyFetcher.getInstance(getActivity()).searchProperties(searchJson, new ServerCallback() {
                    @Override
                    public void onSuccess(ArrayList<Property> results) {
                        mItems = results;
                        for (Property property : results){
                            Log.i("TESTING", property.getmAddress());
                        }
                        setupAdapter();
                    }
                });


            }
        });

        setupAdapter();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchPropertiesTask().execute();

    }

    private void setupAdapter(){
        if(isAdded()){
            mPropertyRecyclerView.setAdapter(new PropertyAdapter(mItems));
        }
    }


    private class PropertyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mAddressTextView;
        private TextView mPriceTextView;
        private Property mProperty;

        public PropertyHolder(View view) {
            super(view);
            view.setClickable(true);

            mAddressTextView = (TextView) itemView.findViewById(R.id.property_address);
            mPriceTextView = (TextView) itemView.findViewById(R.id.property_price);

            view.setOnClickListener(this);
        }

        public void bind(Property property){
            mProperty = property;
            mAddressTextView.setText(mProperty.getmAddress());
            mPriceTextView.setText(String.valueOf(mProperty.getmPrice()));
        }

        @Override
        public void onClick(View view){
            Intent intent = PropertyActivity.newIntent(getActivity(), mProperty.getmId());
            startActivity(intent);
        }


    }

    private class FetchPropertiesTask extends AsyncTask<Void, Void, Void> {
        private final String TAG = "FetchPropertiesTask";
        @Override
        protected Void doInBackground(Void... params){
            try{
                HashMap<String, String> searchParams = new HashMap<>();

                searchParams.put("price_max", "");
                searchParams.put("price_min", "");
                searchParams.put("property_type", "");
                searchParams.put("rooms_max", "");
                searchParams.put("rooms_min", "");
                searchParams.put("square_meters_max", "0");
                searchParams.put("square_meters_min", "0");
                searchParams.put("zipcode", "");


                JSONObject data = new JSONObject(searchParams);

                HashMap<String, JSONObject> tmp = new HashMap<String, JSONObject>();
                tmp.put("search", data);
                data = new JSONObject(tmp);

                PropertyFetcher.getInstance(getActivity()).searchProperties(data, new ServerCallback() {
                    @Override
                    public void onSuccess(ArrayList<Property> results) {
                        mItems = results;
                        setupAdapter();

                    }
                });

                return null;
            } catch (Exception e){
                Log.e("FetchPropertiesFromServer", "instance initializer: "+e.toString() );
            }

            return null;
        }
    }


    private class PropertyAdapter extends RecyclerView.Adapter<PropertyHolder> {
        private ArrayList<Property> mProperties;

        public PropertyAdapter(ArrayList<Property> properties){
            mProperties = properties;
        }

        @Override
        public PropertyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_property, parent, false);

            return new PropertyHolder(view);
        }

        @Override
        public void onBindViewHolder(PropertyHolder holder, int position) {
            Property property = mProperties.get(position);
            holder.bind(property);
        }

        @Override
        public int getItemCount() {
            return mProperties.size();
        }
    }
}
