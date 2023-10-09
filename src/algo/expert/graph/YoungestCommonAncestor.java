package algo.expert.graph;

import java.util.*;

public class YoungestCommonAncestor {
	public static LinkedList<AncestralTree> buildAncestralList(AncestralTree startNode) {
		LinkedList<AncestralTree> ancestorList = new LinkedList<>();
		AncestralTree current = startNode;
		
		while (current != null) {
			ancestorList.add(current);
			current = current.ancestor;
		}
		
		return ancestorList;
	}
	
	public static AncestralTree getYoungestCommonAncestor(AncestralTree topAncestor,
														  AncestralTree descendantOne,
														  AncestralTree descendantTwo) {
		LinkedList<AncestralTree> ancestorsOne = buildAncestralList(descendantOne);
		LinkedList<AncestralTree> ancestorsTwo = buildAncestralList(descendantTwo);
		
		for (AncestralTree iterOne: ancestorsOne) {
			for (AncestralTree iterTwo: ancestorsTwo) { 
				if (iterOne == iterTwo) {
					return iterOne;
				}
			}
		}
		
		return topAncestor;
	}

	static class AncestralTree {
		public char name;
		public AncestralTree ancestor;

		AncestralTree(char name) {
			this.name = name;
			this.ancestor = null;
		}

		// This method is for testing only.
		void addAsAncestor(AncestralTree[] descendants) {
			for (AncestralTree descendant : descendants) {
				descendant.ancestor = this;
			}
		}
	}
}
