import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        double[] rate = new double[N + 1 + 1];//N스테이지라N+1, N+1도있으니+1
        long[] stageCnt = new long[N + 1 + 1];//스테이지별로 멈춘유저숫자들있음
        for (int s : stages) {
            stageCnt[s]++;
        }
        int users = stages.length;//전체유저수
        for (int i = 1; i <= N; i++) {
            if (users == 0) break;
            rate[i] = (double)stageCnt[i] / (double)users;
            users -= stageCnt[i];
        }
        //1번스테이지부터 N번스테이지까지 rate에맞게 스테이지번호필요..매핑
        Integer[] indices = new Integer[N];
        for (int i = 0; i < N; i++) indices[i] = i + 1;
        Arrays.sort(indices, (o1, o2) -> {
            return Double.compare(rate[o2], rate[o1]);
        });
        return Arrays.stream(indices).mapToInt(i->i).toArray();
    }
}