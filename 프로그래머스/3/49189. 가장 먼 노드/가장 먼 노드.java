import java.util.*;
class Solution {
    int max = 0;
    public int solution(int n, int[][] edge) {
    
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        
        
        
        //추가
        for (int[] connection : edge) {
            int from = connection[0];
            int to = connection[1];
            
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }
        
        
        q.offer(1);
        visited[1] = true;
        int lastSize = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            lastSize = size;
            for (int i = 0; i < size; i++) {
                int polled = q.poll();
                List<Integer> nexts = graph.get(polled);
                for (int next : nexts) {
                    if (!visited[next]) {
                        q.offer(next);
                        visited[next] =true;
                    }
                }
            }
        }
        
        
        return lastSize;
    }
    
}
/**
dfs로 최대깊이 구해놓고
    1. bfs로 해당ㄹ ㅔ벨 도달했을때 끊은 다음 출력
    or 2. dfs로 레벨에 해당하면 리스트에 추가해서 출력
*/