import java.util.Date;

public class CS6088BSheth {

	public static void main(String[] args) {
		Lab8BTMethods one = new Lab8BTMethods();
		

		// create date instance for program output header
		Date date = new Date();

		System.out.println("Name                    -   Sukhada Surendra Sheth        ");
		System.out.println("Class                   -   CS608                         ");			
		System.out.println("Date                    -   "+ date);		
		System.out.println("*********************************************************************************");
		System.out.println("                      Assignment 8B - BinaryTree                                 ");
		System.out.println("*********************************************************************************");
		System.out.println("Assumption - Level / Height of Root is Zero                                      ");
		System.out.println("*********************************************************************************");
		System.out.println();
		
		
		
		one.createBinaryTree();
		System.out.println("Binary Tree traveresed in preorder");
		one.preOrder(one.root);
		System.out.println("\n\nHeight of the Tree = " + one.height(one.root));
		System.out.println("\nBinary Tree Displayed in level order");
		one.displayTree(one.root);
		System.out.println("\n\nNumber of Nodes in the binary tree are " + numberOfNodes(one.root));	
		System.out.println("\nLargest Node in the binary tree is " + largestNode(one.root));
		System.out.println("\nSum of elements in binary tree is " + sumOfElements(one.root));
		System.out.println("\nDoes tree have 50 as one of its node ? " + searchFor(one.root, 50));

		System.out.println("\nLevel number with largest number of nodes " + largestNodeNumLevel(one.root, one));

	}

	static int largestNodeNumLevel(Lab8BTNode root , Lab8BTMethods one) {
		int height = one.height(root);
		int largest = 0, largestNoLvl = 0;
		for(int i = 0 ; i < height ; i++)
		{ 
			int k = numberOfElementsOnLvl(root, i);
			if( k >= largest)
			{
				largestNoLvl = i;
				largest = k;
			}
		}
		
		return largestNoLvl;
	}
	
	static int numberOfElementsOnLvl(Lab8BTNode root, int level) {
		int sum = 0;
		if(root == null)
			return 0;
		if(level == 0 ) 
			return 1;
		else if(level > 0) 
		{
			 sum = numberOfElementsOnLvl(root.left, level - 1 ) + 
			numberOfElementsOnLvl(root.right,level - 1 );
		}
			
		return sum;
		}
	
	static boolean searchFor(Lab8BTNode root, int n) {
		if(root == null)
		{
			return false;
		}
		else
		{
			if(root.element == n)
				return true;
			else
			{
				return searchFor(root.left , n) ||  searchFor(root.right, n);
			}
		}

	}

	static int sumOfElements(Lab8BTNode root) {
		if(root == null)
			return 0;
		else {
			int sum = sumOfElements(root.left) + sumOfElements(root.right) + root.element;

			return sum;
		}
	}

	static int largestNode(Lab8BTNode root) {
		if(root == null)
			return 0; 
		else
		{
			int center = root.element;
			int left = largestNode(root.left);
			int right = largestNode(root.right);

			if(left > center)
				center = left;
			if(right > center)
				center = right;

			return center;
		}
	}

	static int numberOfNodes(Lab8BTNode root) {
		if(root == null)
			return 0;
		else
			return( numberOfNodes(root.right) + numberOfNodes(root.left) + 1);

	}

}
