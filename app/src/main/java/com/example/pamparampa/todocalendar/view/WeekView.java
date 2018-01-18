package com.example.pamparampa.todocalendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.example.pamparampa.todocalendar.R;

import java.util.Calendar;

/**
 * Created by Pamparampa on 2018-01-03.
 */

public class WeekView extends PeriodView {

    public WeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initFields() {
        numberOfCols = 7;
        numberOfRows = 24;

        firstVisibleDateTime = getFirstDayOfWeek(date);
        rectTimeUnit = Calendar.HOUR_OF_DAY;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        coordinateRects();
        boardScrollView.resize(width, width * 100 / 33);    // TODO odhardcodowac
    }

    private void coordinateRects() {
        int rectLength = width / (numberOfCols + 1);
        boardLeftPad = rectLength;
        for (int col = 0; col < numberOfCols; col++) {
            for (int j = 0; j < numberOfRows; j++) {
                rects[col][j].setCoordinates(
                        rectLength, rectLength);
            }
        }
    }

    @Override
    protected void composeView(Context context) {
        super.composeView(context);

        topLabel.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.9f
        ));

        boardScrollView.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.1f));
    }

    @Override
    protected void drawTopLabelElement(Canvas canvas, int dayOfWeek){
        String[] weekDaysL = getResources().getStringArray(R.array.weekDaysL);

        //TODO odharcodowac te metode
        float x = (boardLeftPad * 1.25f) + (dayOfWeek * (width / numberOfCols + 1));
        canvas.drawText(
                weekDaysL[dayOfWeek],
                x,
                textSize * 1.5f,
                labelTextPaint);

        calendar.setTime(firstVisibleDateTime);
        calendar.add(Calendar.DAY_OF_MONTH, dayOfWeek);
        canvas.drawText(
                String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
                x,
                textSize * 3,
                labelTextPaint);
    }
}
