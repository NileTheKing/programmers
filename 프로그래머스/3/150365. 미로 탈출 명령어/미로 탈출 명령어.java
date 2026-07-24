class Solution {
    int[][] directions = {{1,0},{0,-1},{0,1},{-1,0}};
    char[] command = {'d', 'l', 'r', 'u'};
    int R, C;
    int m, n;
    char[][] board;
    int k;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.k = k;
        this.m = n;//m이 세로 n이 가로가 편함
        this.n = m;
        this.R = r - 1; //목표지점0인덱스화
        this.C = c - 1;
        // System.out.printf("goal (%d, %d)\n", R, C);
        return backtrack(x - 1, y - 1, new StringBuilder());
    }
    public String backtrack(int r, int c, StringBuilder path) {
        if (r < 0 || r >= m || c < 0 || c>= n) return "impossible";
        if (r == R && c == C && path.length() == k) {
            return path.toString();
        }
        if (path.length() >= k) return "impossible";
        // System.out.printf("%d %d, %s\n", r, c, path);
        for (int i = 0; i < directions.length; i++) {
            int nr = r + directions[i][0];
            int nc = c + directions[i][1];
            //남은거리
            int remainingDist = Math.abs(R - nr) + Math.abs(C - nc);
            //필요한 스텝
            int neededExtraSteps = k - (path.length() + 1);
            if (remainingDist > neededExtraSteps) continue;
            //최단거리조차도 k보다크면 안됨
            //최단거리로 k보다작아도 홀짝
            if ((neededExtraSteps % 2) != (remainingDist % 2)) continue;
            path.append(command[i]);
            String res = backtrack(nr, nc, path);
            if (!res.equals("impossible")) return res;
            path.deleteCharAt(path.length() - 1);
        }
        return "impossible";
    }
}