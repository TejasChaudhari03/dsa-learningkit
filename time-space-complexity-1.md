# Time and Space Complexity

### Definitions

## Time Complexity

It is used to measure the efficiency of an algorithm in terms of speed, as the input size grows. Time complexity expresses how the running time increases relative to the input size (n). It is usually represented using Big-O notation.

## Space Complexity

It measures the total amount of memory space an algorithm needs to run to completion. This includes:

- **Fixed part**: Constant size, independent of input (e.g., program code)
- **Variable part**: Depends on the input and can include:
  - Input storage
  - Auxiliary space (temporary data structures)
  - Function call stack (recursion)

---

## Big O Notation

Big O notation describes the upper bound of an algorithm's growth rate. It represents the worst-case scenario.

### Common Time Complexities (Best to Worst)

| Complexity | Name         | Example Operations                          |
| ---------- | ------------ | ------------------------------------------- |
| O(1)       | Constant     | Array access, hash table lookup             |
| O(log n)   | Logarithmic  | Binary search, balanced BST operations      |
| O(n)       | Linear       | Linear search, single loop                  |
| O(n log n) | Linearithmic | Merge sort, heap sort, quick sort (average) |
| O(n²)      | Quadratic    | Bubble sort, nested loops                   |
| O(n³)      | Cubic        | Triple nested loops                         |
| O(2ⁿ)      | Exponential  | Recursive Fibonacci, subset generation      |
| O(n!)      | Factorial    | Traveling salesman (brute force)            |

### Growth Rate Visualization

```
O(1) < O(log n) < O(n) < O(n log n) < O(n²) < O(n³) < O(2ⁿ) < O(n!)
```

---

## Time Complexity Analysis

### Rules for Calculating Time Complexity

1. **Drop constants**: O(2n) → O(n)
2. **Drop lower-order terms**: O(n² + n) → O(n²)
3. **Different inputs use different variables**: O(a + b) for two arrays
4. **Nested loops multiply**: Two nested loops of size n = O(n²)

### Examples

#### O(1) - Constant Time

```java
public void printFirstElement(int[] arr) {
    System.out.println(arr[0]);  // Always takes same time
}

public int getFirstElement(int[] arr) {
    return arr[0];  // Execution time independent of input size
}
```

#### O(n) - Linear Time

```java
public int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {  // Loop runs n times
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}

public void printAllElements(int[] arr) {
    for (int num : arr) {  // Time increases proportionally with input size
        System.out.println(num);
    }
}
```

#### O(n²) - Quadratic Time

```java
public void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n; i++) {        // Outer loop: n times
        for (int j = 0; j < n - 1; j++) { // Inner loop: n times
            if (arr[j] > arr[j + 1]) {
                // Swap elements
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

public void printAllPairs(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length; j++) {  // Nested loops
            System.out.println(arr[i] + ", " + arr[j]);
        }
    }
}
```

#### O(log n) - Logarithmic Time

```java
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;  // Avoid overflow

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;
}
```

#### O(n log n) - Log-linear Time

```java
public void efficientSort(int[] arr) {
    Arrays.sort(arr); // Java's built-in sort uses TimSort (O(n log n))
}

// Merge Sort example - typical O(n log n) algorithm
public int[] mergeSort(int[] arr) {
    if (arr.length <= 1) return arr;

    int mid = arr.length / 2;
    int[] left = Arrays.copyOfRange(arr, 0, mid);
    int[] right = Arrays.copyOfRange(arr, mid, arr.length);

    return merge(mergeSort(left), mergeSort(right));
}
```

#### O(2ⁿ) - Exponential Time

```java
public int fibonacci(int n) {
    if (n <= 1) return n;
    return fibonacci(n-1) + fibonacci(n-2);  // Brute force approach
}
```

---

## Space Complexity Analysis

### Types of Space Usage

1. **Input Space**: Space used by input data
2. **Auxiliary Space**: Extra space used by algorithm (excluding input)
3. **Total Space**: Input space + Auxiliary space

### Common Space Complexities

#### O(1) - Constant Space

```java
public void reverseArrayInPlace(int[] arr) {
    int left = 0, right = arr.length - 1;  // Only using 2 variables

    while (left < right) {
        // Swap elements
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

        left++;
        right--;
    }
}

public int findMax(int[] arr) {
    int max = arr[0];  // Fixed amount of extra space
    for (int num : arr) {
        if (num > max) max = num;
    }
    return max;
}
```

