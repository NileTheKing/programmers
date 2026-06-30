import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        double[] failRate = new double[N + 1 + 1]; //stageN까지.
        //먼저 stages에서 각 스테이지정보에 적어야함
        int[] count = new int[N + 1 + 1];
        for (int s : stages) count[s]++;
        //이제 stage별로 순회
        int total = stages.length;
        for (int i = 1; i <= N; i++) {
            if (total == 0) break;
            failRate[i] = (double) count[i] / total;
            total -= count[i];
        }
        // for (double f : failRate) System.out.println(f);
        //이제 failRate[i]의 크기를 기준으로 i를 출력하면된다..
        //이게무슨말이냐 하면 failRate의 값을기준으로 그 인덱스로 정렬하는건데..
        //연결지점이 필요한거고..이거를 어케하냐 이거지. 아 이거는 음 클래스로하든가
        //아니면 뭐 Integer정렬이런게 있던거같은데..
        Integer[] ans = new Integer[N];
        for (int i = 0; i <  N; i++) ans[i] = i + 1;
        Arrays.sort(ans, (o1, o2) -> {
            if (Double.compare(failRate[o2], failRate[o1]) == 0) return o1 - o2;
            return Double.compare(failRate[o2], failRate[o1]);
        });
        
        return Arrays.stream(ans).mapToInt(i->i).toArray();
    }
}
/**
실패율에 따라서 스테이지번호를 리턴
스테이지갯수 500
유저 200,000명.(stages는 유저가 도전중인 스테이지번호) stages[i] 1-N+1
결국 실패율이 담긴 배열이 있어야하고 그거 정렬하면된다.연결지점이있을테고


*/