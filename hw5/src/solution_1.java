
/**
 * Created by Aaron Gu on 2016/11/20.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

public class solution_1 {
    private static int N = 202;          // the total points in the graph, since we need add two node: sink and source.
  /* the DFS function is a normal depth first search function implemented using stack*/
    public static boolean DFS(int[][] Res_Graph, int s, int t,int[] former){
        Stack<Integer> Vertix = new Stack<Integer>();
        boolean[] visited = new boolean[N]; // record which node has been visited.
        for(int i=0;i<N;i++){
            visited[i] = false;
        }
        visited[s] = true;
        Vertix.push(s);
        former[s] = -1;
        int cur_node;
        int i;
        while(!Vertix.isEmpty()){   // iterate until the stack is empty.
            cur_node = Vertix.peek();
            for(i=0;i<N;i++) {
                if (Res_Graph[cur_node][i] > 0 && !visited[i]) {
                    former[i] = cur_node;
                    Vertix.push(i);
                    visited[i] = true;
                    break;
                }
            }
            if(i == N){  // if did not find any path, pop the top one node in stack.
                Vertix.pop();
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
        print(Res_Graph);
        return MaxFlow;
    }
    /* the function used to print the result */
    public static void print(int[][] Res_Graph){
        int v = 0;
        int u = 0;
        for(int i=101;i<=200;i++){
            if(Res_Graph[201][i]==1) {
                v = i;
                for(int j=0; j<=100;j++){
                    if(Res_Graph[v][j]==1)
                        u = j;
                }
            }
            if(v!=0){
                System.out.print(u +"  ");
                System.out.print(v);
                System.out.println();
            }
            v = 0;
            u = 0;
        }
    }

    /* the readfile function is used to read in the given txt file and build the Graph accordingly
     * here, I use the 2D array to represent the Graph, in which the Graph[i][j] show the capacity from i to j
      * and Graph[j][i] show the capacity from j to i*/
    public static void readfile(int[][] Graph) throws FileNotFoundException{
        Scanner in = new Scanner(new FileReader("hw5test.txt"));
        int u,v;
        while(in.hasNextLine()){
            u = in.nextInt();
            v = in.nextInt();
            Graph[u][v] = 1;
        }
    }

    /* add source and sink node for this specific question*/
    public static void source_and_sink(int[][] Graph){
        for(int i=1; i<=100;i++){
            Graph[0][i] = 1;
        }
        for(int i=101; i<=200;i++){
            Graph[i][201] = 1;
        }
    }

    /* main function */
    public static void main(String[] args) throws FileNotFoundException{
        int[][] Graph = new int[202][202];
        readfile(Graph);
        source_and_sink(Graph);
        int maxflow = Ford(Graph,0,201);
        System.out.println(maxflow);

    }

}
