//
// Generated by JTB 1.3.2
//

package syntaxtree;

/**
 * Represents a grammar choice, e.g. ( A | B )
 */
public class NodeChoice implements Node {
   public static String choice_num = "7";
   public NodeChoice(Node node) {
      this(node, -1);
   }

   public NodeChoice(Node node, int whichChoice) {
      choice = node;
      which = whichChoice;
   }

   public void accept(visitor.Visitor v) {
      choice.accept(v);
   }
   public <R,A> R accept(visitor.GJVisitor<R,A> v, A argu) {
      return choice.accept(v,argu);
   }
   public <R> R accept(visitor.GJNoArguVisitor<R> v) {
      return choice.accept(v);
   }
   public <A> void accept(visitor.GJVoidVisitor<A> v, A argu) {
      choice.accept(v,argu);
   }

   public Node choice;
   public int which;
}
