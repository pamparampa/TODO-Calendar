package com.example.pamparampa.todocalendar.calendarView;

import android.graphics.Paint;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-02-01.
 */

class CalendarParameters {

    private final Paint labelLinePaint = new Paint();
    private final Paint linePaint = new Paint();
    private int numberOfRows;
    private CalendarSizesManager sizesManager;
    private Calendar calendar;
    private Date firstVisibleDate;
    private final Paint labelTextPaint = new Paint();

    public CalendarParameters() {
        labelTextPaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);
        labelLinePaint.setStyle(Paint.Style.STROKE);
        labelLinePaint.setAntiAlias(true);
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getBoardLeftPad() {
        return sizesManager.getBoardLeftPad();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Date getFirstVisibleDate() {
        return firstVisibleDate;
    }


    public CalendarSizesManager getSizesManager() {
        return sizesManager;
    }

    public Paint getLabelTextPaint() {
        return labelTextPaint;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public void setSizesManager(CalendarSizesManager sizesManager) {
        this.sizesManager = sizesManager;
        labelLinePaint.setStrokeWidth(sizesManager.getBoldLineWidth());
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setFirstVisibleDate(Date firstVisibleDate) {
        this.firstVisibleDate = firstVisibleDate;
    }

    public Paint getLinePaint() {
        return linePaint;
    }

    public Paint getLabelLinePaint() {
        return labelLinePaint;
    }

    public void setTextSize(float textSize) {
        labelTextPaint.setTextSize(textSize);
    }
}
