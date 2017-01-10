import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
 * 
����

2���� ����� ȣ���� ������ �� ������ �ִ�. 
�������� ȣ������ ���ִ� ���ɵ� ���̸� ���ƴٴϰ� �ִ�. 
�������� �� 4���� �������� �̵��� �����ϴ�. ���� ��ġ��, (x, y)���� ������ ���� ���� d�� ���Ͽ�, 

A. (x+d, y+d)�� �̵�.
B. (x+d, y-d)�� �̵�.
C. (x-d, y+d)�� �̵�.
D. (x-d, y-d)�� �̵�. 

�����ϴ�.
�������� �� ���� �� �Ѱ����� ���, �� ���⿡ �ִ� ���� ����� �������� �̵��Ѵ�. ���� ������ ���ٸ� ���ڸ��� �ִ´�. �׸��� �������� �̵��� ������, ���� ������ ����ɴ´�.
������ ��ġ��, �������� �̵������� �־����� ��, �������� ���� ��ġ�� ����Ͻÿ�.

�Է�
ù ��° �ٿ� ������ �� N�� ������ �� K�� �������� �и��Ǿ� �־�����. (1 �� N �� K �� 100,000)
�� ��° �ٿ��� �������� �� ���� K���� �־�����. �� ������ ��A��, ��B��, ��C��, ��D���θ� �̷���� �ִ�.
�� ��° �ٺ��� N���� �ٿ� ���ļ� �� ������ ��ǥ x, y�� �־�����. (0 �� x, y �� 1,000,000,000) �������� �Է¿��� ó�� �־����� �������� �ִ�.

���
�������� ������ ������ ��, �������� ��ǥ�� ����Ѵ�.

��Ʈ


���� �Է�
7 5
ACDBB
5 6
8 9
4 13
1 10
7 4
10 9
3 7


���� ���
7 4

Think
- N,K �� 100000 �� �����̹Ƿ� �迭�� ���踦 ����� �����
- �Է¹��� �� available �� �����صд�.
- �̵��ϸ鼭 ������ ������ ����� ���� �����Ƿ�, ������ ���赵�� ����� ���� ���� ��ȿ����
- ����?
5 6
1 10
3 7
4 13
7 4
8 9
10 9

Solution
- 2������ 1�������� .. -> ��� �ٸ� ������ ������ ����..  
  - ����, ���� O(1) �� �ڷᱸ�� : LinkedList 
  - ������ �Ŀ� ������ ������ ��ũ�帮��Ʈ�� �����.?
- x, y �� x + y �� �������� ���� �Ѵ�.
- x, y �� x - y �� �������� ���� �Ѵ�.

 * 
 */
public class Frog_20160920 {
	static int N,K;
	static int moveX;
	static int moveY;
	static int[][] nodeArray;
	static List<Node> l = new ArrayList<Node>();
	
	static class Node{
		int x;
		int y;
		boolean available;
		
		Node APrev;
		Node ANext;
		Node BPrev;
		Node BNext;
		
		Node (int x, int y){
			this.x = x;
			this.y = y;
			this.available = true;
		}
	};
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		sc.nextLine(); // carrige return
		
		String command = sc.nextLine(); // ACBDD
		Node start = null;
		//nodeArray = new int[N][3];
		
		int startX = 0;
		int startY = 0;
		
		for(int i = 0;i<N;i++){
			Node n;
			// startPoint
			if(i == 0){
				startX = sc.nextInt();
				startY = sc.nextInt();
				n = new Node(startX, startY);
				start = n;
			}
			else {
				//nodeArray[i][0] = sc.nextInt();
				//nodeArray[i][1] = sc.nextInt();
				n = new Node(sc.nextInt(), sc.nextInt());
			}
			
			l.add(n);
		}
		
		Collections.sort(l, new Comparator<Node>(){
			@Override
			public int compare(Node arg0, Node arg1) {
				// TODO Auto-generated method stub				
				return (arg0.x + arg0.y) - (arg1.x + arg1.y);
			}
		});
		
