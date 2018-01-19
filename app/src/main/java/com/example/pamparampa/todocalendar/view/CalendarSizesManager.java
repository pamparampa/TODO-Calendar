package com.example.pamparampa.todocalendar.view;

/**
 * Created by Pamparampa on 2018-01-19.
 */
abstract class CalendarSizesManager {

    int width;
    int height;
    int numberOfRows;
    int numberOfCols;

    CalendarSizesManager(int numberOfCols, int numberOfRows) {
        this.numberOfCols = numberOfCols;
        this.numberOfRows = numberOfRows;
    }

    void setWidth(int width) {
        this.width = width;
    }

    void setHeight(int height) {
        this.height = height;
    }

    int getBoldLineWidth() {
        return 5;
    }

    float getTextSize() {
        return height / 40; // TODO zabezpieczyc przed zbyt malym
    }

    float getTextLineShift(int numberOfLine) {
        return numberOfLine * getTextSize() * 1.5f;
    }

    float getTextLeftShift() {
        return getTextSize() / 3;
    }

    float getTextRectY(int row) {
        return (getRectHeight() * row) + getTextLineShift(1);
    }

    abstract float getTopLabelWeight();

    abstract float getTopLabelElementX(int dayOfWeek);

    abstract float getBoardWeight();

    abstract int getBoardHeight();

    abstract int getBoardLeftPad();

    abstract int getRectWidth();

    abstract int getRectHeight();

}
