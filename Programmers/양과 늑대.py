from collections import deque
from collections import Counter

class Node:
    def __init__(self, val, sheep, wolves, to_visit):
        self.val = val
        self.sheep = sheep
        self.wolves = wolves
        self.to_visit = to_visit
        
        
def solution(info, edges):
    answer = 0
    n = len(info)
    children = [[] for _ in range(n)]
    
    cnt = Counter(info)[0]
    
    for edge in edges:
        parent = edge[0]
        child = edge[1]
        children[parent].append(child)
        

    q = deque()
    q.append(Node(0, 1, 0, set()))
    
    while len(q) > 0:
        if answer == cnt:
            return answer
        
        cur = q.popleft()
        cur.to_visit.update(children[cur.val])
        answer = max(answer, cur.sheep)
        
        for adj in cur.to_visit:
            if info[adj] == 0:  # 양일 경우
                q.append(Node(adj, cur.sheep+1, cur.wolves, cur.to_visit-{adj}))
            elif info[adj] == 1:    # 늑대일 경우
                if len(children[adj]) == 0:     # 늑대인 리프노드는 갈 필요 없음
                    continue
                elif cur.sheep > cur.wolves + 1:
                    q.append(Node(adj, cur.sheep, cur.wolves+1, cur.to_visit-{adj}))
        
    return answer
  
