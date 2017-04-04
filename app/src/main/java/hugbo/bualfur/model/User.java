package hugbo.bualfur.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by stefanc on 04/04/17.
 */

public class User {

    private UUID mId;
    private String mFacebookId;
    private String mFirstName;
    private String mLastName;
    private String mAgeRange;
    private String mGender;
    private boolean mLandlord;
    private boolean mRenter;
    private String mEmail;
    private String mPhone;
    private String mInfo;
    private String mImageUrl;
    private String mProfileUrl;
    private ArrayList<Conversation> mConversations;


    public User(String mFacebookId, String mFirstName, String mLastName, String mAgeRange, String mGender, boolean mLandlord, boolean mRenter, String mEmail, String mPhone, String mInfo, String mImageUrl, String mProfileUrl, ArrayList<Conversation> mConversations) {
        this.mId = UUID.randomUUID();
        this.mFacebookId = mFacebookId;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mAgeRange = mAgeRange;
        this.mGender = mGender;
        this.mLandlord = mLandlord;
        this.mRenter = mRenter;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mInfo = mInfo;
        this.mImageUrl = mImageUrl;
        this.mProfileUrl = mProfileUrl;
        this.mConversations = mConversations;
    }

    public User(String mFacebookId, String mFirstName, String mLastName, String mAgeRange, String mGender, String mEmail, String mPhone, String mInfo, String mImageUrl, String mProfileUrl) {
        this.mFacebookId = mFacebookId;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mAgeRange = mAgeRange;
        this.mGender = mGender;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mInfo = mInfo;
        this.mImageUrl = mImageUrl;
        this.mProfileUrl = mProfileUrl;
    }

    public void setmFacebookId(String mFacebookId) {
        this.mFacebookId = mFacebookId;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }


    public void setmLandlord(boolean mLandlord) {
        this.mLandlord = mLandlord;
    }

    public void setmRenter(boolean mRenter) {
        this.mRenter = mRenter;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public void setmInfo(String mInfo) {
        this.mInfo = mInfo;
    }

    public UUID getmId() {
        return mId;
    }

    public String getmFacebookId() {
        return mFacebookId;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public boolean ismLandlord() {
        return mLandlord;
    }

    public boolean ismRenter() {
        return mRenter;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmPhone() {
        return mPhone;
    }

    public String getmInfo() {
        return mInfo;
    }

    public ArrayList<Conversation> getmConversations() {
        return mConversations;
    }

    public User(){}








}
