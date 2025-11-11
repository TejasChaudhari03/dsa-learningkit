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

// Most frequent vowels and consonants in a string
var mostFrequentVowelsAndConsonants = function (s) {
  let map = {};
  for (let i = 0; i < s.length; i++) {
    if (!map[s[i]]) map[s[i]] = 1;
    else ++map[s[i]];
  }

  let vowels = ["a", "e", "i", "o", "u"];
  let maxVowel = 0;
  let maxConsonant = 0;

  // Approach 1: Iterate through original string (Inefficient - checks duplicates)
  for (let i = 0; i < s.length; i++) {
    if (vowels.includes(s[i])) {
      if (map[s[i]] > maxVowel) maxVowel = map[s[i]]; // Fixed: removed extra bracket
    } else if (s[i] !== " ") {
      // Fixed: handle spaces
      if (map[s[i]] > maxConsonant) maxConsonant = map[s[i]]; // Fixed: removed extra bracket
    }
  }

  // Approach 2: Using Object.keys to get map keys (Fixed - corrected loop condition)
  /*
  let mapKeys = Object.keys(map);
  for (let i = 0; i < mapKeys.length; i++) {
      if (vowels.includes(mapKeys[i])) {
        if (map[mapKeys[i]] > maxVowel) maxVowel = map[mapKeys[i]];
      } else if (mapKeys[i] !== ' ') { // Fixed: handle spaces
        if (map[mapKeys[i]] > maxConsonant) maxConsonant = map[mapKeys[i]];
      }
  }
  */

  // Approach 3: Using Math.max and Set (Optimized - RECOMMENDED)
  /*
    let map = {};
    for (let i = 0; i < s.length; i++) {
      map[s[i]] = (map[s[i]] || 0) + 1; // or
      // map[s[i]] = !map[s[i]] ? 1 : ++map[s[i]];
    }

    let vowels = new Set(['a', 'e', 'i', 'o', 'u']); // Use Set for O(1) lookup
    let maxVowel = 0;
    let maxConsonant = 0;
    let mapKeys = Object.keys(map);
    for (let i = 0; i < mapKeys.length; i++) {
      if (vowels.has(mapKeys[i])) { // Fixed: changed includes() to has() for Set
        maxVowel = Math.max(maxVowel, map[mapKeys[i]]);
      } else if (mapKeys[i] !== ' ') { // Fixed: handle spaces
        maxConsonant = Math.max(maxConsonant, map[mapKeys[i]]);
      }
    }
  */

  return maxVowel + maxConsonant;
};
console.log(mostFrequentVowelsAndConsonants("hello world")); // Output: 5
// Time Complexity:
//   Approach 1: O(n × m) where n is string length, m is vowels array length ≈ O(5n) - Inefficient
//   Approach 2: O(n + k × m) where k is unique chars ≈ O(n + 5k)
//   Approach 3: O(n + k) - Most efficient (uses Set for O(1) lookup)
// Space Complexity: O(k) where k is number of unique characters
// Edge Cases:
// 1. String with no vowels: "bcdfg" -> maxVowel = 0
// 2. String with no consonants: "aeiou" -> maxConsonant = 0
// 3. String with spaces: "hello world" -> spaces ignored
// Explanation: The function counts the frequency of each character and then determines the most frequent vowel and consonant, returning their combined frequency.

// Balanced String Split
var balancedStringSplit = function (s) {
  let temp = 0;
  let count = 0;
  for (let i = 0; i < s.length; i++) {
    if (s[i] === "R") ++temp;
    else --temp;

    if (temp === 0) count++;
  }
  return count;
};
// Time Complexity: O(n) where n is length of string
// Space Complexity: O(1)
// Edge Cases:
// 1. Empty string: "" -> 0
// 2. String with no balanced splits: "RRR" -> 0
// Explanation: The function uses a counter to track the balance between 'R' and 'L'. Each time the counter returns to zero, it indicates a balanced substring, and the count is incremented.
console.log(balancedStringSplit("RLRRLLRLRL")); // Output: 4

