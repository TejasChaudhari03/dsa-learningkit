# **Complete Guide to JavaScript and Java Loops**

## **Table of Contents**

1. [Basic Loop Types](#basic-loop-types)
2. [Best Practices](#best-practices)
3. [Common Loop Patterns](#common-loop-patterns)
4. [Nested Loops](#nested-loops)
5. [Pattern Examples](#pattern-examples)
6. [Common Pitfalls & Optimizations](#common-pitfalls--optimizations)
7. [Advanced Examples](#advanced-examples)

---

## **Basic Loop Types**

### **For Loop**

**JavaScript**

```javascript
// Example: Print index and even numbers from array
let arr = [10, 1, 4, 5, 9];

for (let i = 0; i < arr.length; i++) {
  console.log("Index: " + i);
  if (arr[i] % 2 === 0) {
    console.log("Even number: " + arr[i]);
  }
}
```

**Java**

```java
// Example: Print index and even numbers from array
int[] arr = {10, 1, 4, 5, 9};

for (int i = 0; i < arr.length; i++) {
    System.out.println("Index: " + i);
    if (arr[i] % 2 == 0) {
        System.out.println("Even number: " + arr[i]);
    }
}
```

---

### **While Loop**

**JavaScript**

```javascript
let i = 0;
while (i < arr.length) {
  console.log("Value: " + arr[i]);
  i++;
}
```

**Java**

```java
int i = 0;
while (i < arr.length) {
    System.out.println("Value: " + arr[i]);
    i++;
}
```

---

### **Do-While Loop**

**JavaScript**

```javascript
let i = 0;
do {
  console.log("Checking: " + arr[i]);
  i++;
} while (i < arr.length);
```

**Java**

```java
int i = 0;
do {
    System.out.println("Checking: " + arr[i]);
    i++;
} while (i < arr.length);
```

---

### **Enhanced For Loop (Java only)**

**Java**

```java
for (int num : arr) {
    if (num % 2 == 0) {
        System.out.println("Even number: " + num);
    }
}
```

---

## **Best Practices**

### ✅ **Core Guidelines**

1. **Avoid magic numbers** — use `.length` instead of hardcoding limits.
2. **Update loop counters** to prevent infinite loops.
3. **Always use curly braces `{}`** even if there's one statement — improves readability and reduces errors.
4. **Descriptive variable names** help clarity (e.g., `index` instead of `i` in bigger loops).
5. **Choose the right loop** for the situation:
   - `for` → when the number of iterations is known
   - `while` → when looping until a condition changes
   - `do-while` → when you need to run the code at least once
   - Java's **enhanced for** → clean iteration when you don't need an index

### 💡 **Additional Best Practices**

6. **Cache array length** in performance-critical loops:

   ```javascript
   // Instead of checking arr.length every iteration
   for (let i = 0, len = arr.length; i < len; i++) {
     // loop body
   }
   ```

7. **Use `const` for loop variables that don't change** (JavaScript):

   ```javascript
   for (const item of arr) {
     console.log(item); // item is read-only
   }
   ```

8. **Consider using modern array methods** when appropriate:
   ```javascript
   // Instead of manual loops for common operations
   arr.forEach((item) => console.log(item));
   const evens = arr.filter((num) => num % 2 === 0);
   const doubled = arr.map((num) => num * 2);
   ```

---

## **Common Loop Patterns**

### **1. Element Existence in Array**

```javascript
let arr = [3, 2, 10, 4, 8];

function findElementExistence(x) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] == x) {
      console.log("Index " + (i + 1));
      return;
    }
  }
  return -1;
}

findElementExistence(11);

function findElementUsingInBuiltFunc(x) {
  let index = arr.indexOf(x);
  return index !== -1 ? "Func 2 Index " + (index + 1) : -1;
}

findElementUsingInBuiltFunc();

let res = findElementUsingInBuiltFunc(10);
console.log(res);
```

### **2. Count Negative Numbers**

```javascript
function countAllNegativeNumbers(arr) {
  let count = 0;
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] < 0) count++;
  }
  return count;
}
let res1 = countAllNegativeNumbers(arr);
console.log("Negative Number count " + res1);
```

### **3. Find Largest Number**

```javascript
function findLargestNumber(arr) {
  let max = arr[0];
  // two approaches either consider first element of array OR
  // consider -Infinity (Negative Infinity/ Number.NEGATIVE_INFINITY)
  // But that introduces a "magic value" that doesn't come from the array itself.
  // Starting with arr[0] is cleaner and avoids unnecessary assumptions,
  // because the largest number must be one of the elements in the array.
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] > max) {
      max = arr[i];
    }
  }
  return max;
}

let res2 = findLargestNumber(arr);
console.log("Max number " + res2);
```

### **4. Find Smallest Number**

```javascript
function findSmallestNumber(arr) {
  let min = arr[0];
  // Two approaches:
  // 1. Initialize min = arr[0] (the first element of the array).
  //    This is clean and avoids introducing any external "magic value".
  // 2. Alternatively, initialize min = Infinity (Number.POSITIVE_INFINITY).
  //    This works, but Infinity is not from the array itself.
  //
  // Starting with arr[0] is usually better, because the smallest number
  // must also be one of the elements in the array.
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] < min) {
      min = arr[i];
    }
  }
  return min;
}

let res3 = findSmallestNumber(arr);
console.log("Min number " + res3);
```

### **5. Find Second Largest Number**

```javascript
function findSecondLargestNumber(arr) {
  if (arr.length < 2) return "Array should have atleast 2 elements";
  // Two approaches:
  // 1. Initialize arr[0] (the first element of the array) = first largest
  //    and arr[1] (the first element of the array) = second largest.
  //    This is clean and avoids introducing any external "magic value".
  // 2. Alternatively, first largest and second largest as -Infinity

  let firstLargest = arr[0];
  let secondLargest = arr[1];

  for (let i = 0; i < arr.length; i++) {
    if (arr[i] > firstLargest) {
      secondLargest = firstLargest;
      firstLargest = arr[i];
    } else if (arr[i] > secondLargest && arr[i] != firstLargest)
      secondLargest = arr[i];
  }
  console.log("First Largest :" + firstLargest);
  console.log("Second Largest :" + secondLargest);
}

findSecondLargestNumber(arr);
```

---

## **Nested Loops**

### **Basic Nested Loop Examples**

#### **Example 1: Basic 3x3 Grid**

```javascript
for (let i = 0; i < 3; i++) {
  for (let j = 0; j < 3; j++) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output:
/*
i = 0 || j= 0
i = 0 || j= 1
i = 0 || j= 2
i = 1 || j= 0
i = 1 || j= 1
i = 1 || j= 2
i = 2 || j= 0
i = 2 || j= 1
i = 2 || j= 2
*/
```

#### **Example 2: Triangle Pattern (j < i)**

```javascript
for (let i = 0; i < 3; i++) {
  for (let j = 0; j < i; j++) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output:
/*
i = 1 || j= 0
i = 2 || j= 0
i = 2 || j= 1
*/
```

#### **Example 3: Triangle Pattern (j <= i)**

```javascript
for (let i = 0; i < 3; i++) {
  for (let j = 0; j <= i; j++) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output:
/*
i = 0 || j= 0
i = 1 || j= 0
i = 1 || j= 1
i = 2 || j= 0
i = 2 || j= 1
i = 2 || j= 2
*/
```

#### **Example 4: Reverse Inner Loop**

```javascript
for (let i = 0; i < 3; i++) {
  for (let j = i; j > 0; j--) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output:
/*
i = 1 || j= 1
i = 2 || j= 2
i = 2 || j= 1
*/
```

#### **Example 5: Descending Outer Loop**

```javascript
for (let i = 5; i > 0; i--) {
  for (let j = 0; j < i; j++) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output:
/*
i = 5 || j= 0
i = 5 || j= 1
i = 5 || j= 2
i = 5 || j= 3
i = 5 || j= 4
i = 4 || j= 0
i = 4 || j= 1
i = 4 || j= 2
i = 4 || j= 3
i = 3 || j= 0
i = 3 || j= 1
i = 3 || j= 2
i = 2 || j= 0
i = 2 || j= 1
i = 1 || j= 0
*/
```

#### **Example 6: Both Loops Descending**

```javascript
for (let i = 5; i > 0; i--) {
  for (let j = i; j > 0; j--) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output:
/*
i = 5 || j= 5
i = 5 || j= 4
i = 5 || j= 3
i = 5 || j= 2
i = 5 || j= 1
i = 4 || j= 4
i = 4 || j= 3
i = 4 || j= 2
i = 4 || j= 1
i = 3 || j= 3
i = 3 || j= 2
i = 3 || j= 1
i = 2 || j= 2
i = 2 || j= 1
i = 1 || j= 1
*/
```

---

## **Pattern Examples**

### **Star Patterns**

```javascript
// Right Triangle Pattern
function rightTriangle(n) {
  for (let i = 1; i <= n; i++) {
    let pattern = "";
    for (let j = 1; j <= i; j++) {
      pattern += "* ";
    }
    console.log(pattern);
  }
}

// Inverted Triangle Pattern
function invertedTriangle(n) {
  for (let i = n; i >= 1; i--) {
    let pattern = "";
    for (let j = 1; j <= i; j++) {
      pattern += "* ";
    }
    console.log(pattern);
  }
}

// Pyramid Pattern
function pyramid(n) {
  for (let i = 1; i <= n; i++) {
    let pattern = "";
    // Add spaces
    for (let j = 1; j <= n - i; j++) {
      pattern += " ";
    }
    // Add stars
    for (let k = 1; k <= 2 * i - 1; k++) {
      pattern += "*";
    }
    console.log(pattern);
  }
}
```

---

## **Common Pitfalls & Optimizations**

### ⚠️ **Common Pitfalls**

#### **1. Infinite Loops**

```javascript
// BAD: Forgot to increment counter
let i = 0;
while (i < 5) {
  console.log(i);
  // Missing i++ causes infinite loop
}

// GOOD: Always update counter
let i = 0;
while (i < 5) {
  console.log(i);
  i++; // Essential!
}
```

#### **2. Off-by-One Errors**

```javascript
// BAD: Using <= with array.length
for (let i = 0; i <= arr.length; i++) {
  console.log(arr[i]); // arr[arr.length] is undefined!
}

// GOOD: Use < with array.length
for (let i = 0; i < arr.length; i++) {
  console.log(arr[i]);
}
```

#### **3. Modifying Array While Iterating**

```javascript
// BAD: Removing elements while iterating forward
for (let i = 0; i < arr.length; i++) {
  if (arr[i] === targetValue) {
    arr.splice(i, 1); // This skips elements!
  }
}

// GOOD: Iterate backwards when removing elements
for (let i = arr.length - 1; i >= 0; i--) {
  if (arr[i] === targetValue) {
    arr.splice(i, 1);
  }
}
```

#### **4. Variable Scope Issues**

```javascript
// BAD: Using var in loops (JavaScript)
for (var i = 0; i < 5; i++) {
  setTimeout(() => console.log(i), 100); // Prints 5 five times
}

// GOOD: Use let for block scope
for (let i = 0; i < 5; i++) {
  setTimeout(() => console.log(i), 100); // Prints 0, 1, 2, 3, 4
}
```

### 🚀 **Performance Optimizations**

#### **1. Early Exit Strategies**

```javascript
// Use break to exit early when condition is met
function findFirstEven(arr) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] % 2 === 0) {
      return arr[i]; // Found it, no need to continue
    }
  }
  return null;
}

// Use continue to skip iterations
function processPositiveNumbers(arr) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] <= 0) {
      continue; // Skip negative/zero numbers
    }
    // Process positive numbers
    console.log(arr[i]);
  }
}
```

#### **2. Avoiding Nested Loops with Hash Maps**

```javascript
// BAD: O(n²) - Nested loops to find pairs
function findPairWithSum(arr, target) {
  for (let i = 0; i < arr.length; i++) {
    for (let j = i + 1; j < arr.length; j++) {
      if (arr[i] + arr[j] === target) {
        return [arr[i], arr[j]];
      }
    }
  }
  return null;
}

// GOOD: O(n) - Using hash map
function findPairWithSumOptimized(arr, target) {
  const seen = new Set();
  for (let num of arr) {
    const complement = target - num;
    if (seen.has(complement)) {
      return [complement, num];
    }
    seen.add(num);
  }
  return null;
}
```

#### **3. Cache Calculations**

```javascript
// BAD: Recalculating expensive operations
for (let i = 0; i < arr.length; i++) {
  if (arr[i] > Math.sqrt(arr.length)) {
    // sqrt calculated every iteration
    console.log(arr[i]);
  }
}

// GOOD: Cache expensive calculations
const threshold = Math.sqrt(arr.length);
for (let i = 0; i < arr.length; i++) {
  if (arr[i] > threshold) {
    console.log(arr[i]);
  }
}
```

#### **4. Use Appropriate Data Structures**

```javascript
// BAD: Using arrays for frequent lookups
const users = ["alice", "bob", "charlie"];
function isValidUser(username) {
  return users.indexOf(username) !== -1; // O(n) lookup
}

// GOOD: Using Set for O(1) lookups
const userSet = new Set(["alice", "bob", "charlie"]);
function isValidUser(username) {
  return userSet.has(username); // O(1) lookup
}
```

---

## **Advanced Examples**

### **Number Manipulation Functions**

#### **Count Digits**

```javascript
// Approach 1: Mathematical approach
function countDigits(num) {
  if (num == 0) return 1;
  let count = 0;
  num = Math.abs(num);
  while (num > 0) {
    num = Math.floor(num / 10);
    count++;
  }
  return count;
}
let res = countDigits(0);
console.log(res);

// Approach 2: Converting num to string
function countDigits1(num) {
  // Convert to string, remove minus and decimal point
  let str = Math.abs(num).toString().replace(".", "");
  return str.length;
}

console.log(countDigits1(0)); // 1
console.log(countDigits1(1234)); // 4
console.log(countDigits1(-567)); // 3
console.log(countDigits1(12.34)); // 4
console.log(countDigits1(0.005)); // 3
console.log(countDigits1(-0.01)); // 2
```

#### **Palindrome Check**

```javascript
function palindromeCheck(x) {
  if (x < 0) return false;

  let original = x;
  let rev = 0;

  while (x > 0) {
    let rem = x % 10;
    rev = rev * 10 + rem;
    x = Math.floor(x / 10);
  }

  return original === rev;
}

console.log(palindromeCheck(121)); // true
console.log(palindromeCheck(123)); // false
```

#### **Number Reversal**

```javascript
function reverseNumber(num) {
  let isNegative = num < 0;
  num = Math.abs(num);
  let reversed = 0;

  while (num > 0) {
    reversed = reversed * 10 + (num % 10);
    num = Math.floor(num / 10);
  }

  return isNegative ? -reversed : reversed;
}

console.log(reverseNumber(123)); // 321
console.log(reverseNumber(-456)); // -654
```

---

## **Summary**

This guide covers comprehensive loop usage in both JavaScript and Java, from basic syntax to advanced optimizations. Remember:

- Choose the right loop type for your use case
- Always consider performance implications
- Use modern language features when appropriate
- Avoid common pitfalls like infinite loops and off-by-one errors
- Optimize nested loops with better algorithms and data structures
- Break early when possible to improve performance
- Cache expensive calculations outside loops

Practice these patterns and optimizations to write more efficient and maintainable code!
