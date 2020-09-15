package exe207.course.schedule;

import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 2 || prerequisites == null || prerequisites.length < 2) {
            return true;
        }

        HashMap<Integer, Set<Integer>> depends = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int[] depend = prerequisites[i];
            depends.putIfAbsent(depend[1], new HashSet<>());
            depends.get(depend[1]).add(depend[0]);
        }
        // 没有依赖的对象也需要放入进去，方便判断
        for (int i = 0; i < numCourses; i++) {
            depends.putIfAbsent(i, new HashSet<>());
        }


        while (!depends.isEmpty()) {
            int course = -1;
            for (Map.Entry<Integer, Set<Integer>> dependEntry : depends.entrySet()) {
                Set<Integer> depended = dependEntry.getValue();
                // 说明已经没有前置依赖课程了
                if (depended.isEmpty()) {
                    course = dependEntry.getKey();
                }
            }

            if (course != -1) {
                depends.remove(course);
                for (Map.Entry<Integer, Set<Integer>> dependEntry : depends.entrySet()) {
                    dependEntry.getValue().remove(course);
                }
            } else {
                // 出现环路
                return false;
            }
        }
        return true;
    }
}