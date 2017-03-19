package hugbo.bualfur;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by oddgeir on 19.3.2017.
 */

public class ViewPropertyFragment extends Fragment {

    private Property propertyToView;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle args = getArguments();
        id = args.getString("id", "");

        return inflater.inflate(R.layout.fragment_view_property, container, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        fetchProperty();
    }

    private void fetchProperty() {
        propertyToView = PropertyFetcher.getInstance(getActivity()).getProperty(id);

        try {
            TextView AddressTextView = (TextView) this.getView().findViewById(R.id.property_address);
            TextView PriceTextView = (TextView) this.getView().findViewById(R.id.property_price);
            TextView ZipcodeTextView = (TextView) this.getView().findViewById(R.id.property_zipcode);
            TextView CityTextView = (TextView) this.getView().findViewById(R.id.property_city);
            TextView SizeTextView = (TextView) this.getView().findViewById(R.id.property_size);
            TextView BedroomsTextView = (TextView) this.getView().findViewById(R.id.property_bedrooms);
            TextView BathroomsTextView = (TextView) this.getView().findViewById(R.id.property_bathrooms);
            TextView TypeTextView = (TextView) this.getView().findViewById(R.id.property_houseType);
            //TextView DescriptionTextView = (TextView) this.getView().findViewById(R.id.property_description);

            AddressTextView.setText(propertyToView.getmAddress());
            PriceTextView.setText(propertyToView.getmPrice());
            ZipcodeTextView.setText(propertyToView.getmZipcode());
            CityTextView.setText(propertyToView.getmCity());
            SizeTextView.setText(propertyToView.getmSize());
            BedroomsTextView.setText(propertyToView.getmNumBedrooms());
            BathroomsTextView.setText(propertyToView.getmNumBathrooms());
            TypeTextView.setText(propertyToView.getmPropertyType());
            //DescriptionTextView.setText(propertyToView.getm);
        } catch (NullPointerException e) {
            Log.e("ViewProperty", e.toString());
        }

    }



}
