package algo.expert.graph;

import java.util.*;

public class GraphsBFS {
	// Do not edit the class below except
	// for the breadthFirstSearch method.
	// Feel free to add new properties
	// and methods to the class.
	static class Node {
		String name;
		List<Node> children = new ArrayList<Node>();

		public Node(String name) {
			this.name = name;
		}

		public List<String> breadthFirstSearch(List<String> array) {
			Queue<Node> myQueue = new LinkedList<>();
			
			array.add(this.name);
			for (Node child: this.children) {
				myQueue.add(child);
			}
			
			while (!myQueue.isEmpty()) {
				Node poppedElem = myQueue.poll();
				for (Node child: poppedElem.children) {
					myQueue.add(child);
				}
				
				array.add(poppedElem.name);
			}
			
			return array;
		}

		public Node addChild(String name) {
			Node child = new Node(name);
			children.add(child);
			return this;
		}
	}
	
	public static void main(String[] args) {
		GraphsBFS.Node graph = new GraphsBFS.Node("A");
	    graph.addChild("B").addChild("C").addChild("D");
	    graph.children.get(0).addChild("E").addChild("F");
	    graph.children.get(2).addChild("G").addChild("H");
	    graph.children.get(0).children.get(1).addChild("I").addChild("J");
	    graph.children.get(2).children.get(0).addChild("K");
	    String[] expected = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
	    List<String> inputArray = new ArrayList<String>();
	    inputArray = graph.breadthFirstSearch(inputArray);
	    System.out.println(inputArray);
	}
}
