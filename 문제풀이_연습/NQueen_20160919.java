import java.util.Scanner;

/*
 * 
���� : NQueen

N-Queen������ ������ �����̴�. �̴� N �� N�� ü���� ���� N���� ���� ���� ������ �� ���� ���� �����̴�. 
N�� �־����� ��, ���� ���� ����� ���� ���Ͻÿ�.

�Է�
ù ��° �ٿ� �ڿ��� N�� �־�����. (1 �� N �� 12)

���
ù ��° �ٿ� �� N���� ���� ������ �� ���� ���� ����� ���� ����Ѵ�.


���� �Է�
4

���� ���
2

Solution
- ���� �����ٿ��� 1���� ���� ����
- ������ ���� ǥ�� ���
  - ù��° row �� ù��° �÷��� ��ũ�� ��� = a[0] = 0 , a[1] = 3 
- y = -x + a �� �� ( ��Ȯ���� x + y = a �� �� ) �� �迭�� �־� �θ� ���ڸ� �̵��ϸ鼭 üũ���� �ʾƵ� �ȴ�.  

 */
public class NQueen_20160919 {	
    static int N;
    static int[][] grid = new int[13][13];
    static int answer = 0;
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        N = sc.nextInt();
         
        search(1);
         
        System.out.println(answer);
    }
     
    // ���� �� �ִ� ��ġ���� �˻��ϴ� �Լ�
    static boolean check(int row, int column){
         
        // ���� üũ ( ���� )
        for(int i=0;i<row;i++){
            if(grid[i][column] == 1) return false;
        }
         
        // �밢�� üũ ( ���� )
        int i= row;
        int j= column;
        
        // �»�
        while(0<=--i && 0<=--j){
            if(grid[i][j]==1) return false;
        }
         
        i = row;
        j = column;
        
        // ���
        while(0<=--i && N>++j){
            if(grid[i][j]==1) return false;         
        }
         
        return true;
    }
     
    // ��� ã��
    static void search(int count){
         
        // �࿡ ���� �ϳ��� ���� ��. �׸��� ��Ʈ��ŷ���� �������� ���� 
        if(count > N){
            answer++;
            return;
        }
         
        // �� count-1 �࿡ ���ؼ� N ������ ���� �� ����
        for(int i=0;i<N;i++){
            if(count == 1 || check(count-1, i)){
                grid[count-1][i] = 1;
                search(count+1);
                grid[count-1][i] = 0;
            }
        }
    }
}
