#include <stdio.h>
#include <vector>
#include <algorithm>
using namespace std;

#define MAXN 100005
#define MAXE 300005
typedef long long lld;

int N;		       // 노드의 수
int M;	           // 이미 존재하는 에지의 수
int K;             // 추가할 수 있는 에지의 수
int edge_original[300000][3]; // 이미 존재하는 에지 정보 [i][0]<-->[i][1] 을 잇는 weight [i][2]인 edge를 의미
int edge_addible[300000][3]; // 추가할 수 있는 에지 정보

long long Answer;  // 최소 비용
int Uniqueness; // 유일한지 여부 유일하면 1, 유일하지 않으면 0

/////////////////////////////////////

int E; lld sec;
int P[MAXN],H[MAXN],mom[MAXN][17],maxe[MAXN][17];
int que[MAXN],head,tail;
vector <int> con[MAXN],conv[MAXN];

struct EDGE{
	int s,e,w;
	bool chk;
	bool operator < (const EDGE &ot)const{
		return w<ot.w;
	}
} edge[MAXE];


int find(int n){ return n==P[n]?n:(P[n]=find(P[n])); }

void bfs()
{
	int i,j,k,q;
	head = tail = 0;
	for (i=0;i<17;i++) for (j=1;j<=N;j++) mom[j][i] = 0;
	mom[1][0] = 1;
	que[tail++] = 1;
	for (i=1;i<=N;i++) for (j=0;j<17;j++) maxe[i][j] = -1e9;
	while (head != tail){ 
		q = que[head++];
		for (i=con[q].size();i--;){
			k = con[q][i];
			if (!mom[k][0])
				mom[k][0] = q, maxe[k][0] = conv[q][i], H[k] = H[q]+1, que[tail++] = k;
		}
	}
	for (i=1;i<17;i++) for (j=1;j<=N;j++) mom[j][i] = mom[mom[j][i-1]][i-1], maxe[j][i] = max(maxe[j][i-1],maxe[mom[j][i-1]][i-1]);
}

int get_max_edge(int a,int b)
{
	if (H[a] < H[b]) swap(a,b);
	int i,k,ret=-1e9;
	for (i=0,k=H[a]-H[b];k;i++,k>>=1)
		if (k&1) ret = max(ret,maxe[a][i]), a = mom[a][i];
	if (a == b) return ret;
	for (i=17;i--;) if (mom[a][i] != mom[b][i]){
		ret = max(ret,maxe[a][i]);
		ret = max(ret,maxe[b][i]);
		a = mom[a][i], b = mom[b][i];
	}
	ret = max(ret,maxe[a][0]);
	ret = max(ret,maxe[b][0]);
	return ret;
}

int main(void)
{
	const int T = 50;
	int test_case;
	/* The freopen function below opens input.txt in read only mode and 
	sets your standard input to work with the opened file. 
	When you test your code with the sample data, you can use the function
	below to read in from the sample data file instead of the standard input.
	So. you can uncomment the following line for your local test. But you
	have to comment the following line when you submit for your scores.
	*/
	 freopen("input.txt", "r", stdin);

	/* If you do not use the following function, your output may not be correctly
	applied to standard output if your program is killed for time limit violation.
	So, be sure to keep the following function in your code.
	*/
	setbuf(stdout, NULL);

	/*
	Code to loop through all test cases
	*/
	for(test_case = 1; test_case <= T; ++test_case)
	{
		int i;
		/*
		수정 필요
		*/
		scanf("%d%d%d",&N,&M,&K);
		for(i=0;i<M;i++){
			scanf("%d%d%d",&edge_original[i][0],&edge_original[i][1],&edge_original[i][2]);
		}	
		for(i=0;i<K;i++){
			scanf("%d%d%d",&edge_addible[i][0],&edge_addible[i][1],&edge_addible[i][2]);
		}	
		////////////////////////////////////////////////////////////////////////////////////////////

		Answer = 0; sec = 1e16; E = 0;
		for (i=0;i<M;i++){
			edge[++E].s = edge_original[i][0], edge[E].e = edge_original[i][1], edge[E].w = -edge_original[i][2];
			Answer += edge_original[i][2];
		}
		for (i=0;i<K;i++){
			edge[++E].s = edge_addible[i][0], edge[E].e = edge_addible[i][1], edge[E].w = edge_addible[i][2];
		}
		for (i=1;i<=E;i++) edge[i].chk = 0;
		sort(edge+1,edge+E+1);
		for (i=1;i<=N;i++) P[i] = i, H[i] = 0;
		for (i=1;i<=N;i++) con[i].clear(), conv[i].clear();

		for (i=1;i<=E;i++){
			int a = find(edge[i].s), b = find(edge[i].e);
			if (a == b) continue;
			con[edge[i].s].push_back(edge[i].e); conv[edge[i].s].push_back(edge[i].w);
			con[edge[i].e].push_back(edge[i].s); conv[edge[i].e].push_back(edge[i].w);
			P[b] = a;
			Answer += edge[i].w;
			edge[i].chk = 1;
		}
		bfs();
		for (i=1;i<=E;i++) if (!edge[i].chk){
			sec = min(sec,Answer+edge[i].w-get_max_edge(edge[i].s,edge[i].e));
		}
		Uniqueness = Answer != sec;

		/////////////////////////////////////////////////////////////////////////////////////////////


		// Prints out the answer to standard output
		printf("#%d %lld %d\n", test_case, Answer, Uniqueness);
	}

	return 0;//You have to return 0 for successful exit of the program.
}
