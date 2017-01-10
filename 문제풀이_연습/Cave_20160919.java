import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
 * 
���� : ����

���� �� ������ ������ �������� �Ѵ�. 
��ΰ� �˴ٽ��� ������ ������ �������� ������ ���� �����̴�. 
�� ������ �̷��� ��ֹ��� ���� ������ ������ ���̴�. 
������ ���̴� N�����̰�, ���̴� H�����̴�. 
N�� �׻� ¦���̰�, ��ֹ��� ������ �������� ������ ���鼭 �����ϰ�, ù ��ֹ��� �׻� �����̴�.

�Ʒ� ������ N=14, H =5�� ���̴�. 

���ڴٿ� �� ������ ��ֹ��� ���� ������ �ʴ´�. ��, ó�� ���� ���̿��� ���������� ��ֹ��� �μ��鼭 ��������. 
������ �Ʒ����� 4��° �������� ��������, �Ʒ� �׸��� ���� 8���� ��ֹ��� �μ��� �ȴ�.

������, ù ��°�� �ټ� ��° �������� ���ư��ٸ� ������ 7���� ��ֹ��� �νø� �ȴ�. 

������ ������ ������ �����̱� ������, �ּ����� ��ֹ��� �νð� �;��Ѵ�. �������� �� ������ �ּ� �� ���� ��ֹ��� �μ��� ����� �� �ִ��� ���ϰ�, �׷��� ������ �� �� �� �ִ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.


�Է�
ù ��° �ٿ� N�� H�� �־�����. N�� �׻� ¦���̴�. (2 �� N �� 200,000, 2 �� H �� 500,000)
�� ��° �ٺ��� N���� �ٿ� ���� ��ֹ��� ũ�Ⱑ ������� �־�����. ��ֹ��� ũ��� H���� ���� ����̴�.

���
ù ��° �ٿ� ���˹����� �ı��ؾ� �ϴ� ��ֹ��� �ּҰ��� �׷��� ������ ���� �������� �����Ͽ� ����Ͻÿ�.

���� �Է�
14 5
1
3
4
2
2
4
3
4
3
3
3
2
3
3


���� ���
7 2

Solution
- �� ��������. O (N * H)
- �����ؼ� ( O (nlogn) 1 ���� h ���� �����鼭 ������ ������ ���� �ּҰ��� ã�´�. O(n+H)  
1 2 3 4 5 6 7 8 9 10
---------------------
1 2 3 5 6 6 6 6 6 7

// S �� ������ ���� �� ����
// SB [i] �� ���� i �� ������ ���� �� ������ �ε�ġ�� ����
// Sn �� ������ ����
S �迭
S �ε�ġ�� �迭
now = 0
int Sn; 

for(i=1 ~ H)
  while(now<Sn && S[now]<=i) now++;
  SB[i] = now;
  
�������� �Ȱ��� ó��

 */
public class Cave_20160919 {
	static int N, H; // ����, ������ ����, ����
	static int[] S;
	static int[] J;
	static int[] sCrash;
	static int[] jCrash;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String[] tmp = line.split(" ");
		
		N = Integer.parseInt(tmp[0]);
		H = Integer.parseInt(tmp[1]);
		
		S = new int[N/2]; // N �� �ݵ�� ¦���Ƿ�.
		J = new int[N/2];
		sCrash = new int[H+1];
		jCrash = new int[H+1];
		
		
		int sCount = 0;
		int jCount = 0;
		
		// i%2 �� 0 �̸� ����
		// i%2 �� 1 �̸� ����
		for(int i=0;i<N;i++){
			if(i%2==0){
				S[sCount++] = Integer.parseInt(br.readLine());
			}
			else
				J[jCount++] = Integer.parseInt(br.readLine());
		}
		
		// ����
		Arrays.sort(S);
		Arrays.sort(J);
		
		int minimumCrashCount = 0, crashTimes = 0;
		
		// h = 1 ~ H
		// �ʱⰪ ������ ���� sCrash[0] = sCount
		// ���� �ε����� ������ ����(�ε����� �ڿ������� Ž���Ѵ�) - h �� �ش��ϴ� ������ ����
		
		
		
		
		System.out.println(minimumCrashCount + " " + crashTimes);
	}
}
