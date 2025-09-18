def solution(arr1, arr2):
    m, n, l = len(arr1), len(arr1[0]), len(arr2[0])
    # m * l이 결과로 나올것
    ans = [[0] * l for _ in range(m)]
    for i in range(m):
        for j in range(l):
            # (i,j)
            sum = 0
            for k in range (n):
                sum += arr1[i][k] * arr2[k][j]
            ans[i][j] = sum
    return ans