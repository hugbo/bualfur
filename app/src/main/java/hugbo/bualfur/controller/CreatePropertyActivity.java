package hugbo.bualfur.controller;

import android.support.v4.app.Fragment;

/**
 * Created by oddgeir on 3.4.2017.
 */

public class CreatePropertyActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CreatePropertyFragment();
    };

}
