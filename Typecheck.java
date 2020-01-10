Stringimport syntaxtree.*;
import visitor.*;
import java.util.*;

public class TypeCheck implements GJVisitor<String, Int> {

	public String visit(Goal n, Int argu){
		String _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		return _ret;
	}

	public String visit(MainClass n, Int argu){
		String _ret = null;
		n.f0.accept(this, argu);
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		n.f5.accept(this, argu);
		n.f6.accept(this, argu);
		n.f7.accept(this, argu);
		n.f8.accept(this, argu);
		n.f9.accept(this, argu);
		n.f10.accept(this, argu);
		n.f11.accept(this, argu);
		n.f12.accept(this, argu);
		n.f13.accept(this, argu);
		n.f14.accept(this, argu);
		n.f15.accept(this, argu);
		n.f16.accept(this, argu);
		n.f17.accept(this, argu);
		return _ret;

	}

	public String visit(TypeDeclaration n, Int argu){
		String _ret=null;
		n.f0.accept(this, argu);
		return _ret;
	}

	public String visit(ClassDeclaration n, Int argu){
		String _ret=null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		n.f5.accept(this, argu);
		return _ret;
	}
	public String visit(ClassExtendsDeclaration n, Int argu){
		String _ret=null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		n.f5.accept(this, argu);
		n.f6.accept(this, argu);
		n.f7.accept(this, argu);
		return _ret;
	}
	public String visit(VarDeclaration n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 return _ret;
	}

	public String visit(MethodDeclaration n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 n.f4.accept(this, argu);
		 n.f5.accept(this, argu);
		 n.f6.accept(this, argu);
		 n.f7.accept(this, argu);
		 n.f8.accept(this, argu);
		 n.f9.accept(this, argu);
		 n.f10.accept(this, argu);
		 n.f11.accept(this, argu);
		 n.f12.accept(this, argu);
		 return _ret;
	}

	public String visit(FormalParameterList n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 return _ret;
	}

	public String visit(FormalParameter n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 return _ret;
	}

	public String visit(FormalParameterRest n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 return _ret;
	}

	public String visit(Type n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	public String visit(ArrayType n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 return _ret;
	}

	public String visit(BooleanType n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	public String visit(IntegerType n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	public String visit(Statement n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	public String visit(Block n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 return _ret;
	}

	public String visit(AssignmentStatement n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 return _ret;
	}

	public String visit(ArrayAssignmentStatement n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 n.f4.accept(this, argu);
		 n.f5.accept(this, argu);
		 n.f6.accept(this, argu);
		 return _ret;
	}

	public String visit(IfStatement n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 n.f4.accept(this, argu);
		 n.f5.accept(this, argu);
		 n.f6.accept(this, argu);
		 return _ret;
	}

	public String visit(WhileStatement n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 n.f4.accept(this, argu);
		 return _ret;
	}

	public String visit(PrintStatement n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 n.f4.accept(this, argu);
		 return _ret;
	}

	public String visit(Expression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	public String visit(AndExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 return _ret;
	}
	public String visit(CompareExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "+"
	 * f2 -> PrimaryExpression()
	 */
	public String visit(PlusExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "-"
	 * f2 -> PrimaryExpression()
	 */
	public String visit(MinusExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "*"
	 * f2 -> PrimaryExpression()
	 */
	public String visit(TimesExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "["
	 * f2 -> PrimaryExpression()
	 * f3 -> "]"
	 */
	public String visit(ArrayLookup n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "."
	 * f2 -> "length"
	 */
	public String visit(ArrayLength n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "."
	 * f2 -> Identifier()
	 * f3 -> "("
	 * f4 -> ( ExpressionList() )?
	 * f5 -> ")"
	 */
	public String visit(MessageSend n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 n.f4.accept(this, argu);
		 n.f5.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> Expression()
	 * f1 -> ( ExpressionRest() )*
	 */
	public String visit(ExpressionList n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> ","
	 * f1 -> Expression()
	 */
	public String visit(ExpressionRest n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 return _ret;
	}

	/**
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
	public String visit(PrimaryExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public String visit(IntegerLiteral n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> "true"
	 */
	public String visit(TrueLiteral n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> "false"
	 */
	public String visit(FalseLiteral n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public String visit(Identifier n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> "this"
	 */
	public String visit(ThisExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> "new"
	 * f1 -> "int"
	 * f2 -> "["
	 * f3 -> Expression()
	 * f4 -> "]"
	 */
	public String visit(ArrayAllocationExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 n.f4.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> "new"
	 * f1 -> Identifier()
	 * f2 -> "("
	 * f3 -> ")"
	 */
	public String visit(AllocationExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> "!"
	 * f1 -> Expression()
	 */
	public String visit(NotExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 return _ret;
	}

	/**
	 * f0 -> "("
	 * f1 -> Expression()
	 * f2 -> ")"
	 */
	public String visit(BracketExpression n, Int argu) {
		 String _ret=null;
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 return _ret;
	}
}
