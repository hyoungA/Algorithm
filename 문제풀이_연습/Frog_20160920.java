import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
 * 
문제

2차원 평면의 호수에 개구리 한 마리가 있다. 
개구리는 호수위에 떠있는 연꽃들 사이를 돌아다니고 있다. 
개구리는 총 4가지 방향으로 이동이 가능하다. 현재 위치인, (x, y)에서 임의의 양의 정수 d에 대하여, 

A. (x+d, y+d)로 이동.
B. (x+d, y-d)로 이동.
C. (x-d, y+d)로 이동.
D. (x-d, y-d)로 이동. 

가능하다.
개구리는 네 방향 중 한가지를 골라서, 그 방향에 있는 가장 가까운 연꽃으로 이동한다. 만약 연꽃이 없다면 제자리에 있는다. 그리고 개구리가 이동한 다음에, 원래 연꽃은 가라앉는다.
연꽃의 위치와, 개구리의 이동방향이 주어졌을 때, 개구리의 최종 위치를 출력하시오.

입력
첫 번째 줄에 연꽃의 수 N과 점프의 수 K가 공백으로 분리되어 주어진다. (1 ≤ N ≤ K ≤ 100,000)
두 번째 줄에는 개구리가 고른 방향 K개가 주어진다. 이 방향은 ‘A’, ‘B’, ‘C’, ‘D’로만 이루어져 있다.
세 번째 줄부터 N개의 줄에 걸쳐서 각 연꽃의 좌표 x, y가 주어진다. (0 ≤ x, y ≤ 1,000,000,000) 개구리는 입력에서 처음 주어지는 연꽃위에 있다.

출력
개구리의 점프가 끝났을 때, 개구리의 좌표를 출력한다.

힌트


예제 입력
7 5
ACDBB
5 6
8 9
4 13
1 10
7 4
10 9
3 7


예제 출력
7 4

Think
- N,K 가 100000 개 이하이므로 배열로 관계를 만들기 어려움
- 입력받을 때 available 을 저장해둔다.
- 이동하면서 가능한 연꽃이 사라질 수도 있으므로, 사전에 관계도를 만들어 놓는 것은 비효율적
- 정렬?
5 6
1 10
3 7
4 13
7 4
8 9
10 9

Solution
- 2차원을 1차원으로 .. -> 잠시 다른 문제로 생각해 보면..  
  - 삭제, 삽입 O(1) 인 자료구조 : LinkedList 
  - 정렬한 후에 인접한 점들을 링크드리스트로 만든다.?
- x, y 를 x + y 를 기준으로 정렬 한다.
- x, y 를 x - y 를 기준으로 정렬 한다.

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
		
		// 탐색하면서 동일한 합에 대해서 x, y 를 연결해준다.
		Node s = l.get(0);
		int sum = s.x + s.y;
		
		for(int i=1;i<l.size();i++){
			Node n = l.get(i);
			
			// 같은 크기 == 동일한 직선
			if(sum == n.x + n.y){
				// prev 는 상대적으로 x 값이 작은 값
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
		
		// 탐색하면서 동일한 합에 대해서 x, y 를 연결해준다.
		s = l.get(0);
		int diff = s.x - s.y;
		
		for(int i=1;i<l.size();i++){
			Node n = l.get(i);
			
			// 같은 크기 == 동일한 직선
			if(diff == n.x - n.y){
				// prev 는 상대적으로 x 값이 작은 값
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
	
	// 노드 s 에서 command 에 따라서 이동하는 함수 
	static public void move2(String command, Node s){
		Node n = null; // next 다음
		s.available = false;
		
		// x, y 에서 command 대로 이동한다.
		for(int i = 0; i<command.length(); i++){
			char com = command.charAt(i);
			
			switch (com) {
			case 'A':
				// 우 상향으로 확인 현재 x 보다 큰 값 중에서 기울기가 1인 node 를 탐색한다.
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
				// 우 하향으로 확인 현재 x 보다 큰 값 중에서 기울기가 -1인 node 를 탐색한다.
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
				// 좌 상향으로 확인 현재 x 보다 작은 값 중에서 기울기가 -1인 node 를 탐색한다.	
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
				// 좌 하향으로 확인 현재 x 보다 작은 값 중에서 기울기가 1인 node 를 탐색한다.	
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
