/**
 * Created by Aaron Gu on 2016/12/4.
 */
public class compare {
    public static void main(String[] args) {

        int m = 100;
        int n = 100;
        int w = 10000;
        int j = 0;
        datasets da = new datasets();
        int[][] data = da.generate(m, n, w);
        int[] B;
        B = calculator(data);
        backpack bp = new backpack();
        approx ap = new approx();
        long lStartTime = System.nanoTime();
        boolean[] bp_result = bp.run(data, m, B);
        long lEndTime = System.nanoTime();
        long output = lEndTime - lStartTime;
        System.out.println("Elapsed time in milliseconds: " + (double) output / (1000000 * m));

        long lStartTime_approx = System.nanoTime();
        boolean[] ap_result = ap.run(data, m, B);
        long lEndTime_approx = System.nanoTime();
        long output_approx = lEndTime_approx - lStartTime_approx;
        System.out.println("Elapsed time in milliseconds: " + (double)output_approx / (1000000*m));
        for (int i = 0; i < m; i++) {
            if(bp_result[i] != ap_result[i]){
                j++;
            }
        }
        System.out.println((double)j/m);
//        for (int i = 0; i < m; i++) {
//            System.out.print(bp_result[i] + " ");
//            System.out.print(ap_result[i]);
//            System.out.println();
//        }
    }

   public static int[] calculator(int[][] data){
        int m = data.length;
        int n = data[0].length;
        int sum = 0;
        int[] result = new int[m];
       for(int i=0;i<m;i++){
           sum = 0;
           for(int j=0;j<n;j++){
               sum = sum + data[i][j];
           }
           result[i] = sum/2;
       }
       return result;
   }

}
