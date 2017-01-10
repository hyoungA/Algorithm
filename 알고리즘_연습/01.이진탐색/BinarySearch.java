import java.util.Scanner;

/*

1. binary �Ϲ����� ����Ž��
2. reculsiveBinary ��͸� ����� ����Ž��

Think
- ����Ž���� �ϴ� �� ���ҵ��� ���ĵǾ� �ִٴ� �����Ͽ� �̷������.
- ���� ������ ���� Ž�� ������ ã���� �ϴ� ���� ���� ��쿡 ���� �����̴�.
    ���������� ���� ������, low > high �� �Ǿ� -1 �� �����ϵ��� �����Ѵ�.
- �� ����Ž�� �Լ��� 
- ������� ����Ž���� low, high �ε��� ������ �Ķ���ͷ� �޴´�. 


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
	
	// �Ϲ� ���̳ʸ� ��ġ - target ���� �ִ� �ε��� ����
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
	
	// ����� ���̳ʸ� ��ġ - target ���� �ִ� �ε��� ����
	static int reculsiveBinary(int low, int high, int target) {
		// baseCase
		// ������ ���� ���� ���
		if(low > high) return -1;
		
		int mid = (low + high)/2;
		
		if(numbers[mid] == target) return mid;
		else if(numbers[mid] < target) 
			return reculsiveBinary(mid +1, high, target);
		else 
			return reculsiveBinary(low, mid-1, target);
	}
}
