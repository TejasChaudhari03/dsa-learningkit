# Binary Search Algorithm - Comprehensive Notes

## Overview

Binary Search is an efficient algorithm for finding a target value within a **sorted array** by repeatedly dividing the search interval in half. It has a time complexity of **O(log n)**, making it significantly faster than linear search for large datasets.

## Three Main Binary Search Variants

### Variant 1: `while(l <= r)` - Classic Binary Search

```java
while(l <= r) {
    int m = l + (r - l) / 2;
    // if condition met: l = m + 1;
    // else: r = m - 1;
}
```

**Usage:**

- Standard binary search for **exact matches**
- Finding exact values in sorted arrays
- Both pointers move past each other when target not found
- After loop: `l > r`, return based on problem requirements

**Key Characteristics:**

- Terminates when `l > r`
- Both boundaries are **inclusive**
- Updates: `l = m + 1` or `r = m - 1` (always exclude middle)
- Returns exact match or -1

**Mental Model**

- “Check mid → throw it away → move left or right”

---

### Variant 2: `while(l < r)` - Lower/Upper Bound Search

```java
while(l < r) {
    int m = l + (r - l) / 2;
    // if condition: l = m + 1;
    // else: r = m;
}
// After loop: l == r (converged to single position)
```

**Usage:**

- Finding **first/last occurrence** of a value
- Finding **lower bound** (first element >= target)
- Finding **upper bound** (first element > target)
- Finding insertion position in sorted array

**Key Characteristics:**

- Terminates when `l == r` (converges to single element)
- One side excludes middle: `l = m + 1`
- Other side includes middle: `r = m`
- Returns the converged position

**Mental Model**

- “I know the answer exists — just shrink to it”

---

### Variant 3: `while(l + 1 < r)` - Prevents Infinite Loop

```java
while(l + 1 < r) {
    int m = l + (r - l) / 2;
    // if condition: l = m;
    // else: r = m;
}
// After loop: check both l and r
```

**Usage:**

- When you need to **preserve both boundaries**
- Avoiding infinite loops when both updates include middle
- Problems requiring checking neighbors
- Final answer might be at `l` or `r`
- Floating-point search | Peak / boundary problems | Preciion-based problems

**Key Characteristics:**

- Terminates when elements are adjacent
- Both updates include middle: `l = m` and `r = m`
- Must check both `l` and `r` after loop
- Safer for complex conditions

**Mental Model**

- “Narrow until only two suspects remain”

## Additional Binary Search Variants

Yes! There are several more binary search variants beyond the three mentioned. Here's a comprehensive list:

---

## Variant 4: `while(l < r)` with Right-Biased Mid

```java
while(l < r) {
    int m = l + (r - l + 1) / 2;  // Right-biased (rounds up)
    // if condition: r = m - 1;
    // else: l = m;
}
```

**Usage:**

- Finding **last occurrence** or **rightmost position**
- When `l = m` update is needed (prevents infinite loop)
- Upper bound searches

**Why needed?**

- Regular `m = l + (r - l) / 2` is left-biased (rounds down)
- If we use `l = m` with left-biased mid, infinite loop occurs when `l = r - 1`
- Example: `l = 3, r = 4` → `m = 3` → `l = m = 3` (stuck!)

**Example - Find Last Occurrence:**

```java
// Find rightmost position where arr[i] <= target
while(l < r) {
    int m = l + (r - l + 1) / 2;  // Round up!
    if(arr[m] <= target) {
        l = m;  // Safe because mid rounds up
    } else {
        r = m - 1;
    }
}
```

---

## Variant 5: Three-Way Binary Search (Ternary Search)

```java
while(l <= r) {
    int m1 = l + (r - l) / 3;
    int m2 = r - (r - l) / 3;

    // Trisect the range and decide which third to keep
    if(condition at m1) {
        r = m1 - 1;
    } else if(condition at m2) {
        l = m2 + 1;
    } else {
        l = m1 + 1;
        r = m2 - 1;
    }
}
```

