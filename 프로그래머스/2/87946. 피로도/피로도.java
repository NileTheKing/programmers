class Solution {
    int max = Integer.MIN_VALUE;
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        
        backtrack(k, dungeons, visited, 0);
        return max;
    }
    
    //백트래킹. (남은피로도, 던전정보, 방문한던전 정보, 현재까지 탐사한 던전수)
    public void backtrack(int p, int[][] dungeons, boolean[] visited, int cnt) {
        if (cnt >= max) {
            max = cnt;
        }
        //종료조건은?
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && p >= dungeons[i][0]) { //방문한적 없고 방문이 가능하다면
                visited[i] = true;
                backtrack(p - dungeons[i][1], dungeons, visited, cnt + 1);
                visited[i] = false;
                //던전 보내고 백트래킹
            }
        }
        return;
    }
}
/** k: 현재 피로도 dungeons:피로도 정보 {최소 필요소모, 추가 소모}
던전 하나는 한 번만 갈 수 있음
최대한 많이 돌면 몇번?
그냥 백트래킹 조지면 될듯
왜냐하면 그리디로 하면 안됨
*/
