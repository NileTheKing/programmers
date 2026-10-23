import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        if (k >= enemy.length) return enemy.length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < enemy.length; i++) {
            // System.out.printf("====roundIdx=%d====\n", i);
            int enemyNum = enemy[i];
            pq.offer(enemyNum);
            // System.out.printf("initial: n:%d, enemyNum:%d, leftK:%d\n", n, enemyNum, k);
            while (n < enemyNum && k > 0 && !pq.isEmpty()) {
                k--;
                n += pq.poll();
            }
            if (n < enemyNum) return i;
            // System.out.printf("after mujeokkwon: n:%d, , leftK:%d\n", n, k);
            n -= enemyNum;
            // System.out.printf("terminal: n:%d, , leftK:%d\n", n, k);
        }
        return enemy.length;
    }
}