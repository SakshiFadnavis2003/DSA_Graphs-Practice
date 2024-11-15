import java.util.ArrayList;

public class GraphWithWeights {
    static class Edge2 {
        int source;
        int dest;
        int weight;

        public Edge2(int source, int dest,int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void createGraph(ArrayList<Edge2> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge2>();
        }

        // Adding edges to the respective lists
        graph[0].add(new Edge2(0, 2,2));
        graph[1].add(new Edge2(1, 2,10));
        graph[1].add(new Edge2(1, 3,0));
        graph[2].add(new Edge2(2, 0,2));
        graph[2].add(new Edge2(2, 3,-1));
        graph[3].add(new Edge2(3, 1,0));
        graph[3].add(new Edge2(3, 2,-1));
    }

    public static void main(String[] args) {
        int v = 4;
        ArrayList<Edge2> graph[] = new ArrayList[v];
        //Ways to store graph = 1. Adjacency List to store the edges information
        //why usage - less space,only required data,O(1) complexity to check

        createGraph(graph);

        // Print neighbors of vertex 2
        for (int i = 0; i < graph[2].size(); i++) {
            Edge2 e = graph[2].get(i);
            System.out.println(e.dest + " " + e.weight);
        }

        //2. Adjacency Matrix usage - to find out if the edge is present or not
        //Extra space - O(V2)  and time - O(v) used

        //3. Edge List - ArrayList of Edges
        // usage to sort the arraylist
        // used in min spanning tree

        //4. Implicit Graph -
        // used to implicit in shortest path in matrix from one point to another
        //Given matrix is considered as graph
        //used in flood fill algorithm
    }
}
