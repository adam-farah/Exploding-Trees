package comp2402a5;
import java.lang.reflect.Array;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
*
* @author morin
*
* @param <T>
*/
public class CountdownTree<T> extends
BinarySearchTree<CountdownTree.Node<T>, T> implements SSet<T> {

 // countdown delay factor
 double d;

 public static class Node<T> extends BSTNode<Node<T>,T> {
  int timer;  // the height of the node
 }
  public int size(Node<T> u){
    if (u == null){return 0;}
    return 1 + size(u.left) + size(u.right);
  }
 public CountdownTree(double d) {
  this.d = d;
  sampleNode = new Node<T>();
  c = new DefaultComparator<T>();
 }

 public boolean add(T x) {
  Node<T> u = new Node<T>();
  Node<T> nodept = null;
  u.x = x;
  u.timer = (int)Math.ceil(d);
  if (super.add(u)){
   Node<T> y = u.parent;
   while(y!=null){
    if(y.timer!=0){
     y.timer--;
     if(y.timer==0){
      nodept = y;
      y = y.parent;
     }
     else{y = y.parent;}
    }
   }
   if(nodept!=null){explode(nodept);}
   return true;
  }
  return false;
 }

 public void splice(Node<T> u) {
  Node<T> y = u.parent;
  Node<T> nodept = null;
  super.splice(u);
  while(y != null){
   if(y.timer!=0){
    y.timer--;
    if(y.timer<=0){
     nodept = y;
     y = y.parent;
    }
    else{y = y.parent;}
   }
  }
  if(nodept!=null){explode(nodept);}
 }

 protected void explode(Node<T> u) {
  int num = size(u);
  Node<T> nodeParent = u.parent;
  Node<T>[] ar = (Node<T>[]) Array.newInstance(Node.class,num);
  packIntoArray(u,ar,0);
  if(nodeParent==null){
   r = buildBalanced(ar,0,num);
   r.parent = null;
  }
  else if(nodeParent.right == u){
   nodeParent.right = buildBalanced(ar,0,num);
   nodeParent.right.parent = nodeParent;
  }
  else{
   nodeParent.left = buildBalanced(ar,0,num);
   nodeParent.left.parent = nodeParent;
  }
 }

 int packIntoArray(Node<T> u, Node<T>[] ar, int i){
  if(u==null){return i;}
  i = packIntoArray(u.left,ar,i);
  ar[i++] = u;
  return packIntoArray(u.right,ar,i);
 }

 protected Node<T> buildBalanced(Node<T>[] ar, int i, int num){
  if(num==0){return null;}
  int m = num/2;
  ar[i+m].timer=(int)Math.ceil(d*(double)num);
  ar[i+m].left=buildBalanced(ar,i,m);
  if(ar[i+m].left!=null){ar[i+m].left.parent=ar[i+m];}
  ar[i+m].right=buildBalanced(ar,i+m+1,num-m-1);
  if(ar[i+m].right!=null){ar[i+m].right.parent=ar[i+m];}
  return ar[i+m];
 }

 // Here is some test code you can use
 public static void main(String[] args) {
  Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(1)), 1000);
  Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)), 1000);
  Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)), 1000);

  java.util.List<SortedSet<Integer>> ell = new java.util.ArrayList<SortedSet<Integer>>();
  ell.add(new java.util.TreeSet<Integer>());
  ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(1)));
  ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)));
  ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)));
  Testum.sortedSetSpeedTests(ell, 1000000);
 }
}
