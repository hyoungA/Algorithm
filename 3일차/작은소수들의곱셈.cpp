#include bitsstdc++.h
using namespace std;
 
typedef long long lld;
 
int T, N;
lld A[5843];
 
int main()
{
    A[++N] = 1;
    while (N  5842){
        lld nxt = 1e18;
        for (int i=1;i=N;i++){
            for (int t {2, 3, 5, 7}){
                lld v = A[i]t;
                if (A[N]  v && nxt  v) nxt = v;
            }
        }
        A[++N] = nxt;
    }
    for (scanf(%d, &T);T--;){
        scanf(%d, &N);
        printf(%lldn, A[N]);
    }
}