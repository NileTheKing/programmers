import java.util.*;
class Solution {
    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
    
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
        int m, n;
        m = 101;
        n = 101;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        q.offer(new int[] {characterX, characterY});
        visited[characterX][characterY] = true;
        
        int cnt = -1;
        while(!q.isEmpty()) {
            int size = q.size();
            cnt++;
            for (int i = 0; i < size; i++) {
                int[] polled = q.poll();
                //System.out.printf("(%d, %d)\n", polled[0], polled[1]);
                for (int[] d : directions) {
                    int nx = d[0] + polled[0];
                    int ny = d[1] + polled[1];
                    //System.out.printf("new (%d, %d)\n", nx, ny);
                    //경계
                    if (!inRange(m,n,nx,ny)) {
                        //System.out.printf("range fail\n");
                        continue;}
                    //방문
                    if (visited[nx][ny]) {
                        //System.out.printf("visited fail\n");
                        continue;}
                    //한사각형의 테두리위
                    if (!isEdge(rectangle, nx, ny)) {
                        //System.out.printf("not on edge fail\n");
                        continue;}
                    //다른 사각형의 내부가 아님
                    if (isIncluded(rectangle, nx, ny)) {
                        //System.out.printf("included fail\n");
                        continue;}
                    
                    if (nx ==itemX && ny == itemY) return (cnt + 1)/2;
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }
            }
        }
        return -1;
        
    }
    public boolean inRange(int m, int n, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        return true;
    }
    //한 사각형의 변인ㅇ가
    public boolean isEdge(int[][] rectangle, int x, int y) {
        //사각형은 변이4개..그중하나라도 거치면됨 심지어 한 사각형에만 되면 된다
        for (int[] rec : rectangle) {
            //사각형 두 점을 기준으로
            //ㅡ 2개 -> r가 일치
                //윗변에 일치
            if (y == rec[3] && x >= rec[0] && x <= rec[2]) return true;
            if (y == rec[1] && x >= rec[0] && x <= rec[2]) return true;
            //ㅣ 2개 -> c가일치
            if (x == rec[0] && y >= rec[1] && y <= rec[3]) return true;
            if (x == rec[2] && y >= rec[1] && y <= rec[3]) return true;
        }
        return false;
    }
    //다른 사각형에 포함되지 않는가
    public boolean isIncluded(int[][] rectangle, int x, int y) {
        for (int[] rec : rectangle) {
            //해당 사각형에 대해..(x,y)가 포함되는조건은..
            if (x > rec[0] && x < rec[2] && y > rec[1] && y < rec[3]) return true;
        }
        return false;
    }
}
/**
라인타고 움직여야함
그니까 다음위치는 상하좌우 4방향중에
조건에 맞는위치
    조건: 가장바깥쪽선을 탈 수 있음.
    그렇다는거는 상하좌우 후보에서 각 후보는 바깥쪽에있어야한다.
        바깥쪽에 있으려면 
        1. 바운드안
        2. 테두리위
        3. 다른 사각형의 내부이면 안됨.어느 한 사각형이라도 내부면 불가능.
        이게 아니라면 테두리가 맞다.
*/