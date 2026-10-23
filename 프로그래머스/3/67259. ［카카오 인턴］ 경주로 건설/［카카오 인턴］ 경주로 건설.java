import java.util.*;
class Solution {
    int[][] directions = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
    public int solution(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        Queue<State> q = new LinkedList<>();
        int[][][] visited = new int[m][n][2];//2는 아무상태?ㅁㄹ
        int INF = 1_000_000_000;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j][0] = INF;
                visited[i][j][1] = INF;
            }
        }
        visited[0][0][0] = 0;
        visited[0][0][1] = 0;
        q.offer(new State(0, 0, 0, 0));
        q.offer(new State(0, 0, 0, 1));
        
        int min = INF;
        while (!q.isEmpty()) {
            State polled = q.poll();
            int r = polled.r;
            int c = polled.c;
            int cost = polled.sumCost;
            int prevVertical = polled.prevVertical;
            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                int currentVertical = d[0] == 0 ? 0 : 1;//방금수직으로움직였는지
                //두개 똑같으면 직진중
                boolean straight = prevVertical == currentVertical ? true : false;
                int delta = straight ? 100 : 600;
                int nCost = cost + delta;
                
                //검증
                    //bound
                    //wall
                    //visited
                    //end면갱신
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (board[nr][nc] == 1) continue;
                if (visited[nr][nc][currentVertical] <= nCost) continue;
                if (nr == m - 1 && nc == n - 1) {
                    min = Math.min(min, nCost);
                }
                
                visited[nr][nc][currentVertical] = nCost;
                q.offer(new State(nCost, nr, nc, currentVertical));
            }
        }
        return min;
    }
    public class State {
        int sumCost;
        int r;
        int c;
        int prevVertical; //1 yes 0 no
        State(int sumCost, int r, int c, int prevVertical) {
            this.sumCost = sumCost;
            this.r = r;
            this.c = c;
            this.prevVertical = prevVertical;
        }
    }
}