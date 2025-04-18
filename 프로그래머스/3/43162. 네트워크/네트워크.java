class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                dfs(computers, i, visited);
            }
        }
        return answer;
    }
    
    void dfs(int[][] computers, int current, boolean[] visited) {
        
        visited[current] = true;
        for (int i = 0; i < computers.length; i++) {
            if (computers[current][i] == 1 && !visited[i] && current != i) {
                dfs(computers, i, visited);
            }
        }
    }
}
/**

*/