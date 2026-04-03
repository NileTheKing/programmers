import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        List<int[]> y = new ArrayList<>(); //가능한 y [가로,세로]
        
        //모든 가능성..
        for (int i = 1; i * i <= yellow; i++) {
            int h = i;
            if (yellow % h != 0) continue;
            y.add(new int[] {yellow / h,i});
            //System.out.printf("%d, %d\n", yellow / h,i);
        }
        //이제 되는 경우에 대해서  삥둘러서 갯수맞는지 확인.
        for (int[] trial : y) {
            //전체모양 시도해가지고.. 거기서 yellow빼면 brown인데 지금 brown이랑맞으면됨
            int total = (trial[0] + 2) *  (trial[1] + 2);
            if (total - yellow == brown) return new int[] {trial[0] + 2, trial[1] + 2};
        }
        return new int[0];
    
    }
}
/**
테케1
    yellow가 가운데. 직사각형이니까 ㅡ 아니면 ㅣ ㅣ는안됨. ㅡ임
    그러면 ㅡ배치하고 삥두르면됨. 그러면 4*3
테케2
    yellow배치 그리고삥둘러.
테케3
    24니까
    ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ 1* 24 -> brown불가능
    or
    ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ 2* 12 -> 
    ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
    or
    ㅁㅁㅁㅁㅁㅁㅁㅁ 3* 8
    or
    4*6 여기까지. ->가능
    ㅁㅁㅁㅁㅁㅁ
    ㅁㅁㅁㅁㅁㅁ
    ㅁㅁㅁㅁㅁㅁ
    ㅁㅁㅁㅁㅁㅁ

*/