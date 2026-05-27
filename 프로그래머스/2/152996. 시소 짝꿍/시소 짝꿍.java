import java.util.*;
class Solution {
    public long solution(int[] weights) {
        long cnt = 0;
        Arrays.sort(weights);
        int[] weightBook = new int[1001];
        for (int w : weights) {
            cnt += weightBook[w];
            //내몸무게 절반
            if (w % 2 == 0) cnt += weightBook[w / 2];
            //2/3
            if (w % 3 == 0) cnt += weightBook[2 * w / 3];
            //3/4
            if (w % 4 == 0) cnt += weightBook[3 * w / 4];
            weightBook[w]++;
        }
        return cnt;
    }
}