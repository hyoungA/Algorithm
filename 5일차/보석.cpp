#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 300005
typedef long long lld;
 
int N, K;
int C[MAXN];
 
struct Z{
    int w, c;
} A[MAXN];
 
int main()
{
    scanf("%d%d", &N, &K);
    for (int i=1;i<=N;i++) scanf("%d%d", &A[i].w, &A[i].c);
    for (int i=1;i<=K;i++) scanf("%d", C+i);
    sort(C+1, C+K+1);
    sort(A+1, A+N+1, [](const Z &a, const Z &b){
        return a.w < b.w;
    });
    priority_queue <int> que;
    lld ans = 0;
    for (int i=1,j=1;i<=K;i++){
        while (j <= K && A[j].w <= C[i]) que.push(A[j++].c);
        if (!que.empty()) ans += que.top(), que.pop();
    }
    printf("%lld\n", ans);
}
