package ru.job4j.algo.sliding.window;

import java.util.*;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", start, end);
    }
}

public class Main {
    /**
     * Memory complexity:
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class [I, size: 24 bytes
     * Object type: class java.util.HashMap, size: 48 bytes
     * In total: 152 bytes
     * Time complexity: O(m * k), where m is
     * the difference between the maximum and minimum values of numbers
     * in the arrays in list intervals
     * and k is count of arrays in list intervals
     *
     * @param intervals
     * @return
     */
    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        int startWindow = -1;
        int endWindow = -1;
        int startBorder = 100;
        int endBorder = -100;
        int maxCountOfIntervals = -1;
        int[] result = new int[]{startWindow, endWindow};
        if (!intervals.isEmpty()) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Interval interval : intervals) {
                if (interval.start < startBorder) {
                    startBorder = interval.start;
                }
                if (interval.end > endBorder) {
                    endBorder = interval.end;
                }
            }
            for (int i = startBorder; i <= endBorder; i++) {
                map.put(i, 0);
            }
            for (int current = startBorder; current <= endBorder; current++) {
                for (Interval interval : intervals) {
                    if (inRange(current, interval.start, interval.end)) {
                        map.computeIfPresent(current, (key, value) -> value + 1);
                    }
                }
                if (map.get(current) > maxCountOfIntervals) {
                    maxCountOfIntervals = map.get(current);
                }
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();
                if (val == maxCountOfIntervals) {
                    if (startWindow == -1) {
                        startWindow = key;
                        continue;
                    }
                    if (endWindow == -1) {
                        endWindow = key;
                    }
                }
            }
            result[0] = startWindow;
            result[1] = endWindow;
        }
        return result;
    }

    private static boolean inRange(int value, int start, int end) {
        return value >= start && value <= end;
    }
}
