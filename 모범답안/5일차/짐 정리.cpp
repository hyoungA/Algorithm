#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 1004
 
int N;
int to[MAXN];
bool V[MAXN];
struct Z{
    int v, idx;
} A[MAXN];
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d", &A[i].v), A[i].idx = i;
    sort(A+1, A+N+1, [](const Z &a, const Z &b){
        return a.v < b.v;
    });
    for (int i=1;i<=N;i++) to[A[i].idx] = i;
    int ans = 0;
    for (int i=1;i<=N;i++) if (!V[i]){
        int mn = A[i].v, sum = 0, n = 0;
        int t = i;
        do{
            mn = min(mn, A[t].v);
            sum += A[t].v;
            V[t] = 1; n++;
            t = to[t];
        } while (t != i);
        ans += min(sum + (n-2) * mn, sum + mn + (n+1) * A[1].v);
    }
    printf("%d\n", ans);
}
