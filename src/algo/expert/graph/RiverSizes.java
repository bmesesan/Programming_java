package algo.expert.graph;

import java.util.*;

public class RiverSizes {
	public static class MySet {
		public int row;
		public int col;
		
		MySet (int row, int col) {
			this.row = row;
			this.col = col;
		}
	};
	
	public static class MyWrapper {
		public int value = 1;
	}
	
	public static boolean isVisited(ArrayList<MySet> breadCrumb, MySet elem) {
		for (MySet visitedElem: breadCrumb) {
			if (elem.col == visitedElem.col && elem.row == visitedElem.row) {
				return true;
			}
		}
		return false;
	}
	
	public static List<Integer> riverSizes(int[][] matrix) {
		// Write your code here.
	    ArrayList<Integer> result = new ArrayList<>();
	    ArrayList<MySet> breadCrumbMap = new ArrayList<>();

	    for (int i = 0; i < matrix.length; i++) {
	    	for (int j = 0; j < matrix[0].length; j++) {
	    		MySet setToTest = new MySet(i, j);
	    		if (matrix[i][j] == 1 && !isVisited(breadCrumbMap, setToTest)) {
	    			MyWrapper riverSize = new MyWrapper();
	    			breadCrumbMap.add(setToTest);
	    			searchRunnable(matrix, i, j, riverSize, breadCrumbMap);
	    			result.add(riverSize.value);
    			}
	    	 }
	    }
	    
	    return result;
	}

	public static void searchRunnable(int[][] matrix, int i, int j, MyWrapper riverSize, ArrayList<MySet> breadCrumbMap) {
		// Check Up
		MySet setToTestUp = new MySet(i - 1, j);
		if (i - 1 >= 0 && matrix[i - 1][j] == 1 && !isVisited(breadCrumbMap, setToTestUp)) {
			riverSize.value++;
			breadCrumbMap.add(setToTestUp);
			searchRunnable(matrix, i - 1, j, riverSize, breadCrumbMap);
		}
		
		// Check Down
		MySet setToTestDown = new MySet(i + 1, j);
		if (i + 1 < matrix.length && matrix[i + 1][j] == 1 && !isVisited(breadCrumbMap, setToTestDown)) {
			riverSize.value++;
			breadCrumbMap.add(setToTestDown);
			searchRunnable(matrix, i + 1, j, riverSize, breadCrumbMap);
		}
		
		// Check Left
		MySet setToTestLeft = new MySet(i, j - 1);
		if (j - 1 >= 0 && matrix[i][j - 1] == 1 && !isVisited(breadCrumbMap, setToTestLeft)) {
			riverSize.value++;
			breadCrumbMap.add(setToTestLeft);
			searchRunnable(matrix, i, j - 1, riverSize, breadCrumbMap);
		}
		
		// Check Right
		MySet setToTestRight = new MySet(i, j + 1);
		if (j + 1 < matrix[0].length && matrix[i][j + 1] == 1 && !isVisited(breadCrumbMap, setToTestRight)) {
			riverSize.value++;
			breadCrumbMap.add(setToTestRight);
			searchRunnable(matrix, i, j + 1, riverSize, breadCrumbMap);
		}
	}
	
	public static void main(String[] args) {
		int[][] input = {
			      {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0}
			    };
	    int[] expected = {1, 2, 2, 2, 5};
	    List<Integer> output = RiverSizes.riverSizes(input);
	    Collections.sort(output);
	    System.out.println(output);
	}
}
