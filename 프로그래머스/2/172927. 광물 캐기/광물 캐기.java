import java.util.*;
class Solution {
    int min = Integer.MAX_VALUE;
    Map<String, Integer> map;
    public int solution(int[] picks, String[] minerals) {
        initializeMap();
        int[] choices = new int[minerals.length];
        Arrays.fill(choices, -1);
        backtrack(picks, minerals, 0, choices);
        return min;
    }
    public void backtrack(int[] picks, String[] minerals, int choiceIdx, int[] choices) {
        // System.out.printf("idx %d\n", choiceIdx);
        if (usedAll(picks) || choiceIdx == (minerals.length + 5 - 1)/ 5) { //10개 -> choiceIdx == 2종료. 0indexed

            calculate(minerals, choices);
            return;
        }
        // if (usedAll(picks)) {System.out.printf("returning cuz of empty\n");return;}
        // System.out.printf("idx %d\n", choiceIdx);
        //0 1 2중에 고름..갯수가 있어야 고름 돌 철 다야
        if (picks[0] > 0) {
            choices[choiceIdx] = 0;
            picks[0]--;
            backtrack(picks, minerals, choiceIdx + 1, choices);
            picks[0]++;
        }
        if (picks[1] > 0) {
            choices[choiceIdx] = 1;
            picks[1]--;
            backtrack(picks, minerals, choiceIdx + 1, choices);
            picks[1]++;
        }
        if (picks[2] > 0) {
            choices[choiceIdx] = 2;
            picks[2]--;
            backtrack(picks, minerals, choiceIdx + 1, choices);
            picks[2]++;
        }
        //돌려놓을 필요? picks는 같은레벨의 다른애들한테 영향주는데.. choices0 1은 ㅇ덮어씅지느끼 상관없고.. 2는 어차피 이 뒤에 영향받을애가 다음 뎁스(레벨)인데 걘 영향받는게당연하고 이젠 뎁스로 돌아가는 리턴에서는..상관없지 리턴만 계속될텐데...
        return;
    }
    public void calculate(String[] minerals, int[] choices) {
        // System.out.printf("===calculating===\n");
        // for (int c : choices) System.out.println(c);
        int pirodo = 0;
        for (int i = 0; i < choices.length; i++) {
            //고른 곡괭이 choices[i]로 minerals 5개 캐서 계산후 max계산.
            int pick = choices[i];
            int startJ = 5 * i;
            if (pick == -1) break;
            for (int j = 0; j < 5; j++) {
                if (startJ + j >= minerals.length) break;
                String mineral = minerals[startJ + j];
                String key = pick + mineral;
                // System.out.printf("spent %d\n", map.get(key));
                pirodo += map.get(key);
            }
        }
        // System.out.printf("total = %d\n", pirodo);
        min = Math.min(min, pirodo);
        return;
        
    }
    public void initializeMap() {
        map = new HashMap<>();
        map.put("2stone", 1);
        map.put("2iron", 5);
        map.put("2diamond", 25);
        
        map.put("1stone", 1);
        map.put("1iron", 1);
        map.put("1diamond", 5);
        
        map.put("0stone", 1);
        map.put("0iron", 1);
        map.put("0diamond", 1);
        
        return;
    }
    public boolean usedAll(int[] picks) {
        for (int p : picks) if (p != 0) return false;
        return true;
    }
}
/**
곡괭이가있다. 종류3개
갯수는 각각 0-5사이.캘떄 피로도소모.. 표존재
꺼낸 곡괭이는 없어질떄까지 -5개연속 캐야함

5개단위로 선택해야한다. 곡괭이 다쓸 떄 까지 다캘 떄 까지.
다캘떄 최소피로도 return

백트래킹
총 10번의 선택
*/