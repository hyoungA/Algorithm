import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
���� : �Ҽ����

�����̴� ������ SDS�� ���Ǹ� �Ϸ� ����. 
������ ��ħ�� ���� �Ͼ�� �Ѵٴ� �δ㰨�� ��� �˶� �ð踦 �غ��Ͽ���. 
�ֱٿ� ȭ��� �������, �� ������, ���� �Ѹ��� ���� �˶� �ð谡 ������ ������, �����̴� ���Ǹ� ���� �Ӹ��� ��� �� �� �ִ� �˶��� �غ��Ͽ���.

�� �ð��� �ð��� ���� ����, �� �ð��� �Ǿ� �︮�� �����ϸ�, 4�ڸ��� �Ҽ� 2���� ��� �˶� �ð迡 ǥ�õȴ�. 
ù ��° ���� �ִ� ���� ���ڸ� ������ �� �ְ� �Ǿ��ִ�. �׷� ���� �˶��� ���� ����� �����ϴ�. 
ù ��° ���� �ִ� �Ҽ��� �� ��° ���� �ִ� �Ҽ��� �����ϸ� �ȴ�.
�̶�, �ѹ��� ���ڸ��� ������ �� �ְ�, ���ڸ��� �����Ͽ��� ��, ����� ���� �Ҽ��̾�� �Ѵ�. 
���� 4�ڸ� �Ҽ��̱� ������, ��� �߰����� 4�ڸ��� �����ؾ��Ѵ�. 
(��, 1000�̻��� �Ҽ�) �����̴� �˶��� ��� �︮�� ���� �ȱ� ������, �ִ��� ������ �˶��� ���� �;��Ѵ�. 
�����̸� ���� ��� �ϸ� �ּ����� �ܰ�� ù ��° �Ҽ��� �� ��° �Ҽ��� ������ �� �ִ��� ���Ͻÿ�. 

���� �� 1033�̶�� ���� 8179�� �����Ѵٸ�, 1033 1733 3733 3739 3779 8779 8179�� ������ ������ �����ϴ�.

�Է�
ù ��° �ٿ� �׽�Ʈ ���̽��� �� T�� �־�����. 
�� �׽�Ʈ ���̽��� ù ��° �ٿ� ��� �˶� �ð迡 ���� �ִ� �� ���� �Ҽ��� �������� �и��Ǿ� �־�����. (1,000 �� �Ҽ� �� 9,999)

���
�� �׽�Ʈ ���̽����� ù ��° �ٿ� ù ��° �Ҽ��� �� ��° �Ҽ��� �����ϴ� �ּ����� �ܰ���� ����Ѵ�.


���� �Է�
3
1033 8179
1373 8017
1033 1033


���� ���
6
7
0

Think
- 1000 ~ 9999 ���� ������ ��� �Ҽ��� ���ؼ� ���� �����صд�.
- �ʺ�켱Ž������ �ּ� ��θ� ã�´�.
  - 

Solution
- �ʺ�켱Ž��
- �Ҽ�üũ�ϴ� ��� ( ������ ���� ��� )
  1. Root ���� ����� ������ �������� ���� �ִ��� üũ�ϴ� ���
  2. �����佺�׳׽��� ü
    - 1 ���� N ���� ��� ���� �Ҽ����� �ƴ��� üũ
- Q�� �ִ� ���

 */
public class PrimeNumberPath_20160919 {
	
	static int T;
	static int start, end;
	static boolean [] isPrimeNumber = new boolean[10000];
	static boolean [] check = new boolean[10000];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0;i<10000;i++){
			isPrimeNumber[i] = true;
		}
		
		makePrimeGrid();		
		
		T = sc.nextInt();
		for(int testCase=1;testCase<=T;testCase++){
			start = sc.nextInt();
			end = sc.nextInt();
			
			Queue<int[]> q = new LinkedList<int[]>();
			q.add(new int[]{start, 0});
			check = new boolean[10000];			
			check[start] = true;
			
			while(!q.isEmpty()){
				int[] tmp = q.poll();
				int s = tmp[0];
				int l = tmp[1];
				
				if(s == end){
					System.out.println(l);
					break;
				}
				
				// tmp ���� ���ڸ� ������ ���ؼ� ���� �ִ� ��� �Ҽ��� ��´�.
				int th = s / 1000; // õ
				int h = (s / 100) % 10;
				int t = (s / 10 ) % 10;
				int o = s % 10;
				
				int start = 1000 + h * 100 + t * 10 + o * 1;
				for(int i = start;i<10000;i+=1000){
					if(isPrimeNumber[i] && !check[i]){
						check[i] = true;						
						q.add(new int[]{i, l+1 });
					}
				}
				
				// tmp ���� ���ڸ� ������ ���ؼ� ���� �ִ� ��� �Ҽ��� ��´�.
				start = th * 1000 + t * 10 + o * 1;
				for(int i = start;i < s/1000 * 1000 + 1000  && i<10000;i+=100){
					if(isPrimeNumber[i] && !check[i]){
						check[i] = true;
						q.add(new int[]{i, l+1 });
					}
				}
				
				// tmp ���� ���ڸ� ������ ���ؼ� ���� �ִ� ��� �Ҽ��� ��´�.
				start = th * 1000 + h * 100 +  o * 1;
				for(int i = start ;i< s/100 * 100 + 100  && i<10000;i+=10){
					if(isPrimeNumber[i] && !check[i]){
						check[i] = true;
						q.add(new int[]{i, l+1 });
					}
				}
				
				// tmp ���� ���ڸ� ������ ���ؼ� ���� �ִ� ��� �Ҽ��� ��´�.
				start = th * 1000 + h * 100 + t * 10;
				for(int i = start ; i<s/10 * 10 + 10  && i<10000;i+=1){
					if(isPrimeNumber[i] && !check[i]){
						check[i] = true;
						q.add(new int[]{i, l+1 });
					}
				}
			}
		}
		
	}
	
	static void makePrimeGrid(){
		for(int i = 2;i<10000;i++){		
			int count = 2;
			
			for(int j=i;j<10000;){				
				// ���
				if(j != i && !(j==i && isPrimeNumber[j])){
					isPrimeNumber[j] = false;					
				}
				
				j = i*count;
				count++;
			}
		}
	}
}
