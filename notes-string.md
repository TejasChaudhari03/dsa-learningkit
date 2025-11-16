# Learning Strings - Comprehensive Guide

## Table of Contents

1. [String Fundamentals](#string-fundamentals)
2. [Common String Operations](#common-string-operations)
3. [Data Structures for String Problems](#data-structures-for-string-problems)
4. [Problems & Solutions](#problems--solutions)

---

## String Fundamentals

### What are Strings?

Strings are sequences of characters. In Java, strings are **immutable** objects, while in JavaScript, they are **primitive values** that are also immutable.

### Key Concepts

#### 1. **String Traversal**

- **Forward traversal**: Start from index 0 to length-1
- **Backward traversal**: Start from length-1 to 0 (useful for finding last occurrences)

#### 2. **String Methods to Master**

**Java:**

- `charAt(index)` - Access character at index
- `length()` - Get string length
- `substring(start, end)` - Extract substring
- `trim()` - Remove leading/trailing whitespace
- `split(delimiter)` - Split string into array
- `toCharArray()` - Convert to character array

**JavaScript:**

- `charAt(index)` or `str[index]` - Access character at index
- `length` - Get string length (property, not method)
- `substring(start, end)` - Extract substring
- `trim()` - Remove leading/trailing whitespace
- `split(delimiter)` - Split string into array
- `includes(substring)` - Check if substring exists

---

## Common String Operations

### 1. **Trimming Spaces**

Use when dealing with user input or formatted text.

```java
// Java
String trimmed = s.trim();
```

```javascript
// JavaScript
let trimmed = s.trim();
```

### 2. **Splitting Strings**

Convert string to array of words/tokens.

```java
// Java
String[] words = s.split(" ");
```

```javascript
// JavaScript
let words = s.split(" ");
```

### 3. **Character Comparison**

Direct comparison for pattern matching.

```java
// Java
if (s.charAt(i) == 'a') { }
```

```javascript
// JavaScript
if (s[i] === "a") {
}
```

---

## Data Structures for String Problems

### When to Use Set

**Use Case:** Fast lookups, checking membership, removing duplicates

**Benefits:**

- O(1) average time complexity for lookups
- Automatically handles duplicates
- Ideal for "contains" or "exists" checks

**Example:** Jewels and Stones problem - checking if a character is a jewel

```java
Set<Character> jewelSet = new HashSet<>();
```

```javascript
let jewelSet = new Set();
```

### When to Use ArrayList/Array

**Use Case:** Maintaining order, storing indices, collecting results

**Benefits:**

- Preserves insertion order
- Index-based access
- Dynamic sizing (ArrayList/Array)

**Example:** Finding indices of words containing a character

```java
List<Integer> result = new ArrayList<>();
```

```javascript
let result = [];
```

### When to Use HashMap/Map

**Use Case:** Key-value associations, frequency counting

**Benefits:**

- O(1) average lookup/insert
- Store additional information with keys
- Count occurrences

---

## Problems & Solutions

### Problem 1: Length of Last Word

**Description:** Find the length of the last word in a string (words are separated by spaces).

**Example:**

- Input: `"Hello World"`
- Output: `5`

#### Approach 1: Using Built-in Functions

**Java:**

```java
public static int lengthOfLastWordUsingBuiltIn(String s) {
    String[] words = s.trim().split(" ");
    return words[words.length - 1].length();
}
```

**JavaScript:**

```javascript
var lengthOfLastWord = function (s) {
  let words = s.trim().split(" ");
  return words[words.length - 1].length;
};
```

**Time Complexity:** O(n) - where n is the length of the string  
**Space Complexity:** O(n) - for storing the words array  
**Edge Cases:** String with only spaces: `"    "` → 0

---

#### Approach 2: Using Two Loops

**Java:**

```java
public static int lengthOfLastWordUsingLoops(String s) {
    int length = 0;
    int i = s.length() - 1;

    // Skip trailing spaces
    while (i >= 0) {
        if (s.charAt(i) != ' ')
            break;
        i--;
    }

    // Count the length of the last word
    while (i >= 0) {
        if(s.charAt(i) == ' ')
            break;
        length++;
        i--;
    }

    return length;
}
```

**JavaScript:**

```javascript
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
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)  
**Edge Cases:** String with only spaces: `"    "` → 0

---

#### Approach 3: Using Single Loop

**Java:**

```java
public static int lengthOfLastWordUsingSingleLoop(String s) {
    int length = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
        if (s.charAt(i) != ' ') {
            length++;
        } else if (length > 0) {
            break;
        }
    }
    return length;
}
```

**JavaScript:**

```javascript
var lengthOfLastWordSingleLoop = function (s) {
  let length = 0;
  let i = s.length - 1;
  while (i >= 0) {
    if (s[i] !== " ") {
      length++;
    } else if (length > 0) {
      break;
    }
    i--;
  }
  return length;
};
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1) - Most efficient approach  
**Edge Cases:** String with only spaces: `"    "` → 0

**When to Use:** Single loop approach is most efficient for space-constrained environments.

---

### Problem 2: Find Words Containing Character

**Description:** Return indices of words that contain a specific character.

**Example:**

- Input: `words = ["apple", "banana", "cherry", "date"]`, `x = 'a'`
- Output: `[0, 1, 3]`

**Java:**

```java
public List<Integer> findWordsContainingChar(String[] words, char x) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
        for (char c : words[i].toCharArray()) {
            if (c == x) {
                result.add(i);
                break;
            }
        }
    }
    return result;
}
```

**JavaScript:**

```javascript
var findWordsContainingChar = function (words, c) {
  let res = [];
  for (let i = 0; i < words.length; i++) {
    if (words[i].includes(c)) {
      res.push(i);
    }
  }
  return res;
};
```

**Time Complexity:** O(m × n) - where m is the number of words and n is the average length of each word  
**Space Complexity:** O(k) - where k is the number of words containing the character  
**Edge Cases:** Empty array: `[]` → `[]`

**Key Insight:** Use `includes()` in JavaScript for cleaner code, or iterate through characters in Java.

---

### Problem 3: Jewels and Stones

**Description:** Count how many stones are jewels. Jewels represent types of stones that are jewels (case-sensitive).

**Example:**

- Input: `jewels = "aA"`, `stones = "aAAbbbb"`
- Output: `3` (a, A, A are jewels)

#### Approach 1: Brute Force

**Java:**

```java
public static int numJewelsInStonesBruteForce(String jewels, String stones) {
    int count = 0;
    for (char stone : stones.toCharArray()) {
        for (char jewel : jewels.toCharArray()) {
            if (stone == jewel) {
                count++;
                break;
            }
        }
    }
    return count;
}
```

**JavaScript:**

```javascript
var numJewelsInStonesBruteForce = function (jewels, stones) {
  let count = 0;
  for (let i = 0; i < stones.length; i++) {
    if (jewels.includes(stones[i])) {
      count++;
    }
  }
  return count;
};
```

**Time Complexity:** O(m × n) - where m is the length of stones and n is the length of jewels  
**Space Complexity:** O(1)  
**Edge Cases:**

- Empty jewels: `""` → 0
- Empty stones: `""` → 0

---

#### Approach 2: Using Set (Optimized)

**Java:**

```java
public static int numJewelsInStones(String jewels, String stones) {
    Set<Character> jewelSet = new HashSet<>();
    for (char jewel : jewels.toCharArray()) {
        jewelSet.add(jewel);
    }

    int count = 0;
    for (char stone : stones.toCharArray()) {
        if (jewelSet.contains(stone)) {
            count++;
        }
    }
    return count;
}
```

**JavaScript:**

```javascript
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
```

**Time Complexity:** O(m + n) - Linear time  
**Space Complexity:** O(n) - where n is the length of jewels  
**Edge Cases:**

- Empty jewels: `""` → 0
- Empty stones: `""` → 0

**Why Use Set?** Set provides O(1) average time complexity for lookups, dramatically improving performance over brute force for large inputs.

---

### Problem 4: Most Frequent Vowels and Consonants

**Description:** Find the sum of the highest frequency of a vowel and the highest frequency of a consonant in a string.

**Example:**

- Input: `"hello world"`
- Output: `5` (vowel 'o' appears 2 times, consonant 'l' appears 3 times → 2 + 3 = 5)

#### Approach 1: Iterate Through Original String

**JavaScript:**

```javascript
var mostFrequentVowelsAndConsonants = function (s) {
  let map = {};
  for (let i = 0; i < s.length; i++) {
    if (!map[s[i]]) map[s[i]] = 1;
    else ++map[s[i]];
  }

  let vowels = ["a", "e", "i", "o", "u"];
  let maxVowel = 0;
  let maxConsonant = 0;

  // Iterate through original string
  for (let i = 0; i < s.length; i++) {
    if (vowels.includes(s[i])) {
      if (map[s[i]] > maxVowel) maxVowel = map[s[i]];
    } else if (s[i] !== " ") {
      if (map[s[i]] > maxConsonant) maxConsonant = map[s[i]];
    }
  }

  return maxVowel + maxConsonant;
};
```

**Time Complexity:** O(n × m) where n is string length, m is length of vowels array (5)  
**Actual Complexity:** O(5n) ≈ O(n) but **inefficient** because it checks duplicate characters multiple times  
**Space Complexity:** O(k) where k is number of unique characters  
**Edge Cases:**

- String with no vowels: `"bcdfg"` → maxVowel = 0
- String with no consonants: `"aeiou"` → maxConsonant = 0
- String with spaces: handled by checking `s[i] !== ' '`

**Problem:** Iterates through the entire string (including duplicates) instead of just unique characters in the map.

**Example of Inefficiency:**

- Input: `"aaaaaaa"` (7 a's)
- Checks character 'a' seven times even though map has only 1 entry
- Should only check once!

---

#### Approach 2: Using Object.keys (Incorrect Implementation)

**JavaScript:**

```javascript
var mostFrequentVowelsAndConsonants = function (s) {
  let map = {};
  for (let i = 0; i < s.length; i++) {
    if (!map[s[i]]) map[s[i]] = 1;
    else ++map[s[i]];
  }

  let vowels = ["a", "e", "i", "o", "u"];
  let maxVowel = 0;
  let maxConsonant = 0;

  let mapKeys = Object.keys(map);
  // BUG: Should iterate through mapKeys.length, not vowels.length
  for (let i = 0; i < vowels.length; i++) {
    if (vowels.includes(mapKeys[i])) {
      if (map[mapKeys[i]] > maxVowel) maxVowel = map[mapKeys[i]];
    } else {
      if (map[mapKeys[i]] > maxConsonant) maxConsonant = map[mapKeys[i]];
    }
  }

  return maxVowel + maxConsonant;
};
```

**Time Complexity:** O(n + 5) - Only processes first 5 map keys ❌  
**Space Complexity:** O(k) where k is number of unique characters

**Critical Bug:** Loop runs only 5 times (vowels.length), missing most consonants!

**Corrected Version:**

```javascript
for (let i = 0; i < mapKeys.length; i++) {
  // Fixed!
  if (vowels.includes(mapKeys[i])) {
    if (map[mapKeys[i]] > maxVowel) maxVowel = map[mapKeys[i]];
  } else if (mapKeys[i] !== " ") {
    if (map[mapKeys[i]] > maxConsonant) maxConsonant = map[mapKeys[i]];
  }
}
```

**After Fix:**

- **Time Complexity:** O(n + k × m) where k is unique chars, m is vowels array length (5)
- **Simplified:** O(n + 5k) ≈ O(n + k)

---

#### Approach 3: Optimized with Math.max and Set (RECOMMENDED) ✅

**JavaScript:**

```javascript
var mostFrequentVowelsAndConsonants = function (s) {
  // Build frequency map: O(n)
  let map = {};
  for (let i = 0; i < s.length; i++) {
    map[s[i]] = (map[s[i]] || 0) + 1;
    // Alternative: map[s[i]] = !map[s[i]] ? 1 : ++map[s[i]];
  }

  // Use Set for O(1) lookup instead of array
  let vowels = new Set(["a", "e", "i", "o", "u"]);
  let maxVowel = 0;
  let maxConsonant = 0;

  // Iterate through unique characters only: O(k)
  let mapKeys = Object.keys(map);
  for (let i = 0; i < mapKeys.length; i++) {
    if (vowels.has(mapKeys[i])) {
      maxVowel = Math.max(maxVowel, map[mapKeys[i]]);
    } else if (mapKeys[i] !== " ") {
      maxConsonant = Math.max(maxConsonant, map[mapKeys[i]]);
    }
  }

  return maxVowel + maxConsonant;
};
```

**Alternative Cleaner Syntax (for...in loop):**

```javascript
for (let char in map) {
  if (vowels.has(char)) {
    maxVowel = Math.max(maxVowel, map[char]);
  } else if (char !== " ") {
    maxConsonant = Math.max(maxConsonant, map[char]);
  }
}
```

**Time Complexity:** O(n + k) where n is string length, k is unique characters  
**Space Complexity:** O(k) where k ≤ min(n, 26) for lowercase letters  
**Edge Cases:**

- String with no vowels: `"bcdfg"` → maxVowel = 0
- String with no consonants: `"aeiou"` → maxConsonant = 0
- All same character: `"aaaaaaa"` → map has only 1 key

**Why This is Best:**

1. **Set lookup is O(1)** vs array `includes()` O(m)
2. **Iterates only through unique characters** (k) not entire string (n)
3. **Math.max()** is cleaner than if statements
4. For repetitive strings, k << n, making this significantly faster

---

#### Performance Comparison

| Input               | Approach 1        | Approach 2 (Fixed)     | Approach 3         | Space |
| ------------------- | ----------------- | ---------------------- | ------------------ | ----- |
| `"aaaaaaa"` (7 a's) | O(7 × 5) = O(35)  | O(7 + 1 × 5) = O(12)   | O(7 + 1) = O(8)    | O(1)  |
| `"hello world"`     | O(11 × 5) = O(55) | O(11 + 8 × 5) = O(51)  | O(11 + 8) = O(19)  | O(8)  |
| `"abcdefghij"`      | O(10 × 5) = O(50) | O(10 + 10 × 5) = O(60) | O(10 + 10) = O(20) | O(10) |
| 1000 a's            | O(5000)           | O(1005)                | O(1001)            | O(1)  |

**Key Insight:** As string length grows with repetitive characters, Approach 3 becomes exponentially more efficient.

---

**Java Implementation (Approach 3):**

```java
public static int mostFrequentVowelsAndConsonants(String s) {
    // Build frequency map
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
    }

    // Use Set for O(1) lookup
    Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    int maxVowel = 0;
    int maxConsonant = 0;

    // Iterate through unique characters
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
        char c = entry.getKey();
        int freq = entry.getValue();

        if (vowels.contains(c)) {
            maxVowel = Math.max(maxVowel, freq);
        } else if (c != ' ') {
            maxConsonant = Math.max(maxConsonant, freq);
        }
    }

    return maxVowel + maxConsonant;
}
```

**Time Complexity:** O(n + k)  
**Space Complexity:** O(k)

---

### Problem 5: Balanced String Split

**Description:** Split a string containing 'R' and 'L' characters into the maximum number of balanced substrings, where each substring has an equal number of 'R' and 'L' characters.

**Example:**

- Input: `"RLRRLLRLRL"`
- Output: `4`
- Explanation: The string can be split into "RL", "RRLL", "RL", "RL" - each has equal R's and L's

#### Approach 1: Using Single Counter (Optimized)

**JavaScript:**

```javascript
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
```

**Time Complexity:** O(n) where n is length of string  
**Space Complexity:** O(1)  
**Edge Cases:**

- Empty string: `""` → 0
- Single balanced pair: `"RL"` → 1
- No early balance: `"RRRLLL"` → 1

**How it works:**

- Increment counter for 'R', decrement for 'L'
- When counter reaches 0, we found a balanced substring
- Greedy approach: count as soon as balance is found

---

#### Approach 2: Using Two Counters

**Java:**

```java
public static int balancedStringSplit(String s) {
    int r = 0;
    int l = 0;
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == 'R')
            ++r;
        else
            ++l;
        if (r == l) {
            count++;
            r = 0;
            l = 0;
        }
    }
    return count;
}
```

**JavaScript:**

```javascript
var balancedStringSplitTwoCounters = function (s) {
  let r = 0;
  let l = 0;
  let count = 0;
  for (let i = 0; i < s.length; i++) {
    if (s[i] === "R") ++r;
    else ++l;

    if (r === l) {
      count++;
      r = 0;
      l = 0;
    }
  }
  return count;
};
```

**Time Complexity:** O(n) where n is the length of the string  
**Space Complexity:** O(1)

**Comparison with Approach 1:**

- **Approach 1 (Single Counter):** More elegant, uses +1/-1 logic
- **Approach 2 (Two Counters):** More explicit, easier to understand
- **Both have same complexity:** O(n) time, O(1) space

**Key Insight:** This is a **greedy algorithm**. We count balanced substrings as soon as we encounter them, which guarantees the maximum number of splits.

**Why Greedy Works:**

- Finding balance early allows more opportunities for additional balanced substrings
- Example: `"RLRRLL"` → "RL" + "RRLL" = 2 splits (optimal)
- Waiting longer: `"RLRRLL"` → only 1 split (suboptimal)

---

---

### Problem 7: Valid Palindrome

**Description:** Determine if a string is a palindrome, considering only alphanumeric characters and ignoring cases.

**Example:**

- Input: `"A man, a plan, a canal: Panama"`
- Output: `true`
- Explanation: After removing non-alphanumeric characters and converting to lowercase: "amanaplanacanalpanama" is a palindrome

#### Approach 1: Using Extra Space

**Java:**

```java
public static boolean isPalindrome(String s) {
    StringBuilder filteredStr = new StringBuilder();
    StringBuilder reverseStr = new StringBuilder();

    for(char c : s.toCharArray()){
        if(Character.isLetterOrDigit(c)){
            char lower = Character.toLowerCase(c);
            filteredStr.append(lower);
            reverseStr.insert(0, lower);
        }
    }

    return filteredStr.toString().equals(reverseStr.toString());
}
```

**JavaScript:**

```javascript
var isPalindrome = function (s) {
  s = s.toLowerCase();
  let filteredStr = "";
  let reversedStr = "";
  for (let i = 0; i < s.length; i++) {
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
```

**Alternative JavaScript (using regex):**

```javascript
var isPalindromeRegex = function (s) {
  s = s.toLowerCase();
  let filteredStr = "";
  let reversedStr = "";
  for (let i = 0; i < s.length; i++) {
    if (s[i].match(/[a-z0-9]/)) {
      filteredStr = filteredStr + s[i];
      reversedStr = s[i] + reversedStr;
    }
  }
  return filteredStr === reversedStr;
};
```

**Time Complexity:** O(n) where n is the length of the string  
**Space Complexity:** O(n) for the filtered and reversed strings  
**Edge Cases:**

- Empty string: `""` → `true`
- Only non-alphanumeric: `"!!!"` → `true`
- Mixed case and spaces: `"A man, a plan, a canal: Panama"` → `true`

**Explanation:** This approach filters out non-alphanumeric characters, converts to lowercase, and builds both a filtered string and its reverse. Then it compares them for equality.

---

#### Approach 2: Two Pointers (Optimized - No Extra Space)

**Java:**

```java
public static boolean isPalindromeWithoutExtraSpace(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        }
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        }
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}
```

**JavaScript:**

```javascript
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
```

**Time Complexity:** O(n) where n is the length of the string  
**Space Complexity:** O(1) - Most efficient approach ✅  
**Edge Cases:**

- Empty string: `""` → `true`
- Only non-alphanumeric: `"!!!"` → `true`
- Mixed case and spaces: `"A man, a plan, a canal: Panama"` → `true`

**Why This is Better:**

1. **No extra space** - doesn't create new strings
2. **In-place comparison** - uses two pointers
3. **Early termination** - returns false as soon as mismatch is found

**How it works:**

- Use two pointers (left and right) starting from both ends
- Skip non-alphanumeric characters by moving pointers
- Compare characters after converting to lowercase
- Return false immediately if any mismatch is found

---

### Problem 8: Largest Odd Number in String

**Description:** Given a string representing a large integer, return the largest odd number as a substring. Return an empty string if no odd number exists.

**Example:**

- Input: `"35427"`
- Output: `"35427"` (the entire number is odd)
- Input: `"2468"`
- Output: `""` (no odd digits)

**Approach: Backward Traversal**

**Java:**

```java
public static String largestOddNumber(String num) {
    int i = num.length() - 1;

    while (i >= 0) {
        if ((num.charAt(i) - '0') % 2 == 1)
            return num.substring(0, i + 1);
        i--;
    }
    return "";
}
```

**JavaScript:**

```javascript
var largestOddNumber = function (num) {
  let x = num.length - 1;
  while (x >= 0) {
    if (Number(num[x]) % 2 == 1) return num.substring(0, x + 1);
    x--;
  }
  return "";
};
```

**Time Complexity:** O(n) where n is the length of the string  
**Space Complexity:** O(1)  
**Edge Cases:**

- No odd digits: `"2468"` → `""`
- All odd digits: `"13579"` → `"13579"`
- Empty string: `""` → `""`
- Single digit odd: `"5"` → `"5"`
- Single digit even: `"4"` → `""`

**Key Insight:**

- A number is odd if its last digit is odd
- To find the largest odd number, start from the rightmost digit
- Return substring from start to first odd digit found (from right)
- No need to check every digit - just find first odd from right

**Why Start from Right:**

- Input: `"52468"` → Answer is `"5"`, not `"524"` (which is even)
- The rightmost odd digit determines the largest odd number
- Everything to the right of an odd digit makes the number even

---

### Problem 9: Longest Common Prefix

**Description:** Find the longest common prefix string among an array of strings. If there is no common prefix, return an empty string.

**Example:**

- Input: `["flower", "flow", "flight"]`
- Output: `"fl"`
- Input: `["dog", "racecar", "car"]`
- Output: `""` (no common prefix)

**Approach: Vertical Scanning**

**Java:**

```java
public static String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";

    int x = 0;
    while (x < strs[0].length()) {
        char ch = strs[0].charAt(x); // reference starting string to match with other
        for (int i = 1; i < strs.length; i++) {
            if (ch != strs[i].charAt(x) || x == strs[i].length()) {
                return strs[0].substring(0, x);
            }
        }
        ++x;
    }
    return strs[0];
}
```

**JavaScript:**

```javascript
var longestCommonPrefix = function (strs) {
  if (strs.length == 0) return "";
  let x = 0;
  while (x < strs[0].length) {
    let ch = strs[0][x]; // reference starting string to match with other
    for (let i = 1; i < strs.length; i++) {
      if (ch != strs[i][x] || x == strs[i].length)
        return strs[0].substring(0, x);
    }
    ++x;
  }
  return strs[0];
};
```

**Time Complexity:** O(s) where s is the sum of all characters in all strings, or O(n × m) where n is number of strings and m is length of shortest string

**Example:** `strs = ["flower","flow","flight"]` → m = 4 (flow is smallest string and prefix cannot be longer than this) and n = 3

**Space Complexity:** O(1)  
**Edge Cases:**

- Empty array: `[]` → `""`
- Single string: `["abc"]` → `"abc"`
- No common prefix: `["abc", "def"]` → `""`
- All same strings: `["test", "test"]` → `"test"`
- One empty string: `["", "abc"]` → `""`

**How it works:**

1. Use first string as reference
2. Compare each character position across all strings vertically
3. If any string has different character at position x, return prefix up to x-1
4. If any string is shorter than current position, return prefix up to current position
5. If all strings match through all characters of first string, return first string

**Visualization:**

```
flower
flow
flight
^^^^^^
Compare column by column:
- Column 0: f, f, f → ✅ match
- Column 1: l, l, l → ✅ match
- Column 2: o, o, i → ❌ mismatch → return "fl"
```

**Why This Algorithm:**

- **Efficient** - stops as soon as mismatch is found
- **Simple** - uses first string as reference
- **Safe** - handles edge cases (empty arrays, short strings)

---

### Problem 10: Valid Anagram

**Description:** Given two strings `s` and `t`, determine if `t` is an anagram of `s`. An anagram is a word formed by rearranging the letters of another word, using all the original letters exactly once.

**Example:**

- Input: `s = "anagram"`, `t = "nagaram"`
- Output: `true`
- Input: `s = "rat"`, `t = "car"`
- Output: `false`

#### Approach 1: Using HashMap

**Java:**

```java
public static boolean isAnagramUsingMap(String s, String t) {
    if (s.length() != t.length())
        return false;

    HashMap<Character, Integer> map = new HashMap<>();

    for (char c : s.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
    }

    for (char d : t.toCharArray()) {
        if (!map.containsKey(d) || map.get(d) == 0) {
            return false;
        }
        map.put(d, map.get(d) - 1);
    }

    return true;
}
```

**JavaScript:**

```javascript
var isAnagram = function (s, t) {
  if (s.length !== t.length) return false;
  let map = {};
  for (let i = 0; i < s.length; i++) {
    if (!map[s[i]]) {
      map[s[i]] = 1;
    } else {
      ++map[s[i]];
    }
  }

  for (let j = 0; j < t.length; j++) {
    if (!map[t[j]] || map[t[j]] < 0) {
      return false;
    } else {
      --map[t[j]];
    }
  }

  return true;
};
```

**Time Complexity:** O(n) where n is the length of the strings  
**Space Complexity:** O(k) where k is the number of unique characters in the strings (at most 26 for lowercase letters)  
**Edge Cases:**

- Empty strings: `""`, `""` → `true`
- Different lengths: `"abc"`, `"ab"` → `false`
- Special characters: `"a!b@c"`, `"c@b!a"` → `true`
- Same string: `"abc"`, `"abc"` → `true`

**Explanation:** The method counts the frequency of each character in the first string and then checks if the second string has the same frequency for all characters by decrementing the counts.

---

#### Approach 2: Using Sorting

**Java:**

```java
public static boolean isAnagramUsingSorting(String s, String t) {
    if (s.length() != t.length())
        return false;

    char[] sArr = s.toCharArray();
    char[] tArr = t.toCharArray();

    java.util.Arrays.sort(sArr);
    java.util.Arrays.sort(tArr);

    return java.util.Arrays.equals(sArr, tArr);
}
```

**JavaScript:**

```javascript
var isAnagramSorting = function (s, t) {
  if (s.length !== t.length) return false;
  let sortedS = s.split("").sort().join("");
  let sortedT = t.split("").sort().join("");
  return sortedS === sortedT;
};
```

**Time Complexity:** O(n log n) where n is the length of the strings (due to sorting)  
**Space Complexity:** O(1) if sorting in place, otherwise O(n)  
**Edge Cases:**

- Empty strings: `""`, `""` → `true`
- Different lengths: `"abc"`, `"ab"` → `false`
- Special characters: `"a!b@c"`, `"c@b!a"` → `true`

**Explanation:** The method sorts both strings and then compares them. If they are equal after sorting, the strings are anagrams.

**Comparison with Approach 1:**

- **HashMap approach:** Faster (O(n)) but uses extra space
- **Sorting approach:** Slower (O(n log n)) but conceptually simpler

---

#### Approach 3: Using Character Counting Array (Optimized) ✅

**Java:**

```java
public static boolean isAnagramUsingCounting(String s, String t) {
    if (s.length() != t.length())
        return false;

    int[] count = new int[26]; // Only 26 lowercase letters

    for (int i = 0; i < s.length(); i++) {
        count[s.charAt(i) - 'a']++; // increment for s
        count[t.charAt(i) - 'a']--; // decrement for t
    }

    // Check if all counts are zero
    for (int n : count) {
        if (n != 0)
            return false;
    }

    return true;
}
```

**JavaScript:**

```javascript
var isAnagramCharCount = function (s, t) {
  if (s.length !== t.length) return false;

  const count = new Array(26).fill(0);

  for (let i = 0; i < s.length; i++) {
    count[s.charCodeAt(i) - 97]++; // 'a' → 97
    count[t.charCodeAt(i) - 97]--;
  }

  for (let n of count) {
    if (n !== 0) return false;
  }

  return true;
};
```

**Time Complexity:** O(n) where n is the length of the strings  
**Space Complexity:** O(1) since the size of the count array is constant (26 letters)  
**Edge Cases:**

- Empty strings: `""`, `""` → `true`
- Different lengths: `"abc"`, `"ab"` → `false`
- Same letters different counts: `"aabb"`, `"abab"` → `true`

**Why This is Best:**

1. **O(n) time complexity** - same as HashMap approach
2. **O(1) space complexity** - fixed size array (26)
3. **No hash collisions** - direct array indexing
4. **Single pass** - processes both strings simultaneously

**How it works:**

- Use array of size 26 (for a-z)
- Increment count for characters in first string
- Decrement count for characters in second string
- If all counts are zero, strings are anagrams

**Character to Index Mapping:**

```
'a' - 'a' = 0
'b' - 'a' = 1
'c' - 'a' = 2
...
'z' - 'a' = 25
```

---

#### Performance Comparison

| Approach           | Time Complexity | Space Complexity | Best For                  |
| ------------------ | --------------- | ---------------- | ------------------------- |
| HashMap            | O(n)            | O(k) ≤ O(26)     | Unicode characters        |
| Sorting            | O(n log n)      | O(1) or O(n)     | Simple implementation     |
| Character Counting | O(n)            | O(1)             | Lowercase letters only ✅ |

**When to Use Each:**

- **Character Counting:** Best for lowercase English letters (most efficient)
- **HashMap:** When dealing with Unicode, special characters, or mixed case
- **Sorting:** When simplicity is more important than performance

---

### Problem 11: Isomorphic Strings

**Description:** Two strings `s` and `t` are isomorphic if the characters in `s` can be replaced to get `t`. All occurrences of a character must be replaced with another character while preserving the order. No two characters may map to the same character, but a character may map to itself.

**Example:**

- Input: `s = "egg"`, `t = "add"`
- Output: `true` (e→a, g→d)
- Input: `s = "foo"`, `t = "bar"`
- Output: `false` (o cannot map to both a and r)
- Input: `s = "paper"`, `t = "title"`
- Output: `true` (p→t, a→i, e→l, r→e)

**Approach: Bidirectional Mapping**

**Java:**

```java
public static boolean isIsomorphic(String s, String t) {
    if (s.length() != t.length())
        return false;

    Map<Character, Character> mapST = new HashMap<>();
    Map<Character, Character> mapTS = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
        char charS = s.charAt(i);
        char charT = t.charAt(i);

        if (!mapST.containsKey(charS) && !mapTS.containsKey(charT)) {
            mapST.put(charS, charT);
            mapTS.put(charT, charS);
        } else {
            if (!Character.valueOf(charT).equals(mapST.get(charS)) ||
                !Character.valueOf(charS).equals(mapTS.get(charT)))
                return false;
        }
    }

    return true;
}
```

**JavaScript:**

```javascript
var isIsomorphic = function (s, t) {
  if (s.length !== t.length) return false;

  let mapStoT = {};
  let mapTtoS = {};

  for (let i = 0; i < s.length; i++) {
    if (!mapStoT[s[i]] && !mapTtoS[t[i]]) {
      mapStoT[s[i]] = t[i];
      mapTtoS[t[i]] = s[i];
    } else if (mapStoT[s[i]] !== t[i] || mapTtoS[t[i]] !== s[i]) {
      return false;
    }
  }

  return true;
};
```

**Time Complexity:** O(n) where n is the length of the strings  
**Space Complexity:** O(k) where k is the number of unique characters (at most 26 for lowercase letters)  
**Edge Cases:**

- Different lengths: `"a"`, `"ab"` → `false`
- Same pattern different mapping: `"foo"`, `"bar"` → `false`
- Identical strings: `"egg"`, `"add"` → `true`
- Single character: `"a"`, `"b"` → `true`
- Character maps to itself: `"abc"`, `"abc"` → `true`

**Why Two Maps Are Needed:**

Without bidirectional mapping, you might incorrectly accept strings where two different characters map to the same character.

**Example of Why One Map Fails:**

```
s = "badc"
t = "baba"

With only s→t mapping:
b→b ✓
a→a ✓
d→b ✓ (allowed, but wrong!)
c→a ✓ (allowed, but wrong!)

Result: false positive (should be false)

With bidirectional mapping:
b→b and b→b ✓
a→a and a→a ✓
d→b but b→b ✗ (b already maps to b, not d)
Result: correctly returns false
```

**How it works:**

1. Maintain two hash maps: `s→t` and `t→s`
2. For each character pair:
   - If neither is mapped, create both mappings
   - If either is mapped, verify both mappings are consistent
3. Return false if any inconsistency is found

**Visualization:**

```
s = "egg"
t = "add"

Step 1: e→a, a→e  ✓
Step 2: g→d, d→g  ✓
Step 3: g→d, d→g  ✓ (already mapped correctly)

Result: true
```

```
s = "foo"
t = "bar"

Step 1: f→b, b→f  ✓
Step 2: o→a, a→o  ✓
Step 3: o→r, but o already maps to a  ✗

Result: false
```

**Key Insight:** Isomorphic strings preserve the pattern structure. If a character appears multiple times in `s`, it must map to the same character in `t` at all positions, and vice versa.

---

## Summary

| Problem                         | Best Approach                   | Time   | Space |
| ------------------------------- | ------------------------------- | ------ | ----- |
| Length of Last Word             | Single Loop                     | O(n)   | O(1)  |
| Find Words with Char            | Linear Search                   | O(m×n) | O(k)  |
| Jewels and Stones               | Set Lookup                      | O(m+n) | O(n)  |
| Most Frequent Vowels/Consonants | Map + Set (Approach 3)          | O(n+k) | O(k)  |
| Balanced String Split           | Single Counter (Greedy)         | O(n)   | O(1)  |
| Balanced String Split           | Two Counter (Explicit)          | O(n)   | O(1)  |
| Reverse String II               | Two Pointers (Approach 2)       | O(n)   | O(n)  |
| Valid Palindrome                | Two Pointers (Approach 2)       | O(n)   | O(1)  |
| Largest Odd Number              | Backward Traversal              | O(n)   | O(1)  |
| Longest Common Prefix           | Vertical Scanning               | O(n×m) | O(1)  |
| Valid Anagram                   | Character Counting (Approach 3) | O(n)   | O(1)  |
| Isomorphic Strings              | Bidirectional Mapping           | O(n)   | O(k)  |

---

_This document will be updated as new string problems are added to LearnString.java and LearnString.js_
