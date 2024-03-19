import math

def solution(n, a, b):
    answer = 1
    s = min(a, b)
    l = max(a, b)
    
    for _ in range(0, n//2):
        if s%2 == 1 and s+1 == l:
                return answer
        else:
            s = math.ceil(s/2) if s>1 else 1
            l = math.ceil(l/2) if l>1 else 1
            answer += 1

    return answer 
