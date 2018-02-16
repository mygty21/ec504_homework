/**
 * Created by Aaron Gu on 2016/12/4.
 */
public class backpack {
    public boolean[] run(int data[][],int m,int[] B) {
        int n = data[0].length + 1;
        boolean[] result = new boolean[m];
    for(int i= 0; i < m;i ++){
        int w = B[i] + 1;
        boolean[][] W = new boolean[n][w];
        for(int it = 0; it < n;it++){
            W[it][0] = true;
        }
        for (int iw = 1; iw < w;iw++){
            W[0][iw] = false;
        }
        for(int num = 1; num < n;num++){
            for(int weight = 1;weight<w;weight++){
                if(weight - data[i][num -1] < 0){
                    W[num][weight] = W[num - 1][weight];
                }else {
                    W[num][weight] = (W[num - 1][weight] || W[num - 1][weight - data[i][num - 1]]);
                }
            }
        }
        result[i] = W[n-1][w-1];
    }
    return result;
    }
}
