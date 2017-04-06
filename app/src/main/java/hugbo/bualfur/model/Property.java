package hugbo.bualfur.model;

import java.util.UUID;

/**
 * Created by egill on 12.3.2017.
 * A POJO for our Properties
 */

public class Property {
    /**
     * Instance variables
     */
    private UUID mId;
    private String mServerId;
    private String mAddress;
    private int mZipcode;
    private String mCity;
    private int mPrice;
    private int mSize;
    private double mLat;
    private double mLon;
    private int mNumBedrooms;
    private int mNumBathrooms;

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    private String mDescription;
    private String mPropertyType;


    /**
     * Constructor
     * @param serverPropertyId
     * @param address
     * @param zipcode
     * @param city
     * @param price
     * @param size
     * @param lat
     * @param lon
     * @param numBedrooms
     * @param numBathroooms
     * @param propertyType
     */
    public Property(String serverPropertyId, String address, int zipcode, String city,
                    int price, int size, double lat, double lon,
                    int numBedrooms, int numBathroooms, String propertyType) {
        mId = UUID.randomUUID();
        mServerId = serverPropertyId;
        mAddress = address;
        mZipcode = zipcode;
        mCity = city;
        mPrice = price;
        mSize = size;
        mLat = lat;
        mLon = lon;
        mNumBedrooms = numBedrooms;
        mNumBathrooms = numBathroooms;
        mPropertyType = propertyType;
    }

    public Property(){
        mId = UUID.randomUUID();
    }

    //Getters and setters

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public void setmServerId(String mServerId) {
        this.mServerId = mServerId;
    }

    public String getPhotoFilename(){
        return "IMG_"+getmId().toString()+".jpg";
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public void setmZipcode(int mZipcode) {
        this.mZipcode = mZipcode;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public void setmLat(double mLat) {
        this.mLat = mLat;
    }

    public void setmLon(double mLon) {
        this.mLon = mLon;
    }

    public void setmNumBedrooms(int mNumBedrooms) {
        this.mNumBedrooms = mNumBedrooms;
    }

    public void setmNumBathrooms(int mNumBathrooms) {
        this.mNumBathrooms = mNumBathrooms;
    }

    public void setmPropertyType(String mPropertyType) {
        this.mPropertyType = mPropertyType;
    }

    public UUID getmId() {
        return mId;
    }

    public String getmServerId() {
        return mServerId;
    }


    public String getmAddress() {
        return mAddress;
    }

    public int getmZipcode() {
        return mZipcode;
    }

    public String getmCity() {
        return mCity;
    }

    public int getmPrice() {
        return mPrice;
    }

    public int getmSize() {
        return mSize;
    }

    public double getmLat() {
        return mLat;
    }

    public double getmLon() {
        return mLon;
    }

    public int getmNumBedrooms() {
        return mNumBedrooms;
    }

    public int getmNumBathrooms() {
        return mNumBathrooms;
    }

    public String getmPropertyType() {
        return mPropertyType;
    }
}
