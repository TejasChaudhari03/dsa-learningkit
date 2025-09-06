# **Complete Guide to Recursion in JavaScript and Java**

## **Table of Contents**

1. [What is Recursion?](#what-is-recursion)
2. [Core Components](#core-components)
3. [How Recursion Works - Call Stack](#how-recursion-works---call-stack)
4. [Common Mistakes](#common-mistakes)
5. [When to Use Recursion](#when-to-use-recursion)
6. [Basic Examples](#basic-examples)
7. [Mathematical Functions](#mathematical-functions)
8. [Array Operations](#array-operations)
9. [Advanced Recursion Patterns](#advanced-recursion-patterns)
10. [Recursion vs Iteration](#recursion-vs-iteration)
11. [Optimization Techniques](#optimization-techniques)
12. [Common Recursion Problems](#common-recursion-problems)

---

## **What is Recursion?**

**Recursion** is a programming technique where a function calls itself to solve smaller versions of the same problem. It's a powerful tool for breaking down complex problems into simpler, more manageable subproblems.

Think of recursion like Russian nesting dolls (Matryoshka) - each doll contains a smaller version of itself until you reach the smallest doll that can't be opened further.

---

## **Core Components**

Every recursive function must have **two essential parts**:

### **1. Base Case**

- The condition under which the recursion stops
- Prevents infinite recursion
- Usually the simplest version of the problem that can be solved directly

### **2. Recursive Case**

- The part where the function calls itself
- Must work towards the base case
- The problem size should be reduced in each recursive call

```javascript
function recursiveFunction(parameter) {
  // Base Case - stops recursion
  if (baseCondition) {
    return baseValue;
  }

  // Recursive Case - calls itself with modified parameter
  return recursiveFunction(modifiedParameter);
}
```

---

## **How Recursion Works - Call Stack**

Understanding the call stack is crucial for mastering recursion:

1. **Function Call**: When a function is called, it's added to the call stack
2. **Stack Frame**: Each function call creates a stack frame containing local variables and parameters
3. **Recursive Calls**: Each recursive call adds a new layer to the call stack
4. **Base Case Reached**: When the base case is reached, functions start returning and are removed from the stack
5. **Stack Overflow**: If the base case is never reached, it leads to a stack overflow error

### **Visual Example: Factorial(3)**

```
Call Stack Visualization:
factorial(3)
├── factorial(2)
    ├── factorial(1)
        └── return 1
    └── return 2 * 1 = 2
└── return 3 * 2 = 6
```

---

## **Common Mistakes**

### ⚠️ **Critical Errors to Avoid**

#### **1. Missing Base Case**

```javascript
// BAD: No base case - infinite recursion
function badRecursion(n) {
  console.log(n);
  return badRecursion(n - 1); // Will never stop!
}

// GOOD: Has base case
function goodRecursion(n) {
  if (n <= 0) return; // Base case
  console.log(n);
  return goodRecursion(n - 1);
}
```

#### **2. Incorrect Base Case**

```javascript
// BAD: Base case may never be reached
function badFactorial(n) {
  if (n === 5) return 1; // What if n is not 5?
  return n * badFactorial(n - 1);
}

// GOOD: Proper base case
function goodFactorial(n) {
  if (n <= 1) return 1; // Will always be reached
  return n * goodFactorial(n - 1);
}
```

#### **3. Not Reducing Problem Size**

```javascript
// BAD: Problem size doesn't decrease
function badFunction(n) {
  if (n <= 0) return;
  return badFunction(n); // Same parameter - infinite loop!
}

// GOOD: Problem size decreases
function goodFunction(n) {
  if (n <= 0) return;
  return goodFunction(n - 1); // Parameter decreases
}
```

#### **4. Ignoring Time and Space Complexity**

```javascript
// BAD: Exponential time complexity
function badFibonacci(n) {
  if (n <= 1) return n;
  return badFibonacci(n - 1) + badFibonacci(n - 2); // Recalculates same values
}

// GOOD: Using memoization
const memo = {};
function goodFibonacci(n) {
  if (n <= 1) return n;
  if (memo[n]) return memo[n];
  memo[n] = goodFibonacci(n - 1) + goodFibonacci(n - 2);
  return memo[n];
}
```

---

## **When to Use Recursion**

### ✅ **Ideal Use Cases**

1. **Problems with Recursive Structure**: Factorial, Fibonacci, tree operations
2. **Divide and Conquer**: QuickSort, MergeSort, binary search
3. **Tree and Graph Traversals**: DFS, tree parsing, file system navigation
4. **Backtracking Problems**: N-Queens, maze solving, Sudoku
5. **Mathematical Computations**: GCD, power calculations, combinatorics
6. **String/Array Processing**: Palindrome checking, permutations

### ❌ **When to Avoid Recursion**

1. **Simple Iterative Problems**: Basic counting, linear searches
2. **Performance-Critical Code**: When stack overhead is significant
3. **Large Input Sizes**: Risk of stack overflow
4. **No Clear Recursive Structure**: When iteration is more natural

---

## **Basic Examples**

### **Printing Numbers**

#### **JavaScript**

```javascript
// Example: Print numbers from n to 1
function printDescending(n) {
  if (n <= 0) return; // Base case
  console.log(n);
  printDescending(n - 1); // Recursive case
}

printDescending(5); // Output: 5 4 3 2 1

// Example: Print numbers from 1 to n
function printAscending(current, n) {
  if (current > n) return; // Base case
  console.log(current);
  printAscending(current + 1, n); // Recursive case
}

printAscending(1, 5); // Output: 1 2 3 4 5

// Alternative approach using global variable (not recommended)
let x = 1;
function printAscendingGlobal(n) {
  if (x > n) return;
  console.log(x);
  x++;
  printAscendingGlobal(n);
}
```

#### **Java**

```java
public class RecursionBasics {
    // Example: Print numbers from n to 1
    public static void printDescending(int n) {
        if (n <= 0) return; // Base case
        System.out.println(n);
        printDescending(n - 1); // Recursive case
    }

    // Example: Print numbers from 1 to n
    public static void printAscending(int current, int n) {
        if (current > n) return; // Base case
        System.out.println(current);
        printAscending(current + 1, n); // Recursive case
    }

    public static void main(String[] args) {
        printDescending(5); // Output: 5 4 3 2 1
        printAscending(1, 5); // Output: 1 2 3 4 5
    }
}
```

---

## **Mathematical Functions**

### **1. Sum of Natural Numbers**

```javascript
// Sum of first n natural numbers
function sumNatural(n) {
  if (n <= 0) return 0; // Base case
  return n + sumNatural(n - 1); // Recursive case
}

console.log(sumNatural(5)); // Output: 15 (5+4+3+2+1)

// Trace: sumNatural(5)
// = 5 + sumNatural(4)
// = 5 + (4 + sumNatural(3))
// = 5 + (4 + (3 + sumNatural(2)))
// = 5 + (4 + (3 + (2 + sumNatural(1))))
// = 5 + (4 + (3 + (2 + (1 + sumNatural(0)))))
// = 5 + (4 + (3 + (2 + (1 + 0))))
// = 15
```

### **2. Factorial**

```javascript
function factorial(n) {
  if (n <= 1) return 1; // Base case
  return n * factorial(n - 1); // Recursive case
}

console.log(factorial(5)); // Output: 120 (5*4*3*2*1)

// Java version
/*
public static int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}
*/
```

### **3. Power of Two Check**

```javascript
function isPowerOfTwo(n) {
  if (n === 1) return true; // Base case - 2^0 = 1
  if (n % 2 !== 0 || n < 1) return false; // Base case - odd or negative
  return isPowerOfTwo(n / 2); // Recursive case
}

console.log(isPowerOfTwo(16)); // Output: true
console.log(isPowerOfTwo(18)); // Output: false

// Trace: isPowerOfTwo(16)
// = isPowerOfTwo(8)  (16/2 = 8)
// = isPowerOfTwo(4)  (8/2 = 4)
// = isPowerOfTwo(2)  (4/2 = 2)
// = isPowerOfTwo(1)  (2/2 = 1)
// = true
```

### **4. Greatest Common Divisor (GCD)**

```javascript
function gcd(a, b) {
  if (b === 0) return a; // Base case
  return gcd(b, a % b); // Recursive case - Euclidean algorithm
}

console.log(gcd(48, 18)); // Output: 6

// Trace: gcd(48, 18)
// = gcd(18, 48 % 18) = gcd(18, 12)
// = gcd(12, 18 % 12) = gcd(12, 6)
// = gcd(6, 12 % 6) = gcd(6, 0)
// = 6
```

---

## **Array Operations**

### **1. Array Sum**

```javascript
// Approach 1: Using array length as parameter
function arraySum(arr, n) {
  if (n <= 0) return 0; // Base case
  return arr[n - 1] + arraySum(arr, n - 1); // Recursive case
}

let arr = [1, 2, 3, 4, 5];
console.log(arraySum(arr, arr.length)); // Output: 15

// Approach 2: Using index parameter
function arraySumByIndex(arr, index) {
  if (index === arr.length) return 0; // Base case
  return arr[index] + arraySumByIndex(arr, index + 1); // Recursive case
}

console.log(arraySumByIndex(arr, 0)); // Output: 15

// Approach 3: Backwards from last index
function arraySumBackwards(arr, index) {
  if (index < 0) return 0; // Base case
  return arr[index] + arraySumBackwards(arr, index - 1); // Recursive case
}

console.log(arraySumBackwards(arr, arr.length - 1)); // Output: 15
```

### **2. Sum of Odd Elements**

```javascript
function sumOddElements(arr, index) {
  if (index >= arr.length) return 0; // Base case

  let currentElement = arr[index];
  let currentSum = currentElement % 2 !== 0 ? currentElement : 0;

  return currentSum + sumOddElements(arr, index + 1); // Recursive case
}

let arr = [1, 2, 3, 4, 5];
console.log(sumOddElements(arr, 0)); // Output: 9 (1+3+5)

// Alternative approach - backwards
function sumOddElementsBackwards(arr, index) {
  if (index < 0) return 0; // Base case

  let isOdd = arr[index] % 2 !== 0;
  let currentSum = isOdd ? arr[index] : 0;

  return currentSum + sumOddElementsBackwards(arr, index - 1);
}

console.log(sumOddElementsBackwards(arr, arr.length - 1)); // Output: 9
```

### **3. Find Maximum Element**

```javascript
function findMax(arr, index, currentMax) {
  if (index >= arr.length) return currentMax; // Base case

  let newMax = Math.max(currentMax, arr[index]);
  return findMax(arr, index + 1, newMax); // Recursive case
}

let arr = [3, 7, 2, 9, 1];
console.log(findMax(arr, 0, arr[0])); // Output: 9

// Alternative approach
function findMaxSimple(arr, n) {
  if (n === 1) return arr[0]; // Base case

  let maxOfRest = findMaxSimple(arr, n - 1);
  return Math.max(arr[n - 1], maxOfRest);
}

console.log(findMaxSimple(arr, arr.length)); // Output: 9
```

---

## **Advanced Recursion Patterns**

### **1. Fibonacci Sequence**

```javascript
// Basic Fibonacci (inefficient - exponential time)
function fibonacciBasic(n) {
  if (n <= 1) return n; // Base case
  return fibonacciBasic(n - 1) + fibonacciBasic(n - 2); // Recursive case
}

console.log(fibonacciBasic(6)); // Output: 8

// Optimized Fibonacci with memoization
function fibonacciMemo(n, memo = {}) {
  if (n <= 1) return n;
  if (memo[n]) return memo[n];

  memo[n] = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
  return memo[n];
}

console.log(fibonacciMemo(50)); // Much faster for large numbers
```

### **2. Binary Search**

```javascript
function binarySearch(arr, target, left = 0, right = arr.length - 1) {
  if (left > right) return -1; // Base case - not found

  let mid = Math.floor((left + right) / 2);

  if (arr[mid] === target) return mid; // Base case - found

  if (arr[mid] > target) {
    return binarySearch(arr, target, left, mid - 1); // Search left half
  } else {
    return binarySearch(arr, target, mid + 1, right); // Search right half
  }
}

let sortedArr = [1, 3, 5, 7, 9, 11, 13];
console.log(binarySearch(sortedArr, 7)); // Output: 3 (index)
console.log(binarySearch(sortedArr, 4)); // Output: -1 (not found)
```

### **3. String Reversal**

```javascript
function reverseString(str) {
  if (str.length <= 1) return str; // Base case
  return reverseString(str.slice(1)) + str[0]; // Recursive case
}

console.log(reverseString("hello")); // Output: "olleh"

// Trace: reverseString("hello")
// = reverseString("ello") + "h"
// = (reverseString("llo") + "e") + "h"
// = ((reverseString("lo") + "l") + "e") + "h"
// = (((reverseString("o") + "l") + "l") + "e") + "h"
// = (((("o") + "l") + "l") + "e") + "h"
// = "olleh"
```

### **4. Palindrome Check**

```javascript
function isPalindrome(str, start = 0, end = str.length - 1) {
  if (start >= end) return true; // Base case - single char or empty

  if (str[start] !== str[end]) return false; // Base case - mismatch

  return isPalindrome(str, start + 1, end - 1); // Recursive case
}

console.log(isPalindrome("racecar")); // Output: true
console.log(isPalindrome("hello")); // Output: false
```

---

## **Tree and Graph Operations**

### **1. Tree Traversal (Depth-First)**

```javascript
// Tree node structure
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

// Preorder traversal (Root -> Left -> Right)
function preorderTraversal(root, result = []) {
  if (!root) return result; // Base case

  result.push(root.val); // Process root
  preorderTraversal(root.left, result); // Traverse left subtree
  preorderTraversal(root.right, result); // Traverse right subtree

  return result;
}

// Inorder traversal (Left -> Root -> Right)
function inorderTraversal(root, result = []) {
  if (!root) return result; // Base case

  inorderTraversal(root.left, result); // Traverse left subtree
  result.push(root.val); // Process root
  inorderTraversal(root.right, result); // Traverse right subtree

  return result;
}

// Example usage
let root = new TreeNode(
  1,
  new TreeNode(2, new TreeNode(4), new TreeNode(5)),
  new TreeNode(3)
);

console.log(preorderTraversal(root)); // [1, 2, 4, 5, 3]
console.log(inorderTraversal(root)); // [4, 2, 5, 1, 3]
```

### **2. Calculate Tree Depth**

```javascript
function maxDepth(root) {
  if (!root) return 0; // Base case - empty tree

  let leftDepth = maxDepth(root.left);
  let rightDepth = maxDepth(root.right);

  return Math.max(leftDepth, rightDepth) + 1; // Add 1 for current level
}

console.log(maxDepth(root)); // Output: 3
```

---

## **Recursion vs Iteration**

### **Comparison Table**

| Aspect             | Recursion                                   | Iteration                 |
| ------------------ | ------------------------------------------- | ------------------------- |
| **Readability**    | Often more intuitive for recursive problems | Can be more verbose       |
| **Memory Usage**   | Higher (call stack)                         | Lower (no function calls) |
| **Performance**    | Slower (function call overhead)             | Faster                    |
| **Stack Overflow** | Possible with deep recursion                | Not applicable            |
| **Debugging**      | Can be harder to trace                      | Easier to debug           |

### **Converting Recursion to Iteration**

```javascript
// Recursive factorial
function factorialRecursive(n) {
  if (n <= 1) return 1;
  return n * factorialRecursive(n - 1);
}

// Iterative factorial
function factorialIterative(n) {
  let result = 1;
  for (let i = 2; i <= n; i++) {
    result *= i;
  }
  return result;
}

console.log(factorialRecursive(5)); // 120
console.log(factorialIterative(5)); // 120
```

---

## **Optimization Techniques**

### **1. Memoization**

```javascript
// Without memoization - O(2^n)
function fibSlow(n) {
  if (n <= 1) return n;
  return fibSlow(n - 1) + fibSlow(n - 2);
}

// With memoization - O(n)
const memo = {};
function fibFast(n) {
  if (n <= 1) return n;
  if (memo[n]) return memo[n];

  memo[n] = fibFast(n - 1) + fibFast(n - 2);
  return memo[n];
}

// Using Map for memoization
function fibWithMap(n, memo = new Map()) {
  if (n <= 1) return n;
  if (memo.has(n)) return memo.get(n);

  const result = fibWithMap(n - 1, memo) + fibWithMap(n - 2, memo);
  memo.set(n, result);
  return result;
}
```

### **2. Tail Recursion**

```javascript
// Non-tail recursive (accumulates on return)
function factorialNonTail(n) {
  if (n <= 1) return 1;
  return n * factorialNonTail(n - 1); // Operation after recursive call
}

// Tail recursive (accumulates before recursive call)
function factorialTail(n, accumulator = 1) {
  if (n <= 1) return accumulator;
  return factorialTail(n - 1, n * accumulator); // No operation after recursive call
}

console.log(factorialTail(5)); // 120

// Tail recursive Fibonacci
function fibTail(n, a = 0, b = 1) {
  if (n === 0) return a;
  if (n === 1) return b;
  return fibTail(n - 1, b, a + b);
}
```

### **3. Avoiding Deep Recursion**

```javascript
// Convert deep recursion to iterative approach with explicit stack
function iterativeInorder(root) {
  if (!root) return [];

  const result = [];
  const stack = [];
  let current = root;

  while (current || stack.length > 0) {
    // Go to leftmost node
    while (current) {
      stack.push(current);
      current = current.left;
    }

    // Process current node
    current = stack.pop();
    result.push(current.val);

    // Move to right subtree
    current = current.right;
  }

  return result;
}
```

---

## **Common Recursion Problems**

### **1. Tower of Hanoi**

```javascript
function towerOfHanoi(n, from, to, aux) {
  if (n === 1) {
    console.log(`Move disk 1 from ${from} to ${to}`);
    return;
  }

  towerOfHanoi(n - 1, from, aux, to); // Move n-1 disks to auxiliary
  console.log(`Move disk ${n} from ${from} to ${to}`); // Move largest disk
  towerOfHanoi(n - 1, aux, to, from); // Move n-1 disks to destination
}

towerOfHanoi(3, "A", "C", "B");
```

### **2. Generate All Permutations**

```javascript
function generatePermutations(arr, current = [], result = []) {
  if (current.length === arr.length) {
    result.push([...current]); // Base case - found complete permutation
    return;
  }

  for (let i = 0; i < arr.length; i++) {
    if (current.includes(arr[i])) continue; // Skip if already used

    current.push(arr[i]); // Choose
    generatePermutations(arr, current, result); // Recurse
    current.pop(); // Backtrack
  }

  return result;
}

console.log(generatePermutations([1, 2, 3]));
// Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
```

### **3. N-Queens Problem**

```javascript
function solveNQueens(n) {
  const result = [];
  const board = Array(n)
    .fill()
    .map(() => Array(n).fill("."));

  function isSafe(row, col) {
    // Check column
    for (let i = 0; i < row; i++) {
      if (board[i][col] === "Q") return false;
    }

    // Check diagonal
    for (let i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] === "Q") return false;
    }

    // Check anti-diagonal
    for (let i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
      if (board[i][j] === "Q") return false;
    }

    return true;
  }

  function backtrack(row) {
    if (row === n) {
      result.push(board.map((r) => r.join(""))); // Found solution
      return;
    }

    for (let col = 0; col < n; col++) {
      if (isSafe(row, col)) {
        board[row][col] = "Q"; // Place queen
        backtrack(row + 1); // Recurse
        board[row][col] = "."; // Backtrack
      }
    }
  }

  backtrack(0);
  return result;
}

console.log(solveNQueens(4).length); // Output: 2 (number of solutions)
```

---

## **Java Complete Examples**

```java
public class RecursionComplete {
    // Sum of first n natural numbers
    public static int sum(int n) {
        if (n <= 0) return 0;
        return n + sum(n - 1);
    }

    // Array sum
    public static int arraySum(int[] arr, int n) {
        if (n <= 0) return 0;
        return arr[n - 1] + arraySum(arr, n - 1);
    }

    // Array sum - another approach
    public static int arraySumIndex(int[] arr, int index) {
        if (index >= arr.length) return 0;
        return arr[index] + arraySumIndex(arr, index + 1);
    }

    // Sum of odd elements
    public static int sumOddElements(int[] arr, int index) {
        if (index >= arr.length) return 0;

        int current = (arr[index] % 2 != 0) ? arr[index] : 0;
        return current + sumOddElements(arr, index + 1);
    }

    // Factorial
    public static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    // Power of two check
    public static boolean isPowerOfTwo(int n) {
        if (n == 1) return true;
        if (n % 2 != 0 || n < 1) return false;
        return isPowerOfTwo(n / 2);
    }

    // Binary search
    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) return mid;
        if (arr[mid] > target) return binarySearch(arr, target, left, mid - 1);
        return binarySearch(arr, target, mid + 1, right);
    }

    public static void main(String[] args) {
        // Test all functions
        System.out.println("Sum of 5: " + sum(5)); // 15

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Array sum: " + arraySum(arr, arr.length)); // 15
        System.out.println("Array sum (index): " + arraySumIndex(arr, 0)); // 15
        System.out.println("Sum odd: " + sumOddElements(arr, 0)); // 9

        System.out.println("Factorial 5: " + factorial(5)); // 120
        System.out.println("Is 16 power of 2: " + isPowerOfTwo(16)); // true

        int[] sorted = {1, 3, 5, 7, 9};
        System.out.println("Binary search 7: " + binarySearch(sorted, 7, 0, sorted.length - 1)); // 3
    }
}
```

---

## **Summary and Best Practices**

### **✅ Key Takeaways**

1. **Always define a clear base case** that will eventually be reached
2. **Ensure the problem size decreases** with each recursive call
3. **Consider time and space complexity** - recursion uses O(n) stack space
4. **Use memoization** to optimize overlapping subproblems
5. **Prefer tail recursion** when possible for better optimization
6. **Consider iterative alternatives** for performance-critical code
7. **Debug by tracing the call stack** to understand the flow
8. **Handle edge cases** like empty arrays, negative numbers, etc.

### **🎯 When to Choose Recursion**

- The problem has a **natural recursive structure**
- **Code readability** is more important than performance
- Working with **trees, graphs, or nested structures**
- Implementing **divide-and-conquer algorithms**
- **Backtracking problems** that require trying multiple solutions

### **🔧 Optimization Checklist**

- [ ] Use memoization for overlapping subproblems
- [ ] Convert to tail recursion if possible
- [ ] Consider iterative approach for deep recursion
- [ ] Implement early termination conditions
- [ ] Cache expensive computations
- [ ] Use appropriate data structures

Recursion is a powerful tool that, when used correctly, can make complex problems much more manageable and elegant. Practice these examples and patterns to master recursive thinking!
