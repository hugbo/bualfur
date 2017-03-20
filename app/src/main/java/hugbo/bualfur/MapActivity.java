package hugbo.bualfur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.ArrayList;

import static hugbo.bualfur.R.id.map;

/**
 * Created by Hildur on 3/19/2017.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_map);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        JSONObject data = PropertyFetcher.getInstance(this).defaultParameters();
        PropertyFetcher.getInstance(this).searchProperties(data, new ServerCallback() {
            @Override
            public void onSuccess(ArrayList<Property> results) {
                for (Property property: results){
                    LatLng marker = new LatLng(property.getmLat(), property.getmLon());
                    googleMap.addMarker(new MarkerOptions().position(marker).title(property.getmAddress()));
                }
            }
        });
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }
}
