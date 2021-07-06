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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spn;
    private GoogleMap map;
    String[] places = {"North", "Central", "East"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        spn = (Spinner) findViewById(R.id.spinner);
        spn.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, places);
        aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spn.setAdapter(aa);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);

                LatLng poi_Sg = new LatLng(1.352083, 103.819836);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Sg, 11));

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
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

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String title = marker.getTitle();
                        if (title.equalsIgnoreCase("North - HQ")) {
                            Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
                        } else if (title.equalsIgnoreCase("Central")) {
                            Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (map != null) {
            if (i == 0) {
                Log.d("TAG", "onItemSelected: ");
                LatLng poi_north = new LatLng(1.4484, 103.7790);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north, 15));

        } else if (i == 1) {
            LatLng poi_central = new LatLng(1.3048, 103.8318);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central, 15));
        } else {
            LatLng poi_east = new LatLng(1.3496, 103.9568);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east, 15));
        }
    }

}


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}