import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) {
                return o2[0] - o1[0];//기본키기준 내림차순
            }
            else return o1[col - 1] - o2[col - 1];//col번째 기준 오름차순
        });
        //정렬완료,이제 순회하며 값들을 담는다
        int[] s = new int[data.length];//각 튜플의 칼럼들에 대해 연산
        for (int i = 0; i < data.length; i++) {
            int sum = 0;
            int[] tuple = data[i];
            for (int columnValue : tuple) {
                sum += (columnValue % (i + 1));
            }
            s[i] = sum;
        }
        int ans = 0;//이제 s 순회하면서 xor연산, xor의 항등원은 0
        for (int i = row_begin; i <= row_end; i++) {
            int r = i - 1; //실제 접근은 -1
            ans ^= s[r];
        }
        return ans;
    }
}
