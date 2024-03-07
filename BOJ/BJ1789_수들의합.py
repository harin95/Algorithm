S = int(input())
sum1 = S
N = 0

if S == 1:
    print("1")
    exit()

for i in range(1, S):
    sum1 -= i
    N += 1
    if sum1 == 0:
        break
    elif sum1 < 0:
        N -= 1
        break

print(N)
