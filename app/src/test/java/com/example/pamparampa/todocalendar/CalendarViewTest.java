package com.example.pamparampa.todocalendar;

import android.content.Context;

import com.example.pamparampa.todocalendar.view.CalendarView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by Pamparampa on 2018-01-21.
 */

@RunWith(MockitoJUnitRunner.class)
public class CalendarViewTest {

    @Mock
    Context mockContext;

    CalendarView calendarView;
    @Before
    public void setCalendarView() {
        calendarView = new CalendarView(mockContext, null, true);
    }

    @Test
    public void createCalendarView() {

        assertDates(0);
    }

    @Test
    public void onFlipNext() {
        calendarView.onFlipNext();

        assertDates(1);
    }

    @Test
    public void onFlipPrev() {
        calendarView.onFlipPrev();

        assertDates(-1);
    }

    private void assertDates(int step) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.add(Calendar.WEEK_OF_YEAR, step - 1);
        calendar2.setTime(calendarView.getPrevPeriodView().getDate());

        assertEquals (calendar1.get(Calendar.WEEK_OF_YEAR),
                calendar2.get(Calendar.WEEK_OF_YEAR));

        calendar1.add(Calendar.WEEK_OF_YEAR, 1);
        calendar2.setTime(calendarView.getCurrentPeriodView().getDate());

        assertEquals (calendar1.get(Calendar.WEEK_OF_YEAR),
                calendar2.get(Calendar.WEEK_OF_YEAR));


        calendar1.add(Calendar.WEEK_OF_YEAR, 1);
        calendar2.setTime(calendarView.getNextPeriodView().getDate());

        assertEquals (calendar1.get(Calendar.WEEK_OF_YEAR),
                calendar2.get(Calendar.WEEK_OF_YEAR));
    }
}
