import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 
문제

길이 N의 수열이 주어진다. 이 수열의 초기값은 1, 2, 3, ..., N이다. 
그런데 이 수열의 변경이 빈번히 일어나고, 그런 도중에 어떤 연속된 부분의 합을 구하려 한다. 
만약 N이 5인 경우를 생각하자. 초기에는 1, 2, 3, 4, 5 가 된다. 
이 상황에서 3번째 수를 9로 변경하고 4번째 수를 10으로 변경하면 1, 2, 9, 10, 5가 된다. 
이 때, 2번째부터 5번째까지 합을 구하라고 한다면 26을 출력하면 되는 것이다. 
또, 이 상태에서 1번째 수를 -5로 변경하고, 3번째 수를 5로 변경하면 -5, 2, 5, 10, 5가 된다. 
그 다음, 1번째부터 3번째까지 합을 구하라고 한다면 2가 된다.

이러한 질의를 해결하는 프로그램을 작성하시오.

입력
첫 번째 줄에 정수의 개수 N이 주어진다. (1 ≤ N ≤ 100,000)
두 번째 줄에 질이의 개수 Q가 주어진다. (1 ≤ Q ≤ 200,000)
세 번째 줄부터 Q개의 줄에 걸쳐 질의의 정보가 주어진다. 각 질의는 다음 형태로 이루어진다. 
- 0 x y : x번째 수를 y로 변경한다. (1 ≤ x ≤ N, -100,000 ≤ y ≤ 100,000)
- 1 x y : x번째 수부터 y번째 수까지의 합을 구한다. (1 ≤ x ≤ y ≤ N)

출력
질의 중 1로 시작하는 질의에 대해, 구한 합을 한 줄에 하나씩 출력한다. 이 때, 답의 범위가 32-bit 정수형의 범위를 초과할 수 있음에 유의하여라.

입력 예제
5
7
1 2 4
0 3 9
0 4 10
1 2 5
0 1 -5
0 3 5
1 1 3


출력 예제
9
26
2

Solution
- 어려운 문제
- 구간을 관리하는 트리에 관한 자료구조가 필요
  Segment Tree
  Interval Tree
  Indexed Tree ( 코딩이 쉽다 )
    - 1번 노드는 루트
    - 부모 노드의 번호 : i/2
    - 왼쪽 자식의 번호 : i*2
    - 오른쪽 자식의 번호 : i*2 + 1
    - 값 갱신 구현 : 하나의 값이 변경되면, 부모노드의 값을 갱신한다.

Tip
- n>>1 은 n/2 와 같다. 좀더 빠름
- 구현할 때, 완전 2진 트리로 구현하는 것이 편하므로, 나머지 구간을 0으로 초기화해서 구현한다.
- 처음에는 0으로 초기화 되어 있다고, 생각하고 들어가는 값으로 초기화 시켜 주는 방법도 있고..
- 총 10만개 이므로, 2^17 개로 고정해놓고, 값을 채워서 구현할 수도 있다.
- maximum 사이즈의 4배 사이즈로 잡으면 된다. 여기선 100000 * 4


Think
- 배열의 크기는 N 에 대해서 2^x 과 같거나 작으면 2^(x+1)
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
		
		// 1 ~ N 까지 트리구성
		// 각 값을 합산한 결과를 배열에 구성한다.
		int size = getSizeOfTree(N);
		
		leafStartIndex = (int)Math.pow(2, size);
//		indexedTree = new long[(int)Math.pow(2, size+1)];
//		indexedTree = new long[400001];
		
		// 리프값은 2^(size - 1) 부터 시작한다.
		// 초기화 
		for(int i = 0; i<N;i++){
			change(leafStartIndex + i, i + 1);
		}
		
		// 명령 입력받기
		for(int i=0;i<Q;i++){
			String[] line = br.readLine().split(" ");
			
			int[] com = new int[3];
			
			com[0] = Integer.parseInt(line[0]);
			com[1] = Integer.parseInt(line[1]);
			com[2] = Integer.parseInt(line[2]);
			
			// com[0] == 0 이면 갱신
			if(com[0] == 0){
				// 인덱스, 값
				change(leafStartIndex + com[1] - 1, com[2]);
			}
			// com[0] == 1 이면 구간합 구하기
			// 인덱스를 트리에서 찾도록 변형해주어야 한다.
			else {
//				System.out.println(getSum(leafStartIndex + com[1] - 1, leafStartIndex + com[2] - 1));
				long res = getSum(leafStartIndex + com[1] - 1, leafStartIndex + com[2] - 1);
				
				bw.write(String.valueOf(res) + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	// 갱신하는 함수
	static void change(int index, int value){
		indexedTree[index] = value;
		
		for(int i=index; i != 0; i /= 2){
			//System.out.println(i);
			// 짝수 인덱스이면, 우측에 값이 있으므로 부모노드에 갱신해준다.
			if(i%2 == 0){
				indexedTree[i/2] = indexedTree[i] + indexedTree[i+1];
			}
			// 홀수 인덱스이면, 좌측에 값이 있으므로 부모노드에 갱신해준다.
			else {
				indexedTree[i/2] = indexedTree[i-1] + indexedTree[i]; 
			}
		}
		
	}
	
	// 합을 구하는 함수
	static long getSum(int l, int r){
		long sum = 0;
		
		while(l<=r){
			
			// l 이 홀수인덱스(오른쪽 자식일 경우) 이면 그냥 값을 더해준다.
			if(l%2 == 1){
				sum += indexedTree[l++]; // l 을 다음 노드로 이동(아래에서 다시 그 부모로 이동)
			}
			
			if(r%2 == 0){
				sum += indexedTree[r--]; // r 을 이전 노드로 이동(아래에서 다시 그 부모로 이동)
			}
			
//			l /=2;
//			r /=2;
			
			l>>=1;
			r>>=1;
		}
		
		return sum;
	}
	
	// 배열의 크기를 구하는 함수
	// 총 N
	static int getSizeOfTree(int n){
		int c = 0;
		int r = 0; // 나머지
		
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
