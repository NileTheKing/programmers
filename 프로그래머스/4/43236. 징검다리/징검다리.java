import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int INF = 1_000_000_000;
        Arrays.sort(rocks);
        int l = 0;
        int r = INF;
        int ans = INF;
        while (l <= r) {
            int m = l + (r - l) / 2; //시도하는 값.m은 바위들 간 최소거리
            //m을 만족시키기 위해 몇개제거해야하는지
            int cnt = 0;
            int prev = 0;
            for (int roq : rocks) {
                //얠 안지운다면 거리가 작아서 안됨.
                if (roq - prev < m) {
                    cnt++;
                }else {
                    prev = roq;//안지웠으니 prev
                }
            }
            //마지막위치도 계싼
            if (distance - prev < m) cnt++;
            //너무 많이 제거해야한다 -> 너무 크다
            if (cnt > n) {
                r = m - 1;
            }
            //제거안하고도 가능하다 -> 너무 목표가 작다. 더크게 시도할것.일단갱신
            else {
                ans = m;
                l = m + 1;
            }
        }
        
        return ans;
    }
}