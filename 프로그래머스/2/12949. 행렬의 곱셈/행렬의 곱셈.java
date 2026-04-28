class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int l = arr1.length;
        int m = arr1[0].length;
        int n = arr2[0].length;
        //l * n
        int[][] ans = new int[l][n];
        for (int r = 0; r < l; r++) {
            for (int c = 0; c < n; c++) {
                //행렬의 곱은.. 1의 모든r 2의 모든c를 곱해서 더해야함.
                int val = 0;
                for (int i = 0; i < m; i++) val += arr1[r][i] * arr2[i][c];
                ans[r][c] = val;
            }
        }
        return ans;
    }
}