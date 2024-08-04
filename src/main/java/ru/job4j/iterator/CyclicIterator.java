package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return data.size() == 0 ? false : indexLessThenSize() ? indexLessThenSize() : indexToZero();
    };

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(index++);
    }

    private boolean indexLessThenSize() {
        return index < data.size();
    }

    private boolean indexToZero() {
        index = 0;
        return true;
    }
}