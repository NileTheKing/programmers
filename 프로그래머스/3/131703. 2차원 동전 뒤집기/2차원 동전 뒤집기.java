class Solution {
    public int solution(int[][] beginning, int[][] target) {
        //i 모든경우 j모든경우
            //되는지확인
            //되면 몇개돌렸는지
        int m = beginning.length;
        int n = beginning[0].length;
        int ans = m + n;
        for (int i = 0; i < (1 << m); i++) {
            Outter: for (int j = 0; j < (1 << n); j++) {
                //이상태로 봤을때 모든칸이 target과 같아야함
                //테스트!
                for (int r = 0; r < m; r++) {
                    for (int c = 0; c < n; c++) {
                        int afterFlips = beginning[r][c] ^ ((i >> r) & 1) ^ ((j >> c) & 1);
                        if (afterFlips != target[r][c]) continue Outter;
                    }
                }
                // System.out.printf("%d %d success\n", i, j);
                int cnt1 = 0;
                //모두 같음... 이제 몇개뒤집었는지 카운트
                for (int k = 0; k < m; k++) {//그니ㅏㄲ i의 각 스위치켜짐을 확인
                    if (((i >> k) & 1) == 1) cnt1++;
                }
                int cnt2 = 0;
                for (int k = 0; k < n; k++) {//그니ㅏㄲ i의 각 스위치켜짐을 확인
                    if (((j >> k) & 1) == 1) cnt2++;
                }
                ans = Math.min(cnt1+cnt2, ans);
            }
        }
        return ans == m + n ? -1 : ans;
    }
}