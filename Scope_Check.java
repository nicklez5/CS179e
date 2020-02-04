
import java.util.*;
import syntaxtree.*;

//import static packagename.Type_Constants.*;
public class Scope_Check{
    public Map <String , String> scope_map;
    public Map <String, Vector<String> > method_scope_map;
    public Map <String, String> method_return_type_map;
    public String class_name_id;
    public Scope_Check(){
      scope_map = new HashMap<String,String>();
      method_scope_map = new HashMap<String, Vector<String> >();
      method_return_type_map = new HashMap<String, String>();
      class_name_id = "";
    }
    //Check which Type to put on the map.
    public void add_me(String xyz,String  zzz){
      scope_map.put(xyz,zzz);
    }
    public String fields(String id){
      String str_1 = scope_map.get(id);
      return str_1;
    }
    public String method_fields(String id){
        String str_1 = method_return_type_map.get(id);
        return str_1;
    }
    public boolean check_id(String id){
      if(scope_map.containsKey(id)){
        return true;
      }else{
        return false;
      }
   }

     public void print_map(){
         for(Map.Entry<String,String> entry: scope_map.entrySet()){
             String k = entry.getKey();
             String v = entry.getValue();
             System.out.println(k + ":" + v);
         }
     }
     public void print_method_return_type(){
         for(Map.Entry<String,String> entry: method_return_type_map.entrySet()){
             String k = entry.getKey();
             String v = entry.getValue();
             System.out.println(k + ":" + v);
         }
     }
     public void print_method_vec(){
         for(Map.Entry<String,Vector<String> > entry: method_scope_map.entrySet()){
             String k = entry.getKey();
             Vector<String> v = entry.getValue();
             System.out.println(k + ":" + v);
         }
     }
     //xyz = Map id
     //zzz = Vector<String> parameter type in a list.
     public void add_paramethod(String xyz,Vector<String> zzz){

         method_scope_map.put(xyz,zzz);
     }
     public void add_method_type(String xyz, String zzz){
         method_return_type_map.put(xyz,zzz);
     }
     public String return_method_type(String method_id){
         String tmp_type = "";
         for(Map.Entry<String,String> entry: method_return_type_map.entrySet()){
             String k = entry.getKey();
             String v = entry.getValue();
             if(k.equals(method_id)){
                 tmp_type = v;
             }
         }
         return tmp_type;
     }

     public boolean find_method_name(String method_name1){
         if(method_scope_map.containsKey(method_name1)){
             return true;
         }
         return false;
     }
     public Vector<String> give_me_parameter(String method_id_name){
         Vector<String> str1 = method_scope_map.get(method_id_name);
         Iterator _itr = str1.iterator();
         System.out.println("Current table name: " + class_name_id);
         //System.out.println("Defaulty Method: " + method_id_name);
         //System.out.println("Method types: ");
         while(_itr.hasNext()){
             String temp_str = (String)_itr.next();
             System.out.println(temp_str);
         }
         return str1;
     }

}
