package com.example.team_foxhound.minicapstone_project.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.example.team_foxhound.minicapstone_project.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleMap nMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        nMap = googleMap;

        // Add a marker in Montreal and move the camera
        LatLng montreal = new LatLng(45.5017, -73.5673);
        LatLng montreal2 = new LatLng(45.4500,-73.5850);

        mMap.addMarker(new MarkerOptions().position(montreal).title("Starting Point").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(montreal));
        mMap.setMyLocationEnabled(true);
        mMap.getMaxZoomLevel();
        

        nMap.addMarker(new MarkerOptions().position(montreal2).title("End Point").draggable(true));
        nMap.moveCamera(CameraUpdateFactory.newLatLng(montreal2));
        nMap.getMaxZoomLevel();


        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(montreal, montreal2)
                .width(5).color(Color.RED));
       line.isVisible();
    }


//    FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);


        public void setdirections(View v) {
            Toast.makeText(getApplicationContext(), "Get Directions", Toast.LENGTH_LONG).show();
        }
    }



