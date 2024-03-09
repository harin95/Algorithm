# K개를 뽑아서 N을 만드는 경우의 수 = (K-1)개로 (N-1) 만드는 경우의 수 + (K-1)개로 N-2 만드는 경우의 수 + ... + (K-1)개로 0 만드는 경우의 수
# (K-1)개로 0 ~ N-1을 만드는 경우에 각각 N ~ 0을 더해주면 K개로 N을 만드는 경우가 된다

N, K = map(int, input().split(" "))

dp = [[0 for _ in range(N+1)] for _ in range(K+1)]

for n in range(N+1):
    dp[1][n] = 1

for k in range(K+1):
    dp[k][0] = 1

for k in range(1, K+1):
    for n in range(1, N+1):
        dp[k][n] = dp[k-1][n] + dp[k][n-1]

print(dp[K][N] % 1_000_000_000)
