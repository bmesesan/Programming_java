package algo.expert.bst;
import java.util.*;

class BSTConstruction {
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }

        public BST insert(int value) {
			// Write your code here.
			// Do not edit the return statement of this method.
        	boolean posFound = false;
        	BST currBSTRef = this;
  
        	
        	while (!posFound) {
        		if (currBSTRef == null) {
        			posFound = true;
        			currBSTRef = new BST(value);
        		} else {
        			if (value >= currBSTRef.value) {
        				currBSTRef = currBSTRef.right;
        			} else {
        				currBSTRef = currBSTRef.left;
        			}
        		}
        	}
        	
			return currBSTRef;
		}

        public boolean contains(int value) {
            // Write your code here.
            return false;
        }

        public BST remove(int value) {
            // Write your code here.
            // Do not edit the return statement of this method.
            return this;
        }
    }
}
