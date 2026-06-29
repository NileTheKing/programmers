import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int ans = 0;
        //번호,요청시각,소요시간
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]) {//소요시간
                //요청시각
                if (o1[1] == o2[1]) return o1[0] - o2[0];
                else return o1[1] - o2[1];
            }else return o1[2] - o2[2];
        });
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        int doneCnt = 0;
        int idx = 0;
        int totalWait = 0;
        int currentTime = 0;
        while (doneCnt < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= currentTime) pq.offer(new int[] {idx, jobs[idx][0], jobs[idx++][1]});
            if (pq.isEmpty()) {
                currentTime = jobs[idx][0];
            }else {
                int[] polled = pq.poll();
                currentTime += polled[2];
                totalWait += (currentTime - polled[1]);
                doneCnt++;
            }
        }
        return totalWait / jobs.length;
    }
}