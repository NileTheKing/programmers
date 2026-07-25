import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        //정렬
        int[][] jobsplus = new int[jobs.length][3];//인덱스추가
        for (int i = 0; i < jobs.length; i++) {
            int[] j = jobs[i];
            jobsplus[i][0] = j[0];//요청
            jobsplus[i][1] = j[1];//소요
            jobsplus[i][2] = i;//인덱스..추가
        }
        
        
        //이제 본격작업..준비
        Arrays.sort(jobsplus, (o1, o2) -> o1[0] - o2[0]);
        int idx = 0;
        int cnt = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            //0:요청시각 1:소요시간 2:작업번호
            //소요시간리턴
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            //아닌애들.. 동일한애들
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            //이제 작업벊만
            return o1[2] - o2[2];
        });
        int t = 0;
        //다될떄까지
        int sum = 0;
        while (cnt < jobs.length) {
            //시간맞는애들추가
            while (idx < jobs.length && jobsplus[idx][0] <= t) {
                pq.offer(new int[] {jobsplus[idx][0],jobsplus[idx][1],jobsplus[idx][2]});
                idx++;
            }
            //처리
            if (!pq.isEmpty()) {
                int[] polled = pq.poll();
                int requestedAt = polled[0];
                int duration = polled[1];

                int waited = t + duration - requestedAt;
                sum += waited;
                cnt++;
                t += duration;
            }
            //시간보정
            else if (idx < jobsplus.length && t < jobsplus[idx][0]) t = jobsplus[idx][0];
        }
        return sum / jobs.length;
    }
}
/**
정렬을 사용 -> 큐생략?
pq는 필요하긴할듯

코드
1.정렬
2.while (!완성) 완성은 카운트
    3.조건맞는애들 pq에넣기..while idx.. 시간조건이니까 나중에 시간비면처리해줘야함
    넣은애중에 뽑아가지고 처리 시간계산 등
*/