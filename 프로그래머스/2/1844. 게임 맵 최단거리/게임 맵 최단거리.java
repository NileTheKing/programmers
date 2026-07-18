import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public int solution(int[][] maps) {
        Queue<int[]> q = new LinkedList<>();
        int m = maps.length;
        int n = maps[0].length;
        //visited는 maps로 대신
        q.offer(new int[] {0,0});
        int steps = 1;
        maps[0][0] = 2;
        while (!q.isEmpty()) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                // System.out.printf("at (%d %d)\n", polled[0], polled[1]);
                for (int[] d : directions) {
                    int nr = d[0] + polled[0];
                    int nc = d[1] + polled[1];
                    // System.out.printf("(%d,%d)check\n", nr,nc);
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        // System.out.printf("out of bound\n");
                        continue;
                    }
                    // System.out.printf("passsed 1\n");
                    if (maps[nr][nc] != 1) continue;
                    // System.out.printf("passsed 2\n");
                    if (nr == m - 1 && nc  == n - 1) return steps;
                    // System.out.printf("passsed 3\n");
                    
                    // System.out.printf("checking %d %d\n", nr, nc);
                    q.offer(new int[] {nr, nc});
                    maps[nr][nc] = 2;
                }
            }
        }
        return -1;
    }
}