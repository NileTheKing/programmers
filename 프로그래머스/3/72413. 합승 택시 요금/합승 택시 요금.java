import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<int[]>> graph = new ArrayList<>(); //이웃정보. 이웃은 노드,가격
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());//이웃정보 graph 초기화
        for (int[] f : fares) { //이웃정보완성
            int v1 = f[0], v2 = f[1];
            int weight = f[2];
            graph.get(v1).add(new int[] {v2, weight});
            graph.get(v2).add(new int[] {v1, weight});
        }
        int[][] dist = new int[n + 1][n + 1]; //dist[a][b] -> a에서 출발했을떄 b까지. 다익스트라
        for (int i = 1; i <= n; i++) {//다익스트랴ㅏ dist 배열 초기화. 본인위치는 0
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
            }
        for (int i = 1; i <= n; i++) {
            //i가 출발..다익스트라
            // System.out.printf("===dijkstra %d ====\n", i);
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            pq.offer(new int[] {i, 0});
            while(!pq.isEmpty()) {
                int[] polled = pq.poll();
                int currentNode = polled[0];
                int currentFee = polled[1];
                //pruning
                if (currentFee > dist[i][currentNode]) continue;
                // System.out.printf("at %d..with %d\n", currentNode, currentFee);
                List<int[]> neis = graph.get(currentNode);
                for (int[] nei : neis) {
                    int nextNode = nei[0];
                    int nextFee = nei[1];
                    if (dist[i][nextNode] < currentFee + nextFee) continue;
                    pq.offer(new int[] {nextNode, currentFee + nextFee});
                    dist[i][nextNode] = currentFee + nextFee;
                }
            }
        }
        // for (int i = 1; i <= n; i++) System.out.println(Arrays.toString(dist[i]));
        //s -> i / 2 + i -> a + i -> b
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int commonFee = dist[s][i];
            int aFee = dist[i][a];
            int bFee = dist[i][b];
            int total = commonFee + aFee + bFee;
            // System.out.printf("via %d, fee %d\n", i, total);
            min = Math.min(min, total);
        }
        return min;
    }
}
/**
n 200노드
40,000 * 100,000
*/