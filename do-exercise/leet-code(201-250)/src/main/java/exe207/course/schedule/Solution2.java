package exe207.course.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 2 || prerequisites == null || prerequisites.length < 2) {
            return true;
        }

        boolean[] stack = new boolean[numCourses];
        boolean[] marked = new boolean[numCourses];
        Map<Integer, List<int[]>> edgeFromI = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            edgeFromI.put(i, new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            edgeFromI.get(prerequisites[i][0]).add(prerequisites[i]);
        }
        return !existsCycle(edgeFromI, marked, stack);
    }

    private boolean existsCycle(Map<Integer, List<int[]>> edgeFromI, boolean[] marked, boolean[] stack) {
        for (int i = 0; i < marked.length; i++) {
            // 节点没有被check过
            if (!marked[i]) {
                if (existsCycle(edgeFromI, marked, stack, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existsCycle(Map<Integer, List<int[]>> edgeFromI, boolean[] marked, boolean[] stack, int start) {
        if (stack[start]) {
            return true;
        }
        marked[start] = true;
        stack[start] = true;

        List<int[]> edges = edgeFromI.get(start);
        for (int[] edge : edges) {
            if (existsCycle(edgeFromI, marked, stack, edge[1])) {
                return true;
            }
        }
        stack[start] = false;
        return false;
    }
}