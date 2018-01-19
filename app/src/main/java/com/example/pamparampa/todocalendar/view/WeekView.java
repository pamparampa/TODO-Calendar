package com.example.pamparampa.todocalendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.example.pamparampa.todocalendar.R;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by Pamparampa on 2018-01-03.
 */

public class WeekView extends PeriodView {

    public WeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initNumberOfColsAndRows() {
        numberOfCols = 7;
        numberOfRows = 24;
    }

    @Override
    protected void initFirstVisableDateTime() {
        firstVisibleDateTime = getFirstDayOfWeek(date);
    }

    @Override
    protected void initRectTimeUnit() {
        rectTimeUnit = Calendar.HOUR_OF_DAY;
    }

    @Override
    protected void initSizeManager() {
        sizesManager = new CalendarWeekSizesManager(numberOfCols, numberOfRows);
    }

    @Override
    protected void initTopLabel() {
        topLabel = new WeekTopLabel(context);
    }

    @Override
    protected void initBoardScrollView() {
        boardScrollView = new BoardScrollWeekView(context, null);
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

    protected class BoardScrollWeekView extends PeriodView.BoardScrollView {

        public BoardScrollWeekView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void initBoardView() {
            boardView = new BoardWeekView(context, null);
        }

        protected class BoardWeekView extends PeriodView.BoardScrollView.BoardView {

            public BoardWeekView(Context context, AttributeSet attrs) {
                super(context, attrs);
            }

            @Override
            protected void drawLeftLabel(Canvas canvas) {
                DecimalFormat formatter = new DecimalFormat("00");
                calendar.setTime(firstVisibleDateTime);
                for (int row = 0; row < numberOfRows; row++) {
                    //TODO byc moze dla innych krajow brac hour + pm/am zamiast hour of day
                    canvas.drawText(
                            formatter.format(calendar.get(Calendar.HOUR_OF_DAY)) + ":00",
                            sizesManager.getTextLeftShift(),
                            sizesManager.getTextRectY(row),
                            labelTextPaint
                    );
                    calendar.add(Calendar.HOUR_OF_DAY, 1);
                }
            }
        }
    }
}
