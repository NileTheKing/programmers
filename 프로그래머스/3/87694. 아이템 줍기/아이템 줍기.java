import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        //2배
        for (int[] rec : rectangle) {
            rec[0] *= 2;
            rec[1] *= 2;
            rec[2] *= 2;
            rec[3] *= 2;
        }
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        //bfs
        boolean[][] visited = new boolean[102][102];
        visited[characterX][characterY] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {characterX, characterY});
        int step = 0;
        while (!q.isEmpty()) {
            step++;
            // System.out.printf("===step%d====\n", step);
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                // System.out.printf("(%d, %d) poleld\n", polled[0], polled[1]);
                for (int[] d : directions) {
                    int nr = polled[0] + d[0];
                    int nc = polled[1] + d[1];
                    
                    //bound
                    if (nr < 0 || nr >= 102 || nc < 0 || nc >= 102) continue;
                    //visit
                    if (visited[nr][nc]) continue;
                    //not online
                    if (notOnline(rectangle, nr, nc)) continue;
                    //inside
                    if (inside(rectangle, nr, nc)) continue;
                    //END
                    if (nr == itemX && nc == itemY) return step / 2;//2배확장한 판에서 1칸씩 움직였으므로
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        return -1;
            
    }
    public boolean notOnline(int[][] rectangle, int r, int c) {
        //지금 좌표가..모서리여야한다. 모서리 위에있으려면 4개 사각형 중 각 선분4개중 하나위에있으면된다..16개중에 하나에만 올라가있어도 된다.
        for (int[] rec : rectangle) {
            int x1 = rec[0];
            int y1 = rec[1];
            int x2 = rec[2];
            int y2 = rec[3];
            //r,c가  4개 선분중에 하나
            //아래 ㅡ 라는건.., x범위는 x1 x2 y =y1
            if (r >= x1 && r <= x2 && c == y1) return false;
            //위 ㅡ x범위 [x1, x2] , y = y2
            if (r >= x1 && r <= x2 && c == y2) return false;
            //왼쪽 ㅣ x = x1, y범위 [y1, y2]
            if (r == x1 && c  >= y1 && c  <= y2) return false;
            //오른쪾 ㅣ x = x2, y범위 [y1,y3]
            if (r == x2 && c  >= y1 && c  <= y2) return false;
        }
        return true;
    }
    public boolean inside(int[][] rectangle, int r, int c) {
        //안에있다라는건..4개중에 하나에 안에만 있어도 안된다.
        for (int[] rec : rectangle) {
            int x1 = rec[0];
            int y1 = rec[1];
            int x2 = rec[2];
            int y2 = rec[3];
            if (r > x1 && r < x2 && c > y1 && c < y2) return true;
        }
        
        return false;
    }
}