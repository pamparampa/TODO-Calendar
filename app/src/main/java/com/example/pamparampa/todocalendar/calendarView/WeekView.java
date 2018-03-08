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

    public WeekView(Context context, Boolean test) {
        super(context, test);
    }

    @Override
    protected void initNumberOfColsAndRows() {
        params.setNumberOfCols(7);
        params.setNumberOfRows(24);
    }

    @Override
    protected void initSizeManager() {
        sizesManager = new CalendarWeekSizesManager(params);
        params.setSizesManager(sizesManager);
    }

    @Override
    protected void initRowViewResource() {
        params.setRowViewResource(R.layout.calendar_week_row);
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
                    params.getLabelTextPaint());
        }

        private void drawDayOfMonth(Canvas canvas, int dayOfWeek, float x) {
            calendar.setTime(params.getFirstVisibleDateTime());
            calendar.add(Calendar.DAY_OF_MONTH, dayOfWeek);
            canvas.drawText(
                    String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
                    x,
                    sizesManager.getTextLineShift(2),
                    params.getLabelTextPaint());
        }
    }
}
