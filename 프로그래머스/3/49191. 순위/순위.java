import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        Map<Integer, List<Integer>> win = new HashMap<>();
        Map<Integer, List<Integer>> defeat = new HashMap<>();
        int cnt = 0;
        
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];

            win.computeIfAbsent(winner, k -> new ArrayList<>()).add(loser);
            defeat.computeIfAbsent(loser, k -> new ArrayList<>()).add(winner);
        }
        
        // 1번 선수부터 순회
        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            Queue<Integer> q = new LinkedList<>();
            
            // 이긴 선수들 탐색
            q.offer(i);
            visited[i] = true;
            while (!q.isEmpty()) {
                int winner = q.poll();
                for (int loser : win.getOrDefault(winner, Collections.emptyList())) {
                    if (!visited[loser]) {
                        visited[loser] = true;
                        q.offer(loser);
                    }
                }
            }
            
            // 진 선수들 탐색
            q.offer(i);
            while (!q.isEmpty()) {
                int loser = q.poll();
                for (int winner : defeat.getOrDefault(loser, Collections.emptyList())) {
                    if (!visited[winner]) {
                        visited[winner] = true;
                        q.offer(winner);
                    }
                }
            }
            
            boolean flag = true;
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) cnt++;
        }
        
        return cnt;
    }
}
