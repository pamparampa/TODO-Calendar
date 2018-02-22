package com.example.pamparampa.todocalendar.calendarView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pamparampa on 2018-02-11.
 */

public class CalendarUtil {

    protected static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }
}
