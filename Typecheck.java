Stringimport syntaxtree.*;
import visitor.*;
import java.util.*;
import static packagename.Constants.*;

public class TypeCheck extends GJDepthFirst<String, Int>  {
	//Input of information from a file
	//Stack variable
	//Pop out the item from stack after it has been type checked.

	public String visit(Goal n, Int argu){
		String _ret = "FALSE";

		boolean check_first = false;
		boolean check_sec = false;

		if((n.f0.accept(this, argu)).equals(MAIN_CLASS_TOKEN)){
			check_first = true;
		}
		if((n.f1.accept(this, argu)).equals(NODE_LIST_OPTIONAL_TOKEN)){
			check_sec = true;
		}
		n.f2.accept(this, argu);

		if(check_first && check_sec){
			_ret = "TRUE";
		}
		return _ret;
	}

	public String visit(MainClass n, Int argu){
		String _ret = "FALSE";
		boolean check_first = false;
		boolean check_sec = false;
		boolean chek_third = false;
		n.f0.accept(this, argu);
		if((n.f1.accept(this,argu)).equals(ID_TOKEN)){
			check_first = true;
		}

		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		n.f5.accept(this, argu);
		n.f6.accept(this, argu);
		if((n.f11.accept(this, argu)).equals(ID_TOKEN)){
			check_sec = true;
		}
		n.f7.accept(this, argu);
		n.f8.accept(this, argu);
		n.f9.accept(this, argu);
		n.f10.accept(this, argu);
		n.f12.accept(this, argu);
		n.f13.accept(this, argu);
		n.f14.accept(this, argu);

		if((n.f15.accept(this,argu)).equals(NODE_LIST_OPTIONAL_TOKEN)){
			check_third = true;
		}
		n.f16.accept(this, argu);
		n.f17.accept(this, argu);

		if(check_first && check_sec && check_third){
			_ret = "TRUE";
		}
		return _ret;

	}

	public String visit(TypeDeclaration n, Int argu){
		String _ret = "FALSE";
		boolean check_1 = false;

		if((n.f0.accept(this, argu)).equals(NODE_CHOICE_TOKEN)){
			check_1 = true;
		}
		if(check_1){
			_ret = "TRUE";
		}
		return _ret;
	}

