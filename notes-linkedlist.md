# Linked List — Interview Notes (Java + JavaScript — concise, multi-method analysis)

These notes collect common interview problems, multiple solution methods where useful, code snippets in **Java** and **JavaScript**, time/space complexity, short explanation, and quick interview/resume .

---

# 1 — Brief overview (what a linked list is & when to use)

- **Definition:** A linked list is a linear collection of nodes where each node holds a value and a pointer/reference to the next node (singly) — optionally previous for doubly, and tail-to-head for circular.
- **Variants:** singly, doubly, circular singly/doubly.
- **When to use:** when you need frequent inserts/deletes at arbitrary positions, or you want predictable O(1) insert/delete given a node reference. Not ideal when you need O(1) random access (arrays/ArrayList are better).
- **Core ops & typical complexities (singly):**

  - read by index: O(n)
  - insert/delete at head: O(1)
  - insert/delete at tail: O(n) (unless you maintain `tail` pointer → O(1))
  - traverse: O(n)
  - space: nodes store next pointer → O(n)

---

# 2 — Conventions & node definitions

### Java (assume an inner `Node` class)

```java
class Node {
    int val;
    Node next;
    Node(int v){ val = v; next = null; }
}
```

### JavaScript

```javascript
function Node(val) {
  this.val = val;
  this.next = null;
}
```

Use a sentinel (`dummy`) node for simpler edge-case handling (especially deletes and nth-from-end).

---

# 3 — Basic LinkedList API (get, addAtHead, addAtTail, addAtIndex, deleteAtIndex)

### Problem

Implement standard methods for a list that tracks `head` and `size`.

### Java (core ideas)

```java
public int get(int index) {
  if(index<0 || index>=size) return -1;
  Node cur = head;
  for(int i=0;i<index;i++) cur = cur.next;
  return cur.val;
}
public void addAtHead(int val){ Node n=new Node(val); n.next=head; head=n; size++; }
public void addAtTail(int val){
  Node n=new Node(val);
  if(head==null) head=n;
  else { Node cur=head; while(cur.next!=null) cur=cur.next; cur.next=n; }
  size++;
}
public void deleteAtIndex(int index){
  if(index<0||index>=size) return;
  if(index==0) head = head.next;
  else { Node cur=head; for(int i=0;i<index-1;i++) cur=cur.next; cur.next = cur.next.next; }
  size--;
}
```

### JavaScript

```javascript
MyLinkedList.prototype.get = function (index) {
  if (index < 0 || index >= this.size) return -1;
  let cur = this.head;
  for (let i = 0; i < index; i++) cur = cur.next;
  return cur.val;
};
MyLinkedList.prototype.addAtHead = function (v) {
  let n = new Node(v);
  n.next = this.head;
  this.head = n;
  this.size++;
};
MyLinkedList.prototype.addAtTail = function (v) {
  let n = new Node(v);
  if (!this.head) this.head = n;
  else {
    let cur = this.head;
    while (cur.next) cur = cur.next;
    cur.next = n;
  }
  this.size++;
};
MyLinkedList.prototype.deleteAtIndex = function (index) {
  /* same idea */
};
```

### Notes / alternatives

- **Maintain a `tail` pointer** to make `addAtTail` O(1).
- Use a dummy node to simplify deletions (avoid special-case for head).

**Complexities:** `get` O(n), `addAtHead` O(1), `addAtTail` O(n) (O(1) with `tail`), `addAtIndex` O(n), `deleteAtIndex` O(n). Space O(1) extra.

---

# 4 — Find middle of linked list

### Goal

Return the middle node (if two middles, return the second).

### Method: Fast & slow pointers (single pass)

#### Java

```java
Node slow = head, fast = head;
while(fast != null && fast.next != null){
  slow = slow.next;
  fast = fast.next.next;
}
return slow;
```

#### JS

```javascript
let slow = head,
  fast = head;
while (fast && fast.next) {
  slow = slow.next;
  fast = fast.next.next;
}
return slow;
```

**Complexity:** O(n) time, O(1) space.

**Why:** Fast moves twice as fast; when fast reaches end, slow is mid.

**Interview tip:** Mention off-by-one behavior (returning first vs second middle) and how initialization/loop condition changes which middle is returned.

---

# 5 — Reverse a linked list (iterative)

### Goal

Reverse pointers and return new head.

