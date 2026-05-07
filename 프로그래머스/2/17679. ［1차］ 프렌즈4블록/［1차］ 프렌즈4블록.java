import java.util.*;
class Solution {
    char[][] grid;
    int m;
    int n;
    public int solution(int m, int n, String[] board) {
        //char[][] 변환
        this.m = m;
        this.n = n;
        grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) grid[i][j] = board[i].charAt(j);
        }
        
        int cnt = 0;
        while (true) {
            int currentCnt = 0;
            List<int[]> blocks = new ArrayList<>();
            //지울 블록 찾기
            findBlocks(blocks);
            //블록 지우기
            currentCnt += deleteBlocks(blocks);
            //종료조건
            if (currentCnt == 0) return cnt;
            
            //다음준비
            cnt += currentCnt;
            drop();
        }
    }
    public boolean isBlock(int r, int c) {
        if (grid[r][c] =='.') return false;
        char friend = grid[r][c];
        if (grid[r][c + 1] != friend) return false; 
        if (grid[r + 1][c] != friend) return false;
        if (grid[r + 1][c + 1] != friend) return false;
        
        return true;
    }
    public void findBlocks(List<int[]> blocks) {
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0;j < n - 1; j++) {
                if (isBlock(i,j)) {
                    blocks.add(new int[] {i,j});
                }
            }
        }
    }
    public int deleteBlocks(List<int[]> blocks) {
        int currentCnt = 0;
        for (int[] b : blocks) {
            if (grid[b[0]][b[1]] !='.') {
                grid[b[0]][b[1]] = '.'; 
                currentCnt++;
            }
            if (grid[b[0]][b[1] + 1] != '.') {
                currentCnt++;
                grid[b[0]][b[1] + 1] = '.';
            }
            if (grid[b[0] + 1][b[1]] != '.'){
                currentCnt++;
                grid[b[0] + 1][b[1]] = '.';
            }
            if (grid[b[0] + 1][b[1] + 1] != '.') {
                grid[b[0] + 1][b[1] + 1] = '.';
                currentCnt++;
            }
        }
        return currentCnt;
    }
    public void drop() {
        for(int i = m - 1;i >= 1; i--) {//바닥행
            for (int j = 0; j < n; j++) {//열
                if (grid[i][j] == '.') {//본인이 비었으면
                    Outter : for (int k = i; k  >= 0; k--) {
                        if (grid[k][j] != '.') {      
                            grid[i][j] = grid[k][j];
                            grid[k][j] = '.';//얘도 떨어져야함.. .으로
                                break Outter;
                        }
                    }
                }
            }
        }
    }
}