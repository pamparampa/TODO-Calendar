package com.example.pamparampa.todocalendar.view;

/**
 * Created by Pamparampa on 2018-01-19.
 */

class CalendarWeekSizesManager extends CalendarSizesManager {

    CalendarWeekSizesManager(int numberOfCols, int numberOfRows) {
        super(numberOfCols, numberOfRows);
    }

    @Override
    int getBoardHeight() {
        return width * 100 / 33;
    }

    @Override
    float getTopLabelElementX(int dayOfWeek) {
        return (getBoardLeftPad() * 1.25f) + (dayOfWeek * getRectWidth());

    }

    @Override
    float getTopLabelWeight() {
        return 0.9f;
    }

    @Override
    float getBoardWeight() {
        return 0.1f;
    }

    @Override
    int getBoardLeftPad() {
        return width / (numberOfCols + 1);
    }

    @Override
    int getRectWidth() {
        return width / (numberOfCols + 1);
    }

    @Override
    int getRectHeight() {
        return width / (numberOfCols + 1);
    }
}
