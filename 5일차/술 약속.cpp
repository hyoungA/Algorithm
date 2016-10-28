#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 100005
typedef long long lld;
 
int N;
struct Z{
    int t, c;
} A[MAXN];
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d%d", &A[i].t, &A[i].c);
    sort(A+1, A+N+1, [](const Z &a, const Z &b){
        return a.t * b.c < b.t * a.c;
    });
    lld ans = 0, sum = 0;
    for (int i=1;i<=N;i++){
        ans += sum * A[i].c;
        sum += A[i].t;
    }
    printf("%lld\n", ans<<1);
}
