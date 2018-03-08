package com.example.pamparampa.todocalendar.calendarView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-03-03.
 */

public abstract class CalendarRowView extends View {

    protected int rowUnit;
    private final CalendarRect[] rects;
    private final int numberOfCols = 7; // TODO byc moze lepiej to pobierac z params
    private final Calendar calendar;
    private int rowId;
    private Date date;
    private CalendarParams params;
    private CalendarSizesManager sizesManager;
    private Paint labelTextPaint;

    public CalendarRowView(Context context, AttributeSet attrs) {
        super(context, attrs);

        rowId = 0;
        calendar = Calendar.getInstance();
        setDate(new Date());
        setRowUnit();
        rects = new CalendarRect[numberOfCols];
    }

    public void setDate(Date date) {
        this.date = date;
        calendar.setTime(CalendarUtil.getFirstDayOfWeek(date));
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
        invalidate();
    }

    public void setParams(CalendarParams params) {
        this.params = params;
        sizesManager = params.getSizesManager();
        labelTextPaint = params.getLabelTextPaint();
    }

    public void compose() throws IllegalStateException {
        if (sizesManager == null || params == null) throw new IllegalStateException();

        calendar.setTime(date);
        for (int colId = 0; colId < numberOfCols; colId++) {
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            CalendarRect rect = new CalendarRect(
                    calendar.getTime(),
                    colId,
                    rowId,
                    sizesManager.getRectWidth(),
                    sizesManager.getRectHeight(),
                    params
            );
            rects[colId] = rect;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (sizesManager == null || labelTextPaint == null) return;

        DecimalFormat formatter = new DecimalFormat("00");
        calendar.setTime(date);
        calendar.set(rowUnit, rowId);
        canvas.drawText(
                formatter.format(calendar.get(Calendar.HOUR_OF_DAY)) + ":00",
                sizesManager.getTextLeftShift(),
                sizesManager.getTextLineShift(1),
                labelTextPaint
        );
        for (int col = 0; col < numberOfCols; col++) {
            rects[col].draw(canvas);
        }
    }

    protected abstract void setRowUnit();
}
