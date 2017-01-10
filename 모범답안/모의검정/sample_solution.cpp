#include <iostream>
#include <algorithm>
using namespace std;

#define MAXN 100005
#define MAXE 300005

int N;		       // ����� ��
int M;	           // �̹� �����ϴ� ������ ��
int K;             // �߰��� �� �ִ� ������ ��
int edge_original[300000][3]; // �̹� �����ϴ� ���� ���� [i][0]<-->[i][1] �� �մ� weight [i][2]�� edge�� �ǹ�
int edge_addible[300000][3]; // �߰��� �� �ִ� ���� ����

long long Answer;  // �ּ� ���
int Uniqueness; // �������� ���� �����ϸ� 1, �������� ������ 0

/////////////////////////////////////

int E,mom[MAXN];

struct EDGE{
	int s,e,w;
	bool used,mark;
	bool operator < (const EDGE &ot)const{
		return w<ot.w;
	}
} edge[MAXE];

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
		���� �ʿ�
		*/
		cin >> N >> M >> K;
		for(i=0;i<M;i++){
			cin >> edge_original[i][0] >> edge_original[i][1] >> edge_original[i][2];
		}	
		for(i=0;i<K;i++){
			cin >> edge_addible[i][0] >> edge_addible[i][1] >> edge_addible[i][2];
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

		sort(edge+1,edge+E+1);
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
		cout << "#" << test_case << " " << Answer << " " << Uniqueness << endl;
	}

	return 0;//You have to return 0 for successful exit of the program.
}
