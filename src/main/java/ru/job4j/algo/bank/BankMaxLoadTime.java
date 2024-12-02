package ru.job4j.algo.bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List visitTimes) {
        int startWindow = -1;
        int endWindow = -1;
        int startBorder = 100;
        int endBorder = -100;
        int maxCountOfIntervals = -1;
        int[] result = new int[]{startWindow, endWindow};

        if (!visitTimes.isEmpty()) {
            Map<Integer, Integer> map = new HashMap<>();
            for (var visit : visitTimes) {
                int[] array = (int[]) visit;
                int start = array[0];
                int end = array[1];
                if (start < startBorder) {
                    startBorder = start;
                }
                if (end > endBorder) {
                    endBorder = end;
                }
            }
            for (int i = startBorder; i <= endBorder; i++) {
                map.put(i, 0);
            }


            for (int current = startBorder; current <= endBorder; current++) {
                for (var visit : visitTimes) {
                    int[] array = (int[]) visit;
                    int start = array[0];
                    int end = array[1];
                    if (inRange(current, start, end)) {
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

    private static boolean inRange(int current, int start, int end) {
        return current >= start && current <= end;
    }

    static class Event implements Comparable {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Object other) {
            Event event = (Event) other;
            if (this.time == event.time) {
                return this.type == EventType.ARRIVAL
                        ? -1
                        : 1;
            }
            return Integer.compare(this.time, event.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}
