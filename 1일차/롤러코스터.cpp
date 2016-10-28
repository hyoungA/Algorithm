#include <bits/stdc++.h>
using namespace std;
 
#define pb push_back
 
int L, N, B;
int D[1001][1001];
 
struct Z{
    int e, f, c;
};
vector <Z> arr[1001];
 
int main()
{
    scanf("%d%d%d", &L, &N, &B);
    for (int i=1;i<=N;i++){
        int x, w, f, c;
        scanf("%d%d%d%d", &x, &w, &f, &c);
        arr[x].pb({x+w, f, c});
    }
    for (int i=0;i<=L;i++) for (int j=0;j<=B;j++) D[i][j] = -2e9;
    D[0][0] = 0;
    for (int i=0;i<L;i++) for (int j=0;j<=B;j++) if (D[i][j] >= 0){
        for (Z &z: arr[i]){
            if (j + z.c > B) continue;
            D[z.e][j+z.c] = max(D[z.e][j+z.c], D[i][j] + z.f);
        }
    }
    int ans = -1;
    for (int i=0;i<=B;i++) ans = max(ans, D[L][i]);
    printf("%d\n", ans);
}