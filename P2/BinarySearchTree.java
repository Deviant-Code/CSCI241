/*
	BinarySearchTree.java
	
	Demonstration of instance methods to implement BinarySearchTree.
	
	Starting point for CSCI 241 Assignment 2, Winter 2018
	
	Counts the number of occurrences of words from a text file
	specified on the command line
	
	
	James Hearne, February, 2018
	
*/


import java.util.*;
import java.io.*;

public class BinarySearchTree {

	// inner class for tree nodes
	private class TreeNode {
	
		String data;	// the word stored at this node
		int count;		// occurrence count of this word
		TreeNode left;
		TreeNode right;
		TreeNode parent;
      int height; //holds the height of each node

		// Constructor
		public TreeNode(String data, TreeNode parent) {
			this.data = data;
			this.count = 1;
			this.left = null;
			this.right = null;
			this.parent = parent;
         this.height = 1; // initializing height to 1
		}
	}
	
	// -----------end of inner class TreeNode---------------
	
	// the root of the BinarySearchTree
	private TreeNode root = null;

	// inner method for insertion of a new word
	// or incrementing count of an existing word
	private TreeNode insert(TreeNode tree, String word) {
	
		TreeNode parent = null;
		TreeNode curr = tree;
		
		// find location for this word in the tree
		while (curr != null) {
		
			parent = curr;
		
			// found the word already in the tree, increment its count
			if (curr.data.compareToIgnoreCase(word) == 0) {
				curr.count++;
				return tree;
			}
			
			// look for the word in either left or right subtree
			if (word.compareToIgnoreCase(curr.data) < 0)
				curr = curr.left;
			else
				curr = curr.right;
		}
		
		// this word is not in the tree so create a new tree node
		TreeNode newnode = new TreeNode(word, parent);
		
		// added to an empty tree
		if (parent == null)
			return newnode;
		
		// set the parent's left or right to the new node
		if (word.compareToIgnoreCase(parent.data) < 0)
			parent.left = newnode;
		else
			parent.right = newnode;
			
      fixHeight(newnode);
      tree = checkAvlBalance(newnode);
      
		return tree;
	}
   
   private TreeNode checkAvlBalance(TreeNode curr){
   
      if(curr != null){
         //check if there is a off balance factor between child nodes  
         if(curr.left == null && curr.right == null){
            checkAvlBalance(curr.parent);
         }else if(curr.left == null){
            if(curr.right.height > 1){
               curr =  rebalance(curr);
            } else {
               if(curr.parent != null){
                  checkAvlBalance(curr.parent);
               }
            }
         } else if(curr.right == null){
                
            if(curr.left.height > 1){
               curr = rebalance(curr);
            } else {
               if(curr.parent != null){
                  checkAvlBalance(curr.parent);
               }
            }
         } else if((curr.left.height - curr.right.height) > 1 | (curr.left.height - curr.right.height) < -1){
            curr = rebalance(curr); 
         } else {   
            if(curr.parent != null){
               checkAvlBalance(curr.parent);
            }
         }
      }
     
      
     root = getRoot(curr);
     return root;
     
   }
   
   //returns the root of a tree given any TreeNode in that tree
   private TreeNode getRoot(TreeNode curr){
      while(curr.parent != null){
         curr = curr.parent;
      }
      return curr;
   
   }
   private TreeNode rebalance(TreeNode curr){
      int leftHeight; 
      int rightHeight;
      
      if(curr.left == null){
         leftHeight = 0;
      } else {
         leftHeight = curr.left.height;
      }
      
      if(curr.right == null){
         rightHeight = 0;
      } else {
         rightHeight = curr.right.height;
      }
      
      //EVALUATES A NULL CHILD AS A 0 HEIGHT FOR BOTH CHILDS OF CURR
      if(leftHeight < rightHeight){
         TreeNode yChild = curr.right;
         
         if(yChild.left == null){
            leftHeight = 0;
         } else {
            leftHeight = yChild.left.height;
         }
         
         if(yChild.right == null){
            rightHeight = 0;
         } else {
            rightHeight = yChild.right.height;
         }
         
         if(leftHeight < rightHeight){
            TreeNode xChild = yChild.right;
            //rightright case
            System.out.println("Ran RR Case: " + curr.height);
            return leftRotate(yChild, curr);
         } else { 
            TreeNode xChild = yChild.left;
            //RightLeft case
            rightRotate(yChild, curr);
            return leftRotate(yChild, curr);
         }
      } else {
         TreeNode yChild = curr.left;
         
         //EVALUATES A NULL CHILD AS A ZERO HEIGHT
         if(yChild.left == null){
            leftHeight = 0;
         } else {
            leftHeight = yChild.left.height;
         }
         
         if(yChild.right == null){
            rightHeight = 0;
         } else {
            rightHeight = yChild.right.height;
         }
         
         
         
         if(leftHeight < rightHeight){
            TreeNode xChild = yChild.right;
            //LeftRight Case
            leftRotate(yChild, curr);
            return rightRotate(yChild, curr);
         } else {
            TreeNode xChild = yChild.left;
            //LeftLeft case
            return rightRotate(yChild, curr);
         }
      }
   }
   
