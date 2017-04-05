package hugbo.bualfur.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

import hugbo.bualfur.R;
import hugbo.bualfur.model.Property;
import hugbo.bualfur.services.PropertyService;

/**
 * Created by oddgeir on 19.3.2017.
 */


/**
 * Fragment that shows a single property and all its features
 */
public class ViewPropertyFragment extends Fragment {


    private static final String ARG_PROPERTY_ID = "property_id";
    private static final String TAG = "ViewPropertyFragment";
    private Property mPropertyToView;


    /**
     * Constructor for the fragment
     * @param propertyId
     * @return
     */
    public static ViewPropertyFragment newInstance(UUID propertyId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROPERTY_ID, propertyId);

        ViewPropertyFragment fragment = new ViewPropertyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_property, container, false);

        UUID propertyId = (UUID) getArguments().getSerializable(ARG_PROPERTY_ID);

        updateView(view, propertyId);

        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
        Log.i(TAG, "onCreate: ");

    }

    /**
     * Invokes the PropertyService to get the specified property.
     * Updates the layout to include the property information.
     * @param view
     * @param propertyId
     */
    private void updateView(View view, UUID propertyId){


        mPropertyToView = PropertyService.getInstance(getActivity()).getProperty(propertyId);


        // Assign handlers to all TextViews in the layout that need to be altered
        TextView addressTextView = (TextView) view.findViewById(R.id.property_address);
        TextView priceTextView = (TextView) view.findViewById(R.id.property_price);
        TextView zipcodeTextView = (TextView) view.findViewById(R.id.property_zipcode);
        TextView cityTextView = (TextView) view.findViewById(R.id.property_city);
        TextView sizeTextView = (TextView) view.findViewById(R.id.property_size);
        TextView bedroomsTextView = (TextView) view.findViewById(R.id.property_bedrooms);
        TextView bathroomsTextView = (TextView) view.findViewById(R.id.property_bathrooms);
        TextView typeTextView = (TextView) view.findViewById(R.id.property_houseType);
        //TextView DescriptionTextView = (TextView) this.getView().findViewById(R.id.property_description);


        // Assign handlers to the property information variables and alter the layout
        String address = mPropertyToView.getmAddress();
        addressTextView.setText(address);

        String price = String.valueOf(mPropertyToView.getmPrice());
        priceTextView.setText(price);

        String zipcode = String.valueOf(mPropertyToView.getmZipcode());
        zipcodeTextView.setText(zipcode);

        String city = mPropertyToView.getmCity();
        cityTextView.setText(city);

        String size = String.valueOf(mPropertyToView.getmSize());
        sizeTextView.setText(size);

        String numBedrooms = String.valueOf(mPropertyToView.getmNumBedrooms());
        bedroomsTextView.setText(numBedrooms);

        String numBathrooms = String.valueOf(mPropertyToView.getmNumBathrooms());
        bathroomsTextView.setText(numBathrooms);

        String propertyType = mPropertyToView.getmPropertyType();
        typeTextView.setText(propertyType);
    }

}
