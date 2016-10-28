#include <stdio.h>
#include <vector>
#include <algorithm>
 
using namespace std;
 
int N, M;
int a[102][102];
int e[4][2] = {1, 0, 0, 1, -1, 0, 0, -1};
int check[10003];
 
vector<int> v[10002];
int total_color;
 
struct X{
    int a, b;
 
    X(){}
    X(int _a, int _b){
        a = _a; b = _b;
    }
 
    bool operator()(X aa, X bb){
        if(aa.a != bb.a) return aa.a < bb.a;
        return aa.b<bb.b;
    }
}V[40000];
 
int queue[10002];
 
int size;
 
void dfs(int x, int y, int z, int color){
    a[x][y] = color;
     
    for(int i=0;i<4;i++){
        if(1 <= x + e[i][0] && x + e[i][0] <= N && 1 <= y + e[i][1] && y + e[i][1] <= M){
            if(a[x + e[i][0]][y+e[i][1]] > 0 && a[x + e[i][0]][y+e[i][1]] != color){
                V[size++] = X(color, a[x + e[i][0]][y+e[i][1]]);
                V[size++] = X(a[x + e[i][0]][y+e[i][1]], color);
            }
            if(a[x + e[i][0]][y+e[i][1]] == z){
                dfs(x + e[i][0], y + e[i][1], z, color);
            }
        }
    }
}
     
 
 
int main(){
    int i, j;
     
    scanf("%d%d",&N,&M);
    for(i=1;i<=N;i++){
        for(j=1;j<=M;j++){
            scanf("%d",&a[i][j]);
            if(a[i][j] == 1) a[i][j] = -1;
        }
    }
 
    for(i=1;i<=N;i++)
        for(j=1;j<=M;j++)
            if(a[i][j] <= 0)
                dfs(i, j, a[i][j], ++total_color);
 
    sort(V, V+size, X());
 
    N = total_color;
 
    int ans = N;
 
    for(i=0;i<size;i++)
        if(V[i].a != V[i+1].a || V[i].b != V[i+1].b)
            v[ V[i].a ].push_back( V[i].b );
 
    for(i=1;i<=N;i++){
        for(j = 1; j <= N ; j++) check[j] = 0;
        size = 0;
        queue[size++] = i;
        check[i] = 1;
        for(j=0;j<size;j++){
            for(int k: v[ queue[j]]){
                if(!check[k]){
                    queue[size++] = k;
                    check[k] = check[queue[j]] + 1;
                }
            }
        }
        if( check[queue[size-1]] < ans)
            ans = check[queue[size-1]];
    }
    printf("%d",ans-1);
 
    return 0;
}