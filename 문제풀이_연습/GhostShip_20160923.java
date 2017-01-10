import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
����

����� ������ �������� ���� ���ɼ��� ��ġ���� �־���. 
����� ���ɵ��� �ڰ� �ִ� �� �ð��� ���� ���ɼ��� �ִ� ����Ʈ�� �̿��� Ż���Ϸ��� �Ѵ�.
���ɼ��� ������ ������ ũ���� ���簢�� ��� ���ڰ� ���η� W��, ���η� H�� �̾��� ������� �Ǿ� �ִ�. 
����� ���� �� �ִ� ��ġ���� �����¿�� ������ �� �ִ�. 
���ɼ��� �ſ� ���ұ� ������ ���� ��� �ִ� ���ڸ� ����� ���ڰ� �μ����� ����. 
������ �̹� �μ��� �ִ� ���ڵ� �����Ѵ�. ���� ����� ������ �ƴϱ� ������ �μ��� ���ڴ� �ɾ ������ �� ����.

���ڰ� �ʹ� ���� �μ����� ���ɵ��� ȭ�� ���� ������ ����� ���� ���� ������ ���ڸ� �ı��ϸ鼭 ���ɼ����� Ż���Ϸ��� �Ѵ�. ���� ���ɼ����� Ż���ϴ� ���� ���� ���α׷��� �ۼ�����.

�ð�����: 1��

�Է�

ù �ٿ� �� ���� W(2<=W<=1500)�� H(2<=W<=1500)�� ������ ���̿� �ΰ� �־�����. 
�� ���� �ٺ��� H�ٸ��� ���� W���� ���ڰ� �־�����.

�� ���ڴ� X, O, S, E �� �ϳ��� ��ü ���ڵ� �� S�� E�� ��Ȯ�� �ϳ����� ����ȴ�.
- X�� ó������ �μ��� ���ڸ� ��Ÿ����.
- O�� ���� ��� ������ �� ������, ���� ���� �μ����� ���ڸ� ��Ÿ����.
- S�� ó�� ���� �� �ִ� ������ ��ġ�� ��Ÿ����.
- E�� ���ɼ��� ����Ʈ ��ġ�� ��Ÿ����.

���
���� ���ڸ� �ּ� ������ �ı��ϸ鼭 ���ɼ����� Ż���� �� �ı��Ǵ� ������ ������ ù �ٿ� ����϶�.
���� ���� ���ɼ����� Ż���� �� �ִ� ����� ���� ���, -1�� ����϶�.

���� �Է� 1
4 3
OOSO
OXXO
OOEO

���� ��� 1
4

���� ���� 1
�� �� ���� Ż�� ��ΰ� �����Ѵ�.
���� (��, ��, ��, ��, ��, ��)�� �����̴� ��� 6���� ���ڰ� �μ�����.
���� (��, ��, ��, ��)�� �����̴� ��� 4���� ���ڰ� �μ�����.
���� ���� 4�̴�.

���� �Է� 2
3 3
EXX
XSO
OOX

���� ��� 2
-1

Think
- �ּ� �μ��� ���ڼ� = �ּҰ��
- �ٽ� ��Ʈ���� �Ұ��� -> BFS? �׷���, �μ��� ���ڸ� ����ؾ� �ϹǷ� DFS �� ���� : �ð��ʰ�??
- DFS �� �ּ� ��θ� ã���� �� ��, �� �׸��忡 �ּ� ������ ǥ���صΰ� �ּ� ������ �Ѿ�� �̵����� �ʵ��� © �� �ִ�.

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
				
				// �ּ� �̵� �� ������ �׸���
				chk[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// input üũ
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
		
		// ��� ���
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
		
		// 4���� �̵�
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
		
		// q ���� ���� �������� ������ �Ȱ���.
		while(!q.isEmpty()){
			int tmp[] = q.poll();
			y = tmp[0];
			x = tmp[1];
			
			if(grid[y][x] == 'E'){
				minCount = tmp[2];
				break;
			}
			
			// 4���� �̵�
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
