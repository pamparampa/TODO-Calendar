package com.example.pamparampa.todocalendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Date;

/**
 * Created by Pamparampa on 2018-01-03.
 */

public class WeekView extends PeriodView {

    public WeekView(Context context, Date date) {
        super(context, date);
        firstVisibleDate = getFirstDayOfWeek(date);

        numberOfCols = 7;
        numberOfRows = 24;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void drawRect(Canvas canvas, int col, int row) {
        Paint paint = new Paint();
        canvas.drawRect(boardLeftPad + (col * rectWidth),
                boardTopPad + (row * rectHeight),
                boardLeftPad + (col * (rectWidth + 1)),
                boardTopPad + (row * (rectHeight + 1)),
                paint);
    }


}
