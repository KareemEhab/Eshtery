package com.example.eshtery;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView register;
    TextView forgotP;
    Button loginBtn;
    EditText username;
    EditText password;
    CheckBox rememberMe;
    String usernameStr = "";
    String passwordStr = "";
    boolean isChecked = false;

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
        register = (TextView)findViewById(R.id.textViewRegister);
        forgotP = (TextView)findViewById(R.id.textViewForgotPassword);
        loginBtn = (Button)findViewById(R.id.btnLogin);
        username = (EditText)findViewById(R.id.editTextEmail);
        password = (EditText)findViewById(R.id.editTextPassword);
        rememberMe = (CheckBox)findViewById(R.id.checkBoxRememberMe);
        loadData();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                usernameStr = username.getText().toString();
                passwordStr = password.getText().toString();
                rememberMe();
                Intent I = new Intent(MainActivity.this, MainScreen.class);
                startActivity(I);
            }
        });

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
                Intent I = new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(I);
            }
        });
    }

    public void rememberMe()
    {
            SharedPreferences pref = getSharedPreferences("PREFS", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", usernameStr);
            editor.putString("password", passwordStr);
            editor.putBoolean("checked", rememberMe.isChecked());
            editor.apply();
    }
    public void loadData()
    {
        SharedPreferences pref = getSharedPreferences("PREFS", MODE_PRIVATE);
        isChecked = pref.getBoolean("checked",false);
        if(isChecked)
        {
            usernameStr = pref.getString("username", "");
            passwordStr = pref.getString("password", "");
        }
        username.setText(usernameStr);
        password.setText(passwordStr);
        rememberMe.setChecked(isChecked);
    }
}