import java.util.*;
public class PrintAdjacencyList {
    static class Edge{
        int source;
        int dest;
        public Edge(int source,int dest){
            this.source = source;
            this.dest = dest;
        }
    }
    public static void printList(int V, int[][] edges){
        ArrayList<Edge> graph[] = new ArrayList[V];
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<Edge>();
        }
        for(int i=0;i<edges.length;i++){
            int src = edges[i][0];
            int destin = edges[i][1];
            graph[src].add(new Edge(src, destin));
            graph[destin].add(new Edge(destin, src));
        }
        for (int i = 0; i < V; i++) {
            System.out.print(i + " -> ");
            for (Edge edge : graph[i]) {
                System.out.print(edge.dest + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int v = 5;
        int[][]  edges  = {{0,1},{0,4},{4,1},{4,3},{1,3},{1,2},{3,2}};
        printList(v,edges);
    }


}
