package manfred.end.data.structure.graph.adjacency.list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 使用邻接表表示的图
 *
 * @author Manfred since 2019/8/21
 */
public class Graph {
    /**
     * 节点数量
     */
    private int count;

    /**
     * 邻接表
     */
    private LinkedList<Integer> adj[];

    public Graph(int count) {
        this.count = count;
        adj = new LinkedList[count];
        for (int i = 0; i < count; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }

        boolean[] visited = new boolean[count];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        int[] pre = new int[count];
        for (int i = 0; i < count; i++) {
            pre[i] = -1;
        }
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            LinkedList<Integer> curAdj = adj[cur];
            for (Integer curAdjNode : curAdj) {
                if (!visited[curAdjNode]) {
                    pre[curAdjNode] = cur;
                    if (t == curAdjNode) {
                        print(pre, s, t);
                        return;
                    }
                    queue.add(curAdjNode);
                    visited[curAdjNode] = true;
                }
            }
        }
    }

    public void dfs(int s, int t) {
        if (s == t) {
            return;
        }
        boolean[] visited = new boolean[count];
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visited[s] = true;
        int[] pre = new int[count];
        for (int i = 0; i < count; i++) {
            pre[i] = -1;
        }
        doDfs(stack, pre, visited, s, t);
    }

    private boolean doDfs(Stack<Integer> stack, int[] pre, boolean[] visited, int source, int target) {
        Integer cur = stack.peek();
        LinkedList<Integer> curAdj = adj[cur];
        for (Integer curAdjNode : curAdj) {
            if (!visited[curAdjNode]) {
                // 入栈
                stack.push(curAdjNode);
                visited[curAdjNode] = true;
                pre[curAdjNode] = cur;
                {
                    System.out.print("调试信息：");
                    print(pre, source, curAdjNode);
                    System.out.println();
                }


                if (target == curAdjNode) {
                    print(pre, source, target);
                    return true;
                }

                boolean childFound = doDfs(stack, pre, visited, source, target);
                if (childFound) {
                    return true;
                }

                // 出栈
                pre[curAdjNode] = -1;
                visited[curAdjNode] = false;
                stack.pop();
            }
        }
        return false;
    }


    private void print(int[] prev, int s, int t) { // 递归打印 s->t 的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }
}


