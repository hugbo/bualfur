package hugbo.bualfur.model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by stefanc on 04/04/17.
 */

public class Conversation {

    private String TAG = "Conversation";

    private UUID mId;
    private int mServerId;
    private String mSubject;
    private Date mCreatedAt;
    private Date mUpdatedAt;
    private List<Message> mMessages;

    public Conversation(int mServerId) {
        this.mServerId = mServerId;
    }

    public Conversation(int mServerId, Message message){
        this.mServerId = mServerId;
        mMessages.add(message);
    }


    public Conversation(int mServerId, String mSubject, String mCreatedAt, String mUpdateAt, ArrayList<Message> mMessages) {
        this.mId = UUID.randomUUID();
        this.mServerId = mServerId;
        this.mSubject = mSubject;
        String createdDateString = mCreatedAt.substring(0, mCreatedAt.length() - 2);
        String updatedDateString = mUpdateAt.substring(0, mUpdateAt.length() - 2);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");
        try {
            this.mCreatedAt = formatter.parse(createdDateString);
            this.mUpdatedAt = formatter.parse(updatedDateString);
        } catch (ParseException e) {
            Log.e(TAG, "Conversation: " + e.getStackTrace());
        }
        this.mMessages = mMessages;
    }

    public UUID getmId() {
        return mId;
    }

    public int getmServerId() {
        return mServerId;
    }

    public String getSubject() { return mSubject; }

    public Date getmCreatedAt() { return mCreatedAt; }

    public Date getmUpdatedAt() { return mUpdatedAt; }

    public ArrayList<Message> getmMessages() {
        return (ArrayList<Message>) mMessages;
    }
}
