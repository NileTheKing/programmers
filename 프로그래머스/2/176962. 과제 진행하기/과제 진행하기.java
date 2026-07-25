import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        ArrayDeque<Task> stack = new ArrayDeque<>();
        Task[] tasks = new Task[plans.length];
        for (int i = 0; i < plans.length; i++) {
            String[] plan = plans[i];
            String name = plan[0];
            String time = plan[1];
            String left = plan[2];
            Task transformed = new Task(name, timize(time), timize(left));
            
            tasks[i] = transformed;
            
        }
        Arrays.sort(tasks, (o1,o2) -> o1.start - o2.start);
        //시작순대로 처리하면서, 다음시간오기 전까지 작업. 다하면 스택에서꺼내고, 못한거 다시넣기.
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < tasks.length - 1; i++) {
            // System.out.printf("===== i : %d=====\n", i);
            //다음작업전에 스택에 있는작업들을 위에서부터 최대한 처리하기.
            //초기상태를 넣어줘야하나 아님녀 똑같이 들어가도되나.
            int gotTime = tasks[i+1].start - tasks[i].start;
            stack.offerFirst(tasks[i]);
            while(!stack.isEmpty()) {
                if (gotTime == 0) break; //할거없음
                Task polled = stack.pollFirst();
                int tmp = polled.left;
                // System.out.printf("polled: (%s %d %d)\n", polled.name, polled.start, polled.left);
                polled.left -= gotTime;
                // System.out.printf("after substraction: %d\n", polled.left);
                //근데직관적으로 위에서0방어했으니까 0애서딱 끊어줘야함
                //예를들어 gottimed이 23이면..23만크
                if (polled.left > 0) {//남은시간 다썼는데 완료못함..
                    stack.offerFirst(new Task(polled.name, polled.start, polled.left));
                    gotTime = 0;
                }else { //처리 삽가능.. 0미만임.. 10분남은걸 23분이처리? 그러면 10분뺴
                    //10분남은걸 11분이처리? 1분남고.. 2분남은걸 1분이처리? 못하니까
                    //위에서 1분만큼 처리한거task로다시넣고 gottime0됨
                    gotTime -= tmp;
                    ans.add(polled.name);
                }
            }
        }
        //마무리
        ans.add(tasks[tasks.length - 1].name);
        //stack털기
        while (!stack.isEmpty()) ans.add(stack.pollFirst().name);
        return ans.toArray(new String[0]);
    }
    int timize(String str) {
        String[] parts = str.split(":");
        if (parts.length == 1) return Integer.parseInt(parts[0]);
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
    class Task {
        String name;
        int start;
        int left;
        Task(String n, int s, int l) {
            this.name = n;
            this.start = s;
            this.left = l;
        }
    }
}