# BinarySearchTree
public class BinarySearchTree 
{
	BSTNode root = null;
	int[] A;
	int i;	

	public void balance()
	{
		if(!isEmpty())
		{
			inorder();
			root = null;
			bisectionInsert(0, A.length - 1);
		}
	}
	public void bisectionInsert(int front, int back)
	{
		if(front <= back)
		{
			int middle = (front + back) / 2;
			insert(A[middle]);
			bisectionInsert(front, middle -1);
			bisectionInsert(middle +1, back);
		}
	}
	public int count()
	{
		if(isEmpty()) return 0;
		else          return root.count();
	}
	public int height()
	{
		if(root == null) return 0;
		else             return root.height();
	}
	public boolean isEmpty()
	{
		return root == null;
	}
	public int min()
	{
		if(isEmpty()) return -1;
		else          return root.min();
	}
	public int max()
	{
		if(isEmpty()) return -1;
		else          return root.max();
	}
	public void printInorder()
	{
		if(!isEmpty()) root.printInorder();
	}
	public void insert(int x)
	{
		if(isEmpty()) root = new BSTNode(x);
		else          root.insert(x);
	}
	public void remove(int value)
	{
		if(value == root.x)
		{
			root = new BSTNode(-1, root ,null);
			root.remove(value);
			root = root.left;
		}
		else    root.remove(value);
	}
	public void inorder()
	{
		A = new int[count()];
		i = 0;
		if(!isEmpty()) root.inorder();
	}
	
    //---------------------------------------------------------------------------------------------//
	
	public class BSTNode
	{
		int x;
		BSTNode left = null;
		BSTNode right = null;

		
		public BSTNode(int x)
		{
			this.x = x;
		}
		public BSTNode(int x, BSTNode left, BSTNode right)
		{
			this.x = x;
			this.left = left;
			this.right = right;
		}
		public void inorder()
		{
			if(left != null) left.inorder();
			A[i] = x;
			i++;
			if(right != null) right.inorder();
		}
		public int search(int value)
		{
			if(value == x) return x;
			if((value < x)||(left != null)) return left.search(value);
			if((value > x)&&(right != null)) return right.search(value);
			return -1;
		}
		public void insert(int value)
		{
			if(value < x)
			{
				if(left != null) left.insert(value);
				else             left = new BSTNode(value);
			}
			if(value > x)
			{
				if(right != null) right.insert(value);
				else              right = new BSTNode(value);
			}
		}
		public int min()
		{
			if(left != null) return left.min();
			else             return x;
		}
		public int max()
		{
			if(right != null) return right.max();
			else              return x;
		}
		public int count()
		{
			int count = 1;
			if(left != null) count += left.count();
			if(right != null) count += right.count();
			return count;
		}
		public int height()
		{
			int leftHeight = 0;
			int rightHeight = 0;
			if(left != null) leftHeight = left.height();
			if(right != null) rightHeight = right.height();
			return max(leftHeight, rightHeight) +1;
		}
		public int max(int a, int b)
		{
			if(a > b) return a;
			else      return b;
		}
		public void printInorder()
		{
			if(left != null) left.printInorder();
			System.out.println(x);
			if(right != null) right.printInorder();
		}
		public BSTNode parentOf(int value)
		{
			if(left != null)
			{
				if(value == left.x) return this;
				if(value < x) return left.parentOf(value);
			}
			if(right != null)
			{
				if(value == right.x) return this;
				if(value > x) return right.parentOf(value);
			}
			return null;
		}
		public void removeLeaf(int value)
		{
			BSTNode parent = parentOf(value);
			if((parent.left != null) && (value == parent.left.x))
			{
				parent.left = null;
			}
			if((parent.right != null) && (value == parent.left.x))
			{
				parent.right = null;
			}
		}
		public void removeLeftSub(int value)
		{
			BSTNode parent = parentOf(value);
			if((parent.left != null) && (value == parent.left.x))
			{
				parent.left = parent.left.left;
			}
			if((parent.right != null) && (value == parent.right.x))
			{
				parent.right = parent.right.left;
			}
		}
		public void removeRightSub(int value)
		{
			BSTNode parent = parentOf(value);
			if((parent.right != null) && (value == parent.right.x))
			{
				parent.right = parent.right.right;
			}
			if((parent.left != null) && (value == parent.left.x))
			{
				parent.left = parent.left.right;
			}
		}
		public void remove(int value)
		{
			BSTNode parent =  parentOf(value);
			BSTNode target;
			
			if((parent.right != null) && (parent.right.x == value))
			{
				target = parent.right;
				
				//if target is a leaf
				if((target.left == null) && (target.right == null))
				{
					parent.right = null;
				}
				
				//if target only has a left subtree
				else if((left != null) && (right  == null))
				{
					parent.right = target.left;
				}
				
				//if target only has a right subtree
				else if((right != null) && (left == null))
				{
					parent.right = target.right;
				}
				
				//if target has two subtrees
				else if((target.left != null) && (target.right != null))
				{
					int max = target.left.max();
					BSTNode parentMax = target.parentOf(max);
					parentMax.right = parentMax.right.left;
					target.x = max;
				}
			}
			else
			{
				target = parent.left;
				
				//if target is a leaf
				if((target.left == null) && (target.right == null))
				{
					parent.left = null;
				}
				
				//if target has a left subtree (it may or may not have a right subtree)
				else if(target.left != null)
				{
					int max = target.left.max();
					BSTNode parentMax = target.parentOf(max);
					parentMax.right = parentMax.right.left;
					target.x = max;
				}
				
				//if target only has a right subtree
				else
				{
					parent.left = target.right;
				}
				
			}			
		}
	}
}
