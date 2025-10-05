function Node(val) {
  this.val = val;
  this.next = null;
}
var MyLinkedList = function () {
  this.head = null;
  this.size = 0;
};

/**
 * @param {number} index
 * @return {number}
 */
MyLinkedList.prototype.get = function (index) {
  if (index < 0 || index >= this.size) return -1;

  let curr = this.head;
  for (let i = 0; i < index; i++) {
    curr = curr.next;
  }

  return curr.val;
};

/**
 * @param {number} val
 * @return {void}
 */
MyLinkedList.prototype.addAtHead = function (val) {
  let newNode = new Node(val);

  newNode.next = this.head;
  this.head = newNode;
  this.size++;
};

/**
 * @param {number} val
 * @return {void}
 */
MyLinkedList.prototype.addAtTail = function (val) {
  let newNode = new Node(val);
  if (this.head == null) this.head = newNode;
  else {
    let curr = this.head;
    while (curr.next != null) {
      curr = curr.next;
    }
    curr.next = newNode;
  }
  this.size++;
};

/**
 * @param {number} index
 * @param {number} val
 * @return {void}
 */
MyLinkedList.prototype.addAtIndex = function (index, val) {
  if (index < 0 || index > this.size) return;
  let newNode = new Node(val);
  let curr = this.head;

  if (index == 0) {
    this.addAtHead(val);
    return;
  } else if (index === this.size) {
    this.addAtTail(val);
    return;
  } else {
    for (let i = 0; i < index - 1; i++) {
      curr = curr.next;
    }
    newNode.next = curr.next;
    curr.next = newNode;
  }
  this.size++;
};

/**
 * @param {number} index
 * @return {void}
 */
MyLinkedList.prototype.deleteAtIndex = function (index) {
  if (index < 0 || index >= this.size) return;

  if (index === 0) {
    this.head = this.head.next;
  } else {
    let curr = this.head;
    for (let i = 0; i < index - 1; i++) {
      curr = curr.next;
    }
    curr.next = curr.next.next;
  }

  this.size--;
};

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * var obj = new MyLinkedList()
 * var param_1 = obj.get(index)
 * obj.addAtHead(val)
 * obj.addAtTail(val)
 * obj.addAtIndex(index,val)
 * obj.deleteAtIndex(index)
 */

// Example usage:
var linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);
console.log(linkedList.get(1)); // returns 2
linkedList.deleteAtIndex(1);
console.log(linkedList.get(1)); // returns 3
linkedList.addAtHead(4);
console.log(linkedList.get(0)); // returns 4

// The linked list is now 4 -> 1 -> 3
console.log(JSON.stringify(linkedList, null, 2));

// Middle of the linked list
MyLinkedList.prototype.findMiddle = function () {
  if (this.head == null) return null; // Empty list

  let slow = this.head;
  let fast = this.head;
  while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
  }
  return slow.val;
};
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.
console.log(linkedList.findMiddle()); // returns 1

// Reverse the linked list

MyLinkedList.prototype.reverse = function () {
  let prev = null;
  let curr = this.head;
  while (curr != null) {
    let next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
  }
  this.head = prev;
};
linkedList.reverse();
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.
console.log(JSON.stringify(linkedList, null, 2)); // The linked list is now 3 -> 1 -> 4

// Linked list cycle detection using Floyd's Tortoise and Hare algorithm

MyLinkedList.prototype.hasCycle = function () {
  if (this.head == null || this.head.next == null) return false;
  let slow = this.head;
  let fast = this.head;
  while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow === fast) return true;
  }
  return false;
};
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.
console.log(linkedList.hasCycle()); // returns false

// Linked list cycle detection using a Set
MyLinkedList.prototype.hasCycleSet = function () {
  if (this.head == null) return false;
  let seen = new Set();
  let curr = this.head;
  while (curr != null) {
    if (seen.has(curr)) return true;
    seen.add(curr);
    curr = curr.next;
  }
  return false;
};
// Time complexity: O(n)
// Space complexity: O(n)
// Explanation : Time and space complexity is O(n) and O(n) respectively because we traverse the list once and use a set to store the nodes.
console.log(linkedList.hasCycleSet()); // returns false

// Palindrome Linked List using two-pass approach
MyLinkedList.prototype.isPalindrome = function () {
  let slow = (fast = this.head);
  while (fast && fast.next) {
    slow = slow.next;
    fast = fast.next.next;
  }
  let prev = null;
  let curr = slow;
  while (curr) {
    let next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
  }
  let left = this.head;
  let right = prev;
  while (right) {
    if (left.val !== right.val) return false;
    left = left.next;
    right = right.next;
  }
  return true;
};
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list a constant number of times and use a constant amount of extra space.
console.log(linkedList.isPalindrome()); // returns false

