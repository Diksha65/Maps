package com.example.diksha.bluemaps;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = "MapsActivity";
    private static DataStash dataStash = DataStash.getsDataStash();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        dataStash.googleMap = googleMap;
        StyleUtils.setGoogleMapStyle(MapsActivity.this, dataStash.googleMap, R.raw.blue_maps);
        setMarkers();

        CameraPosition cameraPosition = CameraPosition
                .builder(dataStash.googleMap.getCameraPosition())
                .target(dataStash.getLatLng())
                .zoom(16)
                .tilt(25)
                .build();

        dataStash.googleMap
                .animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition)
                );
    }

    private void setMarkers(){
        dataStash.googleMap.clear();
        int i = 0;
        for(LatLng latLng : dataStash.getLatLngs()){
            i += 1;
            dataStash.googleMap.addMarker(new MarkerOptions().flat(true)
                    .position(latLng)
                    .icon(BitmapDescriptorFactory
                            .fromResource(
                                    StyleUtils.getIconResourceID(i)
                            )));
            dataStash.googleMap.addCircle(new CircleOptions().center(latLng).radius(10)
                    .strokeColor(Color.RED)
                    .fillColor(Color.BLUE)
            );
        }
    }
}
