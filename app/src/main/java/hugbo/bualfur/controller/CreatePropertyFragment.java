package hugbo.bualfur.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

import hugbo.bualfur.R;
import hugbo.bualfur.model.Property;

/**
 * Created by oddgeir on 3.4.2017.
 */

public class CreatePropertyFragment extends Fragment {

    private static final String TAG = "CreatePropertyFragment";
    private static ArrayList<ImageView> mCurrentPhotos;
    private Button mSaveButton;
    private Spinner mTypeSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_property, container, false);

        mSaveButton = (Button) view.findViewById(R.id.create_property_button);

        mSaveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                createProperty(view);
            }
        });

        mTypeSpinner = (Spinner) view.findViewById(R.id.new_property_houseType);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.houseType_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mTypeSpinner.setAdapter(adapter);

        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
        Log.i(TAG, "onCreate: ");
    }

    public void createProperty(View view) {
        String mServerId = null;
        String mAddress = view.findViewById(R.id.new_property_address).toString();
        int mZipcode = Integer.parseInt(view.findViewById(R.id.new_property_zipcode).toString());
        String mCity = view.findViewById(R.id.new_property_city).toString();
        int mPrice = Integer.parseInt(view.findViewById(R.id.new_property_price).toString());
        int mSize = Integer.parseInt(view.findViewById(R.id.new_property_size).toString());
        double mLat = 12.34;
        double mLon = 43.21;
        int mNumBedrooms = Integer.parseInt(view.findViewById(R.id.new_property_bedrooms).toString());
        int mNumBathrooms = Integer.parseInt(view.findViewById(R.id.new_property_bathrooms).toString());
        String mPropertyType = view.findViewById(R.id.new_property_houseType).toString();

        Property newProperty = new Property(mServerId, mAddress, mZipcode, mCity,
                mPrice, mSize, mLat, mLon, mNumBedrooms, mNumBathrooms, mPropertyType);
    }

}
