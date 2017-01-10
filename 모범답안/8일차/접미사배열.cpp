#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 1001
 
int N;
int A[MAXN];
char S[MAXN];
 
int main()
{
    scanf("%s", S); N = strlen(S);
    for (int i=0;i<N;i++) A[i] = i;
    sort(A, A+N, [](const int &a, const int &b){
        return strcmp(S+a, S+b) < 0;
    });
    for (int i=0;i<N;i++) puts(S+A[i]);
}