#### Java

```java
Node prev = null, cur = head;
while(cur != null){
  Node nxt = cur.next;
  cur.next = prev;
  prev = cur;
  cur = nxt;
}
return prev;
```

#### JS

```javascript
let prev = null,
  cur = this.head;
while (cur) {
  let nxt = cur.next;
  cur.next = prev;
  prev = cur;
  cur = nxt;
}
this.head = prev;
```

**Complexity:** O(n) time, O(1) extra space.

**Alternative:** Recursion (simpler code but uses call stack → O(n) space).

**Resume:** Reversed singly-linked list in-place (O(n) time, O(1) space).

---

# 6 — Cycle detection (two methods)

### A. Floyd’s Tortoise & Hare (fast/slow)

```java
Node slow = head, fast = head;
while(fast != null && fast.next != null){
  slow = slow.next;
  fast = fast.next.next;
  if(slow == fast) return true;
}
return false;
```

**Complexity:** O(n) time, O(1) space. Also can be extended to find cycle start (reset one pointer to head, move both by 1).

### B. Using a Set / Hash table

```java
Set<Node> seen = new HashSet<>();
Node cur = head;
while(cur != null){
  if(seen.contains(cur)) return true;
  seen.add(cur);
  cur = cur.next;
}
return false;
```

**Complexity:** O(n) time, O(n) space.

**When to use which:** Floyd is preferred (O(1) space). Use Set when node identity equals detection is required and memory is acceptable.

**Interview tip:** Explain why pointer equality works and how to find cycle entry point after detection.

---

# 7 — Palindrome linked list (multiple ways)

### A. Reverse first half while finding middle (in-place, single pass-ish)

- Reverse first half as you move `slow` & `fast`.
- For odd length, skip middle.
- Compare reversed first half with remaining second half.
- (File has an implementation that reverses as slow advances.)

#### Java

```java
public boolean isPalindrome(Node head) {
    if (head == null || head.next == null) return true;
    Node slow = head, fast = head, prev = null, temp;
    // Reverse first half
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        // Reverse as we go
        temp = slow.next;
        slow.next = prev;
        prev = slow;
        slow = temp;
    }
    // Odd length: skip middle
    if (fast != null) slow = slow.next;
    // Compare reversed first half (prev) with second half (slow)
    while (prev != null && slow != null) {
        if (prev.val != slow.val) return false;
        prev = prev.next;
        slow = slow.next;
    }
    return true;
}
```

#### JavaScript

```javascript
function isPalindrome(head) {
  if (!head || !head.next) return true;
  let slow = head,
    fast = head,
    prev = null,
    temp;
  // Reverse first half
  while (fast && fast.next) {
    fast = fast.next.next;
    temp = slow.next;
    slow.next = prev;
    prev = slow;
    slow = temp;
  }
  // Odd length: skip middle
  if (fast) slow = slow.next;
  // Compare reversed first half (prev) with second half (slow)
  while (prev && slow) {
    if (prev.val !== slow.val) return false;
    prev = prev.next;
    slow = slow.next;
  }
  return true;
}
```

**Complexity:** O(n) time, O(1) space.

### B. Two-pass: find middle → reverse second half → compare

1. Find middle (fast/slow).
2. Reverse second half.
3. Compare halves.
4. (Optional) Restore list by reversing back.

#### Java

```java
public boolean isPalindrome(Node head) {
    if (head == null || head.next == null) return true;
    // Find middle
    Node slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    // Reverse second half
    Node prev = null, cur = slow;
    while (cur != null) {
        Node nxt = cur.next;
        cur.next = prev;
        prev = cur;
        cur = nxt;
    }
    // Compare halves
    Node left = head, right = prev;
    while (right != null) {
        if (left.val != right.val) return false;
        left = left.next;
        right = right.next;
    }
    return true;
}
```

#### JavaScript

```javascript
function isPalindrome(head) {
  if (!head || !head.next) return true;
  // Find middle
  let slow = head,
    fast = head;
  while (fast && fast.next) {
    slow = slow.next;
    fast = fast.next.next;
  }
  // Reverse second half
  let prev = null,
    cur = slow;
  while (cur) {
    let nxt = cur.next;
    cur.next = prev;
    prev = cur;
    cur = nxt;
  }
  // Compare halves
  let left = head,
    right = prev;
  while (right) {
    if (left.val !== right.val) return false;
    left = left.next;
    right = right.next;
  }
  return true;
}
```

