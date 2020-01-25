import java.util.*;
import syntaxtree.*;
import static packagename.Type_Constants.*;
public class Scope_Check{
    Map <String , String> scope_map;
    public Scope_Check(){
      scope_map = new HashMap<>();
    }
    //Check which Type to put on the map.
    public void add_me(String xyz,String  zzz){
      scope_map.put(xyz,zzz);
    }
    public String fields(String id){
      String str_1 = scope_map.get(id);
      return str_1;
    }

}
