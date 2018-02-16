	// this is the typical disjoint_set class, which is the same as the textbook shows
	// make one disjoint set cost O(1)
	// find_set and union cost O(α(n)) 
	public class Disjoint_Set{
		private int[] parent;
		private int[] rank;
		public Disjoint_Set(int n){
			parent = new int[n];
			rank = new int[n];
			for (int i=0; i<n;i++){
				parent[i] = i;				
				rank[i] = 0;
			}
		}

		public int find_set(int x){
			if (parent[x]!= x){
				parent[x] = find_set(parent[x]); 
			}
			return parent[x];
		}

		public void union(int i, int j){
			i = find_set(i);
			j = find_set(j);
			if (rank[i] > rank[j])
				parent[j] = i;
			else{
				parent[i] = j;
				if(rank[i]==rank[j])
					rank[j]++;
			}

		}

	}
