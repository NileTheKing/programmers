import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        Assignment[] assign = new Assignment[plans.length];
        for (int i = 0; i < plans.length; i++) {
            String[] p = plans[i];
            String name = p[0];
            String[] timeParts = p[1].split(":");
            int startAt = Integer.parseInt(timeParts[0]) * 60 + Integer.parseInt(timeParts[1]);
            int duration = Integer.parseInt(p[2]);
            assign[i] = new Assignment(name, startAt, duration);
        }
        Arrays.sort(assign, (o1, o2) -> o1.startAt - o2.startAt);
        int idx = 0; //다음작업
        int doneCnt = 0;
        int time = 0;
        ArrayDeque<Assignment> stack = new ArrayDeque<>();
        List<String> ans = new ArrayList<>();
        while (doneCnt < plans.length && idx < assign.length - 1) {
            Assignment current = assign[idx];
            Assignment next = assign[idx + 1];
            idx++;
            int timeUntilNext = next.startAt - current.startAt;
            // System.out.printf("")
            if (timeUntilNext >= current.duration) {//시간이 남는다.완료
                ans.add(current.name);
                timeUntilNext -= current.duration;
                while (!stack.isEmpty() && timeUntilNext >= 0) {
                    Assignment popped = stack.pop();
                    if (timeUntilNext - popped.duration >= 0) {
                        ans.add(popped.name);
                        timeUntilNext -= popped.duration;
                    }else {
                        popped.duration -= timeUntilNext;
                        timeUntilNext = -1;
                        stack.offerFirst(popped);
                    }
                }
            }else {//시간 안남으면 어쩔수없지
                current.duration -= timeUntilNext;
                stack.offerFirst(current);
            }
                
        }
        stack.offerFirst(assign[idx]);
        while (!stack.isEmpty()) {
            ans.add(stack.pollFirst().name);
        }
        return ans.toArray(new String[0]);

    }
    public class Assignment {
        String name;
        int startAt;
        int duration;
        Assignment (String name, int startAt, int duration) {
            this.name = name;
            this.startAt = startAt;
            this.duration = duration;
        }
    }
}
/**
과제목록이있따..시작되면 하고.. 시간!이 되면 새로운과제를한다
시간남으면 멈ㅊ춰준거 함..근데 최근에 멈춘거. 
그러면 스택이 필요할듯? 과제 진행상황은 카운터로 해야할거고..인덱스말고.
근데 순서대로 진행해야하긴하니까.. 정렬해서 다음과제 idx는 추적해야하함
일단 정렬해서 하긴해야함.. 시작순이 필요해서. 근데 string으로 주어졌으니까 ..근데 꺼냈을떄 시간봐야하니까 그냥 변환때려서 새로운 자료형으로 변환하는게 나을거같어
*/