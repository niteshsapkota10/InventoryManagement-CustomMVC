package Models;

public class Model {
    public int id,rate,quantity,sum;
    public String Name,ExpiryDate,is_salable;
    public Model(){}
    public Model(int id,int rate,int quantity,String Name,String ExpiryDate,String is_salable){
        this.id=id;
        this.rate=rate;
        this.quantity=quantity;
        this.Name=Name;
        this.ExpiryDate=ExpiryDate;
        this.is_salable=is_salable;
    }
    public int getId(){
        return this.id;
    }
    public int getRate(){
        return this.rate;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public String getName(){
        return this.Name;
    }
    public String getExpiryDate(){
        return this.ExpiryDate;
    }
    public String getIs_salable(){
        return  this.is_salable;
    }
    public int getSum(){
        return this.getRate()*this.getQuantity();
    }
}
