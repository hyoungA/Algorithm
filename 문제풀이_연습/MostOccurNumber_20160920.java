import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/*
 * 
����

N���� ������ �־�����. �� �� ���� ���� �����ϴ� ���� ���Ͻÿ�. ���� �̷� ���� ���� ����� ���� ���� ����ϼ���.

�ð�����: 1��


�Է�
ù° �ٿ��� ������ ���� N�� �־�����. (1<=N<=1000000)
��° �ٺ��� N�� �ٿ� ������ �־�����. �� ���� ���밪�� 2^31 - 1 �����̴�.

���
���� ���� �����ϴ� ������ ����Ͻÿ�.

��Ʈ

�Է� ����
4
1
2
3
3

��� ����
3

Think
- ��� ���� ������ �� �迭 ������ �Ұ� ( ���� ���밪�� 2^31 - 1 ) 
- ������ Ȱ��?
- ������ -21 �� ~ 21 ��

Solution
- ������ ���ؼ� �ذ�
- �����ϸ� ��ġ�µ� ���� �����ִ��� Ȯ���Ѵ�. �¿��� ��� üũ

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
