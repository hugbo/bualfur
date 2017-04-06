package hugbo.bualfur.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Base64;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hugbo.bualfur.R;
import hugbo.bualfur.model.Property;
import hugbo.bualfur.model.User;
import hugbo.bualfur.services.NetworkController;
import hugbo.bualfur.services.PictureUtils;
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
    private Bitmap mBitmap;

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

        updatePhotoView();

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


    private void updatePhotoView(){
        if(mPhotoFile == null || !mPhotoFile.exists()){
            mPhotoView.setImageDrawable(null);
        } else {
            mBitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(mBitmap);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if ( requestCode == REQUEST_PHOTO){
            Uri uri = FileProvider.getUriForFile(getActivity(), "hugbo.bualfur.fileprovider", mPhotoFile);

            getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            updatePhotoView();
        }
    }

    public void createProperty(View view) {

        String address = mAddress.getText().toString();
        int zipcode = Integer.valueOf(mZipcode.getText().toString());
        String city = mCity.getText().toString();

        String gMapsApiKey = "AIzaSyC8qF171O_qBdXdwSLnH07kFDtbCk5M1CY";

        String gMapsURL = "https://maps.googleapis.com/maps/api/geocode/json?address="+address+","+zipcode+","+city+"&key="+gMapsApiKey;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, gMapsURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                double lat, lng;


                try {
                    JSONArray arr = response.getJSONArray("results");
                    JSONObject location = arr.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

                    lat = location.getDouble("lat");
                    lng = location.getDouble("lng");



                } catch (JSONException err){
                    Log.e(TAG, "onResponse: "+err.toString() );
                    lat = 0;
                    lng = 0;
                }



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
                mNewProperty.setmLat(lat);
                mNewProperty.setmLon(lng);

                Log.i(TAG, "onResponse: "+String.valueOf(lat));

                final Property newProperty = mNewProperty;

                mCurrentUser = SessionManager.getInstance(getActivity()).getCurrentUser();

                if (mCurrentUser == null) {
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error.toString() );
            }
        });

        NetworkController.getInstance(getActivity()).addToRequestQueue(request);
    }

}
