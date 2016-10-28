#include <bits/stdc++.h>
using namespace std;
 
int T, A, B;
int D[10000];
bool is_prime[10000];
 
int main()
{
    for (int i=1001;i<10000;i+=2){
        is_prime[i] = 1;
        for (int j=2;j*j<=i;j++) if (i % j == 0){
            is_prime[i] = 0;
            break;
        }
    }
    for (scanf("%d", &T);T--;){
        for (int i=0;i<10000;i++) D[i] = 2e9;
        scanf("%d%d", &A, &B);
        queue <int> que;
        D[A] = 0; que.push(A);
        while (!que.empty()){
            int q = que.front(); que.pop();
            for (int i=0;i<10;i++){
                for (int b=1;b<10000;b*=10){
                    int v = q / b / 10 * b * 10 + (q % b) + b * i;
                    if (v < 1000 || !is_prime[v] || D[v] < 2e9) continue;
                    D[v] = D[q]+1; que.push(v);
                }
            }
        }
        printf("%d\n", D[B]);
    }
}