	public String visit(ClassDeclaration n, Int argu){
		String _ret = "FALSE";
		boolean check_1 = false;

		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		if((n.f4.accept(this, argu)).equals(NODE_LIST_OPTIONAL_TOKEN)){
			check_1 = true;
		}
		n.f5.accept(this, argu);
		if(check_1){
			_ret = "TRUE";
		}
		return _ret;
	}
	public String visit(ClassExtendsDeclaration n, Int argu){
		String _ret = "FALSE";

		boolean check_1 = false;

		//Test for F6
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		n.f5.accept(this, argu);
		if((n.f6.accept(this, argu)).equals(NODE_LIST_OPTIONAL_TOKEN)){
			check_1 = true;
		}
		n.f7.accept(this, argu);

		if(check_1){
			_ret = "TRUE";
		}
		return _ret;
	}
	public String visit(VarDeclaration n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_1 = false;
		 boolean check_2 = false;
		 if((n.f0.accept(this, argu)).equals(TYPE_TOKEN)){
			 check_1 = true;
		 }
		 if((n.f1.accept(this, argu)).equals(ID_TOKEN)){
			 check_2 = true;
		 }
		 n.f2.accept(this, argu);
		 if(check_1 && check_2){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(MethodDeclaration n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_1 = false;
		 boolean check_2 = false;
		 boolean check_3 = false;

		 //Check for the statement which is f8
		 //Check for the type - f1 and expression - f10
		 n.f0.accept(this, argu);
		 if((n.f1.accept(this, argu)).equals(TYPE_TOKEN)){
			 check_1 = true;
		 }
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 n.f4.accept(this, argu);
		 n.f5.accept(this, argu);
		 n.f6.accept(this, argu);
		 n.f7.accept(this, argu);
		 if((n.f8.accept(this, argu)).equals(STATEMENT_TOKEN)){
			 check_2 = true;
		 }

		 n.f9.accept(this, argu);
		 if((n.f10.accept(this, argu)).equals(EXP_TOKEN)){
			 check_3 = true;
		 }
		 n.f11.accept(this, argu);
		 n.f12.accept(this, argu);
		 if(check_1 && check_2 && check_3){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(FormalParameterList n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 boolean check_second = false;
		 if((n.f0.accept(this, argu)).equals(FORMALPARAMETER_TOKEN)){
			 check_first = true;
		 }
		 if((n.f1.accept(this, argu)).equals(NODE_LIST_OPTIONAL_TOKEN)){
			 check_second = true;
		 }
		 if(check_first && check_second){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(FormalParameter n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 boolean check_second = false;
		 if((n.f0.accept(this, argu)).equals(TYPE_TOKEN)){
			 check_first = true;
		 }
		 if((n.f1.accept(this, argu)).equals(ID_TOKEN)){
			 check_second = true;
		 }
		 if(check_first && check_second){
			 _ret = "TRUE";
		 }

		 return _ret;
	}

	public String visit(FormalParameterRest n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 n.f0.accept(this, argu);
		 if((n.f1.accept(this,argu)).equals(FORMALPARAMETER_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(Type n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 if((n.f0.accept(this, argu)).equals(NODE_CHOICE_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(ArrayType n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;


		if((n.f0.accept(this, argu)).equals(NODE_TOKEN)){
			check_first = true;
		}
		n.f1.accept(this,argu);
		n.f2.accept(this,argu);
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(BooleanType n, Int argu) {
		String _ret = "FALSE";
		boolean check_first = false;

		if((n.f0.accept(this, argu)).equals(NODE_TOKEN)){
			check_first = true;
		}
		if(check_first){
			_ret = "TRUE";
		}
		return _ret;
	}

	public String visit(IntegerType n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;

		 if((n.f0.accept(this, argu)).equals(NODE_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(Statement n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;

		 if((n.f0.accept(this, argu)).equals(NODE_CHOICE_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(Block n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;


		 n.f0.accept(this, argu);
		 if((n.f1.accept(this, argu)).equals(NODE_LIST_OPTIONAL_TOKEN)){
			 check_first = true;
		 }
		 n.f2.accept(this, argu);

		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(AssignmentStatement n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 boolean check_second = false;

		 if((n.f0.accept(this, argu)).equals(ID_TOKEN)){
			 check_first = true;
		 }
		 n.f1.accept(this, argu);

		 if((n.f2.accept(this, argu)).equals(EXP_TOKEN)){
			 check_second = true;
		 }
		 n.f3.accept(this, argu);
		 if(check_first && check_second){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(ArrayAssignmentStatement n, Int argu) {
		 String _ret = "FALSE";

		 boolean check_first = false;
		 boolean check_second = false;
		 boolean check_third = false;
		 if((n.f0.accept(this, argu)).equals(ID_TOKEN)){
			 check_first = true;
		 }

		 n.f1.accept(this, argu);
		 if((n.f2.accept(this, argu)).equals(EXP_TOKEN)){
			 check_second = true;
		 }

		 n.f3.accept(this, argu);
		 n.f4.accept(this, argu);
		 if((n.f5.accept(this, argu)).equals(EXP_TOKEN)){
			 check_third = true;
		 }
		 n.f6.accept(this, argu);

		 if(check_first && check_second && check_third){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(IfStatement n, Int argu) {
		 String _ret = "FALSE";

		 boolean check_first = false;
		 boolean check_second = false;
		 boolean check_third = false;

		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);

		 if((n.f2.accept(this, argu)).equals(EXP_TOKEN)){
			 check_first = true;
		 }
		 n.f3.accept(this, argu);

		 if((n.f4.accept(this, argu)).equals(STATEMENT_TOKEN)){
			 check_second = true;
		 }

		 n.f5.accept(this, argu);

		 if((n.f6.accept(this, argu)).equals(STATEMENT_TOKEN)){
			 check_third = true;
		 }
		 if(check_first && check_second && check_third){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	public String visit(WhileStatement n, Int argu) {
		String _ret = "FALSE";

		boolean check_third = false;
		boolean check_fifth = false;

		n.f0.accept(this, argu);
		n.f1.accept(this, argu);

		if((n.f2.accept(this, argu)).equals(EXP_TOKEN)){
			check_third = true;
		}
		n.f3.accept(this, argu);

		if((n.f4.accept(this, argu)).equals(STATEMENT_TOKEN)){
			check_fifth = true;
		}

		if(check_third && check_fifth){
			_ret = "TRUE";
		}

		 return _ret;
	}

	public String visit(PrintStatement n, Int argu) {
		 String _ret = "FALSE";

		 boolean check_third = false;


		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);

		 if((n.f2.accept(this, argu)).equals(EXP_TOKEN)){
			 check_third = true;
		 }
		 n.f3.accept(this, argu);
		 n.f4.accept(this, argu);

		 if(check_third){
			 _ret = "TRUE";
		 }

		 return _ret;
	}

	public String visit(Expression n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;

		 if((n.f0.accept(this, argu)).equals(NODE_CHOICE_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}


	public String visit(AndExpression n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 boolean check_third = false;

		 // RETURNS THE CHOICE NUMBER
		 if((n.f0.accept(this, argu)).equals(PRIMARY_EXP_TOKEN)){
			 check_first = true;
		 }

		 n.f1.accept(this, argu);

		 if((n.f2.accept(this, argu)).equals(PRIMARY_EXP_TOKEN)){
			 check_third = true;
		 }

		 if(check_first && check_third){
			 _ret = "TRUE";
		 }

		 return _ret;
	}

	public String visit(CompareExpression n, Int argu) {
		 String _ret = "FALSE";

		 boolean check_first = false;
		 boolean check_third = false;

		 if((n.f0.accept(this,argu)).equals(PRIMARY_EXP_TOKEN)){
			 check_first = true;
		 }

		 n.f1.accept(this, argu);

		 if((n.f2.accept(this, argu)).equals(PRIMARY_EXP_TOKEN)){
			 check_third = true;
		 }

		 if(check_first && check_third){
			 _ret = "TRUE";
		 }

		 return _ret;
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "+"
	 * f2 -> PrimaryExpression()
	 */
	public String visit(PlusExpression n, Int argu) {
		String _ret = "FALSE";

		boolean check_first = false;
		boolean check_third = false;

		if((n.f0.accept(this,argu)).equals(PRIMARY_EXP_TOKEN)){
			check_first = true;
		}

		n.f1.accept(this, argu);

		if((n.f2.accept(this, argu)).equals(PRIMARY_EXP_TOKEN)){
			check_third = true;
		}

		if(check_first && check_third){
			_ret = "TRUE";
		}

		return _ret;

	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "-"
	 * f2 -> PrimaryExpression()
	 */
	public String visit(MinusExpression n, Int argu) {
		String _ret = "FALSE";

	 boolean check_first = false;
	 boolean check_third = false;

	 if((n.f0.accept(this,argu)).equals(PRIMARY_EXP_TOKEN)){
		 check_first = true;
	 }

	 n.f1.accept(this, argu);

	 if((n.f2.accept(this, argu)).equals(PRIMARY_EXP_TOKEN)){
		 check_third = true;
	 }

	 if(check_first && check_third){
		 _ret = "TRUE";
	 }

	 return _ret;
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "*"
	 * f2 -> PrimaryExpression()
	 */
	public String visit(TimesExpression n, Int argu) {
		String _ret = "FALSE";

	 boolean check_first = false;
	 boolean check_third = false;

	 if((n.f0.accept(this,argu)).equals(PRIMARY_EXP_TOKEN)){
		 check_first = true;
	 }

	 n.f1.accept(this, argu);

	 if((n.f2.accept(this, argu)).equals(PRIMARY_EXP_TOKEN)){
		 check_third = true;
	 }

	 if(check_first && check_third){
		 _ret = "TRUE";
	 }

	 return _ret;
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "["
	 * f2 -> PrimaryExpression()
	 * f3 -> "]"
	 */
	public String visit(ArrayLookup n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 boolean check_sec = false;
		 if((n.f0.accept(this, argu)).equals(PRIMARY_EXP_TOKEN)){
			 check_first = true;
		 }
		 n.f1.accept(this, argu);
		 if((n.f2.accept(this, argu)).equals(PRIMARY_EXP_TOKEN)){
			 check_sec = true;
		 }
		 n.f3.accept(this, argu);
		 if(check_first && check_sec){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "."
	 * f2 -> "length"
	 */
	public String visit(ArrayLength n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;

		 if((n.f0.accept(this, argu)).equals(PRIMARY_EXP_TOKEN)){
			 check_first = true;
		 }
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 if(check_first){
			 _ret = "TRUE";
		 }
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
		 String _ret = "FALSE";
		 boolean check_first = false;

		 //check f4
		 n.f0.accept(this, argu);
		 n.f1.accept(this, argu);
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 if((n.f4.accept(this, argu)).equals(NODE_OPTIONAL_TOKEN)){
			 check_first = true;
		 }
		 n.f5.accept(this, argu);
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> Expression()
	 * f1 -> ( ExpressionRest() )*
	 */
	public String visit(ExpressionList n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 boolean check_sec = false;
		 if((n.f0.accept(this, argu)).equals(EXP_TOKEN)){
			 check_first = true;
		 }
		 if((n.f1.accept(this, argu)).equals(NODE_LIST_OPTIONAL_TOKEN)){
			 check_sec = true;
		 }
		 if(check_first && check_sec){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> ","
	 * f1 -> Expression()
	 */
	public String visit(ExpressionRest n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 n.f0.accept(this, argu);
		 if((n.f1.accept(this, argu)).equals(EXP_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
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
		 String _ret = "FALSE";
		 boolean check_first = false;
		 if((n.f0.accept(this, argu)).equals(NODE_CHOICE_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public String visit(IntegerLiteral n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 if((n.f0.accept(this, argu)).equals(NODE_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> "true"
	 */
	public String visit(TrueLiteral n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;

		 if((n.f0.accept(this, argu)).equals(NODE_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> "false"
	 */
	public String visit(FalseLiteral n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;

		 if((n.f0.accept(this, argu)).equals(NODE_TOKEN){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public String visit(Identifier n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;

		 if((n.f0.accept(this, argu)).equals(NODE_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> "this"
	 */
	public String visit(ThisExpression n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 if((n.f0.accept(this, argu)).equals(NODE_TOKEN)){
			 check_first = true;
		 }
		 if(check_first){
			 _ret = "TRUE";
		 }
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
		 String _ret = "FALSE";
		 boolean check_first = false;
		 boolean check_sec = false;
		 n.f0.accept(this, argu);
		 if((n.f1.accept(this, argu)).equals(NODE_TOKEN)){
			 check_first = true;
		 }
		 n.f2.accept(this, argu);
		 if((n.f3.accept(this, argu)).equals(EXP_TOKEN)){
			 check_sec = true;
		 }
		 n.f4.accept(this, argu);
		 if(check_first && check_sec){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> "new"
	 * f1 -> Identifier()
	 * f2 -> "("
	 * f3 -> ")"
	 */
	public String visit(AllocationExpression n, Int argu) {
		 String _ret = "FALSE";
		 boolean check_first = false;
		 n.f0.accept(this, argu);
		 if((n.f1.accept(this, argu)).equals(ID_TOKEN)){
			 check_first = true;
		 }
		 n.f2.accept(this, argu);
		 n.f3.accept(this, argu);
		 if(check_first){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> "!"
	 * f1 -> Expression()
	 */
	public String visit(NotExpression n, Int argu) {
		 String _ret = "FALSE";
		 n.f0.accept(this, argu);
		 if((n.f1.accept(this, argu)).equals(EXP_TOKEN)){
			 _ret = "TRUE";
		 }
		 return _ret;
	}

	/**
	 * f0 -> "("
	 * f1 -> Expression()
	 * f2 -> ")"
	 */
	public String visit(BracketExpression n, Int argu) {
		 String _ret = "FALSE";
		 n.f0.accept(this, argu);
		 if((n.f1.accept(this, argu)).equals(EXP_TOKEN)){
			 _ret = "TRUE";
		 }
		 n.f2.accept(this, argu);
		 return _ret;
	}
}
