import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int jobsIdx = 0;
        int sum = 0;
        int count = 0;
        int t = 0;
        while (count < jobs.length) {
            while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= t) {
                pq.offer(jobs[jobsIdx]);
                jobsIdx++;
            }
            
            if (pq.isEmpty()) {
                t = jobs[jobsIdx][0];
                continue;
            }
            
            int[] polled = pq.poll();
            count++;
            int requestedAt = polled[0];
            int timeTaken = polled[1];
            t += timeTaken;
            int waited = t - requestedAt;
            sum += waited;
        }
        return sum / jobs.length;
    }
}