#include <bits/stdc++.h>
using namespace std;
 
int N, M;
int A[301], B[301];
int D[301][1001];
 
int main()
{
    scanf("%d%d", &M, &N);
    for (int i=1;i<=N;i++) scanf("%d%d", A+i, B+i);
    for (int i=0;i<=N;i++) for (int j=0;j<=M;j++) D[i][j] = 2e9;
    D[0][0] = 2;
    for (int i=0;i<N;i++) for (int j=M;j>=0;j--) if (D[i][j] < 2e9){
        int sum = 0, left = M-j;
        for (int k=i+1;k<=N;k++){
            sum += B[k], left -= A[k];
            if (left < 0 || sum > M) break;
            D[k][sum] = min(D[k][sum], D[i][j]+1);
        }
        D[i][0] = min(D[i][0], D[i][j]+1);
    }
    int ans = 2e9;
    for (int i=0;i<=M;i++) ans = min(ans, D[N][i]);
    printf("%d\n", ans);
}
