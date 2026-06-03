import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int ans = 0;
        for (int i = 0; i < enemy.length; i++) {
            // System.out.printf("===stage %d===\n", i + 1);
            n -= enemy[i];
            pq.offer(enemy[i]);
            while (n < 0 && k > 0 && !pq.isEmpty()) {
                // System.out.printf("n, k, pq.peek() : %d %d %d\n", n, k, pq.peek());
                n += pq.poll();
                k--;
            }
            //pq.offer(enemy[i]);
            if (n >= 0) ans = i + 1;
            else return ans;
        }
        return enemy.length;
    }
}