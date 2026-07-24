import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int l = 0;
        int r = distance;
        int ans = r;
        while (l <= r) {
            int m = (l + r) / 2;
            //n개까지 제거가능.제거를할때는 rock-prev가 m보다 작으면 그 바위는 존재불가
            int prev = 0;
            int cnt = 0;//제거한바위
            for (int roc : rocks) {
                if (roc - prev < m) cnt++;
                else prev = roc;
            }
            if (distance - prev < m) cnt++;
            
            if (cnt > n) {//너무 많이지웠다 -> m이너무 빡빡해서,작아서 
                r = m - 1;
            }else {
                ans = m;
                l = m + 1;
            }
        }
        return ans;
    }
}