package hugbo.bualfur.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by stefanc on 04/04/17.
 */

public class Conversation {

    private UUID mId;
    private String mServerId;
    private Date mTimestamp;
    private User mSender;
    private User mRecipient;
    private List<Message> mMessages;

    public Conversation(String mServerId) {
        this.mServerId = mServerId;
    }

    public Conversation(String mServerId, Message message){
        this.mServerId = mServerId;
        mMessages.add(message);
    }


    public Conversation(UUID mId, String mServerId, Date mTimestamp, User mSender, User mRecipient, ArrayList<Message> mMessages) {
        this.mId = mId;
        this.mServerId = mServerId;
        this.mTimestamp = mTimestamp;
        this.mSender = mSender;
        this.mRecipient = mRecipient;
        this.mMessages = mMessages;
    }

    public UUID getmId() {
        return mId;
    }

    public String getmServerId() {
        return mServerId;
    }

    public Date getmTimestamp() {
        return mTimestamp;
    }

    public User getmSender() {
        return mSender;
    }

    public User getmRecipient() {
        return mRecipient;
    }

    public ArrayList<Message> getmMessages() {
        return (ArrayList<Message>) mMessages;
    }
}
