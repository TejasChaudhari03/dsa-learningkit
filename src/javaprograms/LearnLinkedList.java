package javaprograms;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


class MyLinkedList {

    // Definition for singly-linked list.
    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node head;
    private int size;
    
    // Initialize your data structure here.
    public MyLinkedList() {
        this.head=null;
        this.size=0;
    }
    
    // Get the value of the index-th node in the linked list. If the index is invalid, return -1.
    public int get(int index) {
        if(index<0 || index >=this.size) return -1;
        Node curr = head;
        for(int i=0 ; i<index;i++){
                curr =curr.next;
        }
        return curr.val;
    }
    
    // Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
    public void addAtHead(int val) {
        Node newNode = new Node(val);

        newNode.next = this.head;
        head = newNode;
        size++;        
    }
    

    // Append a node of value val to the last element of the linked list.
    public void addAtTail(int val) {
        Node newNode = new Node(val);
        if(head==null) head=newNode;
        else{
        Node curr = head;
        while(curr.next!=null){
            curr =curr.next;
        }
        curr.next = newNode;
        }
        size++;        
    }
    
    // Add a node of value val before the index-th node in the linked list.
    // If index equals to the length of linked list, the node will be appended to the end of linked list.
    // If index is greater than the length, the node will not be inserted.
    public void addAtIndex(int index, int val) {
        if(index<0 || index >size) return ;
        Node newNode = new Node(val);
        Node curr = this.head;

        if(index == 0){
            this.addAtHead(val);
            return;
        }
        else if(index==size){
            addAtTail(val);
            return;
        }
        else{
            for(int i=0 ; i<index-1;i++){
                curr =curr.next;
            }
            newNode.next= curr.next;
            curr.next = newNode;
        }
        size++;        
    }
    
    // Delete the index-th node in the linked list, if the index is valid.
    public void deleteAtIndex(int index) {
        if(index<0 || index >=size) return ;
            
        if(index==0){
            head=head.next;
        }
        else{
                Node curr=head;
                for(int i=0 ; i<index-1;i++){
                    curr =curr.next;
                }
                curr.next=curr.next.next;
        }

        size--;        
    }

