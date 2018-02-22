package com.example.pamparampa.todocalendar.calendarView;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-01-18.
 */

class CalendarRect {
    private int col;
    private int row;
    private float x;
    private float y;
    private float width;
    private float height;
    private final Date dateTime;
    private final Paint paint;
    private final Calendar calendar;

    public CalendarRect(Date dateTime, int col, int row) {
        this.col = col;
        this.row = row;
        this.dateTime = dateTime;

        calendar = Calendar.getInstance();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    public void setCoordinates(float w, float h) {
        width = w;
        height = h;
        x = col * width;
    }

    public void draw(Canvas canvas, int boardLeftPad) {
        canvas.drawRect(
                boardLeftPad + x,
                0,
                boardLeftPad + x + width,
                height,
                paint);
    }
}
