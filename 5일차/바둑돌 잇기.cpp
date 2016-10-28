#include <bits/stdc++.h>
using namespace std;
 
int N;
int A[404];
int D[404][404], H[404][404];
 
int dy(int s, int e)
{
    if (s > e) return 0;
    int &ret = D[s][e];
    if (ret >= 0) return ret;
    ret = 1e9;
    if (A[s] != A[e]){
        ret = dy(s+1, e-1) + (H[s+1][e-1] + 1) * 2 + (e-s);
        H[s][e] = H[s+1][e-1] + 1;
    }
    for (int k=s;k<e;k++){
        int v = dy(s, k) + dy(k+1, e);
        if (ret > v){
            ret = v;
            H[s][e] = max(H[s][k], H[k+1][e]);
        }
    }
    return ret;
}
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%1d", A+i);
    for (int i=1;i<=N;i++) for (int j=i;j<=N;j++) D[i][j] = -1;
    printf("%d\n", dy(1, N));
}
