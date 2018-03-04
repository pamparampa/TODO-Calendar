package com.example.pamparampa.todocalendar.calendarView;

/**
 * Created by Pamparampa on 2018-01-19.
 */
//TODO dodac state exceptiony jesli zmienne niezainicjalizowane
abstract class CalendarSizesManager {

    int width;
    int height;
    int numberOfRows;
    int numberOfCols;
    int rowHeight;

    CalendarSizesManager(int numberOfCols, int numberOfRows) {
        this.numberOfCols = numberOfCols;
        this.numberOfRows = numberOfRows;
    }

    CalendarSizesManager(int numberOfCols) {
        this.numberOfCols = numberOfCols;
    }

    void setWidth(int width) {
        this.width = width;
    }

    void setHeight(int height) {
        this.height = height;
    }

    void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    int getBoldLineWidth() {
        return 5;
    }

    float getTextSize() {
        if (height != 0) return height / 40; // TODO zabezpieczyc przed zbyt malym
        else return rowHeight / 4;
    }

    float getTextLineShift(int numberOfLine) {
        return numberOfLine * getTextSize() * 1.5f;
    }

    float getTextLeftShift() {
        return getTextSize() / 3;
    }

    int getRectHeight() {
        return rowHeight;
    }

    abstract float getTopLabelWeight();

    abstract float getTopLabelElementX(int dayOfWeek);

    abstract float getBoardWeight();

    abstract int getBoardHeight();

    abstract int getBoardLeftPad();

    abstract int getRectWidth();

}