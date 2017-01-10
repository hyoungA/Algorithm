#include <stdio.h>
#include <algorithm>
 
using namespace std;
 
int N, K;
 
typedef struct COO{
    int x, y, num, flag;
}COO;
 
bool cmp(COO A, COO B){
    return A.num < B.num;
}
 
bool cmp1(COO A, COO B){
    if(A.x + A.y != B.x + B.y) return A.x + A.y < B.x + B.y;
    return A.x < B.x;
}
 
bool cmp2(COO A, COO B){
    if(A.x - A.y != B.x - B.y) return A.x - A.y < B.x - B.y;
    return A.x < B.x;
}
 
COO a[100003];
char Jump[100003];
int v[100003][4];
 
int f(int now, int jump){
    if(v[now][jump] == now) return now;
    for(int i = v[now][jump];; i = v[i][jump]){
        if(!a[i].flag){
            a[now].flag = 1;
            v[now][jump] = i;
            v[i][3-jump] = now;
            return i;
        }
        if(v[i][jump] == i) break;
    }
    return now;
}
 
int main(){
    int i,j;
 
    scanf("%d%d",&N, &K);
    scanf("%s",Jump);
    for(i=0;i<N;i++){
        scanf("%d%d",&a[i].x, &a[i].y);
        a[i].num = i;
        a[i].flag = 0;
    }
    for(i=0;i<N;i++) for(j=0;j<4;j++) v[i][j] = i;
     
    sort(a, a+N, cmp1);
    for(i=0;i<N-1;i++) if(a[i].x + a[i].y == a[i+1].x + a[i+1].y) v[ a[i].num ][ 1 ] = a[i+1].num;
    for(i=N-1;i>0;i--) if(a[i].x + a[i].y == a[i-1].x + a[i-1].y) v[ a[i].num ][ 2 ] = a[i-1].num;
     
    sort(a, a+N, cmp2);
    for(i=0;i<N-1;i++) if(a[i].x - a[i].y == a[i+1].x - a[i+1].y) v[ a[i].num ][ 0 ] = a[i+1].num;
    for(i=N-1;i>0;i--) if(a[i].x - a[i].y == a[i-1].x - a[i-1].y) v[ a[i].num ][ 3 ] = a[i-1].num;
 
    sort(a, a+N, cmp);
 
    int now = 0;
     
    for(i=0; i<K; i++){
        now = f(now, Jump[i] - 'A'); 
    }
    printf("%d %d\n",a[now].x, a[now].y);
 
    return 0;
}