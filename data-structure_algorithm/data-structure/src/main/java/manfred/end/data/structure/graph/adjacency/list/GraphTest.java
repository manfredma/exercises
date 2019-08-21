package manfred.end.data.structure.graph.adjacency.list;

import org.junit.Test;

/**
 * @author Manfred since 2019/8/21
 */
public class GraphTest {

    @Test
    public void testBfs() {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        graph.bfs(0, 6);
        System.out.println();

        graph.dfs(0, 6);
        System.out.println();
        graph.dfs(0, 7);
    }
}
