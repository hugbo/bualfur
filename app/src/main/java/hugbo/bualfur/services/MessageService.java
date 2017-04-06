package hugbo.bualfur.services;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import hugbo.bualfur.model.User;

/**
 * Created by egill on 6.4.2017.
 */

public class MessageService {
    private static MessageService mMessageService;
    private Context mCtx;
    private String TAG = "MessageService";

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
        String devURL = "http://192.168.1.36:3000/conversations/index_json";
        JSONObject data = new JSONObject();


        try {
            data.put("id" , user.getmFacebookId());
        } catch (JSONException e){
            Log.e(TAG, "getAllMessagesForUser: " + e.toString());
        }



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, devURL, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.toString());
            }
        });

        NetworkController.getInstance(mCtx).addToRequestQueue(request);


    }
}
