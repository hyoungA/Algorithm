import java.util.Scanner;

/*
 * 
문제 : 롤러코스터

소들은 롤러코스터를 짓고있다! 소들은 자신들이 가지고 있는 돈을 활용해서 최대한 재밌는 롤러코스터를 만들고 싶어한다.
롤러코스터는 직선형으로, 길이가 L이다. 소들이 롤러코스터를 지을 때, 롤러코스터는 N개의 교체 가능한 부품들중 일부로 구성되어야 한다.
각 부품 i는 고정된 길이 Wi를 가지고 있다. 그리고 다양한 지형의 굴곡 때문에, i번째 부품은 오직 Xi의 위치를 시작점으로만 놓을 수 있다.
소들은 다양한 롤러코스터를 0부터 L까지 빈틈없이 채우고 싶어한다. 만약 중간에 빈칸이 있으면 롤러코스터를 구성할 수 없다. 
또한, 각 부품끼리 겹쳐서 롤러코스터를 건설하는 경우도 없다.
각 i번째 부품은 "재미도" Fi과 짓는데 드는 비용 Ci가 있다. 총 비용은 롤러코스터를 구성하는 부품을의 비용의 합으로 계산된다. 
마찬가지로 총 재미도의 합은 롤러코스터를 구성하는 부품들의 재미도의 합으로 계산된다.
소들의 총 예산은 B이다. 소들을 도와 예산내에서 가장 큰 재미도를 가진 롤러코스터를 지을 수 있도록 도와주자!

입력

첫 번재 줄에 세개의 정수 L, N, B가 공백으로 분리되어 주어진다. (1 ≤ L ≤ 1,000, 1 ≤ N ≤ 10,000, 1 ≤ B ≤ 1,000)
두 번째 줄부터 N개의 줄에 걸쳐 각 부품들의 Xi, Wi, Fi, Ci가 공백으로 분리되어 주어진다. (0 ≤ Xi ≤ L-Wi, 1 ≤ Wi ≤ L, 1 ≤ Fi ≤ 1,000,000, 1 ≤ Ci ≤ 1,000)

출력
첫 번째 줄에 소들이 예산안에 각 부품들을 가지고 지을 수 있는 롤러코스터의 최대 재미도의 합을 출력한다. 만약, 롤러코스터를 짓는 방법이 없다면 -1을 출력한다.

힌트

예제 입력
5 6 10
0 2 20 6
2 3 5 6
0 1 2 1
1 1 1 3
1 2 5 4
3 2 10 2

예제 출력
17

예제 설명
3번, 5번, 6번 부품들을 이용하면 롤러코스터는 비용 7을 이용해서 재미도 17의 롤러코스터를 만들 수 있다. 반면, 1번, 2번 부품들을 이용하면 재미도는 25이지만 비용이 12가 되어 예산(10)을 초과하게 된다.

Solution
- 연결리스트 활용
- D[i][j] = i칸까지 j원을 이용해 만들 수 있는 최대 재미

- 초기값
  - D[0][0] = 0
  - D[0][나머지] = -1
  - L[i][j] = i 를 끝점으로 하는 j 에서 시작하는 위치에 해당하는 부품의 인덱스
  - L[ xi + wi ] = i
  - L[i] 의 원소의 수 = S[i]
- 점화식
  D[i][j] = D[i-W[L[i][k]]][j-C[L[i][k]] + F[L[i][k]]

 */

public class Rollercoaster_20160919 {
	static int L, N, B; // 길이, 부품수, 예산
	static int[][] in = new int[10001][5];
	static int[][] D = new int[1001][1001];
	static int[][] length = new int[1001][1001];
	static int[] S = new int[1001];

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();
		N = sc.nextInt();
		B = sc.nextInt();
		
		// 부품 입력
		for(int i=1;i<=N;i++){
			in[i][0] = sc.nextInt(); // 위치
			in[i][1] = sc.nextInt(); // 길이
			in[i][2] = sc.nextInt(); // 재미
			in[i][3] = sc.nextInt(); // 비용
			
			// length[끝위치][시작위치] = input 인덱스
			length[in[i][0] + in[i][1]][in[i][0]] = i;
		}
		
		// 초기화 
		for(int i = 0 ; i<=L ;i++){
			for(int j = 0 ; j<=L ;j++){
				D[i][j] = -1;
			}
		}
		
		// input 체크
//		System.out.println(in);
		
		// 길이, 비용 기준 dp
		D[0][0] = 0;
		for(int i = 0; i<= L ;i++){
			for(int j = 1 ; j<=B ;j++){
				// 비용 j 로 i 까지 놓아서 얻는 최대 재미
				
				// 길이가 0 이면 재미없다.
				if(i == 0){
					D[i][j] = 0;
					continue;
				}
				
				// 길이가 0 이상이면, K = 시작점, i 는 끝 점
				for(int k = 0 ; k< i ; k++){
					int tmpMaxFun = Integer.MIN_VALUE;
					
					// 놓을 수 있는 부품이 있는 경우
					// in[length[i][k][2] = 놓을 부품의 cost
					// cost 는 이전 cost 와 현재 부품의 cost 가 j 여야만 놓을 수 있다.
					// 이전 부품의 코스트??
					if(length[i][k] > 0){
						// 최소 부품의 경우에는 현재 비용만 고려한다.
						if(k == 0 && in[length[i][k]][3] == j){
							tmpMaxFun = Math.max(tmpMaxFun, in[length[i][k]][2]);
							D[i][j] = tmpMaxFun;
						}
						else {
							// 중각 객차의 경우에는 이전 객차부터 이어줘야 한다.
							if(j-in[length[i][k]][3] >= 0 && D[i-in[length[i][k]][1]][j-in[length[i][k]][3]] != -1){
								tmpMaxFun = Math.max(tmpMaxFun, in[length[i][k]][2]) + D[i - in[length[i][k]][1]][j - in[length[i][k]][3]];
								D[i][j] = tmpMaxFun;
							}
						}
					}
				}
			}
		}
		
		int res = Integer.MIN_VALUE;
		for(int i=0;i<B;i++){
			if(D[L][i] > res){
				res = D[L][i];
			}
		}
		
		if(res < 0){
			System.out.println(-1);
		}
		else{
			System.out.println(res);			
		}
	}
}
