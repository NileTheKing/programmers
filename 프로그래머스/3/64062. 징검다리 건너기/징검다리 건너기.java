import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int l = 0;
        int r = 200000001;
        int ans = 0;
        while (l <= r) {
            int m = (l + r) / 2; //m명이 밟음..성공하면 m명성공
            // System.out.printf("===trying %d====\n",m);
            int maxJump = 0;
            int prev = -1;
            for (int i = 0; i < stones.length; i++) {
                // System.out.printf("checking stones[%d] = %d\n", i, stones[i] - m);
                if (stones[i] - m >= 0) {//밟기가능
                    prev = i;
                }else {
                    //뛰어넘어야함..
                    maxJump = Math.max(maxJump, i - prev + 1);
                }
            }
            //마지막인덱스다음거
            // System.out.printf("maxJump before last: %d\n", maxJump);
            if (stones.length - prev < 0) maxJump = Math.max(maxJump, stones.length - prev);
            // System.out.printf("maxJump after last: %d\n", maxJump);
            //만약 k보다 크면..너무멀리뜀..너무많이 건넛어
            if (maxJump > k) {
                r = m - 1;
            }else {//가능
                ans = m;
                l = m + 1;
            }
        }
        return ans;
    }
}