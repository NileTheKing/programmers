import java.util.*;
class Solution {
    char[][] grid;
    int m,n;
    public int solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        this.grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = board[i].charAt(j);
            }
        }
        //잘나옴
        //이제할것..게임무한반복..지운거없을떄까지
        int cnt = 0;
        int debug = 0;
        while (true) {
            List<int[]> blocks = getBlocks();
            //그다음에 카운팅할겨..각덩어리별로. 덩어리마다 체크해야함
            Set<String> deleted = new HashSet<>();//dedup
            int cntTurn = 0;
            for (int[] b : blocks) {
                //b는 원점이고 모든 점 다해야함
                int cntBlock = 0;
                if (!deleted.contains(String.valueOf(b[0]+"+"+b[1]))) {
                    cntBlock++;
                    deleted.add(String.valueOf(b[0]+"+"+b[1]));
                }
                if (!deleted.contains(String.valueOf((b[0]+1)+"+"+b[1]))) {
                    cntBlock++;
                    deleted.add(String.valueOf((b[0]+1)+"+"+b[1]));
                }
                if (!deleted.contains(String.valueOf(b[0]+"+"+(b[1]+1)))) {
                    cntBlock++;
                    deleted.add(String.valueOf(b[0]+"+"+(b[1]+1)));
                }
                
                if (!deleted.contains(String.valueOf((b[0]+1)+"+"+(b[1]+1)))) {
                    cntBlock++;
                    deleted.add(String.valueOf((b[0]+1)+"+"+(b[1]+1)));
                }
                //다지우기
                grid[b[0]][b[1]] = '#';
                grid[b[0]+1][b[1]] = '#';
                grid[b[0]][b[1]+1] = '#';
                grid[b[0]+1][b[1]+1] = '#';
                // System.out.printf("원점(%d,%d)에대해, counted %d\n", b[0],b[1],cntBlock);
                // System.out.printf("원점처리후 set상태\n");
                // for (String s : deleted) System.out.printf("%s ", s);
                // System.out.println();
                cntTurn +=cntBlock;
            }
            if (cntTurn == 0) break;//한게없으므로 종료
            // System.out.printf("turnCnt:%d\n", cntTurn);
            cnt += cntTurn;
            // System.out.printf("cnt: %d\n", cnt);
            //debug용 그림그리기
            // System.out.println();
            //다시그리기
            // System.out.printf("before\n");
            // for (char[] g : grid) System.out.println(Arrays.toString(g));
            // System.out.println();
            refresh();
            // System.out.printf("after\n");
            // for (char[] g : grid) System.out.println(Arrays.toString(g));
        }
        
        return cnt;
    }
    List<int[]> getBlocks() {
        //순회하면서..1부터 m-1, 1부터n-1까지.. 주변4칸되면 추가
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                int origin = grid[i][j];
                if (origin == '#') continue;
                if (grid[i][j+1] != origin) continue;
                if (grid[i+1][j] != origin) continue;
                if (grid[i+1][j+1] != origin) continue;
                res.add(new int[] {i,j});// i,j는 통과
            }
        }
        return res;
    }
    void refresh() {
        //아래층부터 보면서 내가 비었고 위에 뭐있으면 끌어당김
        for (int c = 0; c < n; c++) {
            StringBuilder sb = new StringBuilder();
            for (int r = m - 1; r >= 0; r--) {
                if (grid[r][c] != '#') sb.append(grid[r][c]);
                grid[r][c] = '#';
            }
            //이제 sb읽어서 아래부터 다시 채워넣는다.
            for (int i = 0; i < sb.length(); i++) {
                grid[m - 1 - i][c] = sb.charAt(i);
            }
        }
        
    }
}