package com.example.pamparampa.todocalendar.calendarView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.example.pamparampa.todocalendar.R;

import java.util.Calendar;

/**
 * Created by Pamparampa on 2018-01-03.
 */

public class WeekView extends PeriodView {

    public WeekView(Context context, AttributeSet attrs) {
        this(context, attrs, false);
    }

    public WeekView(Context context, AttributeSet attrs, Boolean test) {
        super(context, attrs, test);
    }

    @Override
    protected void initNumberOfColsAndRows() {
        numberOfCols = 7;
        numberOfRows = 24;
    }

    @Override
    protected void initFirstVisibleDateTime() {
        firstVisibleDateTime = CalendarUtil.getFirstDayOfWeek(date);
    }

    @Override
    protected void initSizeManager() {
        sizesManager = new CalendarWeekSizesManager(numberOfCols, numberOfRows);
    }

    @Override
    protected void initTopLabel() {
        topLabel = new WeekTopLabel(context);
    }

    protected class WeekTopLabel extends PeriodView.TopLabel {

        public WeekTopLabel(Context context) {
            super(context);
        }

        @Override
        protected void drawTopLabelElement(Canvas canvas, int dayOfWeek){
            float x = sizesManager.getTopLabelElementX(dayOfWeek);

            drawDayOfWeek(canvas, dayOfWeek, x);
            drawDayOfMonth(canvas, dayOfWeek, x);
        }

        private void drawDayOfWeek(Canvas canvas, int dayOfWeek, float x) {
            String[] weekDaysL = getResources().getStringArray(R.array.weekDaysL);

            canvas.drawText(
                    weekDaysL[dayOfWeek],
                    x,
                    sizesManager.getTextLineShift(1),
                    labelTextPaint);
        }

        private void drawDayOfMonth(Canvas canvas, int dayOfWeek, float x) {
            calendar.setTime(firstVisibleDateTime);
            calendar.add(Calendar.DAY_OF_MONTH, dayOfWeek);
            canvas.drawText(
                    String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
                    x,
                    sizesManager.getTextLineShift(2),
                    labelTextPaint);
        }
    }
}
