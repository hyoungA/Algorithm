#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 1000006
 
int N;
int A[MAXN];
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d", A+i);
    sort(A+1, A+N+1);
    int cnt = 0, mx = 0, ans;
    for (int i=1;i<=N;i++){
        if (i == 1 || A[i-1] != A[i]) cnt = 0;
        cnt++;
        if (mx < cnt) mx = cnt, ans = A[i];
    }
    printf("%d\n", ans);
}