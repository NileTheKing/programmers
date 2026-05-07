import java.util.*;
class Solution {
    char[][] grid;
    public int solution(int m, int n, String[] board) {
        //char[][] 변환
        grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) grid[i][j] = board[i].charAt(j);
        }//OK
        // for (char[] g : grid) System.out.println(Arrays.toString(g));
        //맵 순회하면서 2곱2블럭 찾고 블럭들을 또 모으기. 지우기. 맵 동기화 -> 없을 떄 까지
        int cnt = 0;
        while (true) {
            // System.out.printf("===simulation===\n");
            int currentCnt = 0;
            List<int[]> blocks = new ArrayList<>();
            // System.out.printf("blocks\n");
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0;j < n - 1; j++) {
                    if (isBlock(i,j)) {
                        blocks.add(new int[] {i,j});
                        // System.out.printf("(%d,%d) ", i, j);
                    }
                }
            }
            System.out.println();
            for (int[] b : blocks) {
                //b를 기준 다 지움. 근데 지워진거는 패스..
                
                if (grid[b[0]][b[1]] !='.') {
                    grid[b[0]][b[1]] = '.'; 
                    currentCnt++;
                }
                if (grid[b[0]][b[1] + 1] != '.') 
                {
                    currentCnt++;
                    grid[b[0]][b[1] + 1] = '.';
                }
                if (grid[b[0] + 1][b[1]] != '.'){
                    currentCnt++;
                    grid[b[0] + 1][b[1]] = '.';
                }
                if (grid[b[0] + 1][b[1] + 1] != '.') {
                    grid[b[0] + 1][b[1] + 1] = '.';
                    currentCnt++;
                }
            }
            //지워진게 없으면 패스
            if (currentCnt == 0) return cnt;
            
            //다음준비
            cnt += currentCnt;
            //지운것들 채워넣기. 바닥부터 순회하면서 위에가 비었다? 당겨오기.
            for(int i = m - 1;i >= 1; i--) {//바닥행
                for (int j = 0; j < n; j++) {//열
                    if (grid[i][j] == '.') {//본인이 비었으면
                        Outter : for (int k = i; k  >= 0; k--) {//빈자리 위에 찾아서 당겨옴
                            if (grid[k][j] != '.') {
                                grid[i][j] = grid[k][j];
                                grid[k][j] = '.';//얘도 떨어져야함.. .으로
                                break Outter;
                            }
                        }
                    }
                }
            }
            // for (char[] g : grid) System.out.println(Arrays.toString(g));
            // System.out.printf("deleted %d\n", currentCnt);
            // System.out.println("===end simul===");
        }
    }
    public boolean isBlock(int r, int c) {
        if (grid[r][c] =='.') return false;
        char friend = grid[r][c];
        //우
        if (grid[r][c + 1] != friend) return false; 
        //하
        if (grid[r + 1][c] != friend) return false;
        //대각
        if (grid[r + 1][c + 1] != friend) return false;
        
        return true;
    }
}
/**
시뮬레이션.완탐..연쇄폭발(스택?이건 몰겠으)

음.. 어케하냐면..일단 2곱2가 기본이고 붙어있을수있음. 대각선 상하 좌우.
그러면 모든 덩어리를 찾는 작업을 수행해. 각 모든 셀에대해서 수행을해
그다음에 묶어야함. 묶은다음에 한번에 처리임. 예를들어 저거 대각선 두개는 같은 블럭으로 묶일거고
...

암튼 그러면 블럭단위로 한 덩어리를 또 잡고.. 그거 다지우고...
그다음에 맵 풀순회하면서 아래로 붙이고..
지워진게 없으면 끝.
*/