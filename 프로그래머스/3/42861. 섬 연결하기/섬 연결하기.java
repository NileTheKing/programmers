import java.util.*;
class Solution {
    public int solution(int n, int[][] costs) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] c : costs) {
            int v1 = c[0];
            int v2 = c[1];
            int price = c[2];
            
            graph.get(v1).add(new int[] {v2, price});
            graph.get(v2).add(new int[] {v1, price});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        boolean[] visited = new boolean[n];
        pq.offer(new int[] {0,0});
        int ans = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            if (cnt == n) return ans;
            
            int[] polled = pq.poll();
            if (visited[polled[0]]) continue;
            visited[polled[0]] = true;
            ans += polled[1];
            cnt++;
            
            for (int[] nei : graph.get(polled[0])) {
                pq.offer(new int[] {nei[0], nei[1]});
            }
        }
        return ans;
    }
}