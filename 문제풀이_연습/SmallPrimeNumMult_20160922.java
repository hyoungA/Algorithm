import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
문제
인수로 2, 3, 5, 7만을 가지는 숫자들을 '작은 소수들의 곱셈'이라고 정의하자. 
이런 '작은 소수들의 곱셈'에는 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 24, 25, 27 등이 있다. 
N번째 '작은 소수들의 곱셈'을 알아내는 프로그램을 작성하자.

입력
첫 번째 줄에 테스트 케이스의 수 T가 주어진다. (1 ≤ T ≤ 100)
각 테스트 케이스에 대해 첫 번째 줄에 N이 주어진다. (1 ≤ N ≤ 5842)

출력
각 테스트 케이스에 대해 첫 번째 줄에 N번째 '작은 소수들의 곱셈'을 출력한다.

입력 예제
5
1
11
100
1000
5842


출력 예제
1
12
450
385875
2000000000

Think
- 초기값 1 을 큐에 넣고 2,3,5,7 을 곱한값을 넣는다.
- 가장 작은 값을 빼서 2,3,5,7 을 곱한 값을 넣는다. ( 따라서 우선순위 큐가 필요함 ) 
- K번째 뽑히는 값이 정답!
- 입력값이 정렬되어 나오지 않을 수 있으므로, 배열에 값을 넣어둔다.
- 여기서 곱한 값이 중복되는 값이 들어갈 수 있으므로, 예외처리 해준다.
- 작은 소수들의 곱셈이라고 하는 것은 나올 수 있는 범위가 작기 때문으로 보임

Solution
- 미리 5842 개의 계산을 해둔다.

 */
public class SmallPrimeNumMult_20160922 {
	static int T;
	static Queue<Integer> q;
	static int[] res; // 결과저장할 배열
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
			int target = sc.nextInt(); // target 번째 값을 찾는다.
			
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
