import java.util.*;
public class GFG_BFS {
    static class Edge{
        int source;
        int destination;
        public Edge(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }
    public static void main(String args[]){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        adj.add(new ArrayList<>(Arrays.asList(1, 2)));  // List for node 0
        adj.add(new ArrayList<>(Arrays.asList(0, 2)));  // List for node 1
        adj.add(new ArrayList<>(Arrays.asList(0, 1, 3, 4)));  // List for node 2
        adj.add(new ArrayList<>(Arrays.asList(2)));  // List for node 3
        adj.add(new ArrayList<>(Arrays.asList(2)));
        boolean[] visited = new boolean[adj.size()];
        int curr = 0;
        bfs(adj,visited,curr);
    }

    public static void bfs(ArrayList<ArrayList<Integer>> adj,boolean[] visited,int curr){
        int v = adj.size();
        ArrayList<Edge> graph[] = new ArrayList[v];
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<Edge>();
        }
        for(int i=0;i<adj.size();i++){
            int source = i;
            for (int neighbor : adj.get(i)) {
                int destination = neighbor;
                graph[i].add(new Edge(source,destination));
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(curr);

        while(!queue.isEmpty()){
            int current = queue.remove();
            while(!visited[current]){
                System.out.println(current);
                visited[current] = true;
                for(int i=0;i<graph[current].size();i++){
                    Edge e = graph[current].get(i);
                    queue.add(e.destination);
                }
            }
        }
    }

    public ArrayList<Integer> bfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[adj.size()];
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // Start BFS from node 0
        int curr = 0;
        queue.add(curr);
        visited[curr] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);  // Add current node to the result list

            // Visit all the adjacent nodes
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }

}
