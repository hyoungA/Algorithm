import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 
����
�Ｚ ��ſ����Ҵ� �������� �̿��� ���ο� ��� ��� �ý��� ������ ���� ������ �ϰ� �ִ�. 
������ ���Ͽ� ������ ���� N���� ���̰� ���� �ٸ� ž�� ���� ������ ���ʺ��� ������ �������� ���ʷ� �����, �� ž�� ����⿡ ������ �۽ű⸦ ��ġ�Ͽ���. 
��� ž�� ������ �۽ű�� ������ ��ȣ�� ��ǥ��� �����ϰ� ���� ������ ���� �������� �߻��ϰ�, ž�� ��� ��ο��� ������ ��ȣ�� �����ϴ� ��ġ�� ��ġ�Ǿ� �ִ�. 
�ϳ��� ž���� �߻�� ������ ��ȣ�� ���� ���� ������ �� �ϳ��� ž������ ������ �����ϴ�. 
���� ��� ���̰� 6, 9, 5, 7, 4�� �ټ� ���� ž�� ���� ������ �Ϸķ� �� �ְ�, ��� ž������ �־��� ž ������ �ݴ� ����(���� ����)���� ���ÿ� ������ ��ȣ�� �߻��Ѵٰ� ����. 
�׷���, ���̰� 4�� �ټ� ��° ž���� �߻��� ������ ��ȣ�� ���̰� 7�� �� ��° ž�� ������ �ϰ�, 
���̰� 7�� �� ��° ž�� ��ȣ�� ���̰� 9�� �� ��° ž��, 
���̰� 5�� �� ��° ž�� ��ȣ�� ���̰� 9�� �� ��° ž�� ������ �Ѵ�. 
���̰� 9�� �� ��° ž�� ���̰� 6�� ù ��° ž�� ���� ������ ��ȣ�� � ž������ ������ ���� ���Ѵ�.

ž���� ���� N�� ž���� ���̰� �־��� ��, �� ���� ž���� �߻��� ������ ��ȣ�� ��� ž���� �����ϴ����� �˾Ƴ��� ���α׷��� �ۼ��϶�.

�Է�
ù ��° �ٿ� ž�� �� N�� �־�����. (1 �� N �� 500,000)
�� ��° �ٿ� N���� ž���� ���̰� ������ ���� ������� �ϳ��� ��ĭ�� ���̿� �ΰ� ���� �־�����. (1 �� ž�� ���� �� 100,000,000)

���
ù ��° �ٿ� �־��� ž���� ������� ������ ž�鿡�� �߻��� ������ ��ȣ�� ������ ž���� ��ȣ�� �ϳ��� ��ĭ�� ���̿� �ΰ� ����Ѵ�. 
���� ������ ��ȣ�� �����ϴ� ž�� �������� ������ 0�� ����Ѵ�.

�Է� ����
5
6 9 5 7 4

��� ����
0 0 2 2 4

Think
- �̵��ϸ鼭 maximum ���� ������ ( ���� ���� ���� ���θ� �� �� ���� )

Solution
- ������ �̿��ϸ� ���ϴ�.
- �̵��ϸ鼭 ������ ���� ž�� �ǹ̰� �����Ƿ� pop ���ش�.

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
