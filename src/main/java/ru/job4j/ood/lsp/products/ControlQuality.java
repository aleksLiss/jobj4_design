package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.store.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ControlQuality {

    private List<Store> storeList;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void calculateQuality(List<Food> foods) {
        for (Food food : foods) {
            Date now = getNowAsDate();
            long diff = calculateDiff(now, food);
            if (inRange(diff, 0, 24)) {
                storeList.getFirst().addToShop(food);
            }
            if (inRange(diff, 25, 75)) {
                storeList.get(1).addToShop(food);
            }
            if (inRange(diff, 75, 99)) {
                food.setPrice(calculateNewPrice(food));
                storeList.get(1).addToShop(food);
            }
            if (inRange(diff, 100, 100)) {
                storeList.getLast().addToShop(food);
            }
        }
    }

    private boolean inRange(long num, long min, long max) {
        return num >= min && num <= max;
    }

    private long calculateDiff(Date now, Food food) {
        long dff = Math.abs(now.getTime() - food.getCreateDate().getTime());
        long all = Math.abs(food.getCreateDate().getTime() - food.getExpiryDate().getTime());
        return ((dff * 100) / all);
    }

    private Date getNowAsDate() {
        Date now = null;
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        StringBuilder builder = new StringBuilder();
        builder
                .append(localDateTimeNow.getYear())
                .append("/")
                .append(localDateTimeNow.getMonthValue())
                .append("/")
                .append(localDateTimeNow.getDayOfMonth());
        try {
            now = SIMPLE_DATE_FORMAT.parse(builder.toString());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return now;
    }

    private double calculateNewPrice(Food food) {
        return food.getPrice() * (double) (100 - food.getDiscount()) / 100;
    }
}
