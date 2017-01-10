import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
문제 : 등산

최근 출시된 오버워치가 지루해진 형석이는 등산이나 하기로 결정했다. 
형석이는 산의 시작점부터 도착지점까지 산을 오르락~ 내리락~ 하면서 간다. 
하지만 형석이는 몸이 허약하여, 하루동안 급변한 고도의 변화를 느끼면 어지러움과 함께, 구토를 하며 쓰러진다. 
형석이는 이런 증상을 최소화하기 위해, 경로 중 가장 높은 지점과, 가장 낮은 지점의 고도차가 가장 작게 등산 경로를 정하려고 한다. 
하지만 등산 준비로 바쁜 형석이는 당신에게 이 문제를 부탁하였다.
산은 N*N의 크기로 이루어져 있다. 
형석이는 (1,1)을 시작점으로, (N,N)을 도착점으로 정하였다. 
또한 형석이는 상, 하, 좌, 우의 인접한 칸으로 이동할 수 있다. 
가능한 형석이의 등산 경로 중, 가장 높은 지점과 낮은 지점의 차가 최소가 되는 경우의 그 값을 구하여라. 

시간제한: 1초

입력
첫째 줄에 N(2<=N<=100)이 주어진다. 다음 N개의 줄에는 배열이 주어진다. 
배열의 각 수는 0보다 크거나 같고, 200보다 작거나 같은 정수이다.

출력
첫째 줄에 경로 중 고도의 최대와 최소의 차가 가장 작아질 때의 그 값을 출력하세요.


입력 예제
5
1 1 3 6 8
1 1 8 5 6
8 10 0 4 3
8 0 2 3 4
4 3 0 2 1


출력 예제
5

Solution
- 오른쪽이나 아래쪽으로만 갈 수 있는 경우에는 다이나믹 프로그래밍이 가능하지만, 이 문제는 아님
- 결정문제로의 변형 : 최소, 최대 높이 차이가 h 이하가 되는 경로가 존재하는지 확인.
- parametric search : 결정문제로 변형
- Max - Min <= h  h 최대 높이 제한을 두면 최소 값이 정해진다.
  - [0, 5], [1,6], [2,7], ... [195, 200]
- 시간복잡도
  - 이분검색 O (logW)

 * 
 */
public class Climbing_20160919 {
	static int N;
	static int[][] grid = new int[101][101];
	static int[][] check;
	static int min,max;
	static int result ;
	static int[][] move;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		check = new int[101][101];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		result = Integer.MAX_VALUE;
		Queue<int[]> q = new LinkedList<int[]>();
		
		move = new int[4][2];
		move[0] = new int[]{0,1};
		move[1] = new int[]{1,0};
		move[2] = new int[]{0,-1};
		move[3] = new int[]{-1,0};
		
		long start = System.currentTimeMillis();
		
		for(int i= 0;i<N;i++){
			for(int j= 0;j<N;j++){
				grid[i][j] = sc.nextInt();			
				max = Math.max(grid[i][j], max);
				min = Math.min(grid[i][j], min);
			}
		}
		
//		min = Math.min(grid[0][0], grid[N-1][N-1]);
		
		// search
		// 시작점부터 끝점까지 경로 중 가능한 경로를 binary search 로 찾는다.
		int lo = -1, hi = max;
		
		while(lo <= hi){
			int mid = (lo + hi) / 2;
			check = new int[101][101];
			
			for(int n= 0;n<101;n++){
				for(int m= 0;m<101;m++){
					check[n][m] = -1;
				}
			}
			
			if(search(mid, 0, 0, grid[0][0], grid[0][0])){
				hi = mid - 1;
			}
			else{
				lo = mid + 1;
			}
		}
		
		System.out.println(result);
		
//        int lo = 0, hi = max - min, mid;
//        
//        while(lo < hi){
//            mid = (lo + hi) / 2;
//             
//            if(search2(mid)) hi = mid;
//            else lo = mid + 1;
//        }
//          
//        System.out.println(lo);
		
