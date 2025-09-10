
class Solution {
    public int[] solution(int n, long left, long right) {
        int[] ans = new int[(int)(right - left + 1)];
        int idx = 0;
        for (long i = left; i <= right; i++) {
            long row = i / n;
            long col =  i % n;
            ans[idx++] = (int)Math.max(row, col) + 1;
        }
        return ans;
    }
}
/**
엄청난 n을 봐라 무려 10,000,000이다.
n^2  하면 10^14. 10^8이 1억.
right - left < 10^5. (right - left) ^2  < 10^10.100000
*/