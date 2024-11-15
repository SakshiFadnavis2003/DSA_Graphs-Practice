import java.util.*;
//CYCLE DETECTION -  a cycle, which means no course can be completed because each one is waiting for another to finish first.
public class GFG_PrerequisiteTasks {
    // Returns true if all courses can be finished, otherwise false
    public static boolean isPossible(int N, int P, int[][] prerequisites) {
        // Build the adjacency list for the graph
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            adjList.get(pre[1]).add(pre[0]); // Add an edge from pre[1] -> pre[0]
        }

        boolean[] visited = new boolean[N];
        boolean[] onStack = new boolean[N];  // To track the nodes currently in the recursion stack

        // Perform DFS on each course
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                if (hasCycle(adjList, visited, onStack, i)) {
                    return false;  // If a cycle is detected, return false
                }
            }
        }
        return true;  // If no cycles, it's possible to finish all courses
    }

    // Helper function to check for a cycle in the graph using DFS
    public static boolean hasCycle(List<List<Integer>> adjList, boolean[] visited, boolean[] onStack, int curr) {
        // If current node is in recursion stack, cycle detected
        if (onStack[curr]) {
            return true;
        }

        // If already visited, no need to visit again
        if (visited[curr]) {
            return false;
        }

        // Mark the current node as visited and part of the recursion stack
        visited[curr] = true;
        onStack[curr] = true;

        // Recur for all the unvisited neighbors
        for (int neighbor : adjList.get(curr)) {
            if (hasCycle(adjList, visited, onStack, neighbor)) {
                return true;
            }
        }

        // Backtrack: remove the current node from the recursion stack
        onStack[curr] = false;
        return false;
    }

    public static void main(String[] args) {
        int N = 2, P = 2;
        int[][] prerequisites = {{1,0},{0,1}};  // Course dependencies
        boolean result = isPossible(N, P, prerequisites);
        System.out.println(result ? "It is possible to finish all courses" : "It is not possible to finish all courses");
    }
}
