#include <bits/stdc++.h>
using namespace std;
 
int T, N, M;
char A[10][11];
int D[11][11][1024][2];
 
void put_max(int &t, int v){ t = max(t, v); }
 
int main()
{
    for (scanf("%d", &T);T--;){
        scanf("%d%d", &N, &M);
        for (int i=N;i--;) scanf("%s", A[i]);
        for (int i=0;i<=N;i++) for (int j=0;j<=M;j++) for (int msk=0;msk<(1<<M);msk++)
            for (int t=0;t<2;t++) D[i][j][msk][t] = -2e9;
        D[0][0][0][0] = 0;
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++) for (int msk=0;msk<(1<<M);msk++) for (int t=0;t<2;t++) if (D[i][j][msk][t] >= 0){
                int nxt = (bool)(msk & (1 << j));
                int nxt_msk = msk ^ (msk & (1 << j));
                put_max(D[i][j+1][nxt_msk][nxt], D[i][j][msk][t]);
                 
                if (t || (j > 0 && (msk & (1 << (j-1)))) || (msk & (1 << (j+1))) || A[i][j] == 'x') continue;
                nxt_msk = msk | (1 << j);
                put_max(D[i][j+1][nxt_msk][nxt], D[i][j][msk][t]+1);
            }
            for (int msk=0;msk<(1<<M);msk++) for (int t=0;t<2;t++)
                put_max(D[i+1][0][msk][0], D[i][M][msk][t]);
        }
        int ans = 0;
        for (int msk=0;msk<(1<<M);msk++) put_max(ans, D[N][0][msk][0]);
        printf("%d\n", ans);
    }
}