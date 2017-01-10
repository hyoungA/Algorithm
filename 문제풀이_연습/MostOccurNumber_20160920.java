import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/*
 * 
문제

N개의 정수가 주어진다. 이 중 가장 많이 등장하는 수를 구하시오. 만약 이런 수가 여러 개라면 작은 수를 출력하세요.

시간제한: 1초


입력
첫째 줄에는 정수의 개수 N이 주어진다. (1<=N<=1000000)
둘째 줄부터 N개 줄에 정수가 주어진다. 이 수의 절대값은 2^31 - 1 이하이다.

출력
가장 많이 등장하는 정수를 출력하시오.

힌트

입력 예제
4
1
2
3
3

출력 예제
3

Think
- 모든 수의 개수를 셀 배열 선언은 불가 ( 수의 절대값이 2^31 - 1 ) 
- 정렬을 활용?
- 범위는 -21 억 ~ 21 억

Solution
- 정렬을 통해서 해결
- 정렬하면 뭉치는데 많이 뭉쳐있는지 확인한다. 좌에서 우로 체크

 */
public class MostOccurNumber_20160920 {
	static int N;
	static int[] numbers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		
		for(int i=0;i<N;i++){
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(numbers);
		
		int minValue = numbers[0];
		int maxCount = 1;
		int tmpCount = 1;
		int tmpValue = numbers[0];
		
		for(int i = 1;i<N;i++){
			if(tmpValue == numbers[i]){
				tmpCount++;
			}
			else{				
				tmpValue = numbers[i];
				tmpCount = 1;
			}
			
			if(tmpCount > maxCount){
				maxCount = tmpCount;
				minValue = tmpValue;
			}
		}
		
		System.out.println(minValue);
	}
}
