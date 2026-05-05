import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
    
        Task[] task = new Task[plans.length];
        for (int i = 0; i < plans.length; i++) task[i] = new Task(plans[i][0], timize(plans[i][1]), timize(plans[i][2]));
        Arrays.sort(task, (o1,o2) -> o1.start - o2.start);//시간작은순
        
        List<String> ans = new ArrayList<>();
        Stack<Task> stack = new Stack<>();
        for (int i = 0; i < task.length - 1; i++) {
            Task currentTask = task[i];
            Task nextTask = task[i + 1];
            int timeLeftUntilNext = nextTask.start - (currentTask.start + currentTask.playtime);
            //시간 남으면 마무리하고 남으시간동안 다른거
            if (timeLeftUntilNext >= 0) {
                ans.add(currentTask.name);
                while (!stack.isEmpty() && timeLeftUntilNext >= 0) {
                    Task popped = stack.pop();
                    timeLeftUntilNext -= popped.playtime;
                    if (timeLeftUntilNext >= 0) {
                        ans.add(popped.name);
                    }else {
                        stack.push(new Task(popped.name, popped.start, -timeLeftUntilNext));
                    }
                }
            }else { //시간안남음.. 잘라야함
                stack.push(new Task(currentTask.name, currentTask.start, -timeLeftUntilNext));
            }
        }
        ans.add(task[task.length - 1].name);
        while (!stack.isEmpty()) {
            ans.add(stack.pop().name);
        }
        return ans.toArray(new String[0]);
    }
    public int timize(String str) {
        String[] parts = str.split(":");
        if(parts.length == 1) return Integer.parseInt(parts[0]);
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
    class Task {
        String name;
        int start;
        int playtime;
        Task(String name, int start ,int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
}