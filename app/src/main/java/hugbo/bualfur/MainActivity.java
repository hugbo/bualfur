package hugbo.bualfur;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("PropertyListActivity", "protected created");
        // Check if user is logged in and start appropriate activity
        if(AccessToken.getCurrentAccessToken() == null) {
            launchLoginActivty();
        } else {
            createFragment();
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
