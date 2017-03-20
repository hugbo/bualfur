package hugbo.bualfur;

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

import static hugbo.bualfur.R.id.map;

/**
 * Created by Hildur on 3/19/2017.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private List<Marker> mMarkers;

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
                LatLngBounds.Builder builder = new LatLngBounds.Builder();

                for (Property property: results){
                    LatLng latLng = new LatLng(property.getmLat(), property.getmLon());
                    googleMap.addMarker(new MarkerOptions().position(latLng).title(property.getmAddress()));
                    builder.include(latLng);

                }

                LatLngBounds bounds = builder.build();

                int padding = 100;

                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

                googleMap.moveCamera(cu);
            }
        });
    }
}