// Palindrome Linked List comparing first half reversed with second half
MyLinkedList.prototype.isPalindromeUsingReverse = function () {
  // Find middle using slow/fast pointer
  let slow = this.head;
  let fast = this.head;
  let prev = null;

  while (fast != null && fast.next != null) {
    fast = fast.next.next;
    let next = slow.next;
    slow.next = prev;
    prev = slow;
    slow = next;
  }

  // If odd length, skip middle element
  let second = fast == null ? slow : slow.next;
  let first = prev;

  // Compare reversed first half with second half
  while (first != null && second != null) {
    if (first.val != second.val) return false;
    first = first.next;
    second = second.next;
  }
  return true;
};
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list a constant number of times and use a constant amount of extra space.
console.log(linkedList.isPalindromeUsingReverse()); // returns false

// Palindrome Linked List using array
MyLinkedList.prototype.isPalindromeArray = function () {
  let arr = [];
  let curr = this.head;
  while (curr) {
    arr.push(curr.val);
    curr = curr.next;
  }
  let left = 0;
  let right = arr.length - 1;
  while (left < right) {
    if (arr[left] !== arr[right]) return false;
    left++;
    right--;
  }
  return true;
};
// Time complexity: O(n)
// Space complexity: O(n)
// Explanation : Time complexity is O(n) because we traverse the list once. Space complexity is O(n) because we use an array to store the values of the nodes.
console.log(linkedList.isPalindromeArray()); // returns false

// Intersection of Two Linked Lists using Set
MyLinkedList.getIntersectionNode = function (headA, headB) {
  let seen = new Set();
  let currB = headB;
  while (currB) {
    if (seen.has(currB)) return currB;
    currB = currB.next;
  }

  while (headA) {
    if (seen.has(headA)) return headA;
    seen.add(headA);
    headA = headA.next;
  }
};
// Time complexity: O(m + n)
// Space complexity: O(m) or O(n) depending on which list is stored in the set.
// Explanation : Time complexity is O(m + n) because we traverse both lists once. Space complexity is O(m) or O(n) depending on which list is stored in the set.
console.log(MyLinkedList.getIntersectionNode(linkedList.head, linkedList.head)); // returns the head node since both lists are the same

// Intersection of Two Linked Lists using Two Pointers
MyLinkedList.getIntersectionNodeTwoPointers = function (headA, headB) {
  if (!headA || !headB) return null;
  let ptrA = headA,
    ptrB = headB;
  while (ptrA !== ptrB) {
    ptrA = ptrA ? ptrA.next : headB;
    ptrB = ptrB ? ptrB.next : headA;
  }
  return ptrA;
};
// Time complexity: O(m + n)
// Space complexity: O(1)
// Explanation : Time complexity is O(m + n) because we traverse both lists once. Space complexity is O(1) because we use a constant amount of extra space.
console.log(
  MyLinkedList.getIntersectionNodeTwoPointers(linkedList.head, linkedList.head)
); // returns the head node since both lists are the same

// Remove linked list elements

MyLinkedList.prototype.removeElements = function (val) {
  let sentinel = new Node(0);
  sentinel.next = this.head;
  let prev = sentinel;
  while (prev && prev.next) {
    if (prev.next.val === val) {
      prev.next = prev.next.next;
    } else {
      prev = prev.next;
    }
  }
  return sentinel.next;
};
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.
console.log(JSON.stringify(linkedList.removeElements(1), null, 2)); // The linked list is now 3 -> 4
console.log(MyLinkedList.removeElements(1).val); // returns 3

// Remove nth node from end of list using two passes

MyLinkedList.prototype.removeNthFromEnd = function (n) {
  let sentinel = new Node(0);
  sentinel.next = this.head;
  let length = 0;
  while (this.head) {
    this.head = this.head.next;
    length++;
  }
  let prevPos = length - n;
  let prev = sentinel;
  for (let i = 0; i < prevPos; i++) {
    prev = prev.next;
  }
  prev.next = prev.next.next;
  return sentinel.next;
};
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.
console.log(JSON.stringify(linkedList.removeNthFromEnd(1), null, 2)); // The linked list is now 3 -> 4
console.log(MyLinkedList.removeNthFromEnd(1).val); // returns 3

// Remove nth node from end of list using one pass

