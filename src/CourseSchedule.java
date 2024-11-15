import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class CourseSchedule {

    public static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) {
        // Initialize the adjacency list for all tasks
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Fill the adjacency list based on the given prerequisites
        for (ArrayList<Integer> prerequisite : prerequisites) {
            int task = prerequisite.get(0);
            int preTask = prerequisite.get(1);
            adjList.get(preTask).add(task);
        }

        int[] result = new int[n];
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        // Check if a topological sort is possible
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (hasCycle(adjList, visited, recStack, i)) {
                    return new int[0]; // Return an empty array if there’s a cycle
                }
                topSort(adjList, visited, i, stack);
            }
        }

        // Collect the result in the required order
        int j = 0;
        while (!stack.isEmpty()) {
            result[j++] = stack.pop();
        }
        return result;
    }

    // Topological sort using DFS
    public static void topSort(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int curr, Stack<Integer> stack) {
        visited[curr] = true;

        for (int neighbor : adjList.get(curr)) {
            if (!visited[neighbor]) {
                topSort(adjList, visited, neighbor, stack);
            }
        }
        stack.push(curr);
    }

    // Detect cycle
    public static boolean hasCycle(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, boolean[] recStack, int curr) {
        if (recStack[curr]) {
            return true;
        }
        if (visited[curr]) {
            return false;
        }

        visited[curr] = true;
        recStack[curr] = true;

        for (int neighbor : adjList.get(curr)) {
            if (hasCycle(adjList, visited, recStack, neighbor)) {
                return true;
            }
        }

        recStack[curr] = false;
        return false;
    }

    // Main method to test the output format
    public static void main(String[] args) {
        int n = 2, m = 1;
        ArrayList<ArrayList<Integer>> prerequisites = new ArrayList<>();
        prerequisites.add(new ArrayList<>(List.of(1, 0)));

        int[] order = findOrder(n, m, prerequisites);

        if (order.length == 0) {
            System.out.println(0); // No valid order
        } else {
            System.out.println(1); // Valid order
        }
    }


    

}
