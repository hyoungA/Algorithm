#include <stdio.h>
 
int K, N;
 
int S;
int A[103];
int pt[103];
 
long long numbers[100003];
 
long long process() {
    long long last = 1;
    numbers[S ++] = 1;
 
    while (S <= N) {
        long long now = 2147483647;
        now *= now;
        for (int i = 0; i < K; i ++) {
            while (numbers[pt[i]] * (long long) A[i] <= last) {
                pt[i] ++;
            }
            if (now > numbers[pt[i]] * (long long) A[i]) {
                now = numbers[pt[i]] * A[i];
            }
        }
        numbers[S ++] = now;
        last = now;
    }
    return last;
}
 
int main() {
    scanf( "%d %d", &K, &N );
    for (int i = 0; i < K; i ++) {
        scanf( "%d", &A[i] );
    }
    printf( "%lld\n", process() );
 
    return 0;
}