		// Ž���ϸ鼭 ������ �տ� ���ؼ� x, y �� �������ش�.
		Node s = l.get(0);
		int sum = s.x + s.y;
		
		for(int i=1;i<l.size();i++){
			Node n = l.get(i);
			
			// ���� ũ�� == ������ ����
			if(sum == n.x + n.y){
				// prev �� ��������� x ���� ���� ��
				if(s.x < n.x){
					n.BPrev = s;
					s.BNext = n;
				}
				else {
					s.BPrev = n;
					n.BNext = s;
				}
			}
			else {
				s = n;
				sum = s.x + s.y;
			}
		}
		
		Collections.sort(l, new Comparator<Node>(){
			@Override
			public int compare(Node arg0, Node arg1) {
				// TODO Auto-generated method stub				
				return (arg0.x - arg0.y) - (arg1.x - arg1.y);
			}
		});
		
		// Ž���ϸ鼭 ������ �տ� ���ؼ� x, y �� �������ش�.
		s = l.get(0);
		int diff = s.x - s.y;
		
		for(int i=1;i<l.size();i++){
			Node n = l.get(i);
			
			// ���� ũ�� == ������ ����
			if(diff == n.x - n.y){
				// prev �� ��������� x ���� ���� ��
				if(s.x < n.x){
					n.APrev = s;
					s.ANext = n;
				}
				else {
					s.APrev = n;
					n.ANext = s;
				}
			}
			else {
				s = n;
				diff = s.x - s.y;
			}
		}
		
		// move
		//move(command, startX, startY);
		move2(command, start);
		
		System.out.println(moveX + " " + moveY);
	}
	
	// ��� s ���� command �� ���� �̵��ϴ� �Լ� 
	static public void move2(String command, Node s){
		Node n = null; // next ����
		s.available = false;
		
		// x, y ���� command ��� �̵��Ѵ�.
		for(int i = 0; i<command.length(); i++){
			char com = command.charAt(i);
			
			switch (com) {
			case 'A':
				// �� �������� Ȯ�� ���� x ���� ū �� �߿��� ���Ⱑ 1�� node �� Ž���Ѵ�.
				n = s.ANext;
				
				while(n != null){
					if(n.available){
//						System.out.println(n.x + " " + n.y);
						moveX = n.x;
						moveY = n.y;
						n.available = false;
						s = n;
						break;
					}
					n = n.ANext;
				}
				
				break;
			case 'B':
				// �� �������� Ȯ�� ���� x ���� ū �� �߿��� ���Ⱑ -1�� node �� Ž���Ѵ�.
				n = s.BNext;				
				
				while(n != null){
					if(n.available){
//						System.out.println(n.x + " " + n.y);
						moveX = n.x;
						moveY = n.y;
						n.available = false;
						s = n;
						break;
					}
					n = n.BNext;
				}
				
				break;
			case 'C':
				// �� �������� Ȯ�� ���� x ���� ���� �� �߿��� ���Ⱑ -1�� node �� Ž���Ѵ�.	
				n = s.BPrev;
				
				while(n != null){
					if(n.available){
//						System.out.println(n.x + " " + n.y);
						moveX = n.x;
						moveY = n.y;
						n.available = false;
						s = n;
						break;
					}
					n = n.BPrev;
				}
				
				break;
			case 'D':
				// �� �������� Ȯ�� ���� x ���� ���� �� �߿��� ���Ⱑ 1�� node �� Ž���Ѵ�.	
				n = s.APrev;
				
				while(n != null){
					if(n.available){
//						System.out.println(n.x + " " + n.y);
						moveX = n.x;
						moveY = n.y;
						n.available = false;
						s = n;
						break;
					}
					n = n.APrev;
				}
				
				break;		

			default:
				break;
			}
		}
	}
}
