#include bitsstdc++.h
using namespace std;
 
#define MAXN 1000006
typedef long long lld;
 
int N, K, M, X, Y;
int A[MAXN];
lld S[MAXN], B[99], C[99], D[99], E[99];
 
int main()
{
    scanf(%d%d, &N, &K);
    for (int i=1;i=N;i++) scanf(%d, A+i), S[i] = S[i-1] + A[i];
    B[M=1] = 0;
    for (int i=1;i=N;i++){
        for (int j=1;j=M;j++) D[j] = S[i] - B[j];
        for (int j=1;j=X;j++) E[j] = C[j];
        Y = X; X = 0;
        for (int j=1,k=1;(j=Mk=Y)&&XK;){
            if (k  Y  j = M && D[j] = E[k])
                C[++X] = D[j++];
            else
                C[++X] = E[k++];
        }
        B[M+1] = 1e18;
        for (int j=1;j=M+1;j++) if (B[j] = S[i]){
            for (int k=M;k=j;k--) B[k+1] = B[k];
            B[j] = S[i];
            break;
        }
        if (M  K) M++;
    }
    printf(%lldn, C[K]);
}