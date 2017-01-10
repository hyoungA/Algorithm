#include <bits/stdc++.h>
using namespace std;
 
int N, ans[2];
int A[128][128];
 
void dfs(int sy, int sx, int n)
{
    bool sw = 0;
    for (int i=0;i<n;i++) for (int j=0;j<n;j++) if (A[sy][sx] != A[sy+i][sx+j]) sw = 1;
    if (!sw){ ans[A[sy][sx]]++; return; }
    int m = n >> 1;
    dfs(sy, sx, m);
    dfs(sy, sx+m, m);
    dfs(sy+m, sx, m);
    dfs(sy+m, sx+m, m);
}
 
int main()
{
    scanf("%d", &N);
    for (int i=0;i<N;i++) for (int j=0;j<N;j++) scanf("%d", A[i]+j);
    dfs(0, 0, N);
    for (int i=0;i<2;i++) printf("%d\n", ans[i]);
}
