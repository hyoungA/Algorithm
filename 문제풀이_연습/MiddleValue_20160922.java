import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 
문제
정수가 N개 주어진다. 홀수번째 수가 주어질 때마다, 지금까지 주어진 수의 중앙값을 구하는 프로그램을 작성하여라.
예를 들어 1, 4, 5, 3, 6가 주어진다면, 
첫번째 수인 1을 입력받을 때 중앙값이 1이고, 
세번째 수인 5를 입력받을 때까지의 중앙값이 4이고, 
다섯번째 수인 6을 입력받을 때까지의 중앙값이 4이므로 1, 4, 4를 순서대로 출력하는 것이다.

입력
첫 번째 줄에 주어지는 정수의 개수 N이 주어진다. (1 ≤ N ≤ 99,999, N은 홀수)
두 번째 줄부터 N개의 줄에 걸쳐 각 줄에 하나씩 정수가 주어진다. (1 ≤ 주어지는 정수 ≤ 1,000,000,000)

출력
홀수번째 수를 입력받을 때마다 그 때까지 입력받은 정수들의 중앙값을 한 줄에 하나씩 출력한다.

입력 예제
7
1 
9 
5 
3 
2 
8
8

출력 예제
1
5
3
5

Think
- 중앙값은 값을 순서대로 정렬했을 때, 가운데 오는 값
- sort 를 구현한다.

Solution
- 하나는 MaxHeap, 하나는 MinHeap 으로 관리.
- 값에 대해서, L, R 중 어디로 들어가는지 정한다.
- 기본적으로 R 이 L 보다 크면 안되고, L의 최대값은 R +1 개이다. ( L의 최대값이 답이 되도록 할 것이므로 ) 

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
		
		max.add(Integer.parseInt(br.readLine())); // 최초값을 넣어둔다.
		System.out.println(max.peek());
		
		// 입력을 받으면서 규칙에 따라서 넣어준다.
		// 규칙
		// 1. min Heap 의 사이즈가 max heap 보다 커지면, min Heap 에서 max Heap 으로 원소를 이동시킨다. ( 늘 결과값은 max Heap 에서 본 값이다 )
		// 2. max Heap 사이즈의 최대는 min Heap + 1 이다.
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
			
			// 홀수번째 값의 경우 (0, 2, 4, 6 번째 입력) 출력해준다.
			if(i%2 == 0){
				System.out.println(max.peek());
			}
		}
	}
}
