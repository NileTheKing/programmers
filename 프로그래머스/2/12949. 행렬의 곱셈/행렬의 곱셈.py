def solution(arr1, arr2):
    m = len(arr1)
    n = len(arr1[0])
    l = len(arr2[0])
    ans = [[0 for _ in range(l)] for _ in range(m)]
    for i in range(m) :
        for j in range(l):
            sum = 0
            for k in range(n) :
                ans[i][j] += arr1[i][k] * arr2[k][j]
    
    return ans