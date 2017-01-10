#include <bits/stdc++.h>
using namespace std;
 
int N, H;
int S[500001];
 
int main()
{
    scanf("%d%d", &N, &H);
    for (int i=1;i<=N;i++){
        int h; scanf("%d", &h);
        if (i&1){
            S[1]++; S[h+1]--;
        }else{
            S[H-h+1]++;
        }
    }
    for (int i=2;i<=H;i++) S[i] += S[i-1];
    int ans = 2e9, cnt = 0;
    for (int i=1;i<=H;i++){
        if (ans > S[i]) ans = S[i], cnt = 1;
        else if (ans == S[i]) cnt++;
    }
    printf("%d %d\n", ans, cnt);
}