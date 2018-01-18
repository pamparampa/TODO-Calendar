package com.example.pamparampa.todocalendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.example.pamparampa.todocalendar.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-01-03.
 */

public class WeekView extends PeriodView {

    public WeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
        firstVisibleDate = getFirstDayOfWeek(date);

        numberOfCols = 7;
        numberOfRows = 24;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectHeight = rectWidth;
        boardScrollView.resize(width, width * 100 / 33);
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
    protected void drawRect(Canvas canvas, int col, int row) {
        canvas.drawRect(boardLeftPad + (col * rectWidth),
                row * rectHeight,
                boardLeftPad + ((col + 1) * rectWidth),
                (row + 1) * (rectHeight),
                rectPaint);
    }

    @Override
    protected void drawTopLabelElement(Canvas canvas, int dayOfWeek){
        String[] weekDaysL = getResources().getStringArray(R.array.weekDaysL);

        float x = (boardLeftPad * 1.25f) + (dayOfWeek * rectWidth);
        canvas.drawText(
                weekDaysL[dayOfWeek],
                x,
                textSize * 1.5f,
                labelTextPaint);

        calendar.setTime(firstVisibleDate);
        calendar.add(Calendar.DAY_OF_MONTH, dayOfWeek);
        canvas.drawText(
                String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
                x,
                textSize * 3,
                labelTextPaint);
    }
}
