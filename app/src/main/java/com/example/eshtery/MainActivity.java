package com.example.eshtery;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().setIcon(R.drawable.wide_logo);
        //getActionBar().setTitle("");
        //getActionBar().hide();
        ActionBar bar = getSupportActionBar();
        bar.hide();
        //bar.setDisplayShowHomeEnabled(true);
        //bar.setIcon(R.drawable.wide_logo);
        TextView register = (TextView)findViewById(R.id.textViewRegister);
        TextView forgotP = (TextView)findViewById(R.id.textViewForgotPassword);
        Button loginBtn = (Button)findViewById(R.id.btnLogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, Register.class);
                startActivity(I);
            }
        });

        forgotP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, ResetPassword.class);
                startActivity(I);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, MainScreen.class);
                startActivity(I);
            }
        });
    }
}