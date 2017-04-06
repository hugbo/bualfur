package hugbo.bualfur.services;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import hugbo.bualfur.model.Conversation;
import hugbo.bualfur.model.Property;
import hugbo.bualfur.model.User;

/**
 * Created by stefanc on 04/04/17.
 */

public class SessionManager {

    private User mCurrentUser;
    private Context mCtx;
    private static SessionManager mSessionManager;

    private String devURL = "http://192.168.122.1:3000/sessions/get_user/";


    private String prodURL = "http://hugbo-verkefni1-dev.herokuapp.com/sessions/get_user";
    private static final String TAG = "SessionManager";

    private SessionManager(Context context){
        mCtx = context;
    }

    public static synchronized SessionManager getInstance(Context context){
        if(mSessionManager == null){
            mSessionManager = new SessionManager(context);
        }
        return mSessionManager;
    }


    public void getLoggedInUser(final UserCallback callback){

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback(){

            @Override
            public void onCompleted(JSONObject object, GraphResponse response){
                getUserFromServer(object, callback);
            }
        });

        Bundle parameters = new Bundle();

        parameters.putString("fields", "id, picture, first_name, last_name, gender, age_range, link" );
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void getUserFromID(String fbId, final UserCallback callback){

        String devURL = "http://192.168.122.1:3000/profile/json"+fbId;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, devURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject userObject = response.getJSONObject("user");

                    String firstName = userObject.getString("first_name");
                    String lastName = userObject.getString("last_name");
                    String gender = userObject.getString("gender");
                    String ageRange = userObject.getString("age_range");
                    String imageURL = userObject.getString("image_url");
                    String facebookURL = userObject.getString("facebook_url");
                    String phoneNumber = userObject.getString("phone_number");
                    String personalInfo = userObject.getString("personal_info");
                    String email = userObject.getString("email");
                    String facebookId = userObject.getString("facebook_id");

                    User user = new User(facebookId, firstName, lastName, ageRange, gender, email, phoneNumber, personalInfo, imageURL, facebookURL);

                    callback.onSuccess(user);
                } catch (JSONException error){
                    Log.e(TAG, "onResponse: "+error.toString() );
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        });

        NetworkController.getInstance(mCtx).addToRequestQueue(request);
    }

    public void getUserFromServer(JSONObject userdata, final UserCallback callback){
        JSONObject graphData = new JSONObject();
        try {
            graphData.put("graph_data", userdata);
        } catch (JSONException error){
            Log.e(TAG, "getUserFromServer: "+error.toString() );
        }

        Log.i(TAG, "getUserFromServer: "+graphData.toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, prodURL, graphData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i(TAG, "Bualfur response"+response.toString());

                try {
                    JSONObject userObject = response.getJSONObject("user");

                    String firstName = userObject.getString("first_name");
                    String lastName = userObject.getString("last_name");
                    String gender = userObject.getString("gender");
                    String ageRange = userObject.getString("age_range");
                    String imageURL = userObject.getString("image_url");
                    String facebookURL = userObject.getString("facebook_url");
                    String phoneNumber = userObject.getString("phone_number");
                    String personalInfo = userObject.getString("personal_info");
                    String email = userObject.getString("email");
                    String facebookId = userObject.getString("facebook_id");

                    User user = new User(facebookId, firstName, lastName, ageRange, gender, email, phoneNumber, personalInfo, imageURL, facebookURL);

                    mCurrentUser = user;

                } catch (JSONException error){
                    Log.e(TAG, "onResponse: "+error.toString() );
                }

                callback.onSuccess(mCurrentUser);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error);
            }
        });


        NetworkController.getInstance(mCtx).addToRequestQueue(request);
    }

    public boolean isUserAuthenticated() {
        if (mCurrentUser == null) return false;
        else return true;
    }


    public User getCurrentUser(){
        if (mCurrentUser == null) return null;
        else return mCurrentUser;
    }


}
