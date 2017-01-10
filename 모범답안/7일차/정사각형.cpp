#include <bits/stdc++.h>
using namespace std;
 
int N;
int A[252][252], B[252];
 
int main()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) for (int j=1;j<=N;j++) scanf("%d", A[i]+j), A[i][j] += A[i][j-1];
    int ans = 0;
    for (int s=2;s<=N;s++){
        for (int j=s;j<=N;j++){
            for (int i=1;i<=N;i++){
                if (A[i][j]-A[i][j-s] == s) B[i] = B[i-1]+1;
                else B[i] = 0;
                if (B[i] >= s) ans++;
            }
        }
    }
    printf("%d\n", ans);
}
