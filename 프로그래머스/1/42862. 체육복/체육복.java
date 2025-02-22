import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int cnt = 0;
        HashSet<Integer> reserveSet = new HashSet<>();
        for (int r : reserve) {
            reserveSet.add(r);
        }
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        //잃어버리고 여분있던 애 처리
        List<Integer> realNo = new ArrayList<>();
        for (int l : lost) {
            if (reserveSet.contains(l)) {
                cnt++;
                reserveSet.remove(l);
            } else {
                realNo.add(l);
            }
        }
        
        //명단에 없는 애 처리
        for (int l : realNo) {
            if (reserveSet.contains(l - 1)) {
                cnt++;
                reserveSet.remove(l - 1);
            }
            else if (reserveSet.contains(l + 1)) {
                cnt++;
                reserveSet.remove(l + 1);
            }
        }
        return n - lost.length + cnt;
    }
}