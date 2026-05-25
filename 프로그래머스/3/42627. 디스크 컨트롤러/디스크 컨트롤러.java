import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int[][] sortedJobs = new int [jobs.length][3];
        //인덱스, 요청시간, 소요시간
        for (int i = 0; i < jobs.length; i++) {
            sortedJobs[i] = new int[] {i, jobs[i][0], jobs[i][1]};
        }
        Arrays.sort(sortedJobs, (o1, o2) -> o1[1] - o2[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>(//얘는 인덱스..필요
            (o1,o2) -> {
                if (o1[2] != o2[2]) {//소요시간 짧은거
                    return o1[2] - o2[2];
                }else {
                    if (o1[1] != o2[1]) { //소요시간똑같으면 요청시각
                        return o1[1] - o2[1];
                    }else {//똑같으면 작업번호
                        return o1[0] - o2[0];
                    }
                }
            }
        );
        int idx = 0;
        int t = 0;
        int sum = 0;
        int done = 0;
        while (done < sortedJobs.length) {
            //지금 t까지(포함) q에넣기
            while (idx < sortedJobs.length && sortedJobs[idx][1] <= t) {
                // System.out.printf("%d offered\n", idx);
                pq.offer(sortedJobs[idx]);
                idx++;
            }
            // System.out.printf("idx %d\n", idx);
            //지금시간 + 얘의 소요시간 - 요청시각구해서 전체에 더한다.
            // if (pq.isEmpty()) break;
            // System.out.printf("%d %d %d polled\n", pq.peek()[0], pq.peek()[1], pq.peek()[2]);
            if (pq.isEmpty()) {
                t = sortedJobs[idx][1];
            }else {
                int[] polled = pq.poll();
                t += polled[2];
                // System.out.printf("%d added\n", t - polled[1]);
                sum += (t - polled[1]);
                done++;    
            }
            
        }
        return sum / sortedJobs.length;
    
        
    }
}
/**
jobs가 정렬안한상태로 들어옴..
그러면 이제 시간순으로 정렬을한다치면서 idx로 추적
시간가지고 시뮬레이션돌리면서(왜냐하면 대기시간을 구해야하니까)
*/