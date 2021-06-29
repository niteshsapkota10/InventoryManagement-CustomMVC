package Models;

public class UserModel{
    public int id;
    public String UserName,Password,Address,Contact_No;
    public UserModel(){}
    public UserModel(int id,String UserName,String Password,String Address,String Contact_No){
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