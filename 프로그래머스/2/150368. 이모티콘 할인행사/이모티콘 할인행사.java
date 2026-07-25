class Solution {
    int maxService = 0;
    int maxProfit = 0;
    int[][] users;
    int[] emoticons;
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        backtrack(0, new int[emoticons.length]);
        return new int[] {maxService, maxProfit};
    }
    void backtrack(int idx, int[] current) {//할인율들고다니기
        if (idx == current.length) {
            //계산 및 겡신
            calculateAndRefresh(current);
            return;            
        }
        current[idx] = 10;
        backtrack(idx + 1, current);
        
        current[idx] = 20;
        backtrack(idx + 1, current);
        
        current[idx] = 30;
        backtrack(idx + 1, current);
        
        current[idx] = 40;
        backtrack(idx + 1, current);
    }
    //완성시 계산 및 갱신
    void calculateAndRefresh(int[] current) {
        int profit = 0;
        int service = 0;
        for (int[] u : users) {
            int userSpent = 0;
            for (int i = 0; i < emoticons.length; i++) {
                int discountRate = current[i];
                if (u[0] <= discountRate) userSpent += (emoticons[i] * (100-discountRate) / 100);
                if (userSpent >= u[1]) {
                    userSpent = 0;
                    service++;
                    break;
                }
            }
            profit += userSpent;
        }
        if (service > maxService) {
            maxService = service;
            maxProfit = profit;
        }else if (service == maxService) {
            maxProfit = Math.max(maxProfit, profit);
        }else return;//
    }
    
}
/**
사람수100명..마지막에 계산하면그만..100이야껌이지
emoticon7개
4*7이면 겁나작음. 완성하고함ㄴ돼 양궁이랑 비슷하네
*/