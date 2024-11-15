import java.util.ArrayList;

public class GFG_DetectCycles {
    public static void main(String[] args) {
        // Create an adjacency list to represent the graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Example: create a graph with 4 vertices
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to the graph (undirected)
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);

        // Print the adjacency list
        System.out.println("Adjacency List:");
        for (int i = 0; i < adj.size(); i++) {
            System.out.println(i + " -> " + adj.get(i));
        }

        // Array to track visited nodes
        boolean[] visited = new boolean[4];
        int cycleCount = 0;

        // Check for cycles in all components of the graph
        for (int i = 0; i < 4; i++) {
            if (!visited[i]) {
                // If detectCycles returns true, increment the cycle count
                if (detectCycles(adj, visited, i, -1)) {
                    cycleCount++;
                }
            }
        }

        // Output the result: total number of cycles found
        System.out.println("Total cycles detected: " + cycleCount);
    }

    // Modified detectCycles to return boolean and handle cycle detection
    public static boolean detectCycles(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int curr, int parent) {
        visited[curr] = true;

        // Visit all neighbors of the current node
        for (int neighbor : adj.get(curr)) {
            // If the neighbor is already visited and is not the parent, a cycle is detected
            if (visited[neighbor] && parent != neighbor) {
                return true;  // A cycle is found
            }

            // Recursively visit unvisited neighbors
            if (!visited[neighbor]) {
                if (detectCycles(adj, visited, neighbor, curr)) {
                    return true;  // If a cycle is found in the recursive call, propagate the cycle detection
                }
            }
        }

        return false;  // No cycle detected in this component
    }
}
