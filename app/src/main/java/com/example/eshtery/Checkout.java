package com.example.eshtery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eshtery.RecyclerViewClasses.ItemsList;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

        Bundle b = getIntent().getExtras();
        String total = b.getString("total");
        Button btnPlace = findViewById(R.id.buttonPlaceOrder);

        TextView price = findViewById(R.id.textViewPrice);
        price.setText(total+'$');



        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
                String email = ((EditText)findViewById(R.id.editTextEmail)).getText().toString();
                String address = ((EditText)findViewById(R.id.editTextAddress)).getText().toString();
                String appartment = ((EditText)findViewById(R.id.editTextAppartment)).getText().toString();
                String geographicLoc = ((TextView)findViewById(R.id.textViewGeographicAddress)).getText().toString();

                String subject = "Eshtery Order";
                String message = "Your order has been place successfully with a total of " + total + "$\n\n Hope you are satisfied with our application!";

                //JavaMailAPI api = new JavaMailAPI(getApplicationContext(), email, subject,message);
                //api.execute();
                Database db = new Database(getApplicationContext());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                SharedPreferences pref = getSharedPreferences("CART", MODE_PRIVATE);
                Map<String,?> keys = pref.getAll();
                for(Map.Entry<String,?> entry : keys.entrySet()){
                    Cursor C = db.fetchCurrentItem(entry.getValue().toString());
                    byte[] image = C.getBlob(6);
                    ItemsList item = new ItemsList(BitmapFactory.decodeByteArray(image,0,image.length),C.getString(1), C.getString(2), R.drawable.subtract,R.drawable.add);
                    db.insertOrder(item.getName(), ShoppingCart.quantity.get(item.getName()),dtf.format(now), address, geographicLoc, email,appartment);
                }

                Toast.makeText(getApplicationContext(), "Order Placed Successfully", Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(Checkout.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                getLocation();
            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
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