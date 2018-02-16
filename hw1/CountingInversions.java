public class CountingInversions {

/**
* @param answer   the numbers of large inversions in the array.
* @param temp     the temporary array to store the sorted array.  
*/
    public static int countInversions(int[] a) {
        int answer;
        int[] temp = new int[a.length];         

         answer =mergeSort(a,temp, 0, a.length);

        return answer;
    }

/** 
* The mergesort function divide the input array recursively
* @param l_ptr    the pointer point to the first item in the array.
* @param h_ptr    the pointer point to the last item in the array.
*/
    private static int mergeSort (int[] a, int[] temp,int l_ptr, int h_ptr) {
      // the base case, no large inversions, return 0
        if (l_ptr == h_ptr - 1) {
          return 0;
        }
        int mid = (l_ptr + h_ptr)/2;

        return mergeSort (a,temp, l_ptr, mid) + mergeSort (a,temp, mid, h_ptr) + merge (a,temp, low, mid, h_ptr);
    }

/**
*  The merge function counts the large inversions between the two sorted array, 
*  in each recursive call. this function mainly foucs on two things:
*  1. merge the two sorted array to one sorted array.
*  2. count the numbers of large inversions
*/
    private static int merge (int[] a,int[] temp, int l_ptr, int mid, int h_ptr) {
      int count = 0;
      
      int lb = l_ptr; 
      int hb = mid;
      int i = l_ptr;

// the typical mergesort function.  

      while (hb < h_ptr && lb < mid){
        if (a[lb] <= a[hb]) 
        {    temp[i++]  = a[lb]; 
             lb++;                   }
       else {
            temp[i++]  = a[hb];
            hb++;
            } 
       }
      while(hb < h_ptr){
        temp[i++] = a[hb++];
      } 
      while(lb < mid){ 
        temp[i++] = a[lb++];
        }

// this part is used to count the numbers of large inversions, the idea is the same as normal 
// inversions counting.

       lb = l_ptr; 
       hb = mid;
      while(lb < mid && hb < h_ptr){
          if(a[lb] > 2 * a[hb]){
            count = count + (mid - lb);
            hb++;
          }
          else{
            lb++;}
      }

// copy the sorted array from temp back to the input array. 
      for (int m = l_ptr; m < h_ptr; m++)
       {
            a[m] = temp[m];
       }
       return count;
    }
}