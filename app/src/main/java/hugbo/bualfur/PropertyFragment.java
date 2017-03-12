package hugbo.bualfur;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by egill on 12.3.2017.
 */

public class PropertyFragment extends android.support.v4.app.Fragment {
    private Property mProperty;
    private EditText mAddressField;
    private EditText mZipcodeField;
    private EditText mCityField;
    private EditText mPriceField;
    private EditText mSizeField;
    private EditText mBedroomsField;
    private EditText mBathroomsField;
    private Button mSaveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Property to be edited/created
        mProperty = new Property(null, null, 0, null, 0, 0, 0, 0, 0, 0, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View v = inflater.inflate(R.layout.fragment_property, container, false);
        // Handles for input fields
        mAddressField = (EditText) v.findViewById(R.id.property_address);
        mZipcodeField = (EditText) v.findViewById(R.id.property_zipcode);
        mCityField = (EditText) v.findViewById(R.id.property_city);
        mPriceField = (EditText) v.findViewById(R.id.property_price);
        mSizeField = (EditText) v.findViewById(R.id.property_size);
        mBedroomsField = (EditText) v.findViewById(R.id.property_bedrooms);
        mBathroomsField = (EditText) v.findViewById(R.id.property_bathrooms);
        mSaveButton = (Button) v.findViewById(R.id.save_property_button);

        // Attributes to be changed on mProperty
        final String[] mAddressContent = {mProperty.getmAddress()};
        final int[] mZipcodeContent = {mProperty.getmZipcode()};
        final String[] mCityContent = {mProperty.getmCity()};
        final int[] mPriceContent = {mProperty.getmPrice()};
        final int[] mSizeContent = {mProperty.getmSize()};
        final int[] mBedroomsContent = {mProperty.getmNumBedrooms()};
        final int[] mBathroomsContent = {mProperty.getmNumBathrooms()};

        // Add event listeners
        mAddressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAddressContent[0] =  s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mZipcodeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mZipcodeContent[0] = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCityField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCityContent[0] = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPriceField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPriceContent[0] = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSizeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSizeContent[0] = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBedroomsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBedroomsContent[0] = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBathroomsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBathroomsContent[0] = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mProperty.setmAddress(mAddressContent[0]);
                mProperty.setmCity(mCityContent[0]);
                mProperty.setmZipcode(mZipcodeContent[0]);
                mProperty.setmPrice(mPriceContent[0]);
                mProperty.setmSize(mSizeContent[0]);
                mProperty.setmNumBedrooms(mBedroomsContent[0]);
                mProperty.setmNumBathrooms(mBathroomsContent[0]);
            }
        });

        return v;
    }
}
