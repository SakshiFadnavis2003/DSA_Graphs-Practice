import java.util.*;
public class ShortestPathsAlgorithm {
    static class Edge {

        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    public static class pair implements Comparable<pair> {
        int node;
        int distance;
        public pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(pair o) {
            return this.distance - o.distance; //ascending order
            //return o.distance - this.distance; //descending order

        }
    }
    static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));
        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));
        graph[2].add(new Edge(2, 4, 3));
        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    // O(E+ E logV)
    public static void dijkstra(ArrayList<Edge> graph[],int src,int V) {
        PriorityQueue<pair> pq = new PriorityQueue<>(); //E log V
        int dist[] = new int[V];
        for(int i=0; i<V; i++) {
            if(i!=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        boolean[] visited = new boolean[V];
        pq.add(new pair(0,0));

        while(!pq.isEmpty()) {
            pair curr = pq.remove();
            if(!visited[curr.node]){
                visited[curr.node] = true;
                for(int i =0; i<graph[curr.node].size(); i++) {
                    Edge edge = graph[curr.node].get(i);
                    int u =edge.src;
                    int v = edge.dest;
                    if(dist[u]+edge.wt<dist[v]){
                        dist[v] = dist[u]+edge.wt;
                        pq.add(new pair(v,dist[v]));
                    }
                }
            }
        }
        for(int i=0; i<V; i++) {
            System.out.print(dist[i] +" ");
        }
        System.out.println();
    }

    public static void bellmanFord(ArrayList<Edge> graph[],int src,int V){
        int dist[] = new int[V];
        for(int i=0; i<V; i++) {
            if(i!=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        for(int k=0;k<V-1;k++) { //O(V)
            //The below 2 loops combinely gives the complexity as O(E)
            for(int i=0;i<V;i++){
                for(int j=0;j<graph[i].size();j++){
                    Edge edge = graph[i].get(j);
                    int u = edge.src;
                    int v = edge.dest;
                    if(dist[u]!= Integer.MAX_VALUE && dist[u]+edge.wt<dist[v]){
                        dist[v] = dist[u]+edge.wt;
                    }
                }
            }
        }

        for(int i=0;i<V;i++){
            for(int j=0;j<graph[i].size();j++){
                Edge edge = graph[i].get(j);
                int u = edge.src;
                int v = edge.dest;
                if(dist[u]!= Integer.MAX_VALUE && dist[u]+edge.wt<dist[v]){
                    System.out.println("Negative weight cycle");

                }
            }
        }

        for(int i=0; i< dist.length; i++) {
            System.out.print(dist[i] +" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

    }
}
