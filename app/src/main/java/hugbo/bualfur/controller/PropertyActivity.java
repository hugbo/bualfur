package hugbo.bualfur.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

import hugbo.bualfur.model.Property;
import hugbo.bualfur.services.PropertyService;

/**
 * Created by oddgeir on 19.3.2017.
 */

/**
 *  Activity that invokes the view property fragment
 */
public class PropertyActivity extends SingleFragmentActivity {

    private static final String EXTRA_PROPERTY_ID = "hugbo.bualfur.propertyintent.property_id";

    /**
     * Creates an intent with the ID of the property to be viewed
     * @param packageContext
     * @param propertyID
     * @return
     */
    public static Intent newIntent(Context packageContext, UUID propertyID){
        Intent intent = new Intent(packageContext, PropertyActivity.class);
        intent.putExtra(EXTRA_PROPERTY_ID, propertyID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        UUID propertyId = (UUID) getIntent().getSerializableExtra(EXTRA_PROPERTY_ID);
        Log.i("PropetyActivity", propertyId.toString());
        Property tmpProperty = PropertyService.getInstance(this).getProperty(propertyId);
        Log.i("PropAct", tmpProperty.getmAddress());
        return ViewPropertyFragment.newInstance(propertyId);
    };
}
