package hugbo.bualfur.controller;

import android.support.v4.app.Fragment;

public class LoginActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }

    /**
     * Disables user from exiting the login activity without having logged in
     */
    @Override
    public void onBackPressed() {

    }
}
