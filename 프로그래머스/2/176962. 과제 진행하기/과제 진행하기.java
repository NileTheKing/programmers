import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        int len = plans.length;
        Task[] tasks = new Task[len];
        for (int i = 0; i < len; i++) {
            String[] p = plans[i];
            String subject = p[0];
            int time = getTime(p[1]);
            int duration = Integer.parseInt(p[2]);
            // System.out.printf("(%s %d %d)\n",subject, time, duration);
            tasks[i] = new Task(subject, time, duration);
        }
        Arrays.sort(tasks, (o1, o2) -> {
            return o1.time - o2.time;
        });
        // System.out.println(tasks);
        int idx = 0;
        ArrayDeque<Task> stk = new ArrayDeque<>();
        List<String> ans = new ArrayList<>();
        while (idx < len - 1) {
            //맨앞에있는 idx에해당하는 작업 꺼내서 수행하고 시간남으면 작업하기 없으면안하기
            Task current = tasks[idx];
            Task next = tasks[idx + 1];
            //작업하고 시간남으면 처리 없으면안하기인데..
            //스택에 넣어서 단순화가능?지금 current를넣고... next시간되기전까지 하는거잖어
            int currentTime = current.time;
            stk.offerFirst(current);
            int deadline = next.time;
            // System.out.printf("index:%d, subject: %s, current time : %d, nextTime: %d\n", idx, current.subject, currentTime, deadline);
            while (!stk.isEmpty() && currentTime < deadline) {//시간이 안됐으면
                Task popped = stk.pollFirst(); //뽑아서
                int timeGap = deadline - currentTime; //얼마나할수있는지게ㅖ산
                if (timeGap <= 0) break;
                // System.out.printf("%s left: %d, timeGap: %d\n", popped.subject, popped.duration, timeGap);
                if (popped.duration <= timeGap) {//다할수있으면
                    // System.out.printf("%s done\n", popped.subject);
                    ans.add(popped.subject);//완료
                    currentTime += popped.duration;//pop은이미했고 시간반영
                }else { //다못하면 조금이라도 시간줄여서 한다.
                    currentTime += popped.duration;
                    popped.duration -= timeGap;
                    stk.offerFirst(popped);
                }
            }
            idx++;
        }
        ans.add(tasks[len - 1].subject);
        while (!stk.isEmpty()) {
            ans.add(stk.pop().subject);
        }
        return ans.toArray(new String[0]);
        
    }
    public class Task {
        String subject;
        int time;
        int duration;
        Task (String subject, int time, int duration) {
            this.subject = subject;
            this.time = time;
            this.duration = duration;
        }
    }
    public int getTime(String str) {
        String[] parts = str.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
/**
과제가있다..[이름 시작시간 소요시간]

과제를 한다. 시작하기로 한 시간이 되면 바로.
다음과제까지 시간남으면 멈춰둔 과제를 이어서한다. 마지막에 멈춘 것부터.

1.정렬필요
2. 스택필요

1.정렬된거로부터 idx로 추적
2. 해가면서 시간남으면 작업하고 스택넣고. 스택뺴고.
*/