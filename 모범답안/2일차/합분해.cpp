#include <bits/stdc++.h>
using namespace std;
 
const int MOD = 1e9;
int N, K;
int D[201][201];
 
int main()
{
    scanf("%d%d", &N, &K); D[0][0] = 1;
    for (int k=1;k<=K;k++){
        for (int i=0;i<=N;i++) for (int j=i;j<=N;j++){
            D[j][k] = (D[j][k] + D[j-i][k-1]) % MOD;
        }
    }
    printf("%d\n", D[N][K]);
}