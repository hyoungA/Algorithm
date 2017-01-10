import java.util.Scanner;

/*
 * 
문제 : 이친수

0과 1로 이루어진 이진수 중 다음 성질을 만족하는 수를 이친수라고 한다.
- 이친수는 0으로 시작하지 않는다.
- 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.

예를 들면, 1, 10, 100 등이 이친수가 된다. 하지만 010이나 110은 각각 1, 2번 규칙에 위배되므로 이친수가 아니다. 
N이 주어졌을 때, N자리 이친수의 개수를 구하는 프로그램을 작성하시오.

입력

첫 번째 줄에 N이 주어진다. (1 ≤ N ≤ 90)

출력

첫 번째 줄에 N자리 이친수의 개수를 출력한다.

Solution
D[i][0] = i 자리수의 끝자리가 0 인 이친수의 개수
D[i][1] = i 자리수의 끝자리가 1 인 이친수의 개수

Tip
- D 배열을 잘 정의해야 함 : 문제 -> 소문제로 세분화
  - i 자리수의 이친수의 개수를 구한다.
- 초기값을 정한다
  - D[1][0] = 0
  - D[1][1] = 1
- 점화식
  D[i][0] = D[i-1][0] + D[i-1][1]
  D[i][1] = D[i-1][0]
 * 
 */

public class BinaryNumber_20160919 {
	static long[][] D = new long[91][91];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int in = sc.nextInt();
		
		D[1][0] = 0;
		D[1][1] = 1;
		
		for(int i = 2;i<=in;i++){
			D[i][0] = D[i-1][0] + D[i-1][1];
			D[i][1] = D[i-1][0];
		}
		
		System.out.println(D[in][0] + D[in][1]);
	}
}
