import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] f : fares) {
            int v1 = f[0];
            int v2 = f[1];
            int price = f[2];
            
            graph.get(v1).add(new int[] {v2, price});
            graph.get(v2).add(new int[] {v1, price});
        }
        //between all points dijkstra
        for (int i = 1; i <= n; i++) {
            // System.out.printf("====%d dijkstra====\n", i);
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            pq.offer(new int[] {i, 0});
            while (!pq.isEmpty()) {
                int[] polled = pq.poll();
                int v = polled[0];
                int price = polled[1];
                if (dist[i][v] < price) continue;
                // System.out.printf("polled:(%d,%d)\n",v,price);
                for (int[] nei : graph.get(v)) {
                    // System.out.printf("%d %d\n",nei[0],nei[1]);
                    if (price + nei[1] >= dist[i][nei[0]]) continue;
                    dist[i][nei[0]] = price + nei[1];
                    pq.offer(new int[] {nei[0], dist[i][nei[0]]});
                }
            }
        }
        // for (int i = 1; i <= n; i++) System.out.println(Arrays.toString(dist[i]));
        //start -> certain point -> destination
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            // System.out.printf("%d / 2 + %d + %d\n", dist[s][i], dist[i][a],dist[i][b]);
            ans = Math.min(ans, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return ans;
    }
}