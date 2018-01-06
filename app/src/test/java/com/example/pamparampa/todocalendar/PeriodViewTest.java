package com.example.pamparampa.todocalendar;

import org.junit.Test;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.util.Calendar;

/**
 * Created by Pamparampa on 2018-01-03.
 */

public class PeriodViewTest {
    @Test
    public void changeDayOfWeek() {
        Calendar date = Calendar.getInstance();
        date.set(2018, 0, 9);

        System.out.println(date.getFirstDayOfWeek());

        date.set(Calendar.DAY_OF_WEEK, 2);
        System.out.println(date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.DAY_OF_MONTH));
    }
}
