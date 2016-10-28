#include<stdio.h>
  
#define MAX 5000
#define IM 2100000000
  
struct xyz{
    int x, y, z;
}edge[MAX];
int n, m, k;
int en;
int Dist[MAX];
bool BF(){
    int i,j;
    bool UD;
    for (i = 1; i <= n; i++){
        Dist[i] = IM;
    }
    Dist[1] = 0;
    for (i = 0; i <= n; i++){
        UD = 0;
        for (j = 0; j < en; j++){
            if (Dist[edge[j].x] != IM&&Dist[edge[j].y]>Dist[edge[j].x] + edge[j].z){
                Dist[edge[j].y] = Dist[edge[j].x] + edge[j].z;
                UD = 1;
            }
        }
        if (i == n&&UD)
            return 1;
    }
    return 0;
}
  
  
int main(){
    int t, tc;
    int i, j;
    int s, e, d;
  
    scanf("%d", &t);
  
    for (tc = 0; tc < t; tc++){
        scanf("%d%d%d", &n, &m,&k);
        en = 0;
        for (i = 0; i < m; i++){
            scanf("%d%d%d", &s, &e, &d);
            edge[en++] = { s, e, d };
            edge[en++] = { e, s, d };
        }
        for (i = 0; i < k; i++){
            scanf("%d%d%d", &s, &e, &d);
            edge[en++] = { s, e, -d };
        }
        printf("%s\n", BF()?"YES":"NO");
    }
  
    return 0;
}