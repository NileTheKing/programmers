class Solution {
    boolean[][] compressed;
    int m,n;
    int cnt0, cnt1;
    public int[] solution(int[][] arr) {
        cnt0 = 0;
        cnt1 = 0;
        m = arr.length;
        n = m;
        compressed = new boolean[m][n];
        
        //가장큰 단위(m)를 가로로 압축시도..부터 1단위까지 .. 2까지.
            //최대 1024 512 ... 2
        for (int i = m; i >= 2; i /= 2) {
            compress(arr, i);
        }
        //마지막에 이제 집계.. 압축된 애들은.. 압축하면서 더하고 압축안된애들만 따로 더하면됨
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (compressed[i][j]) continue;
                if (arr[i][j] == 1) cnt1++;
                if (arr[i][j] == 0) cnt0++;
            }
        }
        return new int[] {cnt0, cnt1};
    }
    
    public void compress(int[][] arr, int size) {
        //System.out.printf("===Trying with size %d===\n", size);
        for (int i = 0; i < m; i += size) {
            for (int j = 0; j < n; j += size) {
                //이제 각 왼쪽위 꼭짓점에서 싹다 스캔
                if (compressed[i][j]) continue;
                if (isTheSame(arr, i, j, size)) {
                    //System.out.printf("success at top left (%d,%d)\n", i,j);
                    cnt0 = arr[i][j] == 0 ? cnt0 + 1 : cnt0;
                    cnt1 = arr[i][j] == 1 ? cnt1 + 1 : cnt1;
                    mark(i,j,size);
                }
            }
        }
        //System.out.printf("===end of size %d===\n", size);
    }
    public boolean isTheSame(int[][] arr, int r, int c, int size) {
        for (int i = r; i <= r + size - 1; i++) {
            for (int j = c; j <= c  + size - 1; j++) {
                if (arr[r][c] != arr[i][j]) 
                {    
                    //System.out.printf("at (%d,%d) not same. origin:%d, this: %d\n", i,j,arr[r][c], arr[i][j]);
                    return false;
                }
            }
        }
        return true;
    }
    public void mark(int r, int c, int size) {
        for (int i = r; i <= r + size - 1; i++) {
            for (int j = c; j <= c  + size - 1; j++) {
                //System.out.printf("(%d,%d) compressed\n", i,j);
                compressed[i][j] = true;
            }
        }
    }
}