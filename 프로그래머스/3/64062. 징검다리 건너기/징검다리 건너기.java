class Solution {
    public int solution(int[] stones, int k) {
        int l = 0;
        int r = 200000000;
        int ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            //m명이 건넜다고  치자. 그러면 각 s - m을할테고 원래4였는데 3명지나면 1남아야지
            //원래3인데 3지나면 0일테고..뛰어넘기가능이겠지. 근데 m지났다치고 한명이 더 뛰는데
            //뛰어넘는게 k칸이하? 더 가능 그러면 m+1로 갱신
            // System.out.printf("===m : %d===\n", m);
            int prev = -1;// 이전인덱스
            int maxJump = 0;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] - m > 0) {
                    //건널수있음
                    // System.out.printf("(%d) possible, prev : %d\n", i, i);
                    maxJump = Math.max(i - prev, maxJump);
                    prev = i;
                }else { //못건넘.
                    // System.out.printf("(%d) impossible, max: %d\n", i, maxJump);
                }
            }
            maxJump = Math.max(stones.length - prev, maxJump);
            // System.out.printf("final max %d\n", maxJump);
            if (maxJump <= k) {
                ans = m + 1;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return ans;
    }
}
/**
이분탐색
*/