package hugbo.bualfur.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    private ConversationAdapter mAdapter;

    private String TAG = "CreateMessageUsersFrgm";

    private ArrayList<Conversation> mItems = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCurrentUser = SessionManager.getInstance(getActivity()).getCurrentUser();

        if (mCurrentUser == null){
            SessionManager.getInstance(getActivity()).getLoggedInUser(new UserCallback() {
                @Override
                public void onSuccess(User user) {
                    mCurrentUser = user;
                    MessageService.getInstance(getActivity()).getAllMessagesForUser(mCurrentUser, new MessageCallback() {
                        @Override
                        public void onSuccess(ArrayList<Conversation> conversations) {
                            mItems = conversations;
                            for (Conversation c : conversations){
                                Log.i(TAG, "List created");
                            }

                            updateUI();
                        }
                    });

                }
            });
        } else {
            MessageService.getInstance(getActivity()).getAllMessagesForUser(mCurrentUser, new MessageCallback() {
                @Override
                public void onSuccess(ArrayList<Conversation> conversations) {
                    mItems = conversations;
                    for (Conversation c : conversations){
                        Log.i(TAG, "List created");
                    }

                    updateUI();
                }
            });
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_users, container, false);

        mConversationRecyclerView = (RecyclerView) view.findViewById(R.id.conversation_recycler_view);
        mConversationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private class ConversationHolder extends RecyclerView.ViewHolder {

        private TextView mSubjectTextView;
        private TextView mUpdatedAtTextView;
        private Conversation mConversation;

        public ConversationHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_conversation, parent, false));
            Log.i(TAG, "ConversationHolder");

            mSubjectTextView = (TextView) itemView.findViewById(R.id.conversation_subject);
            mUpdatedAtTextView = (TextView) itemView.findViewById(R.id.conversation_created_at);
        }

        public void bind(Conversation conversation) {
            mConversation = conversation;
            mSubjectTextView.setText(mConversation.getSubject());
            mUpdatedAtTextView.setText(mConversation.getmCreatedAt().toString());
        }
    }

    private class ConversationAdapter extends RecyclerView.Adapter<ConversationHolder> {
        private List<Conversation> mConversations;

        public ConversationAdapter(List<Conversation> conversations){
            mConversations = conversations;
            Log.i(TAG, "ConversationAdapter");
        }

        @Override
        public ConversationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ConversationHolder(layoutInflater,parent);
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

    private void updateUI() {
        if(isAdded()) {
            mAdapter = new ConversationAdapter(mItems);
            mConversationRecyclerView.setAdapter(new ConversationAdapter(mItems));
            //Log.i(TAG, mItems.get(0).getSubject());
        }
    }

}
