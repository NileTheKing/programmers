from collections import OrderedDict
def solution(s):
    modified = s[2:-2]
    sliced = modified.split("},{")
    sliced.sort(key=lambda x: len(x))
    ans = OrderedDict()

    for s in sliced:
        csv = s.split(',')
        for i in csv:
            ans[int(i)] = None
    return list(ans.keys())
    