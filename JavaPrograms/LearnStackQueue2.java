package JavaPrograms;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LearnStackQueue2 {
    public static void main(String[] args) {
        MyStackUsingTwoQueues myStack = new MyStackUsingTwoQueues();
        System.out.println("Using MyStackUsingTwoQueues");
        System.out.println("Pushing elements 1 and 2 onto the stack. in this order");
        myStack.push(1);
        myStack.push(2);
        System.out.println("Performing top, pop and empty operations:");
        System.out.println(myStack.top());   // return 2
        System.out.println(myStack.pop());   // return 2
        System.out.println(myStack.empty()); // return false
    
        MyStackUsingSingleQueue myStack2 = new MyStackUsingSingleQueue();
        System.out.println("Using MyStackUsingSingleQueue");
        System.out.println("Pushing elements 1 and 2 onto the stack. in this order");
        myStack2.push(1);
        myStack2.push(2);
        System.out.println("Performing top, pop and empty operations:");
        System.out.println(myStack2.top());   // return 2
        System.out.println(myStack2.pop());   // return 2
        System.out.println(myStack2.empty()); // return false

        MyQueue myQueue = new MyQueue();
        System.out.println("Using MyQueue");
        System.out.println("Pushing elements 1 and 2 onto the queue. in this order");
        myQueue.push(1);
        myQueue.push(2);
        System.out.println("Performing peek, pop and empty operations:");
        System.out.println(myQueue.peek());   // return 1
        System.out.println(myQueue.pop());    // return 1
        System.out.println(myQueue.empty());  // return false
    }
}
class MyStackUsingTwoQueues {
    // Using two queue
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    public MyStackUsingTwoQueues() {
        
    }
    
    public void push(int x) {
        q1.offer(x); // It returns true if the element was successfully added or false if the queue is full.
    }
    
    public int pop() {
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }
        int ans = q1.poll();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return ans;
    }
    
    public int top() {
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }
        int front = q1.poll();
        q2.offer(front);
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return front;        
    }
    
    public boolean empty() {
        return q1.isEmpty();        
    }
}
class MyStackUsingSingleQueue {
    // Using single queue
    Queue<Integer> q = new LinkedList<>();

    public MyStackUsingSingleQueue() {
        
    }
    
    public void push(int x) {
        q.offer(x); // It returns true if the element was successfully added or false if the queue is full.
    }
    
    public int pop() {
        int n = q.size();
        for (int i = 0; i < n - 1; i++) {
            q.offer(q.poll());
        }
        return q.poll();
    }
    
    public int top() {
        int n = q.size();
        for (int i = 0; i < n - 1; i++) {
            q.offer(q.poll());
        }
        int front = q.poll();
        q.offer(front);
        return  front;        
    }
    
    public boolean empty() {
        return q.isEmpty();        
    }
}


/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

class MyQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public MyQueue() {
    }
    
    public void push(int x) {
       s1.push(x);       
    }
    
    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();        
    }
    
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();        
    }
    
    public boolean empty() {
       return s1.isEmpty() && s2.isEmpty();        
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */