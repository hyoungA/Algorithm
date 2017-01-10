import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/*
 * ����

�ð� ����
250 ���� Test Input �Է½� C/C++ 10�� / Java 11�� 

�޸� ����
Stack : 1 Mbytes  /  Total : 256 Mbytes

����Ƚ�� ����
10 ȸ

ä��
����� �����ϸ� Test Input�� ���� ����� �����ؼ� �ǽð����� �˷��ָ� 
�� �ǹ̴� ������ ����.
Pass : Test Input�� ���Ͽ� ��� ���� ó���� ���
Fail : Test Input�� ���Ͽ� �Ϻ� Ȥ�� ��� ���� ó���� ���
�� Test Input : ���� �ý��ۿ��� ����� �ڵ� ���� �� �ڵ����� �ԷµǴ� Input Data

�򰡱��� 
���� ó���� Test Input�� ����

1x1 ũ���� ���簢���� ���� N��, ���� N ���� �̷���� ������ ������ �Ʊ��� ��ġ�Ǿ� �ִ�. 
�Ʊ��� �����ϱ� ���ؼ�, �� ������ ������ �ϱ�� �Ͽ���. �� ���簢���� ��� �ְų�, �Ʊ� �Ǵ� ������ �����ϰ� �ִ�. 
������ ������ ���簢�� ������ ���� �� �� ������, ���簢���� ���� ������ ���� �� ������ ��ġ�Ǿ� �ִ� �Ʊ� �Ǵ� ������ ��� �����Ѵ�. 
������ �� �� �ִ� ������ �������� ������ ����. 

������ ������ (������ ������ ��) - (������ �Ʊ��� ��)�� ���ǵȴ�. �������� �Ʊ��� �� ���� �����ϰ� �Ǵ� ��쵵 �����ϴ�. 
����, ������ ������ �ٸ� ��� ������ ���� �� �ֱ� ������ �ּ��� ���� �� �ƴ϶� 
������ ���� K ���� (��, ��� ������ ���� ����� �� ������ ������������ �����Ͽ��� �� K ����) ������ ���ϰ� �� ������ ���ϸ�, 
�ּ��� �� ��ŭ�� ������ �ŵ� �� �ִٴ� ���� �� �� �ִ�.

���� ���, 3 x 3 ũ���� ���� ������ ����Ͽ� ����. �� ���簢���� ������ ��ġ�Ǿ� �ִٸ� 
������ ���� �����, �Ʊ��� ��ġ�Ǿ� �ִٸ� �Ʊ��� ���� ������, �ƹ��� ���ٸ� 0���� ǥ���Ѵ�. 

�� �������� �������� ���� ���� ������ ��� ���ؼ��� ������ ���� ������ �ϸ� 0 + 3 + 5 + 0 = 8�� ������ ���� �� �ִ�. 
�� ��°�� ���� ������ ��� ���ؼ��� ������ ���� ������ �ϸ� 1 + 0 + 3 - 2 + 5 + 0 = 7�� ������ ���� �� �ִ�.
������ ������ �Ʊ��� ��ġ ��Ȳ�� �־����� ��, K��° ���� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�] 
�Է��� ù �ٿ��� �׽�Ʈ ���̽��� ���� �־�����. �� �׽�Ʈ�� ù° �ٿ��� ������ ũ�⸦ ��Ÿ���� ���� N�� ����� ��Ÿ���� ���� K�� �־�����. 
(1 �� N �� 100, 1 �� K �� N) �� ���� N �ٿ��� �ش� �࿡ �ش��ϴ� �Ʊ��� ������ ��ġ�� ���̿� �ϳ��� ������ �ΰ� N���� ������ �־�����. 
�� ĭ�� ���� �� �ִ� ���� -1,000���� 1,000 �����̴�. 

[���] 
�� �׽�Ʈ ���̽��� ���� ������� ǥ��������� ����ϸ�, �� ���̽����� ���� ���ۿ� ��#x���� ����Ͽ��� �Ѵ�. 
�� �� x�� ���̽��� ��ȣ�̴�. ���� �ٿ� K ��° ���� ������ �ڿ����� ����Ѵ�. 

[����� ��]

�Է� 
3                   ��  �� 3���� ���̽�
2 1                ��   ù��° ���̽�
2 2
2 2
3 2                ��   �ι�° ���̽� 
1 0 3 
-2 5 0
0 -1 0
4 3                ��  ����°  ���̽�, ������ ��� 
1 1 1 1 
1 1 1 1 
1 1 1 1 
1 1 1 1

���

#1 8
#2 7
#3 12

Think


Solution
- ���� ������ �����ϸ� �̸� ���� ������ ������ �� �ִ�.
- ������ l �� ������ r �� l �� r   

 */
public class MockTest_20160921 {
	static int T,N,K;
	static int[][] in;
	static int[][] D; // ���� ��� ������ ����
	static Queue<Integer> q; // ����� �̾Ƴ� �켱���� ť
	
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner sc = new Scanner(System.in);
		//System.setIn(new FileInputStream("C:\\sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T ;testCase ++){
			N = sc.nextInt();
			K = sc.nextInt(); // ���
			
			in = new int[N+1][N+1];
			D = new int[N+1][N+1];
			
			q = new PriorityQueue<Integer>(N, new Comparator<Integer>(){
				@Override
				public int compare(Integer arg0, Integer arg1) {
					// TODO Auto-generated method stub
					return arg1 - arg0;
				}
			});
			
			// �Է� �ޱ�
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					in[i][j] = sc.nextInt();
					q.add(in[i][j]);
				}
			}
			
			// ��� ��� �� ���� �迭
			for(int indexMove = 1;indexMove<=N;indexMove++){
				for(int i=indexMove;i<=N;i++){
					for(int j=indexMove;j<=N;j++){
						// �ڱ��ڽ�, �� ���� ��, �� �� �� �� 
						
//						if(indexMove == 1){
//							D[i][j] = D[i-1][j] + D[i][j-1] - D[i-1][j-1] + in[i][j];
//							q.add(D[i][j]);
//						}
//						else {
//							
//							int tmp = Math.max(in[i][j], D[i-1][j] + D[i][j-1] - D[i-1][j-1] + in[i][j]);
//							
//							if(tmp > D[i][j]){
//								D[i][j] = tmp;
//								q.add(tmp);
//							}
//							
//							q.add(in[i][j] + in[i-1][j]);
//							q.add(in[i][j] + in[i][j-1]);
//						}
						
						int tmp = Math.max(in[i][j], D[i-1][j] + D[i][j-1] - D[i-1][j-1] + in[i][j]);
						
						if(tmp > D[i][j]){
							D[i][j] = tmp;
							q.add(tmp);
						}
						
						q.add(in[i][j] + in[i-1][j]);
						q.add(in[i][j] + in[i][j-1]);
					}
				}
				
//				for(int n = 1;n<=N;n++){
//					for(int m = 1;m<=N;m++){
//						System.out.print(D[n][m] + " ");					
//					}
//					System.out.println();
//				}
			}
			
			for(int i=0;i<K;i++){
				int result = q.poll();
				if(i == K-1){
					System.out.println("#" + testCase + " " + result);
					break;
				}
			}
		}
	}
}
