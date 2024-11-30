package ru.job4j.algo.sort;

import java.util.ArrayList;
import java.util.List;

class IntervalMerger {
    /**
     * Memory Big(O):
     * Object type: class java.util.ArrayList, size: 24 bytes
     * Object type: class java.util.ArrayList, size: 24 bytes
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class java.lang.Boolean, size: 16 bytes
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class java.lang.Integer, size: 16 bytes
     * Object type: class [[I, size: 32 bytes
     * In Total: 192 bytes
     * Time Big(O) = N^2
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        int index = 1;
        int i = 0;
        boolean end = false;
        int start = intervals[i][0];
        int finish = intervals[i][1];
        while (true) {
            while (index < intervals.length) {
                int newStart = intervals[index][0];
                int newFinish = intervals[index][1];
                if (newFinish >= start && newFinish <= finish) {
                    start = newStart;
                    index++;
                    end = index >= intervals.length;
                    continue;
                }
                if (newStart >= start && newStart <= finish) {
                    finish = newFinish;
                    index++;
                    end = index >= intervals.length;
                } else {
                    i = index++;
                    curr.add(start);
                    curr.add(finish);
                    result.add(curr);
                    curr = new ArrayList<>();
                    start = intervals[i][0];
                    finish = intervals[i][1];
                    end = index >= intervals.length;
                    break;
                }
            }
            if (end) {
                curr.add(start);
                curr.add(finish);
                result.add(curr);
                break;
            }
        }
        int[][] res = new int[result.size()][2];
        for (int k = 0; k < result.size(); k++) {
            for (int j = 0; j < 2; j++) {
                res[k][j] = result.get(k).get(j);
            }
        }
        return res;
    }
}