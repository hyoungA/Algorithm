#include <bits/stdc++.h>
using namespace std;
 
const int MOD = 1e6;
int N, K;
int D[10001];
 
int main()
{
    scanf("%d%d", &N, &K); D[0] = 1;
    for (int i=1;i<=N;i++){
        int w; scanf("%d", &w);
        for (int j=w;j<=K;j++) D[j] = (D[j] + D[j-w]) % MOD;
    }
    printf("%d\n", D[K]);
}