// @para root: integer array used to store the representative of each set. take set number(1~m)
//			   as input, and return the correspoding representative.
// @para pos:  integer array used to store the set number. it takes the representative 
//			   as input and return the current set number of this root. 
// @para extracted: the array used to store the extracted number.


public class offline{

	public static void main(String[] args){
		int n = 100; 
		int m = 50;
		int[] root = new int[m+2];
		int[] pos = new int[n+1];
		Disjoint_Set ds = new Disjoint_Set(n+1);
	// read in the file. nead In.class	
		In in = new In("hw3test.txt");
		String current = in.readLine();
		int re = 0; // re is the representative 
		int count = 0; // count the number of extract
	// this while loop read in all the number and E, then 
	// it initialize the disjoint set, and set the representative
	// of each set to the first incoming number of each set.
	// this will take the O(n) running time.
		 while(current!=null){
                if(current.charAt(0)=='E'){
                    count++;
                    root[count]=re;
                    pos[re]=count;
                    re=0;
                }
                else{
                    int temp=Integer.parseInt(current);
                    if(re==0) 
                    	re=temp;
                    ds.union(re,temp); // in initialization, this will take constant time, 
                }					   // since re and temp are all reprsentative of each set.
                current=in.readLine();
            }
            count++;
            root[count]=re;
            pos[re]=count;
            re=0;
// the algorithm described in the textbook. the algorithm takes O(3n * α(n)) running time.

		int[] extracted = new int[m];
        for(int i=1;i<=n;i++){
            int r=ds.find_set(i); //O(n*α(n))
            int j=pos[r];
        // set extract and get the next non-empty set    
            if(j<m+1){
                extracted[j-1]=i;
                int k=j+1;
                while(k<=m){
                    if(root[k]!=-1) 
                    	break;
                    k++;
                }
        // union two sets and update the pos and representative accrodingly.
                if(root[k]!=0){
                    ds.union(root[k],r);  //O(n*α(n))
                    int updated_root=ds.find_set(r); //O(n*α(n))
                    root[k] = updated_root;
                    pos[updated_root]=k;
                    root[j]=-1;
                }
                else{
                    root[k]=r;
                    pos[r]=k;
                    root[j]=-1;
                }
            }
        }

		for (int num=0;num<m;num++)
			System.out.println(extracted[num]);	

	}


}
