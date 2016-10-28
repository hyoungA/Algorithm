#include <cstdio>
#include <queue>
 
using namespace std;
 
queue<int> q;
 
int main() {
    int N, K;
 
    scanf("%d%d", &N, &K);
 
    for (int i = 1; i <= N; i++) q.push(i);
 
    while (--N) {
        printf("%d ", q.front());
        q.pop();
        for (int i = 0; i < K-1; i++) q.push(q.front()), q.pop();
    }
    printf("%d\n", q.front());
}