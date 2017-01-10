#include stdio.h
#include algorithm
#define minf(a,b) ((a)(b)(a)(b))
using namespace std;
 
int arr[11111], res[11111];
int to[111111], vis[11111];
 
int main() {
    int N; int ans = 0;
    scanf (%d,&N);
    for (int i=1;i=N;i++) { scanf (%d,arr+i); res[i] = arr[i]; }
    sort (res+1,res+1+N);
    for (int i=1;i=N;i++) to[res[i]] = i;
    for (int i=1;i=N;i++) {
        if (vis[i]) continue;
        if (res[i] == arr[i]) continue;
        int min = arr[i], sum = 0, len = 0;
        vis[i] = 1;
        sum += arr[i]; len ++;
        for (int j=to[arr[i]];j != i;j=to[arr[j]]) {
            min = minf (min, arr[j]), vis[j] = 1;
            sum += arr[j];
            len ++;
        }
        if (sum + (len-2)  min  sum + min + (len+1)  res[1]) ans += sum + min + (len+1)  res[1];
        else ans += sum + (len-2)  min;
    }
    printf (%d,ans);
    return 0;
}