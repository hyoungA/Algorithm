import java.util.Arrays;
import java.util.Scanner;

/*
 * 
���� : ����

���� A�� B�� ���� N, M���� �ڿ����� ����ִ�. ���� ������ �ൿ�� min(N, M)�� ������ ���̴�. 
- ���� A, B���� ���� �ڿ��� �ϳ��� ����, �� ���� �� ���տ��� �����Ѵ�
- �� �� ���� ���� �׷� C�� �ִ´�.

�츮�� ��ǥ�� �׷� C�� �ִ� ������ ���� �ּҷ� �ϴ� ���̴�.

�Է�
ù ��° �ٿ� N, M�� �������� �и��Ǿ� �־�����. (1 �� N, M �� 1,000)
�� ��° �ٿ� ���� A�� ������ N���� �ڿ����� �������� �и��Ǿ� �־�����.
�� ��° �ٿ� ���� B�� ������ M���� �ڿ����� �������� �и��Ǿ� �־�����.
��� ������ ���Ҵ� 1 �̻� 1,000,000 �����̴�.

���
ù ��° �ٿ� �׷� C�� �������� �ּҰ��� ����Ѵ�.

��Ʈ

���� �Է�
2 1
10 20
30

���� ���
10

Think
- min (N,M) �� �� ������ ���� �� ���� �� ��ŭ �����Ѵٴ� �ǹ�
- ���� �ּҰ� �Ƿ��� �� ������ �����ؼ�, ������ �۰� ���� ���� �̾Ƽ� C �� ��ƾ� �Ѵ�.
- �Ʒ� ���̽��� ���� �ذ��� �ȵ�

2 5
100 3
5 4 3 2 1

Solution
- ������ ���� �������� �ʵ��� ���� ���� ���ϴ� �� ���� �ϳ��̴�.
- ���̳������� ���Ѵ�.
- D[i][j] A �迭���� i��° ������ ������ ��Ī��Ű��, B �迭���� j��° ������ ������ ��Ī ������ ��, ���� ���� �ּ� ��
- A �迭�� B �迭�� ���ؼ� ���� i,j �ε����� �̵��Ѵ� ġ�� 
  - i,j �� ��Ī   : | Ai - Bj | + D[i-1][j-1]
  - i,j �� ��ĪX : 
- ��ȭ��
  - 

 * 
 */
public class Set_20160920 {
	static int N,M;
	static int[] setA;
	static int[] setB;
	static int[][] D;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		int minNM = Integer.MAX_VALUE;
		boolean smallerN = false;
		
		if(N < M){
			minNM = N;
			smallerN = true;
		}
		else {
			minNM = M;
		}
		
		setA = new int[N+1];
		setB = new int[M+1];
		
		for(int i=1;i<=N;i++){
			setA[i] = sc.nextInt();
		}
		
		for(int i=1;i<=M;i++){
			setB[i] = sc.nextInt();
		}
		
		Arrays.sort(setA);
		Arrays.sort(setB);
		
		int result = 0;
		
		// A �� ���� ����, B �� ū ����
		int chkA[], chkB[];
		
		if(N<=M){
			chkA = setA.clone();
			chkB = setB.clone();
		}
		else {
			chkA = setB.clone();
			chkB = setA.clone();
		}
		
		D = new int[chkA.length][chkB.length];
		
		for(int i = 1; i<chkA.length; i++){
			for(int j =0;j<chkB.length;j++){
				if(j == 0){
					D[i][j] = chkA[i]; 
				}
				else {
					D[i][j] = Math.min(D[i-1][j-1] + Math.abs(chkA[i] - chkB[j]), D[i][j-1]);					
				}
			}
		}
		
		System.out.println(D[chkA.length-1][chkB.length-1]);
	}
}
