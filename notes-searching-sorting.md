# 🔎 Searching & 🔄 Sorting — Java & JavaScript (Complete Notes)

## 📑 Table of Contents

- [Searching](#searching)

  - [Linear Search](#linear-search)
  - [Binary Search](#binary-search)

- [Sorting](#sorting)

  - [Bubble Sort](#bubble-sort)
  - [Selection Sort](#selection-sort)
  - [Insertion Sort](#insertion-sort)
  - [Merge Sort](#merge-sort)
  - [Quick Sort](#quick-sort)
  - [Heap Sort](#heap-sort)

- [Complexity Summary](#complexity-summary)
- [Best Practices & Insights](#best-practices--insights)

---

# Searching

### 🔹 Linear Search

**Definition:** Check each element sequentially until the target is found.

- Time: Best **O(1)**, Worst **O(n)**
- Space: **O(1)**
- Use: Small/unsorted datasets

**JavaScript**

```javascript
let arr = [10, 23, 45, 70, 11, 15];
let target = 11;

function linearSearch(arr, target) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] === target) return i;
  }
  return -1;
}

console.log(linearSearch(arr, target)); // Output: 4
```

**Java**

```java
public static int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) return i;
    }
    return -1;
}
```

---

### 🔹 Binary Search

**Definition:** Works on **sorted arrays**. Repeatedly divide search interval in half.

- Time: Best **O(1)**, Avg/Worst **O(log n)**
- Space: **O(1)**
- Use: Large sorted datasets

**JavaScript**

```javascript
let sortedArr = [10, 11, 15, 23, 45, 70];
let target2 = 15;

function binarySearch(arr, target) {
  let left = 0,
    right = arr.length - 1;
  while (left <= right) {
    let mid = Math.floor((left + right) / 2);
    if (arr[mid] === target) return mid;
    else if (arr[mid] > target) right = mid - 1;
    else left = mid + 1;
  }
  return -1;
}

console.log(binarySearch(sortedArr, target2)); // Output: 2
```

**Java**

```java
public static int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        else if (target < arr[mid]) right = mid - 1;
        else left = mid + 1;
    }
    return -1;
}
```

---

# Sorting

### 🔹 Bubble Sort

**Definition:** Repeatedly swap adjacent elements if they are in the wrong order.

- Time: Best **O(n)**, Worst **O(n²)**
- Space: **O(1)**
- Stability: ✅ Yes

**JavaScript**

```javascript
function bubbleSort(arr) {
  for (let i = 0; i < arr.length - 1; i++) {
    let swapped = false;
    for (let j = 0; j < arr.length - 1 - i; j++) {
      if (arr[j] > arr[j + 1]) {
        [arr[j], arr[j + 1]] = [arr[j + 1], arr[j]];
        swapped = true;
      }
    }
    if (!swapped) break;
  }
  return arr;
}
console.log(bubbleSort([5, 10, 2, 8, 1, 4])); // [1,2,4,5,8,10]
```

**Java**

```java
public static void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        boolean swapped = false;
        for (int j = 0; j < arr.length - 1 - i; j++) {
            if (arr[j] > arr[j+1]) {
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                swapped = true;
            }
        }
        if (!swapped) break;
    }
}
```

---

### 🔹 Selection Sort

**Definition:** Select the minimum element and place it at the correct position.

- Time: Best/Worst **O(n²)**
- Space: **O(1)**
- Stability: ❌ No

**JavaScript**

```javascript
function selectionSort(arr) {
  for (let i = 0; i < arr.length; i++) {
    let min = i;
    for (let j = i + 1; j < arr.length; j++) {
      if (arr[j] < arr[min]) min = j;
    }
    [arr[i], arr[min]] = [arr[min], arr[i]];
  }
  return arr;
}
console.log(selectionSort([64, 25, 12, 22, 11])); // [11,12,22,25,64]
```

**Java**

```java
public static void selectionSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        int min = i;
        for (int j = i+1; j < arr.length; j++) {
            if (arr[j] < arr[min]) min = j;
        }
        int temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
    }
}
```

---

### 🔹 Insertion Sort

**Definition:** Insert each element into its correct position in the sorted left side.

- Time: Best **O(n)**, Worst **O(n²)**
- Space: **O(1)**
- Stability: ✅ Yes

**JavaScript**

```javascript
function insertionSort(arr) {
  for (let i = 1; i < arr.length; i++) {
    let curr = arr[i];
    let j = i - 1;
    while (j >= 0 && arr[j] > curr) {
      arr[j + 1] = arr[j];
      j--;
    }
    arr[j + 1] = curr;
  }
  return arr;
}
console.log(insertionSort([12, 11, 13, 5, 6])); // [5,6,11,12,13]
```

**Java**

```java
public static void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        int curr = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > curr) {
            arr[j+1] = arr[j];
            j--;
        }
        arr[j+1] = curr;
    }
}
```

---

### 🔹 Merge Sort

**Definition:** Divide & Conquer → split array, recursively sort halves, merge them.
I'll explain each condition and the reasoning behind this merge sort implementation step by step.

### Key Conditions and Their Purpose

### 1. `if (arr == null || arr.length <= 1) return;`

**Purpose**: Base case for edge cases

- **`arr == null`**: Prevents NullPointerException if someone passes a null array
- **`arr.length <= 1`**: Arrays with 0 or 1 elements are already sorted by definition
- **Why this approach**: No point in sorting something that's already in its final form

### 2. `if (left < right)`

**Purpose**: Main recursive base case

- **What it means**: Only proceed if there are at least 2 elements in the current subarray
- **When `left == right`**: We have exactly 1 element (already sorted)
- **When `left > right`**: Invalid range (shouldn't happen with correct implementation)

**Why this approach**:

```
Example: arr = [38, 27, 43, 9, 82]
Initial call: left=0, right=4 → 0 < 4 ✓ (continue)
Eventually: left=2, right=2 → 2 < 2 ✗ (stop, single element)
```

### 3. `int mid = left + (right - left) / 2;`

**Purpose**: Find the middle point to divide the array

- **Why not `(left + right) / 2`**: Could cause integer overflow with very large indices
- **Why this formula**: `left + (right - left) / 2` is overflow-safe

**Example**:

```
left=0, right=4: mid = 0 + (4-0)/2 = 2
left=0, right=1: mid = 0 + (1-0)/2 = 0
```

### 4. The Recursive Calls

```java
mergeSort(arr, left, mid);        // Sort left half
mergeSort(arr, mid + 1, right);   // Sort right half
```

**Why this approach**:

- **Divide and Conquer**: Break problem into smaller, manageable pieces
- **`mid + 1`**: Ensures no overlap between left and right subarrays
- **Order matters**: Must sort both halves before merging

## The Merge Process Conditions

### 5. `while (i < n1 && j < n2)`

**Purpose**: Compare elements from both subarrays while both have remaining elements

- **`i < n1`**: Left subarray still has elements
- **`j < n2`**: Right subarray still has elements
- **Why both conditions**: Need elements from both sides to compare

### 6. `if (L[i] <= R[j])`

**Purpose**: Maintain stability and ascending order

- **`<=` instead of `<`**: Ensures **stable sorting** (equal elements maintain their relative order)
- **Why stability matters**: Important for objects with multiple fields

### 7. Cleanup loops

```java
while (i < n1) { arr[k++] = L[i++]; }  // Copy remaining left elements
while (j < n2) { arr[k++] = R[j++]; }  // Copy remaining right elements
```

**Purpose**: Handle remaining elements when one subarray is exhausted

## Why This Overall Approach?

### **Divide and Conquer Philosophy**:

1. **Divide**: Split array into two halves
2. **Conquer**: Recursively sort each half
3. **Combine**: Merge the sorted halves

### **Time Complexity Benefits**:

- **O(n log n)** guaranteed performance
- **log n** levels of recursion
- **O(n)** work at each level

### **Visualization of the Process**:

```
[38, 27, 43, 9, 82]
       ↓ split
[38, 27, 43] | [9, 82]
       ↓ split both
[38] [27, 43] | [9] [82]
       ↓ split [27, 43]
[38] [27] [43] | [9] [82]
       ↓ merge back up
[38] [27, 43] | [9, 82]
       ↓
[27, 38, 43] | [9, 82]
       ↓
[9, 27, 38, 43, 82]
```

The beauty of merge sort is that it guarantees consistent performance regardless of input order, making it very reliable for production systems where predictable performance matters.

I'll break down the merge process in detail, explaining the arguments, conditions, and step-by-step flow.

## Merge Function Arguments

```java
public static void merge(int[] arr, int left, int mid, int right)
```

### **Argument Requirements**:

- **`arr`**: The original array being sorted (modified in-place)
- **`left`**: Starting index of the left subarray
- **`mid`**: Ending index of the left subarray
- **`right`**: Ending index of the right subarray

### **Subarray Boundaries**:

- **Left subarray**: `arr[left...mid]` (inclusive)
- **Right subarray**: `arr[mid+1...right]` (inclusive)

**Example**: If `left=0, mid=2, right=4`

```
arr = [27, 38, 43, 9, 82]
Left:  arr[0..2] = [27, 38, 43]
Right: arr[3..4] = [9, 82]
```

## Step-by-Step Merge Process

### **Step 1: Calculate Sizes and Create Temporary Arrays**

```java
int n1 = mid - left + 1;  // Size of left subarray
int n2 = right - mid;     // Size of right subarray
int[] L = new int[n1];    // Temporary left array
int[] R = new int[n2];    // Temporary right array
```

**Why temporary arrays?**

- We need to preserve original values while overwriting `arr`
- Direct merging would overwrite values we haven't processed yet

**Size calculation example**:

```
left=0, mid=2, right=4
n1 = 2 - 0 + 1 = 3  (elements at indices 0, 1, 2)
n2 = 4 - 2 = 2      (elements at indices 3, 4)
```

### **Step 2: Copy Data to Temporary Arrays**

```java
for (int i = 0; i < n1; i++)
    L[i] = arr[left + i];
for (int j = 0; j < n2; j++)
    R[j] = arr[mid + 1 + j];
```

**Visual example**:

```
Original: arr = [27, 38, 43, 9, 82]
                 ↓   ↓   ↓   ↓  ↓
Copy to:  L = [27, 38, 43]
          R = [9, 82]
```

### **Step 3: Three-Pointer Merge Algorithm**

```java
int i = 0, j = 0, k = left;
```

**Pointer purposes**:

- **`i`**: Index for left array `L`
- **`j`**: Index for right array `R`
- **`k`**: Index for original array `arr` (where we place merged result)

## Core Merge Conditions

### **Condition 1: Main Comparison Loop**

```java
while (i < n1 && j < n2) {
    if (L[i] <= R[j]) {
        arr[k++] = L[i++];
    } else {
        arr[k++] = R[j++];
    }
}
```

**Why both conditions `i < n1 && j < n2`?**

- Need elements from **both** arrays to compare
- Loop stops when either array is exhausted
- **AND operation**: Both conditions must be true

**Decision logic**:

- **`L[i] <= R[j]`**: Take from left (≤ ensures stability)
- **`L[i] > R[j]`**: Take from right

### **Step-by-Step Example**:

```
L = [27, 38, 43]    R = [9, 82]
i=0  j=0  k=0

Step 1: L[0]=27, R[0]=9 → 27 > 9
        arr[0] = 9, j=1, k=1

Step 2: L[0]=27, R[1]=82 → 27 ≤ 82
        arr[1] = 27, i=1, k=2

Step 3: L[1]=38, R[1]=82 → 38 ≤ 82
        arr[2] = 38, i=2, k=3

Step 4: L[2]=43, R[1]=82 → 43 ≤ 82
        arr[3] = 43, i=3, k=4

Now i=3, n1=3, so i < n1 is false → exit main loop
```

### **Condition 2 & 3: Cleanup Loops**

```java
while (i < n1) {
    arr[k++] = L[i++];
}
while (j < n2) {
    arr[k++] = R[j++];
}
```

**Why needed?**

- One array might be exhausted before the other
- Remaining elements are already sorted
- Just copy them over

**In our example**:

- After main loop: `i=3, j=1`
- Left array exhausted (`i=3, n1=3`)
- Right array has remaining: `R[1]=82`
- Copy: `arr[4] = 82`

## Critical Requirements for Merge to Work

### **1. Input Subarrays Must Be Pre-sorted**

```java
// This works:
L = [27, 38, 43]  // sorted
R = [9, 82]       // sorted

// This won't work correctly:
L = [43, 27, 38]  // not sorted
R = [82, 9]       // not sorted
```

### **2. Correct Boundary Calculation**

- **Left boundary**: `left` to `mid` (inclusive)
- **Right boundary**: `mid+1` to `right` (inclusive)
- **No gap**: `mid+1` ensures no missing elements
- **No overlap**: Clear separation between subarrays

### **3. In-Place Modification**

- Original array `arr` is modified directly
- Temporary arrays are just workspace
- Final result overwrites the original segment

## Memory and Performance Implications

### **Space Complexity**: O(n)

- Temporary arrays `L` and `R` require extra space
- Total space proportional to the size being merged

### **Time Complexity**: O(n)

- Each element is copied exactly twice (to temp, then back)
- Each element is compared at most once
- Linear time for linear amount of data

### **Why This Approach?**

- **Stable**: Equal elements maintain relative order
- **Predictable**: Always O(n) time regardless of data distribution
- **Simple**: Easy to understand and implement correctly
- **Optimal**: Cannot merge two sorted arrays faster than O(n)

The merge operation is the heart of merge sort's efficiency - it's the "combine" step that makes the divide-and-conquer strategy work effectively.

- Time: Always **O(n log n)**
- Space: **O(n)**
- Stability: ✅ Yes

**JavaScript**

```javascript
function mergeSort(arr) {
  if (arr.length <= 1) return arr;
  let mid = Math.floor(arr.length / 2);
  let left = mergeSort(arr.slice(0, mid));
  let right = mergeSort(arr.slice(mid));
  return merge(left, right);
}

function merge(left, right) {
  let res = [],
    i = 0,
    j = 0;
  while (i < left.length && j < right.length) {
    if (left[i] <= right[j]) res.push(left[i++]);
    else res.push(right[j++]);
  }
  return [...res, ...left.slice(i), ...right.slice(j)];
}

console.log(mergeSort([38, 27, 43, 3, 9, 82, 10])); // [3,9,10,27,38,43,82]
```

**Java**

```java
public static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
        int mid = left + (right-left)/2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid+1, right);
        merge(arr, left, mid, right);
    }
}
public static void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid-left+1, n2 = right-mid;
    int[] L = new int[n1], R = new int[n2];
    for (int i=0;i<n1;i++) L[i]=arr[left+i];
    for (int j=0;j<n2;j++) R[j]=arr[mid+1+j];
    int i=0,j=0,k=left;
    while(i<n1 && j<n2) arr[k++] = (L[i]<=R[j]) ? L[i++] : R[j++];
    while(i<n1) arr[k++]=L[i++];
    while(j<n2) arr[k++]=R[j++];
}
```

---

### 🔹 Quick Sort

**Definition:** Partition around a pivot, recursively sort partitions.

- Time: Best/Avg **O(n log n)**, Worst **O(n²)**
- Space: **O(log n)**
- Stability: ❌ No

**JavaScript**

```javascript
function quickSort(arr) {
  if (arr.length <= 1) return arr;
  let pivot = arr[arr.length - 1];
  let left = [],
    right = [];
  for (let i = 0; i < arr.length - 1; i++) {
    if (arr[i] < pivot) left.push(arr[i]);
    else right.push(arr[i]);
  }
  return [...quickSort(left), pivot, ...quickSort(right)];
}
console.log(quickSort([10, 7, 8, 9, 1, 5])); // [1,5,7,8,9,10]
```

**Java**

```java
public static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi-1);
        quickSort(arr, pi+1, high);
    }
}
public static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low-1;
    for (int j=low;j<high;j++) {
        if (arr[j] < pivot) {
            i++;
            int temp = arr[i]; arr[i]=arr[j]; arr[j]=temp;
        }
    }
    int temp = arr[i+1]; arr[i+1]=arr[high]; arr[high]=temp;
    return i+1;
}
```

---

### 🔹 Heap Sort

**Definition:** Build a max heap and repeatedly extract max element.

- Time: Always **O(n log n)**
- Space: **O(1)**
- Stability: ❌ No

**JavaScript**

```javascript
function heapSort(arr) {
  let n = arr.length;
  for (let i = Math.floor(n / 2) - 1; i >= 0; i--) heapify(arr, n, i);
  for (let i = n - 1; i > 0; i--) {
    [arr[0], arr[i]] = [arr[i], arr[0]];
    heapify(arr, i, 0);
  }
  return arr;
}
function heapify(arr, n, i) {
  let largest = i,
    l = 2 * i + 1,
    r = 2 * i + 2;
  if (l < n && arr[l] > arr[largest]) largest = l;
  if (r < n && arr[r] > arr[largest]) largest = r;
  if (largest != i) {
    [arr[i], arr[largest]] = [arr[largest], arr[i]];
    heapify(arr, n, largest);
  }
}
console.log(heapSort([12, 11, 13, 5, 6, 7])); // [5,6,7,11,12,13]
```

**Java**

```java
public static void heapSort(int[] arr) {
    int n = arr.length;
    for (int i=n/2-1;i>=0;i--) heapify(arr,n,i);
    for (int i=n-1;i>0;i--) {
        int temp=arr[0]; arr[0]=arr[i]; arr[i]=temp;
        heapify(arr,i,0);
    }
}
public static void heapify(int[] arr, int n, int i) {
    int largest=i, l=2*i+1, r=2*i+2;
    if(l<n && arr[l]>arr[largest]) largest=l;
    if(r<n && arr[r]>arr[largest]) largest=r;
    if(largest!=i) {
        int temp=arr[i]; arr[i]=arr[largest]; arr[largest]=temp;
        heapify(arr,n,largest);
    }
}
```

---

# 📊 Complexity Summary

| Algorithm      | Best       | Avg        | Worst      | Space    | Stable? | Use-case              |
| -------------- | ---------- | ---------- | ---------- | -------- | ------- | --------------------- |
| Linear Search  | O(1)       | O(n)       | O(n)       | O(1)     | N/A     | Unsorted small arrays |
| Binary Search  | O(1)       | O(log n)   | O(log n)   | O(1)     | N/A     | Sorted datasets       |
| Bubble Sort    | O(n)       | O(n²)      | O(n²)      | O(1)     | ✅ Yes  | Teaching only         |
| Selection Sort | O(n²)      | O(n²)      | O(n²)      | O(1)     | ❌ No   | Swap-limited          |
| Insertion Sort | O(n)       | O(n²)      | O(n²)      | O(1)     | ✅ Yes  | Nearly sorted arrays  |
| Merge Sort     | O(n log n) | O(n log n) | O(n log n) | O(n)     | ✅ Yes  | Large datasets        |
| Quick Sort     | O(n log n) | O(n log n) | O(n²)      | O(log n) | ❌ No   | Cache-friendly        |
| Heap Sort      | O(n log n) | O(n log n) | O(n log n) | O(1)     | ❌ No   | Memory-efficient      |

---

# 💡 Best Practices & Insights

1. **Use built-in libraries in production**

   - Java → `Arrays.sort()` (uses Dual Pivot QuickSort / TimSort).
   - JS → `arr.sort((a,b)=>a-b)` (usually Timsort).

2. **Stability**

   - Stable → Merge, Insertion, Bubble.
   - Unstable → Quick, Heap, Selection.

3. **Practical choices**

   - Small arrays → Insertion Sort.
   - Large arrays → Merge Sort or Quick Sort.
   - Memory-constrained → Heap Sort.

4. **Binary Search beyond searching**

   - Useful in optimization, allocation, peaks, roots, etc.

5. **Interview tip:** Always discuss **time & space trade-offs**, plus why you choose an algorithm.

---
