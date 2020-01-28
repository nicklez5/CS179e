import syntaxtree.*;
import visitor.*;
import java.util.*;

import static packagename.Constants.*;

class TypeCheck extends GJDepthFirst<String, Int>  {

	Stack<Node> token_stack = new Stack<Node>();
	public Scope_Check current_sym_table;
	public Vector<Scope_Check> current_class_sym;
	public TypeCheck(){

		//Token first_token = new Token();
		//token_parser = new MiniJavaParser(System.in);

		/*
		first_token = token_parser.token;
		token_stack.push(first_token);
		//Inserting tokens from the parser to a stack
		while(first_token.kind != 0){
		token_stack.push(first_token);
		first_token = token_parser.getNextToken();
	}
	//Pop out the stack tokens and insert into a scope map
	while(!token_stack.empty()){
	Token second_token = new Token();
	second_token = token_stack.pop();
	if(second_token.kind == 44){

}
}
*/

//Because it traverses all of the java syntax tree by the visitor
//It would output it all into a stack which can be bunch of strings.

}
public TypeCheck(Depth_Type_Check regular_check){
	current_sym_table = regular_check.sym_table;
	current_class_sym = regular_check.class_sym;
}
//Automatically runs
public static void main (String[] args){

	Goal holy_goal;
	MiniJavaParser xyz = new MiniJavaParser(System.in);
	holy_goal = xyz.goal();
	Depth_Type_Check sym_check = new Depth_Type_Check();
	TypeCheck test_me = new TypeCheck();

	//Missing an argument
	holy_goal.accept(sym_check);
	holy_goal.accept(test_me,1);
	/*

	return test_me.visit(holy_goal,argu)
	*/
	System.out.println(test_me)


	//Typecheck > randomfile.Java
	//Have a parser take that object and read in the files.
	//Unleash the typecheck

}
/**
* f0 - MainClass()
f1 - (typedeclaration_choice) *
f2 - EOF
*/
public String visit(Goal n, Int argu){

	String str_1;
	String str_2;
	String true_value = "TRUE";
	Vector<String> class_title;

	str_1 = n.f0.accept(this, argu);
	class_title.add(current_sym_table.class_name_id);

	str_2 = n.f1.accept(this, argu);

	//Check for distinct
	Vector<Node> temp_nodes = n.f1.nodes;
	Iterator temp_itr = temp_nodes.iterator();
	TypeDeclaration temp_type;
	ClassDeclaration temp_class_declare;
	ClassExtendsDeclaration temp_ext_class_declare;
	while(temp_itr.hasNext()){
		temp_type = temp_itr.next();
		if(temp_type.f0.which == 0){
			temp_class_declare = temp_type.f0.choice;
			class_title.add(temp_class_declare.f1.id_value);
		}
		temp_type.f0.choice
	}


	class_title.add(n.f1)
	n.f2.accept(this, argu);
	if(str_1.equals(true_value) && str_2.equals(true_value)){
		return "TRUE";
	}else{
		return "FALSE";
	}
}

/**
* f0 -> "class"
* f1 -> Identifier()
* f2 -> "{"
* f3 -> "public"
* f4 -> "static"
* f5 -> "void"
* f6 -> "main"
* f7 -> "("
* f8 -> "String"
* f9 -> "["
* f10 -> "]"
* f11 -> Identifier()
* f12 -> ")"
* f13 -> "{"
* f14 -> ( VarDeclaration() )*
* f15 -> ( Statement() )*
* f16 -> "}"
* f17 -> "}"
*/
public String visit(MainClass n, Int argu){
	String _ret;
	String class_name;

	n.f0.accept(this, argu);

	boolean class_found = false;
	class_name = n.f1.accept(this, argu));
	Iterator _it = current_class_sym.iterator();
	while(_it.hasNext()){
		current_sym_table = _it.next();
		if(current_sym_table.class_name_id.equals(class_name)){
			class_found = true;
			break;
		}
	}

	if(!class_found){
		_ret = "FALSE";
		return _ret;
	}

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
	_ret = n.f15.accept(this, argu);
	n.f16.accept(this, argu);
	n.f17.accept(this, argu);
	return _ret;

}

/**
* f0 -> ClassDeclaration()
*       | ClassExtendsDeclaration()
*/
public String visit(TypeDeclaration n, Int argu){
	String _ret;
	_ret = n.f0.accept(this, argu);
	return _ret;
}

