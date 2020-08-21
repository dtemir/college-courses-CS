"""This is an implementation of the Exercise 7"""


# Start by creating a node class
class Node:

    def __init__(self, val):
        self.left = None
        self.right = None
        self.val = val


# Build an insertion function that recursively puts nodes according to their value
# less goes on the left, more goes on the right
def insert_bst(root, node):
    if root is None:
        root = node
    else:
        if root.val.lower() < node.val.lower():
            if root.right is None:
                root.right = node
            else:
                insert_bst(root.right, node)
        else:
            if root.left is None:
                root.left = node
            else:
                insert_bst(root.left, node)


# Build a print function that represents the BST
# It first prints values from the left side of the tree, then from the right side
def print_bst(root):
    if root:
        print_bst(root.left)
        print(root.val)
        print_bst(root.right)


r = Node("Mathematics")
insert_bst(r, Node("Computers"))
insert_bst(r, Node("Spanish"))
insert_bst(r, Node("Geography"))
insert_bst(r, Node("Zoology"))

print_bst(r)

