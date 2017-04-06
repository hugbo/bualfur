package hugbo.bualfur.model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by stefanc on 04/04/17.
 */

public class Message {

    private String TAG = "Message";

    private UUID mId;
    private int mServerId;
    private String mBody;
    private int mServerSenderId;
    private int mConversationId;
    private Date mUpdatedAt;
    private Date mCreatedAt;

    public Message(int mServerId, String mBody, int mServerSenderId, int mConversationId,
                   String mUpdatedAt, String mCreatedAt){
        this.mId = UUID.randomUUID();
        this.mServerId = mServerId;
        this.mBody = mBody;
        this.mServerSenderId = mServerSenderId;
        this.mConversationId = mConversationId;
        String createdDateString = mCreatedAt.substring(0, mCreatedAt.length() - 2);
        String updatedDateString = mUpdatedAt.substring(0, mUpdatedAt.length() - 2);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");
        try {
            this.mCreatedAt = formatter.parse(createdDateString);
            this.mUpdatedAt = formatter.parse(updatedDateString);
        } catch (ParseException e) {
            Log.e(TAG, "Conversation: " + e.getStackTrace());
        }
    }

    public UUID getmId() {
        return mId;
    }

    public int getmServerId() {
        return mServerId;
    }

    public String getmBody() {
        return mBody;
    }

    public int getmServerSenderId() {
        return mServerSenderId;
    }

    public int getmConversationId() {
        return mConversationId;
    }

    public Date getmUpdatedAt() {
        return mUpdatedAt;
    }

    public Date getmCreatedAt() {
        return mCreatedAt;
    }
}