/**
* f0 -> "class"
* f1 -> Identifier()
* f2 -> "{"
* f3 -> ( VarDeclaration() )*
* f4 -> ( MethodDeclaration() )*
* f5 -> "}"
*/
public String visit(ClassDeclaration n, Int argu){
	String _ret;
	String class_name;

	n.f0.accept(this, argu);
	boolean class_found = false;

	class_name = n.f1.accept(this, argu);
	Iterator _it = current_class_sym.iterator();
	while(_it.hasNext()){
		current_sym_table = _it.next();
		if(current_sym_table.class_name_id.equals(class_name)){
			class_found = true;
			break;
		}
	}
	if(!class_found){
		_ret = "FALSE";
		return _ret;
	}
	n.f2.accept(this, argu);
	n.f3.accept(this, argu);
	_ret = n.f4.accept(this, argu);
	n.f5.accept(this, argu);

	return _ret;
}

/**
* f0 -> "class"
* f1 -> Identifier()
* f2 -> "extends"
* f3 -> Identifier()
* f4 -> "{"
* f5 -> ( VarDeclaration() )*
* f6 -> ( MethodDeclaration() )*
* f7 -> "}"
*/
public String visit(ClassExtendsDeclaration n, Int argu){
	String _ret = "FALSE";
	String current_class_name;
	String extending_class_name;
	String method_id_name;
	boolean class_found = false;
	String check_method;
	//Test for F6
	n.f0.accept(this, argu);
	current_class_name = n.f1.accept(this, argu);
	Iterator _it = current_class_sym.iterator();
	while(_it.hasNext()){
		current_sym_table = _it.next();
		if(current_sym_table.class_name_id.equals(current_class_name)){
			class_found = true;
			break;
		}
	}
	if(!class_found){
		_ret = "FALSE";
		return _ret;
	}
	n.f2.accept(this, argu);
	MethodDeclaration random_method;
	extending_class_name = n.f3.accept(this, argu);
	n.f4.accept(this, argu);
	n.f5.accept(this, argu);
	check_method = n.f6.accept(this, argu);
	method_id_name = Helper_Functions.get_methodname(n.f6.elementAt(0));
	n.f7.accept(this, argu);
	if(noOverloading(current_class_name,extending_class_name,method_id_name) && check_method.equals("TRUE")){
		_ret = "TRUE";
	}
	return _ret;
}
/**
* f0 -> Type()
* f1 -> Identifier()
* f2 -> ";"
*/
public String visit(VarDeclaration n, Int argu) {
	String _ret = "FALSE";
	boolean check_1 = false;
	boolean check_2 = false;
	n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	n.f2.accept(this, argu);

	return _ret;
}
/**
* f0 -> "public"
* f1 -> Type()
* f2 -> Identifier()
* f3 -> "("
* f4 -> ( FormalParameterList() )?
* f5 -> ")"
* f6 -> "{"
* f7 -> ( VarDeclaration() )*
* f8 -> ( Statement() )*
* f9 -> "return"
* f10 -> Expression()
* f11 -> ";"
* f12 -> "}"
*/
public String visit(MethodDeclaration n, Int argu) {
	String _ret = "FALSE";
	String exp_id;
	String method_id;
	String statement_id;
	//Check for the statement which is f8
	//Check for the type - f1 and expression - f10
	n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	method_id = n.f2.accept(this, argu);
	n.f3.accept(this, argu);
	n.f4.accept(this, argu);
	n.f5.accept(this, argu);
	n.f6.accept(this, argu);
	n.f7.accept(this, argu);
	statement_id = n.f8.accept(this, argu);
	n.f9.accept(this, argu);
	exp_id = n.f10.accept(this, argu);
	String exp_type = current_sym_table.fields(exp_id);
	String method_type = current_sym_table.fields(method_id);
	if(statement_id.equals("TRUE") && method_type.equals(exp_type)){
		_ret = "TRUE";
	}
	n.f11.accept(this, argu);
	n.f12.accept(this, argu);

	return _ret;
}

/**
* f0 -> FormalParameter()
* f1 -> ( FormalParameterRest() )*
*/
public String visit(FormalParameterList n, Int argu) {
	String _ret = "FALSE";
	boolean check_first = false;
	boolean check_second = false;
	n.f0.accept(this, argu);
	n.f1.accept(this, argu);

	return _ret;
}
/**
* f0 -> Type()
* f1 -> Identifier()
*/
public String visit(FormalParameter n, Int argu) {
	String _ret = "FALSE";
	boolean check_first = false;
	boolean check_second = false;
	n.f0.accept(this, argu);
	n.f1.accept(this, argu);


	return _ret;
}
/**
* f0 -> ","
* f1 -> FormalParameter()
*/
public String visit(FormalParameterRest n, Int argu) {
	String _ret = "FALSE";
	boolean check_first = false;
	n.f0.accept(this, argu);
	n.f1.accept(this, argu);

	return _ret;
}
/**
* f0 -> ArrayType()
*       | BooleanType()
*       | IntegerType()
*       | Identifier()
*/
public String visit(Type n, Int argu) {
	String _ret = "FALSE";
	boolean check_first = false;
	n.f0.accept(this, argu);

	return _ret;
}

