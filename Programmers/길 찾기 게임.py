import sys
sys.setrecursionlimit(10**6)    #for recursion times over 1000

class Node:
    pre = []
    post = []
    
    def __init__(self, val, x, y):
        self.info = [val, x, y]
        self.left = None
        self.right = None
    
    @classmethod
    def insertNode(cls, root, node):
        flag = False
        next_x = node.info[1]
        cur_node = root
        cur_x = root.info[1]
        
        while not flag:
            if next_x < cur_x:
                if cur_node.left is None:
                    cur_node.left = node
                    flag = True
                else:
                    cur_node = cur_node.left
                    cur_x = cur_node.info[1]
            elif next_x > cur_x:
                if cur_node.right is None:
                    cur_node.right = node
                    flag = True
                else:
                    cur_node = cur_node.right
                    cur_x = cur_node.info[1]
                    
    @classmethod
    def pre_order(cls, root):
        Node.pre.append(root.info[0])
        if root.left is None and root.right is None:
            return
        if root.left is not None:
            Node.pre_order(root.left)
        if root.right is not None:
            Node.pre_order(root.right)
    
    @classmethod
    def post_order(cls, root):
        if root.left is not None:
            Node.post_order(root.left)
        if root.right is not None:
            Node.post_order(root.right)
        Node.post.append(root.info[0])
        
        if root.left is None and root.right is None:
            return

def solution(nodeinfo):    
    answer = list()
    nodes = []
    n = len(nodeinfo)
    
    for idx, node in enumerate(nodeinfo):
        nodes.append(Node(idx+1, node[0], node[1]))
        
    sorted_nodes = sorted(nodes, key=lambda x : (-x.info[2], x.info[1]))    
    
    root = sorted_nodes[0]
    
    for i in range(1, n):
        Node.insertNode(root, sorted_nodes[i])
    
    Node.pre_order(root)
    Node.post_order(root)
    
    answer.append(Node.pre)
    answer.append(Node.post)
    
    return answer