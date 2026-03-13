class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] ans = new int[2];
        int sum = 0;
        int l = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < sequence.length; i++) {
            
            int r = i;
            //System.out.printf("====[%d,%d]====\n", l,r);
            sum += sequence[r];
            //System.out.printf("[%d,%d] sum= %d\n", l,r, sum);
            while (sum > k && l < r) { //같
                sum -= sequence[l++];
            }
            //System.out.printf("after [%d, %d] sum= %d\n", l,r,sum);
            if (sum == k) {
                //길이가 더 짧으면 덮ㅅ어쓰기
                if (r - l + 1 < min) {
                    //System.out.printf("{%d,%d} set\n", l,r);
                    ans[0] = l;
                    ans[1] = r;
                    min = r - l + 1;
                }//아닌데 더 뒤에 나온거면 컷
            }
            //System.out.printf("====end [%d,%d]====\n", l,r);
        }
        return ans;
    }
}
/**
sliding window.
*/