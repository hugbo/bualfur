package hugbo.bualfur.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by oddgeir on 3.4.2017.
 */

public class CreatePropertyActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, CreatePropertyActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new CreatePropertyFragment();
    };

}
