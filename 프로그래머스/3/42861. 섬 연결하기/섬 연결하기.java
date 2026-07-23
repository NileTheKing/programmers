import java.util.*;
class Solution {
    public int solution(int n, int[][] costs) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] c : costs) {
            int v1 = c[0];
            int v2 = c[1];
            int expense = c[2];
            graph.get(v1).add(new int[] {v2, expense});
            graph.get(v2).add(new int[] {v1, expense});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        boolean[] visited = new boolean[n+1];
        pq.offer(new int[] {0, 0});//0에서시작
        // visited[0] = true;
        int sum = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            if (cnt >= n) break;
            int[] polled = pq.poll();
            if (visited[polled[0]]) continue;
            visited[polled[0]] = true;
            sum += polled[1];
            cnt++;
            for (int[] nei : graph.get(polled[0])) {
                pq.offer(new int[] {nei[0], nei[1]});//방문처리는 여기서하면안된다
            }
        }
        return sum;
    }
}