def solution(sticker):
    if len(sticker) == 1:
        return sticker[0]
    
    def helper(arr) :
        n = len(arr)
        if n == 0:
            return 0;
        if n == 1:
            return arr[0]
        
        dp = [0] * n
        dp[0] = arr[0]
        dp[1] = max(arr[0], arr[1])
        
        for i in range(2, n) :
            dp[i] = max(dp[i - 1], dp[i - 2] + arr[i])
        return dp[n - 1]
    
    return max(helper(sticker[:-1]), helper(sticker[1:]))