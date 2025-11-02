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

| Problem              | Best Approach | Time   | Space |
| -------------------- | ------------- | ------ | ----- |
| Length of Last Word  | Single Loop   | O(n)   | O(1)  |
| Find Words with Char | Linear Search | O(m×n) | O(k)  |
| Jewels and Stones    | Set Lookup    | O(m+n) | O(n)  |

---

_This document will be updated as new string problems are added to LearnString.java and LearnString.js_
