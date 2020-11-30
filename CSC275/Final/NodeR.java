package Assignments;

public class NodeR {
	private int data;
	private NodeR next;
	
	public NodeR() {
		
	}
	
	public NodeR(int data, NodeR next) {
		this.data = data;
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public NodeR getNext() {
		return next;
	}

	public void setNext(NodeR next) {
		this.next = next;
	}
	
	public static int size(NodeR currNode){
	    int count = 0;
	    while (currNode != null){
	        count++;
	        currNode = currNode.getNext();
	    }
	    return count;
	}

	public static boolean contains(int counter, NodeR currNode) {
		while(currNode != null) {
			if (currNode.getData() == counter) {
				return true;
			}
			currNode = currNode.getNext();
		}
		return false;
		
	}

	
}
