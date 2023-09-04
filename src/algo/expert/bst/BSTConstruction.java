package algo.expert.bst;
import java.util.*;

import algo.expert.bst.FindClosestValueInBST.BST;

class BSTConstruction {
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }

        public BST insert(int value) {
        	BST currBSTRef = this;
  
        	while (currBSTRef != null) {
    			if (value >= currBSTRef.value) {
    				if (currBSTRef.right == null) {
    					currBSTRef.right = new BST(value);
    					return this;
    				}
    				currBSTRef = currBSTRef.right;
    			} else {
    				if (currBSTRef.left == null) {
    					currBSTRef.left = new BST(value);
    					return this;
    				}
    				currBSTRef = currBSTRef.left;
    				
    			}
        	}
        	
			return this;
		}

        public boolean contains(int value) {
        	BST currBST = this;
        	
        	while (currBST != null) {        		
        		if (value == currBST.value) {
        			return true;
        		}
        		else if (value > currBST.value) {
        			currBST = currBST.right;
        		} else {
        			currBST = currBST.left;
        		}
        	}
        	
            return false;
        }
        
        class BSTParrentChild {
        	public BST parrent;
        	public BST child;
        	public int direction; // -1 left; 1 right
        	
        	public BSTParrentChild(BST parrent, BST child, int direction) {
        		this.parrent = parrent;
        		this.child = child;
        		this.direction = direction;
        	}
        }
        
        public BSTParrentChild getRef(int value) {
        	BST parrent = null;
        	BST currBST = this;
        	int direction = 0;
        	
        	while (currBST != null) {        		
        		if (value == currBST.value) {
        			return new BSTParrentChild(parrent, currBST, direction);
        		}
        		else if (value > currBST.value) {
        			parrent = currBST;
        			currBST = currBST.right;
        			direction = 1;
        		} else {
        			parrent = currBST;
        			currBST = currBST.left;
        			direction = -1;
        		}
        	}
        	
            return new BSTParrentChild(parrent, currBST, direction);
        }
        
        
        public BST remove(int value) {
        	// First, get a reference to the node to be removed
        	BSTParrentChild parrentChild = getRef(value);
        	BST nodeToBeRemoved = parrentChild.child;
        	
        	if (nodeToBeRemoved == this && this.left == null && this.right == null) {
        		return this;
        	}
        	
        	// If the node to be removed is a leaf, then set it to null and update the parent
        	if (nodeToBeRemoved.left == null && nodeToBeRemoved.right == null) {
        		if (parrentChild.direction == 1) {
        			parrentChild.parrent.right = null;
        		} else if (parrentChild.direction == -1) {
        			parrentChild.parrent.left = null;
        		}
        		
        		return this;
        	}
        	
        	// Check which value to take the place of the node
        	// 1. Check if the node has a left child
        	BST leftCandidate = null;
        	BST leftCandidateParrent = null;
        	if (nodeToBeRemoved.left != null) {
        		// If the node to be removed has a left child,
        		// Then the left candidate the is lowest right descendant of the left child
        		leftCandidate = nodeToBeRemoved.left;
        		while (leftCandidate.right != null) {
        			leftCandidateParrent = leftCandidate;
        			leftCandidate = leftCandidate.right;
        		}
        	}
        	
        	// 2. Check if the node has a right child
        	BST rightCandidate = null;
        	BST rightCandidateParrent = null;
        	if (nodeToBeRemoved.right != null) {
        		// If the node to be removed has a right child,
        		// Then the right candidate is lowest left descendant of the right child
        		rightCandidate = nodeToBeRemoved.right;
        		while (rightCandidate.left != null) {
        			rightCandidateParrent = rightCandidate;
        			rightCandidate = rightCandidate.left;
        		}
        	}
        	
        	// Check which candidate to choose
        	// In the event in which both are not null, then choose the one which has the minimum
        	// absolute difference between the node to be removed and the candidate.
        	BST finalCandidate = null;
        	if (leftCandidate == null) {
        		finalCandidate = rightCandidate;
        		if (rightCandidateParrent != null) {
        			rightCandidateParrent.left = null;
        		} else {
        			nodeToBeRemoved.right = finalCandidate.right;
        		}
        	} else if (rightCandidate == null) {
        		finalCandidate = leftCandidate;
        		if (leftCandidateParrent != null) {
        			leftCandidateParrent.right = null;
        		} else {
        			nodeToBeRemoved.left = finalCandidate.left;
        		}
        	} else {
        		int absDiffLeft = Math.abs(nodeToBeRemoved.value - leftCandidate.value);
        		int absDiffRight = Math.abs(nodeToBeRemoved.value - rightCandidate.value);
        		if (absDiffLeft < absDiffRight) {
        			finalCandidate = leftCandidate;
        			if (leftCandidateParrent != null) {
            			leftCandidateParrent.right = null;
            		} else {
            			nodeToBeRemoved.left = finalCandidate.left;
            		}
        		} else {
        			finalCandidate = rightCandidate;
        			if (rightCandidateParrent != null) {
            			rightCandidateParrent.left = null;
            		} else {
            			nodeToBeRemoved.right = finalCandidate.right;
            		}
        		}
        	}
        	
        	nodeToBeRemoved.value = finalCandidate.value;
        	
    		return this;
        }
        
        public static void main(String[] args) {
    	    System.out.println("Hello Eclipse!");
    	    
//    	    BST bst = new BST(10);
//    	    bst.left = new BST(5);
//    	    bst.left.left = new BST(2);
//    	    bst.left.left.left = new BST(1);
//    	    bst.left.right = new BST(5);
//    	    
//    	    bst.right = new BST(15);
//    	    bst.right.left = new BST(13);
//    	    bst.right.left.right = new BST(14);
//    	    bst.right.right = new BST(22);
//    	    
//    	    bst = bst.insert(12);
//    	    bst = bst.remove(10);
//    	    boolean result = bst.contains(15);

    	    BST bst = new BST(10);
    	    bst.insert(5);
    	    bst.insert(15);
    	    bst.remove(10);
    	    bst.remove(5);
    	    bst.remove(15);
    	    
//    	    System.out.println("Result is: " + result);
    	}
    }
}
