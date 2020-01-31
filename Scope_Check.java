
import java.util.*;
import syntaxtree.*;

//import static packagename.Type_Constants.*;
public class Scope_Check{
    public Map <String , String> scope_map;
    public Map <String, String> method_scope_map;
    public Map <String, String> method_return_type_map;
    public String class_name_id;
    public Scope_Check(){
      scope_map = new HashMap<>();
      method_scope_map = new HashMap<>();
      method_return_type_map = new HashMap<>();
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
     public void add_method(String xyz,String zzz){
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

}
