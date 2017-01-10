import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 
����

������ �ǹ��� ������ �⺻���� �ǹ��̴�. ������ ���Կ� ����Ͽ� ������ ��Ģ�� ���� ���Ǳ� ������, ��Ģ�� ���� ��Ȯ�� ������ ����ϴ� ���� �߿��ϴ�. 
�������� ���� ���Ը� ����, �� N �� ���� ������ �Ͽ���. ������ �ǹ��� �����ϰ� �����ϱ� ���Ͽ� �� �����ϸ��� ����(���� �Ǵ� ����)�� ����Ͽ� �������� �Ű��Ͽ���. 
������ �ű�µ� ������ �Ǵ� ������ ���� ��Ģ�� ���ؼ� ���ȴ�.

"1�Ϻ��� N�� ������ � ���ӵ� �Ⱓ�� ���Ͽ� �� �Ⱓ ���� ������ ������ ���Ѵ�. ��, �Ϸ� �̻��� �Ⱓ�� ����Ѵ�. 
����, ��ü ������ ��� �Ⱓ�� ���Ͽ� ���� ������ ���յ� �� K��°�� ū ���� ������ �ȴ�. ��, ���յ��� ������������ �������� �� K��° ���̴�."
���� ���, �� 3�ϰ� ������ �Ͽ��ٸ� 1��, 1��~2��, 1��~3��, 2��, 2��~3��, 3�� �� 6���� �� ���� ���Ͽ� �� �Ⱓ���� ������ ������ ���ϰ�, �� �� K��° ū ���� ������ ������ �ȴ�.

�Է�
ù ��° �ٿ��� N�� K�� ���̿� �ϳ��� ������ �ΰ� �־�����. ��, 1��N��1,000,000, 1��K��min(50, N(N+1)/2) �̴�.
���� �ٿ��� �� �������� ������ ��Ÿ���� N���� ������ ���̿� �ϳ� �� ������ �ΰ� �־�����. ���� �ش� �����Ͽ� ������ ���Ҵٸ� ���� ��, ���ظ� ���Ҵٸ� ���� ��, ���� ���� ��� ���ٸ� 0�̴�. 
������ ������ -1,000,000,000 ���� 1,000,000,000 �����̴�.

���
������ �����ϴ� ������ ������ K��°�� ū ���� ��Ÿ���� �ϳ��� ������ ����Ѵ�.


��Ʈ


�Է� ����
4 3
20 -10 10 5


��� ����
20

Think
- N = 4 �� ��쿡 ť ������� 4 * 5 / 2 �� ���� 10 �� ���ٵ�, ū ������ ã�� �۾��� �ϸ� ������ ����. 
  �׷��ٰ�, ������ �� �����ϴ� ���� �ʿ��� ������.. 4 �� ��� 10�� ���ϴ� ���� ��� ���̽��� �� �����ؾ� ��. ��� ����??

Solution
- idea1 : K �� �۴�. -> O(NK) �� ������ Ǯ���� ����� �ؾ� ��.
- idea2 : �迭 2���� ���, 
          G : i��° �Է��� ���������� �ϴ� ������ �յ��� ������������ ���ĵǾ� �ִ� ����Ʈ(�迭) �ϳ���, 
          Ans : ��ü�� ���� ( ���� ���������� �ϴ� ) ������������ ���ĵǾ� �ִ� ����Ʈ(�迭) �� �ʿ�. 
- G �迭 ���� a[i] �� ��ġ�� ã�Ƽ� �־��ش�. ( ū������ ��Ȯ�� K���� ��������. )
- Ans (����迭) : G ��  Ans �� ������Ʈ�Ѵ�. �� �迭�� 
- ������Ʈ�� �ϴ� ������ �̿��ϸ� ������ Ǯ����.

 */
public class Tax_20160922 {
	static int N,K;
	
	static long[] g;
	static int gSize;
	static Queue<Long> a;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String []tmp = line.split(" ");
		
		// �Է°�
		N = Integer.parseInt(tmp[0]);
		K = Integer.parseInt(tmp[1]);
		
		//�ʱ�ȭ
		int qSize = Math.min(50, (N*(N+1))/2);
		a = new PriorityQueue<Long>(qSize);
		g = new long[51];
		
		for(int i=0;i<g.length;i++){
			g[i] = Long.MIN_VALUE;
		}
		
		// �Է°����� �ϳ��ϳ� ó��
		line = br.readLine();
		tmp = line.split(" ");
		
		for(int i=1;i<=N;i++){
			long current = (long)Integer.parseInt(tmp[i-1]);
			
			// g �迭�� ���� ���� �����ش�.
			if(gSize == 0){
				g[gSize++] = current;
			}
			else {
				// �� ���� ��忡 �־��ְ�
				addCurrent(current);
				// ���� ���� g �迭�� ��ġ��Ų��.
				putCurrent(current);
			}
			
			// ����Ʈ�� pq �� �����Ѵ�.
			addAnswer();
		}
		
		long result = 0;
		
		for(int i =0;i<K;i++){
			result = a.poll();
		}
		
		System.out.println(result);
	}
	
	static void addAnswer (){
		for(int i=0;i<gSize;i++){
			a.add(g[i]);
		}
	}
	
	static void addCurrent(long current){
		for(int i=0;i<gSize;i++){
			g[i] += current;
		}
	}
	
	// current �� ���� ���� ��ġ�� ã�Ƽ� �־��ش�.
	// �ϳ��� �б� ���ؼ� current �� ��ġ�� ã�´�. current ���� ���� ���� ������ �� ��ġ�� �ִ� ��ġ�̴�.
	// lastIndex ���� , findIndex + 1 �ε������� ���� ����ó�� ���ش�.
	static void putCurrent(long current){
		int findIndex = -1;
		int lastIndex = Math.min(51, gSize + 1);
		
		for(int i=0;i<lastIndex;i++){
			// i ��ġ�� current �� ��ġ��Ų��.
			if(g[i] < current){
				findIndex = i;
			}
		}
		
		for(int i=lastIndex;i>findIndex;i--){
			g[i+1] = g[i];
		}
		
		g[findIndex] = current;
	}
}
