import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 
����

�ֱٿ� �����̴� ���࿡ ������ ���� ���� ���� �Ǿ���. �¾��̴� �����̰� ���� ���� �����. 
�ٵ� �ƴϳ� �ٸ��� ���� ���� ������ �ִ� ���̴�. �����̿� �¾��̴� ���� �����ϱ�� �ߴ�.

�������� �����̰� ���� ���ٰ� ���� ��ᰡ ���Ƽ�, �̸� �̿��Ͽ� ���� �����ϱ�� �ߴ�. 
������ �� ���� �ʺ�� x��Ƽ�����̴�. �¾��̿� �����̴� ���̰� �ʹ� ���Ƽ�, ���� ��� �� �ϳ��� ��� �̾� ���̰�, �̷� ������ ����� �ߴ�. 
��, �¾��̰� �����̰� �� ����� ���̰� L1, L2�̸�, L1+L2�� x�� ���ƾ� ������ ���� �� �ִ�. ũ�Ⱑ �ٸ���, �� ������ ������ �ֱ� �����̴�.
�׷� ���� ������ �Ϻ��ϰ� ���� �� �ִ� �� ���븦 ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

�Է�
ù ��° �ٿ� ������ �ʺ� X�� �־�����. X�� ������ ��Ƽ�����̴�. 
�� ��° �ٿ� ����� ���� N�� �־�����. (0 �� N �� 1,000,000)
�� ��° �ٺ��� N���� �ٿ� ���� �� ����� ���� Li�� �־�����. Li�� ������ ��������̴�. (1 �� Li �� 100,000,000, 1cm = 10,000,000nm)

���
ù ��° �ٿ� ������ �Ϻ��ϰ� ���� �� �ִ� �� ������ ���ٸ� 'danger'(����ǥ ����)�� �ٷ��Ѵ�. ����, �Ϻ��ϰ� ���� �� �ִ� ��� 'yes L1 L2'�� ����Ѵ�. 
(L1 �� L2). ������ ���� ���� ��쿡�� |L1 - L2|�� ���� ū ���� ����Ѵ�.

���� �Է�
1
4
9999998
1
2
9999999

���� ���
yes 1 9999999

Think
- ���� X ��Ƽ�� õ���������.
- Li �� �ִ밪�� 1 ~ 100000000 �̹Ƿ�, 10��Ƽ
- ������ ũ��� Maximum ����?
- ����� L1 �� ���� ��, L2 �� ū ��
- ���� Ȱ�� ?

Solution
- ������ �ϸ� ���� Ǯ �� �ִ�.
- ���� ���� ���� ���� �� �ִ� 

 */
public class JieunHouse_20160920 {
	static int X, N;
	static long L[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		X = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		if(N == 0){
			System.out.println("danger");
			return;
		}
		
		L = new long[N];
		
		for(int i = 0; i<N;i++){
			L[i] = Long.parseLong(br.readLine());
		}
		br.close();
		Arrays.sort(L);
		
		// Ž��
		int startPos = 0;
		int endPos = N-1;
		
		// 
		boolean findResult = false;
		long target = X * 10000000;
		
		while(startPos != endPos){
			if(L[startPos] + L[endPos] == target){
				System.out.println("yes " + L[startPos] + " " + L[endPos]);
				findResult = true;
				break;
			}
			else if(L[startPos] + L[endPos] > target){
				endPos--;
			}
			else {
				startPos++;
			}
		}
		
		if(!findResult)
			System.out.println("danger");
	}
}
