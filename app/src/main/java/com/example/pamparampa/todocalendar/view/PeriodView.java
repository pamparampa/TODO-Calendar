package com.example.pamparampa.todocalendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-01-03.
 */

public abstract class PeriodView extends View{

    protected final Date date;
    protected Date firstVisibleDate;

    protected int width;
    protected int height;
    protected int rectWidth;
    protected int rectHeight;
    protected int boardLeftPad;
    protected int boardTopPad;
    protected int numberOfCols;
    protected int numberOfRows;

    public PeriodView(Context context, Date date) {
        super(context);
        this.date = date;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;

        boardLeftPad = w / 8;
        boardTopPad = w / 8;

        rectWidth = (width - boardLeftPad) / numberOfCols;
        rectHeight = (height - boardTopPad) / numberOfRows;
    }

    protected Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 1);

        return calendar.getTime();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int col = 0; col < numberOfCols; col++) {
            for (int row = 0; row < numberOfRows; row++) {
                drawRect(canvas, col, row);
            }
        }
    }

    protected abstract void drawRect(Canvas canvas, int col, int row);
}
