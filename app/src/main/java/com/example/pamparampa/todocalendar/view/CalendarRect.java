package com.example.pamparampa.todocalendar.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-01-18.
 */

public class CalendarRect {
    private int col;
    private int row;
    private int x;
    private int y;
    private int width;
    private int height;
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
    }

    public void setCoordinates(int w, int h) {
        width = w;
        height = h;
        x = col * width;
        y = row * height;
    }

    public void draw(Canvas canvas, int boardLeftPad) {
        canvas.drawRect(
                boardLeftPad + x,
                y,
                boardLeftPad + x + width,
                y + height,
                paint);
    }
}
