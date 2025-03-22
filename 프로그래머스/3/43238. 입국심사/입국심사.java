import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        
        long answer = 0;
        Arrays.sort(times);
        //long start = ((n / times.length) - 1) * times[0];
        long start = 0;
        long end = (long)n * times[times.length - 1]; //14
        
        while (start <= end) {
            long mid = start + (end - start) / 2; //30
            long donePeople = 0;
            
            for (int time : times) {
                donePeople += mid / time;
                if (donePeople >= n) break;
            }
            
    
            if (donePeople < n) {
                start = mid + 1;
            }
            else if (donePeople >= n) {
                end = mid - 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}
/**
음 ... 일단 정렬을 하면 좋을 수도 있을듯?
큐?는 딱히 필요없지 그냥 전체 시간 구하는 거니까.
n이 0이 될떄 까지 하면 됨. 시작하자마 times.length만큼 줄이고
7, 10이 지날때마다 -해서 0될때까지

*/