import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
문제 : 

형석이는 M*N 그리드 위에 놓여있는 MN개의 흰색과 검은색 돌들을 가지고 게임을 한다.
image1

인접해 있는 같은 색의 돌들은 같은 그룹을 형성한다. 
위를 보면 A~F의 6개의 그룹을 확인할 수 있다. 여기서 하나의 그룹을 뒤집게 되면, 그 그룹의 색이 반전된다. 아래의 사진은 A그룹을 뒤집었을 때의 그림이다. A라는 그룹을 뒤집었을 때, 인접한 다른 색의 그룹과 합쳐지는 것을 확인할 수 있다. (흰색 B그룹과 합쳐졌다.)

image1

그 후, C를 뒤집게 되면, 아래와 같다. (마찬가지로, A,B,C,D가 한그룹이 되었다.)

image1

그 다음 위에 있는 흰색그룹을 뒤집으면, 전체가 검은색으로 바뀐다. 이렇게 전체를 모두 같은 색으로 만드는 것이 목표이다.

문제는 위의 방법대로 진행하였을 때, 모든 바둑돌을 같은 색으로 만드는 최소한의 단계수를 구하는 것이다.

시간제한: 1초


입력


첫째 줄에는 M*N 그리드는 나타내는 두 양의 정수 M과 N이 빈칸을 사이에 두고 주어진다. M과 N은 2이상 100이하이다. 둘째 줄부터 M개의 각 줄에는 그리드의 가로줄 한 줄에 놓여진 흰색 동을 나타내는 0과 검은색 돌을 나타내는 1이 빈칸을 사이에 두고 연속해서 N개가 주어진다.


출력


첫째 줄에 모든 돌들이 같은 색이 되도록 하는 색 바꾸기의 최소 횟수를 출력한다.


힌트


입력 예제
5 6
1 1 0 1 0 0
1 0 0 1 1 1
1 1 0 1 1 1
0 0 0 0 0 0
1 1 1 0 1 1


출력 예제
2

Think
- 처음부터 인접한 노드를 뒤집어 본다. 이동하면서 그냥 뒤집고, 전체 검사
- 전체가 뒤집히면 횟수 저장
- 시작지점의 인접한 노드는 check 해둔다.

Solution
- 덩어리로 묶어주는 작업이 필요. A그룹, B그룹, C그룹...
- 그룹이 노드고 간선으로 연결되어 있다고 하면 최소 연산으로 전부를 연결해주는 문제로 변형 가능

Tip
- 하나의 노드를 계속 변형해 줘도 동일한 답을 얻음.

 * 
 */
public class FunnyGame_20160919 {
	static int M,N;
	static boolean check[][];
	static int grid[][];
	static int [][] move = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		check = new boolean[M][N];
		grid = new int[M][N];
		int gridPoint = 0;
		
		for(int m=0;m<M;m++){
			for(int n=0;n<N;n++){
				grid[m][n] = sc.nextInt();
				gridPoint += grid[m][n];
			}
		}
		
		// 탐색 :
		boolean checked = false;
		
		for(int m=0;m<M;m++){

			for(int n=0;n<N;n++){
				// copyGrid[m][n] 에서 이동한다.
				if(check[m][n]){
					continue;
				}
				
				// m, n 이 시작 노드
				int copyGrid[][] = grid.clone();
				// value 에 해당하는 값을 뒤집을 것
				int value = copyGrid[m][n];
				
				adjacentCheck(m, n, value, copyGrid);
				
				if(!checked){
					checked = true;
				}
			}
		}
	}
	
	static void adjacentCheck(int m, int n, int value, int[][] grid){
		// m, n 에서 인접한 노드의 값을 바꿔준다.
		int changeValue = 0;
		
		if(value == 0){
			changeValue = 1;
		}
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[]{m, n});
		
		while(!q.isEmpty()){
			int[] tmp = q.poll();
			int tmpM = tmp[0];
			int tmpN = tmp[1];
			
			for(int i=0;i<4;i++){
				int moveM = tmpM + move[i][0];
				int moveN = tmpN + move[i][1];
				
				if(moveM < 0 || moveN < 0 || moveM >= M || moveN >= N){
					continue;
				}
				
				if(grid[moveM][moveN] == value){
					grid[moveM][moveN] = changeValue;
					q.add(new int[]{moveM, moveN});
				}
			}
		}
	}
}
