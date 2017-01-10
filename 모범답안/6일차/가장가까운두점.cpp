#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 500005
 
int N;
struct Z{
    int x, y;
} A[MAXN], B[MAXN];
 
int dfs(int s, int e)
{
    if (e-s < 5){
        int ret = 2e9;
        for (int i=s;i<e;i++) for (int j=i+1;j<=e;j++)
            ret = min(ret, (A[i].x-A[j].x)*(A[i].x-A[j].x)+(A[i].y-A[j].y)*(A[i].y-A[j].y));
        sort(A+s, A+e+1, [](const Z &a, const Z &b){
            return a.y < b.y;
        });
        return ret;
    }
    int m = (s+e) >> 1, mid_x = A[m].x;
    int ret = min(dfs(s, m), dfs(m+1, e));
    {
        // merge
        int x = s;
        for (int l=s,r=m+1;l<=m||r<=e;)
            if (r > e || l <= m && A[l].y <= A[r].y) B[x++] = A[l++];
            else B[x++] = A[r++];
        for (int i=s;i<=e;i++) A[i] = B[i];
    }
    int n = 0;
    for (int i=s;i<=e;i++) if ((mid_x-A[i].x)*(mid_x-A[i].x) <= ret) B[++n] = A[i];
    for (int i=1;i<n;i++){
        for (int j=i+1;j<i+9&&j<=n;j++) ret = min(ret, (B[i].x-B[j].x)*(B[i].x-B[j].x)+(B[i].y-B[j].y)*(B[i].y-B[j].y));
    }
    return ret;
}
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d%d", &A[i].x, &A[i].y);
    sort(A+1, A+N+1, [](const Z &a, const Z &b){
        return a.x < b.x;
    });
     
    printf("%d\n", dfs(1, N));
}
