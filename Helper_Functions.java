
import java.util.*;
import syntaxtree.*;

public class Helper_Functions{
    public Vector<Node> node_placer;
    public Vector<Scope_Check> class_tables;
    //Return the name of the class
    public Helper_Functions(){
            this.node_placer = new Vector<Node>();
            this.class_tables = new Vector<Scope_Check>();
    }
    public Helper_Functions(Vector<Scope_Check> set_me){
        node_placer = new Vector<Node>();
        class_tables = set_me;
    }
    public String get_classname(ClassDeclaration n0){
        return n0.f1.f0.toString();
    }
    //Return the name of the class extends declaration
    public String get_classname(ClassExtendsDeclaration n0){
        return n0.f1.f0.toString();
    }

    public String get_methodname(MethodDeclaration n0){
        return n0.f2.f0.toString();
    }
    public boolean check_success(Vector<String> statement_ID){
        Iterator itr = statement_ID.iterator();
        while(itr.hasNext()){
            String temp_value = (String)itr.next();
            System.out.println(temp_value);
        }
        /*
        Iterator _itr = statement_ID.iterator();
        while(_itr.hasNext()){
            String xyz = (String)_itr.next();
            System.out.println(xyz);
        }
        */

        if(statement_ID.contains("FALSE")){
            System.out.println("HOLY SHIT I FUCKED UP");
            return false;
        }
        return true;
    }
    public boolean check_distinct(Vector<String> List_ID){
        //Create a copy of the vector of nodes.

        Vector<String> temp_vec = new Vector<String>(List_ID);

        String temp_str;
        //Remove one nodes
        //Loop over the vector of nodes and check if there is a node of that kind
        //If found, break while loop and return false otherwise keep going.
        while(temp_vec.size() != 0){
            temp_str = temp_vec.lastElement();
            temp_vec.remove(temp_vec.size() - 1);
            if(temp_vec.size() == 0 || temp_vec.contains(temp_str)) {
                break;
            }
        }
        if(temp_vec.size() == 0){
            return true;

        }else{

            return false;
        }
    }

    //Given that NodeListOptional in a Class Declaration. extract the type environment
    public Vector<String> method_type(String class_id, String method_name1){
        Vector<String> da_list = new Vector<String>();
        Scope_Check temp_scope;
        Iterator _itr = class_tables.iterator();
        while(_itr.hasNext()){
            temp_scope = (Scope_Check)_itr.next();
            if(temp_scope.class_name_id.equals(class_id)){
                da_list = temp_scope.give_me_parameter(method_name1);
                break;
            }
        }

        return da_list;
    }
    public String return_type(int type_number){
        String _ret = "";
        if(type_number == 0){
            _ret = "ARRAY";
        }else if(type_number == 1){
            _ret = "BOOL";
        }else if(type_number == 2){
            _ret = "INT";
        }else if(type_number == 3){
            _ret = "ID";
        }
        return _ret;
    }
    public boolean check_if_no_type(String _id){
        if(!_id.equals("INT") && !_id.equals("ARRAY") && !_id.equals("BOOL")  ){
            return true;
        }
        return false;
    }
    public boolean check_class_id(String class_id){
        Iterator value = class_tables.iterator();
        Scope_Check current_table;
        while(value.hasNext()){
            current_table = (Scope_Check)value.next();
            if(current_table.check_id(class_id)){
                return true;
            }
        }
        return false;
    }
    public Scope_Check fields(String class_id){
        Scope_Check xyz = new Scope_Check();
        Iterator value = class_tables.iterator();
        while(value.hasNext()){
            xyz = (Scope_Check)value.next();
            if(xyz.class_name_id.equals(class_id)){
                return xyz;
            }
        }
        return xyz;

    }

    public boolean noOverloading(String class_id, String class_super_id, String method_name_id){
        Vector<String> method_vec_super = new Vector<String>();
        Vector<String> method_vec = new Vector<String>();
        method_vec= method_type(class_id,method_name_id);
        method_vec_super = method_type(class_super_id, method_name_id);
        if(method_vec.size() != method_vec_super.size()){
            return false;
        }
        for(int i = 0; i < method_vec.size();i++){
            if(!method_vec.elementAt(i).equals(method_vec_super.elementAt(i))){
                return false;
            }
        }
        return true;
    }
    public void print_function(Vector<String> xyz){
        Enumeration enu = xyz.elements();
        System.out.println("The enumeration of values are:");
        while(enu.hasMoreElements()){
            System.out.println(enu.nextElement());
        }
    }
    public void print_all_table_names(Vector<Scope_Check> xyz){
        Iterator _itr = xyz.iterator();
        Scope_Check temp_table;
        while(_itr.hasNext()){
            temp_table = (Scope_Check)_itr.next();
            System.out.println(temp_table.class_name_id);
        }
    }
}
