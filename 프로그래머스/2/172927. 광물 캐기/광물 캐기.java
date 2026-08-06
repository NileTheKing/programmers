import java.util.*;
class Solution {
    Map<String, Integer> map;
    String[] minerals;
    int min = Integer.MAX_VALUE;
    public int solution(int[] picks, String[] minerals) {
        init();
        this.minerals = minerals;
        int targetSize = (minerals.length + 5 - 1) / 5;
        int[] choice = new int[targetSize];
        Arrays.fill(choice, -1);
        backtrack(0, choice, picks);
        
        return min;
    }
    
    void backtrack(int idx, int[] choice, int[] picks) {
        if (idx >= choice.length) {
            // System.out.printf("=====calculating END======\n");
            calculate(choice);
            return;
        }
        if (allEmpty(picks)) {
            // System.out.printf("======calculating NO PICKS=======\n");
            calculate(choice);
            return;
        }
        // System.out.printf("======\n");
        // System.out.printf("idx = %d\n", idx);
        
        if (picks[0] > 0) {
            picks[0]--;
            choice[idx] = 0;
            backtrack(idx + 1, choice, picks);
            picks[0]++;
        }
        if (picks[1] > 0) {
            picks[1]--;
            choice[idx] = 1;
            backtrack(idx + 1, choice, picks);
            picks[1]++;
        }
        if (picks[2] > 0) {
            picks[2]--;
            choice[idx] = 2;
            backtrack(idx + 1, choice, picks);
            picks[2]++;
        }
    }
    void calculate(int[] choice) {
        //choice대로 minerals5개씩
        int res = 0;
        Outter : for (int i = 0; i < choice.length; i++) {
            // System.out.printf("--------\n");
            int turn = 0;
            if (choice[i] == -1) break;
            for(int j = 0; j < 5; j++) {
                int idx = 5 * i + j;
                if (idx >= minerals.length) break;
                int key = getKey(minerals[idx]);
                int pirodo = map.get(String.valueOf(choice[i])+"+"+String.valueOf(key));
                // System.out.printf("idx:%d, key:%d, realkey:%s\n",idx,  key, String.valueOf(choice[i])+"+"+String.valueOf(key));
                res+=pirodo;
                // System.out.printf("turn: %d\n", turn);
            }
            // res += turn;
            // System.out.printf("i=%d,choice:%d, res= %d\n", i,choice[i], res);
        }
        // System.out.printf("res:%d\n--------\n", res);
        min = Math.min(min, res);
        return;
    }
    void init() {
        map = new HashMap<>();
        map.put("0+0", 1);
        map.put("0+1", 1);
        map.put("0+2", 1);
        
        map.put("1+0", 5);
        map.put("1+1", 1);
        map.put("1+2", 1);
        
        map.put("2+0", 25);
        map.put("2+1", 5);
        map.put("2+2", 1);
        
        return;
    }
    int getKey(String str) {
        if (str.equals("diamond")) return 0;
        if (str.equals("iron")) return 1;
        if (str.equals("stone")) return 2;
        return -1;
    }
    boolean allEmpty(int[] picks) {
        for (int p : picks) {
            if (p > 0) return false;
        }
        return true;
    }
}
