import java.util.*;
class Solution {
    int[] dist;
    public int solution(int N, int[][] road, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        dist = new int[N + 1];// dist[n] = n노드까지 최단거리
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        //그래프그리기 3,[1,2] -> 3마을은1마을이랑연결 시간2
        for (int[] r : road) {
            map.computeIfAbsent(r[0], k -> new ArrayList<>()).add(new int[] {r[1],r[2]});
            map.computeIfAbsent(r[1], k -> new ArrayList<>()).add(new int[] {r[0],r[2]});
        }
        //1번마을에서 다른 마을들 순회하면서 K미만으로 갈 수 있는 곳은 카운트.
        //이 파트가 문제임. 어떤 방법을 사용할 수 있을까?..
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);//오름차순..{노드,거리}
        pq.offer(new int[] {1,0});
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            for (int[] nei : map.get(polled[0])) {
                //방문할지말지. 기존보다 나아야만 방문해줌
                if (polled[1] + nei[1] < dist[nei[0]]) {
                    dist[nei[0]] = polled[1]+ nei[1];
                    pq.offer(new int[] {nei[0], dist[nei[0]]});
                }
            }
        }
        
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                //System.out.printf("%d\n", i);
                cnt++;}
        }
        return cnt;
        
    }
}
/**
간선 2000개
노드 50개
*/