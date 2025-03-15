import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        
        int cnt = 0;
        int row = maps.length;
        int col = maps[0].length;
        Queue<int[]> q = new LinkedList<>();
        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}}; //상하좌우
        
        q.add(new int[] {0, 0});//시작 0,0 끝(4,4) (5*5)
        
        while (!q.isEmpty()) {
            cnt++;
            int size = q.size(); //q에 있는 모든 좌표에 대해 근처 확인
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();// 각좌표마다
                for (int[] direction : directions) {//근처확인
                    int currentRow = polled[0] + direction[0];
                    int currentCol = polled[1] + direction[1];
                    
                    //예외처리
                    if (currentRow < 0 || currentRow >= row) {
                        continue;
                    }
                    if (currentCol < 0 || currentCol >= col) {
                        continue;
                    }
                    
                    if (maps[currentRow][currentCol] == 0) {
                        continue;
                    }
                    
                    if (maps[currentRow][currentCol] == 1) {
                        maps[currentRow][currentCol] = 0;
                        q.offer(new int[] {currentRow, currentCol});
                    }
                    
                    if (currentRow == row - 1 && currentCol == col -1) {
                        return cnt + 1;
                    }
                    
                }
            }
        }
        return -1;
    }
}
/**
bfs or dfs로 풀면 되는데 bfs가 더 빠름. 이유는 음...암튼 그럼
*/