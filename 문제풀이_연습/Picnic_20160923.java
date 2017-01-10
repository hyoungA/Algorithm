import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 
 * 
시간 제한
메모리 제한
제출 횟수
정답 횟수 (비율)
정답자 수
1.0 초 512 MB 435 93 (21%) 81 

문제
소들은 피크닉을 갈 예정이다! 각 존의 K(1 <= K <= 100)마리의 소들은 N(1 <= N <= 1,000) 개의 목초지중 하나의 목초지에서 풀을 뜯고 있다. 
이 목초지들을 목초지1 … 목초지 N이라고 명명하자. 그 목초지들은 M (1 <= M <= 10,000) 개의 단방향 길로 연결되어 있다. (어떠한 길도 출발지와 도착지가 같지 않다.)
소들은 그들의 피크닉동안 같은 목초지에서 모이기를 원한다. 하지만 몇마리의 소들은 모든 목초지에 도달할 수 없을 가능성이 있다.(단방향 도로이기 때문에) 
소들을 도와 얼마나 많은 목초지에서 모든 소들이 모일 수 있는지 계산해주자.

입력
첫번째 줄은 세개의 정수가 공백으로 구분되어 입력되어진다. :K,N,M ( 소의 수, 목초지 수, 길의 수 )
2번째줄부터 K+1번째 줄까지는 K마리의 소들의 처음 풀을 뜯고있는 위치가 각 줄에 하나씩 주어진다.
K+2번째 줄부터 M+K+1번째 줄까지는 단방향 도로의 정보 시작점S 와 끝점E가 입력으로 주어진다.

출력
모든 소가 모일 수 있는 가능한 목초지의 수를 출력해주자

예제 입력
2 4 4
2
3
1 2
1 4
2 3
3 4

예제 출력
2

힌트
모든 소들은 목초지 3,4에서 모일 수 있다.

Think
- 방향성있는 그래프에서 모든 원소들이 모일 수 있는 목초지의 수 출력
- 1000 * 1000 의 이동 경로를 저장할 배열을 잡아서 이동가능 여부를 저장한다. 
- 소들이 있는 위치에서 ( max 100마리 ) 목축지로 갈 수 있는지 여부를 체크한다. 
  - i 소가 2,3,4 목축지를 갈 수 있다면 각각 +1 씩 해준다.
- 최종 목축지 정보에서 소의 수만큼 카운팅 되어 있는 목축지가 다 모일 수 있는 목축지이다. 

Solution
- 결국은 모든 소가 모여야 하므로 개수를 카운팅한다. 
- DFS 로 갈 수 있는 목초지에 + 1 을 해준다.
- * 인접리스트를 사용해서, 갈수 있는지를 체크한다. 자바로는 ArrayList 에 만든다.
   [0] -> 
   [1] -> 2, 4
   [2] -> 3
   [3] -> 4
   
     대충 이런식?
- 시간복잡도 O(M) : 간선에 대해서 한번만 가므로.. 

 */
public class Picnic_20160923 {
	static int K,N,M;
	static int[] cowPoint;
	static int[][] G = new int[1000][1000];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		
		K = Integer.parseInt(tmp[0]);
		N = Integer.parseInt(tmp[1]);
		M = Integer.parseInt(tmp[2]);
		
		cowPoint = new int[K];
		
		// 소의 위치 저장
		for(int i=0;i<K;i++){
			cowPoint[i] = Integer.parseInt(br.readLine());
		}
		
		// 목축지 이동 경로 저장
		for(int i=0;i<M;i++){
			tmp = br.readLine().split(" ");
//			int start = 
//			int dest 
		}
		
		
	}
}
