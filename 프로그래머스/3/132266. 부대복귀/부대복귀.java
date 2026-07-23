import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] r : roads) {
            int v1 = r[0];
            int v2 = r[1];
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        //다익스트라
        int[] dist = new int[n + 1];//destination -> 노드인디.. dist[2] -> 2번이라
        //n번하려면 n+1필요
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;//dest -> dest
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);//int[] {노드, 거리} 인데..사실 필요한가 그냥 어차피 거리1 아님? 가중치가 사실상 읎는데 왜냐면 dest기준거리를 들고다니는 게 아니라 새로운 돌아다닐 지점인데...일단 해보자
        //아닌가 dest기준으로 처리만 먼저하는식으로?ㄱㄱ<<이걸로
        pq.offer(new int[] {destination, 0});
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            if (dist[polled[0]] < polled[1]) continue;
            dist[polled[0]] = polled[1];
            for (int nei : graph.get(polled[0])) {
                if (dist[nei] <= polled[1] + 1) continue;
                pq.offer(new int[] {nei, polled[1] + 1});//TODO질문..갱신은어디서
                dist[nei] = polled[1] + 1;
            }
        }
        // System.out.printf("debug\n");
        // for (int d : dist) System.out.printf("%d\n", d);
        
        int[] res = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            // System.out.printf("i:%d, dist[%d] = %d\n", i, sources[i], dist[sources[i]]);
            if (dist[sources[i]] >= Integer.MAX_VALUE) res[i] = -1;
            else res[i] = dist[sources[i]];
        }
        return res;
    }
}
/**
s : sources .. s->destionation 최단거리.
destionation에서 모든 위치까지 다익스트라 구해놓고 구하면끝인데?
*/