// Reverse String II
var reverseStr = function (s, k) {
  s = s.split("");

  for (let x = 0; x < s.length; x = x + 2 * k) {
    let n = k;
    let mid = Math.floor(n / 2);
    for (let i = 0; i < mid; i++) {
      let temp = s[x + i];
      s[x + i] = s[x + n - 1 - i];
      s[x + n - 1 - i] = temp;
    }
  }

  return s.join("");
};
// Time Complexity: O(n) where n is length of string
// Space Complexity: O(1)
// Edge Cases:
// 1. String length less than k: "abc", 5 -> "cba" -- entire string reversed
// 2. String length between k and 2k: "abcdef", 4 -> "dcbaef" -- first k reversed, rest unchanged
// 3. Empty string: "", 3 -> "" -- no change
// 4. k is 0: "abc", 0 -> "abc" -- no change
// 5. k is 8: "abcdefg", 8 -> "gfedcba" -- entire string reversed as length < k
// Explanation: The function reverses the first k characters for every 2k characters in the string. If there are fewer than k characters left, it reverses all of them. If there are between k and 2k characters left, it reverses the first k characters and leaves the rest unchanged.
console.log(reverseStr("abcdefg", 2)); // Output: "bacdfeg"

// Valid Palindrome using extra space

var isPalindrome = function (s) {
  s = s.toLowerCase();
  let filteredStr = "";
  let reversedStr = "";
  for (let i = 0; i < s.length; i++) {
    // if(s[i].match(/[a-z0-9]/)){
    if (
      (s[i].charCodeAt() >= "a".charCodeAt() &&
        s[i].charCodeAt() <= "z".charCodeAt()) ||
      (s[i].charCodeAt() >= "0".charCodeAt() &&
        s[i].charCodeAt() <= "9".charCodeAt())
    ) {
      filteredStr = filteredStr + s[i];
      reversedStr = s[i] + reversedStr;
    }
  }
  return filteredStr === reversedStr;
};
// Time Complexity: O(n) where n is length of string
// Space Complexity: O(n) for storing filtered and reversed strings
// Edge Cases:
// 1. Empty string: "" -> true
// 2. String with only non-alphanumeric characters: "!!!" -> true
// 3. Mixed case and spaces: "A man, a plan, a canal: Panama" -> true
// Explanation: The function filters out non-alphanumeric characters and converts the string to lowercase. It then constructs a filtered string and its reverse, comparing the two to determine if the original string is a palindrome.

// Valid Palindrome without extra space
var isPalindromeTwoPointers = function (s) {
  s = s.toLowerCase();
  let i = 0;
  let j = s.length - 1;

  while (i < j) {
    if (!s[i].match(/[a-z0-9]/i)) {
      ++i;
    } else if (!s[j].match(/[a-z0-9]/i)) {
      --j;
    } else if (s[i] === s[j]) {
      ++i;
      --j;
    } else {
      return false;
    }
  }
  return true;
};
// Time Complexity: O(n) where n is length of string
// Space Complexity: O(1)
// Edge Cases:
// 1. Empty string: "" -> true
// 2. String with only non-alphanumeric characters: "!!!" -> true
// 3. Mixed case and spaces: "A man, a plan, a canal: Panama" -> true
// Explanation: The function uses two pointers to traverse the string from both ends, skipping non-alphanumeric characters and comparing characters for equality.

// Largest Odd Number in String
var largestOddNumber = function (num) {
  let x = num.length - 1;
  while (x >= 0) {
    if (Number(num[x] % 2 == 1)) return num.substring(0, x + 1);
    x--;
  }
  return "";
};
// Time Complexity: O(n) where n is length of string
// Space Complexity: O(1)
// Edge Cases:
// 1. No odd digits: "2468" -> ""
// 2. All odd digits: "13579" -> "13579"
// 3. Empty string: "" -> ""
// Explanation: The function iterates from the end of the string to find the first odd digit. Once found, it returns the substring from the start to that index. If no odd digit is found, it returns an empty string.
console.log(largestOddNumber("35427")); // Output: "35427"

// Longest Common Prefix
var longestCommonPrefix = function (strs) {
  if (strs.length == 0) return "";
  let x = 0;
  while (x < strs[0].length) {
    let ch = strs[0][x]; // reference starting string to match with other
    for (let i = 1; i < strs.length; i++) {
      if (ch != strs[i][x] || x == strs.length) return strs[0].substring(0, x);
    }
    ++x;
  }
  return strs[0];
};
// Time Complexity: O(s) where s is the sum of all characters in all strings or
// O(n * m) where n is number of strings and m is length of shortest string
// Example strs = ["flower","flow","flight"] -> m = 4 (flow is smallest string and prefix cannot be longer than this) and n = 3
// Space Complexity: O(1)
// Edge Cases:
// 1. No common prefix: ["dog", "racecar", "car"] -> ""
