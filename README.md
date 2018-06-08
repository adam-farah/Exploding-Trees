# Exploding Trees
This was the fifth assignment of class COMP 2402 at Carleton University, taught by Professor Dr. Patrick Ryan Morin.

Author: Adam Farah

Skeleton code was provided by Dr.Morin, therefore only the code I the student wrote will only be up hear to see.

In this assignment, you will implement a countdown tree, in the file CountdownTree.java. 
This is a balanced binary search tree that uses partial rebuilding in a manner similar to scapegoat trees.
Every countdown tree has a float parameter d, defined when it is created. We call this the tree's countdown delay factor.
Each node u in a countdown tree has an int countdown timer, u.timer. When a new node is created, it's timer is set to Math.ceil(d).

When a node u's timer reaches 0, the node explodes and the entire subtree rooted at u is rebuilt into a perfectly balanced binary search 
tree (note that u is probably not the root of this new subtree.) Every node v in the rebuilt subtree has it's timer reset to 
Math.ceil(d*size(v)) where size(v) denotes the number of nodes in the subtree rooted at v.

The add(x) operation in a countdown tree works just like adding in a normal (unbalanced) binary search tree. 
A new node u containing x is added as a leaf in the tree and u.timer is set to Math.ceil(d). 
Next, every node on the path from u to the root of the tree has its timer decremented and, 
if any of these nodes' timers drop to 0, then their subtree explodes.

The remove(x) operation works just like in a normal (unbalanced) binary search tree. 
We first find the node w that contains x. Now, it might not be easy to remove w because it has two children 
so we try to find a node u that is easy to delete. If w has no right child, then we set u=w. 
Otherwise, we pick u to be the leftmost node in w's right subtree'. Next we swap u.x and w.x and splice u out of the tree.

Finally, we walk back up the path from (the now removed) u to the root and decrement the timer of every node along the way. 
If any of these nodes' timers drop to 0, then their subtree explodes.


Files:
  BinarySearchTree.java
  BinaryTree.java
  BinaryTreeNode.java
  BSTNode.java
  DefaultComparator.java
  RangeSSet.java
  SortedSSet.java
  SSet.java
  Testum.java

Other: README
