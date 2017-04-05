package hugbo.bualfur.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by egill on 13.3.2017.
 * PropertyListActivity which initializes the PropertyListFragment
 */

public class PropertyListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, PropertyListActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new PropertyListFragment();
   }
}
