import java.util.*;
import syntaxtree.*;
public class Helper_Functions{
  public Vector<Node> node_placer;
  //Return the name of the class
  public String get_classname(ClassDeclaration n0){
     return n0.f1.f0.toString();
  }
  //Return the name of the class extends declaration
  public String get_classname(ClassExtendsDeclaration n0){
      return n0.f1.f0.toString();
  }

  public String get_methodname(MethodDeclaration n0){
      return m0.f2.f0.toString();
  }

  public boolean check_distinct(NodeListOptional n0){
      //Create a copy of the vector of nodes.
      node_placer = (Vector)n0.nodes.clone();
      Node node_object = new Node();
      //Remove one nodes
      //Loop over the vector of nodes and check if there is a node of that kind
      //If found, break while loop and return false otherwise keep going.
      while(node_placer.size() != 0){
          node_object = node_placer.lastElement();
          node_placer.Remove(node_placer.lastElement())
          if(node_placer.contains(node_object)){
              break;
          }
      }
      if(node_placer.size() == 0){
        return false;
      }else{
        return true;
      }
  }
  //Given that NodeListOptional in a Class Declaration. extract the type environment
  public int fields(){

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
}
