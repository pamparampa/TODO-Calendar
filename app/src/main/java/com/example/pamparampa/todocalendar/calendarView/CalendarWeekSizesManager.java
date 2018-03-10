package com.example.pamparampa.todocalendar.calendarView;

/**
 * Created by Pamparampa on 2018-01-19.
 */

class CalendarWeekSizesManager extends CalendarSizesManager {

    CalendarWeekSizesManager(CalendarParams params) {
        super(params);
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
        return width / (params.getNumberOfCols() + 1);
    }

    @Override
    int getRectWidth() {
        return width / (params.getNumberOfCols() + 1);
    }


    int getRectHeight() {
        if(rowHeight == 0) rowHeight = width / (params.getNumberOfCols() + 1);
        return rowHeight;

    }
}
