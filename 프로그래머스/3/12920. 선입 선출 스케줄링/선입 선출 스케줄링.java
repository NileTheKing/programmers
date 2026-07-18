class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) return n;
        int l = 0;
        int r = 10000 * 50000 / 2;
        int t = r;
        //배정 다하는 T를 구함
        while (l <= r) {
            int m = (l + r) / 2;
            long cnt = cores.length;
            for (int c : cores) cnt += (m / c);
            if (cnt >= n) {//배정가능
                t = m;
                r = m - 1;
            }else {
                l = m + 1;
            }
        }
        //배정 다못하는 T - 1에서는 다 배정을안했을거고 T에는 모든 배정이완료
        //즉 T-1까지 일하고 몇개남는지 안다음에
        //T에서 직접시뮬레이션
        int t_minus = t - 1;
        int done_t_minus = cores.length;
        for (int c : cores) done_t_minus += (t_minus / c);
        int left = n - done_t_minus;
        for (int i = 0; i < cores.length; i++) {
            //c = 2, t = 5... 일중.. 4면일안함
            if (t % cores[i] != 0) continue;
            left--;
            if (left == 0) return i + 1;
        }
        return -1;
    }
}
/**
    1  2  3
0:  1  2  3
*/