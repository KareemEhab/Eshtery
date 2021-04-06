package com.example.eshtery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPassword extends AppCompatActivity {

    public static String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnRst = findViewById(R.id.buttonRst);
        EditText pass = findViewById(R.id.editTextPass);
        EditText cfnrmpass = findViewById(R.id.editTextConfirmPass);

        btnRst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.getText().toString().equals(cfnrmpass.getText().toString()))
                {
                    Database db = new Database(getApplicationContext());
                    db.updatePassword(email, pass.getText().toString());
                    Toast.makeText(getApplicationContext(), "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Not the same password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}