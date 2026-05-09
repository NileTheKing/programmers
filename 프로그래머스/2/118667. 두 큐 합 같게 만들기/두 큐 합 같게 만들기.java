import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        int len = queue1.length;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < len; i++) {
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
        int cnt = 0;
        int limit = len * 3; //
        while (!(q1.isEmpty() && q2.isEmpty()) && sum1 != sum2 && cnt < limit) {
            if (sum1 > sum2) {
                int polled = q1.poll();
                sum1 -= polled;
                sum2 += polled;
                q2.offer(polled);
                cnt++;
            }else {
                int polled = q2.poll();
                sum2 -= polled;
                sum1 += polled;
                q1.offer(polled);
                cnt++;
            }
        }
        
        return cnt >= limit ? -1 : cnt;
    }
}
/**
300,000... 두개니까 뭐 n^2는 상상을 못함
nlogn이 최선일듯.수학적으로 가능하다면 n
보아하니 큐라서 수학적으로 할수있는게 없음
어찌됐든 어떤 선택을 해야하고 뒤에 숫자를 읽고 뭐 더 좋은 선택을 할 수 있나?
어찌됐든..뒤에있는거를 나중에 쓸거여도 지금 앞에있는거를 처리를 해야함.
좋든싫든 둘중하나를 골라서 처리해야함. 그다음에 또 상태보는거고...
합은 long쓰시고
*/