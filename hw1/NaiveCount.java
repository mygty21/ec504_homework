public class NaiveCount{
	public int count(int[] a){
		int count = 0;
		for(int i = 0; i < a.length; i++){
			for(int j = i; j < a.length; j++){
				if(a[i] > 2 * a[j]){
					count++;
				}
			}
		}
		return count;
	}
}