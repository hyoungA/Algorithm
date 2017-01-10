import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 
����

���� ���� ������� Ž���ϴ���, ���� �� ���� ���� ��Ȧ�� �߰��ߴ�. 
��Ȧ�� ���� ���忡�� �ٸ� ���������� �̻��� �ܹ��� ��ην� ����� ���� �ð����� �����ð����� �ð��� �ǵ�����. 
���� ������ N���� ����� M���� ����� ����, W���� ��Ȧ�� �����Ǿ��ִ�. �׸��� �� ������ ���ǻ� ����1, ����2, �� ,���� N�̶�� �̸��ٿ�����.
���� ���ڱ� ������ġ���� ����ؼ� ������ �ϴ� �ٽ� ������ġ�� ���ƿ��� �� �ð��� �ǵ��� �� �ִ� ��찡 �ִ��� �ñ�������. 
���� ���� �ð��� �ǵ����� ������ �������� ���ϴ� ���α׷��� �ۼ��Ͽ���.

�Է�
ù ��° �ٿ� �׽�Ʈ���̽��� ���� T�� �־�����. (1 �� T �� 5)
�� �׽�Ʈ ���̽��� ù ��° �ٿ� ������ �� N, ������ �� M, ��Ȧ�� �� W�� �־�����. (1 �� N �� 500, 1 �� M �� 2,500, 1 �� W �� 200)
�� �׽�Ʈ ���̽��� �� ��° �ٺ��� M���� �ٿ� ���� ����� ������ ���� s, e, t�� �������� �и��Ǿ� �־�����. 
s, e�� �ش� ���ΰ� �մ� ������ ��ȣ, t�� �ش� ���θ� ���� �̵��ϴµ� �ɸ��� �ð��� �ǹ��Ѵ�. (1 �� s, e �� N, 1 �� t �� 10,000)

�� �׽�Ʈ ���̽��� (M + 2) ��° �ٺ��� W���� �ٿ� ���� ��Ȧ�� ���� s, e, t�� �������� �и��Ǿ� �־�����. 
s�� �ش� ��Ȧ�� ��������, e�� �ش� ��Ȧ�� ���� ����, t�� �پ��� �ð��� �ǹ��Ѵ�. (1 �� s, e �� N, 1 �� t �� 10,000)
�� ������ �����ϴ� ���ΰ� �� ������ ���� ���� �ִ�.

���
�� �׽�Ʈ ���̽����� ù ��° �ٿ� �ð��� ���� �� �ִٸ� YES, �Ұ����ϴٸ� NO�� ����Ѵ�.

��Ʈ

���� �Է�
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

���� ���
NO
YES

�������� ( O(VE) )
- �� ������ �� ���� �ѷ��ش�. �ִܰ�θ� �ݺ��ؼ� ���Ѵ�.
- �ִܰŸ��� �ִ� 

Solution
- �������� �˰����� �����غ���.
- ��������Ŭ�� üũ�� �� �ִ�.


Think
- ��������Ʈ�� ����.
- ��Ȧ�� ���������� ��Ƽ� �� ����κ��� ����غ���. �׷��� ��Ȧ�� �������� ������ �ٽ� ���ƿ��� ��쿡 �ð��� �������� üũ�Ѵ�.
- �� �������� ��Ȧ�� 2������ Ȯ���ϰ� �� ������ cost �� �����ؼ� ��������Ʈ�� �����. 
- ���� ����Ŭ�� �����ϴ°�?? ����Ŭ Ȯ��??
  - ����Ŭ Ȯ���ϴ� ����� �������带 2�� ��ȸ�ؼ�, ����ġ�� �پ��� ���� �ִ��� Ȯ���ϴ� ���̴�. ���� ����Ŭ�� ���� ������, ����ġ�� �پ��� ����!

 */
public class WormHall_20160923 {
	static int T,N,M,W; // �׽�Ʈ���̽�, �����, ���μ�, ��Ȧ ��
	static List<List<int[]>> adj; // ��������Ʈ
	static boolean[] check; // �湮�� ���� üũ
	static int[] weight; // �� ���忡 �����ϴ� ����ġ
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		T = Integer.parseInt(line);
		
		for(int testCase = 1; testCase<=T;testCase++){
			String[] tmp = br.readLine().split(" ");
			
			N = Integer.parseInt(tmp[0]);
			M = Integer.parseInt(tmp[1]);
			W = Integer.parseInt(tmp[2]);
			
			adj = new ArrayList<List<int[]>>(); // ��������Ʈ
			
			for(int i=0;i<=N;i++){
				adj.add(new ArrayList<int[]>());
			}

			// ���� �Է¹޾Ƽ� ���� ����Ʈ �����
			int routes = M + W;
			
			for(int i=0;i<routes;i++){
				tmp = br.readLine().split(" ");
				
				int s = Integer.parseInt(tmp[0]); // ����
				int e = Integer.parseInt(tmp[1]); // ����
				int t = -1;
				
				if(i >= M){
					t = -Integer.parseInt(tmp[2]); // �ð�
				}
				else {
					t = Integer.parseInt(tmp[2]); // �ð�
				}
				
				List<int[]> l = adj.get(s);
				l.add(new int[]{e, t});
			}
			
			check = new boolean[N+1];
			weight = new int[N+1];
			
			// ����ġ �ʱ�ȭ
			for(int i=0;i<=N;i++){
				weight[i] = Integer.MAX_VALUE;
			}
			
			boolean c = false;
			
			// ��������� �̵��غ���
			for(int k=1;k<=N+1;k++){
				for(int i=1;i<=N;i++){
					//i ������ ���������� �������带 ����.
					if(weight[i] == Integer.MAX_VALUE){
						weight[i] = 0;
					}
					
					// i ���� �̵��� �� �ִ� ������ �ּ� �ð��� �����ϸ鼭 �־��ش�.
					List<int[]> adjList = adj.get(i);
					
					// ������ ����� �ð�����
					for(int j =0;j<adjList.size();j++){
						int[] adjPoint = adjList.get(j); // ����, ����, �ð�
						
						int e = adjPoint[0]; // ��������
						int t = adjPoint[1]; // �ð�
						
						// �������ش�.
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
