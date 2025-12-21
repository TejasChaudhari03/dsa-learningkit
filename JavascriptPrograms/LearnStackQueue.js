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
