package com.example.pamparampa.todocalendar;

import org.junit.Test;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by Pamparampa on 2018-01-03.
 */

public class PeriodViewTest {
    @Test
    public void changeDayOfWeek() {
        Calendar date = Calendar.getInstance();
        //date.setFirstDayOfWeek(Calendar.MONDAY);
        date.set(2018, 1, 1);

        System.out.println(date.getFirstDayOfWeek());

        date.set(Calendar.WEEK_OF_MONTH, date.get(Calendar.WEEK_OF_MONTH));
        date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println(date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void formatter2Digts() {
        DecimalFormat formatter = new DecimalFormat("00");

        System.out.println(formatter.format(2));
    }
}
