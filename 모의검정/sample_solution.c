#include <stdio.h>
#include <stdlib.h>

#define MAXN 100005
#define MAXE 300005

int N;		       // 노드의 수
int M;	           // 이미 존재하는 에지의 수
int K;             // 추가할 수 있는 에지의 수
int edge_original[300000][3]; // 이미 존재하는 에지 정보 [i][0]<-->[i][1] 을 잇는 weight [i][2]인 edge를 의미
int edge_addible[300000][3]; // 추가할 수 있는 에지 정보

long long Answer;  // 최소 비용
int Uniqueness; // 유일한지 여부 유일하면 1, 유일하지 않으면 0

/////////////////////////////////////

int E,mom[MAXN];

typedef struct {
    int s,e,w;
    int used,mark;
} EDGE; EDGE edge[MAXE];

int cmp(const void *a,const void *b)
{
    return ((EDGE*)a)->w-((EDGE*)b)->w;
}

int find(int n){ return n==mom[n]?n:(mom[n]=find(mom[n])); }

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
	// freopen("input.txt", "r", stdin);

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
		int i,j,a,b;
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
		
		Answer = E = 0;
		Uniqueness = 1;
		for (i=0;i<M;i++){
			edge[++E].s = edge_original[i][0], edge[E].e = edge_original[i][1], edge[E].w = -edge_original[i][2];
			Answer += edge_original[i][2];
		}
		for (i=0;i<K;i++)
			edge[++E].s = edge_addible[i][0], edge[E].e = edge_addible[i][1], edge[E].w = edge_addible[i][2];

		for (i=1;i<=E;i++)
			edge[i].used = edge[i].mark = 0;

		qsort(edge+1,E,sizeof(EDGE),cmp);
		for (i=1;i<=N;i++) mom[i] = i;
		for (i=1;i<=E;i++){
			if (i == 1 || edge[i-1].w != edge[i].w){
				for (j=i;j<=E&&edge[i].w==edge[j].w;j++){
					if (find(edge[j].s) != find(edge[j].e)) edge[j].mark = 1;
				}
			}
			a = find(edge[i].s), b = find(edge[i].e);
			if (a == b) continue;
			mom[b] = a;
			Answer += edge[i].w;
			edge[i].used = 1;
		}
		for (i=1;i<=E;i++) if (edge[i].used != edge[i].mark){
			Uniqueness = 0;
			break;
		}

		/////////////////////////////////////////////////////////////////////////////////////////////


		// Prints out the answer to standard output
		printf("#%d %lld %d\n", test_case, Answer, Uniqueness);
	}

	return 0;//You have to return 0 for successful exit of the program.
}
