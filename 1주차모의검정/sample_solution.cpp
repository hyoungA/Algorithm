#include <stdio.h>

int T,N,K,A[109][109],B[109][109];
int MX[109],mn[109],candi[109],tmp[109];

int main(void)
{
	int group_num,group_size[5];
	/* The freopen function below opens input.txt in read only mode and 
	sets your standard input to work with the opened file. 
	When you test your code with the sample data, you can use the function
	below to read in from the sample data file instead of the standard input.
	So. you can uncomment the following line for your local test. But you
	have to comment the following line when you submit for your scores.
	*/
	// freopen("input.txt", "r", stdin);

	/* If you do not use the following function, your output may not be correctly
	applied to standard output if your program is killed for time limit violation.
	So, be sure to keep the following function in your code.
	*/
	setbuf(stdout, NULL);

	/*
	Code to loop through all test cases
	*/
	scanf("%d",&group_num);
	for (int i=0;i<group_num;i++) scanf("%d",group_size+i);
	scanf("%d",&T);
	for (int test_case=1;test_case<=T;test_case++){
		int answer = 0;
		////////////////////////////////////////////////////////////////////////////////////////////
		int i,j,k;
		scanf("%d%d",&N,&K);
		for (i=1;i<=N;i++) for (j=1;j<=N;j++) scanf("%d",A[i]+j);
		for (i=1;i<=K;i++) MX[i] = -1e9;
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
				for (i=1;i<=K;i++) mn[i] = 1e9;
				mn[1] = 0;
				for (i=1;i<=N;i++){
					for (k=1;k<=K;k++) candi[k] = B[i][j]-mn[k];
					int p=1,q=1;
					for (k=1;k<=K;k++){
						if (MX[p] > candi[q]) tmp[k] = MX[p++];
						else tmp[k] = candi[q++];
					}
					for (k=1;k<=K;k++) MX[k] = tmp[k];
					for (k=K;k&&mn[k]>=B[i][j];k--) mn[k+1] = mn[k];
					mn[++k] = B[i][j];
				}
			}
		}
		answer = MX[K];
		/////////////////////////////////////////////////////////////////////////////////////////////

		// Prints out the answer to standard output
		printf("#%d %d\n",test_case,answer);
	}

	return 0;//You have to return 0 for successful exit of the program.
}