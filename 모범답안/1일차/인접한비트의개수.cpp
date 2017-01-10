#include <bits/stdc++.h>
using namespace std;
 
int T, N, K;
int D[101][101][2];
 
int main()
{
    for (scanf("%d", &T);T--;){
        scanf("%*d%d%d", &N, &K);
        for (int i=0;i<=N;i++) for (int j=0;j<=K;j++) for (int k=0;k<2;k++)
            D[i][j][k] = 0;
        D[1][0][0] = D[1][0][1] = 1;
        for (int i=2;i<=N;i++) for (int j=0;j<=K;j++){
            D[i][j][0] = D[i-1][j][0] + D[i-1][j][1];
            D[i][j][1] = D[i-1][j][0] + (j > 0 ? D[i-1][j-1][1] : 0);
        }
        static int ts = 0;
        printf("%d %d\n", ++ts, D[N][K][0] + D[N][K][1]);
    }
}