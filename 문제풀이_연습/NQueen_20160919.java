import java.util.Scanner;

/*
 * 
문제 : NQueen

N-Queen문제는 유명한 문제이다. 이는 N × N인 체스판 위에 N개의 퀸을 서로 공격할 수 없게 놓는 문제이다. 
N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하시오.

입력
첫 번째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 12)

출력
첫 번째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.


예제 입력
4

예제 출력
2

Solution
- 같은 세로줄에는 1개의 퀸만 가능
- 격자판 상태 표현 방법
  - 첫번째 row 의 첫번째 컬럼에 마크한 경우 = a[0] = 0 , a[1] = 3 
- y = -x + a 의 값 ( 정확히는 x + y = a 의 값 ) 을 배열에 넣어 두면 격자를 이동하면서 체크하지 않아도 된다.  

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
     
    // 놓을 수 있는 위치인지 검사하는 함수
    static boolean check(int row, int column){
         
        // 열을 체크 ( 위쪽 )
        for(int i=0;i<row;i++){
            if(grid[i][column] == 1) return false;
        }
         
        // 대각선 체크 ( 위쪽 )
        int i= row;
        int j= column;
        
        // 좌상
        while(0<=--i && 0<=--j){
            if(grid[i][j]==1) return false;
        }
         
        i = row;
        j = column;
        
        // 우상
        while(0<=--i && N>++j){
            if(grid[i][j]==1) return false;         
        }
         
        return true;
    }
     
    // 결과 찾기
    static void search(int count){
         
        // 행에 퀸을 하나씩 놓아 봄. 그리고 백트래킹으로 이전으로 돌림 
        if(count > N){
            answer++;
            return;
        }
         
        // 총 count-1 행에 대해서 N 열까지 놓을 수 있음
        for(int i=0;i<N;i++){
            if(count == 1 || check(count-1, i)){
                grid[count-1][i] = 1;
                search(count+1);
                grid[count-1][i] = 0;
            }
        }
    }
}
