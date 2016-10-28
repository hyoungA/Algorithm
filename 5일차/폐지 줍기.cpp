#include <bits/stdc++.h>
using namespace std;
 
int N;
int D[201][201];
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) for (int j=1;j<=N;j++){
        int v; scanf("%d", &v);
        D[i][j] = max(D[i-1][j], D[i][j-1]) + v;
    }
    printf("%d\n", D[N][N]);
}
