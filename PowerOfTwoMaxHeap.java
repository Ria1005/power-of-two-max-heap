import java.util.Arrays;

public class PowerOfTwoMaxHeap {
    private int[] heap;
    private int size;
    private final int childrenCount;
    
    public PowerOfTwoMaxHeap(int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be >= 0");
        }
        this.childrenCount = 1 << exponent;
        this.heap = new int[16];
        this.size = 0;
    }
    
    public void insert(int value) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
        heap[size] = value;
        size++;
        bubbleUp(size - 1);
    }
    
    public int popMax() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        if (size > 0) {
            bubbleDown(0);
        }
        return max;
    }
    
    public int peekMax() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }
        return heap[0];
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    private void bubbleUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / childrenCount;
            if (heap[index] <= heap[parent]) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }
    
    private void bubbleDown(int index) {
        while (true) {
            int firstChild = index * childrenCount + 1;
            if (firstChild >= size) {
                break;
            }
            
            int lastChild = Math.min(firstChild + childrenCount, size);
            int largestChild = firstChild;
            
            for (int i = firstChild + 1; i < lastChild; i++) {
                if (heap[i] > heap[largestChild]) {
                    largestChild = i;
                }
            }
            
            if (heap[index] >= heap[largestChild]) {
                break;
            }
            swap(index, largestChild);
            index = largestChild;
        }
    }
    
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    // MAIN METHOD - This is what was missing!
    public static void main(String[] args) {
        System.out.println("=== Power of Two Max Heap Test ===\n");
        
        // Test with x=1 (2 children per parent)
        System.out.println("Test 1: x=1 (Binary Heap - 2 children per parent)");
        PowerOfTwoMaxHeap heap1 = new PowerOfTwoMaxHeap(1);
        heap1.insert(10);
        heap1.insert(20);
        heap1.insert(5);
        heap1.insert(30);
        heap1.insert(15);
        heap1.insert(25);
        heap1.insert(35);
        
        System.out.print("Popping all values: ");
        while (!heap1.isEmpty()) {
            System.out.print(heap1.popMax() + " ");
        }
        System.out.println("\n");
        
        // Test with x=2 (4 children per parent)
        System.out.println("Test 2: x=2 (4 children per parent)");
        PowerOfTwoMaxHeap heap2 = new PowerOfTwoMaxHeap(2);
        heap2.insert(50);
        heap2.insert(30);
        heap2.insert(40);
        heap2.insert(10);
        heap2.insert(20);
        heap2.insert(60);
        heap2.insert(45);
        
        System.out.print("Popping all values: ");
        while (!heap2.isEmpty()) {
            System.out.print(heap2.popMax() + " ");
        }
        System.out.println("\n");
        
        // Test with x=3 (8 children per parent)
        System.out.println("Test 3: x=3 (8 children per parent)");
        PowerOfTwoMaxHeap heap3 = new PowerOfTwoMaxHeap(3);
        heap3.insert(100);
        heap3.insert(90);
        heap3.insert(80);
        heap3.insert(70);
        heap3.insert(60);
        heap3.insert(50);
        heap3.insert(40);
        
        System.out.print("Popping all values: ");
        while (!heap3.isEmpty()) {
            System.out.print(heap3.popMax() + " ");
        }
        System.out.println("\n");
        
        // Test with x=4 (16 children per parent)
        System.out.println("Test 4: x=4 (16 children per parent)");
        PowerOfTwoMaxHeap heap4 = new PowerOfTwoMaxHeap(4);
        for (int i = 1; i <= 20; i++) {
            heap4.insert(i);
        }
        System.out.println("Size after 20 inserts: " + heap4.size());
        System.out.println("Max element: " + heap4.popMax());
        System.out.println("New size after pop: " + heap4.size());
        System.out.println("Next max: " + heap4.popMax());
        System.out.println();
        
        // Test edge cases
        System.out.println("Test 5: Edge Cases");
        PowerOfTwoMaxHeap heap5 = new PowerOfTwoMaxHeap(2);
        System.out.println("Is empty? " + heap5.isEmpty());
        heap5.insert(42);
        System.out.println("After insert, is empty? " + heap5.isEmpty());
        System.out.println("Peek max: " + heap5.peekMax());
        System.out.println("Pop max: " + heap5.popMax());
        System.out.println("Is empty after pop? " + heap5.isEmpty());
        System.out.println();
        
        // Test with duplicate values
        System.out.println("Test 6: Duplicate Values");
        PowerOfTwoMaxHeap heap6 = new PowerOfTwoMaxHeap(2);
        heap6.insert(10);
        heap6.insert(10);
        heap6.insert(5);
        heap6.insert(10);
        heap6.insert(5);
        System.out.print("Popping duplicates: ");
        while (!heap6.isEmpty()) {
            System.out.print(heap6.popMax() + " ");
        }
        System.out.println("\n");
        
        // Test with negative numbers
        System.out.println("Test 7: Negative Numbers");
        PowerOfTwoMaxHeap heap7 = new PowerOfTwoMaxHeap(2);
        heap7.insert(-5);
        heap7.insert(-10);
        heap7.insert(-1);
        heap7.insert(-20);
        heap7.insert(-3);
        System.out.println("Max (should be -1): " + heap7.popMax());
        System.out.println("Next max (should be -3): " + heap7.popMax());
        
        System.out.println("\n=== ALL TESTS COMPLETED ===");
    }
}