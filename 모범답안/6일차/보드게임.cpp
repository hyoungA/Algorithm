#include <bits/stdc++.h>
using namespace std;
 
#define pb push_back
typedef pair<int, char> pic;
 
int L, N, M;
char S[1003];
int D[1003][501];
vector <pic> con[501];
 
int main()
{
    scanf("%d", &L);
    for (int i=1;i<=L;i++) scanf(" %c", S+i);
    for (scanf("%d%d", &N, &M);M--;){
        int a, b; char c; scanf("%d%d %c", &a, &b, &c);
        con[a].pb({b, c});
        con[b].pb({a, c});
    }
    for (int i=0;i<=L;i++) for (int j=1;j<=N;j++) D[i][j] = -2e9;
    D[0][1] = 0;
    for (int i=0;i<L;i++) for (int j=1;j<=N;j++) if (D[i][j] >= 0){
        for (pic &p: con[j]){
            int t = p.first; char c = p.second;
            int v = D[i][j] + (int)(c == S[i+1]);
            D[i+1][t] = max(D[i+1][t], v);
        }
    }
    int ans = 0;
    for (int i=1;i<=N;i++) ans = max(ans, D[L][i]);
    printf("%d\n", ans*10);
}
