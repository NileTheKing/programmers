import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int q : queue1) {
            q1.offer(q);
            sum1 += q;
        }
        for (int q : queue2) {
            q2.offer(q);
            sum2 += q;
        }
        // System.out.printf("sum1 sum2 %d %d\n", sum1, sum2);
        int cnt = 0;
        int limit = queue1.length * 3;
        while (cnt <= limit) {
            if (sum1 == sum2) {
                return cnt;
            }else if(sum1 > sum2) {
                int polled = q1.poll();
                sum1 -= polled;
                sum2 += polled;
                q2.offer(polled);
            }else {
                int polled = q2.poll();
                sum2 -= polled;
                sum1 += polled;
                q1.offer(polled);
            }
            cnt++;
            
        }
        return cnt >= limit ? -1 : cnt;
    }
}