#include <bits/stdc++.h>
using namespace std;
 
/*
 * List of Types
 * Set : 0
 * ElementList : 1
 * List : 2
 * Element : 3
 * Atom : 4
 */
 
int T, N;
int cache[202][202][5];
char A[202];
 
int check(int s, int e, int t)
{
    int &ret = cache[s][e][t];
    if (ret >= 0) return ret;
    ret = 0;
    if (t == 0){
        // is set?
        if (A[s] == '{' && A[e] == '}' && check(s+1, e-1, 1)) ret = 1;
    }else if (t == 1){
        // is elementlist?
        if (s > e || check(s, e, 2)) ret = 1;
    }else if (t == 2){
        // is list?
        if (check(s, e, 3)) ret = 1;
        else{
            for (int i=s+1;i<e;i++) if (A[i] == ','){
                if (check(s, i-1, 3) && check(i+1, e, 2)){ ret = 1; break; }
            }
        }
    }else if (t == 3){
        // is element?
        if (check(s, e, 4) || check(s, e, 0)) ret = 1;
    }else{
        // is atom?
        if (s == e) ret = 1;
    }
    return ret;
}
 
int main()
{
    for (scanf("%d", &T);T--;){
        scanf("%s", A+1); N = strlen(A+1);
        for (int i=1;i<=N;i++) for (int j=1;j<=N;j++) for (int k=0;k<5;k++) cache[i][j][k] = -1;
        static int ts = 0;
        printf("Word #%d: %s\n", ++ts, check(1, N, 0) ? "Set" : "No Set");
    }
}
