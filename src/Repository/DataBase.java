package Repository;

import Models.CustomerModel;
import Models.InventoryModel;
import Models.Model;
import Models.UserModel;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
//Class for Repository.DataBase
public class DataBase {
    //Initializing Database Credentials
    private String driver="com.mysql.jdbc.Driver";
    private String db_firsttime_url="jdbc:mysql://localhost:3306/";
    private String db_url="jdbc:mysql://localhost:3306/InventoryManagement";
    private String db_userName="root";
    private String db_password="";
    Statement stmt,statement;
    Connection conn;

    //This Function is for Creating Database If it doesnot Exists
    public void createDatabase(){
        try{
            Class.forName(driver);
            conn=DriverManager.getConnection(db_firsttime_url,db_userName,db_password);
            stmt=conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE if not exists InventoryManagement");
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //This function is for Creating Database Connection for CRUD
   public void createConnection(){
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(db_url, db_userName, db_password);
            stmt=conn.createStatement();
            statement=conn.createStatement();
        }catch (Exception e){
        }
    }
    //This Function is for Creating Table if it Table on Database doesnot Exists
   public void createTable(){
        createConnection();
        try {
            stmt.executeUpdate("CREATE TABLE if not exists Users(" +
                    "ID int Not NULL AUTO_INCREMENT," +
                    "Username varchar(255) Not NULL," +
                    "Address varchar(255) Not NULL," +
                    "Contact varchar(255) Not NULL," +
                    "Password varchar(255) Not NULL," +
                    "PRIMARY KEY (ID));" +
                    "");
            stmt.executeUpdate("CREATE TABLE if not exists Customers(" +
                    "ID int Not NULL AUTO_INCREMENT," +
                    "Name varchar(255) Not NULL," +
                    "PANNumber varchar(255) Not NULL," +
                    "PRIMARY KEY (ID));" +
                    "");
            stmt.executeUpdate("CREATE TABLE if not exists Inventory(" +
                    "ID int Not NULL AUTO_INCREMENT," +
                    "ItemName varchar(255) Not NULL," +
                    "Quantity int Not NULL," +
                    "Rate int Not NULL," +
                    "Expiry_date varchar(255) Not NULL," +
                    "Is_Salable tinyint(1) DEFAULT NULL,"+
                    "PRIMARY KEY (ID));" +
                    "");
            stmt.executeUpdate("CREATE TABLE if not exists Transactions(" +
                    "ID int Not NULL AUTO_INCREMENT," +
                    "Customer_id int Not NULL," +
                    "Product_Name varchar(255) Not NULL," +
                    "Quantity int Not NULL," +
                    "Rate int Not NULL," +
                    "Total int DEFAULT NULL,"+
                    "Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"+
                    "FOREIGN KEY (Customer_id) REFERENCES Customers(ID),"+
                    "PRIMARY KEY (ID));" +
                    "");

            stmt.executeUpdate("CREATE TABLE if not exists Transfer(" +
                    "ID int Not NULL AUTO_INCREMENT," +
                    "Item_ID int Not Null,"+
                    "Branch varchar(255) Not NULL," +
                    "Quantity int Not Null ,"+
                    "PRIMARY KEY (ID));" +
                    "");
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        } }

   public int insertUser(String Username,String Address,String Contact,String Password){
        createConnection();
        try{
            String checkUserQuery="SELECT count(*) FROM `users` WHERE Username='"+Username+"';";
            ResultSet count=stmt.executeQuery(checkUserQuery);
            count.next();
            int usersCount=count.getInt("count(*)");
            System.out.println(usersCount);
            if(usersCount>0) {
                return -1;
            }else {
                stmt.executeUpdate("INSERT INTO Users (Username,Address,Contact,Password) VALUES ('" + Username + "','" + Address + "','" + Contact + "','" + Password + "');");
                conn.close();
                return 1;
            }
        }catch (Exception e) {
            return 0;
        }
    }

    public int updateUser(int id,String Username,String Address,String Contact,String Password){
        createConnection();
        try{
            String checkUserQuery="SELECT count(*) FROM `users` WHERE Username='"+Username+"';";
            ResultSet count=stmt.executeQuery(checkUserQuery);
            count.next();
            int usersCount=count.getInt("count(*)");
            System.out.println(usersCount);
            if(usersCount>0) {
                return -1;
            }else {
                String QueryString="UPDATE Users SET Username='"+Username+"', Address='"+Address+"', Contact='"+Contact+"', Password='"+Password+"' WHERE ID='"+id+"');";
                stmt.executeUpdate(QueryString);
                conn.close();
                return 1;
            }
        }catch (Exception e) {
            return 0;
        }
    }

    //This function is for Inserting Data In Database
    public void insertInventoryData(String ItemName,int Quantity,int Rate,String Expiry_date,int is_salable){
        createConnection();
        try{
            stmt.executeUpdate("INSERT INTO `inventory` (ItemName,Quantity,Rate,Expiry_date,Is_Salable) VALUES ('"+ItemName+"','"+Quantity+"','"+Rate+"','"+Expiry_date+"','"+is_salable+"');");
            JOptionPane.showMessageDialog(null,"Your Inventory is Added ");
            conn.close();
        }catch (Exception e){
        }
    }
   public long insertCustomerData(String Name,String PANNumber){
        long id=0;
        createConnection();
        //stmt.executeUpdate("INSERT INTO `customers` (Name,PANNumber) VALUES ('"+Name+"','"+PANNumber+"');");
            try {
                String sql = "INSERT INTO `customers` (Name,PANNumber) VALUES ('"+Name+"','"+PANNumber+"');";
                long lastInsertedID = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs= statement.getGeneratedKeys();
                if (rs.next())
                {
                    System.out.println("Last Inserted ID = "+rs.getLong(1));
                    id=rs.getLong(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return id;
    }
   public void insertTransactionData(String CustomerName,String CustomerPANNumber,ArrayList<Model> ModelList,int quantity){
        long id=insertCustomerData(CustomerName,CustomerPANNumber);
        long total;
        String QueryString;
        String updateString;
        if(id==0){
            JOptionPane.showMessageDialog(null,"Something Went Wrong !!! ");
        }else{
            createConnection();
            for(int i=0;i< ModelList.size();i++){
                QueryString="";
                total=0;
                total=ModelList.get(i).getQuantity()*ModelList.get(i).getRate();
                QueryString="INSERT INTO `transactions` (Customer_id,Product_Name,Quantity,Rate,Total) VALUES ('"+id+"','"+ModelList.get(i).getName()+"','"+ModelList.get(i).getQuantity()+"','"+ModelList.get(i).getRate()+"','"+total+"');";
                updateString="UPDATE `inventory` SET quantity='"+quantity+"' WHERE ID='"+ModelList.get(i).getId()+"';";
                try {
                    var i1 = stmt.executeUpdate(QueryString);
                    var i2=stmt.executeUpdate(updateString);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
   public void insertTransactionData2(int id,ArrayList<Model> ModelList,int quantity){
        long total;
        String QueryString;
        String updateString;
            createConnection();
            for(int i=0;i< ModelList.size();i++){
                QueryString="";
                total=0;
                total=ModelList.get(i).getQuantity()*ModelList.get(i).getRate();
                QueryString="INSERT INTO `transactions` (Customer_id,Product_Name,Quantity,Rate,Total) VALUES ('"+id+"','"+ModelList.get(i).getName()+"','"+ModelList.get(i).getQuantity()+"','"+ModelList.get(i).getRate()+"','"+total+"');";
                updateString="UPDATE `inventory` SET quantity='"+quantity+"' WHERE ID='"+ModelList.get(i).getId()+"';";
                try {
                    var i1 = stmt.executeUpdate(QueryString);
                    var i2=stmt.executeUpdate(updateString);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

    }

   public Model getDataByID(int id) {
        Model m1 = new Model();
        createConnection();
        try {
            String queryString = "SELECT * from `inventory` where ID='" + id + "';";
                ResultSet rs = stmt.executeQuery(queryString);
                while (rs.next()) {
                    m1.id = rs.getInt(1);
                    m1.rate = rs.getInt(4);
                    m1.quantity = rs.getInt(3);
                    m1.Name = rs.getString(2);
                    m1.ExpiryDate = rs.getString(5);
                    m1.is_salable = rs.getString(6);
                    //m1=new Models.Model(rs.getInt(1),rs.getInt(4),rs.getInt(3),rs.getString(2),rs.getString(5),rs.getString(6));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid ID");
            }
            return m1;
    }
   public UserModel getDataByUserName(String UserName) {
        UserModel m1 = new UserModel();
        createConnection();
        try {
            String queryString = "SELECT * from `users` where Username='" + UserName + "';";
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                m1.id = rs.getInt(1);
                m1.UserName = rs.getString(2);
                m1.Address = rs.getString(3);
                m1.Contact_No = rs.getString(4);
                m1.Password = rs.getString(5);
                //m1=new Models.Model(rs.getInt(1),rs.getInt(4),rs.getInt(3),rs.getString(2),rs.getString(5),rs.getString(6));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid ID");
        }
        return m1;
    }
   public void DeleteUser(int id){
        createConnection();
        try{
            String sql="DELETE FROM `users` WHERE ID='"+id+"';";
            stmt.executeUpdate(sql);
            conn.close();
        }catch (Exception e){
        }
    }
   public int loginData(String Username,String Password){
        createConnection();
        try{
            String checkUserQuery="SELECT count(*) FROM `users` WHERE Username='"+Username+"' AND Password='"+Password+"';";
            ResultSet count=stmt.executeQuery(checkUserQuery);
            count.next();
            int usersCount=count.getInt("count(*)");
            System.out.println(usersCount);
            if(usersCount>0) {
                return -1;
            }else {
                return 1;
            }
        }catch (Exception e) {
            return 0;
        }
    }
    public void getAllInventoryData(){
        ArrayList<InventoryModel> inventoryModels=new ArrayList<>();
        createConnection();
        try{
            String getAllDataQuery="SELECT * FROM `inventory`;";
            ResultSet rs=stmt.executeQuery(getAllDataQuery);
            while (rs.next()) {
                inventoryModels.add(new InventoryModel(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
                //m1=new Models.Model(rs.getInt(1),rs.getInt(4),rs.getInt(3),rs.getString(2),rs.getString(5),rs.getString(6));
            }
            System.out.println("Lists");
            for(int i=0;i<inventoryModels.size();i++){
                System.out.println("Id : "+inventoryModels.get(i).getId());
                System.out.println("Expiry Date : "+inventoryModels.get(i).getExpiry_date());
            }
            revoke(inventoryModels);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void revoke(ArrayList<InventoryModel> inventoryModels){
        int year=(LocalDateTime.now().getYear());
        int month=(LocalDateTime.now().getMonthValue());
        int day=(LocalDateTime.now().getDayOfMonth());
        ArrayList<Integer> ids=new ArrayList<>();
        for(int i=0;i<inventoryModels.size();i++){
            int inyear=Integer.parseInt(inventoryModels.get(i).getExpiry_date().substring(0,4));
            int inmonth=Integer.parseInt(inventoryModels.get(i).getExpiry_date().substring(5,7));
            int inday=Integer.parseInt(inventoryModels.get(i).getExpiry_date().substring(8,10));
            System.out.println(inyear);
            System.out.println(inmonth);
            System.out.println(inday);
            if(inyear<year){
                ids.add(inventoryModels.get(i).getId());
            }else if(inyear==year){
                if(inmonth<month){
                    ids.add(inventoryModels.get(i).getId());
                }else if(month==inmonth){
                    if(inday<day){
                        ids.add(inventoryModels.get(i).getId());
                    }
                }
            }
        }
        updateInventorySalableItems(ids);
    }
    public void updateInventorySalableItems(ArrayList<Integer> ids){
        createConnection();
        String updateSql;
        if(!ids.isEmpty()) {
            try {
                for (int i = 0; i < ids.size(); i++) {
                    updateSql = "UPDATE `inventory` SET is_salable=0 WHERE ID='" + ids.get(i) + "';";
                    stmt.executeUpdate(updateSql);
                    System.out.println("Update SuccessFull");
                    JOptionPane.showMessageDialog(null,"Successfully Revoked Expired Items ");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null,"No Expired or Damaged Items to Revoke !!");
        }
    }
    public ArrayList<CustomerModel> getCustomersData(){
        ArrayList<CustomerModel> customerModels=new ArrayList<>();
        createConnection();
        try{
            String getAllDataQuery="SELECT * FROM `customers`;";
            ResultSet rs=stmt.executeQuery(getAllDataQuery);
            while (rs.next()) {
                customerModels.add(new CustomerModel(rs.getInt(1),rs.getString(2),rs.getString(3)));
//                customerModels.add(new Models.InventoryModel(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
                //m1=new Models.Model(rs.getInt(1),rs.getInt(4),rs.getInt(3),rs.getString(2),rs.getString(5),rs.getString(6));
            }
            System.out.println("Lists");
            for(int i=0;i<customerModels.size();i++){
                System.out.println("Id : "+customerModels.get(i).getId());
                System.out.println("Name : "+customerModels.get(i).getName());
                System.out.println("Pan No : "+customerModels.get(i).getPanno());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return customerModels;
    }
    public void insertTransferData(int item_id,int quantity,String branch){
        createConnection();
        try{
            stmt.executeUpdate("INSERT INTO `transfer` (Item_ID,Branch,Quantity) VALUES ('"+item_id+"','"+branch+"','"+quantity+"');");
            JOptionPane.showMessageDialog(null,"Your Inventory is Transferred ");
            conn.close();
        }catch (Exception e){
        }

    }
}