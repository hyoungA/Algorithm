import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*
 * 
문제

주어지는 K개의 소수만을 인수로 가지는 숫자들 중 1을 제외한 숫자들을 '소수들의 곱셈'이라고 정의하자. 
주어지는 소수가 2, 3, 5, 7인 경우 이런, '소수들의 곱셈'에는 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 24, 25, 27 등이 있다. 
K개의 소수가 주어졌을 때 N번째 '소수들의 곱셈'을 알아내는 프로그램을 작성하자.

입력
첫 번째 줄에 주어지는 소수의 개수 K가 주어진다. (1 ≤ K ≤ 100)
두 번째 줄에 N이 주어진다. (1 ≤ N ≤ 100,000)
세 번째 줄부터 K개의 줄에 걸쳐 소수가 주어진다. (2 ≤ 소수 ≤ 1,000,000)

출력
첫 번째 줄에 K개의 소수로 만들 수 있는 N번째 '소수들의 곱셈'을 출력한다. 답은 항상 부호 있는 32-bit 정수형 이내에 있다.

입력 예제
4
23
2
3
5
7

출력 예제
35

Solution
- N 개의 원소를 POP 할때 보다 K 개원 원소를 처리해야 하므로, NKLogN 의 시간 소요되므로, 더 빠른 방법이 필요.

 */
public class PrimeNumMult_20160922 {
	static int K, N;
	static long primeNumbers[][];
	static Queue<Long> q;
	static long[] multNumber;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		primeNumbers = new long[K][3];
		multNumber = new long[N+1];
		q = new PriorityQueue<Long>(N);
		
		for(int i= 0; i<K;i++){
			primeNumbers[i][0] = Integer.parseInt(br.readLine());
			q.offer(primeNumbers[i][0]);
			primeNumbers[i][1] = 0;
			primeNumbers[i][2] = primeNumbers[i][0];
		}
		
		int count = 0;
		long saved = -1;
		multNumber[0] = 1;
		
		// O(N * K) = 100000 * 100 = 10,000,000
		while(count < N){
			if(q.peek() == saved){
				q.poll();
				
				int minimumIndex = 0;
				long minimumValue = Integer.MAX_VALUE;
				
				for(int j =0 ;j < K;j++){
					if(primeNumbers[j][0] * multNumber[(int)primeNumbers[j][1]] < minimumValue){
						minimumValue = primeNumbers[j][0] * multNumber[(int)primeNumbers[j][1]];
						minimumIndex = j;
					}
				}
				
				primeNumbers[minimumIndex][1]++;
				primeNumbers[minimumIndex][2] = primeNumbers[minimumIndex][0] * multNumber[(int)primeNumbers[minimumIndex][1]] ;
				
				if(primeNumbers[minimumIndex][2] < Integer.MAX_VALUE ){
					q.offer(primeNumbers[minimumIndex][2]);
				}
				
				continue;
			}
			
			else {
				long tmp = q.poll();

				count++;
				saved = tmp;
				multNumber[count] = saved;
				
				int minimumIndex = 0;
				long minimumValue = Integer.MAX_VALUE;
				
				for(int j =0 ;j < K;j++){
					if(primeNumbers[j][0] * multNumber[(int)primeNumbers[j][1]]  < minimumValue){
						minimumValue = primeNumbers[j][0] * multNumber[(int)primeNumbers[j][1]];
						minimumIndex = j;
					}
				}
				
				primeNumbers[minimumIndex][1] ++;
				primeNumbers[minimumIndex][2] = primeNumbers[minimumIndex][0] * multNumber[(int)primeNumbers[minimumIndex][1]] ;
				
				if(primeNumbers[minimumIndex][2] < Integer.MAX_VALUE ){
					q.offer(primeNumbers[minimumIndex][2]);
				}
			}
		}
		System.out.println(saved);
	}
}
