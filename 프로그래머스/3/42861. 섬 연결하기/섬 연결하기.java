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
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return a[1] - b[1];
        });// [목적지, 가격]
        pq.offer(new int[] {0,0});
        int ans = 0;
        int cnt = 0;
        while (!pq.isEmpty() && cnt < n) {
            int[] polled = pq.poll();
            if (visited[polled[0]]) continue;
            visited[polled[0]] = true;
            cnt++;
            ans += polled[1];
            for (int[] nei : graph.get(polled[0])) {
                int nextNode = nei[0];
                // if (visited[nextNode]) continue;
                pq.offer(new int[] {nextNode, nei[1]});
                // visited[nextNode] = true;
            }
        }
        return ans;
    }
}