#### O(n) - Linear Space

```java
public int[] mergeSort(int[] arr) {
    if (arr.length <= 1) {
        return arr;
    }

    int mid = arr.length / 2;
    int[] left = Arrays.copyOfRange(arr, 0, mid);      // Creates new array
    int[] right = Arrays.copyOfRange(arr, mid, arr.length); // Creates new array

    left = mergeSort(left);
    right = mergeSort(right);

    return merge(left, right);  // Creates result array
}

public int[] copyArray(int[] arr) {
    int[] copy = new int[arr.length];  // Space proportional to input
    for (int i = 0; i < arr.length; i++) {
        copy[i] = arr[i];
    }
    return copy;
}

private int[] merge(int[] left, int[] right) {
    int[] result = new int[left.length + right.length];
    int i = 0, j = 0, k = 0;

    while (i < left.length && j < right.length) {
        if (left[i] <= right[j]) {
            result[k++] = left[i++];
        } else {
            result[k++] = right[j++];
        }
    }

    while (i < left.length) result[k++] = left[i++];
    while (j < right.length) result[k++] = right[j++];

    return result;
}
```

#### O(log n) - Logarithmic Space

```java
public int binarySearchRecursive(int[] arr, int target) {
    return binarySearchHelper(arr, target, 0, arr.length - 1);
}

private int binarySearchHelper(int[] arr, int target, int left, int right) {
    if (left > right) {
        return -1;
    }

    int mid = left + (right - left) / 2;

    if (arr[mid] == target) {
        return mid;
    } else if (arr[mid] < target) {
        return binarySearchHelper(arr, target, mid + 1, right); // Call stack depth: log n
    } else {
        return binarySearchHelper(arr, target, left, mid - 1);
    }
}
```

### Factors Affecting Space Complexity

- Data structures used (arrays, hash tables, linked lists, etc.)
- Recursive depth (function call stack)
- Temporary variables and auxiliary data structures

---

## Best, Average, and Worst Case Analysis

Understanding different cases is crucial for comprehensive complexity analysis:

- **Best Case**: Minimum time an algorithm takes (optimistic scenario)
- **Average Case**: Expected time over all possible inputs (realistic expectation)
- **Worst Case**: Maximum time on any input (pessimistic but often most important)

### Example: Quick Sort Analysis

```java
public void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pivotIndex = partition(arr, low, high);
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }
}
```

- **Best Case**: O(n log n) - pivot always divides array in half
- **Average Case**: O(n log n) - random pivot selection
- **Worst Case**: O(n²) - pivot is always smallest/largest element

---

## Common Data Structure Complexities

### Arrays

| Operation | Time | Space |
| --------- | ---- | ----- |
| Access    | O(1) | O(1)  |
| Search    | O(n) | O(1)  |
| Insertion | O(n) | O(1)  |
| Deletion  | O(n) | O(1)  |

### Hash Tables

| Operation | Average | Worst Case | Space |
| --------- | ------- | ---------- | ----- |
| Access    | O(1)    | O(n)       | O(n)  |
| Search    | O(1)    | O(n)       | O(1)  |
| Insertion | O(1)    | O(n)       | O(1)  |
| Deletion  | O(1)    | O(n)       | O(1)  |

### Binary Search Trees (Balanced)

| Operation | Average  | Worst Case | Space |
| --------- | -------- | ---------- | ----- |
| Search    | O(log n) | O(n)       | O(1)  |
| Insertion | O(log n) | O(n)       | O(1)  |
| Deletion  | O(log n) | O(n)       | O(1)  |

### Linked Lists

| Operation | Time | Space |
| --------- | ---- | ----- |
| Access    | O(n) | O(1)  |
| Search    | O(n) | O(1)  |
| Insertion | O(1) | O(1)  |
| Deletion  | O(1) | O(1)  |

---

## Common Sorting Algorithm Complexities

