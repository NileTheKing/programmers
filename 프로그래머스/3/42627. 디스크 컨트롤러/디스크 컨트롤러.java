import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        
        int[][] job = new int[jobs.length][3];//소요시간, 요청시각, 작업번호
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            else if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            else {
                return Integer.compare(a[2], b[2]);
            }
        });
        
        //cf: jobs {요청시각, 소요ㅕ시긴}
        //job : 소요시간, 요청시각, 작업번호
        for (int i = 0; i < jobs.length; i++) {
            job[i][0] = jobs[i][1]; //소요시간 작앞
            job[i][1] = jobs[i][0]; //요청시간 작앞
            job[i][2] = i; //작업번호 작앞
        }
        Arrays.sort(job, (a, b) -> Integer.compare(a[1], b[1]));
        
        
        //pq에서 작업하나씩 꺼내면서 반환시간을 구함.
        //반환시간 = 현재시간 - 요청시간
        //현재시간 = 이전작업 종료시간 + 빈시간 + 소요시간
        //빈시간 = 이전 종료시간 - 수행중작업 요청시간
        //current : {소 요 작번}
        int time = 0;
        int prev = 0;
        int sum = 0;
        int idx = 0;
        
        while (!pq.isEmpty() || idx < job.length) {
            //대기목록 추가 및 처리
            while (idx < job.length && job[idx][1] <= time) {
                pq.offer(job[idx]);
                idx++; //요청시간별로 정렬한 job배열을 idx로 추적
            }
            
            if (!pq.isEmpty()) {
                int[] current = pq.poll();
                time += current[0];
                sum = sum + time - current[1];
            }
            else {
                time = job[idx][1];
            }
            
        }
        
        
        return sum/jobs.length;
    }
}
/**
pq에 넣어놓고 순서대로 비교해서 앞에서 처리.. 그냥 계산만 하면 됨
*/