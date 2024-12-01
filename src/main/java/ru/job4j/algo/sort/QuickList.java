package ru.job4j.algo.sort;

import java.util.Comparator;
import java.util.List;

public class QuickList {

    public static <T> void quickSort(List<T> sequence, Comparator<T> comparator) {
        quickSort(sequence, 0, sequence.size() - 1, comparator);
    }

    private static <T> void quickSort(List<T> sequence, int start, int end, Comparator<T> comparator) {
        if (start >= end) {
            return;
        }
        int h = breakPartition(sequence, start, end, comparator);
        quickSort(sequence, start, h - 1, comparator);
        quickSort(sequence, h + 1, end, comparator);
    }

    private static <T> int breakPartition(List<T> sequence, int start, int end, Comparator<T> comparator) {
        int beginToEnd = start + 1;
        int endToBegin = end;
        T supportElement = sequence.get(start);
        while (true) {
            int resBeginToEnd = comparator.compare(supportElement, sequence.get(beginToEnd));
            int resEndToBegin = comparator.compare(supportElement, sequence.get(endToBegin));
            while (beginToEnd < end && resBeginToEnd > 0) {
                beginToEnd++;
                resBeginToEnd = comparator.compare(supportElement, sequence.get(beginToEnd));
            }
            while (resEndToBegin < 0) {
                endToBegin--;
                resEndToBegin = comparator.compare(supportElement, sequence.get(endToBegin));
            }
            if (beginToEnd >= endToBegin) {
                break;
            }
            swap(sequence, beginToEnd++, endToBegin--);
        }
        swap(sequence, start, endToBegin);
        return endToBegin;
    }

    private static <T> void swap(List<T> array, int i, int j) {
        T t = array.get(i);
        array.set(i, array.get(j));
        array.set(j, t);
    }
}