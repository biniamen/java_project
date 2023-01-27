package loginform;

import java.util.Date;

/**
 *
 * @author Biniyam
 */
public class Payment {
    int share_id,weight;
    double payment,weighted;
    String status;
    Date paid_date;
    
    public Payment(int share_id,double payment, double weighted, Date paid_date,String status,int weight){
        this.share_id = share_id;
        this.payment = payment;
        this.weighted = weighted;
        this.paid_date = paid_date;
        this.status = status;
        this.weight = weight;
    }
    
    //getters
    public int getShareid(){
        return this.share_id;
    }
    public double getPayment(){
        return this.payment;
    }
     public double getWeighted(){
        return this.weighted;
    }
    public Date getPayDate(){
        return this.paid_date;
    }
    public String getStatus(){
        return this.status;
    }
}