    // Find the middle node of the linked list
    // If there are two middle nodes, return the second middle node.
    public Node middleNode(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.

    // Reverse a linked list
    public Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;

        while(curr != null) {
            Node nextTemp = curr.next; // Store next node
            curr.next = prev;          // Reverse the link
            prev = curr;               // Move prev to current
            curr = nextTemp;           // Move to next node
        }

        return prev; // New head of the reversed list
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.

    // Linked list cycle detection using Floyd's Tortoise and Hare algorithm
    public boolean hasCycle(Node head) {
        if (head == null || head.next == null) return false;  // Handle edge cases: empty list or single node can't have cycles
        Node slow = head;                                     // Initialize slow pointer at head
        Node fast = head;                                     // Initialize fast pointer at head
        while (fast != null && fast.next != null) {          // Loop until fast pointer reaches end (null) or second-to-last node
            slow = slow.next;                                 // Move slow pointer one step
            fast = fast.next.next;                           // Move fast pointer two steps
            if (slow == fast) return true;                   // If pointers meet, we found a cycle
        }
        return false;                                        // If we exit loop, no cycle exists
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.

    // Linked list cycle detection using a Set
    public boolean hasCycleUsingSet(Node head) {
        Set<Node> visited = new HashSet<>();
        Node curr = head;
        while (curr != null) {
            if (visited.contains(curr)) {
                return true; // Cycle detected
            }
            visited.add(curr);
            curr = curr.next;
        }
        return false; // No cycle
    }
    // Time complexity: O(n)
    // Space complexity: O(n)
    // Explanation : Time complexity is O(n) because we may need to traverse the entire list in the worst case. Space complexity is O(n) because we store each node in a set.

    // Palindrome Linked List comparing first half reversed with second half
    public boolean isPalindrome(Node head) {
        // Find middle using slow/fast pointer
        Node slow = head; // Slow pointer will reach the middle
        Node fast = head; // Fast pointer will reach the end
        Node prev = null; // To reverse the first half of the list

        while (fast != null && fast.next != null) {
            fast = fast.next.next; // Move fast pointer two steps
            Node next = slow.next; // Store next node
            slow.next = prev; // Reverse the link for the first half
            prev = slow; // Move prev to current slow
            slow = next; // Move slow to next node
        }

        // If odd length, skip middle element
        Node second = fast == null ? slow : slow.next; // Determine the start of the second half
        Node first = prev; // First half is now reversed

        // Compare reversed first half with second half
        while (first != null && second != null) {
            if (first.val != second.val) return false; // If values differ, it's not a palindrome
            first = first.next; // Move to next node in first half
            second = second.next; // Move to next node in second half
        }
        return true; // If all values matched, it's a palindrome
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list a constant number of times. Space complexity is O(1) because we use a constant amount of extra space.

    // Palindrome Linked List using two-pass approach
    public boolean isPalindromeTwoPass(Node head) {
        Node slow = head, fast = head; // Initialize slow and fast pointers to find the middle of the list
        while (fast != null && fast.next != null) { // Traverse the list with fast pointer moving twice as fast as slow
            slow = slow.next; // Move slow pointer one step
            fast = fast.next.next; // Move fast pointer two steps
        }
        Node prev = null; // Initialize a pointer to reverse the second half of the list
        Node curr = slow; // Start reversing from the middle of the list
        while (curr != null) { // Reverse the second half of the list
            Node next = curr.next; // Store the next node
            curr.next = prev; // Reverse the link
            prev = curr; // Move prev to current node
            curr = next; // Move to the next node
        }
        Node left = head; // Pointer for the first half of the list
        Node right = prev; // Pointer for the reversed second half of the list
        while (right != null) { // Compare the two halves
            if (left.val != right.val) return false; // If values differ, it's not a palindrome
            left = left.next; // Move to the next node in the first half
            right = right.next; // Move to the next node in the reversed second half
        }
        return true; // If all values matched, it's a palindrome
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list a constant number of times. Space complexity is O(1) because we use a constant amount of extra space.

    // Palindrome Linked List using array
    public boolean isPalindromeArray(Node head) {
        List<Integer> values = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            values.add(curr.val);
            curr = curr.next;
        }
        int left = 0;
        int right = values.size() - 1;
        while (left < right) {
            if (!values.get(left).equals(values.get(right))) return false;
            left++;
            right--;
        }
        return true;
    }
    // Time complexity: O(n)
    // Space complexity: O(n)
    // Explanation : Time complexity is O(n) because we traverse the list once. Space complexity is O(n) because we use an array to store the values of the nodes.


    // Intersection of Two Linked Lists using two-pointer technique
    public Node getIntersectionNode(Node headA, Node headB) {
        // If either head is null, there can't be an intersection
        if (headA == null || headB == null) return null;

        Node ptrA = headA; // Pointer for list A
        Node ptrB = headB; // Pointer for list B

        // Traverse both lists; when one pointer reaches the end, switch to the other list
        while (ptrA != ptrB) {
            // If ptrA reaches the end, switch to headB; otherwise, move to the next node
            ptrA = (ptrA == null) ? headB : ptrA.next;
            // If ptrB reaches the end, switch to headA; otherwise, move to the next node
            ptrB = (ptrB == null) ? headA : ptrB.next;
        }
        // When ptrA and ptrB meet, return the intersection node (or null if no intersection)
        return ptrA; // or ptrB, they are equal
    }
    // Time complexity: O(m + n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(m + n) because in the worst case, each pointer traverses both lists once. Space complexity is O(1) because we use a constant amount of extra space.

    // Intersection of Two Linked Lists using Set
    public Node getIntersectionNodeUsingSet(Node headA, Node headB) {
        Set<Node> nodesInB = new HashSet<>();
        Node currB = headB;
        while (currB != null) {
            nodesInB.add(currB);
            currB = currB.next;
        }
        Node currA = headA;
        while (currA != null) {
            if (nodesInB.contains(currA)) {
                return currA; // Intersection node
            }
            currA = currA.next;
        }
        return null; // No intersection
    }
    // Time complexity: O(m + n)
    // Space complexity: O(m) or O(n) depending on which list is stored in the set.
    // Explanation : Time complexity is O(m + n) because we traverse both lists once. Space complexity is O(m) or O(n) because we store one of the lists in a set.

    // Remove linked list elements
    public Node removeElements(Node head, int val) {
        Node sentinel = new Node(0);
        sentinel.next = head;
        Node current = sentinel;
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next; // Skip the node with the target value
            } else {
                current = current.next; // Move to the next node
            }
        }
        return sentinel.next; // Return the new head, which is next of sentinel node
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list once. Space complexity is O(1) because we use a constant amount of extra space.


    // Remove nth node from end of list using two passes
    // Remove nth node from end of list using two passes
    public Node removeNthFromEndTwoPass(Node head, int n) {
        Node sentinel = new Node(0); // Create a sentinel node to simplify edge cases
        sentinel.next = head; // Link the sentinel to the head of the list
        int length = 0; // Initialize length counter
        Node curr = head; // Start from the head to calculate the length
        while (curr != null) { // Traverse the list to find its length
            curr = curr.next; // Move to the next node
            length++; // Increment the length counter
        }
        int prevPos = length - n; // Calculate the position of the node before the one to be removed
        Node prev = sentinel; // Start from the sentinel to find the previous node
        for (int i = 0; i < prevPos; i++) { // Traverse to the node just before the target node
            prev = prev.next; // Move to the next node
        }
        prev.next = prev.next.next; // Bypass the target node to remove it from the list
        return sentinel.next; // Return the new head of the list, which is next of sentinel
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list twice. Space complexity is O(1) because we use a constant amount of extra space.

    // Remove nth node from end of list using one pass
    public Node removeNthFromEndOnePass(Node head, int n) {
        Node sentinel = new Node(0); // Create a sentinel node to simplify edge cases
        sentinel.next = head; // Link the sentinel to the head of the list
        Node first = sentinel; // Pointer to traverse the list
        Node second = sentinel; // Pointer to find the node to remove
        
        // Move the first pointer n+1 steps ahead to maintain the gap
        for (int i = 0; i <= n; i++) {
            first = first.next; // Advance the first pointer
        }
        
        // Move both pointers until the first pointer reaches the end
        while (first != null) {
            first = first.next; // Advance the first pointer
            second = second.next; // Advance the second pointer
        }
        
        // Skip the node to be removed
        second.next = second.next.next; // Bypass the target node
        return sentinel.next; // Return the new head of the list
    }
    // Time complexity: O(n) because we traverse the list once.
    // Space complexity: O(1) because we use a constant amount of extra space.

    // Remove duplicates from sorted list
    public Node deleteDuplicates(Node head) {
        Node current = head; // Pointer to traverse the list
        // Traverse the list while checking for duplicates
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next; // Skip duplicate
            } else {
                current = current.next; // Move to the next node
            }
        }
        return head; // Return the head of the modified list
    }
    // Time complexity: O(n) because we traverse the list once.
    // Space complexity: O(1) because we use a constant amount of extra space.

    // Odd Even Linked List
    public Node oddEvenList(Node head) {
        if (head == null) return null; // Handle empty list case
        Node odd = head; // Pointer for odd indexed nodes
        Node even = head.next; // Pointer for even indexed nodes
        Node evenHead = even; // Store the head of the even indexed nodes

        // Rearrange the nodes by skipping even indexed nodes
        while (even != null && even.next != null) {
            odd.next = odd.next.next; // Link odd nodes
            even.next = even.next.next; // Link even nodes
            odd = odd.next; // Move to the next odd node
            even = even.next; // Move to the next even node
        }
        odd.next = evenHead; // Link the end of odd indexed nodes to the head of even indexed nodes
        return head; // Return the head of the modified list
    }
    // Time complexity: O(n) because we traverse the list once.
    // Space complexity: O(1) because we use a constant amount of extra space.

    // Add two numbers represented by linked lists
    public Node addTwoNumbers(Node l1, Node l2) {
        Node ans = new Node(0); // Create a new node for the result
        Node ansHead = ans; // Store the head of the result list
        int carry = 0; // Initialize carry for sum overflow
        
        // Traverse both lists until both are exhausted and no carry remains
        while (l1 != null || l2 != null || carry != 0) {
            // Calculate the sum of the current digits and carry
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = sum / 10; // Update carry for the next iteration
            int digit = sum % 10; // Get the last digit of the sum
            
            Node newNode = new Node(digit); // Create a new node for the digit
            ans.next = newNode; // Link the new node to the result list
            ans = ans.next; // Move to the next position in the result list
            
            // Move to the next nodes in the input lists
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }
        return ansHead.next; // Return the head of the result list, skipping the dummy node
    }
    // Time complexity: O(max(m, n)) because we traverse the longer list once.
    // Space complexity: O(max(m, n)) because we create a new list to store the result.

    // Merge two sorted linked lists without dummy node
    public Node mergeTwoLists(Node l1, Node l2) {
        if(l1 == null) return l2; // If list 1 is empty, return list 2
        if(l2 == null) return l1; // If list 2 is empty, return list 1

        Node curr; // Pointer to build the merged list
        // Initialize the head of the merged list based on the smaller value
        if(l1.val < l2.val) {
            curr = l1; // Start with list 1
            l1 = l1.next; // Move to the next node in list 1
        } else {
            curr = l2; // Start with list 2
            l2 = l2.next; // Move to the next node in list 2
        }
        Node mergedHead = curr; // Store the head of the merged list
        // Merge the two lists by comparing values
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1; // Link the smaller node
                l1 = l1.next; // Move to the next node in list 1
            } else {
                curr.next = l2; // Link the smaller node
                l2 = l2.next; // Move to the next node in list 2
            }
            curr = curr.next; // Move to the next position in the merged list
        }
        // Link any remaining nodes from either list
        if(l1 == null) curr.next = l2; // If list 1 is exhausted, link list 2
        if(l2 == null) curr.next = l1; // If list 2 is exhausted, link list 1
        return mergedHead; // Return the head of the merged list
    }
    // Time complexity: O(m + n) because we traverse both lists once.
    // Space complexity: O(1) because we use a constant amount of extra space.

    // Merge two sorted linked lists with dummy node
    public Node mergeTwoListsWithDummy(Node l1, Node l2) {
        Node dummy = new Node(0); // Create a dummy node to simplify merging
        Node curr = dummy; // Pointer to build the merged list
        // Merge the two lists by comparing values
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1; // Link the smaller node
                l1 = l1.next; // Move to the next node in list 1
            } else {
                curr.next = l2; // Link the smaller node
                l2 = l2.next; // Move to the next node in list 2
            }
            curr = curr.next; // Move to the next position in the merged list
        }
        // Link any remaining nodes from either list
        if (l1 == null) curr.next = l2; // If list 1 is exhausted, link list 2
        if (l2 == null) curr.next = l1; // If list 2 is exhausted, link list 1
        return dummy.next; // Return the merged list, which starts after the dummy node
    }
    // Time complexity: O(m + n) because we traverse both lists once.
    // Space complexity: O(1) because we use a constant amount of extra space.

    // Rotate List to the right by k places
    public Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) return head; // Handle edge cases

        // Compute the length of the list and get the last node
        Node curr = head; // Pointer to traverse the list
        int length = 1; // Initialize length counter
        while (curr.next != null) { // Traverse to find the last node
            curr = curr.next; // Move to the next node
            length++; // Increment the length counter
        }

        Node s = head; // Pointer to find the new head
        Node f = head; // Pointer to find the end of the list
        int kthNode = k % length; // Effective rotations needed
        for (int i = 0; i < kthNode; i++) { // Move the fast pointer k steps ahead
            f = f.next; // Advance the fast pointer
        }
        // Move both pointers until the fast pointer reaches the end
        while (f.next != null) {
            s = s.next; // Move the slow pointer
            f = f.next; // Move the fast pointer
        }

        f.next = head; // Connect the end of the list to the head
        Node newHead = s.next; // New head is the (k % length)-th node
        s.next = null; // Break the link to form the new list
        return newHead; // Return the new head of the rotated list
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list a constant number of times. Space complexity is O(1) because we use a constant amount of extra space.

    // Swap Nodes in Pairs using iterative approach
    public Node swapPairs(Node head) {
        if (head == null || head.next == null) return head;

        Node dummy = new Node(0); // Create a dummy node to simplify edge cases
        dummy.next = head; // Link the dummy to the head of the list
        Node prev = dummy; // Pointer to the node before the current pair
        Node curr = head; // Pointer to the current node

        while (curr != null && curr.next != null) { // Traverse the list in pairs until the curr or next is null
            Node next = curr.next; // Store the next node
            // Swap nodes
            curr.next = next.next; // Link current node to the node after the next
            next.next = curr; // Link next node to the current node
            prev.next = next; // Link previous node to the next node (new head of the pair)

            // Move to the next pair
            prev = curr; // Move prev to current node
            curr = curr.next; // Move curr to the next pair
        }
        return dummy.next; // Return the new head of the list
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list once. Space complexity is O(1) because we use a constant amount of extra space.

    // Swap Nodes in Pairs using recursive approach

    public Node swapPairsRecursive(Node head) {
        if (head == null || head.next == null) return head; // Base case: if the list is empty or has only one node, no swap needed

        Node first = head; // First node of the pair
        Node second = head.next; // Second node of the pair

        // Swap the first two nodes 
        first.next = swapPairsRecursive(second.next); // Recursively swap the rest of the list and link to the first node
        second.next = first; // Link the second node to the first node
        
        return second; // New head is the second node after swap
    }
    // Time complexity: O(n)
    // Space complexity: O(n) due to recursive call stack
    // Explanation : Time complexity is O(n) because we traverse the list once. Space complexity is O(n) because of the recursive call stack.
}

