import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
���� : ���

�ֱ� ��õ� ������ġ�� �������� �����̴� ����̳� �ϱ�� �����ߴ�. 
�����̴� ���� ���������� ������������ ���� ������~ ������~ �ϸ鼭 ����. 
������ �����̴� ���� ����Ͽ�, �Ϸ絿�� �޺��� ���� ��ȭ�� ������ ��������� �Բ�, ���並 �ϸ� ��������. 
�����̴� �̷� ������ �ּ�ȭ�ϱ� ����, ��� �� ���� ���� ������, ���� ���� ������ ������ ���� �۰� ��� ��θ� ���Ϸ��� �Ѵ�. 
������ ��� �غ�� �ٻ� �����̴� ��ſ��� �� ������ ��Ź�Ͽ���.
���� N*N�� ũ��� �̷���� �ִ�. 
�����̴� (1,1)�� ����������, (N,N)�� ���������� ���Ͽ���. 
���� �����̴� ��, ��, ��, ���� ������ ĭ���� �̵��� �� �ִ�. 
������ �������� ��� ��� ��, ���� ���� ������ ���� ������ ���� �ּҰ� �Ǵ� ����� �� ���� ���Ͽ���. 

�ð�����: 1��

�Է�
ù° �ٿ� N(2<=N<=100)�� �־�����. ���� N���� �ٿ��� �迭�� �־�����. 
�迭�� �� ���� 0���� ũ�ų� ����, 200���� �۰ų� ���� �����̴�.

���
ù° �ٿ� ��� �� ���� �ִ�� �ּ��� ���� ���� �۾��� ���� �� ���� ����ϼ���.


�Է� ����
5
1 1 3 6 8
1 1 8 5 6
8 10 0 4 3
8 0 2 3 4
4 3 0 2 1


��� ����
5

Solution
- �������̳� �Ʒ������θ� �� �� �ִ� ��쿡�� ���̳��� ���α׷����� ����������, �� ������ �ƴ�
- ������������ ���� : �ּ�, �ִ� ���� ���̰� h ���ϰ� �Ǵ� ��ΰ� �����ϴ��� Ȯ��.
- parametric search : ���������� ����
- Max - Min <= h  h �ִ� ���� ������ �θ� �ּ� ���� ��������.
  - [0, 5], [1,6], [2,7], ... [195, 200]
- �ð����⵵
  - �̺а˻� O (logW)

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
		// ���������� �������� ��� �� ������ ��θ� binary search �� ã�´�.
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
		
		System.out.println("�ð� : " + (end - start) );
	}
	
	// ũ�� dif �� Ǯ�̰� �Ǹ�, �ּ� �� ����
	static boolean search(int dif, int row, int column, int min, int max){
		if(row == N-1 && column == N-1){
			result = check[N-1][N-1];
			return true;
		}
		
		// �湮���� �ʾ�����, �̵��غ���.
		for(int i = 0 ;i<4;i++){
			int moveRow = move[i][0] + row;
			int moveColumn = move[i][1] + column;
			
			if(moveRow < 0 || moveColumn < 0 || moveRow >= N || moveColumn >= N){
				continue;
			}
			
			int moveMax = Math.max(max, grid[moveRow][moveColumn]);
			int moveMin = Math.min(min, grid[moveRow][moveColumn]);
			
			// �ִ밪�� �ּҰ��� ���� dif �� ���ų� �۾ƾ� �Ѵ�.
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
			
			// �湮���� �ʾ�����, �̵��غ���.
			for(int i = 0 ;i<4;i++){
				int moveRow = move[i][0] + row;
				int moveColumn = move[i][1] + column;
				
				if(moveRow < 0 || moveColumn < 0 || moveRow >= N || moveColumn >= N){
					continue;
				}
				
				int moveMax = Math.max(max, grid[moveRow][moveColumn]);
				int moveMin = Math.min(min, grid[moveRow][moveColumn]);
				
				// �ִ밪�� �ּҰ��� ���� dif �� ���ų� �۾ƾ� �Ѵ�.			
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
                  
                // �湮���� �ʾ�����, �̵��غ���.
                for(int i = 0 ;i<4;i++){
                    int moveRow = move[i][0] + row;
                    int moveColumn = move[i][1] + column;
                      
                    if(moveRow < 0 || moveColumn < 0 || moveRow >= N || moveColumn >= N){
                        continue;
                    }
                      
                    // �ִ밪�� �ּҰ��� ���� dif �� ���ų� �۾ƾ� �Ѵ�.           
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
