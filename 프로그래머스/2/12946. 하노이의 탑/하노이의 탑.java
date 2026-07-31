import java.util.*;
class Solution {
    List<int[]> ans = new ArrayList<>();
    public int[][] solution(int n) {
        backtrack(n, 1, 3, 2);
        return ans.toArray(new int[0][]);
    }
    void backtrack(int n, int from, int to, int via) {
        if (n == 0) return;
        backtrack(n - 1, from, via, to);//일단 n- 1개 치움
        ans.add(new int[] {from, to});
        backtrack(n - 1, via, to, from);//n - 1개도 처리..다음으로
    }
}