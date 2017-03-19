package hugbo.bualfur;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private ArrayList<Property> mItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_property_list, container, false);

        mPropertyRecyclerView = (RecyclerView) view.findViewById(R.id.property_recycler_view);
        mPropertyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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



    private class PropertyHolder extends RecyclerView.ViewHolder {

        private TextView mAddressTextView;
        private TextView mPriceTextView;
        private Property mProperty;

        public PropertyHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_property, parent, false));

            mAddressTextView = (TextView) itemView.findViewById(R.id.property_address);
            mPriceTextView = (TextView) itemView.findViewById(R.id.property_price);


        }

        public void bind(Property property){
            mProperty = property;
            mAddressTextView.setText(mProperty.getmAddress());
            mPriceTextView.setText(String.valueOf(mProperty.getmPrice()));
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

            return new PropertyHolder(layoutInflater, parent);
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
