package exe210.course.schedule.ii;

import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Set[] depends = new Set[numCourses];
        // 没有依赖的对象也需要放入进去，方便判断
        for (int i = 0; i < numCourses; i++) {
            depends[i] = new HashSet<>();
        }

        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int[] depend = prerequisites[i];
            depends[depend[1]].add(depend[0]);
            inDegree[depend[0]]++;
        }

        int[] result = new int[numCourses];
        Queue<Integer> queue = new ArrayDeque<>(numCourses);
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course;
            for (int deCourse : (Iterable<Integer>) depends[course]) {
                inDegree[deCourse]--;
                if (inDegree[deCourse] <= 0) {
                    queue.add(deCourse);
                }
            }
        }

        if (index < numCourses) {
            return new int[0];
        }
        return result;
    }
}