import java.io.BufferedInputStream;
import java.io.BufferedReader;import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * 
���� : �պ���

0���� N������ ���� K���� ���ؼ� �� ���� N�� �Ǵ� ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
������ ������ �ٲ� ���� �ٸ� ���� ����(1+2�� 2+1�� ���� �ٸ� ���). ���� �� ���� ���� ���� �� �� ���� �ִ�.

�Է�
ù ��° �ٿ� �� ���� N, K�� �������� �и��Ǿ� �־�����. (1 �� N, K �� 200)

���
ù ��° �ٿ� ���� 1,000,000,000���� ���� �������� ����Ѵ�.

���� �Է�
20 2

���� ���
21

Solution
- D[i][j] = i �� ������ �� j �� ����� ������
- ���������� ���� ���ڰ� 0, 1, 2...j �� ��� : D[i-1][j], D[i-1][j-1], .. D[i-1][0]
- D[i][j] = D[i-1][j] + D[i-1][j-1] + D[i-1][j-2] + ...  

Tip
- aHb �� �� ( �ߺ� ����� ������ ��� ) ���ε� ��� �� �� �ִ�.
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
