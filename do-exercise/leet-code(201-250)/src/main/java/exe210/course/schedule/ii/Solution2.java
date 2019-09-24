package exe210.course.schedule.ii;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class Solution2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Set[] depends = new Set[numCourses];
        // 没有依赖的对象也需要放入进去，方便判断
        for (int i = 0; i < numCourses; i++) {
            depends[i] = new HashSet<>();
        }
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int[] depend = prerequisites[i];
            depends[depend[0]].add(depend[1]);
            inDegree[depend[1]]++;
        }

        int[] result = new int[numCourses];
        boolean[] marked = new boolean[numCourses];
        boolean[] stack = new boolean[numCourses];
        AtomicInteger index = new AtomicInteger(0);
        for (int i = 0; i < depends.length; i++) {
            if (inDegree[i] == 0) {
                if (!dfs(i, marked, stack, index, depends, result)) {
                    // 存在环路依赖
                    break;
                }
            }
        }
        if (index.get() < numCourses) {
            return new int[0];
        }
        return result;
    }

    private boolean dfs(int start,
                        boolean[] marked,
                        boolean[] stack,
                        AtomicInteger index,
                        Set[] depends,
                        int[] result
    ) {
        if (stack[start]) {
            // 存在环路
            return false;
        }
        stack[start] = true;
        if (!marked[start]) {
            marked[start] = true;
            Set de = depends[start];
            for (Object o : de) {
                int subStart = (Integer) o;
                if (!dfs(subStart, marked, stack, index, depends, result)) {
                    return false;
                }
            }
            result[index.getAndIncrement()] = start;
        }
        stack[start] = false;
        return true;
    }
}