package algo.expert.graph;

import java.util.*;

public class RemoveIslands {
	public static class MyPair {
		public int row;
		public int col;

		MyPair() {
			;
		}

		MyPair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	boolean isVisited(MyPair pair, ArrayList<MyPair> visitedPairs) {
		for (MyPair visitedPair : visitedPairs) {
			if (visitedPair.col == pair.col && visitedPair.row == pair.row) {
				return true;
			}
		}

		return false;
	}

	boolean isValidIsland(int[][] matrix, MyPair startPair) {
		Queue<MyPair> myQueue = new LinkedList<>();
		ArrayList<MyPair> visitedPairs = new ArrayList<MyPair>(); 

		myQueue.add(startPair);

		while (!myQueue.isEmpty()) {
			MyPair cp = myQueue.poll();
			visitedPairs.add(cp);

			if (cp.row == 0 || cp.col == 0 || cp.row == matrix.length - 1 || cp.col == matrix[0].length - 1) {
				return false;
			}

			// Add up
			MyPair cpUp = new MyPair(cp.row - 1, cp.col);
			if (cpUp.row >= 0 && matrix[cpUp.row][cpUp.col] == 1 && !isVisited(cpUp, visitedPairs)) {
				myQueue.add(cpUp);
			}

			// Add Down
			MyPair cpDown = new MyPair(cp.row + 1, cp.col);
			if (cpDown.row < matrix.length && matrix[cpDown.row][cpDown.col] == 1 && !isVisited(cpDown, visitedPairs)) {
				myQueue.add(cpDown);
			}

			// Add left
			MyPair cpLeft = new MyPair(cp.row, cp.col - 1);
			if (cpLeft.col >= 0 && matrix[cpLeft.row][cpLeft.col] == 1 && !isVisited(cpLeft, visitedPairs)) {
				myQueue.add(cpLeft);
			}

			// Add right
			MyPair cpRight = new MyPair(cp.row, cp.col + 1);
			if (cpRight.col < matrix[0].length && matrix[cpRight.row][cpRight.col] == 1 && !isVisited(cpRight, visitedPairs)) {
				myQueue.add(cpRight);
			}

		}

		return true;
	}

	int[][] removeIsland(int[][] matrix, MyPair startPair) {
		Queue<MyPair> myQueue = new LinkedList<>();

		myQueue.add(startPair);

		while (!myQueue.isEmpty()) {
			MyPair cp = myQueue.poll();
			matrix[cp.row][cp.col] = 0;

			// Add up
			MyPair cpUp = new MyPair(cp.row - 1, cp.col);
			if (cpUp.row >= 0 && matrix[cpUp.row][cpUp.col] == 1) {
				myQueue.add(cpUp);
			}

			// Add Down
			MyPair cpDown = new MyPair(cp.row + 1, cp.col);
			if (cpDown.row < matrix.length && matrix[cpDown.row][cpDown.col] == 1) {
				myQueue.add(cpDown);
			}

			// Add left
			MyPair cpLeft = new MyPair(cp.row, cp.col - 1);
			if (cpLeft.col >= 0 && matrix[cpLeft.row][cpLeft.col] == 1) {
				myQueue.add(cpLeft);
			}

			// Add right
			MyPair cpRight = new MyPair(cp.row, cp.col + 1);
			if (cpRight.col < matrix[0].length && matrix[cpRight.row][cpRight.col] == 1) {
				myQueue.add(cpRight);
			}

		}

		return matrix;
	}

	public int[][] removeIslands(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1 && !(i == 0 || i == matrix.length - 1 || j == 0 || j == matrix[0].length - 1)) {
					MyPair startPair = new MyPair(i, j);
					if (isValidIsland(matrix, startPair)) {
						matrix = removeIsland(matrix, startPair);
					}
				}
			}
		}

		return matrix;
	}

	public static void main(String[] args) {
		int[][] input = new int[][] { { 1, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 1, 1 }, { 0, 0, 1, 0, 1, 0 },
				{ 1, 1, 0, 0, 1, 0 }, { 1, 0, 1, 1, 0, 0 }, { 1, 0, 0, 0, 0, 1 }, };
		int[][] expected = new int[][] { { 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 1, 1 }, { 0, 0, 0, 0, 1, 0 },
				{ 1, 1, 0, 0, 1, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 1 }, };
		int[][] actual = new RemoveIslands().removeIslands(input);
		
		System.out.println(actual);
	}
}
