package loginform;

/**
 *
 * @author JAMES
 */
public class User {
    String uname,role;
    int id;
    public User(int id,String uname,String role){
        this.uname = uname;
        this.role = role;
        //this.id = id;
    }
    
    //getters
    public String getRole(){
        return this.role;
    }
    public String getUname(){
        return this.uname;
    }
    public int getUserId(){
        return this.id;
    }
}

