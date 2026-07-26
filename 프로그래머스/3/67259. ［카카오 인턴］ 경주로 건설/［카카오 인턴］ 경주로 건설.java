import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public int solution(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        PriorityQueue<Info> pq = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        boolean[][][] visited = new boolean[m][n][2]; //0수평, 1수직
        pq.offer(new Info(0,0,0,0));
        pq.offer(new Info(0,0,0,1));
        while (!pq.isEmpty()) {
            Info polled = pq.poll();
            if (visited[polled.r][polled.c][polled.prevDir]) continue;
            visited[polled.r][polled.c][polled.prevDir] = true;
            if (polled.r == m - 1 && polled.c == n - 1) return polled.cost;//종료검사는 여기서
            // System.out.printf("(%d,%d) %d %d\n", polled.r, polled.c, polled.cost, polled.prevDir);
            for (int[] d : directions) {
                int nr = d[0] + polled.r;
                int nc = d[1] + polled.c;
                
                // System.out.printf("%d %d\n", nr, nc);
                //bound
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                //valid
                if (board[nr][nc] == 1) continue;
                boolean goingHor = d[0] != 0 ? true : false;
                boolean prevHor = polled.prevDir == 0 ? true : false;
                //TT->straight 11
                //TF -> corner 10
                //FT -> corner 01
                //FF -> straight 00
                int deltaPrice = goingHor == prevHor ? 100 : 600;
                pq.offer(new Info(nr,nc,polled.cost + deltaPrice, goingHor ? 0 : 1));
            }
        }
        return -1;
    }
    class Info {
        int r;
        int c;
        int cost;
        int prevDir;
        Info  (int r, int c, int cost, int prevDir) {
            this.r = r;
            this.c = c;
            this.cost = cost;
            this.prevDir = prevDir;
        }
    }
}