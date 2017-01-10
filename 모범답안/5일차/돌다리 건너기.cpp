#include <bits/stdc++.h>
using namespace std;
 
int N, M;
char S[22], A[2][109];
int D[22][2][109];
 
int main()
{
    scanf("%s%s%s", S+1, A[0]+1, A[1]+1);
    N = strlen(A[0]+1); M = strlen(S+1);
    D[0][0][0] = D[0][1][0] = 1;
    for (int k=0;k<=M;k++){
        for (int i=0;i<2;i++) for (int j=0;j<N;j++){
            if (k < M && A[i^1][j+1] == S[k+1]) D[k+1][i^1][j+1] += D[k][i][j];
            D[k][i][j+1] += D[k][i][j];
        }
    }
    printf("%d\n", D[M][0][N]+D[M][1][N]);
}
