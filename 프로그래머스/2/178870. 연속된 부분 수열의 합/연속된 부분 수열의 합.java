class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] ans = new int[2]; //시작,끝인덱스
        long sum = 0;
        int l = 0;
        int min = sequence.length + 1;
        for (int i = 0; i < sequence.length; i++) {
            //i이자 r은 오른쪽으로 계쏙간다.
            // System.out.printf("===start of %d===\n", i);
            int r = i;
            sum += sequence[i];
            //l이 너무 왼쪽에 있으면 크니까 안됨.
            while (l < r && sum > k) {
                sum -= sequence[l++];
            }
            // System.out.printf("l,r,sum : %d, %d, %d\n", l, r, sum);
            //지금은 l부터 r까지합임.
            if (sum == k && r - l + 1 < min) {
                ans[0] = l;
                ans[1] = r;
                min = r - l + 1;
                // System.out.printf("l,r,min\n", l, r, min);
            }
            // System.out.printf("===end of %d===\n", i);
        }
        return ans;
    }
}