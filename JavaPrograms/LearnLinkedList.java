package JavaPrograms;

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
        if (head == null || head.next == null) return false;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
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
        Node slow = head;
        Node fast = head;
        Node prev = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // If odd length, skip middle element
        Node second = fast == null ? slow : slow.next;
        Node first = prev;

        // Compare reversed first half with second half
        while (first != null && second != null) {
            if (first.val != second.val) return false;
            first = first.next;
            second = second.next;
        }
        return true;
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list a constant number of times. Space complexity is O(1) because we use a constant amount of extra space.

    // Palindrome Linked List using two-pass approach
    public boolean isPalindromeTwoPass(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node prev = null;
        Node curr = slow;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node left = head;
        Node right = prev;
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
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
        if (headA == null || headB == null) return null;
        Node ptrA = headA;
        Node ptrB = headB;
        while (ptrA != ptrB) {
            ptrA = (ptrA == null) ? headB : ptrA.next;
            ptrB = (ptrB == null) ? headA : ptrB.next;
        }
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
    public Node removeNthFromEndTwoPass(Node head, int n) {
        Node sentinel = new Node(0);
        sentinel.next = head;
        int length = 0;
        Node curr = head;
        while (curr != null) {
            curr = curr.next;
            length++;
        }
        int prevPos = length - n;
        Node prev = sentinel;
        for (int i = 0; i < prevPos; i++) {
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return sentinel.next;
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list twice. Space complexity is O(1) because we use a constant amount of extra space.

    // Remove nth node from end of list using one pass
    public Node removeNthFromEndOnePass(Node head, int n) {
        Node sentinel = new Node(0);
        sentinel.next = head;
        Node first = sentinel;
        Node second = sentinel;
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return sentinel.next;
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list once. Space complexity is O(1) because we use a constant amount of extra space.

    // Remove duplicates from sorted list
    public Node deleteDuplicates(Node head) {
        Node current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next; // Skip duplicate
            } else {
                current = current.next;
            }
        }
        return head;
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list once. Space complexity is O(1) because we use a constant amount of extra space.

    // Odd Even Linked List
    public Node oddEvenList(Node head) {
        if (head == null) return null;
        Node odd = head;
        Node even = head.next;
        Node evenHead = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list once. Space complexity is O(1) because we use a constant amount of extra space.

    // Add two numbers represented by linked lists
    public Node addTwoNumbers(Node l1, Node l2) {
        Node ans = new Node(0);
        Node ansHead = ans;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = sum / 10;
            int digit = sum % 10;
            Node newNode = new Node(digit);
            ans.next = newNode;
            ans = ans.next;
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }
        return ansHead.next;
    }
    // Time complexity: O(max(m, n)))
    // Space complexity: O(max(m, n))
    // Explanation : Time complexity is O(max(m, n)) because we traverse the longer list once. Space complexity is O(max(m, n)) because we create a new list to store the result.

    // Merge two sorted linked lists without dummy node
    public Node mergeTwoLists(Node l1, Node l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        Node curr;
        if(l1.val < l2.val) {
            curr = l1;
            l1 = l1.next;
        } else {
            curr = l2;
            l2 = l2.next;
        }
        Node head = curr;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if(l1 == null) curr.next = l2;
        if(l2 == null) curr.next = l1;
        return head;
    }
    // Time complexity: O(m + n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(m + n) because we traverse both lists once. Space complexity is O(1) because we use a constant amount of extra space.

    // Merge two sorted linked lists with dummy node
    public Node mergeTwoListsWithDummy(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 == null) curr.next = l2;
        if (l2 == null) curr.next = l1;
        return dummy.next; // Return the merged list, which starts after the dummy node
    }
    // Time complexity: O(m + n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(m + n) because we traverse both lists once. Space complexity is O(1) because we use a constant amount of extra space.

    // Rotate List to the right by k places
    public Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Compute the length of the list and get the last node
        Node curr = head;
        int length = 1;
        while (curr.next != null) {
            curr = curr.next;
            length++;
        }

        Node s = head;
        Node f = head;
        int kthNode = k % length;
        for (int i = 0; i < kthNode; i++) {
            f = f.next;
        }
        while (f.next != null) {
            s = s.next;
            f = f.next;
        }

        f.next = head; // Connect the end of the list to the head
        Node newHead = s.next; // New head is the (k % length)-th node
        s.next = null; // Break the link to form the new list
        return newHead;
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Explanation : Time complexity is O(n) because we traverse the list a constant number of times. Space complexity is O(1) because we use a constant amount of extra space.
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