**Complexity:** O(n) time, O(1) space.

### C. Array (easy)

- Copy node values into array, two-pointer check.

#### Java

```java
public boolean isPalindromeArray(Node head) {
    List<Integer> vals = new ArrayList<>();
    Node cur = head;
    while (cur != null) {
        vals.add(cur.val);
        cur = cur.next;
    }
    int i = 0, j = vals.size() - 1;
    while (i < j) {
        if (!vals.get(i).equals(vals.get(j))) return false;
        i++; j--;
    }
    return true;
}
```

#### JavaScript

```javascript
function isPalindromeArray(head) {
  const vals = [];
  let cur = head;
  while (cur) {
    vals.push(cur.val);
    cur = cur.next;
  }
  let i = 0,
    j = vals.length - 1;
  while (i < j) {
    if (vals[i] !== vals[j]) return false;
    i++;
    j--;
  }
  return true;
}
```

- **Complexity:** O(n) time, O(n) space.

**Interview tip:** Preferred answer: in-place O(1) space (reverse half), but explain array approach as simple fallback.

---

---

# 8 — Intersection of Two Linked Lists

### A. Two-pointer Switching Trick (No Extra Space)

#### Java

```java
Node a = headA, b = headB;
while (a != b) {
  a = (a == null) ? headB : a.next;
  b = (b == null) ? headA : b.next;
}
return a; // Either intersection node or null
```

#### JavaScript

```javascript
function getIntersectionNode(headA, headB) {
  let a = headA,
    b = headB;
  while (a !== b) {
    a = a ? a.next : headB;
    b = b ? b.next : headA;
  }
  return a; // Either intersection node or null
}
```

**Complexity:** O(m + n) time, O(1) space
**Idea:** Each pointer traverses both lists; total path length equalizes.

---

### B. Hash Set of Nodes (Store Nodes of One List)

#### Java

```java
Set<Node> set = new HashSet<>();
Node cur = headA;
while (cur != null) {
  set.add(cur);
  cur = cur.next;
}
cur = headB;
while (cur != null) {
  if (set.contains(cur)) return cur;
  cur = cur.next;
}
return null;
```

#### JavaScript

```javascript
function getIntersectionNodeSet(headA, headB) {
  const set = new Set();
  let cur = headA;
  while (cur) {
    set.add(cur);
    cur = cur.next;
  }
  cur = headB;
  while (cur) {
    if (set.has(cur)) return cur;
    cur = cur.next;
  }
  return null;
}
```

**Complexity:** O(m + n) time, O(m) space
**Tip:** Easier to code, but less optimal in memory.

---

# 9 — Remove Nth Node from End

### A. Two-pass (Compute Length)

#### Java

```java
int length = 0;
Node cur = head;
while (cur != null) { cur = cur.next; length++; }

Node dummy = new Node(0);
dummy.next = head;
cur = dummy;
for (int i = 0; i < length - n; i++) cur = cur.next;
cur.next = cur.next.next;
return dummy.next;
```

#### JavaScript

```javascript
function removeNthFromEnd(head, n) {
  let dummy = { val: 0, next: head };
  let length = 0,
    cur = head;
  while (cur) {
    length++;
    cur = cur.next;
  }

  cur = dummy;
  for (let i = 0; i < length - n; i++) cur = cur.next;
  cur.next = cur.next.next;
  return dummy.next;
}
```

**Complexity:** O(n) time, O(1) space

---

### B. One-pass Two-pointer (Fast/Slow with Gap)

#### Java

```java
Node dummy = new Node(0);
dummy.next = head;
Node first = dummy, second = dummy;
for (int i = 0; i <= n; i++) first = first.next;
while (first != null) {
  first = first.next;
  second = second.next;
}
second.next = second.next.next;
return dummy.next;
```

#### JavaScript

```javascript
function removeNthFromEndOnePass(head, n) {
  const dummy = { val: 0, next: head };
  let first = dummy,
    second = dummy;
  for (let i = 0; i <= n; i++) first = first.next;
  while (first) {
    first = first.next;
    second = second.next;
  }
  second.next = second.next.next;
  return dummy.next;
}
```

**Complexity:** O(n) time, O(1) space
**Tip:** Dummy node simplifies removing head.

---

