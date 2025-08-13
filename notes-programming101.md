# Basic Programming Concepts

- Variables and Constants
- Control Flow (if-else, loops)
- Functions and Methods
- Data Structures
- Algorithms
- Input/Output
- Error Handling

# Javascript

## Data Types

- Primitive Types
  - Number
  - String
  - Boolean
  - Undefined
  - Null
  - Symbol
  - BigInt
- Reference Types
  - Object
  - Array
  - Function

## Syntax

- Variables (let, const, var)
- Operators
- Conditionals
- Loops
- Functions
- Classes
- Modules
- Error Handling (try-catch)

## Variables (let, const, var)

### var

- Function-scoped or globally-scoped
- Can be redeclared and reassigned
- Hoisted to the top of its scope and initialized with undefined
- Creates property on global object (window) when globally scoped

```javascript
var x = 1;
var x = 2; // Valid redeclaration
x = 3; // Valid reassignment
```

### let

- Block-scoped
- Cannot be redeclared in same scope
- Can be reassigned
- Hoisted but not initialized (Temporal Dead Zone)

```javascript
let y = 1;
// let y = 2; // Error: cannot redeclare
y = 2; // Valid reassignment
{
  let y = 3; // Valid in new block scope
}
```

### const

- Block-scoped
- Must be initialized during declaration
- Cannot be reassigned
- For objects/arrays, properties can still be modified

```javascript
const PI = 3.14;
// PI = 3.142; // Error: cannot reassign

// Object mutation is allowed
const user = { name: "John" };
user.name = "Jane"; // Valid
user.age = 25; // Valid
// user = {}; // Error: cannot reassign
```

### Hoisting Behavior

```javascript
console.log(varVariable); // undefined
console.log(letVariable); // ReferenceError
console.log(constVariable); // ReferenceError

var varVariable = 1;
let letVariable = 2;
const constVariable = 3;
```

### Best Practices

- Use `const` by default
- Use `let` when value needs to change
- Avoid `var` in modern JavaScript
- Declare variables at the top of their scope

## Examples

```javascript
// Variable declaration
let message = "Hello World";

// Function example
function add(a, b) {
  console.log(a + b);
}

function greet(name) {
  console.log("Namaste, " + name);
}

function square(x) {
  let res = x * x;
  return res;
  //return x*x;
}

let value = square(2);
console.log(value);
// Array example
const fruits = ["apple", "banana", "orange"];

// Object example
const person = {
  name: "John",
  age: 30,
  greet() {
    return `Hello, I'm ${this.name}`;
  },
};
```

```javascript
// If else example

//- Function to check vote eligibility

function eligibleToVote(age) {
  if (age < 0) console.log("Invalid input");
  else if (age < 18) console.log("Not eligible");
  else console.log("Eligible");
}

eligibleToVote(-10);
eligibleToVote(10);
eligibleToVote(21);

//- Func to check num even or odd

funciton evenOrOdd(num){
    if(num%2==0)
        console.log(Num+ " is even");
    else
        console.log(Num+ " is odd");
}

evenOrOdd(10);
evenOrOdd(11);
```
