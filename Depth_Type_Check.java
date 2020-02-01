
import syntaxtree.*;
import visitor.*;
import java.util.*;

public class Depth_Type_Check extends DepthFirstVisitor  {

	public Scope_Check sym_table;
	public int primary_exp_number;
	public boolean store_assign;
	public String empty_id;
	public String current_type;
	public String current_method_name;
	public Helper_Functions helper1;
	public Vector<Scope_Check> class_sym;
	public Depth_Type_Check(){
		primary_exp_number = 0;
		store_assign = false;
		empty_id = "";
		helper1 = new Helper_Functions();
		class_sym = new Vector<Scope_Check>();
		sym_table = new Scope_Check();
		current_type = "";
		current_method_name = "";
		//this.helper1 = new Helper_Functions();
	}

	/**
		* f0 - MainClass()
		  f1 - typedeclaration_choice
			f2 - EOF
		*/
	public void visit(Goal n) {
		 this.visit(n.f0);
		 Vector<Node> temp_nodes = n.f1.nodes;

		 Iterator _itr = temp_nodes.iterator();
		 while(_itr.hasNext()){

			 TypeDeclaration temp_declare = (TypeDeclaration)_itr.next();
			 this.visit(temp_declare);
		 }
		 n.f2.accept(this);

		 /*
		 _itr = class_sym.iterator();
		 Scope_Check temp_table;
		 while(_itr.hasNext()){
			 temp_table = (Scope_Check)_itr.next();
			 System.out.println(temp_table.class_name_id);
			 temp_table.print_map();
		 //The last class if there is any.
	 	 }
		 */

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
	public void visit(MainClass n) {
		 String class_name;
		 n.f0.accept(this);
		 n.f1.accept(this);
		 class_name = n.f1.id_value;
		 //System.out.println(class_name);
		 sym_table.class_name_id =  class_name;
		 class_sym.add(sym_table);
		 n.f2.accept(this);
		 n.f3.accept(this);
		 n.f4.accept(this);
		 n.f5.accept(this);
		 n.f6.accept(this);
		 n.f7.accept(this);
		 n.f8.accept(this);
		 n.f9.accept(this);
		 n.f10.accept(this);
		 n.f11.accept(this);
		 n.f12.accept(this);
		 n.f13.accept(this);
		 n.f14.accept(this);
		 n.f15.accept(this);
		 n.f16.accept(this);
		 n.f17.accept(this);
	}

	/**
	 * f0 -> ClassDeclaration()
	 *       | ClassExtendsDeclaration()
	 */
	public void visit(TypeDeclaration n) {
		Scope_Check new_scope_check = new Scope_Check();
		sym_table = new_scope_check;


		if(n.f0.which == 0){
			ClassDeclaration temp_class_declare = (ClassDeclaration)n.f0.choice;
			this.visit(temp_class_declare);
		}else{
			ClassExtendsDeclaration temp_ext_class_declare;
			temp_ext_class_declare = (ClassExtendsDeclaration)n.f0.choice;
			this.visit(temp_ext_class_declare);

		}


	}

	/**
	 * f0 -> "class"
	 * f1 -> Identifier()
	 * f2 -> "{"
	 * f3 -> ( VarDeclaration() )*
	 * f4 -> ( MethodDeclaration() )*
	 * f5 -> "}"
	 */
	public void visit(ClassDeclaration n) {
		 sym_table.class_name_id = n.f1.id_value;

		 Vector<Node> temp_nodes;
		 temp_nodes = n.f3.nodes;
		 Iterator _itr = temp_nodes.iterator();
		 while(_itr.hasNext()){
			 VarDeclaration temp_var = (VarDeclaration)_itr.next();
			 this.visit(temp_var);
		 }
		 //temp_nodes.clear();
		 temp_nodes = n.f4.nodes;
		 _itr = temp_nodes.iterator();
		 while(_itr.hasNext()){
			 MethodDeclaration temp_method = (MethodDeclaration)_itr.next();
			 this.visit(temp_method);
		 }
		 class_sym.add(sym_table);

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
	public void visit(ClassExtendsDeclaration n) {
		 sym_table.class_name_id = n.f1.id_value;

		Vector<Node> temp_nodes;
		temp_nodes = n.f5.nodes;
		Iterator _itr = temp_nodes.iterator();
		while(_itr.hasNext()){
			VarDeclaration temp_var = (VarDeclaration)_itr.next();
			this.visit(temp_var);
		}

		temp_nodes.clear();
		temp_nodes = n.f6.nodes;
		_itr = temp_nodes.iterator();
		while(_itr.hasNext()){
			MethodDeclaration temp_method = (MethodDeclaration)_itr.next();
			this.visit(temp_method);
		}
		class_sym.add(sym_table);

	}

	/**
	 * f0 -> Type()
	 * f1 -> Identifier()
	 * f2 -> ";"
	 */
	public void visit(VarDeclaration n) {
		 String id_name;
		 this.visit(n.f0);
		 id_name = n.f1.f0.toString();

		 //Check if a map contains this key
		sym_table.add_me(id_name,this.current_type);

		 n.f2.accept(this);
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
	public void visit(MethodDeclaration n) {
		 n.f0.accept(this);
		 this.visit(n.f1);
		 String method_return_type = current_type;
		 n.f2.accept(this);
		 //System.out.println(n.f2.id_value);
		 current_method_name = n.f2.id_value;
		 sym_table.add_method_type(current_method_name,method_return_type);
		 if(n.f4.node != null){
			 FormalParameterList temp_formal_parameter_list;
			 temp_formal_parameter_list = (FormalParameterList)n.f4.node;
			 this.visit(temp_formal_parameter_list);
		 }


		 n.f6.accept(this);
		 n.f7.accept(this);
		 n.f8.accept(this);
		 n.f9.accept(this);
		 n.f10.accept(this);
		 n.f11.accept(this);
		 n.f12.accept(this);
	}

	/**
	 * f0 -> FormalParameter()
	 * f1 -> ( FormalParameterRest() )*
	 */
	public void visit(FormalParameterList n) {
		 FormalParameter temp_formal = n.f0;
		 this.visit(temp_formal);

		 Vector<Node> temp_nodes;
		 temp_nodes = n.f1.nodes;

		 Iterator _itr = temp_nodes.iterator();
		 while(_itr.hasNext()){
			 FormalParameterRest temp_formal_parameter_rest = (FormalParameterRest)_itr.next();
			 this.visit(temp_formal_parameter_rest);
		 }

	}

	/**
	 * f0 -> Type()
	 * f1 -> Identifier()
	 */
	public void visit(FormalParameter n) {
		 this.visit(n.f0);
		 String var_id = n.f1.f0.toString();
		 sym_table.add_method(current_method_name, this.current_type);
		 sym_table.add_me(var_id,this.current_type);
	}

	/**
	 * f0 -> ","
	 * f1 -> FormalParameter()
	 */
	public void visit(FormalParameterRest n) {
		 n.f0.accept(this);
		 this.visit(n.f1);
	}

	/**
	 * f0 -> ArrayType()
	 *       | BooleanType()
	 *       | IntegerType()
	 *       | Identifier()
	 */
	public void visit(Type n) {
		 if(n.f0.which == 0){
			 this.current_type = "ARRAY";
		 }else if(n.f0.which == 1){
			 this.current_type = "BOOL";
		 }else if(n.f0.which == 2){
			 this.current_type = "INT";
		 }else if(n.f0.which == 3){
			 this.current_type = "ID";
		 }
	}

	/**
	 * f0 -> "int"
	 * f1 -> "["
	 * f2 -> "]"
	 */
	public void visit(ArrayType n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);
	}

	/**
	 * f0 -> "boolean"
	 */
	public void visit(BooleanType n) {
		 n.f0.accept(this);
	}

	/**
	 * f0 -> "int"
	 */
	public void visit(IntegerType n) {
		 n.f0.accept(this);
	}

	/**
	 * f0 -> Block()
	 *       | AssignmentStatement()
	 *       | ArrayAssignmentStatement()
	 *       | IfStatement()
	 *       | WhileStatement()
	 *       | PrintStatement()
	 */
	public void visit(Statement n) {
		if(n.f0.which == 0){
			Block temp_blk;
			temp_blk = (Block)n.f0.choice;
			this.visit(temp_blk);
		}else if(n.f0.which == 1){
			AssignmentStatement temp_assign;
			temp_assign = (AssignmentStatement)n.f0.choice;
			this.visit(temp_assign);
		}else if(n.f0.which == 2){
			ArrayAssignmentStatement temp_arry_assign;
			temp_arry_assign = (ArrayAssignmentStatement)n.f0.choice;
			this.visit(temp_arry_assign);
		}else if(n.f0.which == 3){
			IfStatement temp_if;
			temp_if = (IfStatement)n.f0.choice;
			this.visit(temp_if);
		}else if(n.f0.which == 4){
			WhileStatement temp_while;
			temp_while = (WhileStatement)n.f0.choice;
			this.visit(temp_while);
		}else if(n.f0.which == 5){
			PrintStatement temp_print;
			temp_print = (PrintStatement)n.f0.choice;
			this.visit(temp_print);
		}

	}

	/**
	 * f0 -> "{"
	 * f1 -> ( Statement() )*
	 * f2 -> "}"
	 */
	public void visit(Block n) {
		Vector<Node> temp_nodes;
		temp_nodes = n.f1.nodes;
		Iterator _itr = temp_nodes.iterator();
		while(_itr.hasNext()){
			Statement temp_statement = (Statement)_itr.next();
			this.visit(temp_statement);
		}
	}

	/**
	 * f0 -> Identifier()
	 * f1 -> "="
	 * f2 -> Expression()
	 * f3 -> ";"
	 */
	public void visit(AssignmentStatement n) {

		 empty_id = n.f0.id_value;

		 this.visit(n.f2);
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
	public void visit(ArrayAssignmentStatement n) {

		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);
		 n.f3.accept(this);
		 n.f4.accept(this);
		 n.f5.accept(this);
		 n.f6.accept(this);
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
	public void visit(IfStatement n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);
		 n.f3.accept(this);
		 n.f4.accept(this);
		 n.f5.accept(this);
		 n.f6.accept(this);
	}

	/**
	 * f0 -> "while"
	 * f1 -> "("
	 * f2 -> Expression()
	 * f3 -> ")"
	 * f4 -> Statement()
	 */
	public void visit(WhileStatement n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);
		 n.f3.accept(this);
		 n.f4.accept(this);
	}

	/**
	 * f0 -> "System.out.println"
	 * f1 -> "("
	 * f2 -> Expression()
	 * f3 -> ")"
	 * f4 -> ";"
	 */
	public void visit(PrintStatement n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);
		 n.f3.accept(this);
		 n.f4.accept(this);
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
	public void visit(Expression n) {

		 if(n.f0.which == 8){
			 	PrimaryExpression temp_prim_exp;
				temp_prim_exp = (PrimaryExpression)n.f0.choice;
				this.visit(temp_prim_exp);
		 }
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "&&"
	 * f2 -> PrimaryExpression()
	 */
	//BOOL TYPE
	public void visit(AndExpression n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);


	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "<"
	 * f2 -> PrimaryExpression()
	 */
	//BOOL TYPE
	public void visit(CompareExpression n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);


	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "+"
	 * f2 -> PrimaryExpression()
	 */
	//INT TYPE
	public void visit(PlusExpression n) {

		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);


	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "-"
	 * f2 -> PrimaryExpression()
	 */
	//INT TYPE
	public void visit(MinusExpression n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);

	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "*"
	 * f2 -> PrimaryExpression()
	 */
	//INT TYPE
	public void visit(TimesExpression n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);

	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "["
	 * f2 -> PrimaryExpression()
	 * f3 -> "]"
	 */
	//ARRAY TYPE
	public void visit(ArrayLookup n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);
		 n.f3.accept(this);
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "."
	 * f2 -> "length"
	 */
	//INT TYPE
	public void visit(ArrayLength n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "."
	 * f2 -> Identifier()
	 * f3 -> "("
	 * f4 -> ( ExpressionList() )?
	 * f5 -> ")"
	 */
	 // ID TYPE
	public void visit(MessageSend n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);
		 n.f3.accept(this);
		 n.f4.accept(this);
		 n.f5.accept(this);
	}

	/**
	 * f0 -> Expression()
	 * f1 -> ( ExpressionRest() )*
	 */
	public void visit(ExpressionList n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
	}

	/**
	 * f0 -> ","
	 * f1 -> Expression()
	 */
	public void visit(ExpressionRest n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
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
	public void visit(PrimaryExpression n) {

		if(n.f0.which == 6){
			AllocationExpression temp_allocate = (AllocationExpression)n.f0.choice;
			this.visit(temp_allocate);
		}
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public void visit(IntegerLiteral n) {
		 n.f0.accept(this);
	}

	/**
	 * f0 -> "true"
	 */
	public void visit(TrueLiteral n) {
		 n.f0.accept(this);
	}

	/**
	 * f0 -> "false"
	 */
	public void visit(FalseLiteral n) {
		 n.f0.accept(this);
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public void visit(Identifier n) {
		 n.f0.accept(this);
	}

	/**
	 * f0 -> "this"
	 */
	public void visit(ThisExpression n) {
		 n.f0.accept(this);
	}

	/**
	 * f0 -> "new"
	 * f1 -> "int"
	 * f2 -> "["
	 * f3 -> Expression()
	 * f4 -> "]"
	 */
	public void visit(ArrayAllocationExpression n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);
		 n.f3.accept(this);

		 n.f4.accept(this);
	}

	/**
	 * f0 -> "new"
	 * f1 -> Identifier()
	 * f2 -> "("
	 * f3 -> ")"
	 */
	public void visit(AllocationExpression n) {
		String xyz = n.f1.id_value;
		sym_table.add_me(empty_id,xyz);




	}

	/**
	 * f0 -> "!"
	 * f1 -> Expression()
	 */
	public void visit(NotExpression n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
	}

	/**
	 * f0 -> "("
	 * f1 -> Expression()
	 * f2 -> ")"
	 */
	public void visit(BracketExpression n) {
		 n.f0.accept(this);
		 n.f1.accept(this);
		 n.f2.accept(this);
	}
}
