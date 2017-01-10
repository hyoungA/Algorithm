import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 
문제 : 동맹의 동맹은 동맹

낙성마을에는 N명의 사람이 있다. 편의상 각 사람들을 1번부터 N번까지 번호를 매기도록 하자. 
처음 이 사람들은 서로를 모르기 때문에, '적대 관계'를 갖고 있다. 
하지만 언제까지나 '적대 관계'로 살아갈 수는 없는 법이다. 이 마을의 사람들은 한 두명씩 '동맹 관계'를 맺기로 하였다. 
당연히 어떤 사람 A와 B가 동맹 관계를 맺으면, 자연스럽게 A의 동맹들과 B의 동맹들도 서로 동맹 관계를 맺게 된다. 
이런 관계들이 많아지다보니 점점 더 복잡한 구조의 동맹 관계가 구성되게 되었다. 누가 누구와 동맹 관계인지 확실히 알기 위해, 이런 관계를 찾아내는 프로그램을 작성하기로 하였다.

동맹 관계를 쉽게 알아내는 프로그램을 작성하시오.

입력
첫 번째 줄에 낙성마을의 사람의 수 N이 주어진다. (1 ≤ N ≤ 100,000)
두 번째 줄에 질의의 수 Q가 주어진다. (1 ≤ Q ≤ 200,000)
세 번째 줄부터 Q개의 줄에 걸쳐 질의가 주어진다. 각 질의는 다음의 형태 중 하나로 주어진다. (1 ≤ a, b ≤ N)
- 0 a b : a번 사람과 b번 사람이 동맹 관계를 맺었음을 의미하는 질의이다.
- 1 a b : a번 사람과 b번 사람이 동맹 관계에 있는지 물어보는 질의이다.

출력
1로 시작하는 모든 질의에 대해, 각 줄에 하나씩 동맹 관계가 아니면 0, 동맹 관계이면 1을 출력한다.

입력 예제
7 
9
0 1 3
1 1 7
0 7 6
1 3 7
0 3 7
0 4 2
0 1 3
1 1 7
1 1 1


출력 예제
0
0
1
1

Think
- 최소에는 모든 사람들의 root 를 본인으로 지정한다.
- 부모를 저장할 배열과, level(depth) 을 저장할 배열을 사용한다.
- 동맹을 맺으려 하면 가장 먼저, 자기 자신의 root 를 찾는다. 
  - root 를 찾으러 이동하면서, 만난 리스트 들에 대해서 가지고 있다가 부모의 값을 다 root 로 수정해준다.
- 동맹을 맺는 대상의 root 도 찾는다.
  - root 를 찾으러 이동하면서, 만난 리스트 들에 대해서 가지고 있다가 부모의 값을 다 root 로 수정해준다.
- 만약 root 가 동일하면, 이미 동맹 관계 이므로 상관 없음
- 만약 root 가 다르면, 두 root 중 depth 가 작은 쪽 root 에서 depth 가 큰 쪽 root 로 부모를 지정해준다. 
   다시 depth 는 Max[ Adepth + 1, Bdepth ] 이다.

 */
public class Clan_20160922 {
	static int N, Q; // 사람 수 , 질의
	static int[] people;
	static int[] parent;
	static int[] depth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		
		N = Integer.parseInt(br.readLine()); // 사람 
		Q = Integer.parseInt(br.readLine()); // 질의
		
		// 초기화
//		people = new int[N + 1];
		parent = new int[N + 1]; // 인덱스가 사람의 번호, 값이 parent
		depth = new int[N + 1]; // depth 는 초기 값이 0 이므로 패스
		
		for(int i=1;i<=N;i++){
			parent[i] = i;
		}
		
		// 질의 입력 받아 답하기
		for(int i=0;i<Q;i++){
			String line = br.readLine();
			String[] tmp = line.split(" ");
			
			int command = Integer.parseInt(tmp[0]); // 0 : 동맹, 1 : 질의 
			int a = Integer.parseInt(tmp[1]); // 동맹 맺는 쪽
			int b = Integer.parseInt(tmp[2]); // 동맹 대상
			
			if(command == 1){
				// 동맹 여부 체크
				if(find(a) == find(b)){
					System.out.println(1);
				}
				else {
					System.out.println(0);
				}
			}
			else {
				// 동맹 맺기
				union(a,b);
			}
		}
	}
	
	// root 찾는 함수
	static int find(int x){
		if(parent[x] == x){
			return parent[x];
		}
		else {
			return find(parent[x]);
		}
	}
	
	// 동맹 맺기
	static void union(int a, int b){
		int aParent = find(a);
		int bParent = find(b);
		
		if(aParent == bParent)
			return;
		
		if(depth[aParent]<depth[bParent]){
			parent[aParent] = bParent;
		}
		else{
			parent[bParent] = aParent;
			depth[aParent]++;
		}
	}
}
