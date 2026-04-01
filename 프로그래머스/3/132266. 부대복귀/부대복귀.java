import java.util.*;
class Solution {
    int[] dist;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> map = new ArrayList<>();
        dist = new int[n + 1];// dist[i] : i에서 dest까지 거리.vice versa
        Arrays.fill(dist, -1);
        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] r : roads) {
            map.get(r[0]).add(r[1]);
            map.get(r[1]).add(r[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(destination);
        dist[destination] = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int polled = q.poll();
                for (int nei : map.get(polled)) {
                    if (dist[nei] != -1) continue;
                    
                    dist[nei] = dist[polled] + 1;
                    q.offer(nei);
                }
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int s : sources) {
            ans.add(dist[s]);
        }
        return ans.stream().mapToInt(i->i).toArray();
        
    }
    
}