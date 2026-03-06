import java.util.*;
class Solution {
    List<List<Integer>> buckets = new ArrayList<>();
    int min = Integer.MAX_VALUE;
    public int solution(int k, int n, int[][] reqs) {
        
        getBuckets(k,n, new ArrayList<>());
        for (List<Integer> b : buckets) {
            calculate(b, reqs);
        }
        
        return min;
    }
    public void calculate(List<Integer> changoo, int[][] reqs) {
        
        //request는 요청시각을 기준으로 오름ㅈ차순.
        //ㅅ뮬레이션 구현
        //비어있으면 처리 아니면 대기...인데 이제 각 창구별로 대기시간 더하면 됨
        int total = 0;
        for (int i = 0; i < changoo.size(); i++) {//창구..
            int mentors = changoo.get(i);
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int j = 0; j < mentors; j++) pq.offer(0);
            int time = 0;
            for (int[] r : reqs ) {
                if (r[2] != i + 1) continue;//창구손님아님
                
                int arrived = r[0];
                
                int polled = pq.poll();
                
                if (arrived < polled) {
                    time += polled - arrived;
                    pq.offer(polled + r[1]);
                }else {
                    pq.offer(r[0] + r[1]);
                }
                
            }
            total += time;
            
            
            //큐로 만들어가지고 시뮬레이션 돌리기
        }
        min = Math.min(total, min);
    }
    
    public void getBuckets(int k, int n, List<Integer> current) {//k가 창구 n은 남은인원
        
        if (k  == 1) {
            current.add(n);
            buckets.add(new ArrayList<>(current));
            current.remove(current.size() - 1);
            return;
        }
        //int leftChangoos = k - current.size();
        for (int i = 1; i <= n - (k -1) ; i++) {
            current.add(i);
            getBuckets(k - 1, n - i, current);
            current.remove(current.size() - 1);
        }
    }
}
/**
떠오르는 개념들은 이분탐색이랑 완탐임

유형 k 5
멘토 n 20
req길이 300(사람수)

그러면.. 20명을 5개의 유형으로 나눈다쳐
그러면 이제 20C5가 되지?
20C5는.. 어케계산하드랄 ㅋㅋ 20 19 18 17 16 한다음에 순서없응께 5!로 나누는건가?
20C5는 얼마여 4 19 6 17 2 76 6 420  한 35000? ez
어니 근데 20을 5개 바구니로 나누는거면 . 뭐냐 이게 

그러면 ㅋ 각 배정마다 시뮬레이션 돌려가지고 최소인거 하면 되겠는디

지금 문제가.
그 뭐시기....
창구가 특정시간에 곽차있음..
이거 대기시간 구하는거는.. 음 일단 이거는 큐임 큐
선입선출이고.. 
/  
*/