package hugbo.bualfur.services;

import org.json.JSONObject;

import java.util.ArrayList;

import hugbo.bualfur.model.Conversation;

/**
 * Created by egill on 6.4.2017.
 */

public interface MessageCallback {
    public void onSuccess(ArrayList<Conversation> conversations);
}
