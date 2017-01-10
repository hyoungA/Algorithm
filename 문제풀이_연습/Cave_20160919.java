import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
 * 
문제 : 동굴

벌레 한 마리가 동굴을 지나려고 한다. 
모두가 알다시피 동굴은 석순과 종유석이 굉장히 많은 공간이다. 
이 벌레는 이렇게 장애물이 많은 동굴을 지나갈 것이다. 
동굴의 길이는 N미터이고, 높이는 H미터이다. 
N은 항상 짝수이고, 장애물은 석순과 종유석이 번갈아 가면서 등장하고, 첫 장애물을 항상 석순이다.

아래 예제는 N=14, H =5의 예이다. 

남자다운 이 벌레는 장애물을 굳이 피하지 않는다. 즉, 처음 정한 높이에서 일직선으로 장애물을 부수면서 지나간다. 
벌레가 아래에서 4번째 구간으로 지나가면, 아래 그림과 같이 8개의 장애물을 부수게 된다.

하지만, 첫 번째나 다섯 번째 구간으로 날아간다면 벌레는 7개의 장애물만 부시면 된다. 

벌레는 아픔을 느끼는 남자이기 때문에, 최소한의 장애물만 부시고 싶어한다. 여러분은 이 벌레가 최소 몇 개의 장애물만 부수고도 통과할 수 있는지 구하고, 그러한 구간이 총 몇 개 있는지 구하는 프로그램을 작성하시오.


입력
첫 번째 줄에 N과 H가 주어진다. N은 항상 짝수이다. (2 ≤ N ≤ 200,000, 2 ≤ H ≤ 500,000)
두 번째 줄부터 N개의 줄에 걸쳐 장애물의 크기가 순서대로 주어진다. 장애물의 크기는 H보다 작은 양수이다.

출력
첫 번째 줄에 개똥벌레가 파괴해야 하는 장애물의 최소값과 그러한 구간의 수를 공백으로 구분하여 출력하시오.

예제 입력
14 5
1
3
4
2
2
4
3
4
3
3
3
2
3
3


예제 출력
7 2

Solution
- 다 보내본다. O (N * H)
- 정렬해서 ( O (nlogn) 1 부터 h 까지 돌리면서 석순과 석주의 합의 최소값을 찾는다. O(n+H)  
1 2 3 4 5 6 7 8 9 10
---------------------
1 2 3 5 6 6 6 6 6 7

// S 는 석순이 정렬 된 상태
// SB [i] 는 높이 i 에 벌레를 쐈을 때 석순이 부딛치는 개수
// Sn 은 석순의 개수
S 배열
S 부딛치는 배열
now = 0
int Sn; 

for(i=1 ~ H)
  while(now<Sn && S[now]<=i) now++;
  SB[i] = now;
  
종유석도 똑같이 처리

 */
public class Cave_20160919 {
	static int N, H; // 석순, 석주의 개수, 높이
	static int[] S;
	static int[] J;
	static int[] sCrash;
	static int[] jCrash;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String[] tmp = line.split(" ");
		
		N = Integer.parseInt(tmp[0]);
		H = Integer.parseInt(tmp[1]);
		
		S = new int[N/2]; // N 은 반드시 짝수므로.
		J = new int[N/2];
		sCrash = new int[H+1];
		jCrash = new int[H+1];
		
		
		int sCount = 0;
		int jCount = 0;
		
		// i%2 가 0 이면 석순
		// i%2 가 1 이면 석주
		for(int i=0;i<N;i++){
			if(i%2==0){
				S[sCount++] = Integer.parseInt(br.readLine());
			}
			else
				J[jCount++] = Integer.parseInt(br.readLine());
		}
		
		// 정렬
		Arrays.sort(S);
		Arrays.sort(J);
		
		int minimumCrashCount = 0, crashTimes = 0;
		
		// h = 1 ~ H
		// 초기값 석순의 개수 sCrash[0] = sCount
		// 새로 부딛히는 석주의 개수(인덱스를 뒤에서부터 탐색한다) - h 에 해당하는 석순의 개수
		
		
		
		
		System.out.println(minimumCrashCount + " " + crashTimes);
	}
}
