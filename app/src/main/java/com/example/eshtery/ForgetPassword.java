package com.example.eshtery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    Button calendar;
    DatePicker cView;
    Button resetPassword;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendar = (Button)findViewById(R.id.btnCalendarReset);
        cView = (DatePicker)findViewById(R.id.calendarViewReset);
        cView.setVisibility(View.INVISIBLE);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleVisibility();
                cView.bringToFront();
            }
        });

        cView.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                //Toast.makeText(getApplicationContext(),String.valueOf(datePicker.getDayOfMonth())+ ' ' + String.valueOf(datePicker.getMonth()) + ' ' + String.valueOf(datePicker.getYear()), Toast.LENGTH_LONG).show();
                date = (TextView)findViewById(R.id.textChangeBirthDateReset);
                date.setText(String.valueOf(datePicker.getDayOfMonth())+ '/' + String.valueOf(datePicker.getMonth()+1) + '/' + String.valueOf(datePicker.getYear()));
                toggleVisibility();
            }
        });

        resetPassword = findViewById(R.id.button2);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getApplicationContext());
                if(db.checkEmailandDate(((EditText)findViewById(R.id.txtEmail2)).getText().toString(), date.getText().toString())) {
                    ResetPassword.email = ((EditText)findViewById(R.id.txtEmail2)).getText().toString();
                    finish();
                    Intent I = new Intent(getApplicationContext(), ResetPassword.class);
                    startActivity(I);
                }
                else
                    Toast.makeText(getApplicationContext(),"Invalid Information", Toast.LENGTH_SHORT).show();
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