// Linear Search Algorithm in JavaScript
let arr = [10, 23, 45, 70, 11, 15];
let target = 11;
function linearSearch(arr, target) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] === target) {
      return i;
    }
  }
  return -1;
}

console.log("Index of Target " + target + ": " + linearSearch(arr, target)); // Output: 4

// Binary Search Algorithm in JavaScript (Array must be sorted)
let sortedArr = [10, 11, 15, 23, 45, 70];
let target2 = 15;

function binarySearch(arr, target) {
  let left = 0;
  let right = arr.length - 1;

  while (right > left) {
    let middle = Math.floor((left + right) / 2);

    if (target == arr[middle]) {
      return middle;
    } else if (target < arr[middle]) {
      right = middle - 1;
    } else {
      left = middle + 1;
    }
  }

  return -1;
}
console.log(
  "Index of Target " + target2 + ": " + binarySearch(sortedArr, target2)
); // Output: 2

// Bubble Sort Algorithm in JavaScript

let arr2 = [5, 10, 2, 8, 1, 4];
function bubbleSort(arr) {
  for (let i = 0; i < arr.length - 1; i++) {
    let isSwapped = false;
    for (let j = 0; j < arr.length - 1 - i; j++) {
      if (arr[j] > arr[j + 1]) {
        let temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
        isSwapped = true;
      }
    }

    if (!isSwapped) break;
  }

  return arr;
}

let res = bubbleSort(arr);
console.log(res); // Output: [1, 2, 4, 5, 8, 10]

// Selection Sort Algorithm in JavaScript
let arr3 = [64, 25, 12, 22, 11];
function selectionSort(arr) {
  for (let i = 0; i < arr.length - 1; i++) {
    // Find the minimum element in the array
    let min = i;
    for (let j = i + 1; j < arr.length; j++) {
      if (arr[j] < arr[min]) min = j;
    }

    if (min != i) {
      let temp = arr[i];
      arr[i] = arr[min];
      arr[min] = temp;
    }
  }

  return arr;
}

let res1 = selectionSort(arr3);
console.log(res1); // Output: [11, 12, 22, 25, 64]

// Insertion Sort Algorithm in JavaScript
let arr4 = [12, 11, 13, 5, 6];
function insertionSort(arr) {
  for (let i = 1; i < arr.length; i++) {
    let curr = arr[i];
    let prev = i - 1;

    while (arr[prev] > curr && prev >= 0) {
      arr[prev + 1] = arr[prev];
      prev--;
    }

    arr[prev + 1] = curr;
  }

  return arr;
}

let res2 = insertionSort(arr4);
console.log(res2); // Output: [5, 6, 11, 12, 13]

// Merge Sort Algorithm in JavaScript
let arr5 = [38, 27, 43, 3, 9, 82, 10];

function mergeSort(arr) {
  if (arr.length <= 1) return arr;

  let mid = Math.floor(arr.length / 2);
  let left = mergeSort(arr.slice(0, mid));
  let right = mergeSort(arr.slice(mid));

  return merge(left, right);
}

function merge(left, right) {
  let res = [];
  let i = 0,
    j = 0;
  while (i < left.length && j < right.length) {
    if (left[i] < right[j]) {
      res.push(left[i]);
      i++;
    } else {
      res.push(right[j]);
      j++;
    }
  }

  return [...res, ...left.slice(i), ...right.slice(j)];
}

let res3 = mergeSort(arr5);
console.log(res3); // Output: [3, 9, 10, 27, 38, 43, 82]
