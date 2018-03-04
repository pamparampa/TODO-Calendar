package com.example.pamparampa.todocalendar.calendarView;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by Pamparampa on 2018-02-01.
 */

public class BoardListView extends ListView{

    private final int SCROLL_BOUND = 100;
    private float initialX;
    private OnFlipListener onFlipListener;

    public BoardListView(Context context, CalendarParameters params) {
        super(context);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setAdapter(new CalendarRowAdapter(context, params));

    }

    public void setOnFlipListener(OnFlipListener onFlipListener) {
        this.onFlipListener = onFlipListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (event.getAxisValue(MotionEvent.AXIS_Y) != 0) return super.onTouchEvent(event);
                break;

            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                break;

            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                if (initialX > finalX + SCROLL_BOUND) {
                    return onFlipListener.onFlipNext();
                } else if (initialX < finalX - SCROLL_BOUND){
                    return onFlipListener.onFlipPrev();
                }
                else return super.onTouchEvent(event);
        }
        return true;

    }
}
