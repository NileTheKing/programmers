class Solution {
    int min = Integer.MAX_VALUE;
    int[][] cost;
    int[][] hint;
    int n;
    public int solution(int[][] cost, int[][] hint) {
        this.cost = cost;
        this.hint = hint;
        this.n = cost.length;
        backtrack(0, 0, 1);
        return min;
    }
    void backtrack(int hintMasking, int spent, int stageNo) {
        //힌트 최대한사용 n장까지
        // System.out.printf("stageNo:%d, spent:%d\n", stageNo,spent);
        int numHintsThisStage = 0;
        //hintMasking을 순회하면서.. hintMasking비트켜졌으면 갯수세
        //TODO..이거 직접해야함?아 예전엔 따로 전역변수로 관리했음..
        for (int i = 0; i < n; i++) {
            if ((hintMasking & (1 << i)) == 0) continue;
            for (int j = 0; j < hint[i].length; j++)
                if (hint[i][j] == stageNo) numHintsThisStage++;
        }
        int toBeUsed = Math.min(numHintsThisStage, n - 1);
        spent += cost[stageNo-1][toBeUsed];
        if (stageNo == n) {
            min = Math.min(min, spent);
            return;
        }
        // System.out.printf("this round: %d\n", cost[stageNo-1][toBeUsed]);
        //안산다
        backtrack(hintMasking, spent, stageNo + 1);
        //산다
        spent += hint[stageNo-1][0];
        hintMasking |= (1 << (stageNo - 1));
        backtrack(hintMasking, spent, stageNo + 1);
        
    }
}