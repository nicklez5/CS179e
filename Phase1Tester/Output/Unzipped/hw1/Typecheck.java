
import syntaxtree.*;
import visitor.*;
import java.util.*;




public class Typecheck extends GJDepthFirst<String,Integer>{


	public Scope_Check current_sym_table;
	public Vector<Scope_Check> current_class_sym;
	public Helper_Functions help_me;
	public String current_method;
	public String temp_method_classname;
	public Vector<String> class_name_vec;
	public Typecheck(){
		 current_method = "";
		 temp_method_classname = "";
		 current_class_sym = new Vector<Scope_Check>();
		 help_me = new Helper_Functions();
		 current_sym_table = new Scope_Check();
		 class_name_vec = new Vector<String>();
	}


	public Typecheck(Depth_Type_Check regular_check){
		current_sym_table = regular_check.sym_table;
		current_class_sym = regular_check.class_sym;
		help_me = new Helper_Functions(current_class_sym);
		current_method = "";
		temp_method_classname = "";
		class_name_vec = new Vector<String>();
	}
	//Automatically runs
	public static void main (String[] args){
		Goal holy_goal;
		String boolean_value_goal;

		try{
			MiniJavaParser xyz = new MiniJavaParser(System.in);
			Depth_Type_Check sym_check = new Depth_Type_Check();
			holy_goal = xyz.Goal();

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
			//test_me.print_all_of_me();
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
	public void print_all_of_me(){
		Iterator table_itr = current_class_sym.iterator();
		Scope_Check temp_table33;
		while(table_itr.hasNext()){
			temp_table33 = (Scope_Check)table_itr.next();
			System.out.println("Table Name: " + temp_table33.class_name_id);
			System.out.println("Printing of variables");
			temp_table33.print_map();
			System.out.println("Printing of method vector types");
			temp_table33.print_method_vec();
			System.out.println("Printing of method return types");
			temp_table33.print_method_return_type();
		}
		System.out.println("End of print");
	}
	public String visit(Goal n, int argu){
		String ret_1 = "TRUE";
		String str_1 = "";

		String true_value = "TRUE";


		//Main Class
		str_1 = this.visit(n.f0,1);

		class_name_vec.add(current_sym_table.class_name_id);
		//help_me.print_function(class_title);

		//Type Declaration
		n.f1.accept(this, argu);

		//Check for distinct
		Vector<Node> temp_nodes = n.f1.nodes;
		Iterator temp_itr = temp_nodes.iterator();

		while(temp_itr.hasNext()){
			String type_declaration_type;
			TypeDeclaration temp_type = (TypeDeclaration)temp_itr.next();
			type_declaration_type = this.visit(temp_type,1);
			if(type_declaration_type.equals("FALSE")){
				//System.out.println("Type declaration failed");
				return "FALSE";
			}
		}
		//help_me.print_function(class_title);
		n.f2.accept(this, argu);

		if(!help_me.check_distinct(class_name_vec)){
			//System.out.println("Distinct class names failed");
			return "FALSE";
		}
		if(!str_1.equals("TRUE")){
			//System.out.println("Main class failed");
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
				//System.out.println("Distinct variable test failed");
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
			//System.out.println("The Statements has failed");
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

		String _ret = "";
		if(n.f0.which == 0){
			ClassDeclaration temp_class;
			temp_class = (ClassDeclaration)n.f0.choice;
			_ret = this.visit(temp_class,1);
			class_name_vec.add(temp_class.f1.f0.toString());
		}else if(n.f0.which == 1){
			ClassExtendsDeclaration temp_ext_class;
			temp_ext_class = (ClassExtendsDeclaration)n.f0.choice;
			_ret = this.visit(temp_ext_class,1);
			class_name_vec.add(temp_ext_class.f1.f0.toString());
		}

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

		class_name = n.f1.f0.toString();

		Iterator _it = current_class_sym.iterator();
		while(_it.hasNext()){
			current_sym_table = (Scope_Check)_it.next();
			if(current_sym_table.class_name_id.equals(class_name)){
				class_found = true;
				break;
			}
		}
		if(!class_found){
			//System.out.println("List of table names test failed ");
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
			//System.out.println("Distinct variable test failed");
			return _ret;
		}
		n.f4.accept(this, argu);
		list_method_nodes = n.f4.nodes;
		Iterator _itr2 = list_method_nodes.iterator();
		MethodDeclaration temp_method_dec;
		while(_itr2.hasNext()){
			temp_method_dec = (MethodDeclaration)_itr2.next();
			String tmp_mtd_name = temp_method_dec.f2.f0.toString();
			//System.out.println("Method_name:" + tmp_mtd_name);
			list_of_method_ids.add(tmp_mtd_name);
			String return_type_1 = this.visit(temp_method_dec,1);
			list_of_method_ids.add(return_type_1);
		}
		//Check for distinct method names and false value from the methods
		if((!help_me.check_distinct(list_of_method_ids)) || (!help_me.check_success(list_of_method_ids))) {
			//System.out.println("Failed distinct check on methods or success on Statements");
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
		//Check that expresion is the correct return type

		String _ret = "FALSE";
		String exp_id;
		String method_id;
		String statement_id;
		Vector<String> list_formal_ids = new Vector<String>();
		Vector<String> list_statement_ids = new Vector<String>();
		Vector<String> list_of_var_ids = new Vector<String>();
		Iterator _itr;
		Vector<Node> list_formal_nodes;
		Vector<Node> list_statement_nodes;
		Vector<Node> list_var_nodes;

		//Check for the statement which is f8
		//Check for the type - f1 and expression - f10
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		method_id = n.f2.f0.toString();
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);

		//LOOP through the formal parameter nodes.
		FormalParameterList temp_formal_parameter_list;
		if(n.f4.node != null){
			temp_formal_parameter_list = (FormalParameterList)n.f4.node;
			list_formal_ids.add(temp_formal_parameter_list.f0.f1.f0.toString());
			list_formal_nodes = temp_formal_parameter_list.f1.nodes;
			_itr = list_formal_nodes.iterator();
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
			//System.out.println("Statement test check failed");
			return _ret;
		}

		n.f9.accept(this, argu);
		n.f10.accept(this, argu);

		//Check for expression type and that it matches with the method.
		Expression temp_expression;
		temp_expression = n.f10;
		String exp_value = this.visit(temp_expression,1);
		if(help_me.check_if_no_type(exp_value)){
			exp_value = current_sym_table.fields(this.visit(temp_expression,1));
		}
		String method_type = current_sym_table.method_fields(method_id);
		if(!exp_value.equals(method_type)){
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
		if(_ret.equals("FALSE")){
			int xyz = n.f0.which;
			//System.out.println("Statement no: " + xyz + " has failed.");
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
		String id_1 = "";
		String id_2 = "";


		String str_2;
		String temp_id2_type = "";
		id_1 = n.f0.f0.toString();
		if(!current_sym_table.check_id(id_1)){
			return _ret;
		}
		id_1 = current_sym_table.fields(id_1);

		n.f1.accept(this, argu);
		id_2 = this.visit(n.f2,1);
		String save_last_id = id_2;
		//System.out.println("Id_2: " + id_2);
		id_2 = current_sym_table.fields(id_2);
		if(id_2 == null){
			id_2 = save_last_id;
		}
		//System.out.println("id_1: " + id_1 + " id_2 " + id_2);

		if(id_1.equals(id_2)){
			_ret = "TRUE";
		}else{
			//System.out.println(id_1 + " = " + id_2 + " has failed.");
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
		//System.out.println("Expression:" + string_1);
		String last_id_value1 = string_1;
		string_1 = current_sym_table.fields(string_1);
		if(string_1 == null){
			string_1 = last_id_value1;
		}
		string_2 = this.visit(n.f4,1);
		//System.out.println("Statement return type1:" + string_2);
		string_3 = this.visit(n.f6,1);
		//System.out.println("Statement return type2:" + string_3);
		if(string_1.equals("BOOL") && string_2.equals("TRUE") && (string_3.equals("TRUE"))){
			_ret = "TRUE";
		}else{
			//System.out.println("Failed values: str_1: " + string_1 + " str2: " + string_2 + " str3: " + string_3);
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

		String string_1 = "";
		String string_2 = "";

		string_1 = this.visit(n.f2,1);
		//System.out.println("While id_1: " + string_1);
		String save_last_id = string_1;
		string_1 = current_sym_table.fields(string_1);
		if(string_1 == null){
			string_1 = save_last_id;
		}
		//System.out.println("Converted while id_1: " + string_1);
		string_2 = this.visit(n.f4,1);
		//System.out.println("While id_2: " + string_2);
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
		String last_id_value = string_1;
		string_1 = current_sym_table.fields(string_1);
		if(string_1 == null){
			string_1 = last_id_value;
		}
		//System.out.println("Print value type:" + string_1);
		if(string_1.equals("INT") || string_1.equals("TRUE")){
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
		//System.out.println("And exp_1: " + check_1);
		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);
		//System.out.println("And exp_2: " + check_2);

		if(check_1.equals("BOOL") && (check_2.equals("BOOL"))){
			_ret = "BOOL";
		}else{
			//System.out.println("AND FAILED");
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
		//System.out.println("Check_1: " + check_1);

		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);
		String last_right_id_value = check_2;
		//System.out.println("Check_2: " + check_2);
		String left_id_type = current_sym_table.fields(check_1);
		String right_id_type = current_sym_table.fields(check_2);
		if(right_id_type == null){
			right_id_type = last_right_id_value;
		}
		//System.out.println("Left ID type: " + left_id_type + " Right ID type: " + right_id_type);
		if(left_id_type.equals("INT") && (right_id_type.equals("INT"))){
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
		//System.out.println("Check_1_value: " + check_1);
		if(help_me.check_if_no_type(check_1)){
			check_1 = current_sym_table.fields(check_1);
		}
		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);
		if(help_me.check_if_no_type(check_2)){
			check_2 = current_sym_table.fields(check_2);
		}
		//System.out.println("Check_1_type: " + check_1);
		//System.out.println("Check_2_type: " + check_2);
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
		if(help_me.check_if_no_type(check_1)){
			check_1 = current_sym_table.fields(check_1);
		}
		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);
		if(help_me.check_if_no_type(check_2)){
			check_2 = current_sym_table.fields(check_2);
		}
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
		if(help_me.check_if_no_type(check_1)){
			check_1 = current_sym_table.fields(check_1);
		}
		n.f1.accept(this, argu);
		check_2 = this.visit(n.f2,1);
		if(help_me.check_if_no_type(check_2)){
			check_2 = current_sym_table.fields(check_2);
		}

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
		Vector<String> method_vec_return_type = new Vector<String>();
		//String class_id_name = current_sym_table.fields(this.visit(random_exp,1)); // Fac
		temp_method_classname = this.visit(random_exp,1);
		String last_id = temp_method_classname;
		temp_method_classname = current_sym_table.fields(temp_method_classname);
		if(temp_method_classname == null){
			temp_method_classname = last_id;
		}
		//System.out.println("Method Class Name: " + temp_method_classname);
		//Could be BT() or root.
		//check f4
		current_method = n.f2.f0.toString();
	    //System.out.println("Method Name: " + current_method);
		Iterator class_itr = current_class_sym.iterator();
		while(class_itr.hasNext()){
			Scope_Check temp_table = (Scope_Check)class_itr.next();
			//Check if the Scope Check class name is equivalent to expression ID aka BT
			if(temp_table.class_name_id.equals(temp_method_classname)){
				//System.out.println(temp_table.class_name_id + " matched " + temp_method_classname);
				method_return_type1 = temp_table.return_method_type(current_method);
				break;
			}else{
				String class_key = temp_table.method_fields(current_method);
				if(class_key != null){
					//System.out.println(class_key);
					//temp_table.print_method_return_type();
					method_return_type1 = temp_table.return_method_type(current_method);
					//System.out.println("Method return type: " + method_return_type1);
					break;
				}
			}
		}

		if(n.f4.node != null){
			ExpressionList random_exp_list = (ExpressionList)n.f4.node;
			_ret = this.visit(random_exp_list,1);
			if(_ret.equals("TRUE")){
				_ret = method_return_type1;
			}
		}else{
			_ret = method_return_type1;
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
		//System.out.println("Method Name: " + current_method);
		Vector<String> match_parameter_type = new Vector<String>();
		String weird_string = this.visit(n.f0,1);
		String save_last_id = weird_string;
		weird_string = current_sym_table.fields(weird_string);
		if(weird_string == null){
			weird_string = save_last_id;
		}
		match_parameter_type.add(weird_string);
		//System.out.println("Weird String: " + weird_string);

		Vector<Node> temp_nodes = n.f1.nodes;
		if(temp_nodes.size() != 0){
			Iterator _itr = temp_nodes.iterator();
			while(_itr.hasNext()){
				ExpressionRest temp_exp_rest = (ExpressionRest)_itr.next();
				match_parameter_type.add(this.visit(temp_exp_rest,1));
			}
			//System.out.println("I was here");
			if(match_parameter_type.size() != random_exp_type.size()){
				System.out.println("Vector Size too small");
				return _ret;
			}
		}

		for(int i = 0; i < random_exp_type.size(); i++){
			//System.out.println("Random exp value: " + random_exp_type.elementAt(i));
			//System.out.println("Match exp value: " + match_parameter_type.elementAt(i));
			if(!random_exp_type.elementAt(i).equals(match_parameter_type.elementAt(i))) {
				//System.out.println("It did not match");
				return _ret;
			}
		}


		_ret = "TRUE";
		//System.out.println("Matched successfully");
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
		//System.out.println("Expression Rest: " + return_type);
		String save_last_id = return_type;
		return_type = current_sym_table.fields(return_type);
		if(return_type == null){
			return_type = save_last_id;
		}
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
		//System.out.println("THIS CALLED");
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
		//System.out.println("Not expression id: " + string_1);
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
