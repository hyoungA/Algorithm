#include <bits/stdc++.h>
using namespace std;
 
#define TS 262144
typedef long long lld;
 
int N, Q;
lld tree[TS], ST = TS/2-1;
 
lld get(int l, int r)
{
    lld ret = 0;
    for (l+=ST,r+=ST;l<=r;l>>=1,r>>=1){
        if (l&1) ret += tree[l++];
        if (!(r&1)) ret += tree[r--];
    }
    return ret;
}
 
void write(int n, int v)
{
    n += ST;
    tree[n] = v;
    for (n>>=1;n;n>>=1) tree[n] = tree[n+n] + tree[n+n+1];
}
 
int main()
{
    scanf("%d%d", &N, &Q);
    for (int i=1;i<=N;i++) tree[ST+i] = i;
    for (int i=ST;i;i--) tree[i] = tree[i+i] + tree[i+i+1];
    while (Q--){
        int t, x, y;
        scanf("%d%d%d", &t, &x, &y);
        if (t){
            printf("%lld\n", get(x, y));
        }else{
            write(x, y);
        }
    }
}