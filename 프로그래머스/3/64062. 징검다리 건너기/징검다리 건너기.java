import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int l = 0;
        int r = 200000000;
        int m = l + (r - l) / 2;
        int ans = 0;
        
        while (l <= r) {
            
            m = l + (r - l) / 2;

            boolean possible = true;
            int cnt = 0;
            for (int s : stones) {
                if (s - m >= 0) {
                    cnt = 0;
                }else {
                    cnt++;
                }
                if (cnt >= k) possible = false;
            }
            //System.out.printf("True/False : %b\n", possible);
            if (possible) {//L을 늘려서 더 만흥ㄴ 사람 시도
                l = m + 1;
                ans = m;//되는놈
            }else { //
                r = m - 1; 
            }
        }
        return ans;
    }
}