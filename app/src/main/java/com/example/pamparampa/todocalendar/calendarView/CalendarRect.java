package com.example.pamparampa.todocalendar.calendarView;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-01-18.
 */

class CalendarRect {
    private final int col;
    private final int row;
    private final Date dateTime;
    private float x;
    private float width;
    private float height;
    private CalendarParameters params;

    public CalendarRect(Date dateTime, int col, int row, int width, int height, CalendarParameters params) {
        this.col = col;
        this.row = row;
        this.dateTime = dateTime;
        this.params = params;
        setCoordinates(width, height);
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
                params.getLinePaint());
    }
}
