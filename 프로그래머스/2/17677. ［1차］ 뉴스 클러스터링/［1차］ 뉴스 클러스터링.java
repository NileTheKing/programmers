import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        
        for (int i = 0; i < str1.length() - 1; i++) {
            String twoLetters = str1.substring(i, i+2).toLowerCase();
            if (!isAlpha(twoLetters)) continue;
            map1.put(twoLetters, map1.getOrDefault(twoLetters, 0) + 1);
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            String twoLetters = str2.substring(i, i+2).toLowerCase();
            if (!isAlpha(twoLetters)) continue;
            map2.put(twoLetters, map2.getOrDefault(twoLetters, 0) + 1);
        }
        //debug map1, map2
        // System.out.printf("===beginning of map1===\n");
        // for (String str : map1.keySet()) {
        //     System.out.printf("key, value : %s, %d\n", str, map1.get(str));
        // }
        // System.out.printf("===end of map1===\n");
        // System.out.printf("===beginning of map2===\n");
        // for (String str : map2.keySet()) {
        //     System.out.printf("key, value : %s, %d\n", str, map2.get(str));
        // }
        // System.out.printf("===end of map2===\n");
        
   
        //합집합 개수
        Set<String> doneStr = new HashSet<>();
        int unionCnt = 0;
        for (String str : map1.keySet()) {
            if (doneStr.contains(str)) continue;
            
            if (map2.containsKey(str)) { //둘다 가지고 있으면
                unionCnt += Math.max(map1.get(str), map2.get(str));
            }else {
                unionCnt += map1.get(str);
            }
            doneStr.add(str);
        }
        for (String str : map2.keySet()) {
            if (doneStr.contains(str)) continue; //이미처리함 map1에서.  or map2에서 하나처리
            unionCnt += map2.get(str);
            doneStr.add(str);
        }
        
        // /교집합 처리
        int intersectionCnt = 0;
        for (String str : map1.keySet()) {
            
            if (map2.containsKey(str)) { //둘다 가지고 있으면
                intersectionCnt += Math.min(map1.get(str), map2.get(str));
                //System.out.printf("%s, %d selected to inter. currentInter: %d\n", str, Math.min(map1.get(str), map2.get(str)), intersectionCnt);
            }

        }
        //System.out.printf("inter, union : %d %d\n", intersectionCnt, unionCnt);
        return intersectionCnt == 0 && unionCnt == 0 ? 65536 : (int)((double)intersectionCnt/unionCnt * 65536);
    
    }
    public boolean isAlpha(String str) {
        char[] chars = str.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) return false;
        }
        return true;
    }
}
/**
fr ra an nc ce
fr re en nc ch

ha an nd ds sh ha ak ke
sh ha ak ke ha an nd ds
       
       
*/