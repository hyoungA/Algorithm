#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 100005
typedef long long lld;
 
int N;
int X[MAXN], Y[MAXN];
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d%d", X+i, Y+i);
    lld ans = 0;
    for (int i=1;i<=N;i++){
        int j = i%N + 1;
        ans += ((lld)X[i]*Y[j] - (lld)X[j]*Y[i]);
    }
    ans = abs(ans);
    printf("%lld.%d\n", ans>>1, (ans&1)*5);
}
