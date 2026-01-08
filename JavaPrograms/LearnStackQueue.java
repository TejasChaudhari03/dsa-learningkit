package JavaPrograms;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.function.BiFunction;

public class LearnStackQueue {
    public static void main(String[] args) {
        // Test Stack
        System.out.println("Testing Stack:");
        MyStack stack = new MyStack(5);
        System.out.println("MyStack created with max size 5.");
        System.out.println("Push elements 10, 20, 30 onto the stack.");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Performing peek, pop and isEmpty operations:");
        System.out.println("Peek: " + stack.peek()); // 30
        System.out.println("Pop: " + stack.pop());   // 30
        System.out.println("Is Empty: " + stack.isEmpty()); // false

        // Test Queue
        System.out.println("\nTesting Queue:");
        MyQueue queue = new MyQueue(5);
        System.out.println("MyQueue created with max size 5.");
        System.out.println("Enqueue elements 10, 20, 30 onto the queue.");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Performing peek, dequeue and isEmpty operations:");
        System.out.println("Peek: " + queue.peek()); // 10
        System.out.println("Dequeue: " + queue.dequeue()); // 10
        System.out.println("Is Empty: " + queue.isEmpty()); // false

        // Test MyStackUsingSingleQueue
        System.out.println("\nTesting MyStackUsingSingleQueue:");
        MyStackUsingSingleQueue stackOneQueue = new MyStackUsingSingleQueue(5);
        System.out.println("MyStackUsingSingleQueue created with max size 5.");
        System.out.println("Push elements 1, 2, 3 onto the stack.");
        stackOneQueue.push(1);
        stackOneQueue.push(2);
        stackOneQueue.push(3);
        System.out.println("Performing top, pop and isEmpty operations:");
        System.out.println("Top: " + stackOneQueue.top());   // 3
        System.out.println("Pop: " + stackOneQueue.pop());   // 3
        System.out.println("Is Empty: " + stackOneQueue.isEmpty()); // false

        // Test MyStackUsingTwoQueues
        System.out.println("\nTesting MyStackUsingTwoQueues:");
        MyStackUsingTwoQueues stackTwoQueues = new MyStackUsingTwoQueues(5);
        System.out.println("MyStackUsingTwoQueues created with max size 5.");
        System.out.println("Push elements 1, 2, 3 onto the stack.");
        stackTwoQueues.push(1);
        stackTwoQueues.push(2);
        stackTwoQueues.push(3);
        System.out.println("Performing top, pop and isEmpty operations:");
        System.out.println("Top: " + stackTwoQueues.top());   // 3
        System.out.println("Pop: " + stackTwoQueues.pop());   // 3
        System.out.println("Is Empty: " + stackTwoQueues.isEmpty()); // false
    
        // Implement Queue using MyStack
        System.out.println("\nTesting MyQueueUsingStacks:");
        MyQueueUsingStacks queueUsingStacks = new MyQueueUsingStacks(5);
        System.out.println("MyQueueUsingStacks created with max size 5.");
        System.out.println("Enqueue elements 10, 20, 30 onto the queue.");
        queueUsingStacks.enqueue(10);
        queueUsingStacks.enqueue(20);
        queueUsingStacks.enqueue(30);
        System.out.println("Performing peek, dequeue and isEmpty operations:");
        System.out.println("Peek: " + queueUsingStacks.peek()); // 10
        System.out.println("Dequeue: " + queueUsingStacks.dequeue()); // 10
        System.out.println("Is Empty: " + queueUsingStacks.isEmpty()); // false


        // Valid Parentheses        
        System.out.println("\nTesting Valid Parentheses:");
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        System.out.println("Is Valid (\"()\"): " + isValidParentheses(s1));
        System.out.println("Is Valid (\"()[]{}\"): " + isValidParentheses(s2));
        System.out.println("Is Valid (\"(]\"): " + isValidParentheses(s3));

        // Test MinStack
        System.out.println("\nTesting MinStack:");
        MinStack minStack = new MinStack();
        System.out.println("Push elements 3, 5, 2, 1 onto the MinStack.");
        System.out.println("Pushing elements 3 and 5 onto the stack. in this order");
        minStack.push(3);   
        minStack.push(5);
        System.out.println("Current Min: " + minStack.getMin()); // 3
        System.out.println("Pushing elements 2 and 1 onto the stack. in this order");
        minStack.push(2);
        minStack.push(1);
        System.out.println("Current Min: " + minStack.getMin()); // 1
        System.out.println("Top element: " + minStack.top());    // 1
        System.out.println("Popping top element.");
        minStack.pop();
        System.out.println("Current Min: " + minStack.getMin()); // 2
        System.out.println("Top element: " + minStack.top());    // 2


        // Remove Outer Parentheses
        System.out.println("\nTesting Remove Outer Parentheses:");
        String s4 = "(()())(())";
        String s5 = "(()())(())(()(()))";
        System.out.println("Original: " + s4 + " | After Removal (Stack): " + removeOuterParenthesesUsingStack(s4));
        System.out.println("Original: " + s5 + " | After Removal (Level): " + removeOuterParenthesesUsingLevel(s5));

        // Remove Outer Parentheses using Level Tracking
        System.out.println("\nTesting Remove Outer Parentheses using Level Tracking:");
        System.out.println("Original: " + s4 + " | After Removal (Level): " + removeOuterParenthesesUsingLevel(s4));
        System.out.println("Original: " + s5 + " | After Removal (Level): " + removeOuterParenthesesUsingLevel(s5));

        // Evaluate Reverse Polish Notation
        System.out.println("\nTesting Evaluate Reverse Polish Notation:");
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println("Tokens: [\"2\", \"1\", \"+\", \"3\", \"*\"] | Evaluated Result: " + evalRPN(tokens)); // 9
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println("Tokens: [\"4\", \"13\", \"5\", \"/\", \"+\"] | Evaluated Result: " + evalRPN(tokens2)); // 6
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println("Tokens: [\"10\", \"6\", \"9\", \"3\", \"+\", \"-11\", \"*\", \"/\", \"*\", \"17\", \"+\", \"5\", \"+\"] | Evaluated Result: " + evalRPN(tokens3)); // 22


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
    // Complexity Analysis:
    // Time Complexity: O(n), where n is the length of the string s.
    // Space Complexity: O(n) in the worst case, when all characters are opening brackets.

    // Remove outer parentheses using stack
    public static String removeOuterParenthesesUsingStack(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
                if (stack.size() > 1) ans.append('(');
            } else {
                if (stack.size() > 1) ans.append(')');
                stack.pop();
            }
        }
        return ans.toString();
    }


    // Remove outer parentheses using level tracking
    public static String removeOuterParenthesesUsingLevel(String s) {
        int level = -1;
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                ++level;
                if(level != 0) ans.append(s.charAt(i));
            } else {
                if(level != 0) ans.append(s.charAt(i));
                --level;
            }
        }
        return ans.toString();
    }
    // Complexity Analysis:
    // Time Complexity: O(n), where n is the length of the string s.
    // Space Complexity: O(n) in the worst case, when all characters are opening brackets.


    // Evaluate Reverse Polish Notation
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Map<String, BiFunction<Integer, Integer, Integer>> map = new HashMap<>();
        map.put("+", (x, y) -> y + x);
        map.put("-", (x, y) -> y - x);
        map.put("*", (x, y) -> y * x);
        map.put("/", (x, y) -> y / x);
        for (int i = 0; i < tokens.length; i++) {
            if (map.containsKey(tokens[i])) {
                int a = stack.pop();
                int b = stack.pop();
                int ans = map.get(tokens[i]).apply(a, b);
                stack.push(ans);
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
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
class MyStack {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public MyStack(int size) {
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
class MyQueue {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    public MyQueue(int size) {
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
    private MyQueue queue;

    public MyStackUsingSingleQueue(int size) {
        this.queue = new MyQueue(size);
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
    private MyQueue queue1;
    private MyQueue queue2;

    public MyStackUsingTwoQueues(int size) {
        this.queue1 = new MyQueue(size);
        this.queue2 = new MyQueue(size);
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
        MyQueue temp = queue1;
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
        MyQueue temp = queue1;
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
 * Queue implementation using two MyStack
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
    private MyStack stackNewest;
    private MyStack stackOldest;

    public MyQueueUsingStacks(int size) {
        this.stackNewest = new MyStack(size);
        this.stackOldest = new MyStack(size);
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


class MinStack {
    private List<int[]> s;
    public MinStack() {
        this.s = new ArrayList<>();
    }
    
    public void push(int val) {
        if (this.s.isEmpty()) {
            this.s.add(new int[]{val, val});
        } else {
            int minVal = Math.min(val, this.s.get(this.s.size() - 1)[1]);
            this.s.add(new int[]{val, minVal});
        }
    }
    
    public void pop() {
        this.s.remove(this.s.size() - 1);
    }
    
    public int top() {
        return this.s.get(this.s.size() - 1)[0];
    }
    
    public int getMin() {
        return this.s.get(this.s.size() - 1)[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */