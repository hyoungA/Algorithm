import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 
����

���� N�� ������ �־�����. �� ������ �ʱⰪ�� 1, 2, 3, ..., N�̴�. 
�׷��� �� ������ ������ ����� �Ͼ��, �׷� ���߿� � ���ӵ� �κ��� ���� ���Ϸ� �Ѵ�. 
���� N�� 5�� ��츦 ��������. �ʱ⿡�� 1, 2, 3, 4, 5 �� �ȴ�. 
�� ��Ȳ���� 3��° ���� 9�� �����ϰ� 4��° ���� 10���� �����ϸ� 1, 2, 9, 10, 5�� �ȴ�. 
�� ��, 2��°���� 5��°���� ���� ���϶�� �Ѵٸ� 26�� ����ϸ� �Ǵ� ���̴�. 
��, �� ���¿��� 1��° ���� -5�� �����ϰ�, 3��° ���� 5�� �����ϸ� -5, 2, 5, 10, 5�� �ȴ�. 
�� ����, 1��°���� 3��°���� ���� ���϶�� �Ѵٸ� 2�� �ȴ�.

�̷��� ���Ǹ� �ذ��ϴ� ���α׷��� �ۼ��Ͻÿ�.

�Է�
ù ��° �ٿ� ������ ���� N�� �־�����. (1 �� N �� 100,000)
�� ��° �ٿ� ������ ���� Q�� �־�����. (1 �� Q �� 200,000)
�� ��° �ٺ��� Q���� �ٿ� ���� ������ ������ �־�����. �� ���Ǵ� ���� ���·� �̷������. 
- 0 x y : x��° ���� y�� �����Ѵ�. (1 �� x �� N, -100,000 �� y �� 100,000)
- 1 x y : x��° ������ y��° �������� ���� ���Ѵ�. (1 �� x �� y �� N)

���
���� �� 1�� �����ϴ� ���ǿ� ����, ���� ���� �� �ٿ� �ϳ��� ����Ѵ�. �� ��, ���� ������ 32-bit �������� ������ �ʰ��� �� ������ �����Ͽ���.

�Է� ����
5
7
1 2 4
0 3 9
0 4 10
1 2 5
0 1 -5
0 3 5
1 1 3


��� ����
9
26
2

Solution
- ����� ����
- ������ �����ϴ� Ʈ���� ���� �ڷᱸ���� �ʿ�
  Segment Tree
  Interval Tree
  Indexed Tree ( �ڵ��� ���� )
    - 1�� ���� ��Ʈ
    - �θ� ����� ��ȣ : i/2
    - ���� �ڽ��� ��ȣ : i*2
    - ������ �ڽ��� ��ȣ : i*2 + 1
    - �� ���� ���� : �ϳ��� ���� ����Ǹ�, �θ����� ���� �����Ѵ�.

Tip
- n>>1 �� n/2 �� ����. ���� ����
- ������ ��, ���� 2�� Ʈ���� �����ϴ� ���� ���ϹǷ�, ������ ������ 0���� �ʱ�ȭ�ؼ� �����Ѵ�.
- ó������ 0���� �ʱ�ȭ �Ǿ� �ִٰ�, �����ϰ� ���� ������ �ʱ�ȭ ���� �ִ� ����� �ְ�..
- �� 10���� �̹Ƿ�, 2^17 ���� �����س���, ���� ä���� ������ ���� �ִ�.
- maximum �������� 4�� ������� ������ �ȴ�. ���⼱ 100000 * 4


Think
- �迭�� ũ��� N �� ���ؼ� 2^x �� ���ų� ������ 2^(x+1)
- 

 */
public class RangeSum_20160922 {
	static int N,Q;
	static long indexedTree[] = new long[400001];
	static int leafStartIndex;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		Q = Integer.parseInt(br.readLine());
		
		// 1 ~ N ���� Ʈ������
		// �� ���� �ջ��� ����� �迭�� �����Ѵ�.
		int size = getSizeOfTree(N);
		
		leafStartIndex = (int)Math.pow(2, size);
//		indexedTree = new long[(int)Math.pow(2, size+1)];
//		indexedTree = new long[400001];
		
		// �������� 2^(size - 1) ���� �����Ѵ�.
		// �ʱ�ȭ 
		for(int i = 0; i<N;i++){
			change(leafStartIndex + i, i + 1);
		}
		
		// ��� �Է¹ޱ�
		for(int i=0;i<Q;i++){
			String[] line = br.readLine().split(" ");
			
			int[] com = new int[3];
			
			com[0] = Integer.parseInt(line[0]);
			com[1] = Integer.parseInt(line[1]);
			com[2] = Integer.parseInt(line[2]);
			
			// com[0] == 0 �̸� ����
			if(com[0] == 0){
				// �ε���, ��
				change(leafStartIndex + com[1] - 1, com[2]);
			}
			// com[0] == 1 �̸� ������ ���ϱ�
			// �ε����� Ʈ������ ã���� �������־�� �Ѵ�.
			else {
//				System.out.println(getSum(leafStartIndex + com[1] - 1, leafStartIndex + com[2] - 1));
				long res = getSum(leafStartIndex + com[1] - 1, leafStartIndex + com[2] - 1);
				
				bw.write(String.valueOf(res) + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	// �����ϴ� �Լ�
	static void change(int index, int value){
		indexedTree[index] = value;
		
		for(int i=index; i != 0; i /= 2){
			//System.out.println(i);
			// ¦�� �ε����̸�, ������ ���� �����Ƿ� �θ��忡 �������ش�.
			if(i%2 == 0){
				indexedTree[i/2] = indexedTree[i] + indexedTree[i+1];
			}
			// Ȧ�� �ε����̸�, ������ ���� �����Ƿ� �θ��忡 �������ش�.
			else {
				indexedTree[i/2] = indexedTree[i-1] + indexedTree[i]; 
			}
		}
		
	}
	
	// ���� ���ϴ� �Լ�
	static long getSum(int l, int r){
		long sum = 0;
		
		while(l<=r){
			
			// l �� Ȧ���ε���(������ �ڽ��� ���) �̸� �׳� ���� �����ش�.
			if(l%2 == 1){
				sum += indexedTree[l++]; // l �� ���� ���� �̵�(�Ʒ����� �ٽ� �� �θ�� �̵�)
			}
			
			if(r%2 == 0){
				sum += indexedTree[r--]; // r �� ���� ���� �̵�(�Ʒ����� �ٽ� �� �θ�� �̵�)
			}
			
//			l /=2;
//			r /=2;
			
			l>>=1;
			r>>=1;
		}
		
		return sum;
	}
	
	// �迭�� ũ�⸦ ���ϴ� �Լ�
	// �� N
	static int getSizeOfTree(int n){
		int c = 0;
		int r = 0; // ������
		
		while(n/2 != 0){
			r = n%2;
			n = n/2 + r;
			c++;
		}
		
		if(r != 0){
			c++;
		}
		
		return c;
	}
}
