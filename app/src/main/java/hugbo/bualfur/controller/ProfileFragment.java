package hugbo.bualfur.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        if (mUserToView == null){
            String serverID = (String) getArguments().getSerializable(ARG_USER_ID);

            updateView(view, serverID);
        } else {
            updateView(view);
        }
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
        Log.i(TAG, "onCreate: ");

    }

    private void updateView(View view, String serverID){
        SessionManager.getInstance(getActivity()).getUserFromID(serverID, new UserCallback(){
            @Override
            public void onSuccess(User user){
                mUserToView = user;
                Log.i(TAG, "onSuccess: "+user.getmFirstName());
            }
        });
    }

    private void updateView(View view){
        Log.i(TAG, "Helloooo!");
    }



}




