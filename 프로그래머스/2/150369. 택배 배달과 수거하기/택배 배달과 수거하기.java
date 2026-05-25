class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long ans = 0;
        int p = 0; // 배달해야할 갯수.. 음수면 잉여
        int d = 0;
        //먼 집부터
        for (int i = n - 1; i >= 0; i--) {
            p += deliveries[i];
            d += pickups[i];
            int currentRoundtrip = 0;
            while (p > 0 || d > 0) {
                currentRoundtrip++;
                p -= cap;//여기서 cap만큼하는게 약간이해 안감지금.
                d -= cap;
            }
            // System.out.printf("at %d, %d needed\n", i, currentRoundtrip);
            ans += ((i + 1) * currentRoundtrip) * 2;
        }
        return ans;
    }
}
/**
무조건 일을끝내야한다
멀리있는거부터 가는게 이득이다
배달완료 & 수거완료 다 해야한다..
그렇다는거는 멀리있는거부터 하는거고 그집에 몇번가야하는지 계산을 해야함
그거는 이제 왕복횟수..로 길이 구할수있고
근데 여기서 생기는 문제.. 딱코가 아니라면? 그거는 이제 잉여수 +- 부호가지고 가져갈것 남은것 이렇게 관리하면서 다음거 계산할때 영향을 주면 됨..
cap 50 < 최대
n 집갯수(거리) 100,000 << n^2절애ㅏㄴ됨.
*/