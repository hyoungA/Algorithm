#include <bits/stdc++.h>
using namespace std;
 
typedef long long lld;
 
int N;
lld D[99];
 
int main()
{
    scanf("%d", &N); D[1] = 1;
    for (int i=2;i<=N;i++){
        for (int j=1;j<i-1;j++) D[i] += D[j];
    }
    lld ans = 0;
    for (int i=1;i<=N;i++) ans += D[i];
    printf("%lld\n", ans);
}