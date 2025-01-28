import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        List<Integer> answer = new ArrayList<>();
        int[] days = new int[progresses.length];
        
        for (int i = 0; i < days.length; i++) {
            //days[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
            days[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
        }
        
        int max = days[0];
        int count = 1;
        for (int i = 1; i < days.length; i++) {
            //현재 확인하는 작업의 남은 일수가 기존의 것보다 크다면
            // 7 3 9에서 기존거는 
            if (days[i] <= max) {
                count++;
            }
            else {
                max = days[i]; //최댓값 갱신
                answer.add(count);//답추가
                count = 1;//카운트 초기화
            }
        }
        answer.add(count);
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}