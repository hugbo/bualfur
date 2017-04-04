package hugbo.bualfur.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by stefanc on 04/04/17.
 */

public class Message {

    private UUID mId;
    private String mServerId;
    private String mText;
    private Boolean mRead;
    private Date mTimestamp;


    public Message(String mServerId, String mText, Boolean mRead) {
        this.mServerId = mServerId;
        this.mText = mText;
        this.mRead = mRead;
        this.mTimestamp = new Date();
    }

    public Message(String mText) {
        this.mText = mText;
        this.mServerId = null;
        this.mRead = false;
        this.mTimestamp = new Date();
    }

    public Message(UUID mId, String mServerId, String mText, Boolean mRead, Date mTimestamp) {
        this.mId = mId;
        this.mServerId = mServerId;
        this.mText = mText;
        this.mRead = mRead;
        this.mTimestamp = mTimestamp;
    }

    public UUID getmId() {
        return mId;
    }

    public String getmServerId() {
        return mServerId;
    }

    public String getmText() {
        return mText;
    }

    public Boolean getmRead() {
        return mRead;
    }

    public Date getmTimestamp() {
        return mTimestamp;
    }
}
