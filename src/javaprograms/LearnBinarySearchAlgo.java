package javaprograms;

public class LearnBinarySearchAlgo {
    public static void main(String[] args) {
        System.out.println("Learn Binary Search Algorithm in Java");

        System.out.println("Square root of 16: " + sqrt(16)); // Should return 4
    }

    // Square root of a number using binary search
    private static int sqrt(int n) {
        if (n < 2) return n; // Handle 0 and 1

        int left = 1, right = n / 2, ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid; // Use long to avoid overflow

            if (square == n) {
                return mid; // Perfect square
            } else if (square < n) {
                ans = mid; // Store the last valid answer
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        return ans; // Return the floor of the square root
    }
    // Complexity analysis:
    // Time Complexity: O(log n) - Binary search halves the search space with each iteration
    // Space Complexity: O(1) - Constant space used for variables
    // Explanation: The function uses binary search to find the largest integer whose square is less than or equal to n.
    // It maintains a search range defined by left and right pointers, and iteratively narrows down this range until it finds the correct integer.

    // Guess Number Game using binary search
    // The guess API is defined in the parent class. We are trying to guess a number between 1 and n.
    public int guessNumber(int n) {
        int left = 1, right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int res = mid;

            if (res == 0) {
                return mid; // Found the picked number
            } else if (res < 0) {
                right = mid - 1; // Mid is higher than the picked number
            } else {
                left = mid + 1; // Mid is lower than the picked number
            }
        }
        return -1; // If we exit the loop without finding the picked number
    }

}
