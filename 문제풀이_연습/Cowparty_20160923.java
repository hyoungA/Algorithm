/*
 * 
문제

N 개 농장의 (편의상 1 ,2 .. , N) 의 대표 암소 한 마리가, X ( 1 <= X <= N) 농장에서 열리는 파티에 참석하려고 한다. 
농장들은 단방향의 길들로 연결되어 있으며, 각 길마다 걸어가는데 걸리는 시간이 주어진다.
모든 소들은 파티에 걸어가야 하고 파티가 마친 후에는 자기가 속한 농장으로 돌아와야 한다. 
모든 소들은 게을러서 가장 최단시간으로 올수 있는 최적의 길을 선택하려고 한다.
모든 소 들 중에서 농장으로 갔다가 돌아오는 데 가장 많이 걸리는 소의 시간은 얼마인가?
시간제한 : 0.5 초

입력
- 첫 번째 줄 : 정수 N , M , X 가 주어진다. (1<=N<=1000)는 농장의 수, (1<=M<=N*(N-1))은 도로의 갯수, (1<=X<=N)는 파티가 열리는 농장이다.
- 두 번재 줄에서 M+1 번째 줄 : i+1 번째 줄은 세 정수 Ai,Bi,Ci 가 주어진다. Ai 에서 Bi 로 가는데 Ti 시간이 소요된다는 것이다.

출력


파티에 참석했다 돌아오는 소 들 중 가장 긴 시간을 출력한다.


힌트


입력 예제
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3


출력 예제
10


4 -> 2 ( 3 )

2 -> 1 -> 3 -> 4 (7)

총 거리합은 10이고, 이 때가 가장 긴 경우이다.

Solution
- 단방향 그래프
- X 정점에서 S 라는 정점으로 왔다가는 최단거리의 최대값
- 정방향에서의 최단경로와 역방향에서의 최단경로가 같다. 역방향으로 체크해본다.

 * 
 */
public class Cowparty_20160923 {

}
