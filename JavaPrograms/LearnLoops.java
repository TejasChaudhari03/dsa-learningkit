package JavaPrograms;
/*
 * LearnLoops.java
 *
 * This file demonstrates different loop use cases
 * inspired by JavaScript examples — translated into Java syntax,
 * with best practices, comments, and edge cases explained.
 */

import java.util.Arrays;

public class LearnLoops {

    // 1. Example - Element existence in the array
    static void findElementExistence(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                System.out.println("Found at Index " + (i + 1)); // 1-based index
                return;
            }
        }
        System.out.println("Element not found");
    }

    // Alternative using in-built methods (linear search is typical in Java)
    static void findElementUsingBuiltIn(int[] arr, int x) {
        // Java arrays do not have indexOf directly (unlike JS).
        // We can either write a loop or use java.util.Arrays.stream.
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                System.out.println("Found using built-in loop at Index " + (i + 1));
                return;
            }
        }
        System.out.println("-1 (Not found)");
    }

    // 2. Example - Count negative numbers in array
    static int countAllNegativeNumbers(int[] arr) {
        int count = 0;
        for (int n : arr) {
            if (n < 0) count++;
        }
        return count;
    }

    // 3. Example - Find largest number
    static int findLargestNumber(int[] arr) {
        if (arr.length == 0) throw new IllegalArgumentException("Array cannot be empty");
        int max = arr[0]; 
        for (int n : arr) {
            if (n > max) max = n;
        }
        return max;
    }

    // 4. Example - Find smallest number
    static int findSmallestNumber(int[] arr) {
        if (arr.length == 0) throw new IllegalArgumentException("Array cannot be empty");
        int min = arr[0];
        for (int n : arr) {
            if (n < min) min = n;
        }
        return min;
    }

    // 5. Example - Find 2nd largest number
    static void findSecondLargestNumber(int[] arr) {
        if (arr.length < 2) {
            System.out.println("Array should have at least 2 elements");
            return;
        }

        int firstLargest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int n : arr) {
            if (n > firstLargest) {
                secondLargest = firstLargest;
                firstLargest = n;
            } else if (n > secondLargest && n != firstLargest) {
                secondLargest = n;
            }
        }

        System.out.println("First Largest : " + firstLargest);
        if (secondLargest != Integer.MIN_VALUE)
            System.out.println("Second Largest : " + secondLargest);
        else
            System.out.println("Second Largest not found (array may contain duplicates only)");
    }

    // 6. Nested Loops Examples (loop inside loop)
    static void nestedLoopsDemo() {
        System.out.println("=== Nested Loops Demo ===");
        System.out.println();

        // Example 1: Simple 3x3 matrix loop
        System.out.println("1) Simple 3x3 matrix loop");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("i = " + i + " || j = " + j);
            }
        }
        System.out.println();

        // Example 2: Inner loop runs up to i-1
        System.out.println("2) Inner loop runs up to i-1");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println("i = " + i + " || j = " + j);
            }
        }
        System.out.println();

        // Example 3: Inner loop runs up to i (inclusive)
        System.out.println("3) Inner loop runs up to i (inclusive)");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.println("i = " + i + " || j = " + j);
            }
        }
        System.out.println();

        // Example 4: Inner loop counts downwards
        System.out.println("4) Inner loop counts downwards");
        for (int i = 0; i < 3; i++) {
            for (int j = i; j > 0; j--) {
                System.out.println("i = " + i + " || j = " + j);
            }
        }
        System.out.println();

        // Example 5: Outer loop decreases
        System.out.println("5) Outer loop decreases");
        for (int i = 5; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.println("i = " + i + " || j = " + j);
            }
        }
        System.out.println();

        // Example 6: Both loops decreasing
        System.out.println("6) Both loops decreasing");
        for (int i = 5; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                System.out.println("i = " + i + " || j = " + j);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 10, 4, 8};
        int[] arrWithNeg = {5, -1, 0, -3, 9, -7};
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arrWithNeg));
        System.out.println("=== Find Element Existence ===");
        findElementExistence(arr, 10);
        findElementExistence(arr, 11);
        findElementUsingBuiltIn(arr, 2);
        System.out.println();

        System.out.println("=== Count Negative Numbers ===");
        System.out.println("Negative Number count: " + countAllNegativeNumbers(arrWithNeg));
        System.out.println();

        System.out.println("=== Largest Number ===");
        System.out.println("Max number: " + findLargestNumber(arr));
        System.out.println();

        System.out.println("=== Smallest Number ===");
        System.out.println("Min number: " + findSmallestNumber(arr));
        System.out.println();

        System.out.println("=== Second Largest Number ===");
        findSecondLargestNumber(arr);
        System.out.println();

        nestedLoopsDemo();
    }
}
// This code provides a comprehensive overview of various loop constructs in Java, including for loops, while loops, and do-while loops, along with practical examples and use cases.