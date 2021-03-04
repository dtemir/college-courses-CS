package assignment.diffpairs;
/**
 *	Pair class
 *	Stores a Pair of integers
 * 	@author UIS computer science department
 *
 */
public class Pair {
	
	private int first;
	private int second;
	
	public Pair(int first, int last)
	{
		this.first = first;
		this.second= last;
		
	}
	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	public String toString()
	{
		return "(" + this.first + " , " + this.second+ ")";
	}
	
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if(!(other instanceof Pair)) {
			return false;
		}
		Pair otherPair = (Pair)other;
		return this.first == otherPair.first && this.second == otherPair.second;
	}
	

}
