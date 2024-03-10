
def solution(s):
    n = len(s)
    ans = 0
    start = 0

    for i in range(n):
        ns = (start + i) % n

        if is_correct(s, ns, n):
            ans += 1
    return ans


def is_correct(s, ns, n):
    stack = []
    dic = {"[": "]", "{": "}", "(": ")"}
    for i in range(n):
        idx = (ns+i) % n
        letter = s[idx]
        if letter == '[' or letter == '{' or letter == '(':
            stack.append(letter)
        else:
            if len(stack) != 0:
                p = stack.pop()
                if dic.get(p) != letter:
                    return False
            else:
                return False

    if len(stack) > 0:
        return False

    return True
