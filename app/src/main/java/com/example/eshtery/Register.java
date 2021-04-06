package com.example.eshtery;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Register extends AppCompatActivity {
    Button calendar;
    DatePicker cView;
    Button register;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.wide_logo_mod);

        calendar = (Button)findViewById(R.id.btnCalendar);
        cView = (DatePicker)findViewById(R.id.calendarView);
        register = findViewById(R.id.btnRegister);
        cView.setVisibility(View.INVISIBLE);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cView = (DatePicker)findViewById(R.id.calendarView);
                toggleVisibility();
                cView.bringToFront();
            }
        });

        cView.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                //Toast.makeText(getApplicationContext(),String.valueOf(datePicker.getDayOfMonth())+ ' ' + String.valueOf(datePicker.getMonth()) + ' ' + String.valueOf(datePicker.getYear()), Toast.LENGTH_LONG).show();
                TextView date = (TextView)findViewById(R.id.textChangeBirthDate);
                date.setText(String.valueOf(datePicker.getDayOfMonth())+ '/' + String.valueOf(datePicker.getMonth()+1) + '/' + String.valueOf(datePicker.getYear()));
                toggleVisibility();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getApplicationContext());
                String Name = ((EditText)findViewById(R.id.txtName)).getText().toString();
                String Email = ((EditText)findViewById(R.id.txtEmail)).getText().toString();
                String Password = ((EditText)findViewById(R.id.txtPassword)).getText().toString();
                int GenderGroup = ((RadioGroup)findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
                String Gender;
                if(GenderGroup == R.id.radioMale)
                    Gender = "Male";
                else
                    Gender = "Female";

                String Birthdate = ((TextView)findViewById(R.id.textChangeBirthDate)).getText().toString();
                String Job = ((Spinner)findViewById(R.id.spnrJob)).getSelectedItem().toString();

                if(db.insertIntoCustomers(Name, Email, Password, Gender, Birthdate, Job))
                    Toast.makeText(getApplicationContext(), "Account created successfully!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void toggleVisibility()
    {
        if(cView.getVisibility() == View.VISIBLE)
            cView.setVisibility(View.INVISIBLE);
        else
            cView.setVisibility(View.VISIBLE);
    }
}