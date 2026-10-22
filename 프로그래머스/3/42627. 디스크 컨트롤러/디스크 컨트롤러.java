import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        //jobs[i] : i번작업, jobs[i][0]: 시점, jobs[i][1]: 소요시간
        //우선순위: 작업소요시간 > 작업요청시각 > 번호
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> {
                if (o1[2] != o2[2]) return o1[2] - o2[2];
                if (o1[1] != o2[1]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        );
        
        int[][] infoJobs = new int[jobs.length][3];//작업번호 시점 소요시간
        for (int i = 0; i < jobs.length; i++) {
            infoJobs[i][0] = i;
            infoJobs[i][1] = jobs[i][0];
            infoJobs[i][2] = jobs[i][1];
        }
        Arrays.sort(infoJobs, (o1, o2) -> o1[1] - o2[1]);
        int infoIdx = 0;
        int sum = 0;
        int done = 0;
        int t = 0;
        // System.out.printf("===debug while====\n");
        while (done < infoJobs.length) {
            //일단 지금시간에 요청온거 다넣음
            // System.out.printf("---infoIdx:%d, sum:%d, done:%d, t:%d\n", infoIdx, sum, done, t);
            while (infoIdx < infoJobs.length && infoJobs[infoIdx][1] <= t) {
                pq.offer(new int[] {infoJobs[infoIdx][0], infoJobs[infoIdx][1], infoJobs[infoIdx][2]});
                infoIdx++;
            }
            //작업꺼내서 처리
            if (pq.isEmpty()) {
                t = infoJobs[infoIdx][1];
                continue;
            }
            int[] polled = pq.poll();
            int requestedAt = polled[1];
            int timeTaken = polled[2];
            // System.out.printf("polled: [%d %d %d]\n", polled[0], requestedAt, timeTaken);
            done++;
            t += timeTaken;
            int waited = t - requestedAt;
            sum += waited;
            
        }

        return sum / infoJobs.length;
    }
}
/**
큐는 우선순위판단용
*/