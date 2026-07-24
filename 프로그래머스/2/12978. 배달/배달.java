import java.util.*;
class Solution {
    public int solution(int N, int[][] road, int K) {
        //graph완성
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int[] r : road) {
            int v1 = r[0];
            int v2 = r[1];
            int cost = r[2];
            graph.get(v1).add(new int[] {v2, cost});
            graph.get(v2).add(new int[] {v1, cost});
        }
        //dijkstra
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[] {1, 0});
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            if (dist[polled[0]] < polled[1]) continue;
            //가지치기..visit은 dist로하는거니까 이미한거지. 위에서. /아래놈은 호출전에할거
            for (int[] nei : graph.get(polled[0])) {
                if (dist[nei[0]] <= polled[1] + nei[1]) continue;
                pq.offer(new int[] {nei[0], polled[1] + nei[1]});
                dist[nei[0]] = polled[1] + nei[1];
            }
        }
        //dist순회
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) cnt++;
        }

        return cnt;
    }
}