package hugbo.bualfur;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * Created by egill on 13.3.2017.
 */

public class PropertyListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PropertyListFragment();
   }
}
