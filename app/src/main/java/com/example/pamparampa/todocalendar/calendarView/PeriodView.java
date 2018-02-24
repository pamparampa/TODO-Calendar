package com.example.pamparampa.todocalendar.calendarView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-01-03.
 */

public abstract class PeriodView extends LinearLayout{

    //TODO byc moze zabronic tworzenia tej klasy bez CalendarView
    protected Date date;
    protected Date firstVisibleDateTime;
    protected Calendar calendar;

    protected int numberOfCols;
    protected int numberOfRows;

    protected TopLabel topLabel;
    protected BoardListView boarListView;

    protected int rectTimeUnit;
    protected CalendarSizesManager sizesManager;

    protected Context context;  // TODO pozbyc sie tej zmiennej // czy aby na pewno?
    private OnFlipListener onFlipListener;
    protected CalendarParameters params;

    public PeriodView(Context context, AttributeSet attrs, Boolean test) {
        super(context, attrs);

        this.context = context;
        calendar = Calendar.getInstance();
        date = new Date();
        initFields();
        if (!test) {
            setOrientation(VERTICAL);
            composeView();
        }

    }

    private void initFields() {
        initNumberOfColsAndRows();
        initFirstVisibleDateTime();
        initSizeManager();
        initTopLabel();
    }

    private void composeView() {

        boarListView = new BoardListView(context, createParams());
        boarListView.setOnFlipListener(onFlipListener);

        topLabel.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                sizesManager.getTopLabelWeight()
        ));

        boarListView.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                sizesManager.getBoardWeight()
        ));

        addView(topLabel);
        addView(boarListView);
    }

    private CalendarParameters createParams() {
        params = new CalendarParameters();
        params.setCalendar(calendar);
        params.setFirstVisibleDate(firstVisibleDateTime);
        params.setNumberOfRows(numberOfRows);
        params.setSizesManager(sizesManager);

        return params;
    }

    public Date getDate() {
        return date;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        sizesManager.setWidth(w);
        sizesManager.setHeight(h);

        boarListView.setSizesManager(sizesManager);

        params.setTextSize(sizesManager.getTextSize());

        //coordinateRects();
        //boarListView.resize(w, sizesManager.getBoardHeight());
    }

    /*
    private void coordinateRects() {
        for (int col = 0; col < numberOfCols; col++) {
            for (int j = 0; j < numberOfRows; j++) {
                rects[col][j].setCoordinates(
                        sizesManager.getRectHeight(), sizesManager.getRectWidth());
            }
        }
    }
    */

    public void setDate(Date date) {
        this.date = date;
        firstVisibleDateTime = CalendarUtil.getFirstDayOfWeek(date);
    }

    public void setOnFlipListener(OnFlipListener onFlipListener) {
        this.onFlipListener = onFlipListener;
    }

    protected abstract void initNumberOfColsAndRows();
    protected abstract void initFirstVisibleDateTime();
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

            canvas.drawLine(0, height, width, height, params.getLabelLinePaint());
        }

        protected abstract void drawTopLabelElement(Canvas canvas, int i);
    }
}
