import java.util.*;
class Solution {
    List<int[]> ans;
    public int[][] solution(int n) {
        ans = new ArrayList<>();
        hanoi(n, 1, 3, 2);
        return ans.toArray(new int[0][]);
    }
    void hanoi(int n, int from, int to, int via) {
        if (n == 0) return;
        //n-1개를 via로
        hanoi(n - 1, from, via, to); //n-1개를 이동..경유지로
        ans.add(new int[] {from, to});//이제 1개남았으니.처리.
        hanoi(n - 1, via, to, from);//결국 이거도 이동해야함
    }
}
/**
ABC기둥 존재.
가장큰 판 n -> C가야함
    n-1개는 B로 이동해야함
    n -> C로 이동
    n-1는 C로 이동해야함

처리완료 후 가장 큰 판 n-1 -> C가야함
    n - 2개는 ~
    반복..
*/