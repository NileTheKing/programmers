import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();//최소힙. 오름차순
        int running = 0;
        int cnt = 0;
        for (int i = 0; i < 24; i++) {
            int currentPlayer = players[i];
            int neededServer = currentPlayer / m;
            while (!pq.isEmpty() && pq.peek() <= i) { //돌아가는 애들 중가장 빨리 끝나는 서버가 현재 시각보다 작다면 일단 서버 다 뺴
                pq.poll();
            }
            running = pq.size();// pq에는 돌아가는 서버들. 갯수구하기
            int diff = neededServer - running; //더 띄울 서버들
            for (int j = 0; j < diff; j++) {
                pq.offer(i+k);
                cnt++;
            }
        }
        
        return cnt;
    }
}
/**
서버 돌아가는 수
현재 시간..
현재 돌아가는 서버 정보를 들고다닐까? 그래서 업데이트를 하는거지.. 몇시에 몇개있고
시간되면 또 계산 다시하고? 흠

매번 뭐 해당시각에 서버 몇대돌아가는지 계산해도 문제없긴하지?
시간24개 사람1000명최대
k는24시? 
서버 뭐 만힝해봤자 1000개 열어놓을테니까 
널널하노 ㅋ

그러면 새로 추가한 모든 서버정보를 이제 추가를하는거여
그리고 매 시간마다 돌아가는애들 몇대있는지 계산.. ㄱㄱ
서버 정보는 열린시각 종료시각 하면 되는거잖아 . 정보 담고있다가 매 시간마다 시간보고 지낫으면 지우는거지 ㅋㅋ
자료구조는 뭘 쓰면 좋을까? 지우기 좋은 list? 정렬이 되는 pq?pq 갠찮은데? 종료시각 일찍 인거부터 큐로 세워두고 가장 일찍 긑나는게 지금시각보다 클때까지 다 꺼내버려
*/