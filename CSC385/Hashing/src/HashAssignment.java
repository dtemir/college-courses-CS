import java.util.*;

public class HashAssignment {

    public static void main(String args[]) {
    	// Test 1: Integer Lists
        List<List<Integer>> intLists = new LinkedList<>();

        intLists.add(new ArrayList<Integer>(Arrays.asList(3, 4, 9, 8, 12, 15, 7, 13)));
        intLists.add(new ArrayList<Integer>(Arrays.asList(15, 24, 50, 12, 3, 9)));
        intLists.add(new ArrayList<Integer>(Arrays.asList(78, 65, 24, 13, 9, 3, 12)));
        intLists.add(new ArrayList<Integer>(Arrays.asList(15, 78, 14, 3, 2, 9, 44, 12)));

        List<Integer> intResult = findCommonElements(intLists);

        System.out.println("Common elements of the integer list");
        System.out.println(intResult + "\n");

        // Test 2: String Lists
        List<List<String>> stringLists = new LinkedList<>();

        stringLists.add(new ArrayList<String>(Arrays.asList("a", "b", "d", "c", "h", "e")));
        stringLists.add(new ArrayList<String>(Arrays.asList("g", "b", "e", "j", "u", "z", "h", "d")));
        stringLists.add(new ArrayList<String>(Arrays.asList("y", "p", "b", "d")));

        List<String> stringResult = findCommonElements(stringLists);

        System.out.println("Common elements of the string list");
        System.out.println(stringResult + "\n");
        
        // Test 3: One Integer List
        List<List<Integer>> singleList = new LinkedList<>();
        
        singleList.add(new ArrayList<Integer>(Arrays.asList(3, 4, 9, 8, 12, 15, 7, 13)));
        
        List<Integer> singleResult = findCommonElements(singleList);
        
        System.out.println("Common elements of the single list");
        System.out.println(singleResult + "\n");
        
        // Test 4: No Common Elements
        List<List<Integer>> noCommonsList = new LinkedList<>();

        noCommonsList.add(new ArrayList<Integer>(Arrays.asList(4, 8, 15, 7, 13)));
        noCommonsList.add(new ArrayList<Integer>(Arrays.asList(15, 24, 50, 12, 3, 9)));
        noCommonsList.add(new ArrayList<Integer>(Arrays.asList(78, 65, 24, 13, 9, 3, 12)));
        noCommonsList.add(new ArrayList<Integer>(Arrays.asList(15, 78, 14, 3, 2, 9, 44, 12)));

        List<Integer> noCommonsResult = findCommonElements(noCommonsList);

        System.out.println("No common elements in the integer list");
        System.out.println(noCommonsResult + "\n");
        
        // Test 5: Empty Collection
        
        List<List<Integer>> emptyList = new LinkedList<>();
        
        List<Integer> emptyResult = findCommonElements(emptyList);
        
        System.out.println("Empty collection");
        System.out.println(emptyResult + "\n");
        
    }

    /*
     * Returns a list of only the elements found in all the other lists
     * 
     * We're using a HashSet class to record values that are in common in all lists
     * Using two HashSets lets us filter-out repeated values as we go through all lists
     * 
     * Complexity: O(kn), where k is the number of lists and n is the number of elements in a list
     */
    public static <T> List<T> findCommonElements(List<List<T>> collections) {
    	
    	if (collections.size() == 0) { // base case: no lists at all
    		return new ArrayList<>();
    	}
    	
        HashSet<T> elementsSeen = new HashSet<>(); // a set to remember unique values
         
        List<T> firstList = collections.get(0); // populate the set with elements of the first list
        for (int i = 0; i < firstList.size(); i++) { // first list
        	elementsSeen.add(firstList.get(i));
        }
        
        for (int i = 1; i < collections.size(); i++) { // other lists
        	List<T> elementsList = collections.get(i);
        	
        	// a set to store only repeated values that the current list has
        	HashSet<T> repeatedElements = new HashSet<>();
        	for (int j = 0; j < elementsList.size(); j++) { // elements of the list
        		T element = elementsList.get(j);
        		// if the current list has any elements that are in the elements we've already seen,
        		// add them to the new set
        		if (elementsSeen.contains(element)) { 
        			repeatedElements.add(element);
        		}
        	}
        	
        	// update the list to only have values that are repeated in all lists
        	elementsSeen = repeatedElements;
        }
        
        // copy down the elements of the set to a list to return
        List<T> toReturn = new ArrayList<>();
        for (T n : elementsSeen) {
        	toReturn.add(n);
        }
        
    	return toReturn;
    }
}