**Usage:**

- Finding **maximum/minimum in unimodal functions**
- Optimization problems
- When function has single peak/valley

**Complexity:**

- Time: O(log₃ n) ≈ O(log n) but with larger constant factor
- Generally not better than binary search for discrete problems

---

## Variant 6: Binary Search on Answer Space

```java
int l = minPossibleAnswer, r = maxPossibleAnswer;
int result = -1;

while(l <= r) {
    int mid = l + (r - l) / 2;

    if(isPossible(mid)) {  // Can we achieve 'mid'?
        result = mid;
        l = mid + 1;  // Try for better answer
    } else {
        r = mid - 1;  // mid is not achievable
    }
}
return result;
```

**Usage:**

- **Minimize the maximum** or **maximize the minimum**
- Capacity/allocation problems
- When direct search is hard but validation is easy

**Classic Problems:**

- Minimum capacity to ship packages within D days
- Koko eating bananas (minimum eating speed)
- Split array largest sum
- Magnetic force between balls

**Example - Koko Eating Bananas:**

```java
// Find minimum eating speed to finish in h hours
int l = 1, r = max(piles);
while(l <= r) {
    int speed = l + (r - l) / 2;
    if(canFinish(piles, speed, h)) {
        result = speed;
        r = speed - 1;  // Try slower speed
    } else {
        l = speed + 1;  // Need faster speed
    }
}
```

---

## Variant 7: Binary Search with Two Pointers

```java
while(l < r) {
    int m = l + (r - l) / 2;

    if(condition) {
        // Use two pointer technique at mid
        int left = m, right = m;
        while(left >= l && condition1) left--;
        while(right <= r && condition2) right++;

        // Update based on findings
    }
}
```

**Usage:**

- Finding range satisfying conditions
- Peak finding in mountain arrays
- Complex constraint problems

---

## Variant 8: Fractional/Real Number Binary Search

```java
double l = 0.0, r = 1000.0;
double epsilon = 1e-6;  // Precision

while(r - l > epsilon) {
    double mid = l + (r - l) / 2.0;

    if(f(mid) < target) {
        l = mid;
    } else {
        r = mid;
    }
}
// Answer is approximately l (or r)
```

**Usage:**

- Finding square root with decimals
- Continuous function optimization
- Physics/engineering calculations

**Key Difference:**

- No integer division
- Loop based on precision, not equality
- Don't exclude mid (use `l = mid`, `r = mid`)

---

## Variant 9: Binary Lifting / Binary Jumping

```java
// Find kth ancestor in a tree
int ancestor = node;
for(int i = 0; i < LOG; i++) {
    if((k & (1 << i)) != 0) {
        ancestor = parent[ancestor][i];
    }
}
```

**Usage:**

- Tree problems (LCA - Lowest Common Ancestor)
- Sparse table queries
- Jump pointer technique

**Not traditional binary search but uses binary decomposition**

---

## Variant 10: Parallel Binary Search

```java
// Process multiple queries simultaneously
// Each query maintains its own [l, r] range
for(int query : queries) {
    while(l[query] <= r[query]) {
        int mid = l[query] + (r[query] - l[query]) / 2;
        // Process all queries at this mid value together
        // Update l[query] or r[query] based on result
    }
}
```

**Usage:**

- Multiple similar queries
- Offline query processing
- When queries can be batched efficiently

---

## Variant 11: Exponential Search + Binary Search

```java
// First, find range using exponential growth
int bound = 1;
while(bound < n && arr[bound] < target) {
    bound *= 2;
}

// Then binary search in [bound/2, min(bound, n-1)]
int l = bound / 2;
int r = Math.min(bound, n - 1);
// Standard binary search...
```

**Usage:**

- Unbounded/infinite arrays
- When target is near beginning
- Streaming data scenarios

**Complexity:**

