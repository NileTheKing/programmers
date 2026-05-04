import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int cnt = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int time = 0; //현재시각
        //서버는 시간단위로 돌다가 꺼지고.. i시에 없으면 증설해야해..
        for (int i = 0; i < players.length; i++) {// i [0,23]
            int totalPlayer = players[i];
            //시간 다된 서버들은 꺼져야함
            while (!pq.isEmpty() && pq.peek() <= i) pq.poll();
            int currentServers = pq.size(); //현재돌아가는 서버수..증설전
            int moreServer = totalPlayer / m - currentServers;
            if (moreServer <= 0) {
                //서버가 필요없음..패스로직인데 뭐바꿔야하지 시간?시간관리해야함?i가있는디
            }
            //필요한 서버만큼 추가증설
            for (int j = 0; j < moreServer; j++) {
                pq.offer(i + k);//종료시각명시
                cnt++;
            }
        }
        
        return cnt;
    }
}
/**
시뮬레이션?
일단 m이있을떄 플레이어수를 m으로 나눈 몫만큼의 서버가 "필요"하다
필요라는 것은 현재 있는 서버수 + 추가할 서버수.

그러면 음.. 시뮬레이션돌리면서 카운트할 각인데..
24시간돌아가고 조건보니까 뭐 시뮬문제맞는듯. 그냥 뭐 복잡한 조건은 없어보여

그러면 시간대로 돌아가면서..알야야할거는 돌아가고있는 서버의 수..그리고 음..시간추적
그리고 음..종료되는 시간을 알아야 현재 서버수 추적가능.. 필요한건 쉽고.

while(시간<종료) {
    총필요한 서버수
    현재 돌아가는 서버수(시간기준)
    추가서버수 cnt.
    추가서버 >> 자료구조인데.. 보통 음 이런 작업문제는 큐긴해 ㅋㅋ 여기선
    종료시간있으니까 pq하면 크기로 쉽게 추적가능..
}
10분마...
*/