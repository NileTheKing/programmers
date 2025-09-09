import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        int coverageWidth = 2 * w + 1;
        int cnt = 0;
        int lastCovered = 0;
        for (int s : stations) {
            int startCovering = s - w;
            int gap = startCovering - 1 - lastCovered;
            cnt += (int)Math.ceil((double)gap / coverageWidth);
            lastCovered = s + w;
        }
        if (lastCovered < n) {
            int gapLength = n - lastCovered;
            cnt += (int) Math.ceil((double) gapLength / coverageWidth);
        }
        return cnt;
    }
}