# 10 — Remove Elements by Value

#### Java

```java
Node dummy = new Node(0);
dummy.next = head;
Node cur = dummy;
while (cur.next != null) {
  if (cur.next.val == val) cur.next = cur.next.next;
  else cur = cur.next;
}
return dummy.next;
```

#### JavaScript

```javascript
function removeElements(head, val) {
  const dummy = { val: 0, next: head };
  let cur = dummy;
  while (cur.next) {
    if (cur.next.val === val) cur.next = cur.next.next;
    else cur = cur.next;
  }
  return dummy.next;
}
```

**Complexity:** O(n) time, O(1) space

---

# 11 — Delete Duplicates from Sorted List

#### Java

```java
Node cur = head;
while (cur != null && cur.next != null) {
  if (cur.val == cur.next.val) cur.next = cur.next.next;
  else cur = cur.next;
}
return head;
```

#### JavaScript

```javascript
function deleteDuplicates(head) {
  let cur = head;
  while (cur && cur.next) {
    if (cur.val === cur.next.val) cur.next = cur.next.next;
    else cur = cur.next;
  }
  return head;
}
```

**Complexity:** O(n) time, O(1) space

---

# 12 — Odd-Even Indexed List

#### Java

```java
if (head == null) return head;
Node odd = head, even = head.next, evenHead = even;
while (even != null && even.next != null) {
  odd.next = odd.next.next;
  even.next = even.next.next;
  odd = odd.next;
  even = even.next;
}
odd.next = evenHead;
return head;
```

#### JavaScript

```javascript
function oddEvenList(head) {
  if (!head) return head;
  let odd = head,
    even = head.next,
    evenHead = even;
  while (even && even.next) {
    odd.next = odd.next.next;
    even.next = even.next.next;
    odd = odd.next;
    even = even.next;
  }
  odd.next = evenHead;
  return head;
}
```

**Complexity:** O(n) time, O(1) space

---

# 13 — Add Two Numbers (Lists as Reversed Digits)

#### Java

```java
Node dummy = new Node(0), cur = dummy;
int carry = 0;
while (l1 != null || l2 != null || carry != 0) {
  int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
  carry = sum / 10;
  cur.next = new Node(sum % 10);
  cur = cur.next;
  if (l1 != null) l1 = l1.next;
  if (l2 != null) l2 = l2.next;
}
return dummy.next;
```

#### JavaScript

```javascript
function addTwoNumbers(l1, l2) {
  const dummy = { val: 0, next: null };
  let cur = dummy,
    carry = 0;
  while (l1 || l2 || carry) {
    let sum = (l1 ? l1.val : 0) + (l2 ? l2.val : 0) + carry;
    carry = Math.floor(sum / 10);
    cur.next = { val: sum % 10, next: null };
    cur = cur.next;
    if (l1) l1 = l1.next;
    if (l2) l2 = l2.next;
  }
  return dummy.next;
}
```

**Complexity:** O(max(m, n)) time, O(max(m, n)) space

---

# 14 — Merge Two Sorted Linked Lists

Merging two **sorted singly linked lists** into one sorted list is a fundamental interview problem.
It can be solved in two standard ways: **without dummy node** (manual head selection) and **with dummy node (sentinel)**.

---

## Approach 1 — Without Dummy Node

### Java

```java
// Merge two sorted linked lists without dummy node
public Node mergeTwoLists(Node l1, Node l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;

    Node curr;
    if (l1.val < l2.val) {
        curr = l1;
        l1 = l1.next;
    } else {
        curr = l2;
        l2 = l2.next;
    }
    Node head = curr;

    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
            curr.next = l1;
            l1 = l1.next;
        } else {
            curr.next = l2;
            l2 = l2.next;
        }
        curr = curr.next;  // advance tail
    }
    if (l1 == null) curr.next = l2;
    if (l2 == null) curr.next = l1;

    return head;
}
```

### JavaScript

```javascript
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
    curr = curr.next; // move forward
  }
  if (!l1) curr.next = l2;
  if (!l2) curr.next = l1;

  return start;
};
```

✅ **Time Complexity:** O(m + n)
✅ **Space Complexity:** O(1)
**Explanation:** Both lists are traversed once, and only pointers are rearranged.
⚠️ Needs extra logic to decide the head node before the loop.

---

## Approach 2 — With Dummy Node (Sentinel)

