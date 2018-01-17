package com.example.pamparampa.todocalendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

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
    }

    @Override
    protected void drawRect(Canvas canvas, int col, int row) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(boardLeftPad + (col * rectWidth),
                row * rectHeight,
                boardLeftPad + ((col + 1) * rectWidth),
                (row + 1) * (rectHeight),
                paint);
    }


}
