public class SortsAndSearchLab {

    private static void selectionSort(Comparable arr[]) {
        for(int i = 0; i < arr.length - 1; i++){
	    for(int j = i + 1; j < arr.length; j++){
			
	    }
	}
    }
    
    private static void insertionSort(Comparable arr[]) {
	for(int i = 1; i < arr.length; i++){
            for (int j = i - 1; j >= 0; j--){
		int res = arr[j].compareTo(arr[j+1]);
		if (res > 0){
		    swap(arr, j, j+1);
		} else {
		    break;
		}
	    }
	}
    }

    private static void swap(Object arr[], int idx1, int idx2){
    	Object temp = arr[idx1];
	arr[idx1] = arr[idx2];
	arr[idx2] = temp;
    }

    public static int indexAt(Comparable arr[], Comparable toFind){
    	int low = 0;
	int high = arr.length - 1;
	int mid;
	
	while(low <= high) {
	    mid = (low + high) / 2;
	    int res = arr[mid].compareTo(toFind);
	    if(res < 0){
	    	low = mid + 1;
	    } else if(res > 0) {
	    	high = mid - 1;
	    } else {
	    	return mid;
	    }
	}
	
	return -1;
    }
    
    private static String getAlpha() {
        StringBuffer sb = new StringBuffer();
        
        for(char c = 'a'; c <= 'z'; c++) {
            sb.append(c);
        }
        
        return sb.toString();
    }
    
    private static String[] createRandomStringArray(int length, int minSize, int maxSize) {
        char alpha[] = getAlpha().toCharArray();
        String res[] = new String[length];
        for(int i = 0; i < res.length; i++) {
            StringBuffer sb = new StringBuffer();
            //Determine size of next String by taking the minSize and maxSize ranges.
            int size = (int)(Math.random() * (maxSize - minSize + 1) + minSize);
            //Creates a new random string
            for(int j = 0; j < size; j++) {
                int ridx = (int)(Math.random() * alpha.length);
                sb.append(alpha[ridx]);
            }
            res[i] = sb.toString();
        }
        
        return res;
    }
    
    private static Integer[] createRandomIntegerArray(int length, int min, int max) {
        Integer res[] = new Integer[length];
        for(int i = 0; i < length; i++) {
            res[i] = Integer.valueOf((int)(Math.random() * (max - min + 1) + min));
        }
        return res;
    }
    
    private static void printArray(Object objects[]) {
        for(int i = 0; i < objects.length; i++) {
            System.out.print(objects[i]);
            if(i < objects.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        final int ARRAY_LENGTH = 100;
        
        System.out.println("-----------Selection Sort on String Array---------");
        String randStrings1[] = createRandomStringArray(ARRAY_LENGTH, 1, 1);
        printArray(randStrings1);
        selectionSort(randStrings1);
        printArray(randStrings1);
        

        System.out.println("\n-----------Selection Sort on Integer Arrays---------");
        Integer randInts1[] = createRandomIntegerArray(ARRAY_LENGTH, -100, 100);
        printArray(randInts1);
        selectionSort(randInts1);
        printArray(randInts1);
        
        System.out.println("\n-----------Insertion Sort on String Array---------");
        String randStrings2[] = createRandomStringArray(ARRAY_LENGTH, 1, 1);
        printArray(randStrings2);
        insertionSort(randStrings2);
        printArray(randStrings2);
        

        System.out.println("\n-----------Insertion Sort on Integer Arrays---------");
        Integer randInts2[] = createRandomIntegerArray(ARRAY_LENGTH, -100, 100);
        printArray(randInts2);
        insertionSort(randInts2);
        printArray(randInts2);

	System.out.println(indexAt(randInts2, Integer.valueOf(42)));
    }
}