- O(log k) where k is position of target
- Better than O(log n) when k << n

---

## Variant 12: Binary Search with Predicate Function

```java
// Generic template using predicate
int binarySearch(Predicate<Integer> predicate) {
    int l = 0, r = n - 1;

    while(l < r) {
        int m = l + (r - l) / 2;

        if(predicate.test(m)) {
            r = m;  // First position where predicate is true
        } else {
            l = m + 1;
        }
    }
    return l;
}
```

**Usage:**

- Abstract binary search logic
- Reusable template for multiple problems
- Clean separation of search logic and condition

---

## Binary Search Invariants (Very Important)

Before writing code, always decide:

1. Is the answer **guaranteed to exist**?
2. Is the condition **monotonic**?
3. Do I need:
   - Exact value?
   - First valid?
   - Last valid?

Wrong invariant = infinite loop or off-by-one bug.

---

## Common Binary Search Problems

### 1. Square Root (Integer Part)

**Problem:** Find `floor(√x)` for a non-negative integer `x`

**Why Variant 1**

- Exact match possible
- Otherwise track last valid value

**Approach:**

- Search space: `[1, x/2]` (for x ≥ 2)
- Check if `mid * mid <= x`
- Store last valid answer when `mid * mid <= x`

**Logic:**

```java
int left = 1, right = x / 2, ans = 0;
while (left <= right) {
    int mid = left + (right - left) / 2;
    long square = (long) mid * mid;  // Avoid overflow

    if (square == x) return mid;      // Perfect square
    else if (square < x) {
        ans = mid;                    // Store valid answer
        left = mid + 1;               // Search larger values
    } else {
        right = mid - 1;              // Search smaller values
    }
}
return ans;
```

**Why return `r` in some implementations?**

- After loop ends with `l > r`, `r` holds the largest value where `mid * mid <= x`

**Complexity:**

- Time: O(log n)
- Space: O(1)

---

### 2. Guess Number Game

**Problem:** Guess a number between 1 and n using an API that returns:

**Why Variant 1**

- Search space is clear `[1, n]`
- Exact match required

* `-1` if guess is too high
* `1` if guess is too low
* `0` if correct

**Logic:**

```java
int left = 1, right = n;
while (left <= right) {
    int mid = left + (right - left) / 2;
    int result = guess(mid);

    if (result == 0) return mid;      // Found it
    else if (result < 0) right = mid - 1;  // Too high
    else left = mid + 1;              // Too low
}
```

**Complexity:**

- Time: O(log n)
- Space: O(1)

---

### 3. Search in Rotated Sorted Array

**Problem:** Search for target in a sorted array rotated at an unknown pivot

**Why Variant 1**

- Need exact index
- Direction decided by sorted half

* Example: `[4,5,6,7,0,1,2]` (rotated from `[0,1,2,4,5,6,7]`)

**Key Insight:**

- At least one half of the array is always sorted
- Determine which half is sorted, then check if target is in that range

**Logic:**

```java
int l = 0, r = nums.length - 1;
while (l <= r) {
    int m = l + (r - l) / 2;

    if (nums[m] == target) return m;

    // Check if left half is sorted
    if (nums[l] <= nums[m]) {
        // Target in sorted left half?
        if (target >= nums[l] && target < nums[m]) {
            r = m - 1;  // Search left
        } else {
            l = m + 1;  // Search right
        }
    }
    // Right half must be sorted
    else {
        // Target in sorted right half?
        if (target > nums[m] && target <= nums[r]) {
            l = m + 1;  // Search right
        } else {
            r = m - 1;  // Search left
        }
    }
}
return -1;
```

**Why `nums[l] <= nums[m]` to check sorted?**

- If left half is sorted, `nums[l]` must be ≤ `nums[m]`
- If not, rotation point is in left half, so right half is sorted

**Complexity:**

- Time: O(log n)
- Space: O(1)

---

