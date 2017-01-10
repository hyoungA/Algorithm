import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 
문제
삼성 통신연구소는 레이저를 이용한 새로운 비밀 통신 시스템 개발을 위한 실험을 하고 있다. 
실험을 위하여 일직선 위에 N개의 높이가 서로 다른 탑을 수평 직선의 왼쪽부터 오른쪽 방향으로 차례로 세우고, 각 탑의 꼭대기에 레이저 송신기를 설치하였다. 
모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고, 탑의 기둥 모두에는 레이저 신호를 수신하는 장치가 설치되어 있다. 
하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능하다. 
예를 들어 높이가 6, 9, 5, 7, 4인 다섯 개의 탑이 수평 직선에 일렬로 서 있고, 모든 탑에서는 주어진 탑 순서의 반대 방향(왼쪽 방향)으로 동시에 레이저 신호를 발사한다고 하자. 
그러면, 높이가 4인 다섯 번째 탑에서 발사한 레이저 신호는 높이가 7인 네 번째 탑이 수신을 하고, 
높이가 7인 네 번째 탑의 신호는 높이가 9인 두 번째 탑이, 
높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신을 한다. 
높이가 9인 두 번째 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신을 하지 못한다.

탑들의 개수 N과 탑들의 높이가 주어질 때, 각 각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내는 프로그램을 작성하라.

입력
첫 번째 줄에 탑의 수 N이 주어진다. (1 ≤ N ≤ 500,000)
두 번째 줄에 N개의 탑들의 높이가 직선상에 놓인 순서대로 하나의 빈칸을 사이에 두고 각각 주어진다. (1 ≤ 탑의 높이 ≤ 100,000,000)

출력
첫 번째 줄에 주어진 탑들의 순서대로 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호를 하나의 빈칸을 사이에 두고 출력한다. 
만약 레이저 신호를 수신하는 탑이 존재하지 않으면 0을 출력한다.

입력 예제
5
6 9 5 7 4

출력 예제
0 0 2 2 4

Think
- 이동하면서 maximum 값을 가지고 ( 따라서 값의 존재 여부를 알 수 있음 )

Solution
- 스택을 이용하면 편하다.
- 이동하면서 이전의 작은 탑은 의미가 없으므로 pop 해준다.

 */

public class Top_20160922 {
	static int N;
	static int[] numbers;
	static int[] res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N+1];
		res = new int[N+1];
		int maximum = Integer.MIN_VALUE;
		int maximumIndex = -1;
		
		
		String [] s = br.readLine().split(" ");
		
		for(int i =1;i<=N;i++){
			numbers[i] = Integer.parseInt(s[i-1]);
		}
		
		res[1] = 0;
		maximum = numbers[1];
		maximumIndex = 1;
		
		for(int i = 2; i<=N ;i++){
			if(numbers[i] > maximum){
				res[i] = 0;
				maximumIndex = i;
				maximum = numbers[i];
			}
			else {
				if(numbers[i] == maximum){
					res[i] = maximumIndex;
					maximumIndex = i;
				}
				// numbers[i] < maximum
				else{
					for(int j=i-1;j>=maximumIndex;j--){
						if(numbers[j] >= numbers[i]){
							res[i] = j;
							break;
						}
					}
				}
			}
		}
		
		for(int i=1;i<=N;i++){
			System.out.print(res[i] + " ");
		}
	}
}
