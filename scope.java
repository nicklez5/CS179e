import java.util.*;
import syntaxtree.*;
import static packagename.Type_Constants.*;
public class Scope_Check{
    Map <String , String> scope_map;
    String class_name_id;
    public Scope_Check(){
      scope_map = new HashMap<>();
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

}
