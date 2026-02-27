class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int m = beginning.length;
        int n = beginning[0].length;
        int min = Integer.MAX_VALUE;
        for (int row = 0; row < (1 << m); row++) {
            Outter : for (int col = 0; col < (1 << n); col++) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int face = beginning[i][j] ^ ((row >> i) & 1) ^ ((col >> j) & 1);
                        if (face != target[i][j]) continue Outter;
                    }
                }
                min = Math.min(min, Integer.bitCount(row) + Integer.bitCount(col));
            }
        }
    
    return min == Integer.MAX_VALUE ? -1 : min;
    }
}