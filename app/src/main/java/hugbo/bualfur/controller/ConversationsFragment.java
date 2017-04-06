package hugbo.bualfur.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hugbo.bualfur.R;
import hugbo.bualfur.model.Conversation;
import hugbo.bualfur.model.Property;
import hugbo.bualfur.model.User;
import hugbo.bualfur.services.SessionManager;
import hugbo.bualfur.services.UserCallback;

import static com.facebook.GraphRequest.TAG;

/**
 * Created by oddgeiron 6.4.2017.
 */

public class ConversationsFragment extends Fragment {

    private ArrayList<Conversation> mConversations;
    private Conversation mCurrentConversation;
    private User mCurrentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversations, container, false);

        SessionManager sm = SessionManager.getInstance(getContext());

        if (!sm.isUserAuthenticated()){
            sm.getLoggedInUser(new UserCallback() {
                @Override
                public void onSuccess(User user) {
                    mCurrentUser = user;
                    Log.i(TAG, "onCreateView: "+mCurrentUser.getmFirstName());
                }
            });
        } else {
            mCurrentUser = sm.getCurrentUser();
        }

        getConversationsFromServer(mCurrentUser);



        return view;
    }

    private class ConversationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mAddressTextView;
        private TextView mPriceTextView;
        private Property mProperty;

        //Constructor
        public ConversationHolder(View view) {
            super(view);
            view.setClickable(true);

            //mAddressTextView = (TextView) itemView.findViewById(R.id.property_address);
            //mPriceTextView = (TextView) itemView.findViewById(R.id.property_price);

            view.setOnClickListener(this);
        }

        public void bind(Conversation conversation){
            mCurrentConversation = conversation;
        }

        @Override
        public void onClick(View view){

        }


    }

    public void getConversationsFromServer(User user) {
        mConversations = user.getmConversations();
    }

    public ArrayList<Conversation> getConversations() { return mConversations; }

    public Conversation getCurrentConversation() { return mCurrentConversation; }

    public void sendMessage(Conversation conversation, String message ) {}

    //Adapter class for the RecyclerView
    private class ConversationAdapter extends RecyclerView.Adapter<ConversationHolder> {

        public ConversationAdapter(ArrayList<Conversation> conversations){
            mConversations = mConversations;
        }

        @Override
        public ConversationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_property, parent, false);

            return new ConversationHolder(view);
        }

        @Override
        public void onBindViewHolder(ConversationHolder holder, int position) {
            Conversation conversation = mConversations.get(position);
            holder.bind(conversation);
        }

        @Override
        public int getItemCount() {
            return mConversations.size();
        }
    }

}
