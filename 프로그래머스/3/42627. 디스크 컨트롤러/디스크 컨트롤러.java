import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int[][] newJobs = new int[jobs.length][3];//번호 시점 시간
        PriorityQueue<int[]> pq = new PriorityQueue<>( //번호, 요청시점, 소요시간
            (o1, o2) -> {
                if (o1[2] == o2[2]) {
                    if (o1[1] == o2[1]) return o1[0] - o2[0];
                    else return o1[1] - o2[1];
                }else {
                    return o1[2] - o2[2];
                }
            }
        );
        for (int i = 0;i < jobs.length; i++) {
            newJobs[i][0] = i;
            newJobs[i][1] = jobs[i][0];
            newJobs[i][2] = jobs[i][1];
        }
        Arrays.sort(newJobs, (a,b) -> a[1] - b[1]);//시점기준 오름차순정렬
        
        int totalWait = 0;
        int done = 0;
        int idx = 0;
        int targetDone = jobs.length;
        int time = 0;
        while (done < targetDone) {
            while (idx < jobs.length && newJobs[idx][1] <= time) {
                pq.offer(newJobs[idx++]);
            }
            
            //다음으로 이동... 큐가비엉있으면 다음작업으로 시간이동
            if (pq.isEmpty()) {
                //비어있으면 다음놈으로 시간이동
                time = newJobs[idx][1];
            }else { //차있으면 작업처리..: poll, 시간, 계산
                int[] polled = pq.poll();//
                done++;
                time += polled[2];// 시간은 이미 처리되어있으니까 소요시간
                totalWait += (time - polled[1]);
            }
        
        }
        return totalWait / jobs.length;
        
    }
}
        //내가  하고싶은것
        //q돌면서 시간시뮬레이션 및 다음 작업 찾으면서 ..idx는 다음작업용이었던거 같다.
        //정렬은 필요없다치고.. 정렬안하면 그냥 매번 풀스캔하면되잖어. 일단 해야하나?
        //시간복잡도 jobs500이잖어.
        //힙자체가 nlogn이고 매번 풀스캔하면 n이니까 n^2logn인데..
        //n이 500이니까 충분
        