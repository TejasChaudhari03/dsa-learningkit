// ---- SQUARE ROOT OF A NUMBER ----

/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function (x) {
  if (x < 2) return x;

  let l = 2;
  let r = Math.floor(x / 2);

  while (l <= r) {
    let m = l + Math.floor((r - l) / 2);
    if (x === m * m) {
      return m;
    } else if (x < m * m) {
      r = m - 1;
    } else {
      l = m + 1;
    }
  }

  return r;
};
console.log(mySqrt(8)); // Output: 2
console.log(mySqrt(4)); // Output: 2

// Complexity Analysis
// Time complexity : O(logn). Binary search takes logarithmic time.
// Space complexity : O(1). Constant space is used.
// Explanation of the code
// We start with two pointers, l and r, initialized to 2 and x/2 respectively.
// We perform binary search by calculating the middle point m.
// If m*m equals x, we have found the square root and return m.
// If m*m is greater than x, we adjust the right pointer r to m-1.
// If m*m is less than x, we adjust the left pointer l to m+1.
// The loop continues until l exceeds r.
// Finally, we return r, which will be the integer part of the square root of x.
// Examples
// For x = 8:
// Initial l = 2, r = 4
// 1st iteration: m = 3, 3*3 = 9 > 8, so r = 2
// 2nd iteration: m = 2, 2*2 = 4 < 8, so l = 3
// Now l > r, we return r = 2.
// For x = 4:
// Initial l = 2, r = 2
// 1st iteration: m = 2, 2*2 = 4 == 4, we return m = 2.
// The code correctly calculates the integer square root of a non-negative integer x using binary search.

// ---- GUESS NUMBER HIGHER OR LOWER ----

/**
 * Forward declaration of guess API.
 * @param {number} num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * var guess = function(num) {}
 */

/**
 * @param {number} n
 * @return {number}
 */
var guessNumber = function (n) {
  let l = 1;
  let r = n;

  while (l <= r) {
    let m = l + Math.floor((r - l) / 2);
    let result = guess(m);
    if (result === 0) {
      return m;
    } else if (result < 0) {
      r = m - 1;
    } else if (result > 0) {
      l = m + 1;
    }
  }
  return -1;
};

console.log(guessNumber(10)); // Output: 6 (assuming the picked number is 6)
// Complexity Analysis
// Time complexity : O(logn). Binary search takes logarithmic time.
// Space complexity : O(1). Constant space is used.
// Explanation of the code
// We start with two pointers, l and r, initialized to 1 and n respectively.
// We perform binary search by calculating the middle point m.
// We call the guess API with m and store the result.
// If the result is 0, we have found the picked number and return m.
// If the result is -1, it means m is higher than the picked number, so we adjust the right pointer r to m-1.
// If the result is 1, it means m is lower than the picked number, so we adjust the left pointer l to m+1.
// The loop continues until l exceeds r.
// If we exit the loop without finding the picked number, we return -1.
// The code correctly implements a binary search to find the picked number using the guess API.

// ---- SEARCH IN ROTATED SORTED ARRAY ----

/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function (nums, target) {
  let l = 0;
  let r = nums.length - 1;
  while (l <= r) {
    let m = l + Math.floor((r - l) / 2);
    if (target == nums[m]) {
      return m;
    }

    if (nums[l] <= nums[m]) {
      // left side sorted
      if (target < nums[m] && target >= nums[l]) {
        r = m - 1;
      } else {
        l = m + 1;
      }
    } else {
      if (target > nums[m] && target <= nums[r]) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
  }
  return -1;
};
console.log(search([4, 5, 6, 7, 0, 1, 2], 0)); // Output: 4
// Complexity Analysis
// Time complexity : O(logn). Binary search takes logarithmic time.
// Space complexity : O(1). Constant space is used.
// Explanation of the code
// We start with two pointers, l and r, initialized to 0 and the last index of the array respectively.
// We perform binary search by calculating the middle point m.
// If the target is found at index m, we return m.
// We check if the left side of the array (from l to m) is sorted by comparing nums[l] and nums[m].
// If the left side is sorted, we check if the target is within the range of the left side. If it is, we adjust the right pointer r to m-1. Otherwise, we adjust the left pointer l to m+1.
// If the left side is not sorted, it means the right side (from m to r) is sorted. We check if the target is within the range of the right side. If it is, we adjust the left pointer l to m+1. Otherwise, we adjust the right pointer r to m-1.
// The loop continues until l exceeds r.
// If we exit the loop without finding the target, we return -1.
// The code correctly implements a binary search to find the target in a rotated sorted array.

// ---- FIRST BAD VERSION ----
/**
 * Definition for isBadVersion()
 *
 * @param {integer} version number
 * @return {boolean} whether the version is bad
 * isBadVersion = function(version) {
 *     ...
 * };
 */
var isBadVersion = function (version) {
  // This is a placeholder for the actual implementation of the isBadVersion API.
  // In a real scenario, this would be provided by the system or defined elsewhere in the codebase.
  return version >= 4; // Example: versions 4 and above are bad
};
/**
 * @param {function} isBadVersion()
 * @return {function}
 */
var solution = function (isBadVersion) {
  /**
   * @param {integer} n Total versions
   * @return {integer} The first bad version
   */
  return function (n) {
    let l = 1;
    let r = n;

    while (l < r) {
      let m = l + Math.floor((r - l) / 2);
      if (!isBadVersion(m)) {
        l = m + 1;
      } else {
        r = m;
      }
    }

    return r;
  };
};
console.log(solution(isBadVersion)(5)); // Output: 4
// Complexity Analysis
// Time complexity : O(logn). Binary search takes logarithmic time.
// Space complexity : O(1). Constant space is used.
// Explanation of the code
// We start with two pointers, l and r, initialized to 1 and n respectively.
// We perform binary search by calculating the middle point m.
// We call the isBadVersion API with m to check if it is a bad version.
// If m is not a bad version, it means the first bad version must be after m, so we adjust the left pointer l to m+1.
// If m is a bad version, it means the first bad version is at m or before m, so we adjust the right pointer r to m.
// The loop continues until l is less than r.
// Finally, we return r, which will be the first bad version.
// The code correctly implements a binary search to find the first bad version using the isBadVersion API.
