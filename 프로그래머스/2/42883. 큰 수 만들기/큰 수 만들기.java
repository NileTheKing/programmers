import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);
        int i = 0;
        // k가 남아있는 동안 반복
        while (k > 0) {
            // 현재 위치 i가 마지막 전까지 있을 경우
            if (i < sb.length() - 1 && sb.charAt(i) < sb.charAt(i+1)) {
                // i번째 숫자가 i+1번째보다 작으면 i번째 숫자 삭제
                sb.deleteCharAt(i);
                k--;
                // 삭제 후 바로 앞 자리부터 재검사 (단, i가 0이면 그대로)
                if (i > 0) {
                    i--;
                }
            } else {
                i++;
                // 만약 i가 마지막 인덱스에 도달하면
                if (i >= sb.length() - 1) {
                    // 남은 k만큼 마지막 자리에서 삭제
                    sb.delete(sb.length() - k, sb.length());
                    k = 0;
                }
            }
        }
        return sb.toString();
    }
}