/**
* f0 -> "int"
* f1 -> "["
* f2 -> "]"
*/
public String visit(ArrayType n, Int argu) {
	String _ret = "FALSE";
	boolean check_first = false;


	n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	n.f2.accept(this, argu);

	return _ret;
}

/**
* f0 -> "boolean"
*/
public String visit(BooleanType n, Int argu) {
	String _ret = "FALSE";
	boolean check_first = false;

	n.f0.accept(this, argu);

	return _ret;
}

/**
* f0 -> "int"
*/
public String visit(IntegerType n, Int argu) {
	String _ret = "FALSE";
	boolean check_first = false;
	n.f0.accept(this, argu);

	return _ret;
}

/**
* f0 -> Block()
*       | AssignmentStatement()
*       | ArrayAssignmentStatement()
*       | IfStatement()
*       | WhileStatement()
*       | PrintStatement()
*/
public String visit(Statement n, Int argu) {
	String _ret = "FALSE";
	boolean check_first = false;

	n.f0.accept(this, argu);
	return _ret;
}

/**
* f0 -> "{"
* f1 -> ( Statement() )*
* f2 -> "}"
*/
public String visit(Block n, Int argu) {
	String _ret = "FALSE";



	n.f0.accept(this, argu);
	_ret = n.f1.accept(this, argu);
	n.f2.accept(this, argu);

	return _ret;
}

/**
* f0 -> Identifier()
* f1 -> "="
* f2 -> Expression()
* f3 -> ";"
*/
public String visit(AssignmentStatement n, Int argu) {
	String _ret = "FALSE";

	String temp_id;
	String str_2;

	temp_id = n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	str_2 = n.f2.accept(this, argu);
	n.f3.accept(this, argu);

	String temp_type = current_sym_table.fields(temp_id);
	if(temp_type.equals(str_2)){
		_ret = "TRUE";
	}
	return _ret;
}

/**
* f0 -> Identifier()
* f1 -> "["
* f2 -> Expression()
* f3 -> "]"
* f4 -> "="
* f5 -> Expression()
* f6 -> ";"
*/
public String visit(ArrayAssignmentStatement n, Int argu) {
	String _ret = "FALSE";

	String string_1;
	String string_2;
	String string_3;

	string_1 = n.f0.accept(this, argu);
	String temp_type = current_sym_table.fields(string_1);
	n.f1.accept(this, argu);
	string_2 = n.f2.accept(this, argu);
	n.f3.accept(this, argu);
	n.f4.accept(this, argu);
	string_3 = n.f5.accept(this, argu);
	n.f6.accept(this, argu);

	if(string_1.equals("ARRAY") && string_2.equals("INT") && string_3.equals("INT")){
		_ret = "TRUE";
	}

	return _ret;
}

/**
* f0 -> "if"
* f1 -> "("
* f2 -> Expression()
* f3 -> ")"
* f4 -> Statement()
* f5 -> "else"
* f6 -> Statement()
*/
public String visit(IfStatement n, Int argu) {
	String _ret = "FALSE";

	String string_1;
	String string_2;
	String string_3;

	n.f0.accept(this, argu);
	n.f1.accept(this, argu);

	string_1 = n.f2.accept(this, argu);
	n.f3.accept(this, argu);
	string_2 = n.f4.accept(this, argu);
	n.f5.accept(this, argu);
	string_3 = n.f6.accept(this, argu);

	if(string_1.equals("BOOL") && string_2.equals("TRUE") && (string_3.equals("TRUE"))){
		_ret = "TRUE";
	}

	return _ret;
}

/**
* f0 -> "while"
* f1 -> "("
* f2 -> Expression()
* f3 -> ")"
* f4 -> Statement()
*/
public String visit(WhileStatement n, Int argu) {
	String _ret = "FALSE";

	String string_1;
	String string_2;
	n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	string_1 = n.f2.accept(this, argu);
	n.f3.accept(this, argu);
	string_2 = n.f4.accept(this, argu);

	if(string_1.equals("BOOL") && string_2.equals("TRUE")){
		_ret = "TRUE";
	}


	return _ret;
}

/**
* f0 -> "System.out.println"
* f1 -> "("
* f2 -> Expression()
* f3 -> ")"
* f4 -> ";"
*/
public String visit(PrintStatement n, Int argu) {
	String _ret = "FALSE";

	String string_1;


	n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	string_1 = n.f2.accept(this, argu);
	n.f3.accept(this, argu);
	n.f4.accept(this, argu);
	if(string_1.equals("INT")){
		_ret = "TRUE";
	}



	return _ret;
}

