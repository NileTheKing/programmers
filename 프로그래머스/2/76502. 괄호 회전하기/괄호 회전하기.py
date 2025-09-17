def solution(s):
    cnt = 0
    for i in range(len(s)): #i는 시작index
        stack = []
        counts = True
        for j in range(len(s)): #j는 번째 인덱스
            index = (i + j) % len(s)
            c = s[index]
            if c == '[' or c == '(' or c == '{':
                stack.append(c)
            else:
                if not stack:
                    counts = False
                    break
                peek = stack[-1]
                if ((c == ')' and peek == '(') or
                    (c == ']' and peek == '[') or
                    (c == '}' and peek == '{')):
                        stack.pop()
                else:
                    counts = False
                    break
        if counts and not stack:
            cnt += 1
    return cnt