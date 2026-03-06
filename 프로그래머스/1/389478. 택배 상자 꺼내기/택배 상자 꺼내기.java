class Solution {
    public int solution(int n, int w, int num) {
        int[][] boxes = new int[(n / w) + 1][w];//시뮬레이션돌려버려
        //순서대로 진짜 쌓음
        int number = 1;
        outter : for (int i = 0; i < boxes.length; i++) {
            if (i % 2 == 1) {//오른쪽부터 쌓아야함.숫자는 전줄 맨 오른쪽
                for (int j = w - 1; j >= 0; j--) {
                    if (number > n) break outter;
                    boxes[i][j] = number++;
                }
            }else {//왼쪽부터 쌓아야함. 
                for (int j = 0; j < w; j++) {
                    if (number > n) break outter;
                    boxes[i][j] = number++;
                }
            }
        }
        // for (int i = 0; i < boxes.length; i++) {
        //     for (int j = 0; j < w; j++) {
        //         System.out.printf("%d ", boxes[i][j]);
        //     }
        //     System.out.println();
        // }
        //이제 ㅏㅆㅎ아졌으니까 해당상자 위에 몇개나 있나보면됨. 그 숫자 행과열을찾고
        //해당 열에서 위로 몇행이 있는지 보면 됨
        int r = 0; int c = 0;
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < w; j++) {
                if (boxes[i][j] == num) {
                    r = i;
                    c = j;
                    break;
                }
            }
        }
        
        //이제 찾았으니까 아래에..
        int cnt = 0;
        for (int j = r; j < boxes.length; j++) {
            if (boxes[j][c] != 0) cnt++;
            else break;
        }
        return cnt;
    }
}