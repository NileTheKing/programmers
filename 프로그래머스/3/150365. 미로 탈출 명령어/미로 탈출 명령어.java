import java.util.*;
class Solution {
    int[][] directions = {{1,0}, {0,-1},{0,1},{-1,0}};// dlru
    int k;
    boolean[][][] visited;
    StringBuilder sb;
    char[][] grid;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        //초기화,가로세로 바꾸기, 0index바꾸기, 배열로 바꾸기
        this.k = k;
        this.sb = new StringBuilder();
        int temp = n;
        n = m;
        m = temp;
        visited = new boolean[m][n][k+1];
        r -= 1;
        c -= 1;
        x -= 1;
        y -= 1;
        grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '.');
        }
        grid[r][c] = 'E';
        grid[x][y] = 'S';
        //사전순으로..뭔가 방향을 먼저탐색하는걸 잘해두고 바로 리턴하면될듯.
        //무가중치이고 -> pq필요없고
        //상태는..음 필요...한가 어렵네.아 이거는 bfs가 아니라 dfs일거같은데?
        //아니 bfs도 가능은한데 귀찮으니까 dfs하고 pruning하는식일듯?홀수짝수
        return backtrack(x, y) ? sb.toString() : "impossible";
    }
    public boolean backtrack(int r, int c) {
        if (sb.length() == k) return grid[r][c] == 'E';//성공
        // System.out.printf("(%d, %d), %s\n", r, c, sb.toString());
        for(int i = 0; i < directions.length; i++) {
            int[] d = directions[i];
            int nr = r + d[0];
            int nc = c + d[1];
                
            //bound
            int m = grid.length;
            int n = grid[0].length;//m n전역안하려고 그냥참조
            if (nr < 0 || nr >= m || nc >= n || nc < 0) continue;
            //visited
            if (visited[nr][nc][sb.length()]) continue;
            char move = '.';
            switch (i) {
                case 0:move='d';break;
                case 1: move='l';break;
                case 2: move='r';break;
                case 3: move='u';break;
            }
            visited[nr][nc][sb.length()] = true;
            sb.append(move);
            if (backtrack(nr,nc)) return true;
            sb.deleteCharAt(sb.length() - 1);
        }
        return false;//실패
    }
}