import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0] - 1;//시작인덱스
            int end = commands[i][1] - 1;//끝인덱스
            int[] cut = new int[end - start + 1];//자른배열
            //array에 있는 거 잘라서 복사
            int idx = 0;
            for (int j = start; j <= end; j++) {
                cut[idx++] = array[j];
                //System.out.println(cut[idx - 1] + "복사완료");
            }
            Arrays.sort(cut);
            //debug
            // System.out.print(i + "번째 명령어 정렬: ");
            // for (int a : cut) {
            //     System.out.print( a + " ");
            // }
            // System.out.println();
            answer[i] = cut[commands[i][2] - 1];
        }
        return answer;
    }
}