import java.util.Scanner;

/*
 * 
���� : ����� ( http://koitp.org/problem/STICK/read/ )

N���� ������ ���� �ٸ� ������ �������� �ִ�. �� �������� ������ ����ؼ�, �� ���̰� K�� �ǵ��� �ϰ� �ʹ�.
�� ����� ���� ���Ͻÿ�. (������ ������ ���� �� ����� �� �ִ�.)

�Է�
ù ��° �ٿ� N, K�� �������� �и��Ǿ� �־�����. (1 �� N �� 100, 1 �� K �� 10,000) 
�� ��° �ٺ��� N���� �ٿ� ���� �� ������� ���̰� �־�����. �� ������� ���̴� 1 �̻� 1,000 ���ϴ�.


���
ù ��° �ٿ� ����⸦ �̿��� �� ���� K�� ���� �� �ִ� ����� ���� 1,000,000���� ���� �������� ����Ѵ�.

���� �Է�
3 10
1
2
5


���� ���
10

Solution
- D[i][j] = ù��°���� i��°���� ����Ͽ� j �� ����� ������
            D[i-1][j-Pi]
- Pi ��� ���� �ѹ��� �Ⱦ��� ���� 1���̻� ���� ���� ������.  
  - �Ⱦ� ���        D[i-1][j] 
  - 1���̻� �� ��� D[i][j-Pi]
- �ʱⰪ
  D[y][0] = 1 ( �����ϴ� ���̹Ƿ� ä���ش� ), D[0][x] = 0

Think
- 

Tip
- �����ϳ� �������� ������ ������ ����� ���´�.

 */
public class Stick_20160920 {
	static int N,K;
	static int[][] D;
	static int[] P;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		// �ʱ�ȭ
		P = new int[101];
		D = new int[N+1][K+1];
		
		for(int i = 1; i<=N;i++){
			P[i] = sc.nextInt();
		}
		
//		Arrays.sort(P);
		D[0][0] = 1;
		
		for(int i= 1;i<=N; i++){
			for(int j= 0;j<=K; j++){
				if(j == 0){
					D[i][j] = 1;
				}
				else {
					if(j - P[i] < 0){
						D[i][j] = D[i-1][j]  % 1000000;
					}
					else {
						D[i][j] = (D[i-1][j] + D[i][j-P[i]]) % 1000000;
					}
				}
			}
		}
		System.out.println(D[N][K]);
	}
}
