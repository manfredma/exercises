package exe57.insert.interval;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }

        boolean isInsert = false;
        List<int[]> result = new ArrayList<>();
        int[] overlap = new int[]{newInterval[0], newInterval[1]};
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (isInsert) {
                result.add(cur);
                continue;
            }
            if (cur[0] > overlap[1]) {
                result.add(overlap);
                result.add(cur);
                isInsert = true;
            } else if (cur[1] < overlap[0]) {
                result.add(cur);
            } else {
                overlap[0] = Math.min(cur[0], overlap[0]);
                overlap[1] = Math.max(cur[1], overlap[1]);
            }
        }
        if (!isInsert) {
            result.add(overlap);
        }
        return result.toArray(new int[result.size()][]);
    }
}