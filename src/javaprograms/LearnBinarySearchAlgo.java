package javaprograms;

public class LearnBinarySearchAlgo extends GuessGame {

    private static boolean isBadVersion(int m) {
        // This is a placeholder for the actual implementation of the isBadVersion API.
        // In a real scenario, this would be provided by the system or defined elsewhere in the codebase.
        return m >= 4; // Example: versions 4 and above are bad
    }

    public LearnBinarySearchAlgo(int n) {
        super(n);
    }

    public static void main(String[] args) {

        System.out.println("Learn Binary Search Algorithm in Java");

        // ---- SQRT ----
        System.out.println("Square root of 16: " + sqrt(16));

        // ---- GUESS NUMBER ----
        LearnBinarySearchAlgo game = new LearnBinarySearchAlgo(100);
        int result = game.guessNumber(100);

        System.out.println("Binary search found: " + result);
        System.out.println("Actual picked number: " + game.getPick());

        // ---- SEARCH IN ROTATED SORTED ARRAY ----
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        int index = search(nums, target);
        System.out.println("Array: [4,5,6,7,0,1,2]");
        System.out.println("Target " + target + " found at index: " + index);
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
            int res = guess(mid);

            if (res == 0) { 
                return mid; // Found the correct number
            } else if (res < 0) {
                right = mid - 1; // Guess is too high, search in the left half
            } else {
                left = mid + 1; // Guess is too low, search in the right half
            } 
        }
        return -1;
    }
    // Complexity analysis:
    // Time Complexity: O(log n) - Binary search halves the search space with each iteration
    // Space Complexity: O(1) - Constant space used for variables
    // Explanation: The function uses binary search to find the picked number. '
    // It maintains a search range defined by left and right pointers, and iteratively narrows down this range based on the feedback from the guess API until it finds the correct number.

    // Search in a rotated sorted array using binary search
    // The array is sorted in ascending order and then rotated at some pivot. We need to find the index of the target value.
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1 ;
        while( l <=r ){
            int m = l + (r-l)/2;
            if(target == nums[m]){
                return m;
            }

            if(nums[l] <= nums[m]){ // This checks if the left half of the array (from index l to m) is sorted.
                if(target < nums[m] && target >= nums[l]){ 
                    // This checks if the target is within the sorted left half. 
                    // If it is, we can safely ignore the right half and continue searching in the left half by moving the right pointer to m - 1.
                    r = m - 1;
                }else{
                    l = m + 1; // If the target is not in the sorted left half, it must be in the right half, so we move the left pointer to m + 1 to continue searching there.
                }
            }else{ // right side sorted 
                if(target > nums[m] && target <= nums[r]){
                    // This checks if the target is within the sorted right half. 
                    // If it is, we can safely ignore the left half and continue searching in the right half by moving the left pointer to m + 1.
                    l = m + 1;
                }else{
                    r = m - 1; // If the target is not in the sorted right half, it must be in the left half, so we move the right pointer to m - 1 to continue searching there.
                }                
            }
        }

        return -1;
    }
    // Complexity analysis:
    // Time Complexity: O(log n) - Binary search halves the search space with each iteration
    // Space Complexity: O(1) - Constant space used for variables
    // Explanation: The function searches for a target value in a rotated sorted array. 
    // It uses binary search to efficiently find the target by determining which half of the array is sorted and adjusting the search range accordingly.

    public static int firstBadVersion(int n) {
        int l = 1 ,r = n;
        while(l < r){ // 
            int m = l + (r-l)/2;
            if(!isBadVersion(m)){ 
                l = m + 1;
            }else{
                r = m;
            }
        }
        return r ;
    }
    // Complexity analysis:
    // Time Complexity: O(log n) - Binary search halves the search space with each iteration
    // Space Complexity: O(1) - Constant space used for variables
    // Explanation: The function finds the first bad version among n versions.
    // It uses binary search to efficiently find the first bad version by checking the middle version
    // and adjusting the search range based on whether the middle version is bad or not.
    // The loop continues until the search range is narrowed down to a single version, which is the first bad version.
}
