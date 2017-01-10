import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*
 * 
����

�־����� K���� �Ҽ����� �μ��� ������ ���ڵ� �� 1�� ������ ���ڵ��� '�Ҽ����� ����'�̶�� ��������. 
�־����� �Ҽ��� 2, 3, 5, 7�� ��� �̷�, '�Ҽ����� ����'���� 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 24, 25, 27 ���� �ִ�. 
K���� �Ҽ��� �־����� �� N��° '�Ҽ����� ����'�� �˾Ƴ��� ���α׷��� �ۼ�����.

�Է�
ù ��° �ٿ� �־����� �Ҽ��� ���� K�� �־�����. (1 �� K �� 100)
�� ��° �ٿ� N�� �־�����. (1 �� N �� 100,000)
�� ��° �ٺ��� K���� �ٿ� ���� �Ҽ��� �־�����. (2 �� �Ҽ� �� 1,000,000)

���
ù ��° �ٿ� K���� �Ҽ��� ���� �� �ִ� N��° '�Ҽ����� ����'�� ����Ѵ�. ���� �׻� ��ȣ �ִ� 32-bit ������ �̳��� �ִ�.

�Է� ����
4
23
2
3
5
7

��� ����
35

Solution
- N ���� ���Ҹ� POP �Ҷ� ���� K ���� ���Ҹ� ó���ؾ� �ϹǷ�, NKLogN �� �ð� �ҿ�ǹǷ�, �� ���� ����� �ʿ�.

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
