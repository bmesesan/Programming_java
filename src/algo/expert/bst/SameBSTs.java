package algo.expert.bst;

import java.util.*;

public class SameBSTs {
	public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
		// Write your code here.
		if (arrayOne.size() != arrayTwo.size()) {
			return false;
		}
		
		if (arrayOne.size() < 1 && arrayTwo.size() < 1) {
			return true;
		}
		
		if (arrayOne.get(0) != arrayTwo.get(0)) {
			return false;
		}
		
		if (arrayOne.size() == 1 && arrayTwo.size() == 1) {
			return true;
		}
		
		ArrayList<Integer> leftArrayOne = new ArrayList<Integer>();
		ArrayList<Integer> leftArrayTwo = new ArrayList<Integer>();
		
		// Build the Left sub-arrays
		for (ListIterator<Integer> li = arrayOne.listIterator(1); li.hasNext();) {
			int value = li.next();
			if (value < arrayOne.get(0)) {
				leftArrayOne.add(value);
			}
		}
		
		for (ListIterator<Integer> li = arrayTwo.listIterator(1); li.hasNext();) {
			int value = li.next();
			if (value < arrayTwo.get(0)) {
				leftArrayTwo.add(value);
			}
		}
		
		boolean isLeftOk = sameBsts(leftArrayOne, leftArrayTwo);
		
		ArrayList<Integer> rightArrayOne = new ArrayList<Integer>();
		ArrayList<Integer> rightArrayTwo = new ArrayList<Integer>();
		
		// Build the Right sub-arrays
		for (ListIterator<Integer> li = arrayOne.listIterator(1); li.hasNext();) {
			int value = li.next();
			if (value >= arrayOne.get(0)) {
				rightArrayOne.add(value);
			}
		}
		
		for (ListIterator<Integer> li = arrayTwo.listIterator(1); li.hasNext();) {
			int value = li.next();
			if (value >= arrayTwo.get(0)) {
				rightArrayTwo.add(value);
			}
		}
		
		boolean isRightOk = sameBsts(rightArrayOne, rightArrayTwo);
		
	    return (isLeftOk & isRightOk);
	}
	
	public static void main(String[] args) {
		List<Integer> arrayOne =
	      new ArrayList<Integer>(Arrays.asList(10, 15, 8, 12, 94, 81, 5, 2, 11));
	    List<Integer> arrayTwo =
	      new ArrayList<Integer>(Arrays.asList(10, 8, 5, 15, 2, 12, 11, 94, 81));
	    boolean isSame = sameBsts(arrayOne, arrayTwo);
	    System.out.println(isSame);
	}
}