## Important Binary Search Techniques

### 1. Preventing Integer Overflow

```java
int mid = left + (right - left) / 2;  // Better than (left + right) / 2
```

### 2. Handling Multiplication Overflow

```java
long square = (long) mid * mid;  // Cast to long before multiplication
```

### 3. Choosing the Right Variant

- **Exact match?** → Use `while(l <= r)`
- **First/last occurrence?** → Use `while(l < r)` with careful boundary updates
- **Complex conditions?** → Use `while(l + 1 < r)` for safety

### 4. Search Space Definition

- Always clearly define what `l` and `r` represent
- Ensure the answer exists within `[l, r]`

---

## Common Pitfalls

1. **Infinite Loop:** Using `l = m` and `r = m` with `while(l < r)`
2. **Off-by-one Errors:** Incorrect boundary updates
3. **Integer Overflow:** Not handling large sums properly
4. **Wrong Variant:** Using wrong template for the problem type

---

## When to Use Binary Search

✅ **Use when:**

- Array/list is sorted (or rotated sorted)
- Searching in a monotonic space
- Answer space has binary property (can eliminate half)
- Need O(log n) time complexity

❌ **Don't use when:**

- Data is unsorted and cannot be sorted
- Need to find all occurrences simultaneously
- Simple linear scan is more efficient for small data

## Common Binary Search Mistakes

- Using `while(l < r)` when exact match needed
- Forgetting overflow in `mid * mid`
- Wrong boundary update (`m` vs `m±1`)
- No invariant defined

---

## How to Choose the Right Variant (Cheat Sheet)

| Requirement              | Use         |
| ------------------------ | ----------- |
| Exact element            | `l <= r`    |
| First true / lower bound | `l < r`     |
| Precision / boundary     | `l + 1 < r` |

---

# Corrected Binary Search Decision Tree

## Comprehensive Decision Tree

```
What are you searching for?
│
├─── EXACT VALUE in sorted array?
│    └─→ Variant 1 (while l <= r)
│        • Classic binary search
│        • Returns index or -1
│        • Both pointers move past mid
│
├─── FIRST/LEFTMOST occurrence (lower bound)?
│    └─→ Variant 2 (while l < r, left-biased mid)
│        • First position where condition is true
│        • First occurrence of target
│        • Minimum valid answer
│
├─── LAST/RIGHTMOST occurrence (upper bound)?
│    └─→ Variant 4 (while l < r, right-biased mid)
│        • Last position where condition is true
│        • Last occurrence of target
│        • Maximum valid answer
│
├─── PEAK in DISCRETE array?
│    └─→ Variant 2 (while l < r)
│        • Peak element in array
│        • Mountain array peak
│        • Local maximum in discrete data
│
├─── OPTIMAL VALUE (answer space search)?
│    └─→ Variant 6 (while l <= r, binary search on answer)
│        • Minimize the maximum
│        • Maximize the minimum
│        • Capacity/allocation problems
│        • "Can we achieve X?" type problems
│
├─── CONTINUOUS/REAL NUMBER optimization?
│    ├─→ Unimodal function (single peak/valley)?
│    │   └─→ Variant 5 (Ternary Search)
│    │       • Mathematical optimization
│    │       • Finding max/min of f(x)
│    │       • Continuous domains
│    │
│    └─→ Binary property in continuous domain?
│        └─→ Variant 8 (Fractional Binary Search)
│            • Square root with decimals
│            • Precision-based search
│            • Physics/engineering calculations
│
├─── UNBOUNDED/INFINITE array?
│    └─→ Variant 11 (Exponential Search + Binary Search)
│        • Unknown array size
│        • Streaming data
│        • Target likely near beginning
│
├─── TREE/GRAPH problem?
│    └─→ Variant 9 (Binary Lifting)
│        • kth ancestor
│        • Lowest Common Ancestor (LCA)
│        • Level/distance queries
│
├─── MULTIPLE SIMILAR QUERIES (offline)?
│    └─→ Variant 10 (Parallel Binary Search)
│        • Batch processing
│        • Multiple queries with same structure
│        • Optimization for many searches
│
├─── NEED TO CHECK BOTH BOUNDARIES?
│    └─→ Variant 3 (while l + 1 < r)
│        • Complex conditions
│        • Need to verify neighbors
│        • Avoid infinite loops with tricky updates
│        • Final answer could be at l or r
│
└─── GENERIC/REUSABLE template?
     └─→ Variant 12 (Predicate-based)
         • Abstract binary search logic
         • Clean code organization
         • Multiple problems with same pattern
```

