import java.util.*;
class Solution {
    int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
    public int[] solution(String[] grid) {
        boolean[][][] visited = new boolean[grid.length][grid[0].length()][4];
        //-> <- ㅣㅣ
        
        char[][] map = new char[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                map[i][j] = grid[i].charAt(j);
                // System.out.printf("%c ", map[i][j]);
            }
            // System.out.println();
        }
        //이제 완탐해봐야해. 완전히 동일한 사이클의 정의:위치가 똑같고 방향도 똑ㄱ같으면 동일임.
        //둘중 하나만 달라도 다른 사이클.
        //그러면 모든 곳을 시작점으로 잡아서 여러 방향으로 시작을 끊어
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int d = 0; d < directions.length; d++) {
                    if (visited[i][j][d]) continue;
                    ans.add(helper(map, visited, i, j, d));
                }
            }
        }
        ans.sort((a,b) -> (a-b));
        return ans.stream().mapToInt(i->i).toArray();
    }
    public int helper(char[][] map, boolean[][][] visited, int r, int c, int d) {
        int len = 0;
        while (true) {
            if (visited[r][c][d]) break;
            visited[r][c][d] = true;
            len++;
            char currentLensSays = map[r][c];
            int next_d = d;//일단초기화
            if (currentLensSays == 'S') {
                next_d = d;
            }else if (currentLensSays == 'L') {
                next_d = (d + 3) % 4;
            }else {//R
                next_d = (d + 1) % 4;
            }
            d = next_d;
            int nr = (r + directions[d][0] + map.length) % map.length;
            int nc = (c + directions[d][1] + map[0].length) % map[0].length;
            r = nr;
            c = nc;
        }
        
        return len;
    } 
}
/**
결국 사이클이 몇개도냐 이거지. 그래프인데 굉장히 특이한 그래프

*/