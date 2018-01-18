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
    protected int numberOfCols;
    protected int numberOfRows;
    protected BoardScrollView boardScrollView;
    protected Paint labelTextPaint;
    protected TopLabel topLabel;
    protected int textSize;
    protected Calendar calendar;
    protected Paint rectPaint;  //TODO posortowac pola
    protected Paint labelLinePaint;

    public PeriodView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray tArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PeriodView,
                0, 0);
        calendar = Calendar.getInstance();

        achieveDate(tArray);

        setOrientation(VERTICAL);

        composeView(context);

        initPaints();
    }

    private void initPaints() {
        labelTextPaint = new Paint();
        labelTextPaint.setAntiAlias(true);
        labelTextPaint.setTextSize(20);  //TODO odhardcodowac

        labelLinePaint = new Paint();
        labelLinePaint.setStrokeWidth(5);   // TODO odharcodowac?

        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.STROKE);
    }

    private void achieveDate(TypedArray tArray) {
        //TODO czy to dziala i czy mozna uproscic
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
    }

    protected void composeView(Context context) {
        topLabel = new TopLabel(context);
        boardScrollView = new BoardScrollView(context, null);

        addView(topLabel);
        addView(boardScrollView);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;

        boardLeftPad = w / 8;
        rectWidth = (width - boardLeftPad) / numberOfCols;

        textSize = height / 40;
        labelTextPaint.setTextSize(textSize);    // TODO zabezpieczyc przed zbyt malym
    }

    protected Date getFirstDayOfWeek(Date date) {
        calendar.setTime(date);
        calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return calendar.getTime();
    }

    protected abstract void drawRect(Canvas canvas, int col, int row);

    protected abstract void drawTopLabelElement(Canvas canvas, int i);

    protected class TopLabel extends View {

        private int width;
        private int height;

        public TopLabel(Context context) {
            super(context);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            width = w;
            height = h;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            for (int i = 0; i < 7; i++) {
                drawTopLabelElement(canvas, i);
            }

            canvas.drawLine(0, height, width, height, labelLinePaint);
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

        public void resize(int w, int h) {
            boardView.setLayoutParams(new LinearLayout.LayoutParams(w, h));
            boardView.postInvalidate();
        }

        protected class BoardView extends View {

            public BoardView(Context context, AttributeSet attrs) {
                super(context, attrs);
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
