import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aaron Gu on 2016/12/4.
 */
public class approx {
    ArrayList<ArrayList<Integer>> set;
    approx(){
        set = new ArrayList<ArrayList<Integer>>();

    }
    public boolean[] run(int data[][],int m,int[] B){
        int t = 0;
        int num = data[0].length;
        boolean result[] = new boolean[m];
        for (int i= 0; i < m; i++){
            t = 0;
            ArrayList<Integer> temp = new ArrayList<Integer>();
            Arrays.sort(data[i]);
            for(int j =num - 1; j>0;j--){
                if (t + data[i][j] <= B[i]){
                    t =t + data[i][j];
                    temp.add(data[i][j]);
                }
            }
            result[i] = (t==B[i]);
            set.add(temp);
        }
        return result;
    }
}
