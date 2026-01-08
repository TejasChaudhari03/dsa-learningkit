/*
Implement a last-in-first-out (LIFO) stack using only one queue. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:
 */
var MyStack = function () {
  this.q = [];
};

/**
 * @param {number} x
 * @return {void}
 */
MyStack.prototype.push = function (x) {
  this.q.push(x);
};

/**
 * @return {number}
 */
MyStack.prototype.pop = function () {
  let n = this.q.length;
  for (let i = 0; i < n - 1; i++) {
    this.q.push(this.q.shift());
  }
  return this.q.shift();
};

/**
 * @return {number}
 */
MyStack.prototype.top = function () {
  let n = this.q.length;
  for (let i = 0; i < n - 1; i++) {
    this.q.push(this.q.shift());
  }
  let front = this.q[0];
  this.q.push(this.q.shift());

  return front;
};

/**
 * @return {boolean}
 */
MyStack.prototype.empty = function () {
  return this.q.length === 0;
};

/**
 * Your MyStack object will be instantiated and called as such:
 * var obj = new MyStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */

// Example usage:
var myStack = new MyStack();
myStack.push(1);
myStack.push(2);
console.log(myStack.top()); // returns 2
console.log(myStack.pop()); // returns 2
console.log(myStack.empty()); // returns false

// Complexity Analysis:
// Time Complexity: O(n) for pop and top operations, O(1) for push and empty operations.
// Space Complexity: O(n) where n is the number of elements in the stack.

// Explanation:
// We use a single queue to simulate stack behavior. For push, we simply enqueue the element.
// For pop and top, we rotate the queue to bring the last added element to the front, allowing us to dequeue it (for pop) or peek at it (for top). The empty function checks if the queue is empty.
// This approach ensures that we adhere to the stack's LIFO principle using only queue operations.

/*
Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
*/

var MyStackUsingTwoQueus = function () {
  this.q1 = [];
  this.q2 = [];
};

/**
 * @param {number} x
 * @return {void}
 */
MyStackUsingTwoQueus.prototype.push = function (x) {
  this.q1.push(x);
};

/**
 * @return {number}
 */
MyStackUsingTwoQueus.prototype.pop = function () {
  let n = this.q1.length;
  for (let i = 0; i < n - 1; i++) {
    // let frontEle = this.q1.shift();
    // this.q2.push(frontEle);
    this.q2.push(this.q1.shift());
  }
  let ans = this.q1.shift();
  let temp = this.q1;
  this.q1 = this.q2;
  this.q2 = temp;

  return ans;
};

/**
 * @return {number}
 */
MyStackUsingTwoQueus.prototype.top = function () {
  let n = this.q1.length;
  for (let i = 0; i < n - 1; i++) {
    this.q2.push(this.q1.shift());
  }
  let front = this.q1[0];
  this.q2.push(this.q1.shift());
  let temp = this.q1;
  this.q1 = this.q2;
  this.q2 = temp;

  return front;
};

/**
 * @return {boolean}
 */
MyStackUsingTwoQueus.prototype.empty = function () {
  return this.q1.length === 0;
};

/**
 * Your MyStackUsingTwoQueus object will be instantiated and called as such:
 * var obj = new MyStackUsingTwoQueus()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */

// Example usage:
var myStackTwoQueues = new MyStackUsingTwoQueus();
myStackTwoQueues.push(1);
myStackTwoQueues.push(2);
console.log(myStackTwoQueues.top()); // returns 2
console.log(myStackTwoQueues.pop()); // returns 2
console.log(myStackTwoQueues.empty()); // returns false

// Complexity Analysis:
// Time Complexity: O(n) for pop and top operations, O(1) for push and empty operations.
// Space Complexity: O(n) where n is the number of elements in the stack.

// Explanation:
// We use two queues to simulate stack behavior. For push, we simply enqueue the element into the first queue.
// For pop and top, we transfer all but the last element from the first queue to the second queue, allowing us to dequeue the last added element (for pop) or peek at it (for top). After that, we swap the roles of the two queues.
// The empty function checks if the first queue is empty.
// This approach ensures that we adhere to the stack's LIFO principle using only queue operations.

/*
  Implement queue using stack
*/

var MyQueue = function () {
  this.s1 = [];
  this.s2 = [];
};

/**
 * @param {number} x
 * @return {void}
 */
