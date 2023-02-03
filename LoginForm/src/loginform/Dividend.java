package loginform;

import java.util.Date;

/**
 *
 * @author Biniyam
 */
public class Dividend {
    int share_id ;
    double dividend_amount;
    String status;
    //String paid_date;
    
    public Dividend(int share_id, double dividend_amount,String status){
        this.share_id = share_id;
        //this.dividend_id = dividend_id;
        this.dividend_amount = dividend_amount;
        //this.paid_date = paid_date;
        this.status = status;
        //this.weight = weight;
    }
    
    //getters
    public int getShareid(){
        return this.share_id;
    }
    public double DividendAmount(){
        return this.dividend_amount;
    }
//     public double getWeighted(){
//        return this.weighted;
//    }
//    
    public String getStatus(){
        return this.status;
    }
}

