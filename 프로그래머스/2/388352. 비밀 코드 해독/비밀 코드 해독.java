import java.util.*;
class Solution {
    List<List<Integer>> allCombs = new ArrayList<>();
    public int solution(int n, int[][] q, int[] ans) {
        
        combination(n, 1, new ArrayList<>());
        //System.out.printf("allcombs size : %d\n", allCombs.size());
        int res = 0;
        for (List<Integer> comb : allCombs) {//각 조합에대해 q되는지
            //System.out.printf("debug 347910\n");
            //for (int c  :comb) System.out.printf("%d ", c);
            // System.out.println();
            boolean flag = true;
            for (int i = 0; i < q.length; i++) {
                int match = ans[i];//q[i]랑 comb비교헤ㅐㅆ을때 match만큼 동일해야함
                int cnt = compare(comb, q[i]);
                //System.out.printf("match %d, cnt %d\n", match, cnt);
                if (match == cnt) continue;
                else {
                    // System.out.printf("comb: ");
                    // for (int c : comb) System.out.printf("%d ", c);
                    // System.out.printf("and \n q: ");
                    // for (int c : q[i]) System.out.printf("%d ", c);
                    // System.out.printf("is diffent match, cnt : %d %d\n", match, cnt);
                    flag = false;
                    break;
                }
            }
            //가능
            // System.out.printf("possible ");
            // for (int c : comb) System.out.printf("%d ",c);
            // System.out.println();
            if (flag) res++;
        }
        return res;
    }
    public void combination(int n, int idx, List<Integer> current) {
        if (current.size() == 5) {
            //System.out.printf("combination added debug\n");
            //for (int c : current) System.out.printf("%d ", c);
            //System.out.println();
            allCombs.add(new ArrayList<>(current));
        }
        
        for (int i = idx; i <= n; i++) { //1부터 30까지
            current.add(i);
            combination(n, i + 1, current);
            current.remove(current.size() - 1);
        }
        return;
    }
    public int compare(List<Integer> list, int[] array) {
        int length = 5;
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (list.get(i) == array[j]) {
                    cnt++;
                    break;
                }   
            }
        }
        return cnt;
    }
}
/**
모든 조합에 대해서 q가 가능한지 보면 되는 것.
모든 조합얻는법? -> 백트래킹..백트래킹?음 잘모르니까 헷갈리네 함 해보자
백트래킹으로 구현하려면? current에 추가하고 취소하고 이런거잖음.그러면 음
아 그러면 리턴값을 애초에 모든거로 ㅋ


*/