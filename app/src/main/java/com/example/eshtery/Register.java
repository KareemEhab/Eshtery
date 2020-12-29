package com.example.eshtery;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;


public class Register extends AppCompatActivity {
    Button calendar;
    DatePicker cView;

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
        cView.setVisibility(View.INVISIBLE);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cView = (DatePicker)findViewById(R.id.calendarView);
                toggleVisibility();
                cView.bringToFront();
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