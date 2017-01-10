#include <bits/stdc++.h>
using namespace std;
 
int N, ans;
bool col[12], d1[23], d2[23];
 
void dfs(int y)
{
    if (y == N){ ans++; return; }
    for (int x=0;x<N;x++){
        if (col[x] || d1[y+x] || d2[y-x+11]) continue;
        col[x] = d1[y+x] = d2[y-x+11] = 1;
        dfs(y+1);
        col[x] = d1[y+x] = d2[y-x+11] = 0;
    }
}
 
int main()
{
    scanf("%d", &N);
    dfs(0);
    printf("%d\n", ans);
}