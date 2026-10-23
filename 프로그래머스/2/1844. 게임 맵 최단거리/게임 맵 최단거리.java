import java.util.*;
class Solution {
    int[][] directions = {{0,1}, {0, -1}, {1,0}, {-1,0}};
    public int solution(int[][] maps) {
        //0,0으로
        int m = maps.length;
        int n = maps[0].length;
        if (m == 1 && n == 1) return 1;
        //bfs인데, 가중치가없어서 가장먼저도착하는게 최단거리임
            //visited있어야함 최단거리로 이미갔기때문
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        boolean[][] visited = new boolean[m][n];
        int cnt = 0;
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;
            // System.out.printf("===cnt:%d===\n", cnt);
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                // System.out.printf("polled: (%d, %d)\n", polled[0], polled[1]);
                for (int[] d : directions) {
                    int nr = d[0] + polled[0];
                    int nc = d[1] + polled[1];
                    // System.out.printf("checking (%d,%d)\n",nr,nc);
                    //bound
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    // System.out.printf("passed bound\n");
                    //wall
                    if (maps[nr][nc] == 0) continue;
                    // System.out.printf("passed wall\n");
                    //visited
                    if (visited[nr][nc]) continue;
                    // System.out.printf("passed visited\n");
                    //returncheck
                    if (nr == m - 1 && nc == n - 1) return cnt + 1;
                    // System.out.printf("passed all (%d,%d)\n", nr,nc);
                    q.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        return -1;
    }
}