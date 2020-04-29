package tree

import "fmt"

/**
定义二叉树的节点
 */
type Node struct {
	value int
	left *Node
	right *Node
}

/**
功能：打印节点的值
参数：nil
返回值：nil
 */
func (node *Node)Print() {
	fmt.Print("%d ", node.value)
}

/**
功能：设置节点的值
参数：节点的值
返回值：nil
 */
func (node *Node) SetValue(value int) {
	node.value = value
}

/**
功能：创建节点
参数：节点的值
返回值：nil
 */
func CreateNode(value int) *Node {
	return &Node{value, nil, nil}
}

/**
功能：查找节点，利用递归进行查找
参数：根节点，查找的值
返回值：查找值所在节点
 */
func (node *Node)FindNode(n *Node, x int) *Node {
	if n == nil {
		return nil
	} else if n.value == x {
		return n
	} else {
		p := node.FindNode(n.left, x)
		if p != nil {
			return p
		}
		return node.FindNode(n.right, x)
	}
}

func (node *Node) GetTreeHeight(n *Node) int {
	if n == nil {
		return 0
	} else {
		lHeight := node.GetTreeHeight(n.left)
		rHeight := node.GetTreeHeight(n.right)
		if lHeight > rHeight {
			return lHeight + 1
		} else {
			return rHeight + 1
		}
	}
}

/**
功能：递归前序遍历二叉树
参数：根节点
返回值：nil
 */
func (node *Node)PreOrder(n *Node) {
	if n != nil {
		fmt.Print("%d ", n.value)
		n.PreOrder(n.left)
		n.PreOrder(n.right)
	}
}

/**
功能：递归中序遍历二叉树
参数：根节点
返回值：nil
 */
func (node *Node)InOrder(n *Node) {
	if n != nil {
		n.InOrder(n.left)
		fmt.Print("%d ", n.value)
	}
}

func (node *Node)PostOrder(n *Node) {
	
}

func main() {
	
}
