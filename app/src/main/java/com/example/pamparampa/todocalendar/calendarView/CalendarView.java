package com.example.pamparampa.todocalendar.calendarView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

import com.example.pamparampa.todocalendar.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-01-21.
 */

public class CalendarView extends ViewFlipper implements OnFlipListener {
    private Calendar calendar;
    private Date date;
    private PeriodView currentPeriodView;
    private PeriodView prevPeriodView;
    private PeriodView nextPeriodView;
    private Context context;
    private Boolean test;

    public Date getDate() {
        return date;
    }

    public PeriodView getCurrentPeriodView() {
        return currentPeriodView;
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, false);
    }

    public PeriodView getPrevPeriodView() {
        return prevPeriodView;
    }

    public PeriodView getNextPeriodView() {
        return nextPeriodView;
    }

    public CalendarView(Context context, AttributeSet attrs, Boolean test) {
        super(context, attrs);

        this.context = context;
        this.test = test;

        date = new Date();
        calendar = Calendar.getInstance();

        prevPeriodView = createPeriodView(-1);
        currentPeriodView = createPeriodView(0);
        nextPeriodView = createPeriodView(1);

        if (!test) {
            addView(prevPeriodView);
            addView(currentPeriodView);
            addView(nextPeriodView);

            showNext();
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        currentPeriodView.onSizeChanged(w, h, oldw, oldh);
        prevPeriodView.onSizeChanged(w, h, oldw, oldh);
        nextPeriodView.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    public boolean onFlipNext() {

        int position = currentPeriodView.getPosition();
        prevPeriodView = currentPeriodView;
        currentPeriodView = nextPeriodView;
        currentPeriodView.setSelection(position);
        date = currentPeriodView.getDate();
        nextPeriodView = createPeriodView(1);

        if (!test) flipNextAnimation();

        return true;
    }

    private void flipNextAnimation() {
        setInAnimation(null);
        setOutAnimation(null);
        showPrevious();
        setInAnimation(context, R.anim.right_enter);
        setOutAnimation(context, R.anim.left_out);
        addView(nextPeriodView);
        removeViewAt(0);
        showNext();
    }

    @Override
    public boolean onFlipPrev() {
        nextPeriodView = currentPeriodView;
        int position = currentPeriodView.getPosition();
        currentPeriodView = prevPeriodView;
        currentPeriodView.setSelection(position);
        date = currentPeriodView.getDate();
        prevPeriodView = createPeriodView(-1);
        if(!test) flipPrevAnimation();

        return false;
    }

    private void flipPrevAnimation() {
        addView(prevPeriodView, 0);
        setInAnimation(context, R.anim.left_enter);
        setOutAnimation(context, R.anim.right_out);
        showPrevious();
        removeViewAt(3);
    }

    private PeriodView createPeriodView(int dateStep) {
        PeriodView periodView = new WeekView(context, null, test);   // TODO obsluzyc inne widoki
        if (dateStep != 0) {
            calendar.setTime(date);
            calendar.add(Calendar.WEEK_OF_YEAR, dateStep);
            periodView.setDate(calendar.getTime());
            periodView.onSizeChanged(getWidth(), getHeight(), 0, 0);
        }
        periodView.setOnFlipListener(this);
        return periodView;
    }
}
