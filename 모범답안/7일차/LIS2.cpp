#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 300005
 
int N, M;
int A[MAXN];
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++){
        int v; scanf("%d", &v);
        int t = lower_bound(A, A+M, v) - A;
        A[t] = v;
        if (t == M) M++;
    }
    printf("%d\n", M);
}
