import java.util.*;
class Solution {
    public int solution(int n, int[][] costs) {
        List<List<int[]>> graph = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        //그래프 정보 추가
        for (int[] cost : costs) {
            int v1 = cost[0];
            int v2 = cost[1];
            int expense = cost[2];
         
            graph.get(v1)
                .add(new int[] {v2, expense});
            graph.get(v2)
                .add(new int[] {v1, expense});
        }
        
        //프림으로 구하기
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int cnt = 0;
        pq.add(new int[] {0, 0});
        int charge = 0;
        while (!pq.isEmpty()) {
            //제일 싼 거 골라서 연결
            int[] bridge = pq.poll();
            int current = bridge[0];
            if (visited[current]) continue;// 이미 연결했으면 패스
            visited[current] = true;
            charge += bridge[1];
            //다음 후보들 pq에 넣음
            for (int[] connected : graph.get(current)) {
                pq.offer(connected);
            }
            
        }
        return charge;
    }
}
/**
그래프 만들어놓고 하나씩 순회
*/