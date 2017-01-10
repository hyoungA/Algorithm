#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 1000006
#define pb push_back
#define sz(v) ((int)(v).size())
 
int N, M;
int f[MAXN], A[MAXN];
char S[MAXN], P[MAXN];
 
int main()
{
    scanf("%s%s", S+1, P+1); N = strlen(S+1), M = strlen(P+1);
    for (int i=2,k=0;i<=M;i++){
        while (k && P[k+1] != P[i]) k = f[k];
        if (P[k+1] == P[i]) k++;
        f[i] = k;
    }
    vector <int> stk;
    for (int i=1;i<=N;i++){
        int k = sz(stk) ? A[stk.back()] : 0;
        while (k && P[k+1] != S[i]) k = f[k];
        if (P[k+1] == S[i]) k++;
        if (k == M)
            for (int j=M;--j;) stk.pop_back();
        else A[i] = k, stk.pb(i);
    }
    for (int t: stk) printf("%c", S[t]); puts("");
}
