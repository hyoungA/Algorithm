#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 200005
#define fr first
#define sc second
#define pb push_back
#define sz(v) ((int)(v).size())
typedef pair<int, int> pii;
 
int N;
struct Z{
    int s, e, h;
} A[MAXN];
 
vector <pii> solve(int s, int e)
{
    if (s == e) return {{A[s].s, A[s].h}, {A[s].e+1, 0}};
    vector <pii> ret;
    int m = s+e >> 1;
    auto l = solve(s, m);
    auto r = solve(m+1, e);
    int ly = 0, ry = 0;
    for (int i=0,j=0;i<sz(l)||j<sz(r);){
        if (i == sz(l)){
            if (max(ly, ry) != r[j].sc) ret.pb(r[j]);
            ry = r[j++].sc;
        }
        else if (j == sz(r)){
            if (max(ly, ry) != l[i].sc) ret.pb(l[i]);
            ly = l[i++].sc;
        }
        else{
            if (l[i].fr < r[j].fr){
                int h = max(l[i].sc, ry);
                if (max(ly, ry) != h) ret.pb({l[i].fr, h});
                ly = l[i++].sc;
            }else if (l[i].fr > r[j].fr){
                int h = max(ly, r[j].sc);
                if (max(ly, ry) != h) ret.pb({r[j].fr, h});
                ry = r[j++].sc;
            }else{
                int h = max(l[i].sc, r[j].sc);
                if (max(ly, ry) != h) ret.pb({l[i].fr, h});
                ly = l[i++].sc;
                ry = r[j++].sc;
            }
        }
    }
    return ret;
}
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d%d%d", &A[i].s, &A[i].e, &A[i].h);
    auto ans = solve(1, N);
    printf("%d\n", sz(ans));
    for (pii &p: ans) printf("%d %d\n", p.fr, p.sc);
}
