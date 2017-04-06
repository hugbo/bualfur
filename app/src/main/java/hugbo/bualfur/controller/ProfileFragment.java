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
import hugbo.bualfur.model.User;
import hugbo.bualfur.services.SessionManager;
import hugbo.bualfur.services.UserCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private static final String ARG_USER_ID = "user_id";
    private static final String TAG = "ProfileFragment";
    private User mUserToView;
    private TextView mUserName;
    private TextView mUserPhone;
    private TextView mUserEmail;
    private TextView mUserAge;

    public static ProfileFragment newInstance(String serverID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, serverID);

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserPhone = (TextView) view.findViewById(R.id.user_phone);
        mUserEmail = (TextView) view.findViewById(R.id.user_email);
        mUserAge = (TextView) view.findViewById(R.id.user_age);

        mUserToView = SessionManager.getInstance(getActivity()).getCurrentUser();

        updateView(view);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
        Log.i(TAG, "onCreate: ");

    }

    private void updateView(View view){
        mUserName.setText(mUserToView.getmFirstName()+" "+mUserToView.getmLastName());
        mUserPhone.setText(mUserToView.getmPhone());
        mUserEmail.setText(mUserToView.getmEmail());
        mUserAge.setText(mUserToView.getmAgeRange());
    }

}




