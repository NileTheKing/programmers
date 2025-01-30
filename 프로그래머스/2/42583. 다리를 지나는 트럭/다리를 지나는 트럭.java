import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int currentWeight = 0;
        
        Queue<int[]> q1 = new LinkedList<>(); //다리에 있는 트럭. {무게, 남은길이}
        Queue<Integer> q2 = new LinkedList<>(); //대기중인 트럭
        int end = 0;
        //대기중인 트럭들
        for (int i = 0; i < truck_weights.length; i++) {
            q2.offer(truck_weights[i]);
        }
        
        //대기중인 트럭이 없을 때 까지
        while (!(q2.isEmpty() && end == truck_weights.length)) {
            answer++; //시간흐름
            
            //다리위 자동차들 처리. 무게,거리 처리
            int size = q1.size();
            for (int i = 0; i < size; i++) {
                int[] current = q1.poll();//무게, 남은거리
                current[1] -= 1;
                if(current[1] <= 0) {//다 지나갔으면 뺌
                    currentWeight -= current[0];
                    end++;
                }
                else {//안지나갔다면 다시 추가
                    q1.offer(current);
                }
            }
            //다리를 건너는 대수가 제한보다 작고 무게를 더해봤을 때 들어올 수 있다면
             if (!q2.isEmpty() && currentWeight + q2.peek() <= weight && q1.size                    () < bridge_length) {
                int truckWeight = q2.poll();
                q1.offer(new int[] {truckWeight, bridge_length});
                currentWeight += truckWeight;
            }
        }
        
        
        return answer;
    }

}
/**
일단 큐나 이런거를 만들어서 사이즈를 정해두고.. 무게를 재야함.
순서대로 지나가니까 쉬울듯? 사이즈만 관리하면 되잖아.
*/