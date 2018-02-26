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
      //**Added int height
      int height;

		// Constructor
		public TreeNode(String data, TreeNode parent) {
			this.data = data;
			this.count = 1;
			this.left = null;
			this.right = null;
			this.parent = parent;
         this.height = 1;
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
         //*Added increment to height when dropping to root.
			if (word.compareToIgnoreCase(curr.data) < 0){
				curr = curr.left;
			} else {
				curr = curr.right;
         }
		}
		
		// this word is not in the tree so create a new tree node
		TreeNode newnode = new TreeNode(word, parent);
      // cannot
      newnode.height = 0;
		
		// added to an empty tree
		if (parent == null)
			return newnode;
		
		// set the parent's left or right to the new node
		if (word.compareToIgnoreCase(parent.data) < 0)
			parent.left = newnode;
		else
			parent.right = newnode;
			
		return tree;
	}
	
	// public wrapper method for word insertion or count increment
	public void insert(String word) {
		root = insert(root, word);
      root.height = treeHeight(root, 1);
      findNodeHeight(root);
      if(balanceFactor(root) > 1){
         root = rebalance(root);
      }
	}

	// public wrapper for dump method
	public void dump() {	
      TreeNode curr = root;
      helpDump(curr);
   }
   
   
   private int treeHeight(TreeNode curr, int height){
      
      if (curr.left == null && curr.right == null){
         return height;
      } else{
      
         if (curr.left == null && curr.right != null){
            return treeHeight(curr.right, height + 1);
         } else if (curr.left != null && curr.right == null){
            return treeHeight(curr.left, height + 1);
         } else{
            int leftPath = treeHeight(curr.left, height + 1);
            int rightPath = treeHeight(curr.right, height + 1);
            
            if(rightPath> leftPath){
               return rightPath;
            } else { 
               return leftPath;
            }  
         } 
      }
   
   }
   
   
   //Works with helper method to calculate height of all nodes in tree
   private void findNodeHeight(TreeNode root){
      helpNodeHeight(root, root.height+1);
   }
   
   //Calculates heights of all nodes in tree bby preorder traversal
   private void helpNodeHeight(TreeNode curr, int height){
      if(curr != null){
         curr.height = height - 1; 
         helpNodeHeight(curr.left, height -1);
         helpNodeHeight(curr.right, height -1);
      }
   
   }
   
   //returns balance factor from left and right subtrees of root
   private int balanceFactor(TreeNode root){
      return 0;
   
   }
   
   private TreeNode rebalance(TreeNode root){
     
      return root;
   }
   
   //helper method for dump
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
