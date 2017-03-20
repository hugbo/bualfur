package hugbo.bualfur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static hugbo.bualfur.R.id.map;

/**
 * Created by Hildur on 3/19/2017.
 * This class handles the Google Maps activity which displays
 * all of the current properties on a map.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    /**
     * Instance variables
     */
    private GoogleMap mMap;
    private List<Marker> mMarkers;

    /**
     * Creates a new intent for the Map Activity
     * @param packageContext
     * @return intent for MapActivity
     */
    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, MapActivity.class);
        return intent;
    }

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


    /**
     * Callback function for the Google Maps map once it initializes.
     * @param googleMap
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {


        ArrayList<Property> properties = PropertyFetcher.getInstance(this).getProperties();

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        //Create markers
        for (Property property: properties){
            LatLng latLng = new LatLng(property.getmLat(), property.getmLon());
            googleMap.addMarker(new MarkerOptions().position(latLng).title(property.getmAddress()));
            builder.include(latLng);

        }

        LatLngBounds bounds = builder.build();

        // Set padding so that we don't zoom in too much
        int padding = 100;

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

        googleMap.moveCamera(cu);
    }
}
