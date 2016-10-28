#include <bits/stdc++.h>
using namespace std;
 
int yy[]={-1, 0, 1, 0}, xx[]={0, 1, 0, -1};
int N, M;
int sy, sx, ey, ex;
int D[1502][1502];
char A[1502][1502];
 
int main()
{
    scanf("%d%d", &M, &N);
    for (int i=1;i<=N;i++) scanf("%s", A[i]+1);
    for (int i=1;i<=N;i++) for (int j=1;j<=M;j++){
        D[i][j] = 2e9;
        if (A[i][j] == 'S') sy = i, sx = j;
        if (A[i][j] == 'E') ey = i, ex = j;
    }
    queue <int> que;
    D[sy][sx] = 0; que.push(sy); que.push(sx);
    while (!que.empty()){
        int y = que.front(); que.pop();
        int x = que.front(); que.pop();
        for (int i=0;i<4;i++){
            int ny = y+yy[i], nx = x+xx[i];
            if (ny < 1 || ny > N || nx < 1 || nx > M || A[ny][nx] == 'X' || D[ny][nx] < 2e9) continue;
            D[ny][nx] = D[y][x]+1;
            que.push(ny); que.push(nx);
        }
    }
    printf("%d\n", D[ey][ex] < 2e9 ? D[ey][ex] : -1);
}