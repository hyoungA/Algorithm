#include <bits/stdc++.h>
using namespace std;
 
const int MOD = 1e7+3;
int N;
int D[101][100001];
 
int gcd(int a, int b){ return b ? gcd(b, a%b) : a; }
 
int main()
{
    scanf("%d", &N);
    D[0][0] = 1;
    for (int i=0;i<N;i++){
        int v; scanf("%d", &v);
        for (int j=0;j<100001;j++) if (D[i][j]){
            D[i+1][j] = (D[i+1][j] + D[i][j]) % MOD;
            D[i+1][gcd(j, v)] = (D[i+1][gcd(j, v)] + D[i][j]) % MOD;
        }
    }
    printf("%d\n", D[N][1]);
}
