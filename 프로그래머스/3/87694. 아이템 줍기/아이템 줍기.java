import java.util.*;
class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        // 1. 모든 좌표를 2배로 스케일링
        for (int i = 0; i < rectangle.length; i++) {
            rectangle[i][0] *= 2;
            rectangle[i][1] *= 2;
            rectangle[i][2] *= 2;
            rectangle[i][3] *= 2;
        }
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        
        int step = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {characterX, characterY});
        boolean[][] visited = new boolean[101][101];
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                for (int[] direction : directions) {
                    int newX = polled[0] + direction[0];
                    int newY = polled[1] + direction[1];
                    
                    if (newX == itemX && newY == itemY) {
                        return step / 2;
                    }
                    
                    if (newX < 0 || newX > 100 || newY < 0 || newY > 100) {
                        continue;
                    }
                    
                    if (isAddable(rectangle, newX, newY) && !visited[newX][newY])
                    {
                        q.offer(new int[] {newX, newY});
                        visited[newX][newY] = true;
                    }
                    
                }
                
            }
        }
        return -1;
    }
    
    public boolean isAddable(int[][] rectangle, int x, int y) {
        //x, y는 확인하려는 좌표
        //점이 외곽 선분위에 있어야함.
        //고로 x,y는 특정선분위에 있으며
        //그 x, y는 다른 직사각형에 같혀있으면 안됨.
        //두개를 만족하면 addable
        
        
        //특정 사각형위에 있는 점일 때 추가 확인
        //x,y의 좌표가 사각형의 내부에 있는지 확인. 경계선은 제외하고 속해있는 게 있다면
        if (isOnEdge(rectangle, x, y)) {
            for (int[] rec : rectangle) {
                if ((x > rec[0] && x < rec[2]) && (y > rec[1] && y < rec[3])) {
                    //완전히 속한다면 안됨
                    return false;
                    
                } 
            }
            return true;
        }
        return false;
        
    }
    public boolean isOnEdge (int[][] rectangle, int x, int y) {
        for (int[] rec : rectangle) {
            //4가지 경우의 수가 있음
            //ㅣ
            if (x == rec[0] && (y >= rec[1] && y <= rec[3])) {
                return true;
            }else if (y == rec[1] && (x >= rec[0] && x <= rec[2])) {//ㅡ
                return true;
            }else if (x == rec[2] && (y >= rec[1] && y <= rec[3])) {
                return true;
            }else if (y == rec[3] && (x >= rec[0] && x <= rec[2])) {//ㅡ
                return true;
            }
        }
        return false;
    }
}