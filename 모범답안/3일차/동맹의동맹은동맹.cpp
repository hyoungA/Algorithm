#include <stdio.h>
 
int N;
 
int group[100003];
 
int find(int x){
    if(group[x] == x) return x;
     
    group[x] = find(group[x]);
 
    return group[x];
}
 
int main(){
     
    int x, a, b;
    int A, B;
 
    scanf("%d",&N);
 
    for(int i=1;i<=N;i++)
        group[i] = i;
 
    int M; scanf("%d",&M); while(M--){
        scanf("%d%d%d",&x,&a,&b);
 
        A = find(a);
        B = find(b);
 
 
        if(x==0)
            group[A] = group[B];
         
        else 
            printf("%d\n",A==B?1:0);
    }
    return 0;
}