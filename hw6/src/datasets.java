import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Aaron Gu on 2016/12/4.
 */
public class datasets {
    public int[][] generate(int m,int n, int w){
        int data[][] = new int[m][n];
        for(int i=0;i < m;i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = ThreadLocalRandom.current().nextInt(1, w + 1);
            }
        }
        return data;
    }

}