---

## Quick Reference Guide

### By Problem Characteristic

| Characteristic                  | Variant | Example Problems                               |
| ------------------------------- | ------- | ---------------------------------------------- |
| **Sorted array + exact target** | 1       | Standard binary search                         |
| **Find first/minimum valid**    | 2       | First bad version, search insert position      |
| **Find last/maximum valid**     | 4       | Last position <= target                        |
| **Discrete peak/valley**        | 2       | Peak element, mountain array                   |
| **Answer space optimization**   | 6       | Koko eating bananas, capacity to ship packages |
| **Continuous optimization**     | 5       | Maximize f(x) where f is unimodal              |
| **Real number search**          | 8       | √x with decimals, nth root                     |
| **Unknown size array**          | 11      | Search in infinite sorted array                |
| **Tree queries**                | 9       | LCA, kth ancestor                              |
| **Batch queries**               | 10      | Multiple similar searches                      |
| **Complex boundaries**          | 3       | Rotated array variants, tricky conditions      |
| **Code reusability**            | 12      | Generic template for multiple problems         |

---

## Decision Flowchart (Step-by-Step)

```
START
  ↓
[1] Is it a tree/graph problem?
  YES → Variant 9 (Binary Lifting)
  NO ↓

[2] Is the domain continuous (real numbers)?
  YES → [2a] Is it unimodal (single peak)?
         YES → Variant 5 (Ternary)
         NO → Variant 8 (Fractional Binary)
  NO ↓

[3] Is the array unbounded/infinite?
  YES → Variant 11 (Exponential Search)
  NO ↓

[4] Do you have multiple similar queries to batch?
  YES → Variant 10 (Parallel Binary Search)
  NO ↓

[5] Are you searching for an optimal value (not in array)?
  YES → Variant 6 (Answer Space Search)
  NO ↓

[6] Are you finding a peak/local maximum in discrete array?
  YES → Variant 2 (Binary Search with l < r)
  NO ↓

[7] Do you need exact match in sorted array?
  YES → Variant 1 (Classic: l <= r)
  NO ↓

[8] Do you need first/leftmost occurrence?
  YES → Variant 2 (Left-biased: l < r)
  NO ↓

[9] Do you need last/rightmost occurrence?
  YES → Variant 4 (Right-biased: l < r with mid+1)
  NO ↓

[10] Do you have complex boundary conditions?
  YES → Variant 3 (l + 1 < r)
  NO ↓

[11] Want generic reusable template?
  YES → Variant 12 (Predicate-based)
  NO → Re-evaluate your problem
```

---

## By Loop Condition

```
while (l <= r)
├─→ Variant 1: Exact match search
└─→ Variant 6: Answer space search

while (l < r)
├─→ Variant 2: First occurrence (mid = l + (r-l)/2)
├─→ Variant 4: Last occurrence (mid = l + (r-l+1)/2)
└─→ Variant 12: Predicate-based template

while (l + 1 < r)
└─→ Variant 3: Complex boundaries, check both l and r

while (r - l > epsilon)
└─→ Variant 8: Fractional/continuous search

Ternary (two mids)
└─→ Variant 5: Unimodal function optimization

Exponential growth then binary
└─→ Variant 11: Unbounded array

Binary decomposition
└─→ Variant 9: Binary lifting for trees

Multiple ranges simultaneously
└─→ Variant 10: Parallel binary search
```

