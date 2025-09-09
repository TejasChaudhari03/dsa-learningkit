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