public class LearnLinkedList {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        // 1. Initialize the linked list.
        // 2. Add 1 at the head. The linked list is now 1
        myLinkedList.addAtHead(1);
        // 3. Add 3 at the tail. The linked list is now 1->3
        myLinkedList.addAtTail(3);
        // 4. Add 2 at index 1. The linked list is now 1->2->3
        myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
        // 5. Get the value at index 1. Should return 2
        System.out.println(myLinkedList.get(1));              // return 2
        // 6. Delete the node at index 1. The linked list is now 1->3
        myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
        System.out.println(myLinkedList.get(1));              // return 3

        // Middle of linked list
        MyLinkedList.Node head = myLinkedList.new Node(1);
        head.next = myLinkedList.new Node(2);
        head.next.next = myLinkedList.new Node(3);
        head.next.next.next = myLinkedList.new Node(4);
        head.next.next.next.next = myLinkedList.new Node(5);    
        MyLinkedList.Node middle = myLinkedList.middleNode(head);
        System.out.println("Middle Node Value: " + middle.val); // Output: 3


        // Reverse a linked list
        // Example: 1 -> 2 -> 3 -> 4 -> 5
        // Generate the linked list
        MyLinkedList.Node head2 = myLinkedList.new Node(1);
        head2.next = myLinkedList.new Node(2);
        head2.next.next = myLinkedList.new Node(3);
        head2.next.next.next = myLinkedList.new Node(4);
        head2.next.next.next.next = myLinkedList.new Node(5);
        MyLinkedList.Node reversedHead = myLinkedList.reverseList(head2);
        System.out.print("Reversed List: ");
        while (reversedHead != null) {
            System.out.print(reversedHead.val + " ");
            reversedHead = reversedHead.next;
        }

