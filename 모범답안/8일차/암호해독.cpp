#include <bits/stdc++.h>
using namespace std;
 
#define MAXN 300005
 
int N;
char S[MAXN], A[MAXN];
 
int main()
{
    scanf("%s", S); N = strlen(S);
    for (int b=0,c=0;b<5;b++){
        for (int i=b;i<N;i+=5){
            A[i] = (S[c++]-'a'-3+26)%26+'a';
        }
    }
    puts(A);
}
