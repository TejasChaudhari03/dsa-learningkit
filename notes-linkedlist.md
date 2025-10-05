# Linked List — Interview Notes (Java + JavaScript — concise, multi-method analysis)

Brief, then deep-dive. These notes collect common interview problems, multiple solution methods where useful, code snippets in **Java** and **JavaScript**, time/space complexity, short explanation, and quick interview/resume phrasing you can use.

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

# 8 — Intersection of two linked lists (two common methods)

### A. Two-pointer switching trick (no extra space)

```java
Node a = headA, b = headB;
while(a != b){
  a = (a == null) ? headB : a.next;
  b = (b == null) ? headA : b.next;
}
return a; // either intersection node or null
```

**Complexity:** O(m + n) time, O(1) space. Elegant: pointers traverse equal total lengths.

### B. Hash set of nodes (store nodes of one list)

```java
Set<Node> s = new HashSet<>();
Node cur = headA;
while(cur != null){ s.add(cur); cur = cur.next; }
cur = headB;
while(cur != null){
  if(s.contains(cur)) return cur;
  cur = cur.next;
}
return null;
```

**Complexity:** O(m + n) time, O(m) (or O(n)) space.

**Interview tip:** Mention trade-off: O(1) space with two-pointer vs simpler set-based solution.

---

# 9 — Remove nth node from end (two ways)

### A. Two-pass (compute length)

1. Compute length `L`.
2. Find node at `L - n` (previous) and remove `prev.next`.

**Complexity:** O(n) time, O(1) space.

### B. One-pass two-pointer (fast/slow with gap)

- Move `first` forward `n+1` steps from dummy; then move both until `first` null; `second` sits before node to remove.

```java
Node dummy = new Node(0);
dummy.next = head;
Node first = dummy, second = dummy;
for(int i=0;i<=n;i++) first = first.next;
while(first != null){
  first = first.next;
  second = second.next;
}
second.next = second.next.next;
```

**Complexity:** O(n) time, O(1) space.

**Note:** Using dummy simplifies removing head.

---

# 10 — Remove elements by value (all nodes with a target value)

- Use a sentinel/dummy node and iterate, skipping nodes with target value.

```java
Node dummy = new Node(0);
dummy.next = head;
Node cur = dummy;
while(cur.next != null){
  if(cur.next.val == val) cur.next = cur.next.next;
  else cur = cur.next;
}
return dummy.next;
```

**Complexity:** O(n) time, O(1) extra space.

**Edge cases:** head contains the value multiple times → dummy handles it.

---

# 11 — Delete duplicates from sorted list

- Since sorted, duplicate nodes cluster. Walk and skip `current.next` while equal.

```java
Node cur = head;
while(cur != null && cur.next != null){
  if(cur.val == cur.next.val) cur.next = cur.next.next;
  else cur = cur.next;
}
return head;
```

**Complexity:** O(n) time, O(1) space.

---

# 12 — Odd-Even indexed list (reorder)

- Partition into odd and even lists, then concatenate: keep `odd` and `evenHead`.

```java
if(head == null) return head;
Node odd = head, even = head.next, evenHead = even;
while(even != null && even.next != null){
  odd.next = odd.next.next;
  even.next = even.next.next;
  odd = odd.next; even = even.next;
}
odd.next = evenHead;
return head;
```

**Complexity:** O(n) time, O(1) space.

**Use-case:** Rearrange nodes by index parity while preserving relative order.

---

# 13 — Add two numbers (lists as reversed digits)

- Build new list digit-by-digit using carry. Use dummy head.

```java
Node dummy = new Node(0), cur = dummy;
int carry = 0;
while(l1 != null || l2 != null || carry != 0){
  int sum = (l1!=null?l1.val:0) + (l2!=null?l2.val:0) + carry;
  carry = sum / 10;
  cur.next = new Node(sum % 10);
  cur = cur.next;
  if(l1 != null) l1 = l1.next;
  if(l2 != null) l2 = l2.next;
}
return dummy.next;
```

**Complexity:** O(max(m,n)) time, O(max(m,n)) space (new list).

---

Got it 👍 — here’s a **Markdown notes section** for **“Merge Two Sorted Linked Lists”** (Java + JavaScript, with & without dummy node, including complexities and explanation).

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

# Additional Practical Tips & Variants (Interview Notes)

- **Sentinel (Dummy) Node**:

  - Use a dummy head (or tail) to simplify insertion/deletion logic.
  - Eliminates edge-case handling for operations at the head of the list.

- **Maintain `size` and `tail` pointers**:

  - `size` → enables O(1) boundary checks for `get`, `addAtIndex`, and `deleteAtIndex`.
  - `tail` → enables O(1) `addAtTail` instead of O(n) traversal.

- **Restoring the list**:

  - If asked _not_ to mutate the original input (e.g., during palindrome check or reverse), remember to restore the list back to its original state.

- **Time vs. Space Trade-offs**:

  - Pointer-based (two-pointer, fast/slow) solutions usually achieve **O(1) extra space**.
  - HashSet/array-based methods are simpler to implement but use **O(n) space**.
  - Be explicit about why you choose one in an interview.

- **Node identity vs. Node value**:

  - For **cycle detection** and **intersection detection**, comparisons are done on _node references_ (identity), not just values.
  - Be ready to clarify this if the interviewer asks.

- **Edge Cases to handle / discuss**:

  - Empty list (`head == null`).
  - Single node list.
  - Even vs. odd length lists (important for middle node / palindrome).
  - Removing the head node (special-case unless dummy used).
  - Removing the nth node where `n == length` (delete head).
  - Circular lists (infinite loops unless handled).

- **Clarity in code**:

  - Use clear conditions like `if (l1 == null) curr.next = l2;` instead of relying on implicit guarantees.
  - Improves readability and reduces bugs.

---

# Quick Complexity Summary (per problem)

- **Basic get/add/delete:** O(n) / O(1) depending on operation.
- **Middle / Reverse / Cycle (Floyd) / Remove nth (one-pass) / Odd-even / DeleteDuplicates / Remove by value:** **O(n) time, O(1) space**.
- **Set-based cycle / Intersection (Set) / Palindrome (array):** **O(n) time, O(n) space**.
- **Add two numbers:** **O(max(m,n)) time, O(max(m,n)) space** (new list of size at most `max(m,n)+1`).
- **Merge two sorted lists:**

  - **Time complexity:** O(m + n) (each node of both lists visited once).
  - **Space complexity:** O(1) (in-place pointer rearrangement, no extra structures).
  - Applies to both approaches: with dummy node or without dummy node.

---
