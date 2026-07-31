import java.util.*;
class Solution {
    public double[] solution(int k, int[][] ranges) {
        //그래프완성
        List<Integer> graph = new ArrayList<>();
        while (k > 1) {
            graph.add(k);
            if (k % 2 == 0) {
                k /= 2;
            }else {
                k = k * 3  + 1;
            }
        }
        graph.add(1);
        //미리 넓이 구해두기
        double[] area = new double[graph.size() - 1];//area[0] -> 0부터1까지
        for (int i = 0; i < area.length; i++) {
            double rectangle = Math.min(graph.get(i), graph.get(i + 1)) * 1.0;
            double triangle = Math.abs(graph.get(i) - graph.get(i + 1)) / 2.0;
            area[i] = rectangle + triangle;
            // System.out.printf("area[%d] = %2f\n", i, area[i]);
        }
        
        //ranges범위 돌기
        int n = graph.size() - 1;
        double[] ans = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int[] r = ranges[i];
            int start = r[0];
            int end = r[1] >= 0 ? n - r[1] : n + r[1];
            if (end < start) {ans[i] = -1; continue;}
            if (end == start) {ans[i] = 0; continue;}
            // System.out.printf("start %d end %d\n", start, end);
            double sum = 0;
            for (int j = start; j < end; j++) {
                sum += area[j];
            }
            ans[i] = sum;
        }

        return ans;
    }
}
/**
우박수열을 구하기. 그러고나서 뭐 범위별로 구하면 그만인데 시키는대로

k == 10000
range 10000
흠....이걸 매번 새로? 아니면 메모해놓은
그냥 2차원 dp아님?
01 
*/
