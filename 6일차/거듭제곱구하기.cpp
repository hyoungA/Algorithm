#include <bits/stdc++.h>
using namespace std;
 
typedef long long lld;
 
const int MOD = 1e9 + 7;
lld N, K;
 
int main()
{
    cin >> N >> K;
    int ans = 1;
    for (int v=N%MOD;K;K>>=1,v=(lld)v*v%MOD) if (K&1) ans = (lld)ans * v % MOD;
    printf("%d\n", ans);
}
