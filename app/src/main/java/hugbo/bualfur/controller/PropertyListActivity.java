package hugbo.bualfur.controller;

import android.support.v4.app.Fragment;

/**
 * Created by egill on 13.3.2017.
 * PropertyListActivity which initializes the PropertyListFragment
 */

public class PropertyListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PropertyListFragment();
   }
}
