#include <bits/stdc++.h>
using namespace std;
 
#define MAXV 1000006
 
int N;
int cnt[MAXV];
 
int main()
{
    int ans = 0;
    for (scanf("%d", &N);N--;){
        int h; scanf("%d", &h);
        if (cnt[h]) cnt[h]--;
        else ans++;
        cnt[h-1]++;
    }
    printf("%d\n", ans);
}