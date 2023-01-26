package loginform;

/**
 *
 * @author JAMES
 */
public class Subscription {
    int share_id,num_share;
    String sub_date;
    public Subscription(int share_id,int num_share,String sub_date){
        this.share_id = share_id;
        this.num_share = num_share;
        this.sub_date = sub_date;
    }
    
    //getters
    public int getShareid(){
        return this.share_id;
    }
    public int getNumshare(){
        return this.num_share;
    }
    public String getSubDate(){
        return this.sub_date;
    }
//    public int getId(){
//        return this.id;
//    }
}

