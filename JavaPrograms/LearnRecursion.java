package JavaPrograms;

public class LearnRecursion {
    static int x = 1; // used in print() function

    public static void main(String[] args) {
        // Example: Print numbers from n to 1
        int a1 = 5;
        System.out.println("Print n to 1:");
        fun(a1); // Output: 5 4 3 2 1

        // Example: Print numbers from 1 to n
        int a2 = 5;
        x = 1; // reset global counter
        System.out.println("\nPrint 1 to n:");
        print(a2); // Output: 1 2 3 4 5

        // Example : Sum of first n natural numbers
        int n = 5;
        System.out.println("\nSum of first " + n + " natural numbers: " + sum(n)); // 15

        // Example: Sum of all elements in an array
        int[] arr = {1, 2, 3, 4, 5};
        int size = arr.length;
        System.out.println("\nSum of array elements: " + arraySum(arr, size)); // 15

        // Example: Sum of all elements in an array (Another Approach)
        int[] arr1 = {2, 3, 4, 5, 6};
        int size1 = arr1.length;
        System.out.println("\nSum of array elements (Another Approach): " + arraySum1(arr1, size1 - 1)); // 15

        // Example: Sum of odd elements in an array
        int[] arr2 = {1, 2, 3, 4, 5};
        int size2 = arr2.length - 1;
        System.out.println("\nSum of odd elements: " + arraySumOdd(arr2, size2)); // 9

        // Example: Factorial of a number
        System.out.println("\nFactorial of 5: " + factorial(5)); // 120

        // Example: Check if a number is a power of two
        System.out.println("\nIs 16 power of two? " + powerOfTwo(16)); // true
        System.out.println("Is 18 power of two? " + powerOfTwo(18)); // false

        // Example: Fibonacci Series
        int fibN = 7;
        System.out.println("\nFibonacci of " + fibN + ": " + fibonacci(fibN)); // 13
        System.out.println("Fibonacci Series up to " + fibN + ":");
        fibonacciSeries(fibN); // 0 1 1 2 3 5 8
    }

    // Example: Print numbers from n to 1
    public static void fun(int a) {
        if (a <= 0) return; // Base case
        System.out.println(a);
        fun(a - 1); // Recursive case
    }

    // Example: Print numbers from 1 to n
    public static void print(int a) {
        if (x > a) return; // Base case
        System.out.println(x);
        x++;
        print(a); // Recursive case
    }

    // Example : Sum of first n natural numbers
    public static int sum(int n) {
        if (n == 0) return 0; // Base case
        return n + sum(n - 1); // Recursive case
    }

    // Example: Sum of all elements in an array
    public static int arraySum(int[] arr, int n) {
        if (n <= 0) return 0; // Base case
        return arr[n - 1] + arraySum(arr, n - 1);
    }

    // Example: Sum of all elements in an array (Another Approach)
    public static int arraySum1(int[] arr, int n) {
        if (n == 0) return arr[n];
        return arr[n] + arraySum1(arr, n - 1);
    }

    // Example: Sum of odd elements in an array
    public static int arraySumOdd(int[] arr, int n) {
        boolean isOdd = arr[n] % 2 != 0;
        if (n == 0) return isOdd ? arr[n] : 0;
        return (isOdd ? arr[n] : 0) + arraySumOdd(arr, n - 1);
    }

    // Example: Factorial of a number
    public static int factorial(int x) {
        if (x == 0 || x == 1) return 1;
        return x * factorial(x - 1);
    }

    // Example: Check if a number is a power of two
    public static boolean powerOfTwo(int n) {
        if (n == 1) return true;
        else if (n % 2 != 0 || n < 1) return false;
        return powerOfTwo(n / 2);
    }

    // Example: Fibonacci Series
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Example: Fibonacci Series up to n terms
    public static void fibonacciSeries(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}