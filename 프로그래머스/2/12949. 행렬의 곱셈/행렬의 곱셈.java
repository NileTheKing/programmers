class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int m = arr1.length;
        int n = arr1[0].length;
        int l = arr2[0].length;
        int[][] ans = new int[m][l];
        //결과는 m * l
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < l; j++) {
                int sum = 0;
                for (int k  = 0; k < n; k++) {
                    sum += arr1[i][k] * arr2[k][j];
                    //System.out.printf("adding %d and %d for ans[%d][%d]\n", arr1[i][k], arr2[k][j], i, j);
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }
}
/**
1 4    3 3 
3 2    3 3 
4 1
*/