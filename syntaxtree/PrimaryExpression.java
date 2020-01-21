//
// Generated by JTB 1.3.2
//

package syntaxtree;

/**
 * Grammar production:
 * f0 -> IntegerLiteral()
 *       | TrueLiteral()
 *       | FalseLiteral()
 *       | Identifier()
 *       | ThisExpression()
 *       | ArrayAllocationExpression()
 *       | AllocationExpression()
 *       | NotExpression()
 *       | BracketExpression()
 */
public class PrimaryExpression implements Node {
   public NodeChoice f0;
   public String choice_num = "1";
   public int primaryexpression_choice;

   public PrimaryExpression(NodeChoice n0) {
      f0 = n0;
      primaryexpression_choice = n0.whichChoice;
   }

   public void accept(visitor.Visitor v) {
      v.visit(this);
   }
   //Cannot override it to
   //public <R,A> bool accept(visitor ) because it would ruin the idea of implementing

   public <R,A> R accept(visitor.GJVisitor<R,A> v, A argu) {

      return choice_num;
      //return v.visit(this,argu);
   }
   public <R> R accept(visitor.GJNoArguVisitor<R> v) {

      return choice_num;
      //return v.visit(this);
   }
   public <A> void accept(visitor.GJVoidVisitor<A> v, A argu) {

      v.visit(this,argu);
   }
}
