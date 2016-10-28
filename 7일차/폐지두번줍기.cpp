#include <bits/stdc++.h>
using namespace std;
 
int yy[]={1, 0}, xx[]={0, 1};
int T, N, M;
char A[101][102];
int D[201][101][101];
 
int main()
{
    for (scanf("%d", &T);T--;){
        scanf("%d%d", &M, &N);
        for (int i=1;i<=N;i++) scanf("%s", A[i]+1);
        for (int i=2;i<=N+M;i++) for (int j=1;j<=N;j++) for (int k=1;k<=N;k++) D[i][j][k] = -2e9;
        D[2][1][1] = A[1][1] == '*';
        for (int i=2;i<N+M;i++) for (int j=1;j<=N;j++) for (int k=1;k<=N;k++) if (D[i][j][k] >= 0){
            for (int d1=0;d1<2;d1++){
                int y1 = j+yy[d1], x1 = i-j+xx[d1];
                if (y1 < 1 || y1 > N || x1 < 1 || x1 > M || A[y1][x1] == '#') continue;
                for (int d2=0;d2<2;d2++){
                    int y2 = k+yy[d2], x2 = i-k+xx[d2];
                    if (y2 < 1 || y2 > N || x2 < 1 || x2 > M || A[y2][x2] == '#') continue;
                    int add = 0;
                    if (A[y1][x1] == '*') add++;
                    if (A[y2][x2] == '*') add++;
                    if (y1 == y2 && A[y1][x1] == '*') add--;
                    D[i+1][y1][y2] = max(D[i+1][y1][y2], D[i][j][k]+add);
                }
            }
        }
        printf("%d\n", D[N+M][N][N]);
    }
}
