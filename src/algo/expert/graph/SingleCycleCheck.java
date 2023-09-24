package algo.expert.graph;

import java.util.*;

public class SingleCycleCheck {
	public static boolean hasSingleCycle(int[] array) {
		int startingIdx = 0;
		
		while (startingIdx < array.length) {
			HashMap<Integer, Integer> breadCrumMap = new HashMap<>();
			int currentIdx = startingIdx;
			boolean isOk = true;
			
			while (isOk) {
				if ((currentIdx + array[currentIdx]) >= array.length) {
					currentIdx = (currentIdx + array[currentIdx]) % array.length;
				} else if ((currentIdx + array[currentIdx]) < 0) {
					int negSum = currentIdx + array[currentIdx];
					int absNegSum = Math.abs(negSum);
					int remainderNegSum = absNegSum % array.length;
					
					if (remainderNegSum == 0) {
						currentIdx = 0;
					} else {
						currentIdx = array.length - remainderNegSum;
					}
					
//					currentIdx = (array.length - (Math.abs(currentIdx + array[currentIdx]) % array.length)) - 1;
				} else {
					currentIdx += array[currentIdx];
				}
				
				if (breadCrumMap.containsKey(currentIdx)) {
					isOk = false;
				} else {
					breadCrumMap.put(currentIdx, 1);
				}
			}
			
			if (breadCrumMap.size() == array.length) {
				return true;
			}
			
			startingIdx++;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		boolean result = SingleCycleCheck.hasSingleCycle(new int[] {1, 2, 3, 4, -2, 3, 7, 8, -26});
		
		System.out.println(result);
	}
}
