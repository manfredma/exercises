package exe210.course.schedule.ii;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. 构造两个对象：
        //    入度数（为0则可以进入排队）和指向被依赖对象集合（出队时需要将被指向对象的入度数-1）
        List<Set<Integer>> depended = new ArrayList<>();
        // 没有依赖的对象也需要放入进去，方便判断
        for (int i = 0; i < numCourses; i++) {
            depended.add(new HashSet<>());
        }

        int[] inDegree = new int[numCourses];
        for (int[] depend : prerequisites) {
            depended.get(depend[1]).add(depend[0]);
            inDegree[depend[0]]++;
        }

        // 2. 初始化待出队的队列（所有入度数为0的队列）
        Queue<Integer> queue = new ArrayDeque<>(numCourses);
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 3. 出队并更新依赖出队节点的节点的入度
        int[] result = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course;
            for (int deCourse : depended.get(course)) {
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