package ru.job4j.ood.lsp.products.formatt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodsDateFormatter implements DateFormatter {

    private static final SimpleDateFormat FOODS_DATE_FORMATTER = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public Date parse(String date) {
        Date resultDate = null;
        try {
            resultDate = FOODS_DATE_FORMATTER.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return resultDate;
    }
}
