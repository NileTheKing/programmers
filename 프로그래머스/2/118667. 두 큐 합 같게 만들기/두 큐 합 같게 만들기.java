import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int q : queue1) {
            q1.offer(q);
            sum1 += q;
        }
        for (int q : queue2) {
            q2.offer(q);
            sum2 += q;
        }
        
        long target = (sum1 + sum2) / 2;
        
        int cnt = 0;
        int max = (queue1.length + queue2.length) * 2;
        while ((!q1.isEmpty() || !q2.isEmpty()) && cnt < max) {
            
            if (sum1 == sum2) break;
            
            else if (sum1 > sum2) {
                int polled = q1.poll();
                sum1 -= polled;
                
                q2.offer(polled);
                sum2 += polled;
                
                cnt++;
            }else {
                int polled = q2.poll();
                sum2 -= polled;
                
                q1.offer(polled);
                sum1 += polled;
                
                cnt++;
            }
        }
        
        return cnt == max ? -1 : cnt;   
    }
}
/**
n = 3*10^5, m = 3 * 10 ^ 5
N*M = 9*10^10. <<XXXXX

큐가 두개가있다..다 더해보고 일단 각각을 뭘로 맞출지 알면되지
각각몇이 추가로 필요한지 알 수 있고 ㅎ므..근데 큐라서 멋대로 꺼낼 수가 없다.
그렇다면...완탐인가 흠.큐를 안쓰고 배열 그대로 완탐을 한다면 .. 불가능. 꺼내고 싶다고 꺼낼 수 있는 것도 아님

1: 3 2 7 2 -> 14
2: 4 6 5 1 -> 16

일단 둘중 하나는 뭔가해야함. 
4를 일단 버려 그다음에 이제 1이 다이어트
끈]ㅌ
그럼 다른 경우를 봐야지
=====
1 2 1 2 -> 6
1 10 1 2 -> 14
:차각각 10 10으로 만들어야함. 큐1은 4필요하고 큐2는 4가 덜필요함.
일단 둘다 똑같고 2가 다이어트해야하니까 
1 2 1 2 1 -> 7
10 1 2  -> 13 [한번]
여기서 2는 다이어트해야하하고 1은 더 필요해
1 2 1 2 1 10 -> 17
1 2 -> 3 [두번]
여기서 똑같고 1은 다이어트 2는 벌크업

2 1 2 1 10 -> 16
1 2 1 -> 4 [세번]

1 2 1 10 -> 14
1 2 1 2 -> 6 [네번]

2 1 10 -> 13
1 2 1 2 1 -> 7 [다섯]

1 10 -> 11
1 2 1 2 1 2 -> 9 [여섯]

10 -> 1 2 1 2 1 2 1 

근데 이게 해결법이 맞나? 그리디..
음 근데 이문제 완탐은 아니고 .. 그리디 dp 아니면 알고리즘인데
알고리즘은 아니넉같은게 일단 수학적으로 뭔가를 하는게 아니고..
최소를 구하는건데.. 다른 큐라는거 떄문에 다른건 제한되고 큐바꼐안됨
근데 큐가지고 할 수 있는게... 음... 우선순위큐도 아니고 뭐 그냥 시뮬레이션아님/

*/