package hugbo.bualfur.controller;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;

import hugbo.bualfur.model.User;
import hugbo.bualfur.services.SessionManager;
import hugbo.bualfur.services.UserCallback;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("PropertyListActivity", "protected created");
        // Check if user is logged in and start appropriate activity
        if(AccessToken.getCurrentAccessToken() == null) {
            launchLoginActivty();
        } else {
            SessionManager.getInstance(getApplicationContext()).getLoggedInUser(new UserCallback() {
                @Override
                public void onSuccess(User user) {
                    createFragment();
                }
            });
        }
    }

    @Override
    protected Fragment createFragment() {
        Log.v("Main", "Fragment created");
        return new PropertyListFragment();
    }

    public void launchLoginActivty(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
