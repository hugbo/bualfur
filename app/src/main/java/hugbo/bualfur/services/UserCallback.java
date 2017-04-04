package hugbo.bualfur.services;

import org.json.JSONObject;

import hugbo.bualfur.model.User;

/**
 * Created by stefanc on 04/04/17.
 */

public interface UserCallback {

    public void onSuccess(User user);
}
