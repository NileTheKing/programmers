import java.util.*;
class Solution {
    int min = Integer.MAX_VALUE;
    public int solution(int[] picks, String[] minerals) {
        backtrack(new Info(picks[0], picks[1], picks[2]), 0, minerals, 0, -1); //TODO
        return min;
    }
    //5마다 고름..미리5칸계산 or 호출마다 배수보고 계산?ㅁㄹ
    public void backtrack(Info info, int idx, String[] minerals, int curr, int holding) {
        if (idx >= minerals.length || (idx % 5 == 0 && info.isEmpty())) {
            // System.out.printf("updating... \n");
            // System.out.printf("!!!info:(%d,%d,%d), idx: %d, curr: %d, hodling : %d!!!\n", info.dia, info.iron, info.stone, idx, curr, holding);
            min = Math.min(curr, min);
            return;
        }
        // System.out.printf("info:(%d,%d,%d), idx: %d, curr: %d, hodling : %d\n", info.dia, info.iron, info.stone, idx, curr, holding);
        
        //5개단위 안함 -> 그냥 갯수그대로하고 idx, curr만 진행
        //5개단위 깸 -> 골라야함 0,
        //idx가 지금 0이면 골라야함  5면 골라야함..
        // 골라0 1 2 3 4 5 6 7 8 9 10
        if (idx % 5 == 0) {
            //골라서 백트래킹
            if (info.dia > 0) {
                info.dia--;//뽑음
                
                backtrack(info, idx + 1, minerals, curr + getDiff(minerals[idx], 0), 0);
                info.dia++;
        
            }
            if (info.iron > 0) {
                info.iron--;//뽑음
                    backtrack(info, idx + 1, minerals, curr + getDiff(minerals[idx], 1), 1);
                info.iron++;
                
            }
            if (info.stone > 0) {
                info.stone--;//뽑음
                         backtrack(info, idx + 1, minerals, curr + getDiff(minerals[idx], 2),2);
                info.stone++;
            }
                
        }else {//그냥 진행. 피로도만
            //지금 뭐들고있는지에 따라 다름.
            int diff = getDiff(minerals[idx], holding);
            
            backtrack(info, idx + 1, minerals, curr + diff, holding);
            //얜 값안바뀌었으니까 백트래킹안해도됨
        }
    }
    
    
    
    
    public int getDiff(String mineral, int holding) {
        if (holding == 0) {
            return 1;
        }else if (holding == 1) {
            if (mineral.equals("diamond")) return 5;
            else return 1;
        }else {
            if (mineral.equals("diamond")) return 25;
            else if(mineral.equals("iron")) return 5;
            else return 1;
        }

    }
    
    public class Info {
        int dia;
        int iron;
        int stone;
        Info (int dia, int iron, int stone) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
        }
        public boolean isEmpty() {
            if (this.dia <= 0 && this.iron <= 0 && this.stone <= 0) {
                // System.out.printf("empty!%d %d %d\n", this.dia, this.iron, this.stone);
                return true;
            }
            else return false;
        }
    }
}
/**
곡괭이는 0-5개씩있다
곡괭이 꺼내면 사용없을떄까지 사용.(5개..피로도랑 내구도랑은 별개)
광물은 정해진 순서가있다.
종료는 다캐거나 곡괭이없을떄(할게없을떄)
그니까 minerals를 뚫고 지나갈건데 뭐부터 쓸거냐 이거지..
근데 곡괭이는 같은종류 연속으로 써야하는건아님.5개 다썼으면 다른거 뽑아도됨..
예시가 좀 불친절.
그러면 이거도 뭐 5개단위로 가는거고 백트래킹이지
각가 3개씩 있다면 
최악의 경우 3 ^ 15완전맥스인데 그건아님
3 * 3 * 3 * 3 * 3 * 2 * 2 * 2 * 2 * 2 * 1  ^ 1 ^ 1 ^ 1정도.
그냥 하는게 더 낫긴해 뭐 어차피 연속으로 광물있는게 아니니까 이문제는 그냥 브루틒포스임
그렇다면 매 백트래킹에서 남은갯수를 가지고있어야하고,, 현재 뭐파고있는지 idx필요.
그러면 음..남은갯수니까 그냥 들고다니면 되는거아님?

잠깐 멀리봐
자.. 5개단위로 쓸수있는거야.. 
0 1 2 3 4 5
left가없으면 그냥 비어있다고 종료돼. 그래서 left필드를 추가해서 막았어
근데 그걸로 막으니까 종료조건이 호출이 안되네 2번테케에선
*/