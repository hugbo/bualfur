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

//        Bundle args = getArguments();
//        id = args.getString("id", "1");
        id = "1";
        return inflater.inflate(R.layout.fragment_view_property, container, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        PropertyFetcher.getInstance(getActivity()).searchProperties(null, new ServerCallback() {
            @Override
            public void onSuccess(ArrayList<Property> results) {
                fetchProperty();
            }
        });
    }

    private void fetchProperty() {
        propertyToView = PropertyFetcher.getInstance(getActivity()).getProperty(id);

        try {
            TextView addressTextView = (TextView) this.getView().findViewById(R.id.property_address);
            TextView priceTextView = (TextView) this.getView().findViewById(R.id.property_price);
            TextView zipcodeTextView = (TextView) this.getView().findViewById(R.id.property_zipcode);
            TextView cityTextView = (TextView) this.getView().findViewById(R.id.property_city);
            TextView sizeTextView = (TextView) this.getView().findViewById(R.id.property_size);
            TextView bedroomsTextView = (TextView) this.getView().findViewById(R.id.property_bedrooms);
            TextView bathroomsTextView = (TextView) this.getView().findViewById(R.id.property_bathrooms);
            TextView typeTextView = (TextView) this.getView().findViewById(R.id.property_houseType);
            //TextView DescriptionTextView = (TextView) this.getView().findViewById(R.id.property_description);

            String address = propertyToView.getmAddress();

            addressTextView.setText(address);

            String price = String.valueOf(propertyToView.getmPrice());

            priceTextView.setText(price);

            String zipcode = String.valueOf(propertyToView.getmZipcode());
            zipcodeTextView.setText(zipcode);

            String city = propertyToView.getmCity();
            cityTextView.setText(city);

            String size = String.valueOf(propertyToView.getmSize());
            sizeTextView.setText(size);

            String numBedrooms = String.valueOf(propertyToView.getmNumBedrooms());
            bedroomsTextView.setText(numBedrooms);

            String numBathrooms = String.valueOf(propertyToView.getmNumBathrooms());
            bathroomsTextView.setText(numBathrooms);

            String propertyType = propertyToView.getmPropertyType();
            typeTextView.setText(propertyType);
            //DescriptionTextView.setText(propertyToView.getm);
        } catch (NullPointerException e) {
            Log.e("ViewProperty", e.toString());
        }

    }



}
