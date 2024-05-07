package exe210.course.schedule.ii;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class Solution2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. 构造两个对象：
        //    入度数（为0则可以进入排队）和指向被依赖对象集合（出队时需要将被指向对象的入度数-1）
        List<Set<Integer>> bePointedTo = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            bePointedTo.add(new HashSet<>());
        }
        int[] outDegree = new int[numCourses];
        for (int[] depend : prerequisites) {
            bePointedTo.get(depend[0]).add(depend[1]);
            outDegree[depend[1]]++;
        }

        int[] result = new int[numCourses];
        // 标记该节点已经被寻找过
        boolean[] marked = new boolean[numCourses];
        boolean[] stack = new boolean[numCourses];
        AtomicInteger index = new AtomicInteger(0);
        for (int i = 0; i < bePointedTo.size(); i++) {
            if (outDegree[i] == 0) {
                if (!dfs(i, marked, stack, index, bePointedTo, result)) {
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

    private boolean dfs(
            int start, boolean[] marked, boolean[] stack, AtomicInteger index,
            List<Set<Integer>> bePointedTo, int[] result) {
        if (stack[start]) {
            // 存在环路
            return false;
        }
        stack[start] = true;
        if (!marked[start]) {
            marked[start] = true;
            Set<Integer> de = bePointedTo.get(start);
            for (Integer subStart : de) {
                if (!dfs(subStart, marked, stack, index, bePointedTo, result)) {
                    return false;
                }
            }
            result[index.getAndIncrement()] = start;
        }
        stack[start] = false;
        return true;
    }
}