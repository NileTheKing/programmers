import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    int m, n;
    public int solution(String[] storage, String[] requests) {
        char[][] board = new char[storage.length][storage[0].length()];
        m = board.length;
        n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            board[i] = storage[i].toCharArray();
        }
        
        int ans = 0;
        for (String r : requests) {
            int cnt = 0; //thistime
            if (r.length() == 1) { //지게차
                cnt += lifter(board, r.charAt(0));
            }else { //크레인
                for (int i = 0; i < m; i++) {//풀스캔
                    for (int j = 0; j < n; j++) {
                        if (r.charAt(0) == board[i][j]) {
                            cnt++;
                            board[i][j] = '.';
                            
                        }
                    }
                }
            }
            //System.out.printf("THIS TIME CNT : %d\n", cnt);
            ans += cnt;
        }
        return m * n - ans;
        
    }
    public int lifter(char[][] board, char c) {//지게차 로직실행...꺼낼 수 있는c를 찾아라!
        List<int[]> toBeDeleted = new ArrayList<>();
        //스캔하면서..c에해당하는 애들이 동시에 탈출가능한지 확인.
        //되는애들은.. 나중에 한 거번에 처리
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != c)  continue; //찾ㅇ르놈아니니까 풀스캔대상에서 제외
                if (escapable(board, i, j)) {
                    toBeDeleted.add(new int[] {i,j});
                }
            }
        }
        int cnt = 0;
        for (int[] t : toBeDeleted) {
            //System.out.printf("%c, at [%d,%d]\n", board[t[0]][t[1]],t[0],t[1]);
            board[t[0]][t[1]] = '.';
            cnt++;
        }
        return cnt;
        
        
    }
    public boolean escapable(char[][] board, int r, int c) {
        if (r == 0 || r == m - 1 || c == 0 || c == n - 1) return true;
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r,c});
        boolean[][] visited = new boolean[m][n];
        visited[r][c] = true;
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            for (int[] d : directions) { //현재 다음좌표가 탈출가능구역이라면 종료
                int nr = polled[0] + d[0];
                int nc = polled[1] + d[1];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) return true; //겸 예외처리
                if (visited[nr][nc]) continue;
                if (board[nr][nc] != '.') continue; //안막혀있으면 못감.
                
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc});
            }
        }
        return false;
    }
}
/**
requests를 순회하면서 명령을 수행
1. 지게차
    얘는 이제.. 순회하면서 그 순회에서! 그 상태에서! 인접면이 있는지 확인. 걔 4면중 하나가 비어있거나
    걔 자체가 끝라인이거나.
2. 크레인
    얘는 ez 그냥 다 꺼내면 되잖아
*/