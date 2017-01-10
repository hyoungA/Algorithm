import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
���� : 

�����̴� M*N �׸��� ���� �����ִ� MN���� ����� ������ ������ ������ ������ �Ѵ�.
image1

������ �ִ� ���� ���� ������ ���� �׷��� �����Ѵ�. 
���� ���� A~F�� 6���� �׷��� Ȯ���� �� �ִ�. ���⼭ �ϳ��� �׷��� ������ �Ǹ�, �� �׷��� ���� �����ȴ�. �Ʒ��� ������ A�׷��� �������� ���� �׸��̴�. A��� �׷��� �������� ��, ������ �ٸ� ���� �׷�� �������� ���� Ȯ���� �� �ִ�. (��� B�׷�� ��������.)

image1

�� ��, C�� ������ �Ǹ�, �Ʒ��� ����. (����������, A,B,C,D�� �ѱ׷��� �Ǿ���.)

image1

�� ���� ���� �ִ� ����׷��� ��������, ��ü�� ���������� �ٲ��. �̷��� ��ü�� ��� ���� ������ ����� ���� ��ǥ�̴�.

������ ���� ������ �����Ͽ��� ��, ��� �ٵϵ��� ���� ������ ����� �ּ����� �ܰ���� ���ϴ� ���̴�.

�ð�����: 1��


�Է�


ù° �ٿ��� M*N �׸���� ��Ÿ���� �� ���� ���� M�� N�� ��ĭ�� ���̿� �ΰ� �־�����. M�� N�� 2�̻� 100�����̴�. ��° �ٺ��� M���� �� �ٿ��� �׸����� ������ �� �ٿ� ������ ��� ���� ��Ÿ���� 0�� ������ ���� ��Ÿ���� 1�� ��ĭ�� ���̿� �ΰ� �����ؼ� N���� �־�����.


���


ù° �ٿ� ��� ������ ���� ���� �ǵ��� �ϴ� �� �ٲٱ��� �ּ� Ƚ���� ����Ѵ�.


��Ʈ


�Է� ����
5 6
1 1 0 1 0 0
1 0 0 1 1 1
1 1 0 1 1 1
0 0 0 0 0 0
1 1 1 0 1 1


��� ����
2

Think
- ó������ ������ ��带 ������ ����. �̵��ϸ鼭 �׳� ������, ��ü �˻�
- ��ü�� �������� Ƚ�� ����
- ���������� ������ ���� check �صд�.

Solution
- ����� �����ִ� �۾��� �ʿ�. A�׷�, B�׷�, C�׷�...
- �׷��� ���� �������� ����Ǿ� �ִٰ� �ϸ� �ּ� �������� ���θ� �������ִ� ������ ���� ����

Tip
- �ϳ��� ��带 ��� ������ �൵ ������ ���� ����.

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
		
		// Ž�� :
		boolean checked = false;
		
		for(int m=0;m<M;m++){

			for(int n=0;n<N;n++){
				// copyGrid[m][n] ���� �̵��Ѵ�.
				if(check[m][n]){
					continue;
				}
				
				// m, n �� ���� ���
				int copyGrid[][] = grid.clone();
				// value �� �ش��ϴ� ���� ������ ��
				int value = copyGrid[m][n];
				
				adjacentCheck(m, n, value, copyGrid);
				
				if(!checked){
					checked = true;
				}
			}
		}
	}
	
	static void adjacentCheck(int m, int n, int value, int[][] grid){
		// m, n ���� ������ ����� ���� �ٲ��ش�.
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
