package hugbo.bualfur.services;

import org.json.JSONObject;

import java.util.ArrayList;

import hugbo.bualfur.model.Property;

/**
 * Created by stefan on 19/03/17.
 * Callback Interface.
 */
public interface PropertyCallback {

    /**
     * Let's you add an onSuccess callback onto a function
     * which works asynchronously.
     * @param results
     */
    public void onSuccess(ArrayList<Property> results);
}
