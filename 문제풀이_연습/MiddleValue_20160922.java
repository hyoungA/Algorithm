import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 
����
������ N�� �־�����. Ȧ����° ���� �־��� ������, ���ݱ��� �־��� ���� �߾Ӱ��� ���ϴ� ���α׷��� �ۼ��Ͽ���.
���� ��� 1, 4, 5, 3, 6�� �־����ٸ�, 
ù��° ���� 1�� �Է¹��� �� �߾Ӱ��� 1�̰�, 
����° ���� 5�� �Է¹��� �������� �߾Ӱ��� 4�̰�, 
�ټ���° ���� 6�� �Է¹��� �������� �߾Ӱ��� 4�̹Ƿ� 1, 4, 4�� ������� ����ϴ� ���̴�.

�Է�
ù ��° �ٿ� �־����� ������ ���� N�� �־�����. (1 �� N �� 99,999, N�� Ȧ��)
�� ��° �ٺ��� N���� �ٿ� ���� �� �ٿ� �ϳ��� ������ �־�����. (1 �� �־����� ���� �� 1,000,000,000)

���
Ȧ����° ���� �Է¹��� ������ �� ������ �Է¹��� �������� �߾Ӱ��� �� �ٿ� �ϳ��� ����Ѵ�.

�Է� ����
7
1 
9 
5 
3 
2 
8
8

��� ����
1
5
3
5

Think
- �߾Ӱ��� ���� ������� �������� ��, ��� ���� ��
- sort �� �����Ѵ�.

Solution
- �ϳ��� MaxHeap, �ϳ��� MinHeap ���� ����.
- ���� ���ؼ�, L, R �� ���� ������ ���Ѵ�.
- �⺻������ R �� L ���� ũ�� �ȵǰ�, L�� �ִ밪�� R +1 ���̴�. ( L�� �ִ밪�� ���� �ǵ��� �� ���̹Ƿ� ) 

 */
public class MiddleValue_20160922 {
	static int N;
	static Queue<Integer> max;
	static Queue<Integer> min;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		min = new PriorityQueue<>(N/2);
		max = new PriorityQueue<>(N/2, new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				// TODO Auto-generated method stub
				return arg1 - arg0;
			}
		});
		
		max.add(Integer.parseInt(br.readLine())); // ���ʰ��� �־�д�.
		System.out.println(max.peek());
		
		// �Է��� �����鼭 ��Ģ�� ���� �־��ش�.
		// ��Ģ
		// 1. min Heap �� ����� max heap ���� Ŀ����, min Heap ���� max Heap ���� ���Ҹ� �̵���Ų��. ( �� ������� max Heap ���� �� ���̴� )
		// 2. max Heap �������� �ִ�� min Heap + 1 �̴�.
		// 3. 
		for(int i =1;i<N;i++){
			int n = Integer.parseInt(br.readLine());
			
			if(max.peek() < n){
				min.add(n);
			}
			else {
				max.add(n);
			}
			
			if(min.size() > max.size()){
				max.add(min.poll());
			}
			
			else if(max.size() == min.size() + 2){
				min.add(max.poll());
			}
			
			// Ȧ����° ���� ��� (0, 2, 4, 6 ��° �Է�) ������ش�.
			if(i%2 == 0){
				System.out.println(max.peek());
			}
		}
	}
}
