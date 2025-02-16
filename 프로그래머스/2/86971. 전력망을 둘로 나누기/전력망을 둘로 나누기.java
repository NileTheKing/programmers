import java.util.*;
class Solution {
    private int answer = Integer.MAX_VALUE;
    public int solution(int n, int[][] wires) {
        List<Integer>[] graph = new ArrayList[n + 1];
        
        // 그래프 초기화
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        //그래프로 옮기기
        for (int[] wire: wires) {
            int node1 = wire[0];
            int node2 = wire[1];
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        for (int[] wire : wires) {
            int tmp1 = wire[0];
            int tmp2 = wire[1];
            graph[tmp1].remove(Integer.valueOf(tmp2));
            graph[tmp2].remove(Integer.valueOf(tmp1));
            int part1 = dfs(graph, 1, new boolean[n+1]);
            int part2 = n - part1;
            answer = Math.min(answer, Math.abs(part1 - part2));
            graph[tmp1].add(tmp2);
            graph[tmp2].add(tmp1);
            
        }
        
        return answer;
        
    
    }
    public int dfs(List<Integer>[] graph, int current, boolean[] visited) {
        int count = 1;
        visited[current] = true;
        for (int neighbor : graph[current]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                count += dfs(graph, neighbor, visited);
            }
        }
        return count;
        
    }
}
/**
백트래킹으로 한번에 한다면?
그냉 메인함수에서 다리 연결끊고 dfs돌려서 차이 구하면서 최솟값 갱신하는 것이 더 낫지 않을까 함
*/