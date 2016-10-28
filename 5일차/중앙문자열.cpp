#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 100005
 
int N;
char S[3][MAXN];
 
bool proc(int m)
{
    int cnt[3] = {0,}, all = 0;
    for (int i=0;i<N;i++){
        if (S[0][i] == S[1][i] && S[1][i] == S[2][i]) continue;
        bool sw = 0;
        for (int j=0;j<3;j++){
            int t = (j+1)%3;
            if (S[j][i] == S[t][i]) cnt[3-j-t]++, sw = 1;
        }
        if (!sw) all++;
    }
    sort(cnt, cnt+3);
    if (cnt[1] > m) return 0;
    if (cnt[2] > m){
        cnt[0] += cnt[2] - m;
        cnt[1] += cnt[2] - m;
        cnt[2] = m;
        if (cnt[1] > m) return 0;
    }
    for (int i=0;i<all;i++){
        sort(cnt, cnt+3);
        cnt[0]++; cnt[1]++;
        if (cnt[1] > m) return 0;
    }
    return 1;
}
 
int main()
{
    for (int i=0;i<3;i++) scanf("%s", S[i]);
    N = strlen(S[0]);
    int s = 0, e = N, ans;
    while (s <= e){
        int m = s+e >> 1;
        if (proc(m)) e = m-1, ans = m;
        else s = m+1;
    }
    printf("%d\n", ans);
}
