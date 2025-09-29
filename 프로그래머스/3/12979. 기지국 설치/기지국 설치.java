import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        int coverageWidth = 2 * w + 1;
        int lastCovered = 0;
        int cnt = 0;
        for (int s : stations) {
            int startCovering = s - w;
            int gap = startCovering - lastCovered - 1; // 3 - 0 - 1 = 2
            int cntThisTime = (int)Math.ceil((double)gap / coverageWidth);
            //System.out.printf("from %d to %d, gap  %d, cnt this time: %d\n", lastCovered, startCovering, gap, cntThisTime);
            cnt += cntThisTime;
            lastCovered = s + w;
        }
        
        if (lastCovered < n) {
            int gap = n - lastCovered;
            cnt += (int)Math.ceil((double)gap / coverageWidth);
        }
        return cnt;
    }
}