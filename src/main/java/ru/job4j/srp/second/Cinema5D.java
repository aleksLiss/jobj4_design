package ru.job4j.srp.second;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

public class Cinema5D implements Cinema {

    private List<Ticket> tickets;

    public Cinema5D() {
        this.tickets = new ArrayList<>();
    }

    /**
     * Здесь так же происходит нарушения принципа единой ответственности,
     * так как в методе добавления билетов в список происходит их создание
     * и затем добавление
     *
     * @param date
     * @param count
     * @return true if were created all Tickets
     * in range from 0 till count and were
     * add to list tickets
     */
    @Override
    public boolean add(Calendar date, int count) {
        IntStream.range(0, count).forEach(i -> {
            tickets.add(new Ticket5D(date));
        });
        return true;
    }
}