### Java

```java
// Merge two sorted linked lists using dummy node
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

    return dummy.next; // skip dummy
}
```

### JavaScript

```javascript
// Merge two sorted linked lists using dummy node
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
```

✅ **Time Complexity:** O(m + n)
✅ **Space Complexity:** O(1)
**Explanation:** Uses a dummy node to avoid separate logic for head selection. At the end, return `dummy.next`.

---

# 15 — Rotate List to the Right by K Places

#### Java

```java
public Node rotateRight(Node head, int k) {
    if (head == null || head.next == null || k == 0) return head;

    // Step 1: find length and last node
    Node curr = head;
    int length = 1;
    while (curr.next != null) {
        curr = curr.next;
        length++;
    }

    // Step 2: find rotation distance
    int steps = k % length;
    if (steps == 0) return head;

    // Step 3: use two pointers
    Node slow = head, fast = head;
    for (int i = 0; i < steps; i++) fast = fast.next;

    // Step 4: move both till fast at tail
    while (fast.next != null) {
        slow = slow.next;
        fast = fast.next;
    }

    // Step 5: rewire links
    fast.next = head;
    Node newHead = slow.next;
    slow.next = null;

    return newHead;
}
// Time: O(n)
// Space: O(1)
```

#### JavaScript

```javascript
MyLinkedList.prototype.rotateRight = function (k) {
  if (!this.head || !this.head.next || k === 0) return this.head;

  // Step 1: find length
  let length = 0;
  let curr = this.head;
  while (curr) {
    length++;
    curr = curr.next;
  }

  // Step 2: handle large k
  k = k % length;
  if (k === 0) return this.head;

  // Step 3: move fast pointer k ahead
  let slow = this.head,
    fast = this.head;
  for (let i = 0; i < k; i++) fast = fast.next;

  // Step 4: move both till fast at end
  while (fast.next) {
    slow = slow.next;
    fast = fast.next;
  }

  // Step 5: rotate
  fast.next = this.head;
  let newHead = slow.next;
  slow.next = null;
  return newHead;
};
// Time: O(n)
// Space: O(1)
```

**Explanation:**
Traverse once to find list length and again to rotate pointers.
Uses two-pointer technique; works efficiently even when k > n.

Absolutely ✅ — here’s your **continued professional markdown notes** in the same clean, consistent format as before — now adding

# **16 — Swap Nodes in Pairs (Iterative + Recursive)**

Swapping nodes in pairs means taking every two adjacent nodes in a linked list and swapping them.
For example, given `1 → 2 → 3 → 4`, the output should be `2 → 1 → 4 → 3`.

There are two standard approaches: **iterative (dummy pointer)** and **recursive**.

---

## Approach 1 — Iterative (Using Dummy Node)

### Java

```java
// Swap Nodes in Pairs using iterative approach
public Node swapPairs(Node head) {
    if (head == null || head.next == null) return head;

    Node dummy = new Node(0); // Create a dummy node to simplify edge cases
    dummy.next = head; // Link dummy to head
    Node prev = dummy; // Tracks node before the current pair
    Node curr = head;  // Current node pointer

    while (curr != null && curr.next != null) { // Process pairs
        Node next = curr.next; // Second node of the pair

        // Swap nodes
        curr.next = next.next; // Connect current node to node after next
        next.next = curr;      // Reverse link: next points to current
        prev.next = next;      // Link previous node to new head of pair

        // Move pointers ahead for next pair
        prev = curr;
        curr = curr.next;
    }
    return dummy.next; // Return new head after dummy
}
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation: Each node visited once, constant extra space.
```

### JavaScript

```javascript
// Swap Nodes in Pairs using iterative approach
MyLinkedList.prototype.swapPairs = function () {
  if (!this.head || !this.head.next) return this.head;

  let sentinel = new Node(0);
  sentinel.next = this.head;
  let prevNode = sentinel;
  let currNode = this.head;
  let nextNode = this.head.next;

  while (currNode && nextNode) {
    // Swap pointers
    prevNode.next = nextNode;
    currNode.next = nextNode.next;
    nextNode.next = currNode;

    // Move to next pair
    prevNode = currNode;
    currNode = currNode.next;
    nextNode = currNode ? currNode.next : null;
  }

  return sentinel.next;
};
// Time complexity: O(n)
// Space complexity: O(1)
// Explanation: Iterates once over list, uses only constant extra nodes (sentinel).
```

