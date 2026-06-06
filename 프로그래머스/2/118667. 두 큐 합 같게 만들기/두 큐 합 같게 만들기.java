import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0, sum2 = 0;
        for (int q : queue1) {
            q1.add(q);
            sum1 += q;
        }
        for (int q : queue2) {
            q2.add(q);
            sum2 += q;
        }
        int cnt = 0;
        int limit = queue1.length * 3 + 1;
        while (sum1 != sum2 && cnt < limit) {
            // if (q1.isEmpty() && q2.isEmpty()) break;
            if (sum1 > sum2) {
                int polled = q1.poll();
                q2.offer(polled);
                sum2 += polled;
                sum1 -= polled;
            }else {
                int polled = q2.poll();
                q1.offer(polled);
                sum1 += polled;
                sum2 -= polled;
            }
            cnt++;
        }
        // System.out.printf("%d %d and cnt %d\n", sum1, sum2, cnt);
        return cnt >= limit ? -1 : cnt;
    }
}