#include <bits/stdc++.h>
using namespace std;
 
#define pb push_back
 
int K, N, M;
int A[101];
int D[101][1001];
vector <int> con[1001];
 
void bfs(int s, int v[])
{
    queue <int> que;
    v[s] = 1; que.push(s);
    while (!que.empty()){
        int q = que.front(); que.pop();
        for (int t: con[q]) if (!v[t]){
            v[t] = 1; que.push(t);
        }
    }
}
 
int main()
{
    scanf("%d%d%d", &K, &N, &M);
    for (int i=1;i<=K;i++) scanf("%d", A+i);
    for (int i=1;i<=M;i++){
        int a, b; scanf("%d%d", &a, &b);
        con[a].pb(b);
    }
    for (int i=1;i<=K;i++) bfs(A[i], D[i]);
    int ans = 0;
    for (int i=1;i<=N;i++){
        bool sw = 0;
        for (int j=1;j<=K;j++) if (!D[j][i]){ sw = 1; break; }
        if (sw) continue;
        ans++;
    }
    printf("%d\n", ans);
}