| Algorithm                       | Best Case  | Average Case | Worst Case | Space    | Stable |
| ------------------------------- | ---------- | ------------ | ---------- | -------- | ------ |
| Quick Sort                      | O(n log n) | O(n log n)   | O(n²)      | O(log n) | No     |
| Merge Sort                      | O(n log n) | O(n log n)   | O(n log n) | O(n)     | Yes    |
| Heap Sort                       | O(n log n) | O(n log n)   | O(n log n) | O(1)     | No     |
| Bubble Sort                     | O(n)       | O(n²)        | O(n²)      | O(1)     | Yes    |
| Selection Sort                  | O(n²)      | O(n²)        | O(n²)      | O(1)     | No     |
| Insertion Sort                  | O(n)       | O(n²)        | O(n²)      | O(1)     | Yes    |
| Tim Sort (Java's Arrays.sort()) | O(n)       | O(n log n)   | O(n log n) | O(n)     | Yes    |

---

## Algorithm/Operation Summary Table

| Algorithm/Operation      | Time Complexity             | Space Complexity    |
| ------------------------ | --------------------------- | ------------------- |
| Access array element     | O(1)                        | O(1)                |
| Binary Search            | O(log n)                    | O(1)                |
| Linear Search            | O(n)                        | O(1)                |
| Sorting (Merge Sort)     | O(n log n)                  | O(n)                |
| Sorting (Quick Sort)     | O(n log n) avg, O(n²) worst | O(log n)            |
| Nested loops (pairs)     | O(n²)                       | O(1)                |
| Fibonacci (recursive)    | O(2ⁿ)                       | O(n)                |
| Hash Table operations    | O(1) avg, O(n) worst        | O(n)                |
| Tree traversal (DFS/BFS) | O(n)                        | O(h) where h=height |

---

## Analysis Techniques

### 1. Counting Operations

Count the number of basic operations (comparisons, assignments, arithmetic) as a function of input size.

### 2. Identifying Patterns

- Single loop over n elements: O(n)
- Nested loops: Multiply complexities
- Dividing problem in half: O(log n)
- Processing all subsets: O(2ⁿ)

### 3. Recursive Analysis

For recursive algorithms, set up recurrence relations:

- T(n) = T(n/2) + O(1) → O(log n)
- T(n) = 2T(n/2) + O(n) → O(n log n)
- T(n) = 2T(n-1) + O(1) → O(2ⁿ)

---

## Optimization Strategies

### Time Optimization

1. **Use better data structures** (hash tables for O(1) lookup)
2. **Avoid redundant calculations** (memoization, dynamic programming)
3. **Use divide and conquer** (reduce problem size)
4. **Preprocessing** (sort data for faster searches)

### Space Optimization

1. **In-place algorithms** (modify input instead of creating new data)
2. **Iterative over recursive** (avoid call stack overhead)
3. **Reuse variables** (don't create unnecessary objects)
4. **Stream processing** (process data in chunks)

---

## Trade-offs

### Time vs Space Trade-offs

- **Hash tables**: Use extra space for faster access time
- **Memoization**: Store results to avoid recomputation
- **Preprocessing**: Use space to store processed data for faster queries

### Examples of Trade-offs

1. **Sorting**: Quick sort (faster average case) vs Merge sort (guaranteed O(n log n))
2. **Search**: Binary search (requires sorted array) vs Linear search (works on any array)
3. **Data structures**: Array (cache-friendly) vs Linked list (dynamic size)

## Key Points for Software Engineers

1. **Understand trade-offs**: Sometimes improving time complexity increases space usage, and vice versa
2. **Optimize for constraints**: If memory is limited, prioritize space; if speed is crucial, prioritize time
3. **Scalability**: Always think about how your solution will scale with very large inputs
4. **Data structures matter**: The choice of data structure significantly impacts time and space complexity
5. **Real-world considerations**: Consider cache performance, memory locality, and system constraints
6. **Premature optimization**: Profile first, then optimize bottlenecks rather than guessing

---

## Interview Quick Notes

### Essential Concepts to Master

- **Big-O, Big-Theta, Big-Omega**: Know how to analyze and express algorithmic complexity
- **Focus on worst-case analysis**: Interviewers often prioritize worst-case scenarios
- **Know common algorithms**: Sorting (Merge, Quick, Heap), Searching (Binary, Hashing), Graph algorithms (DFS, BFS, Dijkstra)
- **Practice trade-offs**: Be ready to discuss why you chose one approach over another
- **Recursion vs Iteration**: Recursion uses stack space; iteration may be more space-efficient
- **Be clear and concise**: Explain your analysis and reasoning during interviews

### Key Points to Remember

1. **Always analyze both time and space complexity**
2. **Consider best, average, and worst cases**
3. **Big O describes growth rate, not exact running time**
4. **Space complexity includes recursive call stack**
5. **Different inputs may require different variables (a, b, c)**

### Common Interview Mistakes & Their Solutions

#### 1. Forgetting to consider space complexity

**Mistake**: Only analyzing time complexity and ignoring memory usage.

**Solution**:

- Always mention both time AND space complexity
- Consider: input space, auxiliary space, and call stack space
- Ask yourself: "Am I creating new data structures? How much extra memory?"

```java
// Example: Always analyze both
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>(); // O(n) space for hash map
    for (int i = 0; i < nums.length; i++) {      // O(n) time
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
        map.put(nums[i], i);
    }
    return new int[]{};
}
// Answer: Time O(n), Space O(n)
```

#### 2. Not distinguishing between best/average/worst cases

**Mistake**: Only mentioning one complexity case without context.

**Solution**:

- Always specify which case you're analyzing
- For algorithms with variable performance, mention all three
- Explain what causes each case

```java
// Example: Quick Sort analysis
public void quickSort(int[] arr, int low, int high) {
    // Best case: O(n log n) - pivot splits array evenly
    // Average case: O(n log n) - random pivot selection
    // Worst case: O(n²) - pivot is always min/max (sorted array)
    // Space: O(log n) best/avg, O(n) worst (recursion depth)
}
```

#### 3. Confusing O(log n) base (usually base 2 in CS)

**Mistake**: Not understanding what log n means or using wrong base.

**Solution**:

- Remember: log n in CS typically means log₂(n)
- Think "how many times can I divide n by 2?"
- Common in divide-and-conquer algorithms

```java
// Example: Binary Search - why it's O(log n)
public int binarySearch(int[] arr, int target) {
    // Array size: 1000 → max steps: log₂(1000) ≈ 10 steps
    // Array size: 1,000,000 → max steps: log₂(1,000,000) ≈ 20 steps
    // We eliminate half the search space each iteration
}
```

#### 4. Not considering the call stack in recursive solutions

**Mistake**: Forgetting that recursion uses stack memory.

**Solution**:

- Count recursive call depth as part of space complexity
- Each recursive call adds a frame to the call stack
- Consider iterative alternatives for space optimization

```java
// Recursive - O(n) space due to call stack
public int fibonacciRecursive(int n) {
    if (n <= 1) return n;
    return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    // Call stack depth: O(n), Time: O(2^n)
}

// Iterative - O(1) space, much better!
public int fibonacciIterative(int n) {
    if (n <= 1) return n;
    int prev2 = 0, prev1 = 1;
    for (int i = 2; i <= n; i++) {
        int current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }
    return prev1;
    // Time: O(n), Space: O(1)
}
```

#### 5. Mixing up array access O(1) with array search O(n)

**Mistake**: Confusing direct access by index with searching for a value.

**Solution**:

- **Access by index**: `arr[5]` → O(1) - direct memory calculation
- **Search for value**: Finding where value `x` is located → O(n) - may check all elements
- Always clarify what operation you're analyzing

```java
// O(1) - Direct access by index
public int getElement(int[] arr, int index) {
    return arr[index]; // Know exactly where to look
}

// O(n) - Search for a value
public int findIndex(int[] arr, int value) {
    for (int i = 0; i < arr.length; i++) { // Must check each element
        if (arr[i] == value) return i;
    }
    return -1;
}

// O(1) - Access with HashMap (average case)
public int getValue(Map<String, Integer> map, String key) {
    return map.get(key); // Hash function provides direct access
}
```

### How to Avoid These Mistakes:

1. **Create a mental checklist**: Time? Space? Best/Avg/Worst cases?
2. **Practice explaining out loud**: Verbalize your analysis process
3. **Draw it out**: Visualize memory usage and call stacks
4. **Ask clarifying questions**: "Are you looking for worst-case analysis?"
5. **Double-check your reasoning**: Walk through small examples

### Quick Mental Checks

- **Nested loops**: Usually O(n²) or higher
- **Single loop**: Usually O(n)
- **No loops, direct access**: Usually O(1)
- **Halving the problem**: Usually O(log n)
- **Processing all combinations**: Usually exponential

### Interview Communication Tips

1. **State your assumptions** about input size and constraints
2. **Explain your thought process** step by step
3. **Consider edge cases** and how they affect complexity
4. **Discuss trade-offs** between different approaches
5. **Be prepared to optimize** your initial solution
6. **Show multiple solutions** when possible (brute force → optimized)
