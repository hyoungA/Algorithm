import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileInputStream; // Method needed to use System.setIn

class Edge implements Comparable<Edge> {
	public Edge(int s, int e, int w) {
		super();
		this.s = s;
		this.e = e;
		this.w = w;
	}

	int s,e,w;
	boolean used,mark;

	@Override
	public int compareTo(Edge ot) {
		return w-ot.w;
	}
}

class DisjointSet {
	public DisjointSet(int n){
		mom  = new int[n+1];
		for (int i=1;i<=n;i++) mom[i] = i;
	}
	private int[] mom;
	
	public int getMom(int n){
		if (n == mom[n]) return n;
		mom[n] = getMom(mom[n]);
		return mom[n];
	}
	
	public boolean same(int a, int b){
		return getMom(a)==getMom(b);
	}
	
	public boolean same(Edge e){
		return same(e.s,e.e);
	}
	
	public void union(int a, int b){
		a = getMom(a); b = getMom(b);
		if (a == b) return;
		mom[b] = a;
	}
}

/*
  The class name has to be Solution, so we recomment using Solution.java.
  You can test your program with the command java Solution.
*/
class Solution {
	final static int T = 50;
	static int N;		       // 노드의 수
	static int M;	           // 이미 존재하는 에지의 수
	static int K;             // 추가가능한 에지의 수
	static int edge_original[][];  // 이미 존재하는 에지 정보 [i][0]<-->[i][1] 을 잇는 weight [i][2]인 edge를 의미
	static int edge_addible[][];  // 추가할 수 있는 에지 정보
	static long Answer;  // 최소 비용
	static int Uniqueness; // 유일한지 여부 유일하면 1, 유일하지 않으면 0
	
	///////////////////////////////////////////
	
	public static void main(String args[]) throws Exception {
		/*
		 The method call below opens input.txt in read only mode and 
	           sets your standard input System.in to work with the opened file. 
	           When you test your code with the sample data, you can use the function
	           below to read in from the sample data file instead of the standard input.
	           So. you can uncomment the following line for your local test. But you
	           have to comment the following line when you submit for your scores.
		*/
	    // System.setIn(new FileInputStream("input.txt"));

		/*
		   Makes a Scanner from standard input System.in and reads data.
		*/
		Scanner sc = new Scanner(System.in);
		for(int test_case = 1; test_case <= T; ++test_case){
			/*
			 Reads one case from standard input. 
		           The meaning of variables N, K, M are given above.
			 How[i][j] indicates the place where the piece at the j+1'th place appears after one press of the i+1'th button
			*/
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			
			edge_original = new int[M][3];
			edge_addible = new int[K][3];
			
			for (int i=0;i<M;i++){
				edge_original[i][0] = sc.nextInt();
				edge_original[i][1] = sc.nextInt();
				edge_original[i][2] = sc.nextInt();
			}
			for (int i=0;i<K;i++){
				edge_addible[i][0] = sc.nextInt();
				edge_addible[i][1] = sc.nextInt();
				edge_addible[i][2] = sc.nextInt();
			}
			///////////////////////////////////////////////////////////////////////////////////////
			
			int i,j;
			ArrayList<Edge> edge = new ArrayList<Edge>();
			Answer = 0;
			for (i=0;i<M;i++){
				int s=edge_original[i][0],e=edge_original[i][1],w=edge_original[i][2];
				edge.add(new Edge(s,e,-w));
				Answer += w;
			}
			for (i=0;i<K;i++){
				int s=edge_addible[i][0],e=edge_addible[i][1],w=edge_addible[i][2];
				edge.add(new Edge(s,e,w));
			}
			Collections.sort(edge);
			DisjointSet ds = new DisjointSet(N);
			int E=edge.size();
			for (i=0;i<E;i++){
				Edge e = edge.get(i);
				if (i == 0 || edge.get(i-1).w != edge.get(i).w){
					for (j=i;j<E&&edge.get(i).w==edge.get(j).w;j++){
						if (!ds.same(edge.get(j))) edge.get(j).mark = true;
					}
				}
				if (ds.same(e)) continue;
				Answer += e.w;
				ds.union(e.s, e.e);
				e.used = true;
			}
			Uniqueness = 1;
			for (Edge e: edge){
				if (e.used != e.mark) Uniqueness = 0;
			}
	
			///////////////////////////////////////////////////////////////////////////////////////

			// Prints out the answer to standard output.
			System.out.println("#" + test_case + " " + Answer + " " + Uniqueness);
		}
	}
}
