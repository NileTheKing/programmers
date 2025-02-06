import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();     
        String[] str = new String[numbers.length];
        
        for (int i = 0; i < str.length; i++) {
            str[i] = Integer.toString(numbers[i]);
        }
        

        Arrays.sort(str, (a, b) -> {

            String ab = String.valueOf(a) + String.valueOf(b);
            String ba = String.valueOf(b) + String.valueOf(a);
            
            return ba.compareTo(ab); //ba가 ab보다 크다면 양수나올 것이고 양수나온다면 b가먼저나감.  
        });
        
        //정렬된 숫자 추가
        for(String num : str) {
            sb.append(num);
        }
        
        if (sb.charAt(0) == '0') {
            return "0";
        }
        
        return sb.toString();
    }
}
/**
1.다 추가해서 비교
2. 자리수는 똑같을 것이므로 담긴 숫자의 맨 앞자리가 큰 거로 붙임.

규칙
맨앞부터채움
각자리는 각 수 중 앞자리가 가장 큰 애가 옴
앞자리가 같다면 ,,
9 5 34 3 30
9 5 34 30 3
//         int[][] nidx = new int[numbers.length][2]; //인덱스와 numbers원본숫자접근용
        
//         //nidx에 인덱스와 숫자 복사. (0,6) (1,10), (2,2)
//         for (int i = 0; i< numbers.length; i++) {
//             nidx[i][0] = i;
//             nidx[i][1] = numbers[i];
//         }

*/