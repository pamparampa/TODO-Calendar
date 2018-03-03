package com.example.pamparampa.todocalendar.calendarView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.pamparampa.todocalendar.R;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-02-01.
 */

class CalendarWeekRowView extends CalendarRowView{

    public CalendarWeekRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void setRowUnit() {
        rowUnit = Calendar.HOUR_OF_DAY;
    }

}
