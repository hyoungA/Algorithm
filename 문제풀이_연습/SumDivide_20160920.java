import java.io.BufferedInputStream;
import java.io.BufferedReader;import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * 
문제 : 합분해

0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 프로그램을 작성하시오.
덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 또한 한 개의 수를 여러 번 쓸 수도 있다.

입력
첫 번째 줄에 두 정수 N, K가 공백으로 분리되어 주어진다. (1 ≤ N, K ≤ 200)

출력
첫 번째 줄에 답을 1,000,000,000으로 나눈 나머지를 출력한다.

예제 입력
20 2

예제 출력
21

Solution
- D[i][j] = i 개 더했을 때 j 를 만드는 가짓수
- 마지막으로 더한 숫자가 0, 1, 2...j 을 고려 : D[i-1][j], D[i-1][j-1], .. D[i-1][0]
- D[i][j] = D[i-1][j] + D[i-1][j-1] + D[i-1][j-2] + ...  

Tip
- aHb 의 식 ( 중복 허용한 가짓수 계산 ) 으로도 계산 할 수 있다.
  - kHn ( n + k -1 , n ) 
  
 */
public class SumDivide_20160920 {
	static int N,K;
	static int D[][]= new int[201][201];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String tmp[] = line.split(" ");
		
		N = Integer.parseInt(tmp[0]);
		K = Integer.parseInt(tmp[1]);
		
		D[0][0] = 1;
		for(int i = 1; i<=K;i++){
			for(int j = 0; j<=N;j++){
				if(j == 0){
					D[i][j] = 1;
				}
				else {
					D[i][j] = (D[i-1][j] + D[i][j-1]) % 1000000000;
				}
			}
		}
		
		System.out.println(D[K][N]);
	}
}
