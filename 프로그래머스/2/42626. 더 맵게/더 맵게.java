import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        
        //pq에 다넣기
        for (int a : scoville) {
            pq.offer(a);
        }
        
        //꺼내면서 작업
        while (true) {
            if (pq.peek() >= K) {
                break;
            }
            else {
                if(pq.size() >= 2) {
                    int a = pq.poll();
                    int b = pq.poll();
                    int c = a + 2 * b;
                    pq.offer(c);
                    answer++;
                }
                else {
                    return -1;
                }
                
            }
        }
        
        return answer;
    }
}
/**
1 2 3 9 10 12 / 7
음.. 
최소힙으로 가장 작은거 꺼내서 합치고 그거를 또 힙에 넣고 한무반복
두개 한번에 ㅓㄲ내면 되지 ㅋㅋㅋ
*/