import java.util.*;
class Solution {
    public int solution(int N, int[][] road, int K) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int[] r : road) {
            int v1 = r[0];
            int v2 = r[1];
            int weight = r[2];
            graph.get(v1).add(new int[] {v2, weight});
            graph.get(v2).add(new int[] {v1, weight});
        }
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1); //초기
        while (!q.isEmpty()) {
            int polled = q.poll();
            List<int[]> neis = graph.get(polled);
            for (int[] nei : neis) {
                int v = nei[0];
                int w = nei[1];
                if (dist[v] < dist[polled] + w) continue;//이미 더 좋은경로가있따
                q.offer(v);
                dist[v] = dist[polled] + w;
            }
        }
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                // System.out.printf("house: %d, time: %d\n", i, dist[i]);
                cnt++;
            }
        }
        return cnt;
    }
}
/**
최단거리를 업데이트해야한다 .. 기준점하나 -> 다익스트라
다익스트라 어케하냐? 까먹음
하지만 논리적으로 생각해보면
1. 출발해서 q에 넣는다
2. 꺼내가지고 ,.. 출발점기준 현재거리까지 한 기록한게있는데..이거보다 작으면 업데이트하고 또 넣어

dist[]배열이있고
*/