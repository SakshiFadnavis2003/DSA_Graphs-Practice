import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        //Ways to store graph =

        // 1. Adjacency List to store the edges information
        //why usage - less space,only required data,O(1) complexity to check

        //2. Adjacency Matrix usage - to find out if the edge is present or not
        //Extra space - O(V2)  and time - O(v) used

        //3. Edge List - ArrayList of Edges
        // usage to sort the arraylist
        // used in min spanning tree

        //4. Implicit Graph -
        // used to implicit in shortest path in matrix from one point to another
        //Given matrix is considered as graph
        //used in flood fill algorithm

        /*Graph Traversals -

        1. Breadth First Search (time complexity - O(v+e))  - Go to immediate neighbors first
        - indirect level order traversals
        - used queue Data Structure (FIFO) for visited array(boolean)
        while(!queue.isEmpty()){
            int curr = queue.remove();
            if(vis[curr] == F){
                print(curr);
                vis[curr] = T;
                curr node neighbors in queue
            }
         }
        for(int i = 0 to graph[curr].size())
        {
            Edge e = graph[curr].get(i);
        }
        queue.add(e.dest)//neighbor

        //Disconnected components = tuta hua graph
        when diconnected starting point = 1 is not enough

        for(int i=0 to v){
            vis[i] = false;
            i = starting;
        } ->in main code


        2. Depth First Search -
        - Keep going to the 1st neighbour
        - recursion is used

        visited -
        - print
        - vis[curr] = true
        - neighbor visit

        void dfs(graph,curr,vis[]){
            print(curr);
            vis[curr] = true;
            for(int i=0 to graph[curr].size()){
                Edge e = graph[curr].get(i);
                dfs(graph,e.dest,vis):
            }
        }
        //in graphs visited is used because there are cycles in the graph but not in tree


        All paths from source to target -
        - Complexity - O(V^V)
        - For given src and target, print all paths that exist from src to target
        - Based on BFS and DFS
        - Modified DFS
            DFS(graph,visited,curr,String path,target){
                //source is not needed
                if(curr==target){
                    print(path);
                    return;
                }
            }
            for(int i=0 to neighbors = graph[curr].size()){
                //Getting neighbors
                Edge e = graph[curr].get(i)
                if(visited[e.dest]==false){
                    visited[curr] = true;
                    DFS(graph,visited,e.dest,path+e.dest,tar);
                    visited[curr] = false;
                }
            }

           Cycle Detection

           1. Directed Graph
           - Complexity - O(v+e)
           - Modified DFS
           - visited array
           - recursion stack - if the child node is visited already then cycle

           dfs(graph,visited,curr,rec[]){
                vis[curr] = true;
                rec[curr] = true;

                for(int i=0 to graph[curr].size()){
                    Edge e = graph[curr].get(i);
                    if(rec[e.dest] == true){
                        return true;
                    }
                    else-if(!visited[e.dest]){
                        dfs(graph,visited,e.dest,rec);
                    }
                    rec[curr] = false;
                }
           }

            //Topological Sorting
                - Directed Acyclic Graph is directed graph with no cycles
                - tells about dependency
                - Topological sorting is used only for DAG'S (not for non DAG'S)
                - it is linear order of vertices such that every directed edge u -> v, the vertex u comes before v in the order
                - a -> b -> c -> d
                  |         |^
                  -----------
                  a->b->c->d
                - using DFS its done
                - using Stack data structure
                - O(v+e)

                dfs(graph,vis,curr,stack){
                    vis[curr] = True;
                    for(int i=0;i<graph[curr].size();i++){
                        Edge e = graph[curr].get(i);
                        if(!visited[e.dest]){
                            dfs(graph,vis,e.dest,stack);
                        }
                    }
                    stack.push(curr); //taaki first last mai nikle
                 }

                 for(int i= 0 to v){
                    if(vis[i] == False){
                        dfs;
                    }
                 }

                 print(stack.pop());

              // Cycles in graphs
              methods to detech cycles in graph
               - undirected graph - dfs
                                  - bfs
                                  - dsu (Disjoint Set Union)
               - directed graph - dfs
                                - bfs
                                - topological Sorting(Kalvins Algorithm)

              2. For undirected graph (dfs) - O(V+E)
                - modified dfs
                - jisko apne visit nahi kiya pahele se visited tha toh ussi ko cycle detect hogaya
                - 3 types of neighbors any graph can have -
                    - Visited - true but not parent - yes cycle
                    - visited - true but parent - do nothing
                    - visited - false

                dfs(graph,vis[],curr,parent){
                    vis[curr] = true;
                    for(int i=0 to graph[curr].size()){
                        Edge e = graph[curr].get(i);
                        if(vis[e.dest] && parent!=e.dest){
                            return true;
                        }
                        if(!vis[e.dest]){
                            if(dfs(graph,vis,e.dest.curr)==true){
                                return true;
                            }
                        }
                    }
                }

               //SHORTEST PATH ALGORITHM

               1. Dijkstra's Algorithm
               - bfs
               - work for - edges ka weight > 0
               - less time complexity
               - Shortest distance from the source to all vertices
               - Relaxation - if distance from u node as an medium is less than distance to directly v then distance v will be the minimum one that is via node u
                    if(dist[u]+wt<dist[v]){
                        dist[v]= dist[u] + wt;
                        pq.add(v,dist[v]);
                    }
                - Greedy Algorithm
                - It can be solved using queue but we are unsing priority queue(more time complexity and sort it on the basis of priority)

                - approach
                    jab tak saare nodes ho nahi jaate
                        - node  - check visit = false then update distance = shortest
                        - relaxation

                    shortest distance nikalne k liye = priority queue

                    needed info - node and distance

                - pseudo code
                    - initialization
                        pair(node,distance[],visited[]){
                        }
                         PQ.add([0,0])
                         distance[]
                         visited[]

                    - algorithm
                        while(!pq.isEmpty()){
                            pair curr = pq.remove();
                            if(!visited[curr.node]{
                                visited[curr.node] = True;
                                for(//neighbors waala loop){
                                    edge e = ....
                                    u = e.src;
                                    v = e.dest;
                                    relaxation code
                                }
                            }
                        }

                2. Bellman Ford Algorithm
                - Shortest Distance from the source to all vertices
                - more time complexity
                - work for edges less than 0 too
                - Dynammic Programming is used

                - approach
                    - Perform this operation V-1 times
                        for all edges(u,v)
                            relaxation

                - Does not work for Negative Weight cycle
                - ek aur baar i and j waala loop run karne k

           //Minimum Spanning Tree
            - a subgraph of a graph in which
                - allvertices are included
                - all vertices are connected
                - no cycles
                - edge weight is minimum
                - only undirected and weighted graph

            1. Prim's Algorithm - O(E log E)
                - print minimum cost of the mst
                - there are 2 sets - non mst set (initially all vertices are included) and second one is mst set which will tell ki konsa mst k liye vertex include hogaya hai
                - use priority queue for minimum distance
                - non mst set - priority queue
                - mst set - visited waala boolean array
                - approach
                    - start with one node(include in MST and remove from non MST)
                    - Calculate cost from every node added in MST to all other nodes in non MST
                    - Least code node is added in MST and removed from non MST and cost is added
                    - Repeat till non mst becomes empty
                    - print cost

                - pseudocode
                pair(dest_node,cost)

                pq.arr(pair(0,0))
                boolean vis[] = false
                while(!pq.isEmpty()){
                    Pair curr = pq.remove()
                    if(!vis[curr.node]){
                        vis[curr.node] = True;
                        cost = cost + curr.cost
                        //adding nodes one by one in priority queue as per the neighbors of the src node
                        for(int i=0;i<graph[curr.node].size();i++){
                            Edge e = graph[curr.node].get(i)
                            if(!vis[e.dest]){
                                pq.add(e.dest,e.wt)
                            }
                        }
                    }
                }


            // Strongly Connected Component
            SCC is a component in which we can reach every vertex of the component from every other vertex
            print no of SCC's


                1. Kosaraju's Algorithm - O(V+E)
                    - to find no of scc's
                    - only for directed graphs
                    - uses reverse DFS - starts from last nodes
                                       - like in topologicalsorting
                    - Steps :
                        - Get nodes in Stack (topologicalSorting) - O(V+E)
                        - Transpose the graph (reverse the edges of the graph ) - O(V+E)
                        - Do DFS according to stack nodes on the transpose graph - O(V+E)

            //Bridge in Graphs
            - Bridge is an edge whose deletion increases the graph's number of connected components

                1. Tarjan's Algorithm
                - used to find bridge, Articulation Points, scc, topological Sorting
                - uses DiscoveryTime[]
                - uses lowestDiscoveryTime[] (of all neighbors)

                - Needs -
                    - DFS
                    DiscoveryTime[] - konse vakt par konse nodes par pohochii
                    lowestDiscoveryTime[] - sabse lowest discovery time
                    kyu jarurat hai uparwaale arrays ki ??
                        - if u and v ye bridge hai :
                        - dt[u] < lowestDicoveryTime[v] (dt[v and all of the nodes connected with v])

                  - Algorithm
                    DFS
                    vis[curr] = True
                    dt[curr] = low[curr] = ++time
                    for(int i=0 to neighbors){
                        Edge e
                        1. if parent
                            continue
                        2. !vis[e.dest] -> dfs
                            low[curr] = min(low[curr],low[neighbor])
                            //bridge condition -
                            if(dt[curr] < low[neighbor]{
                                print(curr+neighbor)
                            }
                        3. vis[e.dest]
                            low[curr] = min(low[curr],dt[weight])
                    }

            // Articulation Point
                - A vertex in an undirected connected graph is an articulation point if removing it diconnects the graph

            // Tarjan's Algorithm
                - Ancestor
                    - a node A that was dicovered before curr node in DFS, is the ancestor of the curr
                    - Discovery time(1st time kab discover hui) and lowest() (all possible reachable nodes from the current node)  will be used for ancestor
                    - DFS based
                    - O(V+E) complexity

                    - conditions for a node to be an Articulation Point
                        - if curr node's parent is -1 (i.e. its the start of DFS) && disconnected children>1

                        - node u ----- node v - Checking if node u is Articulation Point
                            - if it's a bridge, u is the Articulation Point
                            - if cycle is forming with u, u is the Articulation Point

                    - child node can be
                         - parent
                         - backEdge/ancestor
                         - child

                    - basic pseudocode
                        - dfs
                            vis[curr] =time
                            dt[curr] = low[curr] = ++time
                            children = 0
                            for(neigh){
                                Edge e (src->dest)
                                1. weight = par -> ignore
                                2. if(neigh vis){
                                        low[curr] = min(low[curr],dt[neigh])
                                    }
                                3. if(neigh unvisit){
                                        dfs
                                        low[curr] = min(low[curr],low[neigh])
                                    }
                                if(dt[curr]<=low[neigh] && par!=-1){
                                    curr - AP
                                    child++
                                }
                            }
                            if(par=-1 && children>1){
                                curr->AP
                            }





         */
    }
}
