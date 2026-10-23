import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        //모든 노드를 시작으로, 모든그래프의 최단거리를 계산
        
            //초기작업
        List<List<int[]>> info = new ArrayList<>();
        for (int i = 0; i <= n; i++) info.add(new ArrayList<>());
        for (int[] f : fares) {
            int v1 = f[0];
            int v2 = f[1];
            int cost = f[2];
            
            info.get(v1).add(new int[] {v2, cost});
            info.get(v2).add(new int[] {v1, cost});
        }
        //dijkstra초기작업
        int INF = 100_000_000;
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        //모든 다익스트라완성
        for (int i = 1; i <= n; i++) {
            //i로부터 출발
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // {간선, 가격};
            pq.offer(new int[] {i, 0});
            while (!pq.isEmpty()) {
                int[] polled = pq.poll();
                int at = polled[0];
                if (dist[i][at] < polled[1]) continue;
                for (int[] nei : info.get(at)) {
                    int nextNode = nei[0];
                    int nextCost = nei[1];
                    
                    if (dist[i][nextNode] <= dist[i][at] + nextCost) continue;
                    dist[i][nextNode] = dist[i][at] + nextCost;
                    pq.offer(new int[] {nextNode, dist[i][nextNode]});
                }
            }
        }
        
        //모든 지점을 시도하여 싼 것 구하기
        int min = INF;
        //s부터 i경유
        for (int i = 1; i<= n; i++) {
            int total = dist[s][i] + dist[i][a] + dist[i][b];
            min = total < 0 ? min : Math.min(total, min);
            // System.out.printf("%d경유시 %d+%d+%d = %d\n", i, dist[s][i], dist[i][a], dist[i][b], total);
        }
        return min;
    }
}