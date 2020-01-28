import java.util.*;
import syntaxtree.*;

public class Helper_Functions{
  public Vector<Node> node_placer;
  public Vector<Scope_Check> class_tables;
  //Return the name of the class
  public Helper_Functions(Vector<Scope_Check> set_me){
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
      if(statement_ID.contains("FALSE")){
          return false;
      }
      return true;
  }
  public boolean check_distinct(Vector<String> List_ID){
      //Create a copy of the vector of nodes.
      Vector<String> temp_vec;
      temp_vec = List_ID.clone();
      String temp_str;
      //Remove one nodes
      //Loop over the vector of nodes and check if there is a node of that kind
      //If found, break while loop and return false otherwise keep going.
      while(temp_vec.size() != 0){
          temp_str = node_placer.lastElement();
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
  public String method_type(String method_name,Scope_Check tmp_map){
    String xyz_1 = "FALSE";
    //True;
    if(tmp_map.check_id(method_name)){
        xyz_1 = tmp_map.fields(method_name);
    }
    return xyz_1;
  }
  public String return_type(Int type_number){
    if(type_number == ARRAY_TYPE){
       return "ARRAY";
    }else if(type_number == BOOL_TYPE){
      return "BOOL";
    }else if(type_number == INT_TYPE){
      return "INT";
    }else if(type_number == ID_TYPE){
      return "ID";
    }
  }
  public boolean check_class_id(String class_id){
    Iterator value = class_tables.iterator();
    Scope_Check current_table;
    while(value.hasNext()){
      current_table = value.next();
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
          xyz = value.next();
          if(xyz.class_name_id.equals(class_id)){
              return xyz;
          }
        }

  }
  public String methodtype(String class_id, String method_name_id){
      String empty_str = "";
      Scope_Check temp_table = new Scope_Check();
      temp_table = fields(class_id);
      if(temp_table.check_id(method_name_id)){
          return temp_table.fields(method_name_id);
      }else{
          return empty_str;
      }
  }
  public boolean noOverloading(String class_id, String class_super_id, String method_name_id){
      String method_type;
      String method_type_P;
      method_type = methodtype(class_id,method_name_id);
      method_type_P = methodtype(class_super_id, method_name_id);
      if(method_type.isEmpty() || method_type_P.isEmpty()){
        return false;
      }else if(method_type.equals(method_type_P)){
        return true;
      }else{
        return false;
      }
  }
}
