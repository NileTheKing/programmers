class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int)right - (int)left + 1;
        int[] ans = new int[size];
        int idx = 0;
        for (long i = left; i <= right; i++) {
            long row = i / n + 1;
            long col = i % n + 1;
            //System.out.printf("at i: %d, row:%d, col:%d\n", i, row, col);
            
            ans[idx] = row < col ? (int)col : (int)row;
            idx++;
        }
        
        return ans;
    }
}