---

## Common Problem Types → Variant Mapping

### LeetCode-Style Problems

| Problem                              | Variant | Key Insight                    |
| ------------------------------------ | ------- | ------------------------------ |
| Binary Search                        | 1       | Exact match                    |
| Search Insert Position               | 2       | First position >= target       |
| Find First and Last Position         | 2 + 4   | Use both left and right biased |
| Peak Element                         | 2       | Follow upward slope            |
| Search in Rotated Sorted Array       | 1       | Determine sorted half          |
| Koko Eating Bananas                  | 6       | Binary search on speed         |
| Capacity To Ship Packages            | 6       | Binary search on capacity      |
| Split Array Largest Sum              | 6       | Binary search on sum           |
| Sqrt(x) - Integer                    | 1 or 2  | Discrete answer                |
| Sqrt(x) - Decimal                    | 8       | Continuous domain              |
| Find Minimum in Rotated Array        | 2       | Find rotation point            |
| Median of Two Sorted Arrays          | 3       | Complex partition logic        |
| Kth Smallest in Multiplication Table | 6       | Count-based search             |
| Aggressive Cows / Magnetic Force     | 6       | Maximize minimum distance      |

---

## Updated Summary Table (Corrected)

| Variant | Loop        | Mid           | Primary Use Case                     | Secondary Use Cases      |
| ------- | ----------- | ------------- | ------------------------------------ | ------------------------ |
| **1**   | `l <= r`    | `l+(r-l)/2`   | Exact match                          | Search in rotated array  |
| **2**   | `l < r`     | `l+(r-l)/2`   | First occurrence, lower bound        | **Peak finding**         |
| **3**   | `l+1 < r`   | `l+(r-l)/2`   | Complex boundaries                   | Median of two arrays     |
| **4**   | `l < r`     | `l+(r-l+1)/2` | Last occurrence, upper bound         | Rightmost valid position |
| **5**   | Ternary     | Two mids      | **Continuous unimodal optimization** | Mathematical functions   |
| **6**   | `l <= r`    | Answer space  | Min-max, max-min problems            | Capacity, allocation     |
| **7**   | Hybrid      | With pointers | Range finding                        | Advanced problems        |
| **8**   | `r-l > ε`   | Real          | Continuous binary property           | Decimal precision        |
| **9**   | Lifting     | Bit-based     | Tree ancestor queries                | LCA, sparse table        |
| **10**  | Multiple    | Parallel      | Batch query processing               | Offline algorithms       |
| **11**  | Exponential | After bounds  | Unbounded arrays                     | Infinite/streaming data  |
| **12**  | `l < r`     | Generic       | Reusable template                    | Clean abstractions       |

---

## Key Corrections from Previous Version

### ❌ **WRONG** (Previous):

```
Finding peak/valley? → Variant 5 (Ternary)
```

### ✅ **CORRECT** (Updated):

```
Finding peak in discrete array? → Variant 2 (Binary Search)
Finding peak in continuous function? → Variant 5 (Ternary Search)
```

---

## Pro Tips for Choosing

1. **Start with the data type:**
   - Discrete indices → Variants 1-4, 6
   - Continuous domain → Variants 5, 8

2. **Consider what you're finding:**
   - Specific value → Variant 1
   - First/last → Variants 2/4
   - Optimal answer → Variant 6
   - Peak → Variant 2 (discrete) or 5 (continuous)

3. **When in doubt:**
   - Use Variant 2 for most "find first X" problems
   - Use Variant 1 for exact matches
   - Use Variant 6 for optimization problems

4. **Red flags for special variants:**
   - "Unbounded" → Variant 11
   - "Tree/ancestor" → Variant 9
   - "Precision/decimal" → Variant 8
   - "Multiple queries" → Variant 10

---
