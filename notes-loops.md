## **Learning Loops — JavaScript and Java**

### **For Loop**

**JavaScript**

```javascript
// Example: Print index and even numbers from array
let arr = [10, 1, 4, 5, 9];

for (let i = 0; i < arr.length; i++) {
  console.log("Index: " + i);
  if (arr[i] % 2 === 0) {
    console.log("Even number: " + arr[i]);
  }
}
```

**Java**

```java
// Example: Print index and even numbers from array
int[] arr = {10, 1, 4, 5, 9};

for (int i = 0; i < arr.length; i++) {
    System.out.println("Index: " + i);
    if (arr[i] % 2 == 0) {
        System.out.println("Even number: " + arr[i]);
    }
}
```

---

### **While Loop**

**JavaScript**

```javascript
let i = 0;
while (i < arr.length) {
  console.log("Value: " + arr[i]);
  i++;
}
```

**Java**

```java
int i = 0;
while (i < arr.length) {
    System.out.println("Value: " + arr[i]);
    i++;
}
```

---

### **Do-While Loop**

**JavaScript**

```javascript
let i = 0;
do {
  console.log("Checking: " + arr[i]);
  i++;
} while (i < arr.length);
```

**Java**

```java
int i = 0;
do {
    System.out.println("Checking: " + arr[i]);
    i++;
} while (i < arr.length);
```

---

### **Enhanced For Loop (Java only)**

**Java**

```java
for (int num : arr) {
    if (num % 2 == 0) {
        System.out.println("Even number: " + num);
    }
}
```

---

## ✅ **Best Practices for Loops**

1. **Avoid magic numbers** — use `.length` instead of hardcoding limits.
2. **Update loop counters** to prevent infinite loops.
3. **Always use curly braces `{}`** even if there’s one statement — improves readability and reduces errors.
4. **Descriptive variable names** help clarity (e.g., `index` instead of `i` in bigger loops).
5. **Choose the right loop** for the situation:

   - `for` → when the number of iterations is known
   - `while` → when looping until a condition changes
   - `do-while` → when you need to run the code at least once
   - Java’s **enhanced for** → clean iteration when you don’t need an index

### Examples

```javascript
//1. Example -  element existence in the array
let arr = [3, 2, 10, 4, 8];
function findElementExistence(x) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] == x) {
      console.log("Index " + (i + 1));
      return;
    }
  }
  return -1;
}

findElementExistence(11);

function findElementUsingInBuiltFunc(x) {
  let index = arr.indexOf(x);
  return index !== -1 ? "Func 2 Index " + (index + 1) : -1;
}

findElementUsingInBuiltFunc();

let res = findElementUsingInBuiltFunc(10);
console.log(res);

//2. Example - negative numbers in the array
function countAllNegativeNumbers(arr) {
  let count = 0;
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] < 0) count++;
  }
  return count;
}
let res1 = countAllNegativeNumbers(arr);
console.log("Negative Number count " + res1);

//3. Example - find largest number in the array

function findLargestNumber(arr) {
  let max = arr[0];
  // two approaches either consider first element of array OR
  // consider -Infinity (Negative Infinity/ Number.NEGATIVE_INFINITY : src- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Infinity)
  // But that introduces a "magic value" that doesn’t come from the array itself.
  // Starting with arr[0] is cleaner and avoids unnecessary assumptions, because the largest number must be one of the elements in the array.
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] > max) {
      max = arr[i];
    }
  }
  return max;
}

let res2 = findLargestNumber(arr);
console.log("Max number " + res2);

//4. Example - find smallest number in the array

function findSmallestNumber(arr) {
  let min = arr[0];
// Two approaches:
// 1. Initialize min = arr[0] (the first element of the array).
//    This is clean and avoids introducing any external "magic value".
// 2. Alternatively, initialize min = Infinity (Number.POSITIVE_INFINITY).
//    This works, but Infinity is not from the array itself.
//
// Starting with arr[0] is usually better, because the smallest number
// must also be one of the elements in the array.  for (let i = 0; i < arr.length; i++) {
    if (arr[i] < min) {
      min = arr[i];
    }
  }
  return min;
}

let res3 = findSmallestNumber(arr);
console.log("Min number " + res3);

//5. Example - find 2nd largest number in the array

function findSecondLargestNumber(arr) {
  if (arr.length < 2)
        return "Array should have atleast 2 elements"
  // Two approaches:
  // 1. Initialize arr[0] (the first element of the array) = first largest and arr[1] (the first element of the array) = second largest.
  //    This is clean and avoids introducing any external "magic value".
  // 2. Alternatively, first largest and second largest as -Infinity

    let firstLargest = arr[0];
    let secondLargest = arr[1];


    for (let i = 0; i < arr.length; i++) {
        if (arr[i] > firstLargest) {
            secondLargest = firstLargest;
            firstLargest = arr[i];
        }
        else if (arr[i] > secondLargest && arr[i] != firstLargest)
            secondLargest = arr[i];
    }
    console.log("First Largest :" + firstLargest);
    console.log("Second Largest :" + secondLargest);
}

findSecondLargestNumber(arr);
let res3 = findSmallestNumber(arr);
console.log("Min number " + res3);
```

## Loops within Loops

```javascript
// Example 1

for (let i = 0; i < 3; i++) {
  for (let j = 0; j < 3; j++) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output
/*
i = 0 || j= 0
i = 0 || j= 1
i = 0 || j= 2
i = 1 || j= 0
i = 1 || j= 1
i = 1 || j= 2
i = 2 || j= 0
i = 2 || j= 1
i = 2 || j= 2
*/

// Example 2

for (let i = 0; i < 3; i++) {
  for (let j = 0; j < i; j++) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output
/*
i = 1 || j= 0
i = 2 || j= 0
i = 2 || j= 1
*/

// Example 3

for (let i = 0; i < 3; i++) {
  for (let j = 0; j <= i; j++) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output
/*
i = 0 || j= 0
i = 1 || j= 0
i = 1 || j= 1
i = 2 || j= 0
i = 2 || j= 1
i = 2 || j= 2
*/

// Example 4

for (let i = 0; i < 3; i++) {
  for (let j = i; j > 0; j--) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output
/*
i = 1 || j= 1
i = 2 || j= 2
i = 2 || j= 1
*/

// Example 5

for (let i = 5; i > 0; i--) {
  for (let j = 0; j < i; j++) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output
/*
i = 5 || j= 0
i = 5 || j= 1
i = 5 || j= 2
i = 5 || j= 3
i = 5 || j= 4
i = 4 || j= 0
i = 4 || j= 1
i = 4 || j= 2
i = 4 || j= 3
i = 3 || j= 0
i = 3 || j= 1
i = 3 || j= 2
i = 2 || j= 0
i = 2 || j= 1
i = 1 || j= 0
*/

// Example 6

for (let i = 5; i > 0; i--) {
  for (let j = i; j > 0; j--) {
    console.log(`i = ${i} || j= ${j}`);
  }
}

// Output
/*
i = 5 || j= 5
i = 5 || j= 4
i = 5 || j= 3
i = 5 || j= 2
i = 5 || j= 1
i = 4 || j= 4
i = 4 || j= 3
i = 4 || j= 2
i = 4 || j= 1
i = 3 || j= 3
i = 3 || j= 2
i = 3 || j= 1
i = 2 || j= 2
i = 2 || j= 1
i = 1 || j= 1
*/
```