/**
* f0 -> AndExpression()
*       | CompareExpression()
*       | PlusExpression()
*       | MinusExpression()
*       | TimesExpression()
*       | ArrayLookup()
*       | ArrayLength()
*       | MessageSend()
*       | PrimaryExpression()
*/
public String visit(Expression n, Int argu) {
	String _ret;

	 _ret = n.f0.accept(this, argu);

	return _ret;
}

/**
* f0 -> PrimaryExpression()
* f1 -> "&&"
* f2 -> PrimaryExpression()
*/
public String visit(AndExpression n, Int argu) {
	String _ret = "FALSE";
	String check_1;
	String check_2;

	// RETURNS THE CHOICE NUMBER
	check_1 = n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	check_2 = n.f2.accept(this, argu);

	if(check_1.equals("BOOL") && (check_2.equals("BOOL"))){
		_ret = "BOOL";
	}



	return _ret;
}

/**
* f0 -> PrimaryExpression()
* f1 -> "<"
* f2 -> PrimaryExpression()
*/
public String visit(CompareExpression n, Int argu) {
	String _ret = "FALSE";

	String check_1;
	String check_2;

	check_1 = n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	check_2 = n.f2.accept(this, argu);

	if(check_1.equals("INT") && (check_2.equals("INT"))){
		_ret = "BOOL";
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
	String check_1;
	String check_2;

	check_1 = n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	check_2 = n.f2.accept(this, argu);

	if(check_1.equals("INT") && (check_2.equals("INT"))){
		_ret = "INT";
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

	String check_1;
	String check_2;

	check_1 = n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	check_2 = n.f2.accept(this, argu);

	if(check_1.equals("INT") && (check_2.equals("INT"))){
		_ret = "INT";
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

	String check_1;
	String check_2;


	check_1 = n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	check_2 = n.f2.accept(this, argu);

	if(check_1.equals("INT") && (check_2.equals("INT"))){
		_ret = "INT";
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

	String check_1;
	String check_2;

	check_1 = n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	check_2 = n.f2.accept(this, argu);
	n.f3.accept(this, argu);

	if(check_1.equals("ARRAY") && (check_2.equals("INT"))){
		_ret = "INT";
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

	String check_1;
	check_1 = n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	n.f2.accept(this, argu);

	if(check_1.equals("ARRAY")){
		_ret = "INT";
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


	//check f4
	n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	n.f2.accept(this, argu);
	n.f3.accept(this, argu);
	_ret = n.f4.accept(this, argu);
	n.f5.accept(this, argu);


	return _ret;
}

/**
* f0 -> Expression()
* f1 -> ( ExpressionRest() )*
*/
public String visit(ExpressionList n, Int argu) {
	String _ret = "FALSE";

	String string_1;
	String string_2;
	string_1 = n.f0.accept(this, argu);
	string_2 = n.f1.accept(this, argu);

	if(string_1.equals(string_2)){
		_ret = string_1;
	}

	return _ret;
}

/**
* f0 -> ","
* f1 -> Expression()
*/
public String visit(ExpressionRest n, Int argu) {
	String _ret = "FALSE";

	n.f0.accept(this, argu);
	_ret = n.f1.accept(this, argu);

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
	String _ret;

	_ret = n.f0.accept(this, argu);

	return _ret;
}

/**
* f0 -> <INTEGER_LITERAL>
*/
public String visit(IntegerLiteral n, Int argu) {
	String _ret = "INT";

	n.f0.accept(this, argu);

	return _ret;
}

/**
* f0 -> "true"
*/
public String visit(TrueLiteral n, Int argu) {
	String _ret = "BOOL";

	n.f0.accept(this, argu);

	return _ret;
}

/**
* f0 -> "false"
*/
public String visit(FalseLiteral n, Int argu) {
	String _ret = "BOOL";

	n.f0.accept(this, argu);

	return _ret;
}

/**
* f0 -> <IDENTIFIER>
*/
public String visit(Identifier n, Int argu) {
	String _ret = "ID";

	n.f0.accept(this, argu);
	return _ret;
}

/**
* f0 -> "this"
*/
public String visit(ThisExpression n, Int argu) {
	String _ret = "ETC";

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

	String _ret = "FALSE";
	String str_1;

	n.f0.accept(this, argu);
	n.f1.accept(this, argu);
	n.f2.accept(this, argu);
	str_1 = n.f3.accept(this, argu);
	n.f4.accept(this, argu);
	if(str_1.equals("INT")){
		_ret = "ARRAY";
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
	String _ret = "ID";

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
	String _ret = "FALSE";

	String string_1;
	n.f0.accept(this, argu);
	string_1 = n.f1.accept(this, argu);
	if(string_1.equals("BOOL")){
		_ret = "BOOL";
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
	_ret = n.f1.accept(this, argu);
	n.f2.accept(this, argu);
	return _ret;
}
}
