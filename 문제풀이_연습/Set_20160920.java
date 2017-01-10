import java.util.Arrays;
import java.util.Scanner;

/*
 * 
문제 : 집합

집합 A와 B에 각각 N, M개의 자연수가 들어있다. 이제 다음의 행동을 min(N, M)번 시행할 것이다. 
- 집합 A, B에서 각각 자연수 하나씩 고르고, 고른 수는 각 집합에서 삭제한다
- 고른 두 수의 차를 그룹 C에 넣는다.

우리의 목표는 그룹 C에 있는 원소의 합을 최소로 하는 것이다.

입력
첫 번째 줄에 N, M이 공백으로 분리되어 주어진다. (1 ≤ N, M ≤ 1,000)
두 번째 줄에 집합 A의 원소인 N개의 자연수가 공백으로 분리되어 주어진다.
세 번째 줄에 집합 B의 원소인 M개의 자연수가 공백으로 분리되어 주어진다.
모든 집합의 원소는 1 이상 1,000,000 이하이다.

출력
첫 번째 줄에 그룹 C의 원소합의 최소값을 출력한다.

힌트

예제 입력
2 1
10 20
30

예제 출력
10

Think
- min (N,M) 은 각 원소의 개수 중 작은 수 만큼 시행한다는 의미
- 합이 최소가 되려면 두 집합을 정렬해서, 격차가 작게 나는 값을 뽑아서 C 에 담아야 한다.
- 아래 케이스의 문제 해결이 안됨

2 5
100 3
5 4 3 2 1

Solution
- 최적의 답은 교차하지 않도록 차를 구해 더하는 것 중의 하나이다.
- 다이나믹으로 구한다.
- D[i][j] A 배열에서 i번째 수까지 선분을 매칭시키고, B 배열에서 j번째 수까지 선분을 매칭 시켰을 때, 차이 합의 최소 값
- A 배열과 B 배열에 대해서 각각 i,j 인덱스로 이동한다 치면 
  - i,j 를 매칭   : | Ai - Bj | + D[i-1][j-1]
  - i,j 를 매칭X : 
- 점화식
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
		
		// A 가 작은 집합, B 가 큰 집합
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
