package hugbo.bualfur.services;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hugbo.bualfur.R;
import hugbo.bualfur.model.Conversation;
import hugbo.bualfur.model.Message;
import hugbo.bualfur.model.User;

/**
 * Created by egill on 6.4.2017.
 */

public class MessageService {
    private static MessageService mMessageService;
    private Context mCtx;
    private String TAG = "MessageService";
    private ArrayList<Conversation> mConversations;

    private MessageService(Context context){
        mCtx = context;
    }

    public static synchronized MessageService getInstance(Context context){
        if(mMessageService == null){
            mMessageService = new MessageService(context);
        }
        return mMessageService;
    }

    public void getAllMessagesForUser(User user, final MessageCallback callback){
        mConversations = new ArrayList<Conversation>();
        String devURL = "http://192.168.122.1:3000/conversations/index_json";
        String prodURL = "https://hugbo-verkefni1-dev.herokuapp.com/conversations/index_json";
        JSONObject data = new JSONObject();


        try {
            data.put("id" , user.getmFacebookId());
        } catch (JSONException e){
            Log.e(TAG, "getAllMessagesForUser: " + e.toString());
        }



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, prodURL, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonConversations = response.getJSONArray("conversations");

                    for (int i = 0; i < jsonConversations.length(); i++) {
                        JSONObject jsonConv = jsonConversations.getJSONObject(i);

                        int conversationId = jsonConv.getInt("conversation_id");

                        String subject = jsonConv.getString("subject");

                        String createdAt = jsonConv.getString("created_at");

                        String updatedAt = jsonConv.getString("updated_at");

                        ArrayList<Message> messages = new ArrayList<Message>();

                        JSONArray jsonMessages = jsonConv.getJSONArray("messages");

                        for (int j = 0; j < jsonMessages.length(); j++) {
                            JSONObject jsonMessage = jsonMessages.getJSONObject(j);

                            int id = jsonMessage.getInt("id");

                            String body = jsonMessage.getString("body");

                            int serverSenderID = jsonMessage.getInt("sender_id");

                            int messageConversationId = jsonMessage.getInt("conversation_id");

                            String updatedAtString = jsonMessage.getString("updated_at");

                            String createdAtString = jsonMessage.getString("created_at");

                            Message tmpMessage = new Message(id, body, serverSenderID, messageConversationId, updatedAtString, createdAtString);

                            messages.add(tmpMessage);
                        }

                        Conversation conversation = new Conversation(conversationId, subject, createdAt, updatedAt, messages);

                        mConversations.add(conversation);
                    }


                    callback.onSuccess(mConversations);


                } catch (JSONException e) {
                    Log.e(TAG, "onResponse: " + e.getStackTrace() );
                    Toast.makeText(mCtx, R.string.no_messages_error, Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Conversations");
                Log.i(TAG, mConversations.get(0).getmMessages().get(0).getmBody());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.toString());
                Toast.makeText(mCtx, R.string.no_messages_error, Toast.LENGTH_SHORT).show();
            }
        });

        NetworkController.getInstance(mCtx).addToRequestQueue(request);


    }

    public ArrayList<Conversation> getConversationList() {
        return mConversations;
    }

}
