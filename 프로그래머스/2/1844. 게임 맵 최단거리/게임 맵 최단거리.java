import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public int solution(int[][] maps) {
        int m = maps.length;
        int n = maps[0].length;
        
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = true;
        q.offer(new int[] {0,0});
        int step = 1;
        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                for (int[] d : directions) {
                    int nr = polled[0] + d[0];
                    int nc = polled[1] + d[1];
                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;//bound
                    if (visited[nr][nc]) continue;//visited
                    //wall
                    if (maps[nr][nc] == 0) continue;
                    
                    //return
                    if (nr == m - 1 && nc == n - 1) return step;
                    q.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        return -1;
    }
}