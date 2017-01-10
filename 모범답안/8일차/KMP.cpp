#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 1000006
#define pb push_back
#define sz(v) ((int)(v).size())
 
int N, M;
int f[MAXN];
char S[MAXN], P[MAXN];
 
int main()
{
    scanf("%s%s", S+1, P+1); N = strlen(S+1), M = strlen(P+1);
    for (int i=2,k=0;i<=M;i++){
        while (k && P[k+1] != P[i]) k = f[k];
        if (P[k+1] == P[i]) k++;
        f[i] = k;
    }
    vector <int> ans;
    for (int i=1,k=0;i<=N;i++){
        while (k && P[k+1] != S[i]) k = f[k];
        if (P[k+1] == S[i]) k++;
        if (k == M) ans.pb(i-M+1), k = f[k];
    }
    printf("%d\n", sz(ans));
    for (int t: ans) printf("%d ", t); puts("");
}
