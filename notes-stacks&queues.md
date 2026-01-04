# Complete Stack & Queue Guide

## Table of Contents

1. [Core Concepts](#core-concepts)
2. [JavaScript Implementations](#javascript-implementations)
3. [Java Implementations](#java-implementations)
4. [Advanced: Stack Using Queue & Queue Using Stack](#advanced-stack-using-queue--queue-using-stack)
5. [Comparison & Use Cases](#comparison--use-cases)

---

## Core Concepts

### What is a Stack?

A stack is a **Last-In-First-Out (LIFO)** data structure — the last element pushed is the first popped.

**Operations:**

- **Push:** Add an element to the top
- **Pop:** Remove and return the top element
- **Peek/Top:** View the top element without removing it

**Example Flow:**

```
stack.push(1) → [1]
stack.push(3) → [1, 3]
stack.pop()   → returns 3 → [1]
stack.peek()  → returns 1
```

**Common Examples:**

- Stacks of books
- Undo/Redo systems
- Function call (runtime) stack
- Browsing history (back button)
- Expression evaluation (parentheses matching)

**When to Use:**

- Reversing order
- Backtracking algorithms
- Recursion simulation
- Depth-First Search (DFS)
- Parsing expressions

---

### What is a Queue?

A queue is a **First-In-First-Out (FIFO)** data structure — the first element enqueued is the first dequeued.

**Operations:**

- **Enqueue:** Add an element to the end
- **Dequeue:** Remove and return the front element
- **Peek/Front:** View the front element without removing it

**Example Flow:**

```
queue.enqueue(1) → [1]
queue.enqueue(3) → [1, 3]
queue.dequeue()  → returns 1 → [3]
queue.peek()     → returns 3
```

**Common Examples:**

- Ticket counters, printer queues
- Task scheduling, message queues
- Breadth-First Search (BFS)
- Level-order tree traversal
- Producer-consumer patterns

**When to Use:**

- Order-preserving processing
- Producer-consumer patterns
- Breadth-first algorithms
- Task scheduling
- Stream processing

---

### Stack vs Queue — Quick Comparison

| Aspect              | Stack                              | Queue                                   |
| ------------------- | ---------------------------------- | --------------------------------------- |
| **Order**           | Last-In, First-Out (LIFO)          | First-In, First-Out (FIFO)              |
| **Primary Access**  | Top only (push, pop, peek)         | Front and back (enqueue, dequeue, peek) |
| **Operations**      | push, pop, peek                    | enqueue, dequeue, peek                  |
| **Time Complexity** | All O(1) amortized                 | All O(1) amortized                      |
| **Use Cases**       | Recursion, backtracking, DFS, undo | BFS, scheduling, producer-consumer      |
| **When to Prefer**  | Reverse-order or depth-first       | Order-preserving or breadth-first       |

---

## JavaScript Implementations

### Stack in JavaScript

**Simple Array-Based Stack:**

```javascript
let stack = []; // Use array as stack

// Push elements
stack.push(1);
stack.push(2);
stack.push(3);
console.log(stack); // [1, 2, 3]

// Pop elements (LIFO - Last In, First Out)
stack.pop(); // Removes 3
stack.pop(); // Removes 2
console.log(stack); // [1]

// Push again
stack.push(7);
console.log(stack); // [1, 7]

// Peek at top element
let top = stack[stack.length - 1];
console.log(top); // 7

// Check if empty
let isEmpty = stack.length === 0;
console.log(isEmpty); // false

// ❌ Invalid: stack[3] – violates stack discipline
// Direct array access works but breaks stack semantics
// Always use push, pop, and peek for proper stack usage
```

**Using Deque (Recommended for Production):**

```javascript
// JavaScript doesn't have built-in Deque, but you can use array methods
const stack = [];
stack.push(1); // push
stack.push(3);
const top = stack.pop(); // pop -> 3
const peek = stack[stack.length - 1]; // peek -> 1
```

---

### Queue in JavaScript

**⚠️ Naive Array-Based Queue (Inefficient):**

```javascript
let q = [];

// Enqueue (add to end)
q.push(1);
q.push(2);
q.push(3);
console.log(q); // [1, 2, 3]

// Dequeue (remove from front) - O(n) operation!
q.shift(); // Removes 1 - SLOW for large arrays!

// Peek (view front)
let front = q[0];
console.log(front); // 2

// ❌ Invalid for Queue:
q.pop(); // This removes from end and breaks FIFO logic
// Queue should only remove from the front
```

**✅ Efficient Queue Implementation:**

```javascript
class Queue {
  constructor() {
    this._ = []; // backing array
    this.head = 0; // index of current front
  }

  enqueue(v) {
    this._.push(v);
  }

  dequeue() {
    if (this.head >= this._.length) return undefined;
    const val = this._[this.head++];

    // Optional: periodically trim backing array to save memory
    if (this.head > 1000) {
      this._ = this._.slice(this.head);
      this.head = 0;
    }
    return val;
  }

  peek() {
    return this._[this.head];
  }

  size() {
    return this._.length - this.head;
  }

  isEmpty() {
    return this.head >= this._.length;
  }
}

// Usage:
const queue = new Queue();
queue.enqueue(1);
queue.enqueue(3);
const front = queue.dequeue(); // O(1) operation
const peek = queue.peek();
```

**Why This is Better:**

- `shift()` is O(n) because it moves all elements
- Our implementation uses a `head` pointer for O(1) dequeue
- Periodically trims array to prevent memory leak

---

## Java Implementations

### Recommended: Using Deque/ArrayDeque

**Stack (using Deque):**

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> stack = new ArrayDeque<>();
stack.push(1);            // push
stack.push(3);
int top = stack.pop();    // pop -> 3
int peek = stack.peek();  // peek -> 1
boolean empty = stack.isEmpty();
```

**Queue (using Deque):**

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> queue = new ArrayDeque<>();
queue.offer(1);           // enqueue
queue.offer(3);
int front = queue.poll(); // dequeue -> 1
int peek = queue.peek();  // peek -> 3
boolean empty = queue.isEmpty();
```

---

### Custom Implementations from Scratch

#### Basic Stack (Array-Based)

```java
class Stack<T> {
    private int maxSize;
    private T[] stackArray;
    private int top;

    @SuppressWarnings("unchecked")
    public Stack(int size) {
        this.maxSize = size;
        this.stackArray = (T[]) new Object[maxSize];
        this.top = -1; // -1 indicates empty stack
    }

    public void push(T value) {
        if (isFull()) {
            throw new IllegalStateException("Stack overflow");
        }
        stackArray[++top] = value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        T value = stackArray[top];
        stackArray[top--] = null; // Help GC
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return stackArray[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public int size() {
        return top + 1;
    }
}

// Time Complexity: All operations O(1)
// Space Complexity: O(n) where n is maxSize
```

#### Basic Queue (Circular Array)

```java
class Queue<T> {
    private int maxSize;
    private T[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    @SuppressWarnings("unchecked")
    public Queue(int size) {
        this.maxSize = size;
        this.queueArray = (T[]) new Object[maxSize];
        this.front = 0;
        this.rear = -1;
        this.nItems = 0;
    }

    public void enqueue(T value) {
        if (isFull()) {
            throw new IllegalStateException("Queue overflow");
        }
        // Wrap around to beginning if at end
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queueArray[++rear] = value;
        nItems++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        T temp = queueArray[front];
        queueArray[front] = null; // Help GC
        front++;
        // Wrap around to beginning if at end
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queueArray[front];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }

    public int size() {
        return nItems;
    }
}

// Time Complexity: All operations O(1)
// Space Complexity: O(n) where n is maxSize
```

**Why Circular Queue?**

- Avoids wasted space when elements are dequeued
- No need to shift elements
- Uses full array capacity efficiently

---

## Advanced: Stack Using Queue & Queue Using Stack

### JavaScript: Stack Using Single Queue

**Approach: Make push() expensive, keep pop() cheap**

```javascript
var MyStack = function () {
  this.q = [];
};

MyStack.prototype.push = function (x) {
  this.q.push(x);
  // Don't rotate - make push O(1)
};

MyStack.prototype.pop = function () {
  let n = this.q.length;
  // Rotate n-1 elements to bring last to front
  for (let i = 0; i < n - 1; i++) {
    this.q.push(this.q.shift());
  }
  return this.q.shift();
};

MyStack.prototype.top = function () {
  let n = this.q.length;
  // Rotate n-1 elements
  for (let i = 0; i < n - 1; i++) {
    this.q.push(this.q.shift());
  }
  let front = this.q[0];
  this.q.push(this.q.shift()); // Rotate last element back
  return front;
};

MyStack.prototype.empty = function () {
  return this.q.length === 0;
};

// Time Complexity:
// - push(): O(1)
// - pop(): O(n)
// - top(): O(n)
// - empty(): O(1)

// Space Complexity: O(n)
```

---

### JavaScript: Stack Using Two Queues

```javascript
var MyStackUsingTwoQueues = function () {
  this.q1 = [];
  this.q2 = [];
};

MyStackUsingTwoQueues.prototype.push = function (x) {
  this.q1.push(x);
};

MyStackUsingTwoQueues.prototype.pop = function () {
  let n = this.q1.length;
  // Transfer all but last element to q2
  for (let i = 0; i < n - 1; i++) {
    this.q2.push(this.q1.shift());
  }
  let ans = this.q1.shift();
  // Swap queues
  let temp = this.q1;
  this.q1 = this.q2;
  this.q2 = temp;
  return ans;
};

MyStackUsingTwoQueues.prototype.top = function () {
  let n = this.q1.length;
  // Transfer all but last element to q2
  for (let i = 0; i < n - 1; i++) {
    this.q2.push(this.q1.shift());
  }
  let front = this.q1[0];
  this.q2.push(this.q1.shift());
  // Swap queues
  let temp = this.q1;
  this.q1 = this.q2;
  this.q2 = temp;
  return front;
};

MyStackUsingTwoQueues.prototype.empty = function () {
  return this.q1.length === 0;
};

// Time Complexity:
// - push(): O(1)
// - pop(): O(n)
// - top(): O(n)
// - empty(): O(1)

// Space Complexity: O(n)
```

---

### Java: Stack Using Single Queue

**Approach: Make push() expensive (rotate on push)**

```java
class MyStackUsingSingleQueue<T> {
    private Queue<T> queue;

    public MyStackUsingSingleQueue(int size) {
        this.queue = new Queue<>(size);
    }

    // Rotate on push - O(n)
    public void push(T value) {
        int n = queue.size();
        queue.enqueue(value);
        // Rotate all previous elements to back
        for (int i = 0; i < n; i++) {
            queue.enqueue(queue.dequeue());
        }
    }

    // Pop from front - O(1)
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return queue.dequeue();
    }

    public T top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Algorithm:
// 1. Enqueue new element
// 2. Rotate all previous elements by dequeuing and re-enqueuing
// 3. Newest element is now at front

// Example: push(1), push(2), push(3)
// After push(1): [1]
// After push(2): [2, 1]  (rotated 1 to back)
// After push(3): [3, 2, 1]  (rotated 2,1 to back)

// Time Complexity:
// - push(): O(n)
// - pop(): O(1)
// - top(): O(1)
```

---

### Java: Stack Using Two Queues

```java
class MyStackUsingTwoQueues<T> {
    private Queue<T> queue1;
    private Queue<T> queue2;

    public MyStackUsingTwoQueues(int size) {
        this.queue1 = new Queue<>(size);
        this.queue2 = new Queue<>(size);
    }

    public void push(T value) {
        queue1.enqueue(value);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        // Transfer all but last element to queue2
        while (queue1.size() > 1) {
            queue2.enqueue(queue1.dequeue());
        }
        T poppedValue = queue1.dequeue();
        // Swap queue references
        Queue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        return poppedValue;
    }

    public T top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        // Transfer all but last element to queue2
        while (queue1.size() > 1) {
            queue2.enqueue(queue1.dequeue());
        }
        T topValue = queue1.peek();
        queue2.enqueue(queue1.dequeue());
        // Swap queue references
        Queue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        return topValue;
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }
}

// Algorithm:
// 1. For push: simply enqueue to queue1
// 2. For pop: move all but last to queue2, dequeue last, swap queues
// 3. For top: same as pop but peek instead of dequeue

// Example: push(1), push(2), push(3), pop()
// After pushes: queue1=[1,2,3], queue2=[]
// During pop: move 1,2 to queue2, pop 3
// After pop: queue1=[1,2], queue2=[] (queues swapped)

// Time Complexity:
// - push(): O(1)
// - pop(): O(n)
// - top(): O(n)
```

---

### Queue Using Two Stacks

Now let's look at the opposite problem: implementing a queue using stacks!

#### JavaScript: Queue Using Two Stacks

**Approach: Use two stacks - one for enqueue, one for dequeue**

```javascript
var MyQueue = function () {
  this.s1 = []; // stack for enqueue
  this.s2 = []; // stack for dequeue
};

MyQueue.prototype.push = function (x) {
  this.s1.push(x);
};

MyQueue.prototype.pop = function () {
  // If s2 is empty, transfer all elements from s1
  if (this.s2.length === 0) {
    while (this.s1.length) {
      this.s2.push(this.s1.pop());
    }
  }
  return this.s2.pop();
};

MyQueue.prototype.peek = function () {
  // If s2 is empty, transfer all elements from s1
  if (this.s2.length === 0) {
    while (this.s1.length) {
      this.s2.push(this.s1.pop());
    }
  }
  return this.s2[this.s2.length - 1];
};

MyQueue.prototype.empty = function () {
  return this.s1.length === 0 && this.s2.length === 0;
};

// Example usage:
const myQueue = new MyQueue();
myQueue.push(1);
myQueue.push(2);
console.log(myQueue.peek()); // returns 1
console.log(myQueue.pop()); // returns 1
console.log(myQueue.empty()); // returns false

// Time Complexity:
// - push(): O(1)
// - pop(): Amortized O(1) - worst case O(n) when transferring
// - peek(): Amortized O(1) - worst case O(n) when transferring
// - empty(): O(1)

// Space Complexity: O(n)

// Algorithm Explanation:
// 1. s1 (newest): Used for enqueue operations
// 2. s2 (oldest): Used for dequeue operations
// 3. When dequeuing and s2 is empty, transfer all from s1 to s2
// 4. This reverses the order, making FIFO possible

// Example: push(1), push(2), push(3), pop()
// After pushes: s1=[1,2,3], s2=[]
// During pop: transfer to s2 -> s1=[], s2=[3,2,1]
// Pop from s2 returns 1 (bottom of s1, front of queue)
```

**Why This Works:**

- Stack 1 maintains elements in arrival order (newest on top)
- When we need to dequeue, we reverse them into Stack 2
- Stack 2 now has elements in reverse order (oldest on top)
- This lazy transfer makes amortized time O(1)

---

#### Java: Queue Using Two Stacks

```java
class MyQueueUsingStacks {
    private Stacks stackNewest;  // for enqueue
    private Stacks stackOldest;  // for dequeue

    public MyQueueUsingStacks(int size) {
        this.stackNewest = new Stacks(size);
        this.stackOldest = new Stacks(size);
    }

    // Add element to the end of the queue - O(1)
    public void enqueue(int value) {
        stackNewest.push(value);
    }

    // Move elements from stackNewest to stackOldest if needed
    private void shiftStacks() {
        if (stackOldest.isEmpty()) {
            while (!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    // Remove and return the front element - Amortized O(1)
    public int dequeue() {
        shiftStacks();
        if (stackOldest.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return stackOldest.pop();
    }

    // Return front element without removing - Amortized O(1)
    public int peek() {
        shiftStacks();
        if (stackOldest.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return stackOldest.peek();
    }

    // Check if queue is empty - O(1)
    public boolean isEmpty() {
        return stackNewest.isEmpty() && stackOldest.isEmpty();
    }
}

// Algorithm Visualization:
// enqueue(1), enqueue(2), enqueue(3)
// stackNewest: [1, 2, 3] (top)
// stackOldest: []

// dequeue() called:
// Transfer: pop from stackNewest, push to stackOldest
// stackNewest: []
// stackOldest: [3, 2, 1] (top)
// Pop from stackOldest returns 1 ✓ (FIFO maintained)

// enqueue(4)
// stackNewest: [4]
// stackOldest: [3, 2] (after previous dequeue)

// Next dequeue():
// No transfer needed! stackOldest still has elements
// Pop from stackOldest returns 2 ✓

// Time Complexity Analysis:
// - enqueue(): Always O(1)
// - dequeue(): Worst case O(n), but amortized O(1)
//   Each element is moved exactly once from newest to oldest
//   Over n operations, total transfers = n
//   Average per operation = n/n = O(1)
```

---

## Comparison & Use Cases

### When to Use Stack

✅ **Perfect for:**

- Undo/Redo functionality
- Recursive algorithms (DFS, backtracking)
- Expression evaluation (postfix, infix)
- Parentheses matching
- Function call stack simulation
- Browser history (back button)

❌ **Not suitable for:**

- Processing items in arrival order
- Breadth-first searches
- Task scheduling

### When to Use Queue

✅ **Perfect for:**

- Task scheduling
- Breadth-first search (BFS)
- Level-order tree traversal
- Producer-consumer problems
- Print spooling
- Handling requests in order

❌ **Not suitable for:**

- Undo operations
- Depth-first processing
- Reverse-order processing

### Implementation Trade-offs

**Stack Using Single Queue:**

- ✅ Uses less space (one queue)
- ✅ Simpler to implement
- ❌ Push is O(n) if rotating on push
- ❌ Pop/Top is O(n) if rotating on pop

**Stack Using Two Queues:**

- ✅ Clear separation of roles
- ✅ Can optimize based on usage pattern
- ❌ Uses more space (two queues)
- ❌ More complex swapping logic

**Queue Using Two Stacks:**

- ✅ Amortized O(1) for all operations
- ✅ Efficient lazy transfer approach
- ✅ Each element moved at most once
- ❌ Worst case O(n) for individual operations
- ✅ Best approach for queue simulation

### Best Practices

1. **Use Built-in Collections in Production:**

   - Java: `ArrayDeque` for both stack and queue
   - JavaScript: Arrays with proper methods

2. **Custom Implementations:**

   - Use generics in Java for type safety
   - Throw exceptions instead of printing errors
   - Add `isFull()` for bounded structures
   - Set `null` after removal to help GC

3. **Complexity Awareness:**

   - JavaScript `shift()` is O(n) - avoid for large queues
   - Use circular arrays for efficient queues
   - Know when push vs pop should be expensive

4. **Interview Tips:**
   - Explain trade-offs clearly
   - Mention both approaches (single vs two queues/stacks)
   - Discuss time/space complexity
   - Consider real-world usage patterns

---

## Summary

| Structure            | Order | Operations             | Best Use Case              |
| -------------------- | ----- | ---------------------- | -------------------------- |
| **Stack**            | LIFO  | push, pop, peek        | DFS, recursion, undo       |
| **Queue**            | FIFO  | enqueue, dequeue, peek | BFS, scheduling, streaming |
| **Stack (1 Queue)**  | LIFO  | O(n) push or pop       | Space-constrained          |
| **Stack (2 Queues)** | LIFO  | O(1) push, O(n) pop    | Interview practice         |
| **Queue (2 Stacks)** | FIFO  | O(1) amortized all ops | Practical queue simulation |

**Key Takeaway:** Choose the right data structure based on your access pattern. Stack for reverse-order, Queue for first-come-first-served!

**Interview Bonus:** Queue using two stacks is the most efficient "opposite implementation" because of its amortized O(1) complexity for all operations!
