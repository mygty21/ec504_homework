public class question_2
{
	static int[] A1 = {1,2,3,4,5};
	static int[] A2 = {6,7,8,9,10};
public static void main(String[] args)
{
	int length = A1.length;
	int medium = find_medium(1,length,1,length);
	System.out.println(medium);
} 
public static int queries(int[] array, int index)
{
	return array[index-1];
}
public static int find_medium(int head1, int tail1,int head2,int tail2)
{
	int m_index1;
	int m_index2;
	m_index1 = head1 + (tail1 - head1)/2;
	m_index2 = head2 + (tail2 - head2)/2;
	int m_value1 = queries(A1,m_index1);
	int m_value2 = queries(A2,m_index2);
	if (m_value1 > m_value2){
		if ((head1 == tail1) && (head2 == tail2))
			return  m_value2;
		else{
			if ((tail2 + head2) % 2 == 0)
				return find_medium(head1,m_index1,m_index2,tail2);
			else
				return find_medium(head1,m_index1,m_index2+1,tail2);
		}
	}
	else{
		if ((head1 == tail1) && (head2 == tail2))
			return  m_value1;
		else{
			if ((tail2 + head2) % 2 == 0)
				return find_medium(m_index1,tail1,head2,m_index2);
			else
				return find_medium(m_index1+1,tail1,head2,m_index2);
		}		
	}
	}
}


