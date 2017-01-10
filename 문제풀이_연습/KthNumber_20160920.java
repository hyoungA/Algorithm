import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 
����

N���� ���� �־�����. �� ������ �������� �������� ��, K��°�� ��ġ�ϴ� ���� �˾Ƴ���.

�Է�
ù ��° �ٿ� N, K�� �������� �и��Ǿ� �־�����. (1 �� K �� N �� 5,000,000)
�� ��° �ٺ��� N���� �ٿ� ���� N���� ������ �־�����. �� ������ -1,000,000,000 �̻� 1,000,000,000 �����̴�.

���
�־��� N���� ������ �������� �������� ��, K��°�� ��ġ�ϴ� ���� ����Ѵ�.

���� �Է�
2 1
1 
2

���� ���
1

Think
- �ϳ��� ���� ���, �۰ų� ���� ���� left �迭�� �ְ�, ū���� �״�� �д�.
- ���� ������ �۰ų� ���� ���� ���տ� �ִ´�.
- ������ ���� �迭�� K ���� ������ K �� ������ �Ѵ�. K = K - (���� ������ �迭 ũ��)
- ������ ���� �迭�� K ���� ũ�� ���� ���տ� ���Ͽ� �� ������ �ݺ��Ѵ�. 

Solution
- �⺻������ �������� �����Ͽ� Ǭ��.

 */
public class KthNumber_20160920 {
	static int N,K;
	static int[] numbers;
	static int[] left;
	static int[] right;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String[] tmp = line.split(" ");
		numbers = new int[5000000];
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		N = Integer.parseInt(tmp[0]); // ���� ����
		K = Integer.parseInt(tmp[1]); // K ��° ��
		
		for(int i =0;i<N;i++){
			numbers[i] = Integer.parseInt(br.readLine());
			min = Math.min(min, numbers[i]);
			max = Math.max(max, numbers[i]);
		}
		
		System.out.println(search(min, max, K));
	}
	 
	// 
	static int search(int min, int max, int target){
		int lo = min - 1, hi = max;
		int mid = (lo + hi) /2;
//		List<Integer> l = new ArrayList<Integer>();
//		List<Integer> r = new ArrayList<Integer>();
		
		int[] l = new int[5000000];
		int[] r = new int[5000000];
		int numbersSize = N;
		
		while(lo <= hi){
			int lCount = 0;
			int rCount = 0;
			mid = (lo + hi) / 2;
			
			for(int i=0;i<numbersSize;i++){
				if(numbers[i] <= mid){
					l[lCount++] = numbers[i];
				}
				else{
					r[rCount++] = numbers[i];
				}
			}
			
			if(lCount < K){
				K = K - lCount;
				numbers = r;
				lo = mid + 1;
				numbersSize = rCount;
			}
			else {
				// lCount == K : l ���� �� �ִ� ���� ��
				numbers = l;
				hi = mid - 1;
				numbersSize = lCount;
			}
		}
		
		return numbers[K-1];
	}
}