        // Linked list cycle detection using Floyd's Tortoise and Hare algorithm
        MyLinkedList.Node head3 = myLinkedList.new Node(3);
        MyLinkedList.Node second = myLinkedList.new Node(2);
        MyLinkedList.Node third = myLinkedList.new Node(1);
        head3.next = second;
        second.next = third;
        third.next = head3; // Create a cycle
        System.out.println("Has Cycle: " + myLinkedList.hasCycle(head3)); // Output: true

        // Linked list cycle detection using a Set
        MyLinkedList.Node head4 = myLinkedList.new Node(3);
        MyLinkedList.Node second2 = myLinkedList.new Node(2);
        MyLinkedList.Node third2 = myLinkedList.new Node(1);
        head4.next = second2;
        second2.next = third2;
        third2.next = head4; // Create a cycle
        System.out.println("Has Cycle (Set): " + myLinkedList.hasCycleUsingSet(head4)); // Output: true

        // Palindrome Linked List comparing first half reversed with second half
        MyLinkedList.Node head5 = myLinkedList.new Node(1);
        MyLinkedList.Node second3 = myLinkedList.new Node(2);
        MyLinkedList.Node third3 = myLinkedList.new Node(1);
        head5.next = second3;
        second3.next = third3;
        System.out.println("Is Palindrome: " + myLinkedList.isPalindrome(head5)); // Output: true
        System.out.println("Is Palindrome (Two Pass): " + myLinkedList.isPalindromeTwoPass(head5)); // Output: true
        System.out.println("Is Palindrome (Array): " + myLinkedList.isPalindromeArray(head5)); // Output: true

