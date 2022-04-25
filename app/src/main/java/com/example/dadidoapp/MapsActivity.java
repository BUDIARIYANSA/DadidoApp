package com.example.dadidoapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.dadidoapp.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Button btn_maps;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_maps = (Button) findViewById(R.id.btnmaps) ;
        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, ProfilActivity.class);
                intent.putExtra("map_position", location);
                startActivity(intent);
            }
        });

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
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;


            LatLng salatiga = new LatLng(-7.3305, 110.5084 );
            mMap.addMarker(new MarkerOptions().position(salatiga).title("Marker in Salatiga"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(salatiga, 15.0f));
            mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title("New Marker"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
        location = getCompleteAddressString(latLng);

    }

    private String getCompleteAddressString(LatLng latLng) {
        String ret = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder stringBuilder = new StringBuilder("");
                for(int i=0; i<= returnedAddress.getMaxAddressLineIndex();i++) {
                    stringBuilder.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                ret = stringBuilder.toString();
                Log.w("CurrentLocation", ret);
            }
        } catch (Exception ex) {
            Log.w("CurrentLocation", "Can't get Address");
        }
        return ret;
    }
}