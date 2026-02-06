package javaprograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
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
        MyStackUsingTwoQueue stackTwoQueues = new MyStackUsingTwoQueue(5);
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
        MyQueueUsingStack queueUsingStacks = new MyQueueUsingStack(5);
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

        // Next Greater Element I
        System.out.println("\nTesting Next Greater Element I:");
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] result = nextGreaterElement(nums1, nums2);
        System.out.print("Nums1: [4, 1, 2], Nums2: [1, 3, 4, 2] | Next Greater Elements: ");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) {
                System.out.print(", ");
            }
        }

        // Daily Temperatures using Monotonic Stack
        System.out.println("\n\nTesting Daily Temperatures using Monotonic Stack:");
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] days = dailyTemperaturesMonotonicStack(temperatures);
        System.out.print("Temperatures: [73, 74, 75, 71, 69, 72, 76, 73] | Days until warmer temperature: ");
        for (int i = 0; i < days.length; i++) {
            System.out.print(days[i]);
            if (i < days.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        // Daily Temperatures using Pre-initialized Stack
        System.out.println("\nTesting Daily Temperatures using Pre-initialized Stack:");
        int[] daysPreInit = dailyTemperaturesWithPreInitStack(temperatures);
        System.out.print("Temperatures: [73, 74, 75, 71, 69, 72, 76, 73] | Days until warmer temperature: ");
        for (int i = 0; i < daysPreInit.length; i++) {
            System.out.print(daysPreInit[i]);
            if (i < daysPreInit.length - 1) {
                System.out.print(", ");
            }
        }   
        System.out.println();

        // Next Greater Element II using Stack with Modulo Indexing
        System.out.println("\nTesting Next Greater Element II using Stack with Modulo Indexing:");
        int[] numsNGE2 = {1, 2, 1};
        int[] resultNGE2 = nextGreaterElementsUsingModulo(numsNGE2);
        System.out.print("Nums: [1, 2, 1] | Next Greater Elements: ");
        for (int i = 0; i < resultNGE2.length; i++) {
            System.out.print(resultNGE2[i]);
            if (i < resultNGE2.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();
        // Rotting Oranges
        System.out.println("\nTesting Rotting Oranges:");
        int[][] grid = {
            {2,1,1},
            {1,1,0},
            {0,1,1}
        };
        System.out.println("Before rotting:");
        for (int[] grid1 : grid) {
            System.out.println(Arrays.toString(grid1));
        }
        int minutes = orangesRotting(grid);
        System.out.println("After rotting: Grid state:");
        for (int[] grid1 : grid) {
            System.out.println(Arrays.toString(grid1));
        }
        System.out.println("Minutes until all oranges rot: " + minutes);

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
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c != map.get(top)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    // Complexity Analysis:
    // Time Complexity: O(n), where n is the length of the string s. for each character, we perform a constant time operation.
    // Space Complexity: O(n) in the worst case, when all characters are opening brackets.
    // Stack implementation using a single queue
    // Explanation: We use a single queue to simulate stack behavior. When pushing an element, we enqueue it and then rotate the queue to place the new element at the front. This ensures LIFO order.

    // Remove outer parentheses using stack
    public static String removeOuterParenthesesUsingStack(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
                if (stack.size() > 1) {
                    ans.append('(');
                }
            } else {
                if (stack.size() > 1) {
                    ans.append(')');
                }
                stack.pop();
            }
        }
        return ans.toString();
    }
    // Complexity Analysis:
    // Time Complexity: O(n), where n is the length of the string s.
    // Space Complexity: O(n) in the worst case, when all characters are opening brackets.
    // Explanation: We use a stack to track the depth of parentheses. When we encounter an opening parenthesis, we push it onto the stack. 
    // If the stack size is greater than 1, it means we are inside an outer parenthesis, so we append it to the result. For closing parentheses, we only append them if the stack size is greater than 1 before popping.

    // Remove outer parentheses using level tracking
    public static String removeOuterParenthesesUsingLevel(String s) {
        int level = -1;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                ++level;
                if (level != 0) {
                    ans.append(s.charAt(i));
                }
            } else {
                if (level != 0) {
                    ans.append(s.charAt(i));
                }
                --level;
            }
        }
        return ans.toString();
    }
    // Complexity Analysis:
    // Time Complexity: O(n), where n is the length of the string s.
    // Space Complexity: O(n) in the worst case, when all characters are opening brackets.
    // Explanation: We use a level counter to track the depth of parentheses. When we encounter an opening parenthesis, we increment the level. 
    // If the level is not zero, it means we are inside an outer parenthesis, so we append it to the result. For closing parentheses, we only append them if the level is not zero before decrementing.

    // Evaluate Reverse Polish Notation
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Map<String, BiFunction<Integer, Integer, Integer>> map = new HashMap<>();
        map.put("+", (x, y) -> y + x);
        map.put("-", (x, y) -> y - x);
        map.put("*", (x, y) -> y * x);
        map.put("/", (x, y) -> y / x);
        for (String token : tokens) {
            if (map.containsKey(token)) {
                int a = stack.pop();
                int b = stack.pop();
                int ans = map.get(token).apply(a, b);
                stack.push(ans);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        /*
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
        */
        return stack.pop();
    }
    // Complexity Analysis:
    // Time Complexity: O(n), where n is the number of tokens.
    // Space Complexity: O(n) in the worst case, when all tokens are numbers.
    // Explanation: We use a stack to evaluate the RPN expression. For each token, if it's an operator, we pop the top two numbers from the stack, apply the operation, and push the result back onto the stack.
    // If it's a number, we simply push it onto the stack. At the end, the stack contains the final result.

    // Next Greater Element I
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> ngeMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        int n = nums2.length;
        stack.push(nums2[n - 1]); // Explanation: Start by pushing the last element onto the stack to initialize it.
        ngeMap.put(nums2[n - 1], -1); // The last element has no next greater element

        for (int i = n - 2; i >= 0; i--) {
            while (!stack.isEmpty()) {
                if (stack.peek() < nums2[i]) {
                    stack.pop();
                } else {
                    ngeMap.put(nums2[i], stack.peek());
                    break;
                }
            }
            if (stack.isEmpty()) {
                ngeMap.put(nums2[i], -1);
            }
            stack.push(nums2[i]);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = ngeMap.get(nums1[i]);
        }

        return ans;
    }
    // Complexity Analysis:
    // Time Complexity: O(m + n), where m is the length of nums1 and n is the length of nums2.
    // Space Complexity: O(n) for the ngeMap and stack.
    // Explanation: We use a stack to keep track of the next greater elements in nums2. We iterate through nums2 from right to left, and for each element, we pop elements from the stack until we find a greater element or the stack becomes empty.
    // We store the next greater element in a map. Finally, we build the result for nums1 using this map.

    // Daily Temperatures
    // Canonical / self-contained monotonic stack || dailyTemperaturesRightToLeft()
    public static int[] dailyTemperaturesMonotonicStack(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) { // Pop until we find a warmer temperature
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i] = stack.peek() - i;
            } else {
                result[i] = 0;
            }
            stack.push(i);
        }
        return result;
    }
    // Complexity Analysis:
    // Time Complexity: O(n), where n is the length of the temperatures array. Eachelement is pushed and popped at most once.
    // Space Complexity: O(n) in the worst case, when the stack contains all indices.
    // Explanation: We use a monotonic stack to keep track of indices of temperatures. We iterate through the temperatures array from right to left. For each temperature, we pop elements from the stack until we find a warmer temperature

    // Daily Temperatures
    // Pre-initialized stack + break-based popping || dailyTemperaturesRightToLeftWithPreInit()
    public static int[] dailyTemperaturesWithPreInitStack(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(n-1); // Explanation: Start by pushing the last index onto the stack to initialize it.
        for(int i = n-2; i>=0; i--){
            while(!stack.isEmpty()){
                int top = stack.peek();
                if(temperatures[i]>=temperatures[top]){
                    stack.pop();
                } else{
                    ans[i] = top - i;
                    break;
                }
            }
            stack.push(i);
        }
        return ans;
    }
    // Complexity Analysis:
    // Time Complexity: O(n), where n is the length of the temperatures array. Each element is pushed and popped at most once.
    // Space Complexity: O(n) in the worst case, when the stack contains all indices.
    // Explanation: Similar to the previous method, but we pre-initialize the stack with the last index. This avoids checking for an empty stack in each iteration, slightly optimizing the process.


    // Next Greater Element II using Stack of Doubled Array
    public static int[] nextGreaterElementsUsingDoubledArray(int[] nums) {
        int[] arr = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
            arr[i + nums.length] = nums[i];
        }
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                if (stack.peek() <= arr[i]) {
                    stack.pop();
                } else {
                    ans[i] = stack.peek();
                    break;
                }
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            }
            stack.push(arr[i]);
        }
        return java.util.Arrays.copyOfRange(ans, 0, n / 2);
    }
    // Complexity Analysis:
    // Time Complexity: O(n) Each element is pushed to and popped from the stack at most once, where n is the length of the doubled array (2 * original length).
    // Space Complexity: O(n) Stack can hold up to n elements in the worst case (monotonic decreasing array), where n is the length of the doubled array (2 * original length).
    // Explanation:
    // We use a stack to keep track of the next greater elements in a circular manner. We simulate the circular nature by doubling the array. 
    // We iterate from right to left, maintaining a mapping of each element to its next greater element. 
    // For each element, we pop elements from the stack until we find a greater element or the stack becomes empty. We then store the next greater element in the answer array.
    // Finally, we return the first half of the answer array, which corresponds to the original array.



    // Next Greater Element II using Stack with Modulo Indexing
    public static int[] nextGreaterElementsUsingModulo(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        Arrays.fill(ans, -1);
        stack.push(nums[n - 1]); // Explanation: Start by pushing the last element onto the stack to initialize it.
        for(int i = 2 * n - 2; i >= 0; i--) { // Explanation: Iterate from the second last element of the doubled array down to the first element.
                while(!stack.isEmpty()) {
                    int top = stack.peek();
                    if(nums[i % n] < top) {
                        ans[i % n] = top;
                        break; // Found the next greater element if we don't break here, we would pop the next element and potentially overwrite the answer
                    } else {
                        stack.pop();
                    }
                }
            stack.push(nums[i % n]);
        }
        return ans;
    }
    // Complexity Analysis:
    // Time Complexity: O(n), where n is the length of the string s. for each character, we perform a constant time operation.
    // Space Complexity: O(n) in the worst case, when all characters are opening brackets.
    // Explanation:
    // We use a stack to keep track of the next greater elements in a circular manner. We simulate the circular nature by iterating through the array twice using modulo indexing.
    // We iterate from right to left, maintaining a mapping of each element to its next greater element. For each element, we pop elements from the stack until we find a greater element or the stack becomes empty.
    // We then store the next greater element in the answer array. Finally, we return the answer array, which corresponds to the original array.

    // Rotting Oranges
    public static int orangesRotting(int[][] grid) {
        int m = grid.length; // number of rows
        int n = grid[0].length; // number of columns
        Queue<int[]> queue = new LinkedList<>();
        // Enqueue all initially rotten oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j, 0});
                }
            }
        }
        int maxMinutes = 0; // Track the maximum time taken to rot all oranges
        while (!queue.isEmpty()) {
            int[] curr = queue.poll(); // {x, y, level}
            // Process the current rotten orange
            int x = curr[0], y = curr[1], level = curr[2];
            // x->row, y->column, level->minutes
            // Check all four adjacent cells

            if (x > 0 && grid[x - 1][y] == 1) { // Up
                grid[x - 1][y] = 2;
                queue.add(new int[]{x - 1, y, level + 1});
            }
            if (x < m - 1 && grid[x + 1][y] == 1) { // Down
                grid[x + 1][y] = 2;
                queue.add(new int[]{x + 1, y, level + 1});
            }
            if (y < n - 1 && grid[x][y + 1] == 1) { // Right
                grid[x][y + 1] = 2;
                queue.add(new int[]{x, y + 1, level + 1});
            }
            if (y > 0 && grid[x][y - 1] == 1) { // Left
                grid[x][y - 1] = 2;
                queue.add(new int[]{x, y - 1, level + 1});
            }
            maxMinutes = Math.max(level, maxMinutes);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }
        
        return maxMinutes;
    }
    // Complexity Analysis:
    // Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the grid. Each cell is processed at most once.
    // Space Complexity: O(m * n) in the worst case, when all cells are rotten oranges and added to the queue.
    // Explanation: We use a queue to perform a breadth-first search (BFS) starting from all initially rotten oranges. We enqueue their positions and the time level (minutes).
    // For each rotten orange, we check its four adjacent cells. If any of them contain a fresh orange, we rot it and enqueue its position with an incremented time level.
    // We keep track of the maximum time level reached during the BFS. After processing, we check if any fresh oranges remain; if so, we return -1. Otherwise, we return the maximum time level as the result.

}

