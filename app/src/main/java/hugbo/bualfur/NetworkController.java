package hugbo.bualfur;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by stefan on 16/03/17.
 */

public class NetworkController {

    private static NetworkController mNetworkInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private NetworkController(Context context){
        mContext = context;

        mRequestQueue = getRequestQueue();
    }


    public static synchronized NetworkController getInstance(Context context){
        if(mNetworkInstance == null){
            mNetworkInstance = new NetworkController(context);
        }
        return mNetworkInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}
