#include <stdio.h>
#include <queue>
#include <functional>
 
using namespace std;
 
priority_queue<int> q1;
priority_queue<int, vector<int>, greater<int>> q2;
 
int mid;
int N;
 
int main() {
    int i;
    int x, y;
     
    scanf("%d", &N);
     
    for (i = 0; i < N; i ++) {
        scanf("%d", &x);
        if (i == 0) mid = x;
        else {
            if (x < mid) q1.push(x);
            else q2.push(x);
        }
 
        if (!(i & 1)) {
            while (q1.size() > q2.size()) {
                q2.push(mid);
                mid = q1.top();
                q1.pop();
            }
            while (q1.size() < q2.size()) {
                q1.push(mid);
                mid = q2.top();
                q2.pop();
            }
            printf("%d\n", mid);
        }
 
    }
    return 0;
 
}