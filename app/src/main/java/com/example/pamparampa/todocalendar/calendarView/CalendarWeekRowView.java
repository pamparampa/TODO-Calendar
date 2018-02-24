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

class CalendarWeekRowView extends View{
    private CalendarParameters params;
    private Date date;
    private CalendarRect[] rects;
    private final int numberOfCols = 7;
    private int row;
    private Calendar calendar;
    private CalendarSizesManager sizesManager;
    private Paint labelTextPaint;

    public CalendarWeekRowView(Context context, AttributeSet attrs) {
        super(context, attrs);

        date = new Date();
        row = 0;

        calendar = Calendar.getInstance();
        calendar.setTime(CalendarUtil.getFirstDayOfWeek(date));

        rects = new CalendarRect[numberOfCols];
    }

    public void setDate(Date date) {
        this.date = date;
        calendar.setTime(CalendarUtil.getFirstDayOfWeek(date));
    }

    public void setRow(int row) {
        this.row = row;
        invalidate();
    }

    public void setParams(CalendarParameters params) {
        this.params = params;
        labelTextPaint = params.getLabelTextPaint();
    }

    public void setSizesManager(CalendarSizesManager sizesManager) {
        this.sizesManager = sizesManager;
    }

    public void compose() {
        super.invalidate();

        for (int col = 0; col < numberOfCols; col++) {          // TODO przeniesc do innej metody?
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            CalendarRect rect = new CalendarRect(
                    calendar.getTime(),
                    col,
                    row,
                    sizesManager.getRectWidth(),
                    sizesManager.getRectHeight(),
                    params
                    );
            rects[col] = rect;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (sizesManager == null || labelTextPaint == null) return;

        DecimalFormat formatter = new DecimalFormat("00");
        calendar.set(Calendar.HOUR_OF_DAY, row);    // TODO dla innego widoku ma byc inna jednostka
        canvas.drawText(
                formatter.format(calendar.get(Calendar.HOUR_OF_DAY)) + ":00",
                sizesManager.getTextLeftShift(),
                sizesManager.getTextLineShift(1),
                labelTextPaint
        );
        for (int col = 0; col < numberOfCols; col++) {
            rects[col].draw(canvas, sizesManager.getBoardLeftPad());
        }
    }
}
