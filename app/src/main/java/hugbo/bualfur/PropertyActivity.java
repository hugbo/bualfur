package hugbo.bualfur;

import android.support.v4.app.Fragment;

/**
 * Created by oddgeir on 19.3.2017.
 */

public class PropertyActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() { return new ViewPropertyFragment(); };
}
