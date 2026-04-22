import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    int m = 5;
    int n = 5;//가로세로5곱5
    public int[] solution(String[][] places) {
        int[] ans = new int[places.length];
        
        for (int i = 0; i < places.length; i++) {
            char[][] board = new char[m][n];
            //grid로 변환
            for (int j = 0; j < m; j++) board[j] = places[i][j].toCharArray();
    
            //grid순회해서 맨해튼거리2 있는지없는지 체크..완탐.
            // System.out.printf("===checking %d=== \n", i);
            if (check(board)) ans[i] = 1;
            else ans[i] = 0;
            // System.out.printf("===done %d=== \n", i);
        }
        return ans;
    }
    public boolean check(char[][] board) {
        //각 모든 P에서 맨해튼거리2까지 다 탐색..거리가2미만이어도 막혀있으면 ㄱㅊ
        //탐색중에 중간에 boolean해야하나?안해 그냥 일단 구현해
        //아무튼 거리제한, 벽제한을 뚫고 사람찾으면 실패
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 'P') continue;
                // System.out.printf("checking for (%d,%d)\n", i, j);
                Queue<int[]> q = new LinkedList<>();
                boolean[][] visited = new boolean[m][n];
                visited[i][j] = true;
                q.offer(new int[] {i, j});
                int step = 0;
                while (!q.isEmpty() && step < 2) {
                    int size = q.size();
                    step++;
                    for (int k = 0; k < size; k++) {//거리별
                        int[] polled = q.poll();
                        for (int[] d : directions) {
                            int nr = d[0] + polled[0];
                            int nc = d[1] + polled[1];
                            
                            if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                            if (visited[nr][nc]) continue;
                            if (board[nr][nc] == 'X') continue; //파티션은못감(안감)
                            if (board[nr][nc] == 'P') {
                                // System.out.printf("found! (%d,%d)\n", nr, nc);
                                return false;
                            }
                            visited[nr][nc] = true;
                            q.offer(new int[] {nr, nc});
                        }
                    }
                }
                
            // System.out.printf("done checking for (%d,%d)\n", i, j);
            }
        }
        return true;//맨해튼거리2 안에 사람없음
    }
}
/**
맨해튼거리는 아마 우리가아는 격자에서의 거리임.. 가로세로로만.

*/