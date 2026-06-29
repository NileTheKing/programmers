import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> {
                if (o1[2] == o2[2]) {//소요시간
                    if (o1[1] == o2[1]) return o1[0] - o2[0];
                    else return o1[1] - o2[1];
                }else return o1[2] - o2[2];
            }
        ); //extrajob [작업번호,ㅇ ㅛ청시각, 소요시간]
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        //이제 q에 작업들이있다. 다 될떄까지 하면 됨
        int ans = 0;
        int t = 0;
        int idx = 0;
        while (idx < jobs.length || !pq.isEmpty()) {
            while (idx < jobs.length && jobs[idx][0] <= t) {
                // System.out.printf("%d offered\n", idx);
                pq.offer(new int[] {idx, jobs[idx][0], jobs[idx][1]});
                idx++;
            }
            // System.out.printf("===polling===\n");
            if (pq.isEmpty()) {
                t = jobs[idx][0];
            } else {
            int[] polled = pq.poll();
            int duration = polled[2];
            int wait = t + duration - polled[1];
            // System.out.printf("(%d, %d ,%d) polled\n", polled[0],polled[1],polled[2]);
            // System.out.printf("waited = %d\n", wait);
            ans += wait;
            t += duration;
            // System.out.printf("t = %d\n", t);
            }
        }
        return ans / jobs.length;
        
    }
}
/**
작업들이있다. 우선순위대로 해결해라. 우선순위: 소요시간, 요청시각, 작업번호
jobs[i] : i작업번호, (요청시각, 소요시간)
*/