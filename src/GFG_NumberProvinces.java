import java.util.*;
public class GFG_NumberProvinces {
    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(3);
        adj.get(3).add(1);
        adj.get(2).add(4);
        adj.get(4).add(2);

        int V = adj.size();
        int count =0;

        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                // Perform DFS if the city has not been visited yet
                dfs(adj, visited, i);
                // After DFS, we have found one more province
                count++;
            }
        }
        System.out.println(count);
    }
    public static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int curr) {
        // Mark the current city as visited
        visited[curr] = true;

        // Explore all cities that are directly connected to the current city
        for (int neighbor = 0; neighbor < adj.get(curr).size(); neighbor++) {
            // If there's a path between the current city and the neighbor, and the neighbor isn't visited yet
            if (adj.get(curr).get(neighbor) == 1 && !visited[neighbor]) {
                dfs(adj, visited, neighbor); // Recursively visit the neighbor
            }
        }
    }

}
