import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 
문제

N개의 수가 주어진다. 이 수들을 오름차순 정렬했을 때, K번째에 위치하는 수를 알아내자.

입력
첫 번째 줄에 N, K가 공백으로 분리되어 주어진다. (1 ≤ K ≤ N ≤ 5,000,000)
두 번째 줄부터 N개의 줄에 걸쳐 N개의 정수가 주어진다. 각 정수는 -1,000,000,000 이상 1,000,000,000 이하이다.

출력
주어진 N개의 정수를 오름차순 정렬했을 때, K번째에 위치하는 수를 출력한다.

예제 입력
2 1
1 
2

예제 출력
1

Think
- 하나의 수를 잡고, 작거나 같은 수는 left 배열에 넣고, 큰수는 그대로 둔다.
- 수를 수보다 작거나 같은 작은 집합에 넣는다.
- 수보다 작은 배열이 K 보다 작으면 K 를 재정의 한다. K = K - (작은 집합의 배열 크기)
- 수보다 작은 배열이 K 보다 크면 작은 집합에 대하여 위 과정을 반복한다. 

Solution
- 기본적으로 퀵정렬을 구현하여 푼다.

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
		
		N = Integer.parseInt(tmp[0]); // 숫자 개수
		K = Integer.parseInt(tmp[1]); // K 번째 수
		
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
				// lCount == K : l 집합 중 최대 값이 답
				numbers = l;
				hi = mid - 1;
				numbersSize = lCount;
			}
		}
		
		return numbers[K-1];
	}
}
