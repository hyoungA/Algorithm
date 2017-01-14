import java.util.Scanner;

/*

문제
주어진 구간 (시작인덱스 끝 인덱스) 의 최대값 최소값의 차이를 구한다.

구간 트리의 구현
- 배열 크기는 계산하기 복잡하므로 N * 4 로 잡자!
- 배열값이 주어졌을 때, 구간의 최소값, 최대값, 합 등을 구할 때 활용한다.
- 좌측 자식의 인덱스는 2 * 노드인덱스, 우측 자식의 인덱스는 2 * 노드인덱스 + 1 이 된다.
- 재귀로 구현을 할 때는, 좌측 우측을 잘 탐색한다. 최소값의 경우나 합의 경우는 어찌됬건 미리 구성해둔 구간값들의 합이다.

 */
public class RangeTree_20170110 {
	static int T,N,Q;
	static int[] h;
	static int[] hReverse;
	static int[] rangeMin;
	static int[] rangeMax;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int testCase = 1;testCase <= T; testCase++){
			
			N = sc.nextInt(); // 노드의 수
			Q = sc.nextInt(); // 질의의 수
			
			h = new int[N];       // 입력값의 배열
			hReverse = new int[N];// 입력값의 (음수) 배열
			int arraySize = (int)Math.pow(2, Math.ceil(Math.log(N)) + 1) * 2; // 배열의 크기
			
			rangeMin = new int[arraySize]; // 최소값을 저장할 배열
			rangeMax = new int[arraySize]; // 최대값을 저장할 배열
			
			for(int i=0;i<N;i++){
				h[i] = sc.nextInt();
				hReverse[i] = -h[i];
			}
			
			init(h, rangeMin, 1,0,N-1); // 1 1 3 1 2
			init(hReverse, rangeMax, 1,0,N-1); // 3 2 3 1 2
			
			//System.out.println("init complete!");
			
			// query - 주어진 구간에서의 최소와 최대값의 차이 구하기
			// 예를 들어서 0 ~ 2 구간이라면, left = 0, right = 2 구간에 해당사항이 없으면 Inf 리턴.
			for(int i=0;i<Q;i++){
				int s = sc.nextInt();
				int e = sc.nextInt();
				
				int min = query(s,e,1,0,N-1, rangeMin);
				int max = -query(s,e,1,0,N-1, rangeMax);
				int result = max - min;
				
				System.out.println(result);
			}			
		}
	}
	
	// heights 에서 최소값 또는 최대값을 잡아서 rangeValue 에 트리 값을 저장한다.
	static public int init(int[] heights, int[] rangeValue, int nodeIndex, int from, int to){
		if(from == to){
			return rangeValue[nodeIndex] = heights[from];
		}
		else {
			int mid = (from + to) / 2;
			
			int left  = init(heights, rangeValue, 2*nodeIndex    , from , mid);
			int right = init(heights, rangeValue, 2*nodeIndex + 1, mid+1,  to);
			
			return rangeValue[nodeIndex] = Math.min(left, right);
		}
	}
	// left 찾는구간의 왼쪽
	// right 찾는구간의 오른쪽
	// node 
	// nodeLeft
	// nodeRight
	// rangeValue
	static int query(int left, int right, int node, int nodeLeft, int nodeRight, int[] rangeValue) {
		// 교집합이 없으면, 최대값을 리턴
		if(right < nodeLeft || left > nodeRight) return 30000;
		
		// 교집합이 있으면, 최소값을 찾아서 리턴한다.
		else {
			if(left <= nodeLeft && nodeRight <= right) {
				return rangeValue[node];
			}
			else {
				int mid = (nodeLeft + nodeRight) / 2;
				return Math.min(query(left, right, 2*node    , nodeLeft , mid  , rangeValue),
				query(left, right, 2*node + 1, mid + 1  , nodeRight, rangeValue));
			}
		}
		
	}
	
	// update 의 구현
	// update 는 일단 node 의 값이 변경되면, rangeMin 의 값도 변경해주어야 한다.
	// 마찬가지로 최상단부터 ( 1번 노드부터.. ) 갱신하려는 노드 범위에 해당하면, 비교하여 최소값을 갱신해준다.
}
