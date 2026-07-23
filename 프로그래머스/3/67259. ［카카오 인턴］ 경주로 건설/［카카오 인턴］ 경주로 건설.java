import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};//0 2 수직 13수평
    int m, n;
    public int solution(int[][] board) {
        this.m = board.length;
        this.n = board[0].length;
        int[][][] visited = new int[m][n][2]; //[][][이놈은수직수평] 1수직0수평
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(0,0,-1, 0));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j][0] = Integer.MAX_VALUE;
                visited[i][j][1] = Integer.MAX_VALUE;
            }
        }
        visited[0][0][0] = 0;
        visited[0][0][1] = 0;
        int min = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Info polled = q.poll();
            // System.out.printf("at (%d,%d), moved dir:%d, price:%d\n", polled.r, polled.c, polled.prevDirIdx, polled.price);
            for (int i = 0; i < directions.length; i++) {
                // System.out.printf("directions: %d\n", i);
                int nr = polled.r + directions[i][0];
                int nc = polled.c + directions[i][1];
                //boundcheck
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    // System.out.printf("bound\n");
                    continue;
                }
                if (board[nr][nc] == 1) continue;
                if (i % 2 == 1) {//지금 수평으로 이동중
                    // System.out.printf("moving horizontally\n");
                    //visitedcheck
                    //이전에 수직이었음(코너)
                    if (polled.prevDirIdx != - 1 && polled.prevDirIdx % 2 == 0) {
                        int nprice = polled.price + 600;
                        if (visited[nr][nc][0] <= nprice) continue;
                        visited[nr][nc][0] = nprice;
                        if (nr == m - 1 && nc == n - 1) {
                            // System.out.printf("found at price %d\n", nprice);
                            min = Math.min(min, nprice);
                        }
                        q.offer(new Info(nr,nc, i, nprice));
                    }
                    //이전에 수직이었음(직선)
                    else {
                        int nprice =  polled.price + 100;
                        if (visited[nr][nc][0] <= nprice) continue;
                        visited[nr][nc][0] = nprice;
                        if (nr == m - 1 && nc == n - 1) {
                            // System.out.printf("found at price %d\n", nprice);
                            min = Math.min(min, nprice);
                        }
                        q.offer(new Info(nr,nc, i, nprice));
                    }
                }else {
                    //수직으로 이동중
                    // System.out.printf("moving vertically\n");
                    //이전에 수직이었음(직선)
                    if (polled.prevDirIdx == - 1 || polled.prevDirIdx % 2 == 0) {
                        int nprice=  polled.price + 100;
                        if (visited[nr][nc][1] <= nprice) continue;
                        visited[nr][nc][1] = nprice;
                        if (nr == m - 1 && nc == n - 1) {
                            // System.out.printf("found at price %d\n", nprice);
                            min = Math.min(min, nprice);
                        }
                        q.offer(new Info(nr,nc, i, nprice));
                    }
                    //이전에 수평이었음
                    else {
                        int nprice=  polled.price + 600;
                        if (visited[nr][nc][1] <= nprice) continue;
                        visited[nr][nc][1] = nprice;
                        if (nr == m - 1 && nc == n - 1) {
                            // System.out.printf("found at price %d\n", nprice);
                            min = Math.min(min, nprice);
                        }
                        q.offer(new Info(nr,nc, i, nprice));
                    }
                }
            }
        }
        return min;
    }
    class Info {
        int r;
        int c;
        int prevDirIdx;
        int price;
        Info (int r, int c, int prevDirIdx, int price) {
            this.r = r;
            this.c = c;
            this.prevDirIdx = prevDirIdx;
            this.price = price;
        }
    }
}