class Solution {
    int min = Integer.MAX_VALUE;
    public int solution(int[][] beginning, int[][] target) {
        int m = beginning.length; //행(세로)
        int n = beginning[0].length;//가로
        for (int i = 0; i < (1 << m); i++) {//0부터 1023까지. 1<<10은 1024니까..우리는 11111 11111까지. 
            Outter : for (int j = 0; j < (1 << n); j++) {
                //이제 행뒤집은 스위치, 열 뒤집은 스위치 상태있음.
                //beginning 기준 스위치 상태보고 뒤집어가지고 target이랑 비교. 다르면 중지
                //여기서.. 스위치가 01 10이면 뒤집는거고 00 11이면 안뒤집음..이거는 xor일듯
                //왜냐면 xor은..or인데 exclusive해야하니까.
                for (int k = 0; k < m; k++) {
                    for (int l = 0; l < n; l++) {
                        int before = beginning[k][l];
                        //현재 k(행) 뒤집힌 여부랑 l뒤집현여부알아야한다.
                        //소스는 i와 j인데 어케아냐? i의 k번째 비트와 j의l번쨰비트
                        int rowBit = (i >> k) & 1;
                        int colBit = (j >> l) & 1;
                        int after = before ^ rowBit ^ colBit;
                        
                        if (after != target[k][l]) continue Outter;
                    }
                }
                //같으면 카운트 업데이트..카운트는? 스위치(i,j) 1갯수 세면 된다.
                int cnt1 = 0, cnt2 = 0;
                //i와 j의 비트갯수를 센다. 1갯수.
                // System.out.printf("========\n");
                for (int x = 0; x < m; x++) {
                    if (((i >> x) & 1) == 1) 
                    {    
                        cnt1++;
                        // System.out.printf("행 %d 뒤집음\n", x + 1);
                    }
                }
                
                for (int x = 0; x < n; x++) {
                    if (((j >> x) & 1) == 1) 
                    {    
                        cnt2++;
                        // System.out.printf("행 %d 뒤집음\n", x + 1);
                    }
                }
                // System.out.printf("i j cnt1 cnt2: %d %d %d %d\n", i,j,cnt1, cnt2);
                min = Math.min(min, cnt1 + cnt2);
            }
            
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
