public class Model {
    int id,rate,quantity,sum;
    String Name,ExpiryDate,is_salable;
    Model(){}
    Model(int id,int rate,int quantity,String Name,String ExpiryDate,String is_salable){
        this.id=id;
        this.rate=rate;
        this.quantity=quantity;
        this.Name=Name;
        this.ExpiryDate=ExpiryDate;
        this.is_salable=is_salable;
    }
    int getId(){
        return this.id;
    }
    int getRate(){
        return this.rate;
    }
    int getQuantity(){
        return this.quantity;
    }
    String getName(){
        return this.Name;
    }
    String getExpiryDate(){
        return this.ExpiryDate;
    }
    String getIs_salable(){
        return  this.is_salable;
    }
    int getSum(){
        return this.getRate()*this.getQuantity();
    }
}
class UserModel{
    int id;
    String UserName,Password,Address,Contact_No;
    UserModel(){}
    UserModel(int id,String UserName,String Password,String Address,String Contact_No){
        this.id =id;
        this.UserName=UserName;
        this.Password=Password;
        this.Address=Address;
        this.Contact_No=Contact_No;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return Address;
    }

    public String getContact_No() {
        return Contact_No;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserName() {
        return UserName;
    }
}