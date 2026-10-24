import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        //미리 목록
        Set<String> set = new HashSet<>();
        Map<String, Integer> current = new HashMap<>();
        for (String g : gems) set.add(g);
        
        int[] ans = new int[2];
        int min = Integer.MAX_VALUE; //길이로계산할것.
        //two pointer순회
        int l = 0;
        for (int r = 0; r < gems.length; r++) {
            current.put(gems[r], current.getOrDefault(gems[r], 0) + 1);
            //이미 동일하면 계쏙줄임.!동점시갱신안함
            // System.out.printf("at idx:%d, currentSize = %d\n", r, current.size());
            while (l <= r && current.size() == set.size()) {
                //먼저 갱신
                if (r - l + 1 < min) {
                    min = r - l + 1;//길이로
                    ans[0] = l + 1;
                    ans[1] = r + 1;
                }
                //왼쪽제거
                current.put(gems[l], current.get(gems[l]) - 1);//제거
                if (current.get(gems[l]) == 0) {//0개면제거
                    current.remove(gems[l]);
                }
                l++;
            }
        }
        return ans;
        
    }
}
/*배열 10^5.. nlogn까지가능할듯.근데 딱봐도 투포인터임
근데 매 go마다 순회하면?n^2됨. 고로 현재상태를 map으로기록

*/