import java.util.Scanner;

/*

1. binary 일반적인 이진탐색
2. reculsiveBinary 재귀를 사용한 이진탐색

Think
- 이진탐색은 일단 각 원소들이 정렬되어 있다는 가정하에 이루어진다.
- 먼저 생각할 점은 탐색 구간에 찾고자 하는 값이 없을 경우에 대한 가정이다.
    최종적으로 값이 없으면, low > high 가 되어 -1 을 리턴하도록 설계한다.
- 두 이진탐색 함수는 
- 재귀적인 이진탐색은 low, high 인덱스 구간을 파라미터로 받는다. 


 */
public class BinarySearch {
	
	static int[] numbers = {1,2,3,5,9,20,50,100,300,50000,4320000};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true){
			int t = sc.nextInt();
			
			if(t < 0) return;
			else {
				System.out.println("Result1 : " + binary(t));
				System.out.println("Result2 : " + reculsiveBinary(0, 9, t));
			}
		}
	}
	
	// 일반 바이너리 서치 - target 값이 있는 인덱스 리턴
	static int binary(int target) {
		int low = 0;
		int high = 9;
		
		while(low <= high){
			int mid = (low + high)/2;
			
			if(numbers[mid] == target){
				return mid;
			}
			else if(numbers[mid] < target ) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}
		
		return -1;
	}
	
	// 재귀적 바이너리 서치 - target 값이 있는 인덱스 리턴
	static int reculsiveBinary(int low, int high, int target) {
		// baseCase
		// 구간에 답이 없는 경우
		if(low > high) return -1;
		
		int mid = (low + high)/2;
		
		if(numbers[mid] == target) return mid;
		else if(numbers[mid] < target) 
			return reculsiveBinary(mid +1, high, target);
		else 
			return reculsiveBinary(low, mid-1, target);
	}
}
