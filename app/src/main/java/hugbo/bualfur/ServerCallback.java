package hugbo.bualfur;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stefan on 19/03/17.
 * Callback Interface.
 */
public interface ServerCallback {

    /**
     * Let's you add an onSuccess callback onto a function
     * which works asynchronously.
     * @param results
     */
    public void onSuccess(ArrayList<Property> results);
}
