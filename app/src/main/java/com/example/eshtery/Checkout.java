package com.example.eshtery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Checkout extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView textGeographic;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Button btn = findViewById(R.id.buttonLocation);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        textGeographic = findViewById(R.id.textViewGeographicAddress);
        context = this;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(Checkout.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                getLocation();
                Toast.makeText(context,"EEEEE2",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        Toast.makeText(context,"EEEEE",Toast.LENGTH_LONG).show();
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location loc = task.getResult();
                if (loc != null) {
                    try {
                        Geocoder geocoder = new Geocoder(Checkout.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                        textGeographic.setText(Html.fromHtml("<font color = '000000'><b>Address:</b><br></font>" + addresses.get(0).getAddressLine(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}