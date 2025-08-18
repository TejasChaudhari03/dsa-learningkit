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

Alright — here’s a **JavaScript vs Java side-by-side cheat sheet** matching the concepts you listed.
I’ve kept the formatting tight so you can compare quickly at a glance.

---

## **JavaScript vs Java — Syntax Comparison**

| Concept                  | JavaScript                                                | Java                                                                 |
| ------------------------ | --------------------------------------------------------- | -------------------------------------------------------------------- |
| **Variable Declaration** | `let x = 5;` <br> `const PI = 3.14;` <br> `var y = 10;`   | `int x = 5;` <br> `final double PI = 3.14;`                          |
| **Typing**               | Dynamic (`let a = 5; a = "hi";`)                          | Static (`int a = 5; // must stay int`)                               |
| **Scope**                | `let`/`const` → block-scoped <br> `var` → function/global | Block or class scoped                                                |
| **Constant**             | `const name = "John";`                                    | `final String name = "John";`                                        |
| **Hoisting**             | `var` is hoisted, `let`/`const` in TDZ                    | No hoisting                                                          |
| **Function**             | `js function add(a,b){return a+b;} `                      | `java static int add(int a,int b){return a+b;} `                     |
| **String**               | `"Hello"` or `'Hello'` or `` `Hello` ``                   | `"Hello"`                                                            |
| **Template String**      | `` `Hi ${name}` ``                                        | `"Hi " + name`                                                       |
| **If-Else**              | `js if(x>0){...}else{...} `                               | `java if(x>0){...}else{...} `                                        |
| **Switch**               | `js switch(day){case 1:...}`                              | `java switch(day){case 1:...}`                                       |
| **Loops**                | `for`, `while`, `do...while`, `for...of`, `for...in`      | `for`, `while`, `do...while`, enhanced `for`                         |
| **Array**                | `let arr = [1,2,3];`                                      | `int[] arr = {1,2,3};`                                               |
| **Object**               | `let obj = {name:"John"};`                                | `class Person {String name;}`                                        |
| **Method in Object**     | `js greet(){return "Hi";} `                               | `java String greet(){return "Hi";} `                                 |
| **Class**                | `class Person { constructor(name){this.name=name;} }`     | `class Person { String name; Person(String name){this.name=name;} }` |
| **Module Import**        | `import {x} from './file.js'`                             | `import java.util.Scanner;`                                          |
| **Printing**             | `console.log("Hello");`                                   | `System.out.println("Hello");`                                       |
| **Input**                | `prompt("Enter:")`                                        | `Scanner sc = new Scanner(System.in); sc.nextLine();`                |
| **Error Handling**       | `js try {...} catch(e){...} finally {...} `               | `java try {...} catch(Exception e){...} finally {...} `              |

---

Ah, I see the problem — side-by-side _tables_ work fine for quick syntax comparisons, but for slightly longer examples like “Even or Odd” and “Eligible to Vote,” the table format just squishes things and makes it harder to read.

Instead, here’s a **clear separation**:
I’ll show **JavaScript** first, then **Java**, with nice spacing and headings so your brain isn’t bouncing between columns.

---

### **Even or Odd**

**JavaScript**

```javascript
function evenOrOdd(num) {
  if (num % 2 == 0) {
    console.log(num + " is even");
  } else {
    console.log(num + " is odd");
  }
}

evenOrOdd(10);
evenOrOdd(11);
```

**Java**

```java
static void evenOrOdd(int num) {
    if (num % 2 == 0) {
        System.out.println(num + " is even");
    } else {
        System.out.println(num + " is odd");
    }
}

public static void main(String[] args) {
    evenOrOdd(10);
    evenOrOdd(11);
}
```

---

### **Eligible to Vote**

**JavaScript**

```javascript
function eligibleToVote(age) {
  if (age < 0) {
    console.log("Invalid");
  } else if (age < 18) {
    console.log("Not eligible");
  } else {
    console.log("Eligible");
  }
}

eligibleToVote(-10);
eligibleToVote(10);
eligibleToVote(21);
```

**Java**

```java
static void eligibleToVote(int age) {
    if (age < 0) {
        System.out.println("Invalid");
    } else if (age < 18) {
        System.out.println("Not eligible");
    } else {
        System.out.println("Eligible");
    }
}

public static void main(String[] args) {
    eligibleToVote(-10);
    eligibleToVote(10);
    eligibleToVote(21);
}
```
