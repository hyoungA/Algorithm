#include <bits/stdc++.h>
using namespace std;
 
typedef long long lld;
 
const int MOD = 1e9 + 7;
 
int N;
 
struct MAT{
    int m[2][2];
     
    MAT operator * (const MAT &ot)const{
        MAT ret = {0, };
        for (int i=0;i<2;i++) for (int j=0;j<2;j++) for (int k=0;k<2;k++)
            ret.m[i][j] = (ret.m[i][j] + (lld)m[i][k] * ot.m[k][j]) % MOD;
        return ret;
    }
} B, V;
 
int main()
{
    B.m[0][0] = B.m[0][1] = B.m[1][0] = V.m[0][0] = V.m[1][1] = 1;
    scanf("%d", &N);
    for (;N;N>>=1,B=B*B) if (N&1) V = V*B;
    printf("%d\n", (V.m[1][0] + V.m[1][1]) % MOD);
}
