import java.util.Scanner;
import java.io.FileInputStream; // Method needed to use System.setIn

/*
  The class name has to be Solution, so we recomment using Solution.java.
  You can test your program with the command java Solution.
*/
class optimal {	
	///////////////////////////////////////////
	
	public static void main(String args[]) throws Exception {
		/*
		 The method call below opens input.txt in read only mode and 
	           sets your standard input System.in to work with the opened file. 
	           When you test your code with the sample data, you can use the function
	           below to read in from the sample data file instead of the standard input.
	           So. you can uncomment the following line for your local test. But you
	           have to comment the following line when you submit for your scores.
		*/
	    // System.setIn(new FileInputStream("input.txt"));

		/*
		   Makes a Scanner from standard input System.in and reads data.
		*/
		Scanner sc = new Scanner(System.in);
		
		int i, j, k;
		int T, group_num, group_size[];

		group_num = sc.nextInt();
		group_size = new int[group_num];
		for(i=0;i<group_num;i++) group_size[i] = sc.nextInt();
		
		T = sc.nextInt();
		for(int test_case = 1; test_case <= T; ++test_case){
			int answer = 0, N, K;
			///////////////////////////////////////////////////////////////////////////////////////
			N = sc.nextInt();
			K = sc.nextInt();
			
			int[][] A = new int[N+1][N+1], B = new int[N+1][N+1];
			int[] MX = new int[K+1], mn = new int[K+2], candi = new int[K+1], tmp = new int[K+1];
			
			for (i=1;i<=N;i++) for (j=1;j<=N;j++) A[i][j] = sc.nextInt();
			for (i=1;i<=K;i++) MX[i] = (int)-1e9;
			
			for (int side=1;side<=N;side++){
				for (i=1;i<=N;i++){
					int sum = 0;
					for (j=1;j<=N;j++){
						sum += A[i][j];
						if (j > side) sum -= A[i][j-side];
						B[i][j] = sum;
					}
				}
				for (j=side;j<=N;j++) for (i=1;i<=N;i++) B[i][j] += B[i-1][j];
				for (j=side;j<=N;j++){
					for (i=1;i<=K;i++) mn[i] = (int)1e9;
					mn[1] = 0;
					for (i=1;i<=N;i++){
						for (k=1;k<=K;k++) candi[k] = B[i][j]-mn[k];
						int p=1,q=1;
						for (k=1;k<=K;k++){
							if (MX[p] > candi[q]) tmp[k] = MX[p++];
							else tmp[k] = candi[q++];
						}
						for (k=1;k<=K;k++) MX[k] = tmp[k];
						for (k=K;k > 0 && mn[k] >= B[i][j];k--) mn[k+1] = mn[k];
						mn[++k] = B[i][j];
					}
				}
			}
			answer = MX[K];

			///////////////////////////////////////////////////////////////////////////////////////

			// Prints out the answer to standard output.
			System.out.println("#" + test_case + " " + answer);
		}
	}
}
