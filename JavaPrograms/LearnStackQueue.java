package JavaPrograms;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
public class LearnStackQueue {
    public static void main(String[] args) {
        // Test Stack
        System.out.println("Testing Stack:");
        Stacks stack = new Stacks(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Peek: " + stack.peek()); // 30
        System.out.println("Pop: " + stack.pop());   // 30
        System.out.println("Is Empty: " + stack.isEmpty()); // false

        // Test Queue
        System.out.println("\nTesting Queue:");
        Queues queue = new Queues(5);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Dequeue: " + queue.dequeue()); // 10
        System.out.println("Is Empty: " + queue.isEmpty()); // false

        // Test MyStackUsingSingleQueue
        System.out.println("\nTesting MyStackUsingSingleQueue:");
        MyStackUsingSingleQueue stackOneQueue = new MyStackUsingSingleQueue(5);
        stackOneQueue.push(1);
        stackOneQueue.push(2);
        stackOneQueue.push(3);
        System.out.println("Top: " + stackOneQueue.top());   // 3
        System.out.println("Pop: " + stackOneQueue.pop());   // 3
        System.out.println("Is Empty: " + stackOneQueue.isEmpty()); // false

        // Test MyStackUsingTwoQueues
        System.out.println("\nTesting MyStackUsingTwoQueues:");
        MyStackUsingTwoQueues stackTwoQueues = new MyStackUsingTwoQueues(5);
        stackTwoQueues.push(1);
        stackTwoQueues.push(2);
        stackTwoQueues.push(3);
        System.out.println("Top: " + stackTwoQueues.top());   // 3
        System.out.println("Pop: " + stackTwoQueues.pop());   // 3
        System.out.println("Is Empty: " + stackTwoQueues.isEmpty()); // false
    
        // Implement Queue using Stacks
        MyQueueUsingStacks queueUsingStacks = new MyQueueUsingStacks(5);
        queueUsingStacks.enqueue(10);
        queueUsingStacks.enqueue(20);
        queueUsingStacks.enqueue(30);
        System.out.println("Dequeue: " + queueUsingStacks.dequeue()); // 10
        System.out.println("Is Empty: " + queueUsingStacks.isEmpty()); // false


        // Valid Parentheses        
        // System.out.println("\nTesting Valid Parentheses:");
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        System.out.println("Is Valid (\"()\"): " + isValidParentheses(s1));
        System.out.println("Is Valid (\"()[]{}\"): " + isValidParentheses(s2));
        System.out.println("Is Valid (\"(]\"): " + isValidParentheses(s3));
    }

    // Validate parentheses using a Deque to match pairs
    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c != map.get(top)) return false;
            }
        }
        return stack.isEmpty();
    }
}

/**
 * Basic Stack implementation using an array
 * Follows LIFO (Last In First Out) principle
 * 
 * Time Complexity:
 * - push(): O(1)
 * - pop(): O(1)
 * - peek(): O(1)
 * - isEmpty(): O(1)
 * 
 * Space Complexity: O(n) where n is maxSize
 */
class Stacks {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public Stacks(int size) {
        this.maxSize = size;
        this.stackArray = new int[maxSize];
        this.top = -1; // -1 indicates empty stack
    }

    // Add element to top of stack
    public void push(int value) {
        if (top < maxSize - 1) {
            stackArray[++top] = value;
        } else {
            System.out.println("Stack is full");
        }
    }

    // Remove and return top element
    public int pop() {
        if (top >= 0) {
            return stackArray[top--];
        } else {
            System.out.println("Stack is empty");
            return -1;
        }
    }

    // Return top element without removing
    public int peek() {
        if (top >= 0) {
            return stackArray[top];
        } else {
            System.out.println("Stack is empty");
            return -1;
        }
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }
}

/**
 * Basic Queue implementation using a circular array
 * Follows FIFO (First In First Out) principle
 * 
 * Time Complexity:
 * - enqueue(): O(1)
 * - dequeue(): O(1)
 * - isEmpty(): O(1)
 * 
 * Space Complexity: O(n) where n is maxSize
 */
class Queues {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    public Queues(int size) {
        this.maxSize = size;
        this.queueArray = new int[maxSize];
        this.front = 0;
        this.rear = -1;
        this.nItems = 0;
    }

    // Add element to rear of queue
    public void enqueue(int value) {
        if (nItems < maxSize) {
            // Wrap around to beginning if at end
            if (rear == maxSize - 1) {
                rear = -1;
            }
            queueArray[++rear] = value;
            nItems++;
        } else {
            System.out.println("Queue is full");
        }
    }