   private TreeNode rightRotate(TreeNode yChild, TreeNode curr){
      curr.left = yChild.right;
      yChild.right = curr;
      yChild.parent = curr.parent;
      
      curr.parent = yChild;
      
      if(yChild.parent != null){
         if(yChild.data.compareToIgnoreCase(yChild.parent.data) < 0){
            yChild.parent.left = yChild;
         } else {
            yChild.parent.right = yChild;
         }
      }
      
      curr.height = curr.height -2;
      TreeNode recurseParent = yChild.parent;
      while(recurseParent != null){
         recurseParent.height--;
         recurseParent = recurseParent.parent;
      }
         
      return yChild;
      
   }
   
   private TreeNode leftRotate(TreeNode yChild, TreeNode curr){
      curr.right = yChild.left;
      yChild.left = curr;
      yChild.parent = curr.parent;
      curr.parent = yChild;
      if(yChild.parent != null){
         if(yChild.data.compareToIgnoreCase(yChild.parent.data) < 0){
            yChild.parent.left = yChild;
         } else {
            yChild.parent.right = yChild;
         }
      }
      
      curr.height = curr.height -2;
      
      TreeNode recurseParent = yChild.parent;
      while(recurseParent != null){
         recurseParent.height--;
         recurseParent = recurseParent.parent;
      }
      return yChild;
   
   }
	
   //fixes height of parent nodes after an insert of new node
   private void fixHeight(TreeNode curr){
      if(curr.parent != null){
         if(curr.height == curr.parent.height){
            curr.parent.height++;
            fixHeight(curr.parent);
         }
      }
      
   }
  
	// public wrapper method for word insertion or count increment
	public void insert(String word) {
      if(exist(root, word)){
         //System.out.println("This word exists");
         root = insert(root, word);
      } else {
		   root = insert(root, word);
         //root = rebalance(root);
      }
      
	}
   
   
   //Checks if word exists in tree.
   private boolean exist(TreeNode curr, String word){
      boolean exist = false;
      if(curr != null){
         if(curr.data.equalsIgnoreCase(word)){
            return true;
         } 
         return (exist(curr.left, word) | exist(curr.right, word));   
      } 
      return exist;
   
   }
   

	// public wrapper for dump method
	public void dump() {	
		TreeNode curr = root;
      helpDump(curr);
	}
   
   //helper method for dump, prints data for each treeNode
   private void helpDump(TreeNode curr){
      if(curr != null){
         System.out.println(curr.data+" ,"+curr.count+" ,"+getParent(curr)+" ,"+getLeftChild(curr)+" ,"+getRightChild(curr)+" ,"+curr.height);
         helpDump(curr.left);
         helpDump(curr.right);
      }
   }
   
   //Provides parent node for dump and if null returns *
   private String getParent(TreeNode curr){
      if(curr.parent == null){
         return "*";
      } else {
         return curr.parent.data;
      }
   }
   
   //Returns left child of node or * if null
   private String getLeftChild(TreeNode curr){
      if(curr.left == null){
         return "*";
      }else {
         return curr.left.data;
      }
   
   }
   
   //Returns right child of node or * if null
   private String getRightChild(TreeNode curr){
      if(curr.right == null){
         return "*";
      }else {
         return curr.right.data;
      }
   }


	// test the BinarySearchTree
	public static void main(String[] args) {
	
		// should be able to read input from a file
		// specified in the first command-line argument
		BufferedReader in;
		
		try {
			in = new BufferedReader(new FileReader(args[0]));
		}
		catch (Exception e) {
			System.out.println("Cannot open file " + args[0]);
			return;
		}
		
		BinarySearchTree tree = new BinarySearchTree();
		String delimiters = " \t,;.!@#$%^&*()_+-=<>?[]{}:\"'";
				
		// read each line of the file
		String line = "";
		try {
			line = in.readLine();
		}
		catch (Exception e) {
			System.out.println("I/O error: " + e);
		}
		
		while (line != null) {
			
			// get each word from the line add it to the tree
			StringTokenizer tokenizer = new StringTokenizer(line, delimiters);
			while (tokenizer.hasMoreTokens()) 
				tree.insert(tokenizer.nextToken());
					
			try {
				line = in.readLine();
				}
			catch (Exception e) {
				System.out.println("I/O error: " + e);
			}	
		}
		
		tree.dump();
	}
}