		long end = System.currentTimeMillis();
		
		System.out.println("시간 : " + (end - start) );
	}
	
	// 크기 dif 로 풀이가 되면, 최소 값 저장
	static boolean search(int dif, int row, int column, int min, int max){
		if(row == N-1 && column == N-1){
			result = check[N-1][N-1];
			return true;
		}
		
		// 방문하지 않았으면, 이동해본다.
		for(int i = 0 ;i<4;i++){
			int moveRow = move[i][0] + row;
			int moveColumn = move[i][1] + column;
			
			if(moveRow < 0 || moveColumn < 0 || moveRow >= N || moveColumn >= N){
				continue;
			}
			
			int moveMax = Math.max(max, grid[moveRow][moveColumn]);
			int moveMin = Math.min(min, grid[moveRow][moveColumn]);
			
			// 최대값과 최소값의 차가 dif 와 같거나 작아야 한다.
			int difference = moveMax - moveMin;
			
			if((check[moveRow][moveColumn] == -1 || check[moveRow][moveColumn] > difference) && (difference) <= dif ){
				check[moveRow][moveColumn] = moveMax - moveMin;
				if(search(dif, moveRow, moveColumn, moveMin, moveMax)){					
					return true;
				}
			}
		}
		
		return false;
	}
	
	static boolean search2(Queue<int[]> q, int mid){
		while(!q.isEmpty()){
			int[] tmp = q.poll();
			
			int row = tmp[0];
			int column = tmp[1];
			int min = tmp[2];
			int max = tmp[3];
			
			if(row == N-1 && column == N-1){
				result = max - min;
				return true;
			}
			
			// 방문하지 않았으면, 이동해본다.
			for(int i = 0 ;i<4;i++){
				int moveRow = move[i][0] + row;
				int moveColumn = move[i][1] + column;
				
				if(moveRow < 0 || moveColumn < 0 || moveRow >= N || moveColumn >= N){
					continue;
				}
				
				int moveMax = Math.max(max, grid[moveRow][moveColumn]);
				int moveMin = Math.min(min, grid[moveRow][moveColumn]);
				
				// 최대값과 최소값의 차가 dif 와 같거나 작아야 한다.			
				if(moveMax - moveMin <= mid){
					q.add(new int[]{moveRow, moveColumn, moveMax, moveMin});
				}
			}
		}
		
		return false;
	}
	
    static boolean search2(int mid){
        for(int lo = min; lo<=max; ++lo){
            for(int i = 0;i<N; i++){
                for(int j = 0;j<N; j++){
                    if((grid[i][j] < lo || grid[i][j] > lo + mid)){
                        check[i][j] = 1;
                    }
                    else
                        check[i][j] = 0;
                }
            }
             
            if(check[0][0] > 0) continue;
             
            Queue<int[]> q = new LinkedList<int[]>();
            q.add(new int[]{0,0});
            check[0][0] = 1;
             
            while(!q.isEmpty()){
                int[] tmp = q.poll();
                  
                int row = tmp[0];
                int column = tmp[1];
//                int min = tmp[2];
//                int max = tmp[3];
                  
                if(row == N-1 && column == N-1){
                    return true;
                }
                  
                // 방문하지 않았으면, 이동해본다.
                for(int i = 0 ;i<4;i++){
                    int moveRow = move[i][0] + row;
                    int moveColumn = move[i][1] + column;
                      
                    if(moveRow < 0 || moveColumn < 0 || moveRow >= N || moveColumn >= N){
                        continue;
                    }
                      
                    // 최대값과 최소값의 차가 dif 와 같거나 작아야 한다.           
                    if(check[moveRow][moveColumn] == 0){
                        q.add(new int[]{moveRow, moveColumn});
                        check[moveRow][moveColumn] = 1;
                    }
                }
            }
        }
          
        return false;
    }

}
