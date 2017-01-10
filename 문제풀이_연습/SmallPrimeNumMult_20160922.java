import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
����
�μ��� 2, 3, 5, 7���� ������ ���ڵ��� '���� �Ҽ����� ����'�̶�� ��������. 
�̷� '���� �Ҽ����� ����'���� 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 24, 25, 27 ���� �ִ�. 
N��° '���� �Ҽ����� ����'�� �˾Ƴ��� ���α׷��� �ۼ�����.

�Է�
ù ��° �ٿ� �׽�Ʈ ���̽��� �� T�� �־�����. (1 �� T �� 100)
�� �׽�Ʈ ���̽��� ���� ù ��° �ٿ� N�� �־�����. (1 �� N �� 5842)

���
�� �׽�Ʈ ���̽��� ���� ù ��° �ٿ� N��° '���� �Ҽ����� ����'�� ����Ѵ�.

�Է� ����
5
1
11
100
1000
5842


��� ����
1
12
450
385875
2000000000

Think
- �ʱⰪ 1 �� ť�� �ְ� 2,3,5,7 �� ���Ѱ��� �ִ´�.
- ���� ���� ���� ���� 2,3,5,7 �� ���� ���� �ִ´�. ( ���� �켱���� ť�� �ʿ��� ) 
- K��° ������ ���� ����!
- �Է°��� ���ĵǾ� ������ ���� �� �����Ƿ�, �迭�� ���� �־�д�.
- ���⼭ ���� ���� �ߺ��Ǵ� ���� �� �� �����Ƿ�, ����ó�� ���ش�.
- ���� �Ҽ����� �����̶�� �ϴ� ���� ���� �� �ִ� ������ �۱� �������� ����

Solution
- �̸� 5842 ���� ����� �صд�.

 */
public class SmallPrimeNumMult_20160922 {
	static int T;
	static Queue<Integer> q;
	static int[] res; // ��������� �迭
	static int[] num = new int[]{2,3,5,7};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		q = new PriorityQueue<Integer>(5843);
		res = new int[5844];
		q.add(1);
		int count = 0;
		int saved = -1;

		for(int i=1;i<=T;i++){
			int target = sc.nextInt(); // target ��° ���� ã�´�.
			
			while(count <= target){
				int tmp = q.poll();
				
				if(tmp == saved){
					continue;
				}
				
				res[++count] = tmp;
				
				for(int j =0 ;j<4;j++){
					long calc = (long)tmp * num[j];
					if(calc <= Integer.MAX_VALUE ){
						q.add((int)calc);
					}
				}
				
				saved = tmp;
			}
			
			System.out.println(res[target]);
		}
	}
}
