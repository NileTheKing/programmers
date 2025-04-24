import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        //두배 스케일링
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
        boolean[][] visited = new boolean[101][101];
        int[] current = {characterX, characterY};
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(current);
        
        int step = 0;//찾을 때 까지 bfs
        while (!q.isEmpty()) {
            //System.out.println("q is called");
            step++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                for (int[] dir : directions) {
                    int newX = polled[0] + dir[0];
                    int newY = polled[1] + dir[1];
                    if (newX == itemX && newY == itemY) return step / 2;
                    
                    if (newX < 0 || newX > 100 || newY < 0 || newY > 100) {
                        continue;
                    }
                    
                    if (isOnEdge(rectangle, newX, newY) &&
                       !isIn(rectangle, newX, newY)) {
                        if (!visited[newX][newY]){
                            q.offer(new int[] {newX, newY});
                            visited[newX][newY] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    public boolean isOnEdge(int[][] rectangle, int x, int y) {
        
        for (int[] rec : rectangle) {
            // ㅣ
            if (x == rec[0] && y >= rec[1] && y <= rec[3]) return true;
            //   ㅣ
            if (x == rec[2] && y >= rec[1] && y <= rec[3]) return true;
            // ㅡ
            if (y == rec[3] && x >= rec[0] && x <= rec[2]) return true;
            // __
            if (y == rec[1] && x >= rec[0] && x <= rec[2]) return true;
        }
        return false;
    }
    
    public boolean isIn(int[][] rectangle, int x, int y) {
        
        for (int[] rec : rectangle) {
            if (x > rec[0] && x < rec[2] && y > rec[1] && y < rec[3]) {
                return true;
            }
        }
        
        return false;
    }

}

/**

*/