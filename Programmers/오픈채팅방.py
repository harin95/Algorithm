def solution(records):
    answer = []

    dict = {}

    for record in records:
        inp_list = list(record.split(" "))
        if len(inp_list) == 3:
            dict[inp_list[1]] = inp_list[2]

    for record in records:
        inp_list = list(record.split(" "))
        if inp_list[0] == "Enter":
            answer.append(f'{dict.get(inp_list[1])}님이 들어왔습니다.')
        elif inp_list[0] == "Leave":
            answer.append(f'{dict.get(inp_list[1])}님이 나갔습니다.')
    return answer
