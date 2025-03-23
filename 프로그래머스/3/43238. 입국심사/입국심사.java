import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        
        long answer = 0;
        Arrays.sort(times);
        
        long low = 1;
        long high = (long)times[times.length - 1] * n;
        
        while (low <= high) {
            long mid = low + (high - low) / 2; //중간 시간
            long donePeople = 0;
            
            for (int time : times) {
                donePeople += mid/time;
                if (donePeople >= n) break;
            }
            
            if (donePeople >= n) {
                high = mid - 1;
                answer = mid;
            }
            else {
                low = mid + 1;
            }
            
            
        }
        return answer;
    }
}
/**
만약 10,10이라면
0분 4명남고
10분 2명나목
20분 0명남음

10 * (n / 창구수)

low와 high(가장 오래걸리는 창구) 를 두고 평균값은 mid를 구해 mid시간으로 해결할 수 있는지 확인
해결할 수 있는지 확인: mid동안 처리한 사람수를 카운트해서 gt n or lt n등 케이스별로 low/high 수정
*/