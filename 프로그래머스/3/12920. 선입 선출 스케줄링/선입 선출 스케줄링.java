
class Solution {
    public int solution(int n, int[] cores) {
        if (cores.length >= n) return n;
        int l = 0;
        int r = 50000 * 10000 / 2;
        int t = r; //걸리는 시간 계산하는거임.
        while (l <= r) {
            int m = l + (r - l) / 2;
            int cnt = cores.length;
            //m시간 안에 다 할 수 있는지 확인.지금은 정확히 찾는게 아니라 되는지만..
            //되는지 어떻게보냐면.. m으로 가능한지인데
            for (int c : cores) {
                cnt += m / c; //m이라는 시간동안 c코어가 c걸리는데 몇개했는지
                if (cnt >= n) break;
            }
            //System.out.printf("for trying time %d, did %d\n", m, cnt);
            if (cnt < n) { //시간이 너무적었다
                l = m + 1;
            }else if (cnt >= n) {
                r = m - 1;
                t = m;
            }
        }
        // System.out.printf("T is %d\n", t);
        //t구했음.
        //이제 t-1까지 몇개끝냈는지
        int doneTil = cores.length;
        for (int c : cores) {
            doneTil += (t - 1) / c;
        }
        int left = n - doneTil; //남은갯수
        // System.out.printf("need to do %d\n",left);
        for (int i = 0; i < cores.length; i++) {
            //이시간에 작업중인애들빼고 작업없는 애들
            if (t % cores[i] != 0) continue;
            left--;
            if (left == 0) return i + 1;
        }
        return -1;
    }
}