import java.util.*;
class Solution {
    List<List<Integer>> combs = new ArrayList<>();
    public int solution(int n, int[][] q, int[] ans) {
        getCombs(1, n, new ArrayList<>());
        //조합 디버깅
        // for (List<Integer> c : combs) {
        //     System.out.printf("output comb\n");
        //     for (int i : c) {
        //         System.out.printf("%d ", i);
        //     }
        // }
        
        int cnt = 0;
        for (List<Integer> c : combs) {
            boolean able = true;
            for (int i = 0; i < q.length; i++) {
                int match = findMatch(c, q[i]);
                if (match != ans[i]) {
                    able = false;    
                    break;
                }
            }
            //다 통과하면 카운트
            if (able) cnt++;
        }
        return cnt;
    }
    public int findMatch(List<Integer> o1, int[] o2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        
        for (int i : o1) {
            set1.add(i);
        }
        for (int i  : o2) {
            set2.add(i);
        }
        
        int cnt = 0;
        for (int num1 : set1) {
            if (set2.contains(num1)) cnt++;
        }
        return cnt;
        
    }
    public void getCombs(int idx, int n, List<Integer> current) {
        if (current.size() == 5) {
            combs.add(new ArrayList<>(current));
        }
        
        for (int i = idx; i <= n; i++) {
            current.add(i);
            getCombs(i + 1, n, current);
            current.remove(current.size() - 1);
        }
    }
    
}
/**
비밀코드는 5개의 숫자
q에 대입해서 통과도미녀 ok
다통과하면 답 갯수에 ㅜㅊ가

n은 30. 그러몀ㄴ 이제 30개중에 5개 고른게 답후보
q(시도횟수) 는 10개. 10개의 테스트
ans는 q에 해당하는 정답개수

**/