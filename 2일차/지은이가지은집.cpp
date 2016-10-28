#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 1000006
typedef long long lld;
 
lld X;
int N, A[MAXN];
 
int main()
{
    scanf("%lld%d", &X, &N); X *= (int)1e7;
    for (int i=1;i<=N;i++) scanf("%d", A+i);
    sort(A+1, A+N+1);
    for (int i=1,r=N;i<=r;i++){
        while (r > i && A[i]+A[r] > X) r--;
        if (i >= r) break;
        if (A[i] + A[r] == X){ printf("yes %d %d\n", A[i], A[r]); return 0; }
    }
    puts("danger");
}