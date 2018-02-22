package com.example.pamparampa.todocalendar.calendarView;

import android.graphics.Paint;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-02-01.
 */

class CalendarParameters {

    private int numberOfRows;
    private CalendarSizesManager sizesManager;
    private Calendar calendar;
    private Date firstVisibleDate;
    private Paint labelTextPaint;

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
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setFirstVisibleDate(Date firstVisibleDate) {
        this.firstVisibleDate = firstVisibleDate;
    }

    public void setLabelTextPaint(Paint labelTextPaint) {
        this.labelTextPaint = labelTextPaint;
    }
}
