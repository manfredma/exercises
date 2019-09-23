package exe210.course.schedule.ii;

import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> depends = new HashMap<>();
        // 没有依赖的对象也需要放入进去，方便判断
        for (int i = 0; i < numCourses; i++) {
            depends.put(i, new HashSet<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int[] depend = prerequisites[i];
            depends.get(depend[0]).add(depend[1]);
        }

        int[] result = new int[numCourses];
        int index = 0;

        while (!depends.isEmpty()) {
            int course = -1;
            // 找到没有前置课程的课程
            for (Map.Entry<Integer, Set<Integer>> dependEntry : depends.entrySet()) {
                Set<Integer> depended = dependEntry.getValue();
                if (depended.isEmpty()) {
                    course = dependEntry.getKey();
                    result[index++] = course;
                    break;
                }
            }

            if (course != -1) {
                depends.remove(course);
                for (Map.Entry<Integer, Set<Integer>> dependEntry : depends.entrySet()) {
                    dependEntry.getValue().remove(course);
                }
            } else {
                // 出现环路
                return new int[0];
            }
        }
        return result;
    }
}