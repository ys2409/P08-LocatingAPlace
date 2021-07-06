package com.myapplicationdev.android.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);

                LatLng poi_Sg = new LatLng(1.352083, 103.819836);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Sg, 11));

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED){
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                LatLng poi_north = new LatLng(1.4484, 103.7790);

                Marker n = map.addMarker(new MarkerOptions().position(poi_north).title("HQ - North").snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am - 5pm Tel: 65433456").icon(BitmapDescriptorFactory.fromResource(R.drawable.star)));

                LatLng poi_central = new LatLng(1.3048, 103.8318);
                Marker c = map.addMarker(new MarkerOptions().position(poi_central).title("Central").snippet("Block 3A, Orchard Ave 3, 134542 Operating hours: 11am - 8pm Tel: 67788652").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_east = new LatLng(1.3496, 103.9568);
                Marker e = map.addMarker(new MarkerOptions().position(poi_east).title("East").snippet("Block 555, Tampines Ave 3, 287788 Operating hours: 9am - 5pm Tel: 66776677").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null){
                    LatLng poi_north = new LatLng(1.4484, 103.7790);
                    //Marker n = map.addMarker(new MarkerOptions().position(poi_north).title("HQ - North").snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am - 5pm Tel: 65433456").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north, 15));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null){
                    LatLng poi_central = new LatLng(1.3048, 103.8318);
                    //Marker c = map.addMarker(new MarkerOptions().position(poi_central).title("Central").snippet("Block 3A, Orchard Ave 3, 134542 Operating hours: 11am - 8pm Tel: 67788652").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central, 15));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map != null) {
                    LatLng poi_east = new LatLng(1.3496, 103.9568);
                    //Marker e = map.addMarker(new MarkerOptions().position(poi_east).title("East").snippet("Block 555, Tampines Ave 3, 287788 Operating hours: 9am - 5pm Tel: 66776677").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east, 15));
                }
            }
        });
    }
}