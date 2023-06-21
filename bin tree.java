public class Main {
	public static void main(String[] args)
	{
		BinaryTree tree = new BinaryTree();
		tree.add(8);
		tree.add(4);
		tree.add(6);
		tree.add(2);
		tree.add(3);
		tree.add(7);
		tree.add(10);
		System.out.println(tree.getRoot().getValue());
		System.out.println(tree.getRoot().getLeft().getValue());
		System.out.println(tree.getRoot().getRight().getValue());
		System.out.println(tree.getRoot().getLeft().getLeft().getValue());
		System.out.println(tree.getRoot().getLeft().getRight().getValue());
		System.out.println(tree.getRoot().getLeft().getLeft().getRight().getValue());
		System.out.println(tree.getRoot().getLeft().getRight().getRight().getValue());
		System.out.println("");
		System.out.println(tree.findNode(7).getValue());
		System.out.println(tree.findNode(9));
		System.out.println("");
		tree.remove(3);
		tree.remove(4);
		System.out.println(tree.getRoot().getValue());
		System.out.println(tree.getRoot().getLeft().getValue());
		System.out.println(tree.getRoot().getRight().getValue());
		System.out.println(tree.getRoot().getLeft().getLeft().getValue());
		System.out.println(tree.getRoot().getLeft().getRight().getValue());
	}
}

class BinaryTreeNode<TValue extends Comparable> //класс одного элемента дерева
{
	private TValue value;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
    public BinaryTreeNode(TValue value) // запись значения
    {
        this.value = value;
    }
    public TValue getValue() // получение значения
    {
    	return value;
    }
    public void setLeft(BinaryTreeNode node) // присваивание левого ребёнка
    {
    	left = node;
    }
    public BinaryTreeNode getLeft() // получение левого ребёнка
    {
    	return left;
    }
    public void setRight(BinaryTreeNode node) // присваивание правого ребёнка
    {
    	right = node;
    }
    public BinaryTreeNode getRight() // получение правого ребёнка
    {
    	return right;
    }
}

class BinaryTree<TValue extends Comparable> // класс дерева
{
	private BinaryTreeNode<TValue> root;
	
	public void add(TValue value) // добавление корневого элемента
    {
        if (root == null)
        {
        	root = new BinaryTreeNode(value);
        }
        else
        {
        	addTo(root, value);
        }
    }
    
    private void addTo(BinaryTreeNode node, TValue value) // присваивание значения левого или правого ребёнка, учитывая их заполненность
    {
    	if (value.compareTo(node.getValue()) < 0)
    	{
    		if (node.getLeft() == null)
    		{
    			node.setLeft(new BinaryTreeNode(value));
    		}
    		else
    		{
    			addTo(node.getLeft(), value);
    		}
    	}
    	else if (value.compareTo(node.getValue()) > 0)
    	{
    		if (node.getRight() == null)
    		{
    			node.setRight(new BinaryTreeNode(value));
    		}
    		else
    		{
    			addTo(node.getRight(), value);
    		}
    	}
    }
    public BinaryTreeNode getRoot() // получение значения корня
    {
    	return root;
    }
    public BinaryTreeNode findNode(TValue value) // нахождение определённого значения из дерева
    {
    	NodeWithParent maybeResult = findNodeWithParentIn(root, value, null);
    	if (maybeResult == null)
    	{
    		return null;
    	}
    	else
    	{
    		return maybeResult.getNode();
    	}
    }
    private NodeWithParent findNodeWithParentIn(BinaryTreeNode node, TValue value, BinaryTreeNode parent) // нахождение определённого значения + нахождение его родителя
    {
    	if (node == null)
    	{
    		return null;
    	}
    	else if (value.compareTo(node.getValue()) == 0)
    	{
    		return new NodeWithParent(node, parent);
    	}
    	else if (value.compareTo(node.getValue()) < 0)
    	{
    		return findNodeWithParentIn(node.getLeft(), value, node);
    	}
    	else 
    	{
    		return findNodeWithParentIn(node.getRight(), value, node);
    	}
    }
    private NodeWithParent findNodeWithParent(TValue value) // 
    {
    	return findNodeWithParentIn(root, value, null);
    }
    public Boolean remove(TValue value) // удаление определённого значения из дерева
	{
    	NodeWithParent nodeWithParent = findNodeWithParent(value);
    	if (nodeWithParent == null)
    	{
        	return false;
    	}
    	BinaryTreeNode current = nodeWithParent.getNode();
    	BinaryTreeNode parent = nodeWithParent.getParent();
    	if (current.getRight() == null)
    	{
        	if (parent == null)
        	{
        		root = current.getLeft();
        	}
        	else
        	{
            	int result = parent.getValue().compareTo(current.getValue());
            	if (result > 0)
            	{
                	parent.setLeft(current.getLeft());
            	}
            	else if (result < 0)
            	{
            		parent.setRight(current.getLeft());
            	}	
        	}
    	}
        else if (current.getRight().getLeft() == null)
        {
        	current.getRight().setLeft(current.getLeft());
            if (parent == null)
            {
                root = current.getRight();
            }
            else
            {
                int result = parent.getValue().compareTo(current.getValue());
                if (result > 0)
            	{
                	parent.setLeft(current.getRight());
            	}
            	else if (result < 0)
            	{
            		parent.setRight(current.getRight());
            	}
    		}
		}
        else
        {
            BinaryTreeNode leftmost = current.getRight().getLeft();
            BinaryTreeNode leftmostParent = current.getRight();
            while (leftmost.getLeft() != null)
            {
                leftmostParent = leftmost;
                leftmost = leftmost.getLeft();
            }
            leftmostParent.setLeft(leftmost.getRight());
            leftmost.setLeft(current.getLeft());
            leftmost.setRight(current.getRight());
            if (parent == null)
            {
                root = leftmost;
            }
            else
            {
                int result = parent.getValue().compareTo(current.getValue());
                if (result > 0)
            	{
                	parent.setLeft(leftmost);
            	}
            	else if (result < 0)
            	{
                	parent.setRight(leftmost);
            	}
        	}
    	}
		return true;
	}
}

class NodeWithParent // класс значения + его родитель
{
	private BinaryTreeNode node;
	private BinaryTreeNode parent;
	public NodeWithParent(BinaryTreeNode node, BinaryTreeNode parent)
	{
		this.node = node;
		this.parent = parent;
	}
	public BinaryTreeNode getNode()
	{
		return node;
	}
	public BinaryTreeNode getParent()
	{
		return parent;
	}
}