/**
 * Basic Stack implementation using an array Follows LIFO (Last In First Out)
 * principle
 *
 * Time Complexity: - push(): O(1) - pop(): O(1) - peek(): O(1) - isEmpty():
 * O(1)
 *
 * Space Complexity: O(n) where n is maxSize
 */
class MyStack {

    private final int maxSize;
    private final int[] stackArray;
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
 * Basic Queue implementation using a circular array Follows FIFO (First In
 * First Out) principle
 *
 * Time Complexity: - enqueue(): O(1) - dequeue(): O(1) - isEmpty(): O(1)
 *
 * Space Complexity: O(n) where n is maxSize
 */
class MyQueue {

    private final int maxSize;
    private final int[] queueArray;
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
 * Stack implementation using a single queue Maintains LIFO order by rotating
 * elements on push
 *
 * Time Complexity: - push(): O(n) - rotates all existing elements - pop(): O(1)
 * - simply dequeues front - top(): O(1) - peeks at front - isEmpty(): O(1)
 *
 * Space Complexity: O(n) where n is number of elements
 *
 * Explanation: The key insight is to make push expensive instead of pop. When
 * we push a new element, we: 1. Enqueue the new element 2. Rotate all previous
 * elements by dequeuing and re-enqueuing them This ensures the newest element
 * is always at the front
 */
class MyStackUsingSingleQueue {

