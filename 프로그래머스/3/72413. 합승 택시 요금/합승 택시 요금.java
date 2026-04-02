import java.util.*;
class Solution {
    int[][] dist;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0; //자기자신은 0
        }
        Map<Integer, List<int[]>> map = new HashMap<>();//간선만들기
        for (int[] f : fares) {
            map.computeIfAbsent(f[0], k -> new ArrayList<>()).add(new int[] {f[1], f[2]});
            map.computeIfAbsent(f[1], k -> new ArrayList<>()).add(new int[] {f[0], f[2]});
        }
        
        for (int i = 1; i <= n; i++) {
            //이제 각 dist[i]에 대해서 i를시작지점으로 했을때 최단거리 구하기
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);
            //위치,최소거리
            pq.offer(new int[] {i, 0});//
            while (!pq.isEmpty()) {
                int[] polled = pq.poll();
                int pos = polled[0];
                int money = polled[1];//현재 금액
                if (dist[i][pos] < money) continue;
                if (map.get(pos) == null) continue;
                for (int[] nei : map.get(pos)) {
                    if (money + nei[1] < dist[i][nei[0]]) {
                        dist[i][nei[0]] = money + nei[1];
                        pq.offer(new int[] {nei[0], money + nei[1]});
                    }
                }
            }
        }
        //이제 모든지점을 시작으로했을떄 각지점까지의 거리를 알겠다..
        int min = Integer.MAX_VALUE;
        //그니까 시작지점 -> 특정지점 -> A/B이렇게
        for (int i = 1; i <= n; i++) {
            min = Math.min(
                dist[s][i] + dist[i][a] + dist[i][b],
                min
            );
        }
        return min;
        
    }
}
/** 
노드 200
간선 (N^2 + N) / 2
이제...한지점에서..합승..합승이란 무엇일까
=..이것이 다익스트라 문제를 어떻게 할가.
그니까 쉽게 보면.. 목적지가2개인거임. 시작점에서 목적지두개로 다익스트라..
흠.........그러면 이게 아 합승을 하냐 안하냐도 갈림길이니까 백트래킹도있는건가..
지금 머릿속에 시나리오를 못그리니까 코드로 못옮기는것.
그러면 음.. dist[]는 각각간것 더한거랑 같이간거랑 해가지고 하는거아님?
그러면 으악 너무 어렵
만약 목표지점이 하나면..그냥 돌려버리면 되는데
목표지점이 두개야.
아근데 이거 무조건 들르는게 아니라 그냥 갈라져버리는거잖아 그치
그러면 둘다 각자 가되 최대한 겹치게 가면되는거아님? 안겹치면 그냥 빠이?
1번문제기준..
아 그러면........ 모든지점에서 A까지 최단거리 + B까지 최단거리를 구해.
그ㅡ리고 이제 시작지점에서 그 지점까지 거리를 알면..
시작지점에서 모든지점까지 거리 + 각 지점에서 A거리 + B거리하면.. 돈의 합임
어?
그니가 dist[1] = 1지점에서 A+B
그다음에..s에서 1지점까지 거리구해..그러면됨

그니까 각지점에서 각각 dist를구해.
그다음에 그 지점에서 A + B 하면 거기를 하차지점으로 해서 시작할 떄 요금알수있음.
아 그러니까 이문제는..그냥 모든 지점을 시작점으로 했을때 dist를 여러개 다 가지고있어야겟네
원래 dist[n]는 시작점고정일때 n까지 최소거리였잖아
근데 이제 확장해서 dist[k][n] 해버리면.. k를 시작점으로 할때 n까지의 거리가 되는거지
그러면 dist[k]는 k를 시작으로했을떄 모든그거를.. 아 ㅋㅋㅋㅋㅋ천재네
이렇게하잖아? 그러면 이제 s를 시작으로했을떄로 해가지고... 중간지점을 h라고하면
s+h 그다음에 dist[h]해가지고 dist[h][a] dist[h][b]하면됨ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ


**/