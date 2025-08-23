## Loop in Loop

### Patterns

```javascript

```

#### Example - Count digits, palindrome, reverse etc.,

```javascript
// function to count digits
function countDigits(num) {
  if (num == 0) return 1;
  let count = 0;
  num = Math.abs(num);
  while (num > 0) {
    num = Math.floor(num / 10);
    count++;
  }
  return count;
}
let res = countDigits(0);
console.log(res);

// Approach - Converting num to string
function countDigits1(num) {
  // Convert to string, remove minus and decimal point
  let str = Math.abs(num).toString().replace(".", "");
  return str.length;
}

console.log(countDigits1(0)); // 1
console.log(countDigits1(1234)); // 4
console.log(countDigits1(-567)); // 3
console.log(countDigits1(12.34)); // 4
console.log(countDigits1(0.005)); // 3
console.log(countDigits1(-0.01)); // 2

// Palindrome

function palindromeCheck(x) {
  if (x < 0) return false;

  let original = x;
  let rev = 0;

  while (x > 0) {
    let rem = x % 10;
    rev = rev * 10 + rem;
    x = Math.floor(x / 10);
  }

  return original === rev;
}

console.log(palindromeCheck(121)); // true
console.log(palindromeCheck(123)); // false
```
