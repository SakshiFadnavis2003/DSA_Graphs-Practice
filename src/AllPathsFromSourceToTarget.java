import java.util.ArrayList;

public class AllPathsFromSourceToTarget {
    static class Edge2 {
        int source;
        int dest;

        public Edge2(int source, int dest) {
            this.source = source;
            this.dest = dest;
        }
    }

    public static void createGraph(ArrayList<Edge2> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge2>();
        }

        // Adding edges to the respective lists
        graph[0].add(new Edge2(0, 1));
        graph[0].add(new Edge2(0, 2));
        graph[1].add(new Edge2(1, 0));
        graph[1].add(new Edge2(1, 3));
        graph[2].add(new Edge2(2, 0));
        graph[2].add(new Edge2(2, 4));
        graph[3].add(new Edge2(3, 1));
        graph[3].add(new Edge2(3, 4));
        graph[3].add(new Edge2(3, 5));
        graph[4].add(new Edge2(4, 2));
        graph[4].add(new Edge2(4, 3));
        graph[4].add(new Edge2(4, 5));
        graph[5].add(new Edge2(5, 3));
        graph[5].add(new Edge2(5, 4));
        graph[5].add(new Edge2(5, 6));
        graph[6].add(new Edge2(6, 5));
    }

    public static void dfs(ArrayList<Edge2> graph[],boolean[] visited,int target,String path,int curr){
        if(curr==target){
            System.out.println(path);
            return;
        }
        for(int i=0;i<graph[curr].size();i++){
            Edge2 e = graph[curr].get(i);
            if(!visited[e.dest]){
                visited[curr]=true;
                dfs(graph,visited,target,path+e.dest,e.dest);
                visited[curr]=false;
            }
        }
    }

    public static void main(String[] args) {
        int v = 7;
        ArrayList<Edge2> graph[] = new ArrayList[v];
        createGraph(graph);
        dfs(graph,new boolean[v],5,"0",0);

    }
}
