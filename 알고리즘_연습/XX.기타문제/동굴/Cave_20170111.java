import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*

문제 : 동굴

벌레 한 마리가 동굴을 지나려고 한다.
모두가 알다시피 동굴은 석순과 종유석이 굉장히 많은 공간이다.
이 벌레는 이렇게 장애물이 많은 동굴을 지나갈 것이다.
동굴의 길이는 N미터이고, 높이는 H미터이다.
N은 항상 짝수이고, 장애물은 석순과 종유석이 번갈아 가면서 등장하고, 첫 장애물을 항상 석순이다.

아래 예제는 N=14, H =5의 예이다.

남자다운 이 벌레는 장애물을 굳이 피하지 않는다. 즉, 처음 정한 높이에서 일직선으로 장애물을 부수면서 지나간다.
벌레가 아래에서 4번째 구간으로 지나가면, 아래 그림과 같이 8개의 장애물을 부수게 된다.

하지만, 첫 번째나 다섯 번째 구간으로 날아간다면 벌레는 7개의 장애물만 부시면 된다.

벌레는 아픔을 느끼는 남자이기 때문에, 최소한의 장애물만 부시고 싶어한다.
여러분은 이 벌레가 최소 몇 개의 장애물만 부수고도 통과할 수 있는지 구하고, 그러한 구간이 총 몇 개 있는지 구하는 프로그램을 작성하시오.


[입력]
첫 번째 줄에 테스트 케이스의 갯수 T가 주어진다. 이어서 차례로 T개 테스트 케이스가 주어진다. (1 ≤ T ≤ 10) 각 케이스의 첫 번째 줄에는 장애물의 수 N과 동굴의 높이 H가 주어진다. (2 ≤ N ≤ 200,000, 2 ≤ H ≤ 500,000) 이후 N개의 줄에는 장애물의 크기가 순서대로 주어진다. 장애물의 크기는 H보다 작은 양의 정수이다.




[출력]
각각의 테스트 케이스에 대하여 #x(x는 테스트 케이스 번호를 의미)를 출력하고 공백을 하나 둔 최소 개수의 장애물을 지날 때 지나는 장애물의 수와 그러한 구간의 수를 공백으로 구분하여 출력한다.




[입출력 예]
(입력)
2                                                   ← 2 test cases in total
6 6                                                ← 1st case
3
3
3
3
3
3
14 5                                              ← 2nd case
1
3
4
2
2
4
3
4
3
3
3
2
3
3


(출력)
#1 3 6
#2 7 2

Think
- 무식하게 생각하면 N 번 탐색을 H 번 수행하면 부딪치는 총 수를 알 수 있다.
  다만, 그렇게 되면 최악의 경우 200000 * 500000 = 10,000,000,000 ( 십억 ) 번 연산이 생겨서 시간초과가 생긴다.

- 시간초과를 줄이는 방법
  - 값을 입력받을 때 높이의 횟수를 카운팅한다. 석순과 종유석을 다른 배열에 일단 기록한다.
    왜냐하면, 석순은 높이가 3이라고 할 때, 높이 2에도 1에도 영향을 줄 수 있지만, 종유석은 높이 3을 받았을 때 천장쪽으로 영향을 주기 때문이다.
    
  ex) 높이가 5라 가정하면,  
       석순   : 높이 3이면, 3,2,1 인 높이에서도 충돌한다. -> 아래쪽 배열에 누적해야 한다.
       종유석 : 높이 3이면, 3,4,5 인 높이에서도 충돌한다. -> 위쪽 배열에 누적해야 한다.
       
    이렇게 하면, 석순기준 4 높이에서 부딪치는 횟수 = (3 높이에서 충돌 횟수  + 4 높이 충돌 횟수 ) 이 된다. 
    이런식으로 석순과 종유석 별로 각각 높이에서 부딪치는 횟수를 누적한다.
    
    여기서 계산횟수상의 이득을 얻는다. 
    4 높이에서 충돌한 횟수를 구할 때, N번을 탐색하는 것보다 아래를 산출해 내는 것이 더 빠르다.
      ( 석순을 3번 충돌한 횟수 + 4 높이의 석순의 수 ) + ( 4 높이 아래로 떨어지는 종유석의 수 ) 
   
   그리고 석순과 종유석에 부딪친 횟수를 합한 결과를 담은 배열에 결과를 넣는다.
   오름차순 정렬하여, 최소 부딪친 횟수와 경우의 수를 출력한다.
 */
public class Cave_20170111 {
	static int T,N,H;
	static int[] fromBottom;
	static int[] fromCeil;
	static int[] h;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testCase=1;testCase<=T;testCase++){
			String token[] = br.readLine().split(" ");
			
			N = Integer.parseInt(token[0]);
			H = Integer.parseInt(token[1]);
			
			fromBottom = new int[H+1]; // 석순
			fromCeil = new int[H+1]; // 종유석
			
			h = new int[H+1]; // 석순, 종유석의 높이에 따른 충돌 횟수의 합을 저장한 배열
			
			// N 개 입력받기 ( O(N/2) )
			for(int i=0;i<N/2;i++){
				int bot = Integer.parseInt(br.readLine());
				int ceil = Integer.parseInt(br.readLine());
				
				fromBottom[bot]++;
				fromCeil[H-ceil+1]++;
			}
			
			// bottom, ceil 정리
			for(int i=H;i>0;i--){
				fromBottom[i-1] += fromBottom[i];
				fromCeil[H-i+1] += fromCeil[H-i]; 
			}
			
			// 합계 담기
			for(int i=1;i<=H;i++){
				h[i] = fromBottom[i] + fromCeil[i];
			}
			
			// 오름차순 정렬 ( O(lgH) )
			Arrays.sort(h);
			
			int minCrash = h[1];
			int crashTimes = 1;
			
			for(int i=2;i<=H;i++){
				if(minCrash != h[i]){
					break;
				}
				else 
					crashTimes++;
			}
			
			
			System.out.println("#" + testCase + " " + minCrash + " " + crashTimes);
		}
	}
}