MyLinkedList.prototype.removeNthFromEndOnePass = function (n) {
  let sentinel = new Node(0);
  sentinel.next = this.head;
  let first = sentinel;
  // Move first n+1 steps ahead
  for (let i = 0; i <= n; i++) {
    first = first.next;
  }
  let second = sentinel;
  // Move first to the end, maintaining the gap
  while (first) {
    first = first.next;
    second = second.next;
  }
  second.next = second.next.next;
  return sentinel.next;
};
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.
console.log(JSON.stringify(linkedList.removeNthFromEndOnePass(1), null, 2)); // The linked list is now 3 -> 4
console.log(MyLinkedList.removeNthFromEndOnePass(1).val); // returns 3

// Remove duplicates from sorted list
MyLinkedList.prototype.deleteDuplicates = function () {
  let curr = this.head;
  while (curr && curr.next) {
    if (curr.val == curr.next.val) {
      curr.next = curr.next.next;
    } else {
      curr = curr.next;
    }
  }
  return this.head;
};
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.
console.log(JSON.stringify(linkedList.deleteDuplicates(), null, 2)); // The linked list is now 3 -> 4
console.log(MyLinkedList.deleteDuplicates().val); // returns 3

// Odd Even Linked List
MyLinkedList.prototype.oddEvenList = function () {
  if (!this.head || !this.head.next) return this.head;
  let odd = this.head;
  let even = this.head.next;
  let evenStart = even;
  while (odd.next && even.next) {
    odd.next = odd.next.next;
    even.next = even.next.next;
    odd = odd.next;
    even = even.next;
  }
  odd.next = evenStart;
  return this.head;
};
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation : Time and space complexity is O(n) and O(1) respectively because we traverse the list once and use a constant amount of extra space.
console.log(MyLinkedList.oddEvenList()); // The linked list is now 3 -> 4

// Add two numbers represented by linked lists
MyLinkedList.addTwoNumbers = function (l1, l2) {
  let ans = new Node();
  let ansHead = ans;
  let carry = 0;
  while (l1 || l2 || carry) {
    let sum = (l1 ? l1.val : 0) + (l2 ? l2.val : 0) + carry;
    carry = Math.floor(sum / 10);
    let digit = sum % 10;
    let newNode = new Node(digit);
    ans.next = newNode;
    ans = ans.next;
    l1 = l1 && l1.next;
    l2 = l2 && l2.next;
  }
  return ansHead.next;
};
// Time complexity: O(max(m, n))
// Space complexity: O(max(m, n))
// Explanation : Time complexity is O(max(m, n)) because we traverse both lists once. Space complexity is O(max(m, n)) because the size of the new list is at most max(m, n) + 1.
console.log(
  JSON.stringify(
    MyLinkedList.addTwoNumbers(linkedList.head, linkedList.head),
    null,
    2
  )
); // The new linked list is now 6 -> 8
console.log(MyLinkedList.addTwoNumbers(linkedList.head, linkedList.head).val); // returns 6

// Merge two sorted linked lists without dummy node
MyLinkedList.mergeTwoLists = function (l1, l2) {
  if (!l1) return l2;
  if (!l2) return l1;

  let curr = null;
  if (l1.val < l2.val) {
    curr = l1;
    l1 = l1.next;
  } else {
    curr = l2;
    l2 = l2.next;
  }

  let start = curr;
  while (l1 && l2) {
    if (l1.val < l2.val) {
      curr.next = l1;
      l1 = l1.next;
    } else {
      curr.next = l2;
      l2 = l2.next;
    }
    curr = curr.next;
  }
  if (!l1) curr.next = l2;
  if (!l2) curr.next = l1;
  return start;
};
// Time complexity: O(m + n)
// Space complexity: O(1)
// Explanation : Time complexity is O(m + n) because we traverse both lists once. Space complexity is O(1) because we use a constant amount of extra space.
console.log(MyLinkedList.mergeTwoLists(linkedList.head, linkedList.head)); // The new linked list is now 3 -> 3 -> 4 -> 4

// Merge two sorted linked lists using sentinel/dummy node
MyLinkedList.mergeTwoListsDummy = function (l1, l2) {
  let sentinel = new Node(0);
  let curr = sentinel;
  while (l1 && l2) {
    if (l1.val < l2.val) {
      curr.next = l1;
      l1 = l1.next;
    } else {
      curr.next = l2;
      l2 = l2.next;
    }
    curr = curr.next;
  }
  if (!l1) curr.next = l2;
  if (!l2) curr.next = l1;
  return sentinel.next;
};
// Time complexity: O(m + n)
// Space complexity: O(1)
console.log(MyLinkedList.mergeTwoListsDummy(linkedList.head, linkedList.head)); // The new linked list is now 3 -> 3 -> 4 -> 4
