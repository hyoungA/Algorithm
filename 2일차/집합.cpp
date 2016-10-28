#include <bits/stdc++.h>
using namespace std;
 
int N, M;
int A[1001], B[1001];
int D[1001][1001];
 
void put_min(int &t, int v){ t = min(t, v); }
 
int main()
{
    scanf("%d%d", &N, &M);
    for (int i=1;i<=N;i++) scanf("%d", A+i);
    for (int i=1;i<=M;i++) scanf("%d", B+i);
    if (N > M){
        swap(N, M);
        for (int i=1;i<=M;i++) swap(A[i], B[i]);
    }
    sort(A+1, A+N+1);
    sort(B+1, B+M+1);
    for (int i=0;i<=N;i++) for (int j=0;j<=M;j++) D[i][j] = 2e9;
    D[0][0] = 0;
    for (int i=0;i<=N;i++) for (int j=0;j<=M;j++) if (D[i][j] < 2e9){
        if (j < M) put_min(D[i][j+1], D[i][j]);
        if (i < N && j < M) put_min(D[i+1][j+1], D[i][j] + abs(A[i+1]-B[j+1]));
    }
    printf("%d\n", D[N][M]);
}