    // Remove and return front element
    public int dequeue() {
        if (nItems > 0) {
            int temp = queueArray[front++];
            // Wrap around to beginning if at end
            if (front == maxSize) {
                front = 0;
            }
            nItems--;
            return temp;
        } else {
            System.out.println("Queue is empty");
            return -1;
        }
    }

    // Return front element without removing
    public int peek() {
        if (nItems > 0) {
            return queueArray[front];
        } else {
            System.out.println("Queue is empty");
            return -1;
        }
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return nItems == 0;
    }

    // Get current size of queue
    public int size() {
        return nItems;
    }
}

/**
 * Stack implementation using a single queue
 * Maintains LIFO order by rotating elements on push
 * 
 * Time Complexity:
 * - push(): O(n) - rotates all existing elements
 * - pop(): O(1) - simply dequeues front
 * - top(): O(1) - peeks at front
 * - isEmpty(): O(1)
 * 
 * Space Complexity: O(n) where n is number of elements
 * 
 * Explanation:
 * The key insight is to make push expensive instead of pop.
 * When we push a new element, we:
 * 1. Enqueue the new element
 * 2. Rotate all previous elements by dequeuing and re-enqueuing them
 * This ensures the newest element is always at the front
 */
class MyStackUsingSingleQueue {
    private Queues queue;

    public MyStackUsingSingleQueue(int size) {
        this.queue = new Queues(size);
    }

    // Add element and rotate queue to maintain LIFO order
    public void push(int value) {
        int n = queue.size();
        queue.enqueue(value);
        // Rotate all previous elements to back
        for (int i = 0; i < n; i++) {
            queue.enqueue(queue.dequeue());
        }
    }

    // Remove and return top element
    public int pop() {
        if (queue.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return queue.dequeue();
    }

    // Return top element without removing
    public int top() {
        if (queue.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return queue.peek();
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

/**
 * Stack implementation using two queues
 * Maintains LIFO order by transferring elements between queues
 * 
 * Time Complexity:
 * - push(): O(1) - simply enqueues to queue1
 * - pop(): O(n) - transfers n-1 elements to queue2
 * - top(): O(n) - transfers n-1 elements, peeks, then transfers back
 * - isEmpty(): O(1)
 * 
 * Space Complexity: O(n) where n is number of elements
 * 
 * Explanation:
 * We use two queues to simulate stack behavior:
 * 1. For push: simply enqueue to queue1
 * 2. For pop: move all but last element to queue2, dequeue last, swap queues
 * 3. For top: same as pop but don't remove the element
 * This ensures LIFO order using only queue operations
 */
class MyStackUsingTwoQueues {
    private Queues queue1;
    private Queues queue2;

    public MyStackUsingTwoQueues(int size) {
        this.queue1 = new Queues(size);
        this.queue2 = new Queues(size);
    }

    // Add element to queue1
    public void push(int value) {
        queue1.enqueue(value);
    }

    // Remove and return top element
    public int pop() {
        if (queue1.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        
        // Transfer all but last element to queue2
        while (queue1.size() > 1) {
            queue2.enqueue(queue1.dequeue());
        }
        
        // Dequeue the last element (top of stack)
        int poppedValue = queue1.dequeue();
        
        // Swap queue references
        Queues temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        
        return poppedValue;
    }

    // Return top element without removing
    public int top() {
        if (queue1.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        
        // Transfer all but last element to queue2
        while (queue1.size() > 1) {
            queue2.enqueue(queue1.dequeue());
        }
        
        // Peek at the last element
        int topValue = queue1.peek();
        
        // Transfer the last element to queue2 as well
        queue2.enqueue(queue1.dequeue());
        
        // Swap queue references
        Queues temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        
        return topValue;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return queue1.isEmpty();
    }                           
}

/**
 * Queue implementation using two stacks
 * enqueue: push onto stackNewest
 * dequeue: pop from stackOldest; if empty, transfer all elements from stackNewest to stackOldest first
 *
 * Time Complexity:
 * - enqueue(): O(1)
 * - dequeue(): Amortized O(1)
 * - isEmpty(): O(1)
 *
 * Space Complexity: O(n)
 */
class MyQueueUsingStacks {
    private Stacks stackNewest;
    private Stacks stackOldest;

    public MyQueueUsingStacks(int size) {
        this.stackNewest = new Stacks(size);
        this.stackOldest = new Stacks(size);
    }

    // Add element to the end of the queue
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

    // Remove and return the front element
    public int dequeue() {
        shiftStacks();
        if (stackOldest.isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return stackOldest.pop();
    }

    // Return front element without removing
    public int peek() {
        shiftStacks();
        if (stackOldest.isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return stackOldest.peek();
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return stackNewest.isEmpty() && stackOldest.isEmpty();
    }
}