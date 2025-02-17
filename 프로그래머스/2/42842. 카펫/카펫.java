class Solution {
    public int[] solution(int brown, int yellow) {
        
        int added = brown + yellow;

        for (int i = 1; i * i <= yellow; i++) {
            int first = i;
            int last = yellow / first;
            if (yellow % first == 0) {
                int totalSquared = (first + 2) * (last + 2);
                if (totalSquared - yellow == brown) {
                    return new int[] {last + 2, first + 2};
                }
            }
        }
        return null;
        
        
    }
}
     
        
   

/**
ㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁ
가운데에 있는 게 그냥 한줄로 되어있거나
 등등
2면 가로2 세로1 (가로1 세로2는 안됨 규칙상)
1이면 가로1 세로1
24면 가로24 세로1/ 가로12 세로2 /가로8 세로3/ 가로6 세로4 요놈임. 왜?
ㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅂㅂㅂㅂㅂㅂㅁ
ㅁㅂㅂㅂㅂㅂㅂㅁ
ㅁㅂㅂㅂㅂㅂㅂㅁ
ㅁㅂㅂㅂㅂㅂㅂㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁ
가로8 세로3이면
최소 가로10 세로5여야함
그렇다면 50 - 24하면 26임. 안됨
가로6 세로4면
최소 가로8 세로6여야함
48 - 24 = 24니까 가능.

*/