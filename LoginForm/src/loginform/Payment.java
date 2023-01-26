package loginform;

/**
 *
 * @author JAMES
 */
public class Payment {
    int share_id;
    double payment,weighted;
    String paid_date,status;
    public Payment(int share_id,double payment, double weighted, String paid_date){
        this.share_id = share_id;
        this.payment = payment;
        this.weighted = weighted;
        this.paid_date = paid_date;
        this.status = status;
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
    public String getPayDate(){
        return this.paid_date;
    }
    public String getStatus(){
        return this.status;
    }
}

