import sys
from collections import deque

input = sys.stdin.readline()

n, k = map(int, input.split())

q = deque()

for i in range(1, n+1):
    q.append(i)

answer = []

while len(q) > 0:
    for i in range(k-1):
        temp = q.popleft()
        q.append(temp)
    answer.append(q.popleft())

ans_print = str(answer)
ans_print = ans_print.replace("[", "<")
ans_print = ans_print.replace("]", ">")

print(ans_print)