MyQueue.prototype.push = function (x) {
  this.s1.push(x);
};

/**
 * @return {number}
 */
MyQueue.prototype.pop = function () {
  if (this.s2.length === 0) {
    while (this.s1.length) {
      this.s2.push(this.s1.pop());
    }
  }
  return this.s2.pop();
};

/**
 * @return {number}
 */
MyQueue.prototype.peek = function () {
  if (this.s2.length === 0) {
    while (this.s1.length) {
      this.s2.push(this.s1.pop());
    }
  }
  return this.s2[this.s2.length - 1];
};

/**
 * @return {boolean}
 */
MyQueue.prototype.empty = function () {
  return this.s1.length === 0 && this.s2.length === 0;
};

/**
 * Your MyQueue object will be instantiated and called as such:
 * var obj = new MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */

// Example usage:
var myQueue = new MyQueue();
myQueue.push(1);
myQueue.push(2);
console.log(myQueue.peek()); // returns 1
console.log(myQueue.pop()); // returns 1
console.log(myQueue.empty()); // returns false

// Complexity Analysis:
// Time Complexity: Amortized O(1) for push, pop, and peek operations.
// Space Complexity: O(n) where n is the number of elements in the queue.
// Explanation:
// We use two stacks to simulate queue behavior. For push, we simply push the element onto the first stack.
// For pop and peek, if the second stack is empty, we transfer all elements from the first stack to the second stack, reversing their order. This allows us to pop or peek at the front element of the queue.
// The empty function checks if both stacks are empty.
// This approach ensures that we adhere to the queue's FIFO principle using only stack operations.

// Valid Pareentheses

/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function (s) {
  if (s.length % 2 !== 0) return false;
  let stack = [];
  let map = {
    "{": "}",
    "[": "]",
    "(": ")",
  };
  for (let i = 0; i < s.length; i++) {
    //if(s[i]==="{" || s[i]==="[" || s[i]==="(" ){
    if (map[s[i]]) {
      stack.push(s[i]);
    } else {
      let top = stack.pop();
      //if(!top || (top === "[" && s[i] !="]") || !top || (top === "{" && s[i] !="}") || !top || (top === "(" && s[i] !=")")){
      if (!top || s[i] != map[top]) {
        return false;
      }
    }
  }

  return stack.length === 0;
};

// Example usage:
console.log(isValid("()")); // true
console.log(isValid("()[]{}")); // true
console.log(isValid("(]")); // false
console.log(isValid("([)]")); // false
console.log(isValid("{[]}")); // true
// Complexity Analysis:
// Time Complexity: O(n), where n is the length of the string s.
// Space Complexity: O(n) in the worst case, when all characters are opening brackets.
// Explanation:
// We use a stack to keep track of opening brackets. For each character in the string, if it's an opening bracket, we push it onto the stack. If it's a closing bracket, we pop the top element from the stack and check if it matches the corresponding opening bracket. If there's a mismatch or if the stack is empty when we encounter a closing bracket, we return false. Finally, if the stack is empty after processing all characters, we return true, indicating that the parentheses are valid.

// Min Stack - Using Stack of Pairs to Keep Track of Minimums

var MinStack = function () {
  this.s = [];
};

/**
 * @param {number} val
 * @return {void}
 */
