class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int)right - (int)left + 1;
        int[] ans = new int[size];
        int idx = 0;
        for (long i = left; i <= right; i++) {
            int row = (int)(i / n) + 1;
            int col = (int)(i % n) + 1;
            //System.out.printf("at i: %d, row:%d, col:%d\n", i, row, col);
            
            ans[idx] = row < col ? (int)col : (int)row;
            idx++;
        }
        
        return ans;
    }
}