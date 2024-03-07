def solution(dirs):
    cnt = 0 
    routes = set()
    
    #시작점
    r = 5
    c = 5

    dict = {"U" : [1, 0], "D" : [-1, 0], "L" : [0, -1], "R" : [0, 1]}

    for dir in dirs:
        nr = r + dict.get(dir)[0]
        nc = c + dict.get(dir)[1]

        if(nr < 0 or nr > 10 or nc < 0 or nc > 10):
            continue;
        else:
            routes.add((r, c, nr, nc))
            routes.add((nr, nc, r, c))
            r = nr
            c = nc


    return len(routes)/2
