
import java.util.ArrayList;

class HamiltonianPath {
    // Check if the graph has a Hamiltonian path
    public static boolean check(int N, int M, ArrayList<ArrayList<Integer>> Edges) {
        // visited array to track visited nodes
        boolean[] visited = new boolean[N];

        // Call DFS starting from node 0 (adjusting for zero-based index)
        dfs(Edges, visited, 0);

        // After DFS, check if all nodes were visited
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                return false;  // If any node is not visited, the graph is disconnected
            }
        }
        return true;  // All nodes are visited, the graph is connected
    }

    // Depth First Search (DFS) function
    public static void dfs(ArrayList<ArrayList<Integer>> Edges, boolean[] visited, int curr) {
        // Mark the current node as visited
        visited[curr] = true;

        // Recur for all the unvisited neighbors
        for (int neighbor : Edges.get(curr)) {
            if (!visited[neighbor]) {
                dfs(Edges, visited, neighbor);
            }
        }
    }

    // Main method for testing purposes
    public static void main(String[] args) {

        int N = 4, M = 4;

        // Create adjacency list
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
        }

        // Example edges (1-based indexing, adjusted for 0-based)
        edges.get(0).add(1);  // Edge 1-2 (0-based: 0-1)
        edges.get(1).add(0);  // Edge 1-2 (0-based: 1-0)
        edges.get(1).add(2);  // Edge 2-3 (0-based: 1-2)
        edges.get(2).add(1);  // Edge 2-3 (0-based: 2-1)
        edges.get(2).add(3);  // Edge 3-4 (0-based: 2-3)
        edges.get(3).add(2);  // Edge 3-4 (0-based: 3-2)

        // Call check method to see if there's a Hamiltonian path
        boolean result = check(N, M, edges);
        System.out.println(result ? 1 : 0);  // Output 1 for true, 0 for false
    }
}

