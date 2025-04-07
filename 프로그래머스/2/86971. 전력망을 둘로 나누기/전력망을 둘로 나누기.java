import java.util.*;
class Solution {
    public int solution(int n, int[][] wires) {
        
        List<Integer>[] graph = new ArrayList[n + 1];//todo 
        int answer = Integer.MAX_VALUE;
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        } //
    
        //그래프 완성
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        //하나씩 잘라가며 최솟차이값을 저장.dfs활용
        for (int[] wire : wires) {
            int from = wire[0];
            int to = wire[1];
            
            //삭제
            graph[from].remove(Integer.valueOf(to));
            graph[to].remove(Integer.valueOf(from));
            //dfs
            int part1 =  dfsAndCount(1, new boolean[n + 1], graph);
            int part2 = n - part1;
            answer = Math.min(answer, Math.abs(part1 - part2));
            //복구
            graph[from].add(to);
            graph[to].add(from);
        }
        return answer;
    }
    
    int dfsAndCount(int current, boolean[] visited, List<Integer>[] graph) {
        int count = 1;
        visited[current] = true;
        
        for (int next : graph[current]) {
            if (!visited[next]) {
                visited[next] = true;
                count += dfsAndCount(next, visited, graph);
            }
        }
        
        return count; 
    }
}