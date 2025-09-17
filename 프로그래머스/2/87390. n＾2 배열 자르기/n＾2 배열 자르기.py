def solution(n, left, right):
    ans = []
    for i in range(left, right+1):
        row = int(i / n + 1)
        col = int(i % n + 1)
        if col <= row:
            ans.append(row)
        else:
            ans.append(col)
    return ans