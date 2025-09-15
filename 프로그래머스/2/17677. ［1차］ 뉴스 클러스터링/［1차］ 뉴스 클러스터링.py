from collections import Counter
def solution(str1, str2):
    list1 = [str1[i:i+2].lower() for i in range(len(str1) - 1) if str1[i:i+2].isalpha()]
    list2 = [str2[i: i+2].lower() for i in range(len(str2) - 1) if str2[i:i+2].isalpha()]
    
    counter1 = Counter(list1)
    counter2 = Counter(list2)
    
    intersection = counter1 & counter2
    union = counter1 | counter2
    
    intersectionCnt = sum(intersection.values())
    unionCnt = sum(union.values())
    
    if unionCnt == 0:
        return 65536
    
    return int((intersectionCnt / unionCnt) * 65536)