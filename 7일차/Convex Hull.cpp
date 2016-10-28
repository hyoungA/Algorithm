#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 100005
#define pb push_back
#define sz(v) ((int)(v).size())
typedef long long lld;
 
int N;
struct Z{
    int x, y;
} A[MAXN];
 
int ccw(int ax, int ay, int bx, int by, int cx, int cy)
{
    lld k = (lld)(bx-ax)*(cy-ay)-(lld)(cx-ax)*(by-ay);
    if (k > 0) return 1;
    if (k) return -1;
    return 0;
}
 
int ccw(const Z &a, const Z &b, const Z &c){ return ccw(a.x, a.y, b.x, b.y, c.x, c.y); }
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d%d", &A[i].x, &A[i].y);
    for (int i=2;i<=N;i++) if (A[1].y > A[i].y || A[1].y == A[i].y && A[1].x > A[i].x)
        swap(A[1], A[i]);
    sort(A+2, A+N+1, [o=A[1]](const Z &a, const Z &b){
        int k = ccw(o, a, b);
        if (k) return k > 0;
        return abs(a.x-o.x)+(a.y-o.y) < abs(b.x-o.x)+(b.y-o.y);
    });
    vector <int> stk = {1};
    for (int i=2;i<=N;i++){
        while (sz(stk) > 1 && ccw(A[stk[sz(stk)-2]], A[stk.back()], A[i]) <= 0) stk.pop_back();
        stk.pb(i);
    }
    printf("%d\n", sz(stk));
}
