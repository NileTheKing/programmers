from collections import Counter
def solution(k, tangerine):
    map = Counter(tangerine)
    sorted_values = sorted(map.values(), reverse = True)
    
    cnt = 0
    types = 0
    for val in sorted_values:
        cnt += val
        types +=1
        if cnt >= k:
            return types
        
    return -1