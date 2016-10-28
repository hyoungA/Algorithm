#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 100005
#define pb push_back
typedef long long lld;
 
int N;
int dep[MAXN], par[MAXN][17];
vector <int> con[MAXN];
 
int lca(int a, int b)
{
    if (dep[a] < dep[b]) swap(a, b);
    for (int i=17;i--;) if ((dep[a]-dep[b])&(1<<i)) a = par[a][i];
    if (a == b) return a;
    for (int i=17;i--;) if (par[a][i] != par[b][i])
        a = par[a][i], b = par[b][i];
    return par[a][0];
}
 
int get_dist(int a, int b)
{
    int c = lca(a, b);
    return dep[a] + dep[b] - 2 * dep[c];
}
 
void dfs(int n)
{
    for (int t: con[n]) if (!par[t][0]){
        par[t][0] = n; dep[t] = dep[n]+1;
        dfs(t);
    }
}
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<N;i++){
        int a, b;
        scanf("%d%d", &a, &b);
        con[a].pb(b); con[b].pb(a);
    }
    par[1][0] = 1; dfs(1);
    for (int i=1;i<17;i++) for (int j=1;j<=N;j++)
        par[j][i] = par[par[j][i-1]][i-1];
    lld ans = 0;
    for (int i=1;i<N;i++) ans += get_dist(i, i+1);
    printf("%lld\n", ans);
}