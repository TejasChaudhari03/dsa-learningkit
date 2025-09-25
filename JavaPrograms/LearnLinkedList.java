package JavaPrograms;
class MyLinkedList {

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
    
    public MyLinkedList() {
        this.head=null;
        this.size=0;
    }
    
    public int get(int index) {
        if(index<0 || index >=this.size) return -1;
        Node curr = head;
        for(int i=0 ; i<index;i++){
                curr =curr.next;
        }
        return curr.val;
    }
    
    public void addAtHead(int val) {
        Node newNode = new Node(val);

        newNode.next = this.head;
        head = newNode;
        size++;        
    }
    
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

    public Node middleNode(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
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
        // Reverse the linked list
        MyLinkedList.Node prev = null;
        MyLinkedList.Node curr = head2;
        while(curr != null) {
            MyLinkedList.Node nextTemp = curr.next; // Store next node
            curr.next = prev;                       // Reverse the link
            prev = curr;                            // Move prev to current
            curr = nextTemp;                       // Move to next node
        }

        // Print the reversed linked list
        MyLinkedList.Node newHead = prev; // New head of the reversed list
        System.out.print("Reversed Linked List: ");
        while(newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
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
