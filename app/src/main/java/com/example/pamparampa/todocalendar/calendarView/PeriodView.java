package com.example.pamparampa.todocalendar.calendarView;

import android.content.Context;
import android.graphics.Canvas;
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
    protected final Context context;

    protected Date date;    // TODO zastanawiam sie czy nie wyrzucic
    protected Calendar calendar;

    protected TopLabel topLabel;
    private BoardListView boarListView;

    protected CalendarSizesManager sizesManager;

    protected final CalendarParams params = new CalendarParams();

    public PeriodView(Context context, Boolean test) {
        super(context, null);

        this.context = context;
        calendar = Calendar.getInstance();
        setDate(new Date());
        initFields();
        if (!test) {
            setOrientation(VERTICAL);
            composeView();
        }
    }

    private void initFields() {
        initSizeManager();
        initRowViewResource();
        initNumberOfColsAndRows();
        initTopLabel();
    }

    protected abstract void initRowViewResource();

    private void composeView() {

        boarListView = new BoardListView(context, params);

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

    public Date getDate() {
        return date;
    }

    public void setOnFlipListener(OnFlipListener onFlipListener) {
        boarListView.setOnFlipListener(onFlipListener);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        sizesManager.setWidth(w);
        sizesManager.setHeight(h);

        params.setTextSize(sizesManager.getTextSize());
    }

    public void setDate(Date date) {
        this.date = date;
        params.setFirstVisibleDateTime(CalendarUtil.getFirstDayOfWeek(date));
    }

    protected abstract void initNumberOfColsAndRows();
    protected abstract void initSizeManager();
    protected abstract void initTopLabel();

    public int getPosition() {
        return boarListView.getFirstVisiblePosition();
    }

    public void setSelection(int position) {
        boarListView.setSelection(position);
    }

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

            for (int i = 0; i < params.getNumberOfCols(); i++) {
                drawTopLabelElement(canvas, i);
            }

            canvas.drawLine(0, height, width, height, params.getLabelLinePaint());
        }

        protected abstract void drawTopLabelElement(Canvas canvas, int i);
    }
}
