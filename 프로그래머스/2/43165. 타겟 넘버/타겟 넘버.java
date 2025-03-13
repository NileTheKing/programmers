class Solution {
    private int cnt = 0;
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return cnt;
    }
    
    public void dfs(int level, int current, int[] numbers, int target) {
        if (level == numbers.length) {
            if (current == target) {
                cnt++;
            }
            return;
        }
        
        dfs(level + 1, current + numbers[level], numbers, target);
        dfs(level + 1, current - numbers[level], numbers, target);
    }
}
/**
dfs해서 백트래킹?식으로해서 끝까지 해서 카운팅
*/