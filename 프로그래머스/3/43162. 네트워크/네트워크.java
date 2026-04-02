class Solution {
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        int cnt = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            cnt++;
            //System.out.printf("at %d, cluster found.\n", i);
            dfs(computers, i); //지도정보와 찾고자하는
        }
        return cnt;
    }
    public void dfs(int[][] computers, int current) {
        if (visited[current]) return;
        visited[current] = true;
        //System.out.printf("visited %d\n", current);
        
        for (int i = 0; i < computers.length; i++) {
            if (current == i) continue;
            if (computers[current][i] == 0) continue;
            
            dfs(computers, i);
        }
    }
}
/**
순회하면서 덩어리 찾기. computerse[m][m] = 항상1
*/