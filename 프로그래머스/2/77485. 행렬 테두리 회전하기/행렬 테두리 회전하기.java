import java.util.*;
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        //map생성
        int[][] map = new int[rows][columns];
        for (int i  = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = i * columns + j + 1;
            }
        }
        // System.out.println("init");
        // for (int[] m : map) System.out.println(Arrays.toString(m));
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = move(map, queries[i]);
        }
        return ans;
    }
    public int move(int[][] map, int[] query) {
        // System.out.println("====q====");
        int r1 = query[0] - 1;
        int c1 = query[1] - 1;
        int r2 = query[2] -1;
        int c2 = query[3] - 1;
    
        int temp1 = map[r1][c1];//왼쪽위
        int temp2 = map[r1][c2];//오른쪽위
        int temp3 = map[r2][c2];//오른쪽아래
        int temp4 = map[r2][c1];//왼쪽아래
        
        //위ㅡ..
        // System.out.println("upper hori");
        int min = Integer.MAX_VALUE;
        int val = temp1;
        for (int c = c1 + 1; c <= c2; c++) {
            int targetC = c;
            min  = Math.min(min, map[r1][c]);
            int temp = map[r1][targetC];
            map[r1][targetC] = val;
            val = temp;
        }
        // for (int[] m  : map) System.out.println(Arrays.toString(m));
        // 오른ㅣ c2이고 r1부터 r2-1까지!주의 temp2에서 읽어야함
        // System.out.println("right vertical");
        val = temp2;
        for (int r = r1 + 1; r <= r2; r++) {
            int targetR = r;
            min  = Math.min(min, map[r][c2]);
            int temp = map[targetR][c2];
            map[targetR][c2] = val;
            val = temp;
        }
        // for (int[] m  : map) System.out.println(Arrays.toString(m));
        // 아래ㅡ r2이고 c1+1부터 c2R까지!주의 temp3에서 읽어야함
        // System.out.println("bottom  hori");
        val = temp3;
        for (int c = c2 - 1; c >= c1; c--) {
            int targetC = c;
            min  = Math.min(min, map[r2][c]);
            int temp = map[r2][targetC];
            map[r2][targetC] = val;
            val = temp;
        }
        
        // 왼ㅣ c1이고 r1+1부터 r2까지!주의 temp4에서 읽어야함
        // System.out.println("left vertical");
        val = temp4;
        for (int r = r2 - 1; r >= r1; r--) {
            int targetR = r;
            min  = Math.min(min, map[r][c1]);
            int temp = map[r][c1];
            map[r][c1] = val;
            val = temp;
        }
        // for (int[] m  : map) System.out.println(Arrays.toString(m));
        
        // System.out.printf("returning %d\n", min);
        return min;
        
    }
}
/**
가로세로 100*100
q는 10000
즉 풀스캔을 10^4 * 10^4가능. 1억이네. 그냥 풀스캔가증
회전할떄.. 규칙이 위에
ㅡ는 마지막칸뺴고 오른쪽한칸. 걘 다음 ㅣ가처리
ㅣ오른쪽거는 마지막칸뺴고 다 아래로. 마지막칸은 다음 ㅡ가처리
ㅡ 아래는 맨왼쪽거빼고 다 왼쪽한칸. 맨왼쪽은 다음 ㅣ가처리.
ㅣ 왼쪽거는 맨 위뺴고 다 위로한칸..
그럼 그림으로 보면 꼭짓점은 예외로치고 ㅡ ㅣ ㅡ ㅣ 다 일반화가능
위ㅡ일반항은 >

*/