import math
def solution(n, stations, w):
    coverage_width = 2 * w + 1
    last_cover = 0

    cnt = 0
    for station in stations:
        start_cover = station - w
        gap = start_cover - last_cover - 1
        cnt += math.ceil(gap / coverage_width)
        last_cover = station + w
        
    if last_cover < n:
        gap = n - last_cover
        cnt += math.ceil(gap / coverage_width)
        
    return cnt
        