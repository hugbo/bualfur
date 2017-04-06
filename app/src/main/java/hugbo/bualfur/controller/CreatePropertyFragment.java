package hugbo.bualfur.controller;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hugbo.bualfur.R;
import hugbo.bualfur.model.Property;
import hugbo.bualfur.model.User;
import hugbo.bualfur.services.PropertyService;
import hugbo.bualfur.services.SessionManager;
import hugbo.bualfur.services.UserCallback;

/**
 * Created by oddgeir on 3.4.2017.
 */

public class CreatePropertyFragment extends Fragment {

    private static final String TAG = "CreatePropertyFragment";
    private static ArrayList<ImageView> mCurrentPhotos;
    private Button mSaveButton;
    private Spinner mTypeSpinner;
    private String mServerId;
    private EditText mAddress;
    private EditText mZipcode;
    private EditText mCity;
    private EditText mPrice;
    private EditText mSize;
    private EditText mNumBedrooms;
    private EditText mNumBathrooms;
    private Spinner mPropertyType;
    private ImageButton mImageButton;
    private ImageView mPhotoView;
    private User mCurrentUser;
    private File mPhotoFile;
    private Property mNewProperty;
    private static final int REQUEST_PHOTO=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_property, container, false);


        mNewProperty = new Property();

        PackageManager packageManager = getActivity().getPackageManager();

        mSaveButton = (Button) view.findViewById(R.id.create_property_button);
        mAddress = (EditText) view.findViewById(R.id.new_property_address);
        mZipcode = (EditText) view.findViewById(R.id.new_property_zipcode);
        mCity = (EditText) view.findViewById(R.id.new_property_city);
        mPrice = (EditText)view.findViewById(R.id.new_property_price);
        mSize = (EditText)view.findViewById(R.id.new_property_size);
        mNumBedrooms = (EditText)view.findViewById(R.id.new_property_bedrooms);
        mNumBathrooms = (EditText)view.findViewById(R.id.new_property_bathrooms);
        mPropertyType = (Spinner) view.findViewById(R.id.new_property_houseType);
        mImageButton = (ImageButton) view.findViewById(R.id.new_property_camera);
        mPhotoView = (ImageView) view.findViewById(R.id.new_property_image1);
        mPhotoFile = PropertyService.getInstance(getActivity()).getPhotoFile(mNewProperty);


        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;

        mImageButton.setEnabled(canTakePhoto);


        mImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Uri uri = FileProvider.getUriForFile(getActivity(),"hugbo.bualfur.fileprovider", mPhotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                List<ResolveInfo> cameraActivities = getActivity().getPackageManager().queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo activity : cameraActivities){
                    getActivity().grantUriPermission(activity.activityInfo.packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

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
        double mLat = 64.139707;
        double mLon = -21.950430;

        String address = mAddress.getText().toString();
        int zipcode = Integer.valueOf(mZipcode.getText().toString());
        String city = mCity.getText().toString();
        int price = Integer.valueOf(mPrice.getText().toString());
        int size = Integer.valueOf(mSize.getText().toString());
        int numBedrooms = Integer.valueOf(mNumBathrooms.getText().toString());
        int numBathrooms = Integer.valueOf(mNumBathrooms.getText().toString());
        String propertyType = mPropertyType.getSelectedItem().toString();

        mNewProperty.setmAddress(address);
        mNewProperty.setmZipcode(zipcode);
        mNewProperty.setmCity(city);
        mNewProperty.setmPrice(price);
        mNewProperty.setmSize(size);
        mNewProperty.setmNumBedrooms(numBedrooms);
        mNewProperty.setmNumBathrooms(numBathrooms);
        mNewProperty.setmPropertyType(propertyType);


        final Property newProperty = mNewProperty;

        mCurrentUser = SessionManager.getInstance(getActivity()).getCurrentUser();

        if (mCurrentUser == null){
            SessionManager.getInstance(getActivity()).getLoggedInUser(new UserCallback() {
                @Override
                public void onSuccess(User user) {
                    mCurrentUser = user;
                    PropertyService.getInstance(getActivity()).postPropertyToServer(newProperty, mCurrentUser);
                }
            });
        } else {
            PropertyService.getInstance(getActivity()).postPropertyToServer(newProperty, mCurrentUser);
        }

        Intent intent = PropertyPagerActivity.newIntent(getActivity(), newProperty.getmId());

        startActivity(intent);

    }

}
