import java.util.*;
class Solution {
    public int solution(int n, int[][] costs) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] c : costs) {
            int v1 = c[0];
            int v2= c[1];
            int cost = c[2];
            graph.get(v1).add(new int[] {v2, cost});
            graph.get(v2).add(new int[] {v1, cost});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        boolean[] visited = new boolean[n];
        pq.offer(new int[] {0,0});
        // visited[0] = true;//전 후 ?
        int ans = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            if (cnt == n) break;
            int[] polled = pq.poll();
            if (visited[polled[0]]) continue;
            visited[polled[0]] = true;
            cnt++;
            ans += polled[1];
            // System.out.printf("polled: %d %d\n", polled[0], polled[1]);
            for (int[] nei : graph.get(polled[0])) {
                pq.offer(new int[] {nei[0], nei[1]});
            }
        }
        return ans;
        
    }
}