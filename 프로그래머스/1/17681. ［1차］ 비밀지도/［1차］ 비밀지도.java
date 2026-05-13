import java.util.*;
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        //arr1과 2를 동시에 순회하면서 ..
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //arr1과 arr2..bit로확인하면서.
            // System.out.printf("===row===\n");
            int bit1 = arr1[i];
            int bit2 = arr2[i];
            // System.out.printf("bit1, bit2 (%d %d)\n", bit1, bit2);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {//i행j열 변환중
                int digit = n - j - 1;// n이5고 j가0이면 5번째자리.
                boolean wall1 = false, wall2 = false;
                if ((bit1 & (1 << digit)) != 0) wall1 = true;
                if ((bit2 & (1 << digit)) != 0) wall2 = true;
                // System.out.printf("j digit wall1 wall2: (%d %d %b %b)\n", j, digit, wall1, wall2);
                if (wall1 || wall2) sb.append('#');//둘중하나라도 벽이면
                else sb.append(' ');
            }
            ans.add(sb.toString());
        }
        return ans.toArray(new String[0]);
        
    }
}
/**
공백 ' ', 벽'#'
지도는 2개있따
지도는 10진수로 나와있는데 길이가n인 이진수로 변환
1은 # 0은 공백
겹쳐서 하나라도 벽이면 전체지도는 벽이됨

*/