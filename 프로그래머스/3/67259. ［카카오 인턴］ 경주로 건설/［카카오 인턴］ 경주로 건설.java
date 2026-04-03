import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
        PriorityQueue<Info> pq = new PriorityQueue<>(
            (o1, o2) -> o1.price - o2.price
        );
        int m = board.length;
        int[][][] dist = new int[m][m][2];//[][][1]은 수직
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        dist[0][0][0] = 0;
        dist[0][0][1] = 0;
        pq.offer(new Info(0,0,-1,0));
        while (!pq.isEmpty()) {
            Info polled = pq.poll();
            if (polled.prev != -1 && dist[polled.r][polled.c][polled.prev] < polled.price) continue;
            //System.out.printf("(%d, %d) at %d \n", polled.r, polled.c, polled.price);
            if (polled.r == m -1 && polled.c == m - 1) return polled.price;
            for (int[] d : directions) {
                int nr = polled.r + d[0];
                int nc = polled.c + d[1];
                boolean sameDirection = (polled.prev == -1) 
                    || (d[0] == 0 && polled.prev == 0) ||
                    (d[0] != 0 && polled.prev == 1);
                int nPrice = sameDirection ? polled.price + 100 : polled.price + 600;
                int going = d[0] != 0 ? 1 : 0;
                //bound
                if (nr < 0 || nr >= m || nc < 0 || nc >= m) continue;
                //막힌길
                if (board[nr][nc] == 1) continue;
                //방문했음.근데 그게 더 나음
                if (dist[nr][nc][going] < nPrice) continue;
                dist[nr][nc][going] = nPrice;
                pq.offer(new Info(nr,nc,going,nPrice));
                
            }
        }
        return -1;
    }
    public class Info {
        int r;
        int c;
        int prev; //-1,0,1 . 1이 수직 0수평
        int price;
        Info(int r, int c, int prev, int price) {
            this.r = r;
            this.c = c;
            this.prev = prev;
            this.price = price;
        }
    }
}
/**
건설은 비어있어먄 할 수 있따..(이동할수있다)
이전 이동경로랑 같으면 직선 다르면 코너.
그러면 q에 상태를 관리. 좌표, 이전(수직or수평), 금액 을 가지고 다닌ㄴ다.
근데 이거는 다익스트라가 아니지않나 그냥 상ㅇ태관리 bfs아님? 완탐

생각을 해보자
while
    size?이거는 레벨별로 하는거잖어.
*/