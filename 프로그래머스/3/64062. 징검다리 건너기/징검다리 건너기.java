class Solution {
    public int solution(int[] stones, int k) {
        long l = 0;
        long r = Long.MAX_VALUE;
        long ans = 0;
        while (l <= r) {
            long m = (l + r) / 2;
            // System.out.printf("===%d===\n", m);
            //m명 가능했다고 가정해서 다 깎아버린다음에..예를들어 m이 20임.
            //그러면 20명 지나가면 20깎여있을거고.. 그 상태에서 가능하면 m+1명가능으로 메모지.
            //암튼 m숫자깎고 m+1명 가정으로하는것. for로 stones깎인거돌고 최대거리찾아서k이상이면 불가 이하면 가능
            //지금 하고자 하는것: 최대거리구하기.
            int prev = -1;
            int max = -1;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] - m < 0) {
                }else {
                    max = Math.max(max, i - prev);
                    // System.out.printf("i, prev, max: %d %d %d\n",i,prev,max);
                    prev = i;//밟음
                }
            }
            max = Math.max(max, stones.length - prev);
            // System.out.printf("max %d\n", max);
            if (max != -1 && max <= k) {
                // System.out.printf("%d success %d renew\n", m, m + 1);
                ans = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return (int)ans;
    }
}
        //2억명도 가능하니까 시뮬레이션돌리ㅕ면 터짐. 이분탐색