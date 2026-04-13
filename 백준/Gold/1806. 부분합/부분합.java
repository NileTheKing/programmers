import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int l = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int r = i;//r은간다
            sum += arr[r];
            while (sum >= S) {
                min = Math.min(r - l  + 1, min);
                sum -= arr[l];
                l++;
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 :min);
    }
}