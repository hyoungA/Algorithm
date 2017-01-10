#include <bits/stdc++.h>
using namespace std;
 
#define pb push_back
typedef pair<int, int> pii;
 
int N, M, K;
int D[3001], E[3001];
vector <pii> con[3001];
 
void dijkstra(int s, int dist[])
{
    for (int i=1;i<=N;i++) dist[i] = 2e9;
    priority_queue <pii> que;
    dist[s] = 0; que.push({0, s});
    while (!que.empty()){
        int q = que.top().second, d = -que.top().first; que.pop();
        if (dist[q] != d) continue;
        for (pii &p: con[q]){
            int v = d + p.second, t = p.first;
            if (dist[t] > v)
                dist[t] = v, que.push({-v, t});
        }
    }
}
 
int main()
{
    scanf("%d%d%d", &N, &M, &K);
    for (int i=1;i<=M;i++){
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        con[a].pb({b, c});
    }
    dijkstra(1, D);
    dijkstra(K, E);
    if (D[K] < 2e9 && E[1] < 2e9)
        printf("YES\n%d\n", D[K] + E[1]);
    else puts("NO");
}