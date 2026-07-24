class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) return n;
        int l = 0;
        int r = 50000 * 10000 / 2;
        int t_plus = r;
        while (l <= r) {
            int m = (l + r) / 2;
            long cnt = 0;
            cnt += cores.length;
            for (int c : cores) {
                cnt += m / c;
            }
            if (cnt >= n) {//시간 m주면 n개이상가능..
                t_plus = m;
                r = m - 1;
            }else {
                l = m + 1;
            }
        }
        //우리는 n개 딱 처리할수있는거 원함. 그거 -1시간하면 마무리가능.
        int t = t_plus - 1;
        //t일때 몇개가능
        int cnt = 0;
        cnt += cores.length;
        for (int c : cores) cnt += t / c;
        int left = n - cnt;
        // System.out.printf("tplus = %d\n", t_plus);
        // System.out.printf("cnt = %d\n", cnt);
        // System.out.printf("left = %d\n", left);
        for (int i = 0; i < cores.length; i++) {
            if (t_plus % cores[i] != 0) continue;//일중인코어
            left--;
            if (left == 0) return i + 1;
        }
        return -1;
    }
}