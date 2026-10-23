import java.util.*;
class Solution {
    public int solution(int N, int[][] road, int K) {
        //이웃정보 입력
        List<List<int[]>> info = new ArrayList<>();// info.get(3) -> [5,6] => 3에서 5가려면 6원든다
        for (int i = 0; i <= N; i++) info.add(new ArrayList<>());
        for (int[] r : road) {
            int v1 = r[0];
            int v2 = r[1];
            int cost = r[2];
            info.get(v1).add(new int[] {v2, cost});
            info.get(v2).add(new int[] {v1, cost});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]); //[노드,비용]. 간선비용 정렬
        //1마을부터 나머지까지 최단거리 구하기..dijkstra
        int[] dist = new int[N + 1];// dist[N] = 1에서 N번마을거리
        int INF = 1_000_000_000;
        Arrays.fill(dist, INF);
        dist[1] = 0;
        pq.offer(new int[] {1, 0});
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int currentNode = polled[0];
            // System.out.printf("===currentNode:%d, cost:%d====\n", currentNode, polled[1]);
            // int extraCost = polled[1];
            //만약..지금 더 싼게있으면 걍 패스하면되잖아 굳이 또 이웃추가안할테니
            for (int[] nei : info.get(currentNode)) {
                int nextNode = nei[0];
                int nextCost = nei[1];
                if (dist[currentNode] + nextCost >= dist[nextNode]) continue;
                dist[nextNode] = dist[currentNode] + nextCost;
                pq.offer(new int[] {nextNode, dist[nextNode]});
            }
        }
    
        //마지막에 카웅ㄴ팅
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) cnt++;
        }
        return cnt;
    }
}