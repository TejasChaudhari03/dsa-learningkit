let arr2 = [1, 2, 3, 4, 8, -1, 8];
let arr = [8, 8, 8];
function findElementExistence(x) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] == x) return "Index " + (i + 1);

    if (i == arr.length - 1) console.log(-1);
  }
}

//findElementExistence(11);

function findElementExistence2(x) {
  let index = arr.indexOf(x);
  return index !== -1 ? "Func 2 Index " + (index + 1) : -1;
}

let x = findElementExistence2(2);
console.log(x);
let count = 0;
function countAllNegativeNumbers(arr) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] < 0) count++;
  }
}
countAllNegativeNumbers(arr);
console.log("Negative Number count " + count);

function findLargestNumber(arr) {
  let max = arr[0];

  for (let i = 0; i < arr.length; i++) {
    if (arr[i] > max) {
      max = arr[i];
    }
  }
  return max;
}

let res2 = findLargestNumber(arr);
console.log("Max number " + res2);

function findSmallestNumber(arr) {
  let min = arr[0];
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] < min) {
      min = arr[i];
    }
  }
  return min;
}

let res3 = findSmallestNumber(arr);
console.log("Min number " + res3);

function findSecondLargestNumber(arr) {
  if (arr.length < 2) {
    console.log("Array should have atleast 2 elements");
    return;
  }

  let firstLargest = arr[0];
  let secondLargest = arr[1];

  for (let i = 0; i < arr.length; i++) {
    if (arr[i] > firstLargest) {
      secondLargest = firstLargest;
      firstLargest = arr[i];
    } else if (arr[i] > secondLargest && arr[i] != firstLargest)
      secondLargest = arr[i];
  }
  if (firstLargest === secondLargest) {
    console.log("No distinct second largest element exists");
    return;
  }
  console.log("First Largest :" + firstLargest);
  console.log("Second Largest :" + secondLargest);
}

findSecondLargestNumber(arr);
