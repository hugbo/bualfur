package hugbo.bualfur;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by egill on 12.3.2017.
 */

public class PropertyFragment extends Fragment {
    private Property mProperty;
    private EditText mAddressField;
    private EditText mZipcodeField;
    private EditText mCityField;
    private EditText mPriceField;
    private EditText mSizeField;
    private EditText mBedroomsField;
    private EditText mBathroomsField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProperty = new Property(null, null, 0, null, 0, 0, 0, 0, 0, 0, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View v = inflater.inflate(R.layout.fragment_property, container, false);

        mAddressField = (EditText) v.findViewById(R.id.property_address);
        mZipcodeField = (EditText) v.findViewById(R.id.property_zipcode);
        mCityField = (EditText) v.findViewById(R.id.property_city);
        mPriceField = (EditText) v.findViewById(R.id.property_price);
        mSizeField = (EditText) v.findViewById(R.id.property_size);
        mBedroomsField = (EditText) v.findViewById(R.id.property_bedrooms);
        mBathroomsField = (EditText) v.findViewById(R.id.property_bathrooms);

        // Add event listeners
        mAddressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProperty.setmAddress(s.toString());
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
                mProperty.setmZipcode(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }
}
