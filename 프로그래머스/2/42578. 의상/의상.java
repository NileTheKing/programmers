import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String[] item : clothes) {
            map.put(item[1], map.getOrDefault(item[1], 0) + 1);//카테고리와 갯수 저장
        }
        
        int ans = 1;
        for (int cnt : map.values()) {
            ans *= (cnt + 1);
        }
        
        return ans - 1; //안입는경우
    }
}