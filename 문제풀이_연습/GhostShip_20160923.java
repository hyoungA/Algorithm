import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
문제

강희는 정신을 차려보니 낯선 유령선에 납치당해 있었다. 
강희는 유령들이 자고 있는 낮 시간에 몰래 유령선에 있는 구명보트를 이용해 탈출하려고 한다.
유령선의 갑판은 동일한 크기의 정사각형 모양 판자가 가로로 W개, 세로로 H개 이어진 모양으로 되어 있다. 
강희는 현재 서 있는 위치에서 상하좌우로 움직일 수 있다. 
유령선은 매우 낡았기 때문에 강희가 딛고 있는 판자를 벗어나면 판자가 부서지고 만다. 
심지어 이미 부서져 있는 판자도 존재한다. 물론 강희는 유령이 아니기 때문에 부서진 판자는 걸어서 지나갈 수 없다.

판자가 너무 많이 부서지면 유령들이 화를 내기 때문에 강희는 가장 작은 개수의 판자를 파괴하면서 유령선에서 탈출하려고 한다. 강희가 유령선에서 탈출하는 것을 돕는 프로그램을 작성하자.

시간제한: 1초

입력

첫 줄에 두 정수 W(2<=W<=1500)와 H(2<=W<=1500)가 공백을 사이에 두고 주어진다. 
그 다음 줄부터 H줄마다 각각 W개의 문자가 주어진다.

각 문자는 X, O, S, E 중 하나며 전체 문자들 중 S와 E는 정확히 하나임이 보장된다.
- X는 처음부터 부서진 판자를 나타낸다.
- O는 강희가 밟고 지나갈 수 있지만, 밟은 이후 부서지는 판자를 나타낸다.
- S는 처음 강희가 서 있는 판자의 위치를 나타낸다.
- E는 유령선의 구명보트 위치를 나타낸다.

출력
강희가 판자를 최소 개수로 파괴하면서 유령선에서 탈출할 때 파괴되는 판자의 개수를 첫 줄에 출력하라.
만약 강희가 유령선에서 탈출할 수 있는 방법이 없는 경우, -1을 출력하라.

예제 입력 1
4 3
OOSO
OXXO
OOEO

예제 출력 1
4

예제 설명 1
총 두 가지 탈출 경로가 존재한다.
강희가 (좌, 좌, 하, 하, 우, 우)로 움직이는 경우 6개의 판자가 부서진다.
강희가 (우, 하, 하, 좌)로 움직이는 경우 4개의 판자가 부서진다.
따라서 답은 4이다.

예제 입력 2
3 3
EXX
XSO
OOX

예제 출력 2
-1

Think
- 최소 부서진 판자수 = 최소경로
- 다시 백트랙이 불가함 -> BFS? 그러나, 부서진 판자를 기억해야 하므로 DFS 로 접근 : 시간초과??
- DFS 로 최소 경로를 찾고자 할 때, 각 그리드에 최소 레벨을 표시해두고 최소 레벨을 넘어서면 이동하지 않도록 짤 수 있다.

 */
public class GhostShip_20160923 {
	static int W,H;
	static char[][] grid;
	static int[] dx = new int[]{-1,1,0,0};
	static int[] dy = new int[]{0,0,1,-1};
	static int minCount= Integer.MAX_VALUE;
	static int[][] chk;
	static Queue<int[]> q;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		W = sc.nextInt();
		H = sc.nextInt();
		sc.nextLine();
		grid = new char[H][W];
		int startIndexH = -1;
		int startIndexW = -1;
		chk = new int[H][W];
		q = new LinkedList<int[]>();
		
		for(int i=0;i<H;i++){
			String line = sc.nextLine();
			for(int j=0;j<W;j++){
				grid[i][j] = line.charAt(j);
				
				if(grid[i][j] == 'S'){
					startIndexH = i;
					startIndexW = j;
				}
				
				// 최소 이동 값 저장할 그리드
				chk[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// input 체크
//		for(int i=0;i<H;i++){
//			for(int j=0;j<W;j++){
//				System.out.print(grid[i][j]);
//			}
//			System.out.println();
//		}
		
		// search
		//searchDFS(startIndexW, startIndexH, 0);
		
		// search
		searchBFS(startIndexW, startIndexH);
		
		// 결과 출력
		if(minCount == Integer.MAX_VALUE){
			System.out.println(-1);
		}
		else{
			System.out.println(minCount);
		}
		
		
	}
	
	static public void searchDFS(int x, int y, int count){
		if(count >= minCount){
			return;
		}
		
		if(grid[y][x] == 'E'){
			minCount = Math.min(minCount, count);
			return;
		}
		
		// 4방향 이동
		for(int i=0;i<4;i++){
			if(x + dx[i] <0 || x+dx[i] >= W || y + dy[i] <0 || y+dy[i] >=H || grid[y+dy[i]][x+dx[i]] == 'X' || chk[y+dy[i]][x+dx[i]] <= count + 1){
				continue;
			}
			
			grid[y][x] = 'X';
			chk[y+dy[i]][x+dx[i]] = count + 1;
			searchDFS(x+dx[i], y+dy[i], count+1);
			grid[y][x] = 'O';
		}
	}
	
	static void searchBFS(int x, int y){
		q.add(new int[]{y, x, 0});
		chk[y][x] = 0;
		
		// q 에서 현재 레벨보다 작으면 안간다.
		while(!q.isEmpty()){
			int tmp[] = q.poll();
			y = tmp[0];
			x = tmp[1];
			
			if(grid[y][x] == 'E'){
				minCount = tmp[2];
				break;
			}
			
			// 4방향 이동
			for(int i=0;i<4;i++){
				if(x + dx[i] <0 || x+dx[i] >= W || y + dy[i] <0 || y+dy[i] >=H || grid[y+dy[i]][x+dx[i]] == 'X' || chk[y+dy[i]][x+dx[i]] <= tmp[2] + 1){
					continue;
				}
				
				chk[y+dy[i]][x+dx[i]] = tmp[2] + 1;
				q.add(new int[]{y+dy[i], x+dx[i], tmp[2]+1});
			}
		}
	}
}
