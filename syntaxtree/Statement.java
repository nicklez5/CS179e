//
// Generated by JTB 1.3.2
//

package syntaxtree;

/**
 * Grammar production:
 * f0 -> Block()
 *       | AssignmentStatement()
 *       | ArrayAssignmentStatement()
 *       | IfStatement()
 *       | WhileStatement()
 *       | PrintStatement()
 */
public class Statement implements Node {
   public NodeChoice f0;
   public static String choice_num = "6";
   public int statement_choice;

   public Statement(NodeChoice n0) {
      f0 = n0;
      statement_choice = n0.which;
   }

   public void accept(visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}
