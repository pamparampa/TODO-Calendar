package com.example.pamparampa.todocalendar.calendarView;

import android.graphics.Paint;

import java.util.Date;

/**
 * Created by Pamparampa on 2018-02-01.
 */

class CalendarParams {

    private final Paint labelLinePaint = new Paint();
    private final Paint linePaint = new Paint();
    private int numberOfRows;
    private int numberOfCols;
    private CalendarSizesManager sizesManager;
    private Date firstVisibleDateTime;
    private final Paint labelTextPaint = new Paint();
    private int rowViewResource;

    public CalendarParams() {
        labelTextPaint.setAntiAlias(true);

        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);

        labelLinePaint.setStyle(Paint.Style.STROKE);
        labelLinePaint.setAntiAlias(true);
    }

    public int getNumberOfCols() {
        return numberOfCols;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public Date getFirstVisibleDateTime() {
        return firstVisibleDateTime;
    }

    public CalendarSizesManager getSizesManager() {
        return sizesManager;
    }

    public Paint getLabelTextPaint() {
        return labelTextPaint;
    }

    public Paint getLinePaint() {
        return linePaint;
    }

    public Paint getLabelLinePaint() {
        return labelLinePaint;
    }

    public int getRowViewResource() {
        return rowViewResource;
    }

    public void setNumberOfCols(int numberOfCols) {
        this.numberOfCols = numberOfCols;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public void setSizesManager(CalendarSizesManager sizesManager) {
        this.sizesManager = sizesManager;
        labelLinePaint.setStrokeWidth(sizesManager.getBoldLineWidth());
    }

    public void setFirstVisibleDateTime(Date firstVisibleDateTime) {
        this.firstVisibleDateTime = firstVisibleDateTime;
    }

    public void setTextSize(float textSize) {
        labelTextPaint.setTextSize(textSize);
    }

    public void setRowViewResource(int rowViewResource) {
        this.rowViewResource = rowViewResource;
    }

}
