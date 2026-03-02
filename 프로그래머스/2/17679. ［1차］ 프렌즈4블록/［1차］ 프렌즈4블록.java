import java.util.*;
class Solution {
    int[][] directions = {{0,1},{1,0},{1,1}};
    public int solution(int m, int n, String[] board) {
        char[][] friends = new char[m][n];
        for (int i = 0; i < m; i++) {
            friends[i] = board[i].toCharArray();
        }
        boolean found = true;
        int ans = 0;
        while (found) {
            
            found = false;
            Set<List<Integer>> set = new HashSet<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (friends[i][j] == '$') continue;
                    List<List<Integer>> blocks = find(i,j,friends);
                    if (blocks != null) {
                        for (List<Integer> b : blocks) {
                            set.add(b);
                            found = true;
                        }
                    }else continue;
                }
            }

            for (List<Integer> s : set) {
                friends[s.get(0)][s.get(1)] = '$';
                ans++;                
            }
            //떨어뜨리기 작업
            for (int i = m-1; i >= 0; i--) { //햔번에 하나만떨구는대신 풀스캔.
                for (int j = 0; j < n; j++) {
                    if (friends[i][j] == '$') fill(i,j,friends);
                }
            }
            
            
        }
        return ans;
    }
    public List<List<Integer>> find(int r, int c, char[][] friends) {
        List<List<Integer>> blocks = new ArrayList<>();
        blocks.add(Arrays.asList(r,c));
        char origin = friends[r][c];
        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr < 0 || nr >= friends.length || nc < 0 || nc>= friends[0].length) return null;
            if (friends[nr][nc] != origin) return null;
            
            blocks.add(Arrays.asList(nr,nc));
        }
        // for (List<Integer> b : blocks) {
        //     friends[b.get(0)][b.get(1)] = '$'; //찾은애들은 $처리.. 방문처리!근데 방문처리하고나서 원래대로 어떻게돌리냐
        // }
        return blocks;
    }
    public void fill(int r, int c, char[][] friends) { //하나만 ㄷ떨굼
        
        //r,c가 지금 비어있으니까 위로 올라가서 하나 떙겨오셈
        for (int i = r; i >= 0; i--) {
            if (friends[i][c] != '$') {
                char temp = friends[i][c];
                friends[i][c] = '$';
                friends[r][c] = temp;
                return;
            }
        }
        return;
        
    }
}