import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTraversals {
    static class Edge {
        int source;
        int dest;

        public Edge(int source, int dest) {
            this.source = source;
            this.dest = dest;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        // Adding edges to the respective lists
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));
        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));
        graph[6].add(new Edge(6, 5));
    }

    public static void BFS(ArrayList<Edge> graph[],boolean[] visited,int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.remove();
            if (!visited[current]) {
                System.out.print(current + " ");
                visited[current] = true;
                for (int i = 0; i < graph[current].size(); i++) {
                    Edge e = graph[current].get(i);
                    queue.add(e.dest);
                }
            }
        }
    }

    public static void DFS(ArrayList<Edge> graph[],boolean[] visited,int start) {
        System.out.println(start);
        visited[start] = true;
        for (int i = 0; i < graph[start].size(); i++) {
            Edge e = graph[start].get(i);
            if (!visited[e.dest]) {
                DFS(graph, visited, e.dest);
            }
//            DFS(graph, visited, e.dest);
        }

    }

    public static void main(String[] args) {
        int v = 7;
        ArrayList<Edge> graph[] = new ArrayList[v]; // Adjacency List

        createGraph(graph);

        boolean[] visited = new boolean[graph.length];
//        //for disconnected;
//        for(int i=0;i<v;i++){
//            if(visited[i]==false){
//                BFS(graph,visited,i);
//            }
//        }

//        //for connected;
//        BFS(graph,visited,0);

        //for connected
//        DFS(graph, visited, 0);

        //for disconnected;
        for(int i=0;i<v;i++){
            if(visited[i]==false){
                DFS(graph, visited, i);
            }
        }



        System.out.println();
    }
}