        // Intersection of Two Linked Lists using two-pointer technique
        MyLinkedList.Node common = myLinkedList.new Node(8);
        common.next = myLinkedList.new Node(4);
        common.next.next = myLinkedList.new Node(5);
        MyLinkedList.Node headA = myLinkedList.new Node(4);
        headA.next = myLinkedList.new Node(1);
        headA.next.next = common;
        MyLinkedList.Node headB = myLinkedList.new Node(5);
        headB.next = myLinkedList.new Node(0);
        headB.next.next = myLinkedList.new Node(1);
        headB.next.next.next = common;
        MyLinkedList.Node intersection = myLinkedList.getIntersectionNode(headA, headB);
        System.out.println("Intersection Node Value: " + (intersection != null ? intersection.val : "No Intersection")); // Output: 8
        // Intersection of Two Linked Lists using Set
        MyLinkedList.Node intersectionSet = myLinkedList.getIntersectionNodeUsingSet(headA, headB);
        System.out.println("Intersection Node Value (Set): " + (intersectionSet != null ? intersectionSet.val : "No Intersection")); // Output: 8

        // Remove linked list elements
        MyLinkedList.Node head6 = myLinkedList.new Node(1);
        MyLinkedList.Node second6 = myLinkedList.new Node(2);
        MyLinkedList.Node third6 = myLinkedList.new Node(6);
        head6.next = second6;
        second6.next = third6;
        myLinkedList.removeElements(head6, 6);  // List becomes 1 -> 2
        System.out.print("List after removing elements: ");
        MyLinkedList.Node curr = head6;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();

