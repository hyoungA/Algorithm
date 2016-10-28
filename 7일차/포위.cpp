#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 50004
#define pb push_back
#define sz(v) ((int)(v).size())
#define all(v) (v).begin(), (v).end()
typedef long long lld;
 
int N;
struct Z{
    int x, y;
} A[MAXN], B[MAXN];
 
int ccw(int ax, int ay, int bx, int by, int cx, int cy)
{
    lld k = (lld)(bx-ax)*(cy-ay)-(lld)(cx-ax)*(by-ay);
    if (k > 0) return 1;
    if (k) return -1;
    return 0;
}
  
int ccw(const Z &a, const Z &b, const Z &c){ return ccw(a.x, a.y, b.x, b.y, c.x, c.y); }
 
int solve()
{
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
    sort(B+1, B+N+1, [](const Z &a, const Z &b){
        return a.x < b.x;
    });
    int n = sz(stk);
    vector <int> cnt(N+1, 0);
    int s = 0, e = 0;
    for (int i=0;i<n;i++){
        if (A[stk[s]].x < A[stk[i]].x || A[stk[s]].x == A[stk[i]].x && A[stk[s]].y < A[stk[i]].y)
            s = i;
        if (A[stk[e]].x > A[stk[i]].x || A[stk[e]].x == A[stk[i]].x && A[stk[e]].y < A[stk[i]].y)
            e = i;
    }
    vector <Z> hull;
    for (int i=s;;i=(i+1)%n){
        hull.pb(A[stk[i]]);
        if (i == e) break;
    }
    reverse(all(hull));
    if (sz(hull) == 1)
        for (int i=1;i<=N;i++) if (B[i].x == hull[0].x && B[i].y <= hull[0].y) cnt[i]++;
    for (int i=1,j=1;i<sz(hull);i++){
        while (j <= N && B[j].x <= hull[i].x){
            if (B[j].x >= hull[i-1].x){
                // y = (hull[i].y - hull[i-1].y) * (B[j].x-hull[i-1].x) / (hull[i].x - hull[i-1].x) + hull[i-1].y
                // B[j].y <= y
                if ((lld)(B[j].y - hull[i-1].y) * (hull[i].x - hull[i-1].x) <=
                    (lld)(hull[i].y - hull[i-1].y) * (B[j].x - hull[i-1].x)) cnt[j]++;
            }
            j++;
        }
    }
    hull.clear();
    for (int i=0;i<n;i++){
        if (A[stk[s]].x > A[stk[i]].x || A[stk[s]].x == A[stk[i]].x && A[stk[s]].y > A[stk[i]].y)
            s = i;
        if (A[stk[e]].x < A[stk[i]].x || A[stk[e]].x == A[stk[i]].x && A[stk[e]].y > A[stk[i]].y)
            e = i;
    }
    for (int i=s;;i=(i+1)%n){
        hull.pb(A[stk[i]]);
        if (i == e) break;
    }
    if (sz(hull) == 1)
        for (int i=1;i<=N;i++) if (B[i].x == hull[0].x && B[i].y >= hull[0].y) cnt[i]++;
    for (int i=1,j=1;i<sz(hull);i++){
        while (j <= N && B[j].x <= hull[i].x){
            if (B[j].x >= hull[i-1].x){
                // y = (hull[i].y - hull[i-1].y) * (B[j].x-hull[i-1].x) / (hull[i].x - hull[i-1].x) + hull[i-1].y
                // y <= B[j].y
                if ((lld)(hull[i].y - hull[i-1].y) * (B[j].x - hull[i-1].x) <=
                    (lld)(B[j].y - hull[i-1].y) * (hull[i].x - hull[i-1].x)) cnt[j]++;
            }
            j++;
        }
    }
    int ret = 0;
    for (int i=1;i<=N;i++) if (cnt[i] == 2) ret++;
    return ret;
}
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d%d", &A[i].x, &A[i].y);
    for (int i=1;i<=N;i++) scanf("%d%d", &B[i].x, &B[i].y);
    printf("%d ", solve());
    for (int i=1;i<=N;i++) swap(A[i], B[i]);
    printf("%d\n", solve());
}
