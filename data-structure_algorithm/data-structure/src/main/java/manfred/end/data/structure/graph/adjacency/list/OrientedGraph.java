package manfred.end.data.structure.graph.adjacency.list;

import java.util.LinkedList;

/**
 * @author Manfred since 2019/9/23
 */
public class OrientedGraph {
    /**
     * 节点数量
     */
    private int count;

    /**
     * 邻接表
     */
    private LinkedList<Integer>[] adj;

    public OrientedGraph(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        }
        this.count = count;
        adj = new LinkedList[count];
    }

    public void addEdge(int s, int t) {
        if (s < 0 || s >= count) {
            throw new IndexOutOfBoundsException("vertex " + s + " not in bound.");

        }
        if (t < 0 || t >= count) {
            throw new IndexOutOfBoundsException("vertex " + t + " not in bound.");
        }
        adj[s].add(t);
    }

}
