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
    protected Date firstVisibleDateTime;
    protected Calendar calendar;

    protected int numberOfCols;
    protected int numberOfRows;

    protected TopLabel topLabel;
    protected BoardScrollView boardScrollView;

    protected Paint labelTextPaint;
    protected Paint labelLinePaint;

    private CalendarRect[][] rects;
    protected int rectTimeUnit;
    protected CalendarSizesManager sizesManager;

    protected Context context;

    public PeriodView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        TypedArray tArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PeriodView,
                0, 0);
        calendar = Calendar.getInstance();

        achieveDate(tArray);
        setOrientation(VERTICAL);
        initFields();
        composeView();
        initPaints();
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

    private void initFields() {
        initNumberOfColsAndRows();
        initFirstVisableDateTime();
        initRectTimeUnit();
        initSizeManager();
        initTopLabel();
    }

    private void composeView() {

        initBoard();
        initBoardScrollView();

        topLabel.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                sizesManager.getTopLabelWeight()
        ));

        boardScrollView.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                sizesManager.getBoardWeight()));

        addView(topLabel);
        addView(boardScrollView);
    }

    protected void initBoardScrollView() {
        boardScrollView = new BoardScrollView(context, null);
    }

    private void initBoard() {
        rects = new CalendarRect[numberOfCols][numberOfRows];
        calendar.setTime(firstVisibleDateTime);
        calendar.add(rectTimeUnit, 1);
        for (int col = 0; col < numberOfCols; col++) {
            for (int row = 0; row < numberOfRows; row++) {
                rects[col][row] = new CalendarRect(calendar.getTime(), col, row);
            }
        }
    }

    private void initPaints() {
        labelTextPaint = new Paint();
        labelTextPaint.setAntiAlias(true);

        labelLinePaint = new Paint();
        labelLinePaint.setStrokeWidth(sizesManager.getBoldLineWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        sizesManager.setWidth(w);
        sizesManager.setHeight(h);

        labelTextPaint.setTextSize(sizesManager.getTextSize());

        coordinateRects();
        boardScrollView.resize(w, sizesManager.getBoardHeight());
    }

    private void coordinateRects() {
        for (int col = 0; col < numberOfCols; col++) {
            for (int j = 0; j < numberOfRows; j++) {
                rects[col][j].setCoordinates(
                        sizesManager.getRectHeight(), sizesManager.getRectWidth());
            }
        }
    }

    protected Date getFirstDayOfWeek(Date date) {
        calendar.setTime(date);
        calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    protected abstract void initNumberOfColsAndRows();
    protected abstract void initFirstVisableDateTime();
    protected abstract void initRectTimeUnit();
    protected abstract void initSizeManager();
    protected abstract void initTopLabel();

    protected abstract class TopLabel extends View {

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

            for (int i = 0; i < numberOfCols; i++) {
                drawTopLabelElement(canvas, i);
            }

            canvas.drawLine(0, height, width, height, labelLinePaint);
        }

        protected abstract void drawTopLabelElement(Canvas canvas, int i);
    }

    protected class BoardScrollView extends ScrollView {

        protected BoardView boardView;
        protected Context context;

        public BoardScrollView(Context context, AttributeSet attrs) {
            super(context);

            this.context = context;

            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            initBoardView();
            linearLayout.addView(boardView);
            addView(linearLayout);

        }

        protected void initBoardView() {
            boardView = new BoardView(context, null);
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
                drawLeftLabel(canvas);
                drawBoard(canvas);
            }

            private void drawBoard(Canvas canvas) {
                for (int col = 0; col < numberOfCols; col++) {
                    for (int row = 0; row < numberOfRows; row++) {
                        rects[col][row].draw(canvas, sizesManager.getBoardLeftPad());
                    }
                }
            }

            protected void drawLeftLabel(Canvas canvas) {};
        }
    }
}
