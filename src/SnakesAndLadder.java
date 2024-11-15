import java.util.*;

public class SnakesAndLadder {

    // Method to find the minimum number of throws required
    public static int minThrow(int N, int arr[]) {
        // Create a map to store ladder destinations
        HashMap<Integer, Integer> map = new HashMap<>();
        // Boolean array to keep track of visited cells
        boolean[] visited = new boolean[30];

        // Processing ladders and snakes
        for (int i = 0; i < N * 2 - 1; i += 2) {
            map.put(arr[i], arr[i + 1]); // Ladder mapping
        }

        // BFS to find the minimum number of throws
        return bfs(map, visited);
    }

    // BFS implementation to find the minimum throws to reach the last cell
    public static int bfs(HashMap<Integer, Integer> map, boolean[] visited) {
        // Moves for a dice (1 to 6)
        int[] moves = {1, 2, 3, 4, 5, 6};
        // Queue for BFS; storing the position and number of throws
        Queue<int[]> queue = new LinkedList<>();
        // Start from position 0 with 0 throws
        queue.add(new int[]{0, 0});
        visited[0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currPos = curr[0];   // Current position
            int throwsCount = curr[1]; // Number of throws taken so far

            // If we reach the last position (position 29), return the number of throws
            if (currPos == 29) {
                return throwsCount;
            }

            // Try all possible dice rolls (1-6)
            for (int i = 0; i < moves.length; i++) {
                int nextPos = currPos + moves[i];

                // If next position is within bounds and not visited
                if (nextPos <= 29 && !visited[nextPos]) {
                    visited[nextPos] = true;

                    // If there's a ladder, we move directly to the ladder's destination
                    if (map.containsKey(nextPos)) {
                        queue.add(new int[]{map.get(nextPos), throwsCount + 1});
                    } else {
                        // For regular cells, add the new position
                        queue.add(new int[]{nextPos, throwsCount + 1});
                    }
                }
            }
        }
        return -1; // If we can't reach the last position, return -1
    }

    public static void main(String[] args) {
        // Test case 1: Expected output 5
        int arr[] = {11, 4, 29, 17, 21, 7, 16, 12};
        int N = 4;  // Total number of ladders
        System.out.println(minThrow(N, arr));  // Output the minimum throws required
    }
}
