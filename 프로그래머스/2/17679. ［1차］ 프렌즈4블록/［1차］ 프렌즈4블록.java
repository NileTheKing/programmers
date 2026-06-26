import java.util.*;
class Solution {
    int m, n;
    public int solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        //char[][] 로 바꾸기
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = board[i].charAt(j);
            }
        }
        //변한게없으면 그만두기
        int ans = 0;
        while (true) {
            //스캔 후 4덩어리찾기
            List<int[]> candidates = getCandidates(grid);
            //찾은거 처리. 처리한거는 $로 표시해서 중복잘 뺴기
            int cnt = 0;
            for (int[] candi : candidates) {
                int r = candi[0];
                int c = candi[1];
                int debugcnt = 0;
                if (grid[r][c] != '$') {
                    grid[r][c] = '$';
                    cnt++;
                }
                if (grid[r + 1][c] != '$') {
                    cnt++;
                    grid[r+1][c] = '$';
                }
                if (grid[r + 1][c + 1] != '$') {
                    cnt++;
                    grid[r+1][c+1] = '$';
                }
                if (grid[r][ c + 1] != '$') {
                    cnt++;
                    grid[r][c+1] = '$';
                }
            }
            ans += cnt;
            if (cnt == 0) return ans;
            //떨어뜨리기
            drop(grid);
            
        }
    }
    public List<int[]> getCandidates(char[][] grid) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < m -1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (grid[i][j] == '$') continue;
                if (!isSame(grid, i, j)) continue;
                res.add(new int[] {i, j});
            }
        }
        return res;
    }
    public boolean isSame(char[][] grid, int r, int c) {
        char origin = grid[r][c];
        if (grid[r + 1][c] != origin) return false;
        if (grid[r][c + 1] != origin) return false;
        if (grid[r + 1][c + 1] != origin) return false;
        return true;
    }
    public void drop(char[][] grid) {
        for (int c = 0; c < n; c++) {
            StringBuilder sb = new StringBuilder();
            for (int r = m - 1; r >= 0; r--) {
                if (grid[r][c] != '$') sb.append(grid[r][c]);
                grid[r][c] = '$';
            }
            //이제 sb읽어서 아래부터 다시 채워넣는다.
            for (int i = 0; i < sb.length(); i++) {
                grid[m - 1 - i][c] = sb.charAt(i);
            }
        }
    }
}
/**
턴별로 스캔
    4덩어리 찾기: 좌측상단기준 4개찾기. 겹치는거..
        중복제거!
    제거: 공백으로하고 갯수세기
    떨어뜨리기: 맵돌면서 아래로 다 당기기...
    반복
전체 갯수
*/