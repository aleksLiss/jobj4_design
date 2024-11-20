package ru.job4j.ood.srp.formatter;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class ReportGregorianCalendarParser implements DateTimeParser<GregorianCalendar> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public String parse(GregorianCalendar calendar) {
        DATE_FORMAT.setCalendar(calendar);
        return DATE_FORMAT.format(calendar.getTime());
    }
}
