import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        double[] stageInfo = new double[N + 1];
        int tmp = stages.length;
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int s : stages) { //i인애 카운트하는거임
                if (s == i) cnt++;
            }
            // System.out.printf("stage %d, cnt %d, total %d\n", i, cnt, tmp);
            if (tmp == 0) break;
            stageInfo[i] = (double)cnt / tmp; //해당스테이지에서 멈춘 사람수 찾기가능
            // System.out.printf("stage%d %2f\n", i, stageInfo[i]);
            tmp -= cnt;
        }
        //전체분모 n인데 점점 줄고가고.. 오케이 그러면 만들수있음 실패율을.
        //근데 어떻게 이거랑 stage랑 연결하냐 이거지.. 정렬할건데..Integer가지고했던게 기억이나는데 왜 Integer은 되고..
        Integer[] ans = new Integer[N];
        for (int i = 0; i < N; i++) ans[i] = i + 1;
        Arrays.sort(ans, (o1, o2) -> {
            if (Double.compare(stageInfo[o2], stageInfo[o1]) == 0) {
                return o1 - o2;
            }
            else return Double.compare(stageInfo[o2], stageInfo[o1]);
        });
        return Arrays.stream(ans).mapToInt(i->i).toArray();
    }
}
/**
이거를 브루트포스로 하려면
stages를 순회를 하면서 스테이지배열에 이제 기입을 하는거지
근데 이거를 매 스테이지마다 한다치면 500 * 200,000니까 100 000 000 어딱인디
근데 수학적으로 단축도 될거같은데
예를 들면 음..N까지 배열(스테이지정보) 만들어놓고
1스테이지까지 간 애들 ..2스테이지 카운트 하고나면 되는거잖음
*/