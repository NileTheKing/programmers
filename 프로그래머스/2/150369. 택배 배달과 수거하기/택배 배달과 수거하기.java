class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long d = 0;//배달할 것 (+할것 -남는것)
        long p = 0;//픽업할 것 
        //n은 1인덱스, pickup은 0인덱스
        long ans = 0;//
        for (int i = n - 1; i >= 0; i--) {
            //거리는 i+1다.
            d += deliveries[i];
            p += pickups[i];
            //할일이 남았으면
            int rep = 0;
            while (d > 0 || p > 0) {
                rep++;
                d -= cap;
                p -= cap;
            }
            ans += (2 * rep * (i + 1));
        }
        return ans;
    }
}
/**
1.멀리서부터 가는게 이득 (할일이 남으면 처리가능)
2.어차피 가야한다.
3.잉여관리
*/