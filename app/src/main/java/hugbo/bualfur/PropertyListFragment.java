package hugbo.bualfur;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by egill on 13.3.2017.
 */

public class PropertyListFragment extends Fragment {
    private RecyclerView mPropertyRecyclerView;
    private PropertyAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_property_list, container, false);

        mPropertyRecyclerView = (RecyclerView) view.findViewById(R.id.property_recycler_view);
        mPropertyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        try {
            Thread.sleep(1000);
        } catch (Exception e){
            //NOTHING
        }
        updateUI();

        return view;
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

    private void updateUI(){
        PropertyStorage propertyStorage = PropertyStorage.get(getActivity());
        ArrayList<Property> properties = propertyStorage.getProperties();

        mAdapter = new PropertyAdapter(properties);
        mPropertyRecyclerView.setAdapter(mAdapter);
    }
}
