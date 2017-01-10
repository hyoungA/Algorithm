import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 
���� : ������ ������ ����

������������ N���� ����� �ִ�. ���ǻ� �� ������� 1������ N������ ��ȣ�� �ű⵵�� ����. 
ó�� �� ������� ���θ� �𸣱� ������, '���� ����'�� ���� �ִ�. 
������ ���������� '���� ����'�� ��ư� ���� ���� ���̴�. �� ������ ������� �� �θ� '���� ����'�� �α�� �Ͽ���. 
�翬�� � ��� A�� B�� ���� ���踦 ������, �ڿ������� A�� ���͵�� B�� ���͵鵵 ���� ���� ���踦 �ΰ� �ȴ�. 
�̷� ������� �������ٺ��� ���� �� ������ ������ ���� ���谡 �����ǰ� �Ǿ���. ���� ������ ���� �������� Ȯ���� �˱� ����, �̷� ���踦 ã�Ƴ��� ���α׷��� �ۼ��ϱ�� �Ͽ���.

���� ���踦 ���� �˾Ƴ��� ���α׷��� �ۼ��Ͻÿ�.

�Է�
ù ��° �ٿ� ���������� ����� �� N�� �־�����. (1 �� N �� 100,000)
�� ��° �ٿ� ������ �� Q�� �־�����. (1 �� Q �� 200,000)
�� ��° �ٺ��� Q���� �ٿ� ���� ���ǰ� �־�����. �� ���Ǵ� ������ ���� �� �ϳ��� �־�����. (1 �� a, b �� N)
- 0 a b : a�� ����� b�� ����� ���� ���踦 �ξ����� �ǹ��ϴ� �����̴�.
- 1 a b : a�� ����� b�� ����� ���� ���迡 �ִ��� ����� �����̴�.

���
1�� �����ϴ� ��� ���ǿ� ����, �� �ٿ� �ϳ��� ���� ���谡 �ƴϸ� 0, ���� �����̸� 1�� ����Ѵ�.

�Է� ����
7 
9
0 1 3
1 1 7
0 7 6
1 3 7
0 3 7
0 4 2
0 1 3
1 1 7
1 1 1


��� ����
0
0
1
1

Think
- �ּҿ��� ��� ������� root �� �������� �����Ѵ�.
- �θ� ������ �迭��, level(depth) �� ������ �迭�� ����Ѵ�.
- ������ ������ �ϸ� ���� ����, �ڱ� �ڽ��� root �� ã�´�. 
  - root �� ã���� �̵��ϸ鼭, ���� ����Ʈ �鿡 ���ؼ� ������ �ִٰ� �θ��� ���� �� root �� �������ش�.
- ������ �δ� ����� root �� ã�´�.
  - root �� ã���� �̵��ϸ鼭, ���� ����Ʈ �鿡 ���ؼ� ������ �ִٰ� �θ��� ���� �� root �� �������ش�.
- ���� root �� �����ϸ�, �̹� ���� ���� �̹Ƿ� ��� ����
- ���� root �� �ٸ���, �� root �� depth �� ���� �� root ���� depth �� ū �� root �� �θ� �������ش�. 
   �ٽ� depth �� Max[ Adepth + 1, Bdepth ] �̴�.

 */
public class Clan_20160922 {
	static int N, Q; // ��� �� , ����
	static int[] people;
	static int[] parent;
	static int[] depth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		
		N = Integer.parseInt(br.readLine()); // ��� 
		Q = Integer.parseInt(br.readLine()); // ����
		
		// �ʱ�ȭ
//		people = new int[N + 1];
		parent = new int[N + 1]; // �ε����� ����� ��ȣ, ���� parent
		depth = new int[N + 1]; // depth �� �ʱ� ���� 0 �̹Ƿ� �н�
		
		for(int i=1;i<=N;i++){
			parent[i] = i;
		}
		
		// ���� �Է� �޾� ���ϱ�
		for(int i=0;i<Q;i++){
			String line = br.readLine();
			String[] tmp = line.split(" ");
			
			int command = Integer.parseInt(tmp[0]); // 0 : ����, 1 : ���� 
			int a = Integer.parseInt(tmp[1]); // ���� �δ� ��
			int b = Integer.parseInt(tmp[2]); // ���� ���
			
			if(command == 1){
				// ���� ���� üũ
				if(find(a) == find(b)){
					System.out.println(1);
				}
				else {
					System.out.println(0);
				}
			}
			else {
				// ���� �α�
				union(a,b);
			}
		}
	}
	
	// root ã�� �Լ�
	static int find(int x){
		if(parent[x] == x){
			return parent[x];
		}
		else {
			return find(parent[x]);
		}
	}
	
	// ���� �α�
	static void union(int a, int b){
		int aParent = find(a);
		int bParent = find(b);
		
		if(aParent == bParent)
			return;
		
		if(depth[aParent]<depth[bParent]){
			parent[aParent] = bParent;
		}
		else{
			parent[bParent] = aParent;
			depth[aParent]++;
		}
	}
}
