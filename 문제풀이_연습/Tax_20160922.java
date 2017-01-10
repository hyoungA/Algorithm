import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 
문제

납세의 의무는 국민의 기본적인 의무이다. 세금은 수입에 비례하여 정해진 규칙에 따라 계산되기 때문에, 규칙에 따라서 정확한 수입을 계산하는 것이 중요하다. 
여러분은 새로 가게를 열고, 총 N 일 동안 영업을 하였다. 납세의 의무를 성실하게 수행하기 위하여 매 영업일마다 손익(이익 또는 손해)을 기록하여 세무서에 신고하였다. 
세금을 매기는데 기준이 되는 수입은 다음 규칙에 의해서 계산된다.

"1일부터 N일 사이의 어떤 연속된 기간에 대하여 이 기간 동안 손익의 총합을 구한다. 단, 하루 이상의 기간만 고려한다. 
다음, 전체 가능한 모든 기간에 대하여 구한 손익의 총합들 중 K번째로 큰 값이 기준이 된다. 즉, 총합들을 내림차순으로 정렬했을 때 K번째 값이다."
예를 들어, 총 3일간 영업을 하였다면 1일, 1일~2일, 1일~3일, 2일, 2일~3일, 3일 총 6가지 기 간에 대하여 각 기간마다 손익의 총합을 구하고, 이 중 K번째 큰 값이 세금의 기준이 된다.

입력
첫 번째 줄에는 N과 K가 사이에 하나의 공백을 두고 주어진다. 단, 1≤N≤1,000,000, 1≤K≤min(50, N(N+1)/2) 이다.
다음 줄에는 매 영업일의 손익을 나타내는 N개의 정수가 사이에 하나 의 공백을 두고 주어진다. 만약 해당 영업일에 이익을 보았다면 양의 값, 손해를 보았다면 음의 값, 이익 손해 모두 없다면 0이다. 
손익의 범위는 -1,000,000,000 부터 1,000,000,000 사이이다.

출력
조건을 만족하는 손익의 총합이 K번째로 큰 값을 나타내는 하나의 정수를 출력한다.


힌트


입력 예제
4 3
20 -10 10 5


출력 예제
20

Think
- N = 4 일 경우에 큐 사이즈는 4 * 5 / 2 에 따라서 10 이 될텐데, 큰 값만을 찾는 작업을 하면 문제가 생김. 
  그렇다고, 값들을 다 저장하는 것이 필요한 것인지.. 4 의 경우 10을 구하는 경우는 모든 케이스를 다 저장해야 함. 어떻게 저장??

Solution
- idea1 : K 가 작다. -> O(NK) 로 문제를 풀려는 노력을 해야 함.
- idea2 : 배열 2개를 잡고, 
          G : i번째 입력을 마지막으로 하는 구간의 합들이 내림차순으로 정렬되어 있는 리스트(배열) 하나와, 
          Ans : 전체에 대해 ( 어디든 마지막으로 하는 ) 내림차순으로 정렬되어 있는 리스트(배열) 이 필요. 
- G 배열 에는 a[i] 의 위치를 찾아서 넣어준다. ( 큰순으로 정확히 K개만 가져간다. )
- Ans (정답배열) : G 와  Ans 를 머지소트한다. 두 배열의 
- 머지소트를 하는 과정을 이용하면 문제가 풀린다.

 */
public class Tax_20160922 {
	static int N,K;
	
	static long[] g;
	static int gSize;
	static Queue<Long> a;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String []tmp = line.split(" ");
		
		// 입력값
		N = Integer.parseInt(tmp[0]);
		K = Integer.parseInt(tmp[1]);
		
		//초기화
		int qSize = Math.min(50, (N*(N+1))/2);
		a = new PriorityQueue<Long>(qSize);
		g = new long[51];
		
		for(int i=0;i<g.length;i++){
			g[i] = Long.MIN_VALUE;
		}
		
		// 입력값들은 하나하나 처리
		line = br.readLine();
		tmp = line.split(" ");
		
		for(int i=1;i<=N;i++){
			long current = (long)Integer.parseInt(tmp[i-1]);
			
			// g 배열에 현재 값을 더해준다.
			if(gSize == 0){
				g[gSize++] = current;
			}
			else {
				// 각 값을 노드에 넣어주고
				addCurrent(current);
				// 현재 값을 g 배열에 위치시킨다.
				putCurrent(current);
			}
			
			// 리스트를 pq 에 삽입한다.
			addAnswer();
		}
		
		long result = 0;
		
		for(int i =0;i<K;i++){
			result = a.poll();
		}
		
		System.out.println(result);
	}
	
	static void addAnswer (){
		for(int i=0;i<gSize;i++){
			a.add(g[i]);
		}
	}
	
	static void addCurrent(long current){
		for(int i=0;i<gSize;i++){
			g[i] += current;
		}
	}
	
	// current 의 값을 넣을 위치를 찾아서 넣어준다.
	// 하나씩 밀기 위해서 current 의 위치를 찾는다. current 보다 작은 값이 나오면 그 위치가 넣는 위치이다.
	// lastIndex 부터 , findIndex + 1 인덱스까지 값을 변경처리 해준다.
	static void putCurrent(long current){
		int findIndex = -1;
		int lastIndex = Math.min(51, gSize + 1);
		
		for(int i=0;i<lastIndex;i++){
			// i 위치에 current 를 위치시킨다.
			if(g[i] < current){
				findIndex = i;
			}
		}
		
		for(int i=lastIndex;i>findIndex;i--){
			g[i+1] = g[i];
		}
		
		g[findIndex] = current;
	}
}