        // Remove nth node from end of list using two passes
        MyLinkedList.Node head7 = myLinkedList.new Node(1);
        MyLinkedList.Node second7 = myLinkedList.new Node(2);
        MyLinkedList.Node third7 = myLinkedList.new Node(3);
        head7.next = second7;
        second7.next = third7;
        myLinkedList.removeNthFromEndTwoPass(head7, 2);  // List becomes 1 -> 3
        System.out.print("List after removing nth node from end: ");
        MyLinkedList.Node curr2 = head7;
        while (curr2 != null) {
            System.out.print(curr2.val + " ");
            curr2 = curr2.next;
        }
        System.out.println();

        // Remove nth node from end of list using one pass
        MyLinkedList.Node head8 = myLinkedList.new Node(1);
        MyLinkedList.Node second8 = myLinkedList.new Node(2);
        MyLinkedList.Node third8 = myLinkedList.new Node(3);
        head8.next = second8;
        second8.next = third8;
        myLinkedList.removeNthFromEndOnePass(head8, 2);  // List becomes 1 -> 3
        System.out.print("List after removing nth node from end (one pass): ");
        MyLinkedList.Node curr3 = head8;
        while (curr3 != null) {
            System.out.print(curr3.val + " ");
            curr3 = curr3.next;
        }
        System.out.println();

        // Remove duplicates from sorted list
        MyLinkedList.Node head9 = myLinkedList.new Node(1);
        MyLinkedList.Node second9 = myLinkedList.new Node(2);
        MyLinkedList.Node third9 = myLinkedList.new Node(2);
        MyLinkedList.Node fourth9 = myLinkedList.new Node(3);
        head9.next = second9;
        second9.next = third9;
        third9.next = fourth9;
        myLinkedList.deleteDuplicates(head9);
        System.out.print("List after removing duplicates (sorted): ");
        MyLinkedList.Node curr4 = head9;
        while (curr4 != null) {
            System.out.print(curr4.val + " ");
            curr4 = curr4.next;
        }
        System.out.println();

        // Odd Even Linked List
        MyLinkedList.Node head10 = myLinkedList.new Node(1);
        MyLinkedList.Node second10 = myLinkedList.new Node(2);
        MyLinkedList.Node third10 = myLinkedList.new Node(3);
        head10.next = second10;
        second10.next = third10;
        myLinkedList.oddEvenList(head10);
        System.out.print("List after odd-even rearrangement: ");
        MyLinkedList.Node curr5 = head10;
        while (curr5 != null) {
            System.out.print(curr5.val + " ");
            curr5 = curr5.next;
        }
        System.out.println();

