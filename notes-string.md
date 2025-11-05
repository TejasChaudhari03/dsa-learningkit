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

## Best Practices & Tips

### 1. **Choose the Right Approach**

- Use built-in functions for readability
- Use manual iteration for space efficiency
- Use Sets/HashMaps for lookup-heavy operations

### 2. **Handle Edge Cases**

- Empty strings
- Strings with only spaces
- Single character strings
- Very long strings

### 3. **Optimize Based on Constraints**

- Small input → Brute force is acceptable
- Large input → Use efficient data structures (Set, HashMap)
- Space-constrained → Use in-place algorithms

### 4. **Common Patterns**

- **Two pointers:** For palindrome, reversing
- **Sliding window:** For substring problems
- **Hash structures:** For frequency/existence checks
- **Stack:** For balanced parentheses, nested structures

---

## Summary

| Problem                         | Best Approach           | Time   | Space |
| ------------------------------- | ----------------------- | ------ | ----- |
| Length of Last Word             | Single Loop             | O(n)   | O(1)  |
| Find Words with Char            | Linear Search           | O(m×n) | O(k)  |
| Jewels and Stones               | Set Lookup              | O(m+n) | O(n)  |
| Most Frequent Vowels/Consonants | Map + Set (Approach 3)  | O(n+k) | O(k)  |
| Balanced String Split           | Single Counter (Greedy) | O(n)   | O(1)  |
| Balanced String Split           | Two Counter (Explicit)  | O(n)   | O(1)  |

---

_This document will be updated as new string problems are added to LearnString.java and LearnString.js_
