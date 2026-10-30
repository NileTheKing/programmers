import java.util.*;
class Solution {
    public int solution(int[] a) {
        int[] minFromLeft = new int[a.length];
        int[] minFromRight = new int[a.length];
        Arrays.fill(minFromLeft, Integer.MAX_VALUE);
        Arrays.fill(minFromRight, Integer.MAX_VALUE);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < a.length; i++) {
            minFromLeft[i] = Math.min(a[i-1], min);
            min = Math.min(min, minFromLeft[i]);
        }
        min = Integer.MAX_VALUE;
        for (int i = a.length - 1 - 1; i >= 0; i--) {
            minFromRight[i] = Math.min(a[i+1], min);
            min = Math.min(min, minFromRight[i]);
        }
        
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            //둘다 나보다작으면 실패
            // System.out.printf("leftMin me rightMin=(%d %d %d)\n", minFromLeft[i],a[i],minFromRight[i]);
            if (Math.max(minFromLeft[i], minFromRight[i]) < a[i]) continue;
            //이외는 통과(1개만, 둘다 크면)
            else cnt++;
        }
        return cnt;
    }
}