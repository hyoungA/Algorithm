#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 100005
typedef long long lld;
 
const double eps = 1e-8;
int N;
int X[MAXN], Y[MAXN];
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d%d", X+i, Y+i);
    for (int t=2;t--;){
        int x, y;
        scanf("%d%d", &x, &y);
        int ans = 0;
        for (int i=1;i<=N;i++){
            int j = i%N + 1;
            int ax = min(X[i], X[j]), bx = max(X[i], X[j]);
            if (x < ax || x > bx || ax == bx) continue;
            double ty = (double)(Y[j] - Y[i]) * (x - X[i]) / (X[j] - X[i]) + Y[i];
            if (ty < y + eps) ans ^= 1;
        }
        puts(ans ? "in" : "out");
    }
}
