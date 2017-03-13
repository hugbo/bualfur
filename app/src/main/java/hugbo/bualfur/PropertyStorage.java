package hugbo.bualfur;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by egill on 13.3.2017.
 */

// Singleton class to store Property instances
public class PropertyStorage {
    private static PropertyStorage sPropertyStorage;
    private ArrayList<Property> mProperties;

    public static PropertyStorage get(Context context){
        if (sPropertyStorage == null){
            sPropertyStorage = new PropertyStorage(context);
        }
        return sPropertyStorage;
    }

    private PropertyStorage(Context context){
        mProperties = new ArrayList<Property>();
        // Dummy properties to be used
        Property tmpProp = new Property("Smyrlahraun 50", 201, "Hafnarfjörður", 60, 60, 500, 50, 1, 1, "Einbýlishús");
        mProperties.add(tmpProp);
        tmpProp = new Property("Gunnlaugsstræti 33", 105, "Reykjavík", 60, 60, 800, 70, 2, 1, "Einbýlishús");
        mProperties.add(tmpProp);
        tmpProp = new Property("Gerðargata 12", 101, "Reykjavík", 60, 60, 300, 40, 1, 2, "Fjölbýlishús");
        mProperties.add(tmpProp);
    }

    public ArrayList<Property> getProperties(){
        return mProperties;
    }

    public Property getProperty(UUID id){
        for (Property property : mProperties){
            if (property.getmId().equals(id)){
                return property;
            }
        }
        return null;
    }
}
