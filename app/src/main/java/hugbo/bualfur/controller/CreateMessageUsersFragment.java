package hugbo.bualfur.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;

import hugbo.bualfur.R;
import hugbo.bualfur.model.Conversation;
import hugbo.bualfur.model.User;
import hugbo.bualfur.services.MessageCallback;
import hugbo.bualfur.services.MessageService;
import hugbo.bualfur.services.SessionManager;
import hugbo.bualfur.services.UserCallback;

/**
 * Created by egill on 5.4.2017.
 */

public class CreateMessageUsersFragment extends Fragment {

    private User mCurrentUser;
    private RecyclerView mConversationRecyclerView;
    private String TAG = "CreateMessageUsersFrgm";

    private ArrayList<Conversation> mItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_users, container, false);

        mCurrentUser = SessionManager.getInstance(getActivity()).getCurrentUser();

        if (mCurrentUser == null){
            SessionManager.getInstance(getActivity()).getLoggedInUser(new UserCallback() {
                @Override
                public void onSuccess(User user) {
                    mCurrentUser = user;
                }
            });
        }

        MessageService.getInstance(getActivity()).getAllMessagesForUser(mCurrentUser, new MessageCallback() {
            @Override
            public void onSuccess(ArrayList<Conversation> conversations) {

                for (Conversation c : conversations){
                    Log.i(TAG, "onSuccess: "+c.getSubject());
                }
            }
        });

        return view;
    }
}