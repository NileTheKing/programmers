import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        Map<String, Integer> moneyEarned = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        int len = enroll.length;
        for (int i = 0; i < len; i++) parent.put(enroll[i], referral[i]);
        // System.out.println(parent);
        for (int i = 0; i < seller.length; i++) {
            // System.out.printf("==== idx : %d====\n", i);
            //계산
            int total = amount[i] * 100;
            String sold = seller[i];
            while (total >= 1) {
                if (sold.equals("-")) break;
                int share = total * 1 / 10;
                int mine = total - share;
                if (share < 1) {
                    moneyEarned.put(sold, moneyEarned.getOrDefault(sold, 0) + total);
                    break;
                }
                moneyEarned.put(sold, moneyEarned.getOrDefault(sold, 0) + mine);
                // System.out.printf("seller parent total share mine:%s %s %d %d %d\n", sold, parent.get(sold), total, share, mine);
                total = share;
                sold = parent.get(sold);
            }
        }
        int[] ans = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            if (moneyEarned.get(enroll[i]) == null) continue;
            ans[i] = moneyEarned.get(enroll[i]);
        }
        return ans;
        
    }
}
/**
노드 10000개
간선 10000개

금액계산해서 enroll순서대로 수익금나열

절삭
돈이 타고올라가는데 언제까지? 10%한게 1보다 작으면 거기까지
*/