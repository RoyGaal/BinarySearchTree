import java.util.*;
public class Driver {

	public static void main(String[] args){
		BinarySearchTree t = new BinarySearchTree();
	
		int[] a = new int[40];
		
		System.out.println("Random numbers:");
		for(int i = 0; i < a.length; i++)
		{
			a[i] = new Random().nextInt(100);
			t.insert(a[i]);
			System.out.print(a[i] + ", ");
			
		}
		
		System.out.println();
		System.out.println();
		System.out.print("Count is: " + t.count());
		System.out.println();
		System.out.println();
		
		
		
		System.out.println("BST in order:");
		t.printInorder();
		System.out.println();
		
		System.out.println("max value: " + t.max());
		System.out.println("min value: " + t.min());
		System.out.println();
		
		System.out.println("Height: " + t.height());
		System.out.println();
		
		
		
	}
}
