package hugbo.bualfur;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by stefan on 16/03/17.
 * Singleton class that handles network requests
 * based on the Volley request tutorial from Google
 */

public class NetworkController {

    private static NetworkController mNetworkInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;


    /**
     * Constructor
     * @param context the context from the activity/fragment that's using
     *                the network controller.
     */
    private NetworkController(Context context){
        mContext = context;

        mRequestQueue = getRequestQueue();
    }


    /**
     *
     * @param context the context from the activity/fragment that's using
     *                the network controller
     * @return NetworkController
     */
    public static synchronized NetworkController getInstance(Context context){
        if(mNetworkInstance == null){
            mNetworkInstance = new NetworkController(context);
        }
        return mNetworkInstance;
    }


    /**
     * Gets or creates a new RequestQueue for handling network requests
     * @return RequestQueue
     */
    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }


    /**
     * Adds a new Request to the request queue
     * @param request
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}
