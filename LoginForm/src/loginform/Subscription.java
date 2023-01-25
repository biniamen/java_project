package loginform;

/**
 *
 * @author JAMES
 */
public class Subscription {
    int share_id,num_share;
    //int id;
    public Subscription(int share_id,int num_share){
        this.share_id = share_id;
        this.num_share = num_share;
        
    }
    
    //getters
    public int getShareid(){
        return this.share_id;
    }
    public int getNumshare(){
        return this.num_share;
    }
//    public String getAddress(){
//        return this.address;
//    }
//    public int getId(){
//        return this.id;
//    }
}

