import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 
문제

그의 많은 농장들을 탐험하던중, 존은 몇 개의 놀라운 웜홀을 발견했다. 
웜홀은 현재 농장에서 다른 농장으로의 이상한 단방향 통로로써 당신이 들어갔던 시간보다 이전시간으로 시간을 되돌린다. 
존의 농장은 N개의 농장과 M개의 양방향 도로, W개의 웜홀로 구성되어있다. 그리고 각 농장은 편의상 농장1, 농장2, … ,농장 N이라고 이름붙여주자.
존은 갑자기 현재위치에서 출발해서 여행을 하다 다시 현재위치로 돌아왔을 때 시간이 되돌아 가 있는 경우가 있는지 궁금해졌다. 
존을 도와 시간을 되돌리는 여행이 가능한지 구하는 프로그램을 작성하여라.

입력
첫 번째 줄에 테스트케이스의 개수 T가 주어진다. (1 ≤ T ≤ 5)
각 테스트 케이스의 첫 번째 줄에 농장의 수 N, 도로의 수 M, 웜홀의 수 W가 주어진다. (1 ≤ N ≤ 500, 1 ≤ M ≤ 2,500, 1 ≤ W ≤ 200)
각 테스트 케이스의 두 번째 줄부터 M개의 줄에 걸쳐 양방향 도로의 정보 s, e, t가 공백으로 분리되어 주어진다. 
s, e는 해당 도로가 잇는 지점의 번호, t는 해당 도로를 통해 이동하는데 걸리는 시간을 의미한다. (1 ≤ s, e ≤ N, 1 ≤ t ≤ 10,000)

각 테스트 케이스의 (M + 2) 번째 줄부터 W개의 줄에 걸쳐 웜홀의 정보 s, e, t가 공백으로 분리되어 주어진다. 
s는 해당 웜홀의 시작지점, e는 해당 웜홀의 도착 지점, t는 줄어드는 시간을 의미한다. (1 ≤ s, e ≤ N, 1 ≤ t ≤ 10,000)
두 지점을 연결하는 도로가 한 개보다 많을 수도 있다.

출력
각 테스트 케이스마다 첫 번째 줄에 시간을 돌릴 수 있다면 YES, 불가능하다면 NO를 출력한다.

힌트

예제 입력
2
3 3 1
1 2 2
1 3 4
2 3 1
3 1 3
3 2 1
1 2 3
2 3 4
3 1 8

예제 출력
NO
YES

벨만포드 ( O(VE) )
- 간 정점을 다 값을 뿌려준다. 최단경로를 반복해서 구한다.
- 최단거리가 있는 

Solution
- 벨만포드 알고리즘을 적용해본다.
- 음수사이클을 체크할 수 있다.


Think
- 인접리스트를 구성.
- 웜홀의 종료점만을 모아서 각 점들로부터 출발해본다. 그래서 웜홀의 시작점을 만나서 다시 돌아오는 경우에 시간이 음수인지 체크한다.
- 한 지점에서 웜홀이 2개인지 확인하고 각 지점의 cost 를 구분해서 인접리스트를 만든다. 
- 음수 사이클이 존재하는가?? 사이클 확인??
  - 사이클 확인하는 방법은 벨만포드를 2번 순회해서, 가중치가 줄어드는 것이 있는지 확인하는 것이다. 음의 사이클을 돌면 돌수록, 가중치가 줄어들기 때문!

 */
public class WormHall_20160923 {
	static int T,N,M,W; // 테스트케이스, 농장수, 도로수, 웜홀 수
	static List<List<int[]>> adj; // 인접리스트
	static boolean[] check; // 방문한 지점 체크
	static int[] weight; // 각 농장에 도달하는 가중치
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		T = Integer.parseInt(line);
		
		for(int testCase = 1; testCase<=T;testCase++){
			String[] tmp = br.readLine().split(" ");
			
			N = Integer.parseInt(tmp[0]);
			M = Integer.parseInt(tmp[1]);
			W = Integer.parseInt(tmp[2]);
			
			adj = new ArrayList<List<int[]>>(); // 인접리스트
			
			for(int i=0;i<=N;i++){
				adj.add(new ArrayList<int[]>());
			}

			// 도로 입력받아서 인접 리스트 만들기
			int routes = M + W;
			
			for(int i=0;i<routes;i++){
				tmp = br.readLine().split(" ");
				
				int s = Integer.parseInt(tmp[0]); // 시작
				int e = Integer.parseInt(tmp[1]); // 종료
				int t = -1;
				
				if(i >= M){
					t = -Integer.parseInt(tmp[2]); // 시간
				}
				else {
					t = Integer.parseInt(tmp[2]); // 시간
				}
				
				List<int[]> l = adj.get(s);
				l.add(new int[]{e, t});
			}
			
			check = new boolean[N+1];
			weight = new int[N+1];
			
			// 가중치 초기화
			for(int i=0;i<=N;i++){
				weight[i] = Integer.MAX_VALUE;
			}
			
			boolean c = false;
			
			// 벨만포드로 이동해보기
			for(int k=1;k<=N+1;k++){
				for(int i=1;i<=N;i++){
					//i 지점을 시작점으로 벨만포드를 돈다.
					if(weight[i] == Integer.MAX_VALUE){
						weight[i] = 0;
					}
					
					// i 에서 이동할 수 있는 지점을 최소 시간을 갱신하면서 넣어준다.
					List<int[]> adjList = adj.get(i);
					
					// 인접한 노드의 시간갱신
					for(int j =0;j<adjList.size();j++){
						int[] adjPoint = adjList.get(j); // 시작, 종료, 시간
						
						int e = adjPoint[0]; // 종료지점
						int t = adjPoint[1]; // 시간
						
						// 갱신해준다.
						if(weight[i] + t < weight[e] ){
							weight[e] = weight[i] + t;
							if(k == N + 1){
								c = true;
							}
						}
					}
				}
			}
			
			if(!c){
				System.out.println("NO");
			}
			else{
				System.out.println("YES");
			}
		}
	}
}
