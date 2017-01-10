import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/*
 * 폭격

시간 제한
250 개의 Test Input 입력시 C/C++ 10초 / Java 11초 

메모리 제한
Stack : 1 Mbytes  /  Total : 256 Mbytes

제출횟수 제한
10 회

채점
답안을 제출하면 Test Input에 대한 결과를 판정해서 실시간으로 알려주며 
그 의미는 다음과 같다.
Pass : Test Input에 대하여 모두 정답 처리된 경우
Fail : Test Input에 대하여 일부 혹은 모두 오답 처리된 경우
※ Test Input : 검정 시스템에서 제출된 코드 실행 時 자동으로 입력되는 Input Data

평가기준 
정답 처리된 Test Input의 개수

1x1 크기의 정사각형이 가로 N행, 세로 N 열로 이루어진 지역에 적군과 아군이 배치되어 있다. 
아군을 지원하기 위해서, 이 지역에 폭격을 하기로 하였다. 한 정사각형은 비어 있거나, 아군 또는 적군이 차지하고 있다. 
폭격은 연속한 직사각형 영역에 대해 할 수 있으며, 정사각형이 폭격 영역에 들어가면 이 지역에 배치되어 있는 아군 또는 적군은 모두 전사한다. 
폭격을 할 수 있는 영역의 면적에는 제한이 없다. 

폭격의 성과는 (전사한 적군의 수) - (전사한 아군의 수)로 정의된다. 적군보다 아군을 더 많이 공격하게 되는 경우도 가능하다. 
또한, 성과와 별도로 다른 고려 사항이 있을 수 있기 때문에 최선의 성과 뿐 아니라 
성과가 상위 K 등인 (즉, 모든 가능한 폭격 방법과 그 성과를 내림차순으로 정렬하였을 때 K 등인) 영역을 구하고 그 성과를 구하면, 
최소한 이 만큼의 성과를 거둘 수 있다는 것을 알 수 있다.

예를 들어, 3 x 3 크기인 다음 영역을 고려하여 보자. 각 정사각형에 적군이 배치되어 있다면 
적군의 수를 양수로, 아군이 배치되어 있다면 아군의 수를 음수로, 아무도 없다면 0으로 표현한다. 

이 지역에서 폭격으로 가장 높은 성과를 얻기 위해서는 다음과 같이 폭격을 하면 0 + 3 + 5 + 0 = 8의 성과를 얻을 수 있다. 
두 번째로 높은 성과를 얻기 위해서는 다음과 같이 폭격을 하면 1 + 0 + 3 - 2 + 5 + 0 = 7의 성과를 얻을 수 있다.
지역의 적군과 아군의 배치 상황이 주어졌을 때, K번째 폭격 성과를 구하는 프로그램을 작성하시오.

[입력] 
입력의 첫 줄에는 테스트 케이스의 수가 주어진다. 각 테스트의 첫째 줄에는 지역의 크기를 나타내는 정수 N과 등수를 나타내는 정수 K가 주어진다. 
(1 ≤ N ≤ 100, 1 ≤ K ≤ N) 그 다음 N 줄에는 해당 행에 해당하는 아군과 적군의 배치가 사이에 하나의 공백을 두고 N개의 정수로 주어진다. 
한 칸에 들어올 수 있는 값은 -1,000부터 1,000 사이이다. 

[출력] 
각 테스트 케이스의 답을 순서대로 표준출력으로 출력하며, 각 케이스마다 줄의 시작에 “#x”를 출력하여야 한다. 
이 때 x는 케이스의 번호이다. 같은 줄에 K 번째 폭격 성과를 자연수로 출력한다. 

[입출력 예]

입력 
3                   ←  총 3개의 케이스
2 1                ←   첫번째 케이스
2 2
2 2
3 2                ←   두번째 케이스 
1 0 3 
-2 5 0
0 -1 0
4 3                ←  세번째  케이스, 예제의 경우 
1 1 1 1 
1 1 1 1 
1 1 1 1 
1 1 1 1

출력

#1 8
#2 7
#3 12

Think


Solution
- 세금 문제와 유사하며 이를 세금 문제로 변형할 수 있다.
- 시작점 l 과 종료점 r 을 l 과 r   

 */
public class MockTest_20160921 {
	static int T,N,K;
	static int[][] in;
	static int[][] D; // 산출 결과 저장할 변수
	static Queue<Integer> q; // 결과값 뽑아낼 우선순위 큐
	
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner sc = new Scanner(System.in);
		//System.setIn(new FileInputStream("C:\\sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int testCase = 1; testCase <= T ;testCase ++){
			N = sc.nextInt();
			K = sc.nextInt(); // 등수
			
			in = new int[N+1][N+1];
			D = new int[N+1][N+1];
			
			q = new PriorityQueue<Integer>(N, new Comparator<Integer>(){
				@Override
				public int compare(Integer arg0, Integer arg1) {
					// TODO Auto-generated method stub
					return arg1 - arg0;
				}
			});
			
			// 입력 받기
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					in[i][j] = sc.nextInt();
					q.add(in[i][j]);
				}
			}
			
			// 계산 결과 값 저장 배열
			for(int indexMove = 1;indexMove<=N;indexMove++){
				for(int i=indexMove;i<=N;i++){
					for(int j=indexMove;j<=N;j++){
						// 자기자신, 위 더한 값, 좌 더 한 값 
						
//						if(indexMove == 1){
//							D[i][j] = D[i-1][j] + D[i][j-1] - D[i-1][j-1] + in[i][j];
//							q.add(D[i][j]);
//						}
//						else {
//							
//							int tmp = Math.max(in[i][j], D[i-1][j] + D[i][j-1] - D[i-1][j-1] + in[i][j]);
//							
//							if(tmp > D[i][j]){
//								D[i][j] = tmp;
//								q.add(tmp);
//							}
//							
//							q.add(in[i][j] + in[i-1][j]);
//							q.add(in[i][j] + in[i][j-1]);
//						}
						
						int tmp = Math.max(in[i][j], D[i-1][j] + D[i][j-1] - D[i-1][j-1] + in[i][j]);
						
						if(tmp > D[i][j]){
							D[i][j] = tmp;
							q.add(tmp);
						}
						
						q.add(in[i][j] + in[i-1][j]);
						q.add(in[i][j] + in[i][j-1]);
					}
				}
				
//				for(int n = 1;n<=N;n++){
//					for(int m = 1;m<=N;m++){
//						System.out.print(D[n][m] + " ");					
//					}
//					System.out.println();
//				}
			}
			
			for(int i=0;i<K;i++){
				int result = q.poll();
				if(i == K-1){
					System.out.println("#" + testCase + " " + result);
					break;
				}
			}
		}
	}
}
