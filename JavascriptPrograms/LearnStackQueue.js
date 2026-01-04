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