MinStack.prototype.push = function (val) {
  if (this.s.length === 0) {
    this.s.push([val, val]);
  } else {
    // Math.min(last min current value)
    let lastMinVal = this.s[this.s.length - 1][1];
    let minVal = Math.min(val, lastMinVal);
    this.s.push([val, minVal]);
  }
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function () {
  this.s.pop();
};

/**
 * @return {number}
 */
MinStack.prototype.top = function () {
  return this.s[this.s.length - 1][0];
};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function () {
  return this.s[this.s.length - 1][1];
};

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */

// Example usage:
var minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
console.log(minStack.getMin());
minStack.pop();
console.log(minStack.top());
console.log(minStack.getMin());
// Complexity Analysis:
// Time Complexity: O(1) for all operations (push, pop, top, getMin).
// Space Complexity: O(n) where n is the number of elements in the stack.
// Explanation:
// We use a stack to store pairs of values: the actual value and the minimum value at that point in the stack. This allows us to retrieve the minimum value in constant time. When pushing a new value, we compare it with the current minimum and store the smaller of the two as the new minimum. The pop operation simply removes the top element, and both top and getMin operations return the respective values from the top pair in the stack.

/**
 * @param {string} s
 * @return {string}
 */
var removeOuterParenthesesUsingStack = function (s) {
  let stack = [];
  let ans = "";

  for (let i = 0; i < s.length; i++) {
    if (s[i] === "(") {
      stack.push(s[i]);
      ans += stack.length > 1 ? s[i] : "";
    } else {
      ans += stack.length > 1 ? s[i] : "";
      stack.pop();
    }
  }

  return ans;
};
// Example usage:
console.log(removeOuterParenthesesUsingStack("(()())(())")); // "()()()"
console.log(removeOuterParenthesesUsingStack("(()())(())(()(()))")); // "()()()()(())"
console.log(removeOuterParenthesesUsingStack("()()")); // ""
// Complexity Analysis:
// Time Complexity: O(n), where n is the length of the string s.
// Space Complexity: O(n) in the worst case, when all characters are opening brackets.
// Explanation:
// We use a stack to keep track of the depth of nested parentheses. For each character in the string, if it's an opening bracket, we push it onto the stack and add it to the result string only if the stack size is greater than 1 (indicating it's not an outermost bracket). If it's a closing bracket, we add it to the result string only if the stack size is greater than 1 before popping from the stack. This way, we effectively remove the outermost parentheses from each primitive substring.

// Remove Outermost Parentheses - Using Level Counter
var removeOuterParenthesesUsingLevel = function (s) {
  let level = -1;
  let ans = "";

  for (let i = 0; i < s.length; i++) {
    if (s[i] === "(") {
      ++level;
      ans += level ? s[i] : "";
    } else {
      ans += level ? s[i] : "";
      --level;
    }
  }

  return ans;
};
// Example usage:
console.log(removeOuterParenthesesUsingLevel("(()())(())")); // "()()()"
console.log(removeOuterParenthesesUsingLevel("(()())(())(()(()))")); // "()()()()(())"
console.log(removeOuterParenthesesUsingLevel("()()")); // ""
// Complexity Analysis:
// Time Complexity: O(n), where n is the length of the string s.
// Space Complexity: O(n) in the worst case, when all characters are opening brackets.
// Explanation:
// We use a level counter to keep track of the depth of nested parentheses. For each character in the string, if it's an opening bracket, we increment the level and add it to the result string only if the level is greater than 0 (indicating it's not an outermost bracket). If it's a closing bracket, we add it to the result string only if the level is greater than 0 before decrementing the level. This way, we effectively remove the outermost parentheses from each primitive substring.

// Evaluate Reverse Polish Notation - RPN

/**
 * @param {string[]} tokens
 * @return {number}
 */
var evalRPN = function (tokens) {
  let stack = [];
  const map = {
    "+": (a, b) => b + a,
    "-": (a, b) => b - a,
    "*": (a, b) => b * a,
    "/": (a, b) => Math.trunc(b / a),
  };
  for (let i = 0; i < tokens.length; i++) {
    // if(tokens[i] === "+" || tokens[i] === "-" || tokens[i] === "*" || tokens[i] === "/"){
    // if(["+","-","*","/"].includes[tokens[i]]){
    if (map[tokens[i]]) {
      let a = stack.pop();
      let b = stack.pop();
      // let ans = eval(`${b} ${tokens[i]} ${a}`);
      // let ans = map[tokens[i]](Number(a),Number(b));
      let ans = map[tokens[i]](+a, +b);
      stack.push(Math.trunc(ans));
    } else {
      stack.push(tokens[i]);
    }
  }

  // return Number(stack.pop());
  return Number(stack[0]);
};

// Example usage:
console.log(evalRPN(["2", "1", "+", "3", "*"])); // 9
console.log(evalRPN(["4", "13", "5", "/", "+"])); // 6
console.log(
  evalRPN(["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"])
); // 22
// Complexity Analysis:
// Time Complexity: O(n), where n is the number of tokens.
// Space Complexity: O(n) in the worst case, when all tokens are numbers.
// Explanation:
// We use a stack to evaluate the RPN expression. For each token, if it's a number, we push it onto the stack. If it's an operator, we pop the top two numbers from the stack, apply the operator, and push the result back onto the stack. At the end, the stack contains the final result of the RPN expression.
