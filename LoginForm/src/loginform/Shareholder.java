package loginform;

/**
 *
 * @author JAMES
 */
public class Shareholder {
    String sharename,phone,address;
    int id;
    public Shareholder(int id,String sharename,String phone, String address){
        this.sharename = sharename;
        this.phone = phone;
        this.id = id;
        this.address = address;
    }
    
    //getters
    public String getSharename(){
        return this.sharename;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getAddress(){
        return this.address;
    }
    public int getId(){
        return this.id;
    }
}