        // Add two numbers represented by linked lists
        MyLinkedList.Node l1 = myLinkedList.new Node(2);
        MyLinkedList.Node l2 = myLinkedList.new Node(3);
        MyLinkedList.Node l3 = myLinkedList.new Node(4);
        l1.next = l2;
        l2.next = l3;
        MyLinkedList.Node l4 = myLinkedList.new Node(5);
        MyLinkedList.Node l5 = myLinkedList.new Node(6);
        MyLinkedList.Node l6 = myLinkedList.new Node(7);
        l4.next = l5;
        l5.next = l6;
        MyLinkedList.Node result = myLinkedList.addTwoNumbers(l1, l4);
        System.out.print("Sum list: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();

        // Merge two sorted linked lists without dummy node
        MyLinkedList.Node l7 = myLinkedList.new Node(1);
        MyLinkedList.Node l8 = myLinkedList.new Node(3);
        MyLinkedList.Node l9 = myLinkedList.new Node(5);
        l7.next = l8;
        l8.next = l9;
        MyLinkedList.Node l10 = myLinkedList.new Node(2);
        MyLinkedList.Node l11 = myLinkedList.new Node(4);
        MyLinkedList.Node l12 = myLinkedList.new Node(6);
        l10.next = l11;
        l11.next = l12;
        MyLinkedList.Node mergedList = myLinkedList.mergeTwoLists(l7, l10);
        System.out.print("Merged List (without dummy): ");
        while (mergedList != null) {
            System.out.print(mergedList.val + " ");
            mergedList = mergedList.next;
        }
        System.out.println();
        // Merge two sorted linked lists with dummy node
        MyLinkedList.Node l13 = myLinkedList.new Node(1);
        MyLinkedList.Node l14 = myLinkedList.new Node(3);
        MyLinkedList.Node l15 = myLinkedList.new Node(5);
        l13.next = l14;
        l14.next = l15;
        MyLinkedList.Node l16 = myLinkedList.new Node(2);
        MyLinkedList.Node l17 = myLinkedList.new Node(4);
        MyLinkedList.Node l18 = myLinkedList.new Node(6);
        l16.next = l17;
        l17.next = l18;
        MyLinkedList.Node mergedListWithDummy = myLinkedList.mergeTwoListsWithDummy(l13, l16);
        System.out.print("Merged List (with dummy): ");
        while (mergedListWithDummy != null) {
            System.out.print(mergedListWithDummy.val + " ");
            mergedListWithDummy = mergedListWithDummy.next;
        }
        System.out.println();


        // Rotate List to the right by k places
        MyLinkedList.Node head20 = myLinkedList.new Node(1);
        MyLinkedList.Node second20 = myLinkedList.new Node(2);
        MyLinkedList.Node third20 = myLinkedList.new Node(3);
        MyLinkedList.Node fourth20 = myLinkedList.new Node(4);
        MyLinkedList.Node fifth20 = myLinkedList.new Node(5);
        head20.next = second20;
        second20.next = third20;
        third20.next = fourth20;
        fourth20.next = fifth20;
        MyLinkedList.Node rotatedList = myLinkedList.rotateRight(head20, 2); // Rotate by 2 places
        System.out.print("Rotated List: ");
        while (rotatedList != null) {
            System.out.print(rotatedList.val + " ");
            rotatedList = rotatedList.next;
        }


        // Swap Nodes in Pairs using iterative approach
        MyLinkedList.Node head21 = myLinkedList.new Node(1);
        MyLinkedList.Node second21 = myLinkedList.new Node(2);
        MyLinkedList.Node third21 = myLinkedList.new Node(3);
        MyLinkedList.Node fourth21 = myLinkedList.new Node(4);
        head21.next = second21;
        second21.next = third21;
        third21.next = fourth21;
        MyLinkedList.Node swappedList = myLinkedList.swapPairs(head21);
        System.out.print("Swapped List (iterative): ");
        while (swappedList != null) {
            System.out.print(swappedList.val + " ");
            swappedList = swappedList.next;
        }
        System.out.println();

        // Swap Nodes in Pairs using recursive approach 
        MyLinkedList.Node head22 = myLinkedList.new Node(1);
        MyLinkedList.Node second22 = myLinkedList.new Node(2);
        MyLinkedList.Node third22 = myLinkedList.new Node(3);
        MyLinkedList.Node fourth22 = myLinkedList.new Node(4);
        head22.next = second22;
        second22.next = third22;
        third22.next = fourth22;
        MyLinkedList.Node swappedListRecursive = myLinkedList.swapPairsRecursive(head22);
        System.out.print("Swapped List (recursive): ");
        while (swappedListRecursive != null) {
            System.out.print(swappedListRecursive.val + " ");
            swappedListRecursive = swappedListRecursive.next;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
