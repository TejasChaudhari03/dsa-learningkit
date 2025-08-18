public class LearnLoops {
    public static void main(String[] args) {
        int[] arr = {10, 1, 4, 5, 9};
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Index: " + i);
            if (arr[i] % 2 == 0) {
                System.out.println("Even number: " + arr[i]);
            }
        }
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        for (int i = 0; i < arr1.length; i++) {
            System.out.println("Current number: " + arr[i]);
        }
        
        for (int i = arr1.length - 1; i >= 0; i--) {
            System.out.println("Reverse index: " + i);
        }
    }
}