✅ **Key Insight:** Dummy (sentinel) node simplifies head handling — avoids separate logic for swapping the first pair.

---

## Approach 2 — Recursive

### Java

```java
// Swap Nodes in Pairs using recursive approach
public Node swapPairsRecursive(Node head) {
    if (head == null || head.next == null) return head; // Base case

    Node first = head;        // First node
    Node second = head.next;  // Second node

    // Swap and recursively process next pairs
    first.next = swapPairsRecursive(second.next);
    second.next = first;

    return second; // Return new head of this pair
}
// Time complexity: O(n)
// Space complexity: O(n) due to recursion stack
// Explanation: Each node visited once, recursion depth = list length / 2.
```

### JavaScript

```javascript
// Swap Nodes in Pairs using recursive approach
MyLinkedList.prototype.swapPairsRecursive = function (head = this.head) {
  if (!head || !head.next) return head;

  let first = head;
  let second = head.next;

  first.next = this.swapPairsRecursive(second.next);
  second.next = first;

  return second;
};
// Time complexity: O(n)
// Space complexity: O(n) (recursion stack)
// Explanation: Simple and elegant recursive definition.
```

✅ **Intuition:** Recursively swap the rest of the list first, then connect current two nodes in reversed order.

---

## When to Use Which

| Approach                   | Pros                                                | Cons                                                         |
| -------------------------- | --------------------------------------------------- | ------------------------------------------------------------ |
| **Iterative (Dummy Node)** | In-place, constant space, avoids recursion overhead | Slightly more verbose setup                                  |
| **Recursive**              | Cleaner, elegant, concise                           | Uses O(n) stack space, risk of stack overflow on large lists |

---

# Additional Practical Tips & Interview Variants

- **Sentinel (Dummy) Node:**
  Simplifies head/tail insertions and deletions by removing edge-case checks.

- **Maintain `size` and `tail`:**
  Enables O(1) operations for length checks and tail insertions.

- **Restoring the list:**
  Reverse back if asked _not_ to mutate the original (e.g., palindrome or partial reverse problems).

- **Trade-offs (Time vs Space):**

  - Prefer O(1) space pointer-based methods when performance matters.
  - O(n) array/set-based methods are acceptable when clarity or debugging ease is prioritized.

- **Node Identity vs Value:**
  Always compare **node references** (not just values) in cycle/intersection problems.

- **Edge Cases to Handle:**

  - Empty or single-node lists.
  - Even vs odd length (for middle/palindrome).
  - Deleting the head or tail.
  - Rotating by 0 or full length.
  - Circular lists (infinite loop guard).

- **Clean Code Practices:**
  Prefer clear conditionals (`if (l1 == null) curr.next = l2;`) over implicit logic — improves readability and avoids overwrites.

---

# Quick Complexity Summary (Per Problem)

| Problem                                                                                                | Time         | Space        | Notes / Key Idea                    |
| ------------------------------------------------------------------------------------------------------ | ------------ | ------------ | ----------------------------------- |
| Basic get/add/delete                                                                                   | O(1)–O(n)    | O(1)         | Depends on operation                |
| Reverse / Middle / Floyd Cycle / Remove nth (one-pass) / Odd-even / DeleteDuplicates / Remove by value | O(n)         | O(1)         | Single traversal                    |
| Set-based Cycle / Intersection (Set) / Palindrome (array)                                              | O(n)         | O(n)         | Uses extra memory                   |
| Add Two Numbers                                                                                        | O(max(m, n)) | O(max(m, n)) | Creates new result list             |
| Merge Two Sorted Lists                                                                                 | O(m + n)     | O(1)         | Dummy node simplifies logic         |
| Rotate Right by K                                                                                      | O(n)         | O(1)         | Two-pointer technique + modulo math |
| Palindrome (reverse half)                                                                              | O(n)         | O(1)         | In-place compare                    |
| Intersection (two-pointer)                                                                             | O(m + n)     | O(1)         | Elegant switching trick             |
| **Swap Nodes in Pairs (Iterative)**                                                                    | **O(n)**     | **O(1)**     | Dummy node for smooth iteration     |
| **Swap Nodes in Pairs (Recursive)**                                                                    | **O(n)**     | **O(n)**     | Elegant but stack-space recursive   |

---
