// Recursion in JavaScript

/*
A function that calls itself to solve smaller version of the same problem
Two main parts:
1. Base Case: The condition under which the recursion stops
2. Recursive Case: The part where the function calls itself with a modified argument
*/

// Example: Print numbers from n to 1
function fun(a) {
  if (a <= 0) return; // Base case
  console.log(a);
  fun(a - 1); // Recursive case
}

let a1 = 5;
fun(a1); // Output: 5 4 3 2 1

// Example: Print numbers from 1 to n
function print(a) {
  if (x > a) return; // Base case
  console.log(x);
  x++;
  print(a); // Recursive case
}
let x = 1;
let a2 = 5;
print(a2); // Output: 1 2 3 4 5

// Example : Sum of first n natural numbers
function sum(n) {
  if (n == 0) return 0; // Base case
  return n + sum(n - 1); // Recursive case
}

let n = 5;
console.log(sum(n)); // Output: 15 (5+4+3+2+1)

// Example: Sum of all elements in an array
function arraySum(arr, n) {
  if (n <= 0) return 0;
  return arr[n - 1] + arraySum(arr, n - 1);
}

let arr = [1, 2, 3, 4, 5];
let size = arr.length;
console.log(arraySum(arr, size)); // Output: 15 (1+2+3+4+5)

// Another Approach
let arr1 = [1, 2, 3, 4, 5];
function arraySum1(n) {
  if (n == 0) return arr1[n];
  return arr1[n] + arraySum1(n - 1);
}

let size1 = arr1.length - 1;
console.log(arraySum1(size1)); // Output: 15 (1+2+3+4+5)

// Sum of odd elements in an array

// Another Approach
let arr2 = [1, 2, 3, 4, 5];
function arraySum1(n) {
  let isOdd = arr1[n] % 2 != 0;
  if (n == 0) return isOdd ? arr1[n] : 0;

  return (isOdd ? arr1[n] : 0) + arraySum1(n - 1);
  //   return arr1[n] + arraySum1(n - 1);
}
let size2 = arr2.length - 1;
console.log(arraySum1(size2)); // Output: 9 (1+3+5)

// Example: Factorial of a number

function factorial(x) {
  if (x == 0 || x == 1) return 1;
  return x * factorial(x - 1);
}

console.log(factorial(5)); // Output: 120 (5*4*3*2*1)

// Example: Check if a number is a power of two

function powerOfTwo(n) {
  if (n == 1) return true;
  else if (n % 2 != 0 || n < 1) return false;
  return powerOfTwo(n / 2);
}

console.log(powerOfTwo(16)); // Output: true
