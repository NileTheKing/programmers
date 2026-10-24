import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int cnt = 0;//증설시추가
        PriorityQueue<Integer> pq = new PriorityQueue<>();//종료시각
        //초기서버0대
        for (int i = 0; i < players.length; i++) {
            //시각: i ~ i + 1
            //서버종료
            while (!pq.isEmpty() && pq.peek() <= i) {
                pq.poll();
            }
            int currentUsers = players[i];//현재유저
            int currentServers = pq.size(); //현재서버수
            int extraServersNeeded = currentUsers / m; //전체 필요한 서버수
            
            //서버가부족하다면 증설
            while (pq.size() < extraServersNeeded) {
                pq.offer(i + k);
                cnt++;
            }
        }
        return cnt;
    }
}