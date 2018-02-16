import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static java.lang.Math.pow;    //only used to compute power

public class hw4_2{
    // the function used to read in the files hw4test.txt.
	public static double[][] readfile() throws FileNotFoundException {
		double[][] vertices= new double[100][2];
        Scanner in = new Scanner(new FileReader("hw4test.txt"));
		int n = 0;
		double temp_x,temp_y;
		while(in.hasNextLine()){
			temp_x= in.nextDouble();
			temp_y = in.nextDouble();
			vertices[n][0] =temp_x;
			vertices[n][1] = temp_y;
			n++;
		}
		return vertices;

	}
	//this function is used to compute the a in linear regression for points from m to n.
	public static double compute_a(int m,int n,double[][] vertices){
		double sum_x=0;
		double sum_y=0;
		double sum_xy=0;
		double sum_x2=0;
		double number = n-m+1;
		for(int i=m-1;i<n;i++){
			sum_x = sum_x + vertices[i][0];
			sum_y = sum_y + vertices[i][1];
			sum_xy = sum_xy + vertices[i][0] * vertices[i][1];
			sum_x2 = sum_x2 + pow(vertices[i][0],2);
		}
		double numerator = number * sum_xy - sum_x * sum_y;
		double denominator = number * sum_x2 - pow(sum_x,2);
		return  numerator/denominator;

	}
    //this function is used to compute the b in linear regression for points from m to n.
	public static double compute_b(int m,int n,double[][] vertices){
		double sum_x=0;
		double sum_y=0;
		double number = n-m+1;
		double a = compute_a(m,n,vertices);
		for(int i=m-1;i<n;i++){
			sum_x = sum_x + vertices[i][0];
			sum_y = sum_y + vertices[i][1];
		}
		double numerator = sum_y - a * sum_x;
		return numerator/number;
	}
	//this function is used to compute the error r for points m to n.
	public static double compute_r(int m,int n,double[][] vertices){
		double error = 0;
		double difference = 0;
		double a = compute_a(m,n,vertices);
		double b = compute_b(m,n,vertices);
		if(m==n) {return 0;}
		for (int i = m-1;i<n;i++){
			difference = vertices[i][1]-a*vertices[i][0]-b;
			error = error + pow(difference,2);
		}
		return error;
	}
	//this function is used to calculate the OPT tables
	public static double OPT(int n,int k,double[][] vertices) {
		double[][] Matrix_Opt = new double[n+1][k];
		double error[][] = new double[n+1][n+1];
		double temp = 0;
		double min = 1000; // a number to assign min to infinite, so we can update it.
		int[][] path = new int[n+1][k]; //used to keep tracking the points to segment.
		int previous = 0;
        int[] trace = new int[k];// a small matrix used to print the segmentation
        int num = k;
        //compute the error matrix Eij for all i and j
		for (int i = 1; i < 100; i++) {
			for (int j = i; j <= 100; j++) {
				error[i][j] = compute_r(i, j, vertices);
			}
		}
		// iteratively compute the OPT matrix
		for (int j = 1; j <= 100; j++) {
			for (int i = 0; i < k; i++) {
				if (j == 1 || j == 2) {    //initialize the value
					Matrix_Opt[j][i] = 0;
				}
				else {
				    // when k=0, it means only one line left, the OPT value is the same with error value.
					if (i == 0) {
						Matrix_Opt[j][i] = error[1][j];
					}
					else {
					    // follow the dynamic rules to update the table
						for (int m = 1; m < j; m++) {
							temp = Matrix_Opt[m-1][i - 1] + error[m][j];
							if (temp < min) {
								min = temp;
								previous = m;
							}
						}
						Matrix_Opt[j][i] = min;
						path[j][i] = previous;
						min=1000;
					}
				}
			}
		}
		//print out the exact segmentation points
        trace[num - 1] = path[n][num - 1];
        while(num>1) {
            System.out.println(trace[num - 1]);
            trace[num - 2] = path[trace[num - 1]][num - 2];
            num--;
        }
		return Matrix_Opt[n][k-1];

	}
	public static void main(String[] args) throws FileNotFoundException {
		double[][] vertices;
		vertices = readfile();
		double temp = OPT(100,4,vertices);
		System.out.println(temp);

	}
}