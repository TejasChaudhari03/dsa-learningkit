// Removes duplicates from a sorted array, keeps one of each element
let a1 = [0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4];

function removeDuplicatesSorted(a) {
  let x = 0;
  for (let i = 0; i < a.length; i++) {
    //console.log(`i=${i}, x=${x}, a=${a}`);
    if (a[i] > a[x]) {
      x = x + 1;
      a[x] = a[i];
      //console.log(`Updated -> x=${x}, a=${a}`);
    }
  }
  return x + 1;
}

let result1 = removeDuplicatesSorted(a1);
console.log("Result after removing duplicates: " + result1);

// Removes all occurrences of a specific value from the array
let a2 = [0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4];

function removeAllOccurrences(a, val) {
  let x = 0;
  for (let i = 0; i < a.length; i++) {
    //console.log(`i=${i}, x=${x}, a=${a}`);
    if (a[i] !== val) {
      a[x] = a[i];
      x = x + 1;
      //console.log(`Updated -> x=${x}, a=${a}`);
    }
  }
  return x;
}

let result2 = removeAllOccurrences(a2, 2);
console.log("Result after removing all occurrences of 2: " + result2);

// Reverse the string
let a3 = ["h", "e", "l", "l", "o"];

function reverseString(a) {
  for (let i = 0; i < a.length / 2; i++) {
    let temp = a[i];
    a[i] = a[a.length - 1 - i];
    a[a.length - 1 - i] = temp;
  }

  return a;
}
let res = reverseString(a3);
console.log("Reversed string: " + res);

let a4 = [7, 1, 5, 3, 6, 4];

// Best time to buy and sell stock
function maxProfit(prices) {
  let minPrice = prices[0];
  let maxProfit = 0;

  for (let i = 1; i < prices.length; i++) {
    if (prices[i] - minPrice > maxProfit) {
      maxProfit = prices[i] - minPrice;
    }

    if (prices[i] < minPrice) {
      minPrice = prices[i];
    }
  }
  return maxProfit;
}

console.log("Max profit: " + maxProfit(a4));

let a5 = [2, 7, 11, 15];
let a6 = [1, 2, 3, 4];

// Merge Sorted Arrays

// Approach 1 - Using extra space copy of the array
function mergeSortedArrays(a, alen, b, blen) {
  let aCopy = a.slice(0, alen); // Copy of a
  let p1 = 0; // Pointer for aCopy
  let p2 = 0; // Pointer for b

  for (let i = 0; i < alen + blen; i++) {
    if (p2 >= blen || (p1 < alen && aCopy[p1] < b[p2])) {
      a[i] = aCopy[p1];
      p1++;
    } else {
      a[i] = b[p2];
      p2++;
    }
  }
  return a;
}

console.log("Merging two sorted arrays");
console.log("Array a: " + a5);
console.log("Array b: " + a6);
let merged = mergeSortedArrays(a5, 4, a6, 4);
console.log("Merged array: " + merged);

// Approach 2 - Going reverse

function mergeSortedArraysReverse(a, alen, b, blen) {
  let p1 = alen - 1;
  let p2 = blen - 1;
  for (let i = alen + blen - 1; i >= 0; i--) {
    if (p2 < 0) break;

    if (p1 >= 0 && a[p1] > b[p2]) {
      a[i] = a[p1];
      p1--;
    } else {
      a[i] = b[p2];
      p2--;
    }
  }
  return a;
}

console.log("Merging two sorted arrays using reverse approach");
console.log("Array a: " + a5);
console.log("Array b: " + a6);
let mergedRev = mergeSortedArraysReverse(a5, 4, a6, 4);
console.log("Merged array: " + mergedRev);

// Move zeroes

let a7 = [0, 1, 0, 3, 12];

function moveZeroes(a) {
  let x = 0;

  for (let i = 0; i < a.length; i++) {
    if (a[i] != 0) {
      a[x] = a[i];
      x++;
    }
  }
  // fill remaining with zeroes
  for (let i = x; i < a.length; i++) {
    a[i] = 0;
  }
}

console.log("Array before moving zeroes: " + a7);
moveZeroes(a7);
console.log("Array after moving zeroes: " + a7);

// Max consecutive ones

// Version 1 – update only when hitting 0

let a8 = [1, 1, 0, 1, 1, 1];
function countConsecutiveOne_v1(a) {
  let maxCount = 0,
    currCount = 0;

  for (let i = 0; i < a.length; i++) {
    if (a[i] === 1) {
      currCount++;
    } else {
      maxCount = Math.max(maxCount, currCount);
      currCount = 0;
    }
  }
  return Math.max(maxCount, currCount); // <-- final check needed
}

console.log(
  "Max consecutive ones (v1-update only when hitting 0): " +
    countConsecutiveOne_v1(a8)
);

/// Version 2 – update at every step

let a9 = [1, 1, 0, 1, 1, 1];
function countConsecutiveOne_v2(a) {
  let maxCount = 0,
    currCount = 0;

  for (let i = 0; i < a.length; i++) {
    if (a[i] === 1) {
      currCount++;
      maxCount = Math.max(maxCount, currCount); // <-- update immediately
    } else {
      currCount = 0;
    }
  }
  return maxCount; // no final check needed
}

/*
Version 1 = “update later” (needs final check).
Version 2 = “update sooner” (no final check).
*/

// Missing number

let a10 = [3, 0, 1];

function missingNumber(a) {
  let n = a.length;
  let total = (n * (n + 1)) / 2;

  let sum = 0;
  for (let i = 0; i < a.length; i++) {
    sum += a[i];
  }
  return total - sum;
}

console.log("Missing number: " + missingNumber(a10));

// Single Number
// Using XOR Operator
let a11 = [2, 2, 1];

function singleNumber(a) {
  let result = 0;
  for (let i = 0; i < a.length; i++) {
    result = result ^ a[i];
  }
  return result;
}

console.log("Single number: " + singleNumber(a11));

// Using HashMap
let a12 = [4, 1, 2, 1, 2];
function singleNumberHashMap(a) {
  let hash = {};
  for (let i = 0; i < a.length; i++) {
    if (hash[a[i]] !== undefined) {
      hash[a[i]] += 1;
    } else {
      hash[a[i]] = 1;
    }
  }
  for (let key in hash) {
    if (hash[key] === 1) {
      return key;
    }
  }
  return -1;
}
console.log("Single number using HashMap: " + singleNumberHashMap(a12));
