# Time and Space Complexity

### Definitions

## Time Complexity - It is used to measure the efficiency of algorithm in terms of speed, as the input size grows.

## Space Complexity - It is used to measure the efficiency of algorithm in terms of memory usage, as the input size grows.

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
public int getFirstElement(int[] arr) {
    return arr[0];  // Always takes same time
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

| Algorithm      | Best Case  | Average Case | Worst Case | Space    |
| -------------- | ---------- | ------------ | ---------- | -------- |
| Quick Sort     | O(n log n) | O(n log n)   | O(n²)      | O(log n) |
| Merge Sort     | O(n log n) | O(n log n)   | O(n log n) | O(n)     |
| Heap Sort      | O(n log n) | O(n log n)   | O(n log n) | O(1)     |
| Bubble Sort    | O(n)       | O(n²)        | O(n²)      | O(1)     |
| Selection Sort | O(n²)      | O(n²)        | O(n²)      | O(1)     |
| Insertion Sort | O(n)       | O(n²)        | O(n²)      | O(1)     |

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

---

## Interview Quick Notes

### Key Points to Remember

1. **Always analyze both time and space complexity**
2. **Consider best, average, and worst cases**
3. **Big O describes growth rate, not exact running time**
4. **Space complexity includes recursive call stack**
5. **Different inputs may require different variables (a, b, c)**

### Common Interview Mistakes

- Forgetting to consider space complexity
- Not distinguishing between best/average/worst cases
- Confusing O(log n) base (usually base 2 in CS)
- Not considering the call stack in recursive solutions
- Mixing up array access O(1) with array search O(n)

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
