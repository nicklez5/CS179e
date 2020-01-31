
import syntaxtree.*;
import visitor.*;
import java.util.*;




public class Typecheck extends GJDepthFirst<String,Integer>{


	public Scope_Check current_sym_table;
	public Vector<Scope_Check> current_class_sym;
	public Helper_Functions help_me;
	public String current_method;
	public String temp_method_classname;
	public Typecheck(){
		 current_method = "";

	}


	public Typecheck(Depth_Type_Check regular_check){
		current_sym_table = regular_check.sym_table;
		current_class_sym = regular_check.class_sym;
		help_me = new Helper_Functions(current_class_sym);
		current_method = "";
		temp_method_classname = "";
	}
	//Automatically runs
	public static void main (String[] args){
		Goal holy_goal;
		String boolean_value_goal;
		try{
			MiniJavaParser xyz = new MiniJavaParser(System.in);
			holy_goal = xyz.Goal();
			Depth_Type_Check sym_check = new Depth_Type_Check();

			//Missing an argument
			holy_goal.accept(sym_check);

			/*Printing all the data calculated from Depth_Type_Check

			Iterator _itr = sym_check.class_sym.iterator();
	        Scope_Check temp_table;
	        while(_itr.hasNext()){
	            temp_table = (Scope_Check)_itr.next();
	            System.out.println(temp_table.class_name_id);
	        }
			*/

			Typecheck test_me = new Typecheck(sym_check);
			holy_goal.accept(test_me,1);

			boolean_value_goal = test_me.visit(holy_goal,1);
			if(boolean_value_goal.equals("TRUE")){
				System.out.println("Program type checked successfully");
			}else{
				System.out.println("Type error");
			}
		
		} catch (ParseException e){
			System.out.println("Type error");
		}




		//Typecheck > randomfile.Java
		//Have a parser take that object and read in the files.
		//Unleash the typecheck

	}
	/**
	* f0 - MainClass()
	f1 - (typedeclaration_choice) *
	f2 - EOF
	*/
	public String visit(Goal n, int argu){
		String ret_1 = "TRUE";
		String str_1 = "";

		String true_value = "TRUE";
		Vector<String> class_title = new Vector<String>();

		//Main Class
		str_1 = this.visit(n.f0,1);

		class_title.add(current_sym_table.class_name_id);
		//help_me.print_function(class_title);

		//Type Declaration
		n.f1.accept(this, argu);

		//Check for distinct
		Vector<Node> temp_nodes = n.f1.nodes;
		Iterator temp_itr = temp_nodes.iterator();
		TypeDeclaration temp_type;
		ClassDeclaration temp_class_declare;
		ClassExtendsDeclaration temp_ext_class_declare;

		while(temp_itr.hasNext()){
			temp_type = (TypeDeclaration)temp_itr.next();
			if(temp_type.f0.which == 0){
				temp_class_declare = (ClassDeclaration)temp_type.f0.choice;
				class_title.add(temp_class_declare.f1.id_value);
			}else if(temp_type.f0.which == 1){
				temp_ext_class_declare = (ClassExtendsDeclaration)temp_type.f0.choice;
				class_title.add(temp_ext_class_declare.f1.id_value);
			}
		}
		//help_me.print_function(class_title);
		n.f2.accept(this, argu);
		if(!help_me.check_distinct(class_title)){
			return "FALSE";
		}
		if(!str_1.equals("TRUE")){
			return "FALSE";
		}
		return ret_1;

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

	public String visit(MainClass n, int argu){
		String _ret = "FALSE";
		String class_name;


		n.f0.accept(this, argu);

		//Check for the distinct in the VarDeclaration
		Vector<String> temp_variables = new Vector<String>();
		Vector<String> temp_statement_variables = new Vector<String>();
		Vector<Node> temp_node_var;

		boolean class_found = false;
		n.f1.accept(this, argu);
		class_name = n.f1.f0.toString();

		Iterator _it = current_class_sym.iterator();
		while(_it.hasNext()){
			current_sym_table = (Scope_Check)_it.next();
			//System.out.println(current_sym_table.class_name_id);
			if(current_sym_table.class_name_id.equals(class_name)){
				class_found = true;
				break;
			}
		}

		if(!class_found){
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

		//Check for distinct ness
		temp_node_var = n.f14.nodes;
		Iterator _itr = temp_node_var.iterator();
		VarDeclaration temp_random_var;
		while(_itr.hasNext()){
			temp_random_var = (VarDeclaration)_itr.next();
			temp_variables.add(temp_random_var.f1.f0.toString());
		}
		//If all failed return fail
		if(!temp_variables.isEmpty()){
			if(!help_me.check_distinct(temp_variables)){
				return _ret;
			}
		}

		//Check for success in Statements
		n.f15.accept(this, argu);
		temp_node_var = n.f15.nodes;
		_itr = temp_node_var.iterator();
		Statement temp_statement;
		while(_itr.hasNext()){
			temp_statement = (Statement)_itr.next();
			temp_statement_variables.add(this.visit(temp_statement,1));
		}
		if(!help_me.check_success(temp_statement_variables)){
			System.out.println("I died here");
			return _ret;
		}

		_ret = "TRUE";
		n.f16.accept(this, argu);
		n.f17.accept(this, argu);
		return _ret;

	}

	/**
	* f0 -> ClassDeclaration()
	*       | ClassExtendsDeclaration()
	*/
	public String visit(TypeDeclaration n, int argu){
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
	public String visit(ClassDeclaration n, int argu){
		String _ret = "FALSE";
		String class_name;
		Vector<String> list_of_var_ids = new Vector<String>();
		Vector<String> list_of_method_ids = new Vector<String>();

		Vector<Node> list_var_nodes;
		Vector<Node> list_method_nodes;
		n.f0.accept(this, argu);





		boolean class_found = false;

		class_name = n.f1.accept(this, argu);
		Iterator _it = current_class_sym.iterator();
		while(_it.hasNext()){
			current_sym_table = (Scope_Check)_it.next();
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
		list_var_nodes = n.f3.nodes;
		Iterator _itr = list_var_nodes.iterator();
		VarDeclaration temp_var_dec;
		while(_itr.hasNext()){
			temp_var_dec = (VarDeclaration)_itr.next();
			list_of_var_ids.add(temp_var_dec.f1.f0.toString());
		}
		if(!help_me.check_distinct(list_of_var_ids)){
			return _ret;
		}
		n.f4.accept(this, argu);
		list_method_nodes = n.f4.nodes;
		Iterator _itr2 = list_method_nodes.iterator();
		MethodDeclaration temp_method_dec;
		while(_itr2.hasNext()){
			temp_method_dec = (MethodDeclaration)_itr2.next();
			list_of_method_ids.add(temp_method_dec.f2.f0.toString());
			list_of_method_ids.add(this.visit(temp_method_dec,1));
		}
		//Check for distinct method names and false value from the methods
		if((!help_me.check_distinct(list_of_method_ids)) || (!help_me.check_success(list_of_method_ids))) {
			return _ret;
		}
		n.f5.accept(this, argu);
		_ret = "TRUE";

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
	public String visit(ClassExtendsDeclaration n, int argu){
		String _ret = "FALSE";

		Vector<String> list_of_var_ids = new Vector<String>();
		Vector<String> list_of_methods_ids = new Vector<String>();

		Vector<Node> list_var_nodes;
		Vector<Node> list_method_nodes;

		String current_class_name;
		String extending_class_name;
		String method_id_name;
		boolean class_found = false;


		//Test for F6
		n.f0.accept(this, argu);

		//Getting the class name and setting the current table to that scope
		current_class_name = n.f1.accept(this, argu);
		Iterator _it = current_class_sym.iterator();
		while(_it.hasNext()){
			current_sym_table = (Scope_Check)_it.next();
			if(current_sym_table.class_name_id.equals(current_class_name)){
				class_found = true;
				break;
			}
		}
		//If there is no class of that name found. Remove it.
		if(!class_found){
			_ret = "FALSE";
			return _ret;
		}
		n.f2.accept(this, argu);
		MethodDeclaration random_method;
		extending_class_name = n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		n.f5.accept(this, argu);

		//Check for Similarity of the variable declaration
		list_var_nodes = n.f5.nodes;
		Iterator _itr = list_var_nodes.iterator();
		VarDeclaration temp_var_dec;
		while(_itr.hasNext()){
			temp_var_dec = (VarDeclaration)_itr.next();
			list_of_var_ids.add(temp_var_dec.f1.f0.toString());
		}
		if(!help_me.check_distinct(list_of_var_ids)){
			return _ret;
		}


		//Check for similarity of the method name.
		//LOOP over the methods again and get the values of noOverloading
		list_method_nodes = n.f6.nodes;
		Iterator _itr2 = list_method_nodes.iterator();
		MethodDeclaration temp_method;
		while(_itr2.hasNext()){
			String temp_method_name;
			temp_method = (MethodDeclaration)_itr2.next();
			temp_method_name = help_me.get_methodname(temp_method);
			list_of_methods_ids.add(temp_method_name);
			if(!help_me.noOverloading(current_class_name,extending_class_name,temp_method_name)){
				list_of_methods_ids.add("FALSE");
			}
			list_of_methods_ids.add(this.visit(temp_method,1));
		}
		if((!help_me.check_distinct(list_of_methods_ids)) || (!help_me.check_success(list_of_methods_ids)))   {
			return _ret;
		}



		n.f6.accept(this, argu);
		n.f7.accept(this, argu);
		_ret = "TRUE";
		return _ret;
	}
	/**
	* f0 -> Type()
	* f1 -> Identifier()
	* f2 -> ";"
	*/
	public String visit(VarDeclaration n, int argu) {
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
	public String visit(MethodDeclaration n, int argu) {

		//Check for Formal Parameter List type. Distinct
		//CHeck on distinct id's on var Declaration
		//Get the fields
		//Check for the statement trueness
		//Check that expresion is the correct return type.
		String _ret = "FALSE";
		String exp_id;
		String method_id;
		String statement_id;
		Vector<String> list_formal_ids = new Vector<String>();
		Vector<String> list_statement_ids = new Vector<String>();
		Vector<String> list_of_var_ids = new Vector<String>();

		Vector<Node> list_formal_nodes;
		Vector<Node> list_statement_nodes;
		Vector<Node> list_var_nodes;

		//Check for the statement which is f8
		//Check for the type - f1 and expression - f10
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		method_id = n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);

		//LOOP through the formal parameter nodes.
		FormalParameterList temp_formal_parameter_list;
		temp_formal_parameter_list = (FormalParameterList)n.f4.node;
		list_formal_ids.add(temp_formal_parameter_list.f0.f1.f0.toString());
		list_formal_nodes = temp_formal_parameter_list.f1.nodes;
		Iterator _itr = list_formal_nodes.iterator();
		FormalParameterRest temp_formal_parameter_rest;
		while(_itr.hasNext()){
			FormalParameter temp_formal_parameter_etc;
			temp_formal_parameter_rest = (FormalParameterRest)_itr.next();
			temp_formal_parameter_etc = temp_formal_parameter_rest.f1;
			list_formal_ids.add(temp_formal_parameter_etc.f1.f0.toString());
		}
		if(!help_me.check_distinct(list_formal_ids)){
			return _ret;
		}

		n.f5.accept(this, argu);
		n.f6.accept(this, argu);
		n.f7.accept(this, argu);

		//Check for distinct variable id's
		list_var_nodes = n.f7.nodes;
		_itr = list_var_nodes.iterator();
		VarDeclaration temp_var_dec;
		while(_itr.hasNext()){
			temp_var_dec = (VarDeclaration)_itr.next();
			list_of_var_ids.add(temp_var_dec.f1.f0.toString());
		}
		if(!help_me.check_distinct(list_of_var_ids)){
			return _ret;
		}

		n.f8.accept(this, argu);

		//Checking the validity of the statements.
		list_statement_nodes = n.f8.nodes;
		_itr = list_statement_nodes.iterator();
		Statement temp_statement;
		while(_itr.hasNext()){
			temp_statement = (Statement)_itr.next();
			list_statement_ids.add(this.visit(temp_statement,1));
		}
		if(!help_me.check_success(list_statement_ids)){
			return _ret;
		}

		n.f9.accept(this, argu);
		n.f10.accept(this, argu);

		//Check for expression type and that it matches with the method.
		Expression temp_expression;
		temp_expression = n.f10;
		String exp_type = this.visit(temp_expression,1);
		String method_type = current_sym_table.fields(method_id);
		if(!exp_type.equals(method_type)){
			return _ret;
		}
		_ret = "TRUE";
		n.f11.accept(this, argu);
		n.f12.accept(this, argu);

		return _ret;
	}

	/**
	* f0 -> FormalParameter()
	* f1 -> ( FormalParameterRest() )*
	*/
	public String visit(FormalParameterList n, int argu) {
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
	public String visit(FormalParameter n, int argu) {
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
	public String visit(FormalParameterRest n, int argu) {
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
	public String visit(Type n, int argu) {
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
	public String visit(ArrayType n, int argu) {
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
	public String visit(BooleanType n, int argu) {
		String _ret = "FALSE";
		boolean check_first = false;

		n.f0.accept(this, argu);

		return _ret;
	}

	/**
	* f0 -> "int"
	*/
	public String visit(IntegerType n, int argu) {
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
	public String visit(Statement n, int argu) {
		String _ret = "";
		if(n.f0.which == 0){
			Block temp_blk;
			temp_blk = (Block)n.f0.choice;
			_ret = this.visit(temp_blk,1);
		}else if(n.f0.which == 1){
			AssignmentStatement temp_assign;
			temp_assign = (AssignmentStatement)n.f0.choice;
			_ret = this.visit(temp_assign,1);
		}else if(n.f0.which == 2){
			ArrayAssignmentStatement temp_arry_assign;
			temp_arry_assign = (ArrayAssignmentStatement)n.f0.choice;
			_ret = this.visit(temp_arry_assign,1);
		}else if(n.f0.which == 3){
			IfStatement temp_if;
			temp_if = (IfStatement)n.f0.choice;
			_ret = this.visit(temp_if,1);
		}else if(n.f0.which == 4){
			WhileStatement temp_while;
			temp_while = (WhileStatement)n.f0.choice;
			_ret = this.visit(temp_while,1);
		}else if(n.f0.which == 5){
			PrintStatement temp_print;
			temp_print = (PrintStatement)n.f0.choice;
			_ret = this.visit(temp_print,1);
		}
		return _ret;
	}

	/**
	* f0 -> "{"
	* f1 -> ( Statement() )*
	* f2 -> "}"
	*/
	public String visit(Block n, int argu) {
		String _ret = "";
		Vector<Node> temp_nodes;
		temp_nodes = n.f1.nodes;
		Iterator _itr = temp_nodes.iterator();
		while(_itr.hasNext()){
			Statement temp_statement = (Statement)_itr.next();
			_ret = this.visit(temp_statement,1);
			if(_ret.equals("FALSE")){
				return _ret;
			}
		}

		return _ret;
	}

	/**
	* f0 -> Identifier()
	* f1 -> "="
	* f2 -> Expression()
	* f3 -> ";"
	*/
	public String visit(AssignmentStatement n, int argu) {
		String _ret = "FALSE";

		String temp_id;
		String str_2;

		temp_id = n.f0.f0.toString();
		n.f1.accept(this, argu);

		str_2 = this.visit(n.f2,1);
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
	public String visit(ArrayAssignmentStatement n, int argu) {
		String _ret = "FALSE";

		String string_1;
		String string_2;
		String string_3;

		string_1 = n.f0.f0.toString();
		String temp_type = current_sym_table.fields(string_1);
		n.f1.accept(this, argu);
		string_2 = this.visit(n.f2,1);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		string_3 = this.visit(n.f5,1);
		n.f6.accept(this, argu);

		if(temp_type.equals("ARRAY") && string_2.equals("INT") && string_3.equals("INT")){
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
	public String visit(IfStatement n, int argu) {
		String _ret = "FALSE";

		String string_1;
		String string_2;
		String string_3;


		string_1 = this.visit(n.f2,1);

		string_2 = this.visit(n.f4,1);

		string_3 = this.visit(n.f6,1);

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
	public String visit(WhileStatement n, int argu) {
		String _ret = "FALSE";

		String string_1;
		String string_2;

		string_1 = this.visit(n.f2,1);

		string_2 = this.visit(n.f4,1);

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
	public String visit(PrintStatement n, int argu) {
		String _ret = "FALSE";

		String string_1;


		string_1 = this.visit(n.f2,1);
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
	public String visit(Expression n, int argu) {
		String _ret = "";
		if(n.f0.which == 0){
			AndExpression temp_and = (AndExpression)n.f0.choice;
			_ret = this.visit(temp_and,1);
		}else if(n.f0.which == 1){
			CompareExpression temp_cmp = (CompareExpression)n.f0.choice;
			_ret = this.visit(temp_cmp,1);
		}else if(n.f0.which == 2){
			PlusExpression temp_plus = (PlusExpression)n.f0.choice;
			_ret = this.visit(temp_plus,1);
		}else if(n.f0.which == 3){
			MinusExpression temp_minus = (MinusExpression)n.f0.choice;
			_ret = this.visit(temp_minus,1);
		}else if(n.f0.which == 4){
			TimesExpression temp_times = (TimesExpression)n.f0.choice;
			_ret = this.visit(temp_times,1);
		}else if(n.f0.which == 5){
			ArrayLookup temp_array_look = (ArrayLookup)n.f0.choice;
			_ret = this.visit(temp_array_look,1);
		}else if(n.f0.which == 6){
			ArrayLength temp_array_length = (ArrayLength)n.f0.choice;
			_ret = this.visit(temp_array_length,1);
		}else if(n.f0.which == 7){
			MessageSend temp_msg = (MessageSend)n.f0.choice;
			_ret = this.visit(temp_msg,1);
		}else if(n.f0.which == 8){
			PrimaryExpression temp_prim_exp = (PrimaryExpression)n.f0.choice;
			_ret = this.visit(temp_prim_exp,1);
		}

		return _ret;
	}

	/**
	* f0 -> PrimaryExpression()
	* f1 -> "&&"
	* f2 -> PrimaryExpression()
	*/
	public String visit(AndExpression n, int argu) {
		String _ret = "FALSE";
		String check_1;
		String check_2;

		// RETURNS THE CHOICE NUMBER
		check_1 = this.visit(n.f0,1);
		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);

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
	public String visit(CompareExpression n, int argu) {
		String _ret = "FALSE";

		String check_1;
		String check_2;

		check_1 = this.visit(n.f0,1);
		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);

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
	public String visit(PlusExpression n, int argu) {
		String _ret = "FALSE";
		String check_1;
		String check_2;

		check_1 = this.visit(n.f0,1);
		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);

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
	public String visit(MinusExpression n, int argu) {
		String _ret = "FALSE";

		String check_1;
		String check_2;

		check_1 = this.visit(n.f0,1);
		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);

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
	public String visit(TimesExpression n, int argu) {
		String _ret = "FALSE";

		String check_1;
		String check_2;


		check_1 = this.visit(n.f0,1);
		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);

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
	public String visit(ArrayLookup n, int argu) {
		String _ret = "FALSE";

		String check_1;
		String check_2;

		check_1 = this.visit(n.f0,1);
		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);

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
	public String visit(ArrayLength n, int argu) {
		String _ret = "FALSE";

		String check_1;
		check_1 = this.visit(n.f0,1);
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
	new Fac(). computeFac(10)
	*/
	public String visit(MessageSend n, int argu) {
		String _ret = "FALSE";
		String method_return_type1 = "";
		PrimaryExpression random_exp = n.f0;

		String class_id_name = this.visit(random_exp,1); // Fac

		temp_method_classname = class_id_name;
		//check f4
		current_method = n.f2.f0.toString();

		ExpressionList random_exp_list = (ExpressionList)n.f4.node;
		_ret = this.visit(random_exp_list,1);
		if(_ret.equals("TRUE")){
			Iterator _itr = current_class_sym.iterator();
			while(_itr.hasNext()){
				Scope_Check temp_scope_chk = (Scope_Check)_itr.next();
				if(temp_scope_chk.class_name_id.equals(class_id_name)){
					method_return_type1 = temp_scope_chk.return_method_type(current_method);

					return method_return_type1;
				}
			}
		}
		n.f5.accept(this, argu);


		return _ret;
	}

	/**
	* f0 -> Expression()
	* f1 -> ( ExpressionRest() )*
	*/
	public String visit(ExpressionList n, int argu) {
		String _ret = "FALSE";
		Vector<String> random_exp_type = help_me.method_type(temp_method_classname,current_method);

		Vector<String> match_parameter_type = new Vector<String>();
		match_parameter_type.add(this.visit(n.f0,1));

		Vector<Node> temp_nodes = n.f1.nodes;
		if(temp_nodes.size() != 0){
			Iterator _itr = temp_nodes.iterator();
			while(_itr.hasNext()){
				ExpressionRest temp_exp_rest = (ExpressionRest)_itr.next();
				match_parameter_type.add(this.visit(temp_exp_rest,1));
			}
			System.out.println("I was here");
			if(match_parameter_type.size() != random_exp_type.size()){
				System.out.println("Vector Size too small");
				return _ret;
			}
			for(int i = 0; i < random_exp_type.size(); i++){
				if(random_exp_type.elementAt(i).equals(match_parameter_type.elementAt(i))) {
					System.out.println("It did not match");
					return _ret;
				}
			}
		}else{
			if(random_exp_type.elementAt(0).equals(match_parameter_type.elementAt(0))){
				_ret = "TRUE";
				return _ret;
			}
		}


		_ret = "TRUE";
		return _ret;
	}

	/**
	* f0 -> ","
	* f1 -> Expression()
	*/
	public String visit(ExpressionRest n, int argu) {
		String return_type;

		n.f0.accept(this, argu);
		return_type = this.visit(n.f1,1);

		return return_type;
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
	public String visit(PrimaryExpression n, int argu) {
		String _ret = "";
		if(n.f0.which == 0){
			 IntegerLiteral tmp_int = (IntegerLiteral)n.f0.choice;
			 return this.visit(tmp_int,1);
		}else if(n.f0.which == 1){
			 TrueLiteral tmp_true = (TrueLiteral)n.f0.choice;
			 return this.visit(tmp_true,1);
		}else if(n.f0.which == 2){
			FalseLiteral tmp_false = (FalseLiteral)n.f0.choice;
			return this.visit(tmp_false,1);
		}else if(n.f0.which == 3){
			Identifier tmp_id = (Identifier)n.f0.choice;
			return this.visit(tmp_id,1);
		}else if(n.f0.which == 4){
			ThisExpression tmp_this = (ThisExpression)n.f0.choice;
			return this.visit(tmp_this,1);
		}else if(n.f0.which == 5){
			ArrayAllocationExpression tmp_array_all = (ArrayAllocationExpression)n.f0.choice;
			return this.visit(tmp_array_all,1);
		}else if(n.f0.which == 6){
			AllocationExpression tmp_allocate = (AllocationExpression)n.f0.choice;
			return this.visit(tmp_allocate,1);
		}else if(n.f0.which == 7){
			NotExpression tmp_not = (NotExpression)n.f0.choice;
			return this.visit(tmp_not,1);
		}else if(n.f0.which == 8){
			BracketExpression tmp_bracket = (BracketExpression)n.f0.choice;
			return this.visit(tmp_bracket,1);
		}


		return _ret;
	}

	/**
	* f0 -> <INTEGER_LITERAL>
	*/
	public String visit(IntegerLiteral n, int argu) {
		String _ret = "INT";

		n.f0.accept(this, argu);

		return _ret;
	}

	/**
	* f0 -> "true"
	*/
	public String visit(TrueLiteral n, int argu) {
		String _ret = "BOOL";

		n.f0.accept(this, argu);

		return _ret;
	}

	/**
	* f0 -> "false"
	*/
	public String visit(FalseLiteral n, int argu) {
		String _ret = "BOOL";

		n.f0.accept(this, argu);

		return _ret;
	}

	/**
	* f0 -> <IDENTIFIER>
	*/
	public String visit(Identifier n, int argu) {
		String _ret = "";
		String str_1 = n.f0.toString();

		if(current_sym_table.check_id(str_1)){
			_ret = n.f0.toString();
		}
		return _ret;
	}

	/**
	* f0 -> "this"
	*/
	public String visit(ThisExpression n, int argu) {
		String _ret = current_sym_table.class_name_id;

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
	public String visit(ArrayAllocationExpression n, int argu) {

		String _ret = "FALSE";
		String str_1;
		str_1 = this.visit(n.f3,1);
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
	public String visit(AllocationExpression n, int argu) {
		String _ret = "ID";

		n.f0.accept(this, argu);
		_ret = n.f1.f0.toString();
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);

		return _ret;
	}

	/**
	* f0 -> "!"
	* f1 -> Expression()
	*/
	public String visit(NotExpression n, int argu) {
		String _ret = "FALSE";

		String string_1;
		n.f0.accept(this, argu);
		string_1 = this.visit(n.f1,1);
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
	public String visit(BracketExpression n, int argu) {
		String _ret = "FALSE";
		n.f0.accept(this, argu);

		_ret = this.visit(n.f1,1);
		n.f2.accept(this, argu);

		return _ret;
	}
}
