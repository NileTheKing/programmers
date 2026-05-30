import java.util.*;
class Solution {
    int[][] directions = {{-1,0}, {0,1}, {1,0}, {0,-1}};//상우하좌
    boolean[][] visited;
    char[][] grid;
    int m, n;
    public int solution(String[] board) {
        this.m = board.length;
        this.n = board[0].length();
        this.grid = new char[m][n];
        int startR = 0, startC = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = board[i].charAt(j);
                if (grid[i][j] == 'R') {
                    startR = i;
                    startC = j;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startR, startC});
        this.visited = new boolean[m][n];
        int step = 0;
        visited[startR][startC] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                for(int[] d : directions) {
                    //nr nc는.. 부딛힐떄까지 가는거임. 둘중하나는0이니까 그대로
                    //그러면 지금위치랑 방향주면 장애물부딛힐떄까지 가서 좌표주는
                    int[] newCoord = getCoord(polled[0], polled[1], d[0], d[1]);
                    
                    //getCoord 바운드랑 장애물고려해서 위치구함
                    if (visited[newCoord[0]][newCoord[1]]) continue;//guardClause
                    
                    //종료체크
                    if (grid[newCoord[0]][newCoord[1]] == 'G') return step + 1;
                    //newCoord방문처리
                    visited[newCoord[0]][newCoord[1]] = true;
                    q.offer(new int[] {newCoord[0], newCoord[1]});
                    //q에추가
                }
            }
            step++;
        }
        return -1;
    }
    public int[] getCoord(int r, int c, int dr, int dc) {
        //방향으로 될떄까지. 가면서 바운드체크, 장애물체크하면서..중복체크는 위에서하자.
        if (dr != 0) { //dr방향으로 될떄까지
            while (true) {
                if (r + dr >= m || r + dr< 0) {//바운드
                    return new int[] {r, c};
                }
                if (grid[r + dr][c] == 'D') {//장애물
                    return new int[] {r,c};
                }
                r += dr;
            }
        }else {//dc방향으로 될때까지.
            while (true) {
                if (c + dc >= n || c + dc < 0) {//바운드
                    return new int[] {r, c};
                }
                if (grid[r][c + dc] == 'D') {//장애물
                    return new int[] {r,c};
                }

                c += dc;
            }
        }
    }
}
