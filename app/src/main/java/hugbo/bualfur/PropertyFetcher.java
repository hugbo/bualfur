package hugbo.bualfur;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stefan on 18/03/17.
 */

public class PropertyFetcher {
    private final String TAG = "PropertyFetcher";
    private ArrayList<Property> mProperties;
    private String developmentURL = "http://192.168.0.101:3000/properties/search";
    private String productionURL = "https://hugbo-verkefni1-dev.herokuapp.com/properties/search";
    private Context mCtx;

    public PropertyFetcher(Context context){
            mCtx = context;
    }


    public ArrayList<Property> getProperties(){
        return mProperties;
    }


    public void searchProperties(JSONObject data, final ServerCallback callback){
        mProperties = new ArrayList<Property>();

        if(data == null){
            // Use dummy properties
            Property tmpProp = new Property("1","Smyrlahraun 50", 201, "Hafnarfjörður", 60, 60, 500, 50, 1, 1, "Einbýlishús");
            mProperties.add(tmpProp);
            tmpProp = new Property("2","Gunnlaugsstræti 33", 105, "Reykjavík", 60, 60, 800, 70, 2, 1, "Einbýlishús");
            mProperties.add(tmpProp);
            tmpProp = new Property("3","Gerðargata 12", 101, "Reykjavík", 60, 60, 300, 40, 1, 2, "Fjölbýlishús");
            mProperties.add(tmpProp);

            return;
        }

        JsonObjectRequest requestObject = new JsonObjectRequest(Request.Method.POST, productionURL, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    JSONArray propertiesArray = response.getJSONArray("properties");
//                    Log.i(TAG, tmp.toString());

                    for (int i = 0; i < propertiesArray.length(); i++) {
                        JSONObject property = propertiesArray.getJSONObject(i);
//                        Log.i(TAG, property.toString());

                        String id = property.getString("property_id");

                        String address = property.getString("address");


                        int zipcode = property.getInt("zipcode");

                        String city = property.getString("city");


                        int price = property.getInt("price");


                        int size = property.getInt("size");

                        String propertyType = property.getString("property_type");

                        int numBedrooms = property.getInt("rooms");


                        int numBathrooms = property.getInt("bathrooms");


                        JSONObject gpsLocation = property.getJSONObject("gpslocation");

                        double lat = gpsLocation.getDouble("lat");

                        double lng = gpsLocation.getDouble("lng");

                        Property tmpProperty = new Property(id, address, zipcode, city, price, size, lat, lng, numBedrooms, numBathrooms, propertyType);
                        Log.i(TAG, "adding property!");
                        mProperties.add(tmpProperty);

                    }
                    callback.onSuccess(mProperties);
                } catch (JSONException je){
                    Log.e(TAG, je.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("PropertyStorage", error.toString());
            }
        });



        NetworkController.getInstance(mCtx).addToRequestQueue(requestObject);
    }

}
