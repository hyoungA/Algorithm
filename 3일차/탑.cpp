#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 500005
#define pb push_back
#define sz(v) ((int)(v).size())
 
int N;
int A[MAXN];
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d", A+i);
    vector <int> stk;
    for (int i=1;i<=N;i++){
        while (sz(stk) && A[stk.back()] < A[i]) stk.pop_back();
        if (!sz(stk)) printf("0 ");
        else printf("%d ", stk.back());
        stk.pb(i);
    } puts("");
}