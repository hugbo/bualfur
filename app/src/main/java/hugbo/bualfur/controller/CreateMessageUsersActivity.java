package hugbo.bualfur.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by egill on 5.4.2017.
 */

public class CreateMessageUsersActivity extends SingleFragmentActivity {
    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, CreateMessageUsersActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new CreateMessageUsersFragment();
    };
}
