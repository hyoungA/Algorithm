#include <bits/stdc++.h>
using namespace std;
 
#define pb push_back
typedef pair<int, int> pii;
 
int N, M, A, B;
int D[101];
vector <int> con[101];
 
int main()
{
    scanf("%d%d%d%d", &N, &A, &B, &M);
    for (int i=1;i<=M;i++){
        int a, b; scanf("%d%d", &a, &b);
        con[a].pb(b); con[b].pb(a);
    }
    for (int i=1;i<=N;i++) D[i] = 2e9;
    queue <int> que;
    que.push(A); D[A] = 0;
    while (!que.empty()){
        int q = que.front(); que.pop();
        for (int t: con[q]) if (D[t] == 2e9){
            D[t] = D[q]+1, que.push(t);
        }
    }
    printf("%d\n", D[B] < 2e9 ? D[B] : -1);
}