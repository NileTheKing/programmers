import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        Task[] enhancedPlans = new Task[plans.length];
        for (int i = 0; i < plans.length; i++) {
            String[] plan = plans[i];
            String subject = plan[0];
            String[] timeParts = plan[1].split(":");
            int time = Integer.parseInt(timeParts[0]) * 60 + Integer.parseInt(timeParts[1]);
            int duration = Integer.parseInt(plan[2]);
            
            enhancedPlans[i] = new Task(subject, time, duration);
        }
        Arrays.sort(enhancedPlans, (o1, o2) -> o1.time - o2.time);
        
        ArrayDeque<Task> stack = new ArrayDeque<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < enhancedPlans.length - 1; i++) {
            //지금 작업 i인덱스거 하고 시간..체크
            Task current = enhancedPlans[i];
            stack.offerFirst(current);
            Task next = enhancedPlans[i + 1];
            int timeLeft = next.time - current.time;//다음 실행까지 남은시간
            // System.out.printf("===current: %s, next:%s, timeLeft : %d===\n", current.subject, next.subject, timeLeft);
            
            while (!stack.isEmpty() && timeLeft > 0) {//시간이 조금이라도 남았으면 조금이라도 하고 스택에넣음
                Task popped = stack.pollFirst();//이번에 수행할작업
                int gap = timeLeft - popped.duration;// 남은시간 - 소요시간..다하고 몇분남냐이거임 +이면 남는거구 -이면 부족한거..그러면 부족한거만큼뺴고 가능.예를들어 5만큼부족하면 5ㄴ남는거
                // System.out.printf("popped %s, gap: %d\n", popped.subject, gap);
                if (gap >= 0) { //다함
                    //pop유지..남은시간갱신
                    // System.out.printf("done %s\n",popped.subject);
                    ans.add(popped.subject);
                    timeLeft -= popped.duration;
                    // System.out.printf("timeLeft %d\n",timeLeft);
                }else {// 시간안에 다 못함..그러니까 시간0으로만들고 다시넣기
                    popped.duration = -gap;
                    // System.out.printf("done a bit..%s duration now..%d\n", popped.subject, popped.duration);
                    stack.offerFirst(popped);
                    timeLeft = 0;
                }
            }
        }
        //이제 마지막거 처리할 시간..얘 스택에 걍 넣고 뽑으면됨
        stack.offerFirst(enhancedPlans[enhancedPlans.length - 1]);
        while (!stack.isEmpty()) ans.add(stack.pollFirst().subject);
        return ans.toArray(new String[0]);
    }
    class Task {
        String subject;
        int time;
        int duration;
        Task (String subject, int time, int duration) {
            this.subject = subject;
            this.time = time;
            this.duration = duration;
        }
    }
}
/**
plans가 있는데 시간순으로 함. 그래서 정렬해둬야함. 자료형 정의?(커스텀화?) 음 굳이 필요안할수됬고
근데 해두면 편하긴하지?
idx끝일떄까지 하는거

그리고 끝나고나서 마무리

하던작업은 스택.
*/