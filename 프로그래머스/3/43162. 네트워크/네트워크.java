class Solution {
    public int solution(int n, int[][] computers) {
        int cnt = 0;
        
        boolean[] visited = new boolean[computers.length];
        
        for (int i = 0; i < computers.length; i++) {
            if (visited[i]) {
                continue;
            }
            else {
                dfs(computers, visited, i);
                cnt++;
            }
        }
        return cnt;
    }
    
    public void dfs(int[][] computers, boolean[] visited, int current) {
        
        if (visited[current]) return;
        visited[current] = true;
        
        //현재 확인중인 컴퓨터의 연결상태 확인
        for (int i = 0; i < visited.length; i++) {
            if (current!= i && computers[current][i] == 1) {
                dfs(computers, visited, i);
            }
        }
        
        return;
    }
}
/**
몇덩어리로 나누어 지냐
그래프
이거 리트코드에서 풀어본듯? 그래프 그냥 주어진 배열 그대로 써도 될듯? visited정도만 해가지고 하면 될듯.

visited처리를 들어가기 전에 하나?
*/