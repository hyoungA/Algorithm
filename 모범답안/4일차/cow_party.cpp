#include <bits/stdc++.h>
using namespace std;
 
int N, M, X;
int D[1001], E[1001];
int w[1001][1001];
 
void dijkstra(int s, int dir, int dist[])
{
    for (int i=0;i<=N;i++) dist[i] = 1e9;
    dist[s] = 0;
    vector <int> visit(N+1, 0);
    for (int i=1;i<=N;i++){
        int t = 0;
        for (int j=1;j<=N;j++) if (!visit[j] && dist[t] > dist[j]) t = j;
        if (!t) break;
        visit[t] = 1;
        for (int j=1;j<=N;j++){
            int v = dir ? w[j][t] : w[t][j];
            if (v == 1e9) continue;
            dist[j] = min(dist[j], dist[t] + v);
        }
    }
}
 
int main()
{
    scanf("%d%d%d", &N, &M, &X);
    for (int i=1;i<=N;i++) for (int j=1;j<=N;j++)
        w[i][j] = i == j ? 0 : 1e9;
    while (M--){
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        w[a][b] = min(w[a][b], c);
    }
    dijkstra(X, 0, D);
    dijkstra(X, 1, E);
    int ans = 0;
    for (int i=1;i<=N;i++) ans = max(ans, D[i] + E[i]);
    printf("%d\n", ans);
}