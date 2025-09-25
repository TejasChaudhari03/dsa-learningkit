# 📑 Searching & Sorting — Cheat Sheet

## 🔎 Searching

**Linear Search**

- Time: O(n) | Space: O(1) | Works on unsorted

```js
// JavaScript
function linearSearch(arr, t) {
  for (let i = 0; i < arr.length; i++) if (arr[i] === t) return i;
  return -1;
}
```

```java
// Java
int linearSearch(int[] arr,int t){ for(int i=0;i<arr.length;i++) if(arr[i]==t) return i; return -1; }
```

**Binary Search** (sorted)

- Time: O(log n) | Space: O(1)

```js
function binarySearch(arr, t) {
  let l = 0,
    r = arr.length - 1;
  while (l <= r) {
    let m = Math.floor((l + r) / 2);
    if (arr[m] == t) return m;
    if (arr[m] > t) r = m - 1;
    else l = m + 1;
  }
  return -1;
}
```

```java
int binarySearch(int[] arr,int t){int l=0,r=arr.length-1;while(l<=r){int m=l+(r-l)/2;if(arr[m]==t)return m;if(t<arr[m])r=m-1;else l=m+1;}return -1;}
```

---

## 🔄 Sorting

**Bubble Sort**

- O(n²), Stable

```js
function bubbleSort(a) {
  for (let i = 0; i < a.length; i++) {
    let s = false;
    for (let j = 0; j < a.length - 1 - i; j++) {
      if (a[j] > a[j + 1]) {
        [a[j], a[j + 1]] = [a[j + 1], a[j]];
        s = true;
      }
    }
    if (!s) break;
  }
  return a;
}
```

```java
void bubbleSort(int[] a){for(int i=0;i<a.length;i++){boolean s=false;for(int j=0;j<a.length-1-i;j++){if(a[j]>a[j+1]){int t=a[j];a[j]=a[j+1];a[j+1]=t;s=true;}}if(!s)break;}}
```

**Selection Sort**

- O(n²), Unstable

```js
function selectionSort(a) {
  for (let i = 0; i < a.length; i++) {
    let m = i;
    for (let j = i + 1; j < a.length; j++) if (a[j] < a[m]) m = j;
    [a[i], a[m]] = [a[m], a[i]];
  }
  return a;
}
```

**Insertion Sort**

- O(n²), Stable (best O(n))

```js
function insertionSort(a) {
  for (let i = 1; i < a.length; i++) {
    let k = a[i],
      j = i - 1;
    while (j >= 0 && a[j] > k) {
      a[j + 1] = a[j];
      j--;
    }
    a[j + 1] = k;
  }
  return a;
}
```

**Merge Sort**

- O(n log n), Stable, Space O(n)

```js
function mergeSort(a) {
  if (a.length <= 1) return a;
  let m = Math.floor(a.length / 2);
  let l = mergeSort(a.slice(0, m)),
    r = mergeSort(a.slice(m));
  return merge(l, r);
}
function merge(l, r) {
  let res = [];
  while (l.length && r.length) {
    res.push(l[0] <= r[0] ? l.shift() : r.shift());
  }
  return res.concat(l, r);
}
```

**Quick Sort**

- O(n log n) avg, O(n²) worst, Unstable

```js
function quickSort(a) {
  if (a.length <= 1) return a;
  let p = a[a.length - 1],
    L = [],
    R = [];
  for (let i = 0; i < a.length - 1; i++) (a[i] < p ? L : R).push(a[i]);
  return [...quickSort(L), p, ...quickSort(R)];
}
```

**Heap Sort**

- O(n log n), Unstable, In-place

```js
function heapSort(a) {
  let n = a.length;
  for (let i = Math.floor(n / 2) - 1; i >= 0; i--) heapify(a, n, i);
  for (let i = n - 1; i > 0; i--) {
    [a[0], a[i]] = [a[i], a[0]];
    heapify(a, i, 0);
  }
  return a;
}
function heapify(a, n, i) {
  let l = 2 * i + 1,
    r = 2 * i + 2,
    m = i;
  if (l < n && a[l] > a[m]) m = l;
  if (r < n && a[r] > a[m]) m = r;
  if (m != i) {
    [a[i], a[m]] = [a[m], a[i]];
    heapify(a, n, m);
  }
}
```

---

## 📊 Complexity Overview

| Algorithm      | Best       | Avg        | Worst      | Space    | Stable |
| -------------- | ---------- | ---------- | ---------- | -------- | ------ |
| Linear Search  | O(1)       | O(n)       | O(n)       | O(1)     | N/A    |
| Binary Search  | O(1)       | O(log n)   | O(log n)   | O(1)     | N/A    |
| Bubble Sort    | O(n)       | O(n²)      | O(n²)      | O(1)     | ✅     |
| Selection Sort | O(n²)      | O(n²)      | O(n²)      | O(1)     | ❌     |
| Insertion Sort | O(n)       | O(n²)      | O(n²)      | O(1)     | ✅     |
| Merge Sort     | O(n log n) | O(n log n) | O(n log n) | O(n)     | ✅     |
| Quick Sort     | O(n log n) | O(n log n) | O(n²)      | O(log n) | ❌     |
| Heap Sort      | O(n log n) | O(n log n) | O(n log n) | O(1)     | ❌     |

---

⚡ **Revision Tip:**

- Use **Insertion Sort** for small/almost sorted arrays.
- **Merge Sort** when stability matters.
- **Quick Sort** for general use (fast avg, cache-friendly).
- **Heap Sort** when memory efficiency is key.
- In real-world, rely on **built-in sort functions** (`Arrays.sort`, `arr.sort`).

---
