import java.util.*;
class Solution {
    int m, n;
    int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public int solution(int[][] maps) {
        //시작좌표 0,0 목표좌표..끝인 m-1, n-1
        m = maps.length;
        n = maps[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0,0});
        visited[0][0] = true;
        
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                //System.out.printf("at (%d, %d) \n", polled[0], polled[1]);
                //cnt++;
                for (int[] d : directions) {
                    int nr = polled[0] + d[0];
                    int nc = polled[1] + d[1];
                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    if (maps[nr][nc] == 0) continue;
                    if (visited[nr][nc]) continue;
                    if (nr == m - 1 && nc == n - 1) return cnt + 1;
                    
                    visited[nr][nc]  = true;
                    //maps[nr][nc] = 0;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        return -1;
        
        
    }
}