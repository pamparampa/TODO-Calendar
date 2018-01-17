package com.example.pamparampa.todocalendar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.pamparampa.todocalendar.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-01-03.
 */

public abstract class PeriodView extends LinearLayout{

    protected Date date;
    protected Date firstVisibleDate;

    protected int width;
    protected int height;
    protected int rectWidth;
    protected int rectHeight;
    protected int boardLeftPad;
    protected int boardTopPad;
    protected int numberOfCols;
    protected int numberOfRows;
    private final BoardScrollView boardScrollView;

    public PeriodView(Context context, AttributeSet attrs)
    {   //TODO czy to dziala i czy mozna uproscic
        super(context, attrs);

        TypedArray tArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PeriodView,
                0, 0);
        try {
            DateFormat format = SimpleDateFormat.getDateInstance();
            String stringDate = tArray.getString(R.styleable.PeriodView_date);
            if (stringDate != null) {
                date = format.parse(tArray.getString(R.styleable.PeriodView_date));
            }
            else {
                date = new Date();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            tArray.recycle();
        }

        setOrientation(VERTICAL);

        TopLabel topLabel = new TopLabel(context);
        boardScrollView = new BoardScrollView(context, null);

        topLabel.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.9f
        ));
        addView(topLabel);

        boardScrollView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.1f));
        addView(boardScrollView);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;

        boardLeftPad = w / 8;
        boardTopPad = boardLeftPad;

        rectWidth = (width - boardLeftPad) / numberOfCols;

        boardScrollView.setSize(width, width * 100 / 33);
    }

    protected Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 1);

        return calendar.getTime();
    }

    protected abstract void drawRect(Canvas canvas, int col, int row);

    protected class TopLabel extends View {

        public TopLabel(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setTextSize(20);  //TODO odhardcodowac

            canvas.drawText("P", 0, 1, boardLeftPad, 10, paint);
            canvas.drawText("10", 0, 2, boardLeftPad, 50, paint);
        }
    }

    protected class BoardScrollView extends ScrollView {

        private final BoardView boardView;

        public BoardScrollView(Context context, AttributeSet attrs) {
            super(context);

            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            boardView = new BoardView(context, null);
            boardView.setLayoutParams(new LinearLayout.LayoutParams(400, 400));

            linearLayout.addView(boardView);
            addView(linearLayout);

        }

        public void setSize(int w, int h) {
            boardView.setLayoutParams(new LinearLayout.LayoutParams(w, h));
            boardView.postInvalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {

        }

        protected class BoardView extends View {

            public BoardView(Context context, AttributeSet attrs) {
                super(context);
            }

            @Override
            protected void onDraw(Canvas canvas) {
                for (int col = 0; col < numberOfCols; col++) {
                    for (int row = 0; row < numberOfRows; row++) {
                        drawRect(canvas, col, row);
                    }
                }
            }
        }
    }
}
