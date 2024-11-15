import java.util.*;

public class CycleDetection {
    static class Edge2 {
        int source;
        int dest;

        public Edge2(int source, int dest) {
            this.source = source;
            this.dest = dest;
        }
    }

    public static boolean Directed(ArrayList<Edge2> graph[], boolean[] visited, int curr, boolean[] rec) {
        visited[curr] = true;
        rec[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge2 e = graph[curr].get(i);
            if (rec[e.dest]) {
                return true; // Cycle detected
            } else if (!visited[e.dest]) {
                if (Directed(graph, visited, e.dest, rec)) {
                    return true; // Cycle detected in recursion
                }
            }
        }
        rec[curr] = false; // Backtrack
        return false; // No cycle found
    }

    public static void topSort(ArrayList<Edge2> graph[],boolean vis[],int curr,Stack<Integer> stack){
        vis[curr] = true;
        for(int i = 0; i < graph[curr].size(); i++) {
            Edge2 e = graph[curr].get(i);
            if (!vis[e.dest]) {
                topSort(graph, vis, e.dest, stack);
            }
        }
        stack.push(curr);
    }

    public static void topSorting(ArrayList<Edge2> graph[],int V){
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < V; i++) {
            if(!visited[i]) {
                topSort(graph, visited, i, stack);
            }
        }
        while(!stack.isEmpty()) {
            System.out.print(stack.pop()+" ");
        }
        System.out.println();
    }

    public static boolean Undirected(ArrayList<Edge2> graph[], boolean vis[], int curr, int parent) {
        vis[curr] = true;
        for(int i = 0; i < graph[curr].size(); i++) {
            Edge2 e = graph[curr].get(i);
            if (vis[e.dest] && e.dest != parent) {
                return true;
            } else if (!vis[e.dest]) {
                if (Undirected(graph, vis, e.dest, parent)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void createGraph(ArrayList<Edge2> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge2>();
        }

        // Adding edges in both directions to create an undirected graph
        graph[0].add(new Edge2(0, 1));
        graph[1].add(new Edge2(1, 0));

        graph[0].add(new Edge2(0, 2));
        graph[2].add(new Edge2(2, 0));

        graph[1].add(new Edge2(1, 3));
        graph[3].add(new Edge2(3, 1));

        graph[2].add(new Edge2(2, 4));
        graph[4].add(new Edge2(4, 2));

        graph[3].add(new Edge2(3, 5));
        graph[5].add(new Edge2(5, 3));

        graph[4].add(new Edge2(4, 5));
        graph[5].add(new Edge2(5, 4));

        graph[5].add(new Edge2(5, 6));
        graph[6].add(new Edge2(6, 5));


    }

    public static void main(String[] args) {
        int V = 7; // Number of vertices
        ArrayList<Edge2>[] graph = new ArrayList[V]; // Declare the graph
        createGraph(graph);

        boolean[] visited = new boolean[V];
        boolean[] rec = new boolean[V];

        // Detect cycle in directed graph
        System.out.println("Cycle Detection in Directed Graph:");
        boolean directedCycle = false;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (Directed(graph, visited, i, rec)) {
                    directedCycle = true;
                    System.out.println("Cycle detected in the directed graph.");
                    break; // Exit if a cycle is found
                }
            }
        }
        if (!directedCycle) {
            System.out.println("No cycle detected in the directed graph.");
        }

        // Perform topological sorting
        System.out.println("Topological Sorting of the Directed Graph:");
        topSorting(graph, V);

        // Reset the visited array before checking the undirected graph
        Arrays.fill(visited, false);

        // Detect cycle in undirected graph
        System.out.println("Cycle Detection in Undirected Graph:");
        boolean undirectedCycle = false;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (Undirected(graph, visited, i, -1)) {
                    undirectedCycle = true;
                    System.out.println("Cycle detected in the undirected graph.");
                    break; // Exit if a cycle is found
                }
            }
        }
        if (!undirectedCycle) {
            System.out.println("No cycle detected in the undirected graph.");
        }
    }

}
