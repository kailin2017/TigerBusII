package com.kailin.architecture_model.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public final class DateRangeUtil {

    private static final AtomicReference<DateRangeUtil> reference = new AtomicReference<>();

    public static DateRangeUtil getInstance() {
        while (true) {
            DateRangeUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new DateRangeUtil();
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private final LoggerUtil loggerUtil = LoggerUtil.getInstance();

    private DateRangeUtil() {
    }

    public boolean isToDay(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        String toDay = simpleDateFormat.format(calendar.getTime());
        String checkDay = simpleDateFormat.format(date);
        return toDay.equalsIgnoreCase(checkDay);
    }

    public DateRange rangeforDay(int day) {
        Calendar calendar = Calendar.getInstance();
        Date dateFrom;
        Date dateTo;
        if (day > 0) {
            dateFrom = calendar.getTime();
            calendar.add(Calendar.DATE, day);
            dateTo = calendar.getTime();
        } else {
            dateTo = calendar.getTime();
            calendar.add(Calendar.DATE, day);
            dateFrom = calendar.getTime();
        }
        DateRange dateRange = new DateRange(dateFrom, dateTo);
        loggerUtil.d("rangeforDay",dateRange);
        return new DateRange(dateFrom, dateTo);
    }

    public static class DateRange {
        private final Date dateFrom;
        private final Date dateTo;
        private final String dateFromString;
        private final String dateToString;

        public DateRange(Date dateFrom, Date dateTo) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.dateFromString = simpleDateFormat.format(dateFrom);
            this.dateToString = simpleDateFormat.format(dateTo);
        }

        public Date getDateFrom() {
            return dateFrom;
        }

        public Date getDateTo() {
            return dateTo;
        }

        public String getDateFromString() {
            return dateFromString;
        }

        public String getDateToString() {
            return dateToString;
        }

        @Override
        public String toString() {
            return "DateRange{" +
                    "dateFrom='" + dateFrom + '\'' +
                    ", dateTo='" + dateTo + '\'' +
                    '}';
        }
    }
}
