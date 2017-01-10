import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 
문제 : 소수경로

형석이는 다음날 SDS에 강의를 하러 간다. 
하지만 아침에 일찍 일어나야 한다는 부담감에 고급 알람 시계를 준비하였다. 
최근에 화장실 사진찍기, 뺨 때리기, 냄새 뿌리기 등의 알람 시계가 나오고 있지만, 형석이는 강의를 위해 머리를 써야 끌 수 있는 알람을 준비하였다.

이 시계의 시간을 맞춰 놓고, 그 시간이 되어 울리기 시작하면, 4자리의 소수 2개가 고급 알람 시계에 표시된다. 
첫 번째 적혀 있는 수는 숫자를 변경할 수 있게 되어있다. 그럼 이제 알람을 끄는 방법은 간단하다. 
첫 번째 적혀 있는 소수를 두 번째 적혀 있는 소수로 변경하면 된다.
이때, 한번에 한자리만 변경할 수 있고, 한자리를 변경하였을 때, 변경된 수는 소수이어야 한다. 
또한 4자리 소수이기 때문에, 경로 중간에도 4자리를 유지해야한다. 
(즉, 1000이상의 소수) 형석이는 알람이 계속 울리는 것이 싫기 때문에, 최대한 빠르게 알람을 끄고 싶어한다. 
형석이를 도와 어떻게 하면 최소한의 단계로 첫 번째 소수를 두 번째 소수로 변경할 수 있는지 구하시오. 

예를 들어서 1033이라는 수를 8179로 변경한다면, 1033 1733 3733 3739 3779 8779 8179의 순서로 변경이 가능하다.

입력
첫 번째 줄에 테스트 케이스의 수 T가 주어진다. 
각 테스트 케이스의 첫 번째 줄에 고급 알람 시계에 적혀 있는 두 개의 소수가 공백으로 분리되어 주어진다. (1,000 ≤ 소수 ≤ 9,999)

출력
각 테스트 케이스마다 첫 번째 줄에 첫 번째 소수를 두 번째 소수로 변경하는 최소한의 단계수를 출력한다.


예제 입력
3
1033 8179
1373 8017
1033 1033


예제 출력
6
7
0

Think
- 1000 ~ 9999 까지 가능한 모든 소수에 대해서 값을 저장해둔다.
- 너비우선탐색으로 최소 경로를 찾는다.
  - 

Solution
- 너비우선탐색
- 소수체크하는 방법 ( 언제나 쓰는 방법 )
  1. Root 까지 나누어서 나누어 떨어지는 수가 있는지 체크하는 방법
  2. 에라토스테네스의 체
    - 1 부터 N 까지 모든 수가 소수인지 아닌지 체크
- Q에 넣는 방법

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
				
				// tmp 에서 한자리 변경을 통해서 갈수 있는 모든 소수를 담는다.
				int th = s / 1000; // 천
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
				
				// tmp 에서 한자리 변경을 통해서 갈수 있는 모든 소수를 담는다.
				start = th * 1000 + t * 10 + o * 1;
				for(int i = start;i < s/1000 * 1000 + 1000  && i<10000;i+=100){
					if(isPrimeNumber[i] && !check[i]){
						check[i] = true;
						q.add(new int[]{i, l+1 });
					}
				}
				
				// tmp 에서 한자리 변경을 통해서 갈수 있는 모든 소수를 담는다.
				start = th * 1000 + h * 100 +  o * 1;
				for(int i = start ;i< s/100 * 100 + 100  && i<10000;i+=10){
					if(isPrimeNumber[i] && !check[i]){
						check[i] = true;
						q.add(new int[]{i, l+1 });
					}
				}
				
				// tmp 에서 한자리 변경을 통해서 갈수 있는 모든 소수를 담는다.
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
				// 배수
				if(j != i && !(j==i && isPrimeNumber[j])){
					isPrimeNumber[j] = false;					
				}
				
				j = i*count;
				count++;
			}
		}
	}
}
