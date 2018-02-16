
/**
 * Created by Aaron Gu on 2016/11/20.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

public class solution_2 {
    private static int N = 10;          // the total points in the graph, since we need add two node: sink and source.
    private static int M = Integer.MAX_VALUE;
    /* the DFS function is a normal depth first search function implemented using stack*/
    public static boolean DFS(int[][] Res_Graph, int s, int t,int[] former){
        Stack<Integer> Vertex = new Stack<Integer>();
        boolean[] visited = new boolean[N]; // record which node has been visited.
        for(int i=0;i<N;i++){
            visited[i] = false;
        }
        visited[s] = true;
        Vertex.push(s);
        former[s] = -1;
        int cur_node;
        int i;
        while(!Vertex.isEmpty()){   // iterate until the stack is empty.
            cur_node = Vertex.peek();
            for(i=0;i<N;i++) {
                if (Res_Graph[cur_node][i] > 0 && !visited[i]) {
                    former[i] = cur_node;
                    Vertex.push(i);
                    visited[i] = true;
                    break;
                }
            }
            if(i == N){  // if did not find any path, pop the top one node in stack.
                Vertex.pop();
            }
        }


        return visited[t];  // return it the sink has been visited or not.
    }
    /* the Ford function: implementation of Ford-Fulkerson Algorithm */
    public static int Ford(int[][] Graph, int s, int t){
        int u,v;
        int MaxFlow = 0;
        int flow;
        int path[] = new int[N];   // the int array used to record the path or the finding node.
        int[][] Res_Graph = new int[N][N];  // the Residual Graph
        /* initialize the Residual Graph, since the initial Flow is 0, the Residual Graph will be exactly the same as
            the Graph with capacity */
        for (u = 0; u < N; u++)
            for (v = 0; v < N; v++)
                Res_Graph[u][v] = Graph[u][v];
        /* while we can find a path in Residual Graph, from the source to the sink*/
        while(DFS(Res_Graph,s,t,path)){
            u = t;
            flow = Integer.MAX_VALUE;// initialize with positive infinite
            // determine the minimum flow along the path we find
            while(u != s){
                flow = Math.min(flow,Res_Graph[path[u]][u]);
                u = path[u];
            }
            // update the Residual Graph, here, I skip the procedure as first, updating the graph then compute the Residual Graph
            // but just update the Residual Graph reversely. It's trivial to show the results are the same.
            for(u = t;u!=s;u=path[u]){
                Res_Graph[path[u]][u] = Res_Graph[path[u]][u] - flow;
                Res_Graph[u][path[u]] = Res_Graph[u][path[u]] + flow;
            }

            MaxFlow += flow;
        }
        return MaxFlow;
    }


    /* main function */
    public static void main(String[] args) throws FileNotFoundException{
        int[][] Graph = new int[][]{{0,50,36,11,8,0,0,0,0,0},{0,0,0,0,0,M,M,M,M,0},{0,0,0,0,0,0,M,0,M,0},
                                        {0,0,0,0,0,0,0,M,M,0},{0,0,0,0,0,0,0,0,M,0},{0,0,0,0,0,0,0,0,0,45},
                                       {0,0,0,0,0,0,0,0,0,42},{0,0,0,0,0,0,0,0,0,10},{0,0,0,0,0,0,0,0,0,3},{0,0,0,0,0,0,0,0,0,0}};
        int maxflow = Ford(Graph,0,9);
        System.out.println(maxflow);

    }

}
