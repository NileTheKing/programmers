import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        //제일 오래걸리면 10억명대기에 10억분걸리고 심사가 1명..그럼 10억 곱하기 10억인데
        long INF = 1_000_000_000L * 1_000_000_000;
        // System.out.printf("%d\n", INF);
        long l = 0;
        long r = INF;
        Arrays.sort(times);
        long ans = r;
        while (l <= r) {
            long cnt = 0; 
            long m = l + (r - l) / 2;//시간제한
            // System.out.printf("====TRYING %d===\n", m);
            for (int t : times) {
                cnt += (m / t);
                if (cnt >= n) break;//이미 가능
            }
            // System.out.printf("counted %d\n", cnt);
            //시간안에 처리했는지 확인
            if (cnt >= n) {
                r = m - 1;
                ans = m;
            }else {
                l = m + 1;
            }
        }
        return ans;
    }
}
/**
사람이 10억 -> 시뮬레이션 불가능. 최적화문제 -> log류.. paremtric search
*/