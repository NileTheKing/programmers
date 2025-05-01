import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        int length = times.length;
        Arrays.sort(times);
        long left = 1; //1
        long right = (long)times[length - 1] * n; // 60

        while(left <= right) {
            long done = 0;
            long mid = left + (right - left) / 2;
            for (int time : times) {
                done += mid / time; //mid는 시간. 각 심사관별로 몇명처리가능한지 계싼
            }
            
            //mid는 예상시간
            //done은 총 걸린 시간을 mid로 예측했을 때 총 처리한 사람 수
            //if(done == n) break;
            if (done >= n) {//실제로 n명보다 더 처리할 수 있었다면 너무 시간을 널널하게줌
                right = mid - 1;
                answer = mid;
            }
            else {//잘 수행 못헀으므로 시간을 더 줌
                left = mid + 1;
            }
        }
        
        return answer;
    }
}
/**
구하는 최솟값: 모든 사람이 심사 받는데 걸리는 시간

우선 times 정렬해가지고 가장 짧은애랑 가장 긴 애로 해봄
mid만들어서 

7과 10의 사이인 8.5라고 가정
그러면 8.5 
*/