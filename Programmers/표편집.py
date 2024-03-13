class Node:
    def __init__(self, idx, prev, next):
        self.idx = idx
        self.prev = prev
        self.next = next
    
def solution(n, k, cmd_list):
    # 표 초기화
    head_node = Node(-1, None, None)
    prev_node = head_node

    for idx in range(0, n):
        next_node = Node(idx, prev_node, None)
        prev_node.next = next_node
        prev_node = next_node

    cur_node = head_node
    for _ in range(k+1):
        cur_node = cur_node.next

    stack = []

    for cmd in cmd_list:
        command = cmd[0]
        
        if command == "U":
            x = int(cmd.split(" ")[1])
            for _ in range(x):
                cur_node = cur_node.prev
        
        elif command == "D":
            x = int(cmd.split(" ")[1])
            for _ in range(x):
                cur_node = cur_node.next
        
        elif command == "C":
            stack.append(cur_node)

            if cur_node.next == None:   #마지막 노드일 경우
                cur_node.prev.next = None
                cur_node = cur_node.prev
            else:
                cur_node.prev.next = cur_node.next
                cur_node.next.prev = cur_node.prev
                cur_node = cur_node.next
        else:   #복구
            undo_node = stack.pop()
            if undo_node.next == None:  #마지막 노드였을 경우
                undo_node.prev.next = undo_node
            else:
                undo_node.prev.next = undo_node
                undo_node.next.prev = undo_node
        
    print_node = head_node.next
    ans = ""
    for i in range(n):
        if print_node is not None and i == print_node.idx:
            ans += "O"
            print_node = print_node.next
        else:
            ans += "X"

    return ans
  
