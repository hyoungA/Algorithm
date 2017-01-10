#include <bits/stdc++.h>
using namespace std;
 
int N, M, L;
int A[1002], D[1002];
 
int main()
{
    scanf("%d%d%d", &N, &M, &L);
    for (int i=1;i<=N;i++) scanf("%d", A+i);
    A[++N] = L;
    for (int i=1;i<=N;i++){
        D[i] = 1e9;
        for (int j=0;j<i;j++) if (A[i] - A[j] <= M)
            D[i] = min(D[i], D[j]+1);
    }
    printf("%d\n", D[N]-1);
}
