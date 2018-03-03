package com.example.pamparampa.todocalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pamparampa.todocalendar.calendarView.CalendarView;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.calendar_week_row);

    }
}
