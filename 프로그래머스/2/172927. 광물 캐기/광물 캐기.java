import java.util.*;
class Solution {
    int min = Integer.MAX_VALUE;
    public int solution(int[] picks, String[] minerals) {
        int[] choice = new int[(minerals.length + 5 - 1) / 5];
        Arrays.fill(choice, -1);
        backtrack(picks, minerals, 0, choice);
        return min;
    }
    //5개단위로 할건데 만약minerals가 13임. 그러면 13 / 5면 2인데 덩어리는 3개필요하다. 13 + 5 - 1 / 5 일케하면3.
    //처음값ㅁ노르겠는데 암튼 nth가 저거 값보다 커지면안됨 근데 0index하ㅣ고 0 1 2 하고 3이면긑
    public void backtrack(int[] picks, String[] minerals, int nth, int[] choice) {
        if (nth == (minerals.length + 5 - 1) / 5 || allEmpty(picks)) {
            //choice가지고 이제 꺼내서 실제로 계산하고 ..최솟값갱신
            calculate(choice, minerals);
            return;
        }
        // System.out.printf("nth %d \n", nth);
        if (picks[0] > 0) {
            choice[nth] = 0;//dia
            picks[0]--;
            backtrack(picks, minerals, nth + 1, choice);
            picks[0]++;
        }
        if (picks[1] > 0) {
            choice[nth] = 1;//iron
            picks[1]--;
            backtrack(picks, minerals, nth + 1, choice);    
            picks[1]++;
        }
        if (picks[2] > 0) {    
            choice[nth] = 2;//stone
            picks[2]--;
            backtrack(picks, minerals, nth + 1, choice);
            picks[2]++;
        }
        //backtrack해야하나?
    }
    public void calculate(int[] choice, String[] minerals) {
        //choice에ㅐ는 5개단위로 뭐썼는지 적혀있음. minerals 순회하면서 계산하면됨
        int fatigue = 0;//피로도
        // System.out.printf("========\n");
        for (int i = 0; i < choice.length; i++) {
            int pick = choice[i]; //이거가지고 5개 캙럿.
            if (pick == -1) break;
            // System.out.printf("pick : %d \n", pick);
            if (pick == 0) { //dia로캠..i * 5로부터 5개
                for (int j = 0; j < 5; j++) {
                    int index = i * 5 + j;
                    if (index >= minerals.length) break;
                    fatigue++;
                }
            }else if (pick == 1) { //iron으로캠
                for (int j = 0; j < 5; j++) {
                    int index = i * 5 + j;
                    if (index >= minerals.length) break;
                    if (minerals[index].equals("diamond")) fatigue += 5;
                    else fatigue++;
                }
            }else{//stone으로캠
                for (int j = 0; j < 5; j++) {
                    int index = i * 5 + j;
                    if (index >= minerals.length) break;
                    if (minerals[index].equals("diamond")) fatigue += 25;
                    else if(minerals[index].equals("iron")) fatigue += 5;
                    else fatigue++;
                }
            }
        }
        // System.out.printf("피로도 : %d\n", fatigue);
        min = Math.min(fatigue, min);
        return;      
    }
    public boolean allEmpty(int[] picks) {
        for (int p : picks) if (p != 0) return false;
        return true;
    }
    
}
/**
picks 는  dia iron stone각 갯수
minerals는 순서대로만 캐는것이 가능.
minerals 최대50..인데 5개씩 캐니까 총 10번의 선택을한다. 3^10이면 가능

피로도 계산은 마지막에 하는게 깔끔.
*/