    private final MyQueue queue;

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
 * Stack implementation using two queues Maintains LIFO order by transferring
 * elements between queues
 *
 * Time Complexity: - push(): O(1) - simply enqueues to queue1 - pop(): O(n) -
 * transfers n-1 elements to queue2 - top(): O(n) - transfers n-1 elements,
 * peeks, then transfers back - isEmpty(): O(1)
 *
 * Space Complexity: O(n) where n is number of elements
 *
 * Explanation: We use two queues to simulate stack behavior: 1. For push:
 * simply enqueue to queue1 2. For pop: move all but last element to queue2,
 * dequeue last, swap queues 3. For top: same as pop but don't remove the
 * element This ensures LIFO order using only queue operations
 */
class MyStackUsingTwoQueue {

    private MyQueue queue1;
    private MyQueue queue2;

    public MyStackUsingTwoQueue(int size) {
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
 * Queue implementation using two MyStack enqueue: push onto stackNewest
 * dequeue: pop from stackOldest; if empty, transfer all elements from
 * stackNewest to stackOldest first
 *
 * Time Complexity: - enqueue(): O(1) - dequeue(): Amortized O(1) - isEmpty():
 * O(1)
 *
 * Space Complexity: O(n)
 */
class MyQueueUsingStack{

    private final MyStack stackNewest;
    private final MyStack stackOldest;

    public MyQueueUsingStack(int size) {
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

    private final List<int[]> s;

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
