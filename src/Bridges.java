import java.util.ArrayList;

public class Bridges {
    static class Edge {
        int src;
        int dest;
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }
    public static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));
        graph[1].add(new Edge(1, 0));
        graph[2].add(new Edge(2, 1));
        graph[3].add(new Edge(3, 4));
    }

    public static void getBridge(ArrayList<Edge> graph[],int V){
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean vis[] = new boolean[V];

        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                dfs(graph,i,vis,dt,low,time,-1);
            }
        }
    }
    public static void dfs(ArrayList<Edge> graph[],int curr,boolean vis[], int[] dt,int[] low,int time,int parent){
        vis[curr] = true;
        dt[curr] = low[curr] =++time;
        for(int i=0; i<graph[curr].size(); i++) {
            Edge edge = graph[curr].get(i);
            if(edge.dest == parent){
                continue;
            }
            else if(!vis[edge.dest]) {
                dfs(graph,edge.dest,vis,dt,low,time,curr);
                low[curr] = Math.min(low[curr], low[edge.dest]);
                if(dt[curr]<low[edge.dest]) {
                    System.out.println(curr+"->"+edge.dest);
                }
            }
            else{
                low[curr] = Math.min(low[curr], dt[edge.dest]);
            }
        }
    }

    public static void dfsAP(ArrayList<Edge> graph[],int curr,boolean vis[], int[] dt,int[] low,int time,int parent, boolean[] ap){
        vis[curr] = true;
        dt[curr] = low[curr] =++time;
        int children =0;
        for(int i=0; i<graph[curr].size(); i++) {
            Edge edge = graph[curr].get(i);
            int neigh = edge.dest;
            if(edge.dest == parent){
                continue;
            }
            else if(vis[edge.dest]) {
                low[curr] = Math.min(low[curr], dt[neigh]);

            }
            else{
                dfs(graph,neigh,vis,dt,low,time,curr);
                low[curr] = Math.min(low[curr], low[neigh]);
                if(dt[curr]<=low[edge.dest] && parent !=-1) {
                    ap[curr] = true;
                }
                children++;
            }
        }
        if(parent!=-1 && children>1) {
            ap[curr] = true;
        }
    }

    //O(V+E)
    public static void getAP(ArrayList<Edge> graph[],int V){
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean[] ap = new boolean[V];
        boolean[] vis = new boolean[V];
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                dfs(graph,i,vis,dt,low,time,-1);

            }
        }
        for(int i=0; i<V; i++) {
            if(ap[i]) {
                System.out.println(i);

            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        getBridge(graph,V);
    }
}
