import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        int coverageWidth = w * 2 + 1;
        int lastCovered = 0;
        int ans = 0;
        for (int s : stations) {
            int startCovering = s - w;
            int gap = startCovering - lastCovered - 1;
            int cnt = (int)Math.ceil((double)gap / coverageWidth);
            ans += cnt;
            //System.out.printf("startCovering %d, gap %d, coveraageWidth %d, cnt %d\n", startCovering, gap, coverageWidth, cnt);
            lastCovered = s + w;
        }
        if (lastCovered < n) {
            int gap = n - lastCovered;
            ans += (int)Math.ceil((double)gap / coverageWidth);
        }
        return ans;
    }
}