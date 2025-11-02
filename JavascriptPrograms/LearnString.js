/*
 * JavaScript program to find the length of the last word in a string
 */

// Using built-in functions
var lengthOfLastWord = function (s) {
  let words = s.trim().split(" "); // words array after trimming spaces
  // e.g. ["Hello", "World"]
  return words[words.length - 1].length;
};
// Time Complexity: O(n)
// Space Complexity: O(1)
// Edge Cases:
// 1. String with only spaces: "    " -> 0
// Explanation: The trim() method removes leading and trailing spaces, and split(" ") divides the string into words based on spaces. The last word's length is then returned.
console.log(lengthOfLastWord("Hello World")); // Output: 5

// Using two loops
// Trim all space from the end of the string and then count the length of the last word till a space is encountered
var lengthOfLastWordTwoLoops = function (s) {
  let length = 0;
  let i = s.length - 1;
  // Trim spaces from the end
  while (i >= 0 && s[i] === " ") {
    i--;
  }
  // Count the length of the last word
  while (i >= 0 && s[i] !== " ") {
    length++;
    i--;
  }
  return length;
};
// Time Complexity: O(n)
// Space Complexity: O(1)
// Edge Cases:
// 1. String with only spaces: "    " -> 0
// Explanation: The first loop trims spaces from the end of the string, and the second loop counts the length of the last word until a space is encountered.

console.log(lengthOfLastWordTwoLoops("Hello World")); // Output: 5

// Using a single loop
// Traverse the string from the end, skipping spaces and counting the length of the last word
var lengthOfLastWordSingleLoop = function (s) {
  let length = 0;
  let i = s.length - 1;
  while (i >= 0) {
    if (s[i] !== " ") {
      length++;
    } else if (length > 0) {
      break; // Break if we have already counted some letters of the last word
    }
    i--;
  }
  return length;
};
// Time Complexity: O(n)
// Space Complexity: O(1)
// Edge Cases:
// 1. String with only spaces: "    " -> 0
// Explanation: The loop continues until the beginning of the string is reached, counting the length of the last word.

console.log(lengthOfLastWordSingleLoop("Hello World")); // Output: 5

// Find words containing character and return index of those words
var findWordsContainingChar = function (s, c) {
  let res = [];
  for (let i = 0; i < s.length; i++) {
    for (let j = 0; j < words.length; j++) {
      if (words[j].includes(c)) {
        res.push(j);
      }
    }
  }
  return res;
};

console.log(findWordsContainingChar("Hello World", "o")); // Output: [0, 1]
// Time Complexity: O(n*m) where n is number of words and m is average length of words
// Space Complexity: O(k) where k is number of words containing character
// Edge Cases:
// 1. Character not present in any word: "Hello World", "x" -> []
// Explanation: The nested loops check each word for the presence of the character and store the indices of matching words in the result array.
// Space Complexity: O(k) where k is number of words containing character

// Jewels and Stones

// Using brute force
var numJewelsInStonesBruteForce = function (jewels, stones) {
  let count = 0;
  for (let i = 0; i < stones.length; i++) {
    if (jewels.includes(stones[i])) {
      count++;
    }
    // Alternative brute force approach or scene behind includes()
    // for (let j = 0; j < jewels.length; j++) {
    //   if (stones[i] === jewels[j]) {
    //     count++;
    //     break;
    //   }
    // }
  }
  return count;
};
// Time Complexity: O(n * m) where n is length of stones and m is length of jewels
// Space Complexity: O(1)
// Edge Cases:
// 1. No jewels: "", "abc" -> 0
// 2. No stones: "a", "" -> 0
// Explanation: The includes() method checks if each stone is a jewel, leading to higher time complexity for larger inputs.

// Using Set for efficient lookup
var numJewelsInStones = function (jewels, stones) {
  let jewelSet = new Set(jewels);
  let count = 0;
  for (let stone of stones) {
    if (jewelSet.has(stone)) {
      count++;
    }
  }
  return count;
};
// Time Complexity: O(n + m) where n is length of jewels and m is length of stones
// Space Complexity: O(k) where k is number of unique jewels
// Edge Cases:
// 1. No jewels: "", "abc" -> 0
// 2. No stones: "a", "" -> 0
// Explanation: The Set allows for O(1) average time complexity for lookups, making the counting process efficient.
