#include <stdio.h>
  
int N, K;
int a[5000003];
 
int find(int left, int right){
    if(left == right) return a[left];
  
    int mid = a[ (left + right) >> 1] ;
    int i = left - 1, j = right + 1;
    int temp;
  
    while(1){
        while(a[++i] < mid);
        while(a[--j] > mid);
        if(i>=j) break;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
  
    if(i == j && i == K) return mid;
  
    if(K < i) return find(left, i-1);
    return find(j+1, right);
  
}
  
int main(){
 
    int i,j;
  
    scanf("%d%d",&N, &K);
    for(i=1;i<=N;i++) scanf("%d",&a[i]);
 
  
    printf("%d",find(1, N));
  
    return 0;
}