import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] house = new int[N]; //1-indexed N개인
        for (int i = 0; i < N; i++) {
            String num = br.readLine();
            int coord = Integer.parseInt(num);
            house[i] = coord;
        }
        int l = 1;//최소1
        int ans = 0;
        Arrays.sort(house); //1번인덱
        int r = house[N-1] - house[0];
        while (l <= r) {
            int m = l + (r - l) / 2;
            
            //최대로 m이어야함 for문순회했을떄
            int cnt = 0;
            int prev = house[0]; //맨앞집에는 설치
            cnt++;//한개설치
            for (int i = 1; i < house.length; i++) {
                //지금 보는 집에다가 공유기설치했는데 m보다작으면안됨
                if (house[i] - prev < m) {
                    continue;
                }else {
                    cnt++;// 설치
                    prev = house[i]; //설치함.
                }
            }
            //m이 너무 크면 설치를안해서 C보다작을것
            if (cnt < C) {
                //m이 너무커가지고 설치를 못함. 더작아야함
                r = m - 1;
            }else {
                ans = m;//가능..메모
                l = m + 1;
            }
        }
        System.out.println(ans);
    }
}
/**갯수가 C인거랑 이분ㄴ탐색하면서 count한거랑 비교해서 탐색범위를 조정할것
 * for문 순회할때는.. 일단 시작지점에 하나박고.. 가장인접한애가 
 */
