import java.util.Scanner;

/*
 * 
문제 : 막대기 ( http://koitp.org/problem/STICK/read/ )

N가지 종류의 서로 다른 길이의 막대기들이 있다. 이 막대기들을 적당히 사용해서, 총 길이가 K가 되도록 하고 싶다.
그 경우의 수를 구하시오. (각각의 막대기는 여러 번 사용할 수 있다.)

입력
첫 번째 줄에 N, K가 공백으로 분리되어 주어진다. (1 ≤ N ≤ 100, 1 ≤ K ≤ 10,000) 
두 번째 줄부터 N개의 줄에 걸쳐 각 막대기의 길이가 주어진다. 각 막대기의 길이는 1 이상 1,000 이하다.


출력
첫 번째 줄에 막대기를 이용해 총 길이 K를 만들 수 있는 경우의 수를 1,000,000으로 나눈 나머지를 출력한다.

예제 입력
3 10
1
2
5


예제 출력
10

Solution
- D[i][j] = 첫번째부터 i번째까지 고려하여 j 를 만드는 가짓수
            D[i-1][j-Pi]
- Pi 라는 수를 한번도 안쓰는 경우와 1번이상 쓰는 경우로 나눈다.  
  - 안쓴 경우        D[i-1][j] 
  - 1번이상 쓴 경우 D[i][j-Pi]
- 초기값
  D[y][0] = 1 ( 참조하는 값이므로 채워준다 ), D[0][x] = 0

Think
- 

Tip
- 정렬하나 정렬하지 않으나 동일한 결과가 나온다.

 */
public class Stick_20160920 {
	static int N,K;
	static int[][] D;
	static int[] P;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		// 초기화
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
