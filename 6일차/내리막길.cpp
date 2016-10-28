#include <bits/stdc++.h>
using namespace std;
 
const int MOD = 1234567;
 
const int yy[]={-1, 0, 1, 0}, xx[]={0, -1, 0, 1};
int N, M;
int A[501][501], D[501][501];
bool V[501][501];
 
int dy(int y, int x)
{
    int &ret = D[y][x];
    if (V[y][x]) return ret;
    V[y][x] = 1; ret = 0;
    for (int i=0;i<4;i++){
        int ny = y+yy[i], nx = x+xx[i];
        if (ny < 1 || ny > N || nx < 1 || nx > M || A[ny][nx] <= A[y][x]) continue;
        ret = (ret + dy(ny, nx)) % MOD;
    }
    return ret;
}
 
int main()
{
    scanf("%d%d", &N, &M);
    for (int i=1;i<=N;i++) for (int j=1;j<=M;j++) scanf("%d", A[i]+j);
    D[1][1] = V[1][1] = 1;
    printf("%d\n", dy(N, M));
}
