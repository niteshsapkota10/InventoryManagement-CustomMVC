package ControllersView;

import Models.CustomerModel;
import Models.Model;
import Models.UserModel;
import Repository.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class SampleApp {
    //Initializing Components
    private int quantity;
    private JFrame f,f2,createUserFrame,LoginFrame,editUserFrame,deleteUserFrame,editUserInterfaceFrame,transferFrame;
    private JPanel dataEntry,dataList,footer;
    private JLabel idLabel,nameLabel,rateLabel,quantityLabel,expiryLabel,salableLabel,customerLabel,customerNameLabel,customerPANLabel,usernameLabel,userAddressLabel,userContactLabel,userPasswordLabel,userconfirmPasswordLabel;
    private JTextField nameInput,rateInput,idInput,quantityInput,expiryInput,customerNameInput,customerPANInput,userNameInput,userAddressInput,userContactInput,customerNameInput1,customerPANInput1;
    private JPasswordField userPasswordInput,userPasswordInput2;
    private JRadioButton maleBtn,femaleBtn,unspecifiedBtn;
    private ButtonGroup bg;
    private JComboBox yesNo;
    private JTable infoTable;
    private JScrollPane sp;
    private JButton signInBtn,getDataBtn,removeFromCartBtn,createUserBtn,editUserBtn,deleteUserBtn,loginBtn,logoutBtn,addToCartBtn,generateBillBtn,addInventoryBtn,transferBtn,revokeBtn,clearBtn,UsersBtn,signUpBtn;
    private DataBase db1;
    private DefaultTableModel infoTableModel;
    private ArrayList<Model> ModelList=new ArrayList<Model>();
    private boolean loginStatus=false;
    private JComboBox yearCombo,monthCombo,dayCombo,panNoCombo;
    private JRadioButton alreadyUser,newUser;
    private ArrayList<CustomerModel> customerModels=new ArrayList<>();
    private ArrayList<String> pannos=new ArrayList<>();
    private JTextField customerIdInput;

    //Constructor
    SampleApp(){
        Login();
    }
    //This function is for Creating GUI frame and Calling pannels
    void frameGui(){
        f=new JFrame("ABC INVENTORY MANAGEMENT SYSTEM");
        f.setSize(1024,700);
        f.setVisible(true);
        f.setDefaultCloseOperation(3);
        setMenu();
        setDataEntry();
        setDataList();
        setFooter();
    }
    //This function Sets the Menu (File,Edit,View)
    void setMenu(){
        JMenuBar menuBar=new JMenuBar();
        f.setJMenuBar(menuBar);
        JMenu file=new JMenu("File");
        JMenu edit=new JMenu("Edit");
        JMenu view=new JMenu("View");
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);
    }
    //This function sets the DataEntry Panel form at the left side
    void setDataEntry(){
        dataEntry=new JPanel();
        Insets insets=dataEntry.getInsets();
        dataEntry.setLayout(null);
        dataEntry.setPreferredSize(new Dimension(500,500));
        if(loginStatus){
            usernameLabel=new JLabel(userNameInput.getText());
            dataEntry.add(usernameLabel);
            usernameLabel.setBounds(300 + insets.left, 50 + insets.top, usernameLabel.getPreferredSize().width, usernameLabel.getPreferredSize().height);
        }else {
            loginBtn = new JButton("Login");
            loginBtn.setBounds(300 + insets.left, 50 + insets.top, loginBtn.getPreferredSize().width, loginBtn.getPreferredSize().height);
            dataEntry.add(loginBtn);
            loginBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Login();
                }
            });
        }
        logoutBtn=new JButton("Logout");
        logoutBtn.setBounds(370+insets.left,50+insets.top,logoutBtn.getPreferredSize().width,logoutBtn.getPreferredSize().height);
        dataEntry.add(logoutBtn);
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                Login();

            }
        });

        //ID Field Starts
        idLabel=new JLabel("ID");
        dataEntry.add(idLabel);
        idLabel.setBounds(20+insets.left,100+insets.top,idLabel.getPreferredSize().width,idLabel.getPreferredSize().height);
        idInput=new JTextField();
        dataEntry.add(idInput);
        idInput.setBounds(60+insets.left,100+insets.top,idInput.getPreferredSize().width+75,idInput.getPreferredSize().height);
        //ID Field Ends
        //Name Field Starts
        nameLabel=new JLabel("Name");
        dataEntry.add(nameLabel);
        nameLabel.setBounds(200 + insets.left, 100 + insets.top, nameLabel.getPreferredSize().width, nameLabel.getPreferredSize().height);
        nameInput=new JTextField();
        dataEntry.add(nameInput);
        nameInput.setBounds(270 + insets.left, 100 + insets.top, nameInput.getPreferredSize().width+100, nameInput.getPreferredSize().height);
        //Name Field Ends
        //Rate Field Starts
        rateLabel=new JLabel("Rate");
        rateLabel.setBounds(20 + insets.left, 150 + insets.top, rateLabel.getPreferredSize().width, rateLabel.getPreferredSize().height);
        dataEntry.add(rateLabel);
        rateInput=new JTextField();
        rateInput.setBounds(60 + insets.left, 150 + insets.top, rateInput.getPreferredSize().width+75, rateInput.getPreferredSize().height);
        dataEntry.add(rateInput);
        //Rate Field Ends
        //Quantity Field Starts
        quantityLabel=new JLabel("Quantity");
        quantityLabel.setBounds(200 + insets.left, 150 + insets.top, quantityLabel.getPreferredSize().width+25, quantityLabel.getPreferredSize().height);
        dataEntry.add(quantityLabel);
        quantityInput=new JTextField();
        quantityInput.setBounds(270 + insets.left, 150 + insets.top, quantityInput.getPreferredSize().width+75, quantityInput.getPreferredSize().height);
        dataEntry.add(quantityInput);
        //Quantity Field Ends
        //Expiry Date Field Starts
        expiryLabel=new JLabel("Expiry Date");
        expiryLabel.setBounds(20 + insets.left, 200 + insets.top, expiryLabel.getPreferredSize().width+25, expiryLabel.getPreferredSize().height);
        dataEntry.add(expiryLabel);

        int year=(LocalDateTime.now().getYear());
        Integer years[]=new Integer[10];
        years[0]=year;
        for(int y=1;y<10;y++){
            years[y]=year+1;
            year=year+1;
        }
        yearCombo=new JComboBox(years);
        yearCombo.setBounds(100+insets.left,200+insets.top,yearCombo.getPreferredSize().width+20,yearCombo.getPreferredSize().height);
        dataEntry.add(yearCombo);


        String months[]=new String[12];
        for(int m=1;m<=12;m++){
            if(m<10){
                months[m-1]="0"+Integer.toString(m);
            }else{
                months[m-1]=Integer.toString(m);
            }
        }
        monthCombo=new JComboBox(months);
        monthCombo.setBounds(180+insets.left,200+insets.top,monthCombo.getPreferredSize().width+20,monthCombo.getPreferredSize().height);
        dataEntry.add(monthCombo);

        String days[]=new String[30];
        for(int d=1;d<=30;d++){
            if(d<10){
                days[d-1]="0"+Integer.toString(d);
            }else{
                days[d-1]=Integer.toString(d);
            }
        }
        dayCombo=new JComboBox(days);
        dayCombo.setBounds(240+insets.left,200+insets.top,dayCombo.getPreferredSize().width+20,dayCombo.getPreferredSize().height);
        dataEntry.add(dayCombo);


        //Quantity Field Ends
        //Salable Field Starts
        salableLabel=new JLabel("Is Salable");
        salableLabel.setBounds(320 + insets.left, 200 + insets.top, salableLabel.getPreferredSize().width, salableLabel.getPreferredSize().height);
        dataEntry.add(salableLabel);

        String flags[]={"Yes","No"};
        yesNo=new JComboBox(flags);
        yesNo.setBounds(390 + insets.left, 200 + insets.top, yesNo.getPreferredSize().width+20, yesNo.getPreferredSize().height);
        dataEntry.add(yesNo);
        //Add to Cart Field Starts



        addToCartBtn=new JButton("Add To Cart");
        addToCartBtn.setBounds(270+insets.left,250+insets.top,addToCartBtn.getPreferredSize().width,addToCartBtn.getPreferredSize().height);
        dataEntry.add(addToCartBtn);
        addToCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCartFunction();
            }
        });
        //Add to Cart Field Ends
        //Remove from Cart Field Starts
        removeFromCartBtn=new JButton("Remove From Cart");
        removeFromCartBtn.setBounds(100+insets.left,250+insets.top,addToCartBtn.getPreferredSize().width+70,addToCartBtn.getPreferredSize().height);
        dataEntry.add(removeFromCartBtn);
        removeFromCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromCartFunction();
            }
        });
        //Remove from Cart Field Ends
        //Get Data Button Field Starts
        getDataBtn=new JButton("Get Data");
        getDataBtn.setBounds(10+insets.left,250+insets.top,addToCartBtn.getPreferredSize().width,addToCartBtn.getPreferredSize().height);
        dataEntry.add(getDataBtn);
        getDataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDataFromDb();
            }
        });
        //Get Data Button Field Ends
        //Customer Starts
        //customerLabel,customerNameLabel,customerPANLabel

        customerLabel=new JLabel("Customer Details");
        customerLabel.setBounds(20+insets.left,300+insets.top,customerLabel.getPreferredSize().width,customerLabel.getPreferredSize().height);
        dataEntry.add(customerLabel);

        alreadyUser=new JRadioButton("Already a Customer ?");
        alreadyUser.setBounds(150+insets.left,300+insets.top,alreadyUser.getPreferredSize().width,alreadyUser.getPreferredSize().height);
        dataEntry.add(alreadyUser);

        newUser=new JRadioButton("New Customer ?");
        newUser.setBounds(300+insets.left,300+insets.top,newUser.getPreferredSize().width,newUser.getPreferredSize().height);
        dataEntry.add(newUser);

        bg=new ButtonGroup();
        bg.add(alreadyUser);
        bg.add(newUser);

        alreadyUser.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(alreadyUser.isSelected()){
                    System.out.println("Already User Selected");
                    customerNameInput.setEditable(false);
                    customerPANInput1.setEditable(false);
                    panNoCombo.setVisible(true);
                }
            }
        });

        newUser.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(newUser.isSelected()){
                    System.out.println("New User Selected");
                    panNoCombo.setVisible(false);
                    customerNameInput.setEditable(true);
                    customerPANInput1.setEditable(true);
                }
            }
        });

        customerNameLabel=new JLabel("Customer Name");
        customerNameLabel.setBounds(20+insets.left,350+insets.top,customerNameLabel.getPreferredSize().width,customerNameLabel.getPreferredSize().height);
        dataEntry.add(customerNameLabel);

        customerNameInput1=new JTextField();
        customerNameInput1.setBounds(120+insets.left,350+insets.top,customerNameInput1.getPreferredSize().width+100,customerNameInput1.getPreferredSize().height);
        dataEntry.add(customerNameInput1);

        customerPANLabel=new JLabel("PAN NO : ");
        customerPANLabel.setBounds(230+insets.left,350+insets.top,customerPANLabel.getPreferredSize().width,customerPANLabel.getPreferredSize().height);
        dataEntry.add(customerPANLabel);

        customerPANInput=new JTextField();
        customerPANInput.setBounds(280+insets.left,350+insets.top,customerPANInput.getPreferredSize().width+100,customerPANInput.getPreferredSize().height);
//        dataEntry.add(customerPANInput);

//        String Pannos[]={"A","B"};

        db1=new DataBase();
        customerModels=db1.getCustomersData();
        int[] customerids=new int[customerModels.size()];
        String[] customerNames=new String[customerModels.size()];
        String[] pannos=new String[customerModels.size()];
        for(int i=0;i<customerModels.size();i++){
            customerids[i]= customerModels.get(i).getId();
            customerNames[i]=customerModels.get(i).getName();
            pannos[i]=customerModels.get(i).getPanno();
        }
        panNoCombo=new JComboBox(pannos);
        panNoCombo.setBounds(280+insets.left,350+insets.top,panNoCombo.getPreferredSize().width+100,panNoCombo.getPreferredSize().height);
        dataEntry.add(panNoCombo);

        panNoCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int panNoComboSelectedIndex=panNoCombo.getSelectedIndex();
                customerNameInput1.setText(customerNames[panNoComboSelectedIndex]);
                customerIdInput.setText(String.valueOf(customerids[panNoComboSelectedIndex]));
            }
        });

        customerLabel=new JLabel("New Customer Details");
        customerLabel.setBounds(20+insets.left,400+insets.top,customerLabel.getPreferredSize().width,customerLabel.getPreferredSize().height);
        dataEntry.add(customerLabel);
        customerNameLabel=new JLabel("Customer Name");
        customerNameLabel.setBounds(20+insets.left,450+insets.top,customerNameLabel.getPreferredSize().width,customerNameLabel.getPreferredSize().height);
        dataEntry.add(customerNameLabel);

        customerNameInput=new JTextField();
        customerNameInput.setBounds(120+insets.left,450+insets.top,customerNameInput.getPreferredSize().width+100,customerNameInput.getPreferredSize().height);
        dataEntry.add(customerNameInput);

        customerPANLabel=new JLabel("PAN NO : ");
        customerPANLabel.setBounds(230+insets.left,450+insets.top,customerPANLabel.getPreferredSize().width,customerPANLabel.getPreferredSize().height);
        dataEntry.add(customerPANLabel);

        customerPANInput1=new JTextField();
        customerPANInput1.setBounds(280+insets.left,450+insets.top,customerPANInput.getPreferredSize().width+100,customerPANInput.getPreferredSize().height);
        dataEntry.add(customerPANInput1);
        //Customer Ends

        customerIdInput=new JTextField();
        customerIdInput.setBounds(280+insets.left,600+insets.top,customerIdInput.getPreferredSize().width+100,customerIdInput.getPreferredSize().height);
        dataEntry.add(customerIdInput);
        //Generate Bill Btn Starts

        generateBillBtn=new JButton("Generate Bill");
        generateBillBtn.setBounds(270+insets.left,500+insets.top,generateBillBtn.getPreferredSize().width,generateBillBtn.getPreferredSize().height);
        dataEntry.add(generateBillBtn);
        generateBillBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBillFunction();
            }
        });

        //Generate Bill Btn Ends
        dataEntry.setBorder(BorderFactory.createTitledBorder("Data Entry "));
        //Data Entry Fields Ends
        f.add(dataEntry,BorderLayout.WEST);

    }
    void addToCartFunction(){
        if(validations()){
            int id=Integer.parseInt(idInput.getText());
            String name=nameInput.getText();
            int rate=Integer.parseInt(rateInput.getText());
            int quantityInp=Integer.parseInt(quantityInput.getText());
            String expiryYear=new String(yearCombo.getSelectedItem().toString());
            String expiry_month=new String(monthCombo.getSelectedItem().toString());
            String expiry_day=new String(dayCombo.getSelectedItem().toString());
            String expiry_date=expiryYear+"-"+expiry_month+"-"+expiry_day;
            String salableStatus=yesNo.getSelectedItem().toString();
            if(quantityInp>quantity) {
                JOptionPane.showMessageDialog(f, "Sufficient Quantity Not Available");
            }else {
                Model m1 = new Model(id, rate, quantityInp, name, expiry_date, salableStatus);
                ModelList.add(m1);
                updateTable();
                setClearBtn();
                quantity=quantity-quantityInp;
            }
        }else {
            JOptionPane.showMessageDialog(null,"Something went wrong!! ");
        }
    }
    void removeFromCartFunction(){
        int id=Integer.parseInt(idInput.getText());
        try {
            quantity=quantity+ModelList.get(id).getQuantity();
            ModelList.remove(id-1);
            updateTable();
            setClearBtn();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Please Use Correct Index !!! ");
        }
    }
    void getDataFromDb(){
        String idInp=idInput.getText();
        if(idInp.equals("")){
            JOptionPane.showMessageDialog(null,"ID Invalid");
        }else{
            int id=Integer.parseInt(idInp);
            db1=new DataBase();
            Model m1=db1.getDataByID(id);
            try {
                idInput.setText(String.valueOf(m1.getId()));
                nameInput.setText(m1.getName());
                rateInput.setText(String.valueOf(m1.getRate()));
                quantity=m1.getQuantity();
                int inyear=Integer.parseInt(m1.getExpiryDate().substring(0,4));
                int inmonth=Integer.parseInt(m1.getExpiryDate().substring(5,7));
                int inday=Integer.parseInt(m1.getExpiryDate().substring(8,10));

                int year=(LocalDateTime.now().getYear());
                Integer years[]=new Integer[10];
                years[0]=year;
                for(int y=1;y<10;y++){
                    years[y]=year+1;
                    year=year+1;
                }
                int yearindex=0;
                for(int j=1;j<10;j++){
                    if (years[j]==inyear) {
                        yearindex = j;
                        break;
                    }
                }
                yearCombo.setSelectedIndex(yearindex);
                monthCombo.setSelectedIndex(inmonth-1);
                dayCombo.setSelectedIndex(inday-1);

                int saleStatus = Integer.parseInt(m1.getIs_salable());
                if (saleStatus == 1) {
                    yesNo.setSelectedIndex(0);
                } else {
                    yesNo.setSelectedIndex(1);
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,"ID INVALID");
            }
        }
    }
    void generateBillFunction(){
        System.out.println(ModelList.size());
        if(ModelList.size()==0){
            JOptionPane.showMessageDialog(null,"No Items In The Cart");
        }else{
            if(alreadyUser.isSelected()){
                System.out.println("Already user is selected ");
                db1=new DataBase();
                int id=Integer.parseInt(customerIdInput.getText());
                db1.insertTransactionData2(id,ModelList,quantity);
                JOptionPane.showMessageDialog(null,"Bill Generation Successfull");
                ModelList.clear();
                customerNameInput.setText("");
                customerPANInput.setText("");
                updateTable();
            }else if(newUser.isSelected()){
                System.out.println("New User is selected ");
                if(customerNameInput.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Customer Name is Important ");
                }else{
                    if(customerPANInput.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Customer PAN No is Important");
                    }else{
                        db1=new DataBase();
                        db1.insertTransactionData(customerNameInput.getText(),customerPANInput.getText(),ModelList,quantity);
                        JOptionPane.showMessageDialog(null,"Bill Generation Successfull");
                        ModelList.clear();
                        customerNameInput.setText("");
                        customerPANInput.setText("");
                        updateTable();
                    }
                }
            }
        }
    }

    void Login(){
        LoginFrame=new JFrame("Login");
        LoginFrame.setSize(400,300);
        LoginFrame.setVisible(true);
        LoginFrame.setLayout(null);

        usernameLabel=new JLabel("Username");
        LoginFrame.add(usernameLabel);
        usernameLabel.setBounds(10,50,80,25);

        userNameInput=new JTextField();
        LoginFrame.add(userNameInput);
        userNameInput.setBounds(110,50,120,25);

        userPasswordLabel=new JLabel("Password");
        LoginFrame.add(userPasswordLabel);
        userPasswordLabel.setBounds(10,80,80,25);

        userPasswordInput=new JPasswordField();
        LoginFrame.add(userPasswordInput);
        userPasswordInput.setBounds(110,80,120,25);

        signInBtn=new JButton("SignIn");
        LoginFrame.add(signInBtn);
        signInBtn.setBounds(55,120,100,25);
        signInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInFunc();
            }
        });
        UsersBtn=new JButton("Users Account");
        UsersBtn.setBounds(50,160,130,25);
        LoginFrame.add(UsersBtn);
        UsersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUsersBtn();
            }
        });
        LoginFrame.setDefaultCloseOperation(1);
    }
    void SignInFunc(){
        db1=new DataBase();
        int rs=db1.loginData(userNameInput.getText(),new String(userPasswordInput.getPassword()));
        if(rs==-1){
            loginStatus=true;
            JOptionPane.showMessageDialog(null,"Login SuccessFull");
            LoginFrame.dispatchEvent(new WindowEvent(LoginFrame, WindowEvent.WINDOW_CLOSING));
            frameGui();
        }else if(rs==1 || rs==0){
            JOptionPane.showMessageDialog(null,"Credentials Do not Match");
        }
    }
    //This function is for listing data from database in table at the right side of the GUI
    void setDataList(){
        dataList=new JPanel(new GridLayout());
        dataList.setBorder(BorderFactory.createTitledBorder("List of data "));
        infoTable=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        infoTableModel=new DefaultTableModel(0,0);
        String column[] = new String[] { "S.N.", "Item Name", "Rate","Quantity", "Sum"};
        infoTableModel.setColumnIdentifiers(column);
        infoTable.setModel(infoTableModel);
        updateTable();
        sp=new JScrollPane(infoTable);
        dataList.add(sp);
        infoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infotableMouseClicked(evt);
            }
        });
        f.add(dataList,BorderLayout.CENTER);
    }

    //This function handles row Clicked Events in JTable
    void infotableMouseClicked(java.awt.event.MouseEvent evt){
        infoTableModel = (DefaultTableModel)infoTable.getModel();
        int selectedRowIndex = infoTable.getSelectedRow();
        String id=infoTableModel.getValueAt(selectedRowIndex, 0).toString();
        idInput.setText(id);
        nameInput.setText(infoTableModel.getValueAt(selectedRowIndex, 1).toString());
        rateInput.setText(infoTableModel.getValueAt(selectedRowIndex, 2).toString());
        String gender=infoTableModel.getValueAt(selectedRowIndex, 3).toString();
        String CoVidStatus=infoTableModel.getValueAt(selectedRowIndex,4).toString();
        if(gender.equals("male")){
            maleBtn.setSelected(true);
        }else if(gender.equals("Female")){
            femaleBtn.setSelected(true);
        }else if(gender.equals("Unspecified")){
            unspecifiedBtn.setSelected(true);
        }
        if(CoVidStatus.equals("Positive")){
            yesNo.setSelectedIndex(0);
        }else if(CoVidStatus.equals("Negative")){
            yesNo.setSelectedIndex(1);
        }
    }
    //This functions sets buttons for Save,Update,Delete and Clear at the Footer
    void setFooter(){
        footer=new JPanel(new GridLayout(1,4));
        footer.setBorder(BorderFactory.createTitledBorder("Actions"));

        addInventoryBtn=new JButton("Add TO Inventory");
        footer.add(addInventoryBtn);
        addInventoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAddToInventoryBtn();
            }
        });

        transferBtn=new JButton("Transfer");
        footer.add(transferBtn);
        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTransferFunction();
            }
        });

        revokeBtn=new JButton("Revoke");
        revokeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDeletebtn();
            }
        });

        footer.add(revokeBtn);
        clearBtn=new JButton("Clear");
        footer.add(clearBtn);
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClearBtn();
            }
        });

        /*
        UsersBtn=new JButton("Users Account");
        UsersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUsersBtn();
            }
        });
        */
        //footer.add(UsersBtn);
        f.add(footer,BorderLayout.SOUTH);
    }

    //This function performs action on Save Button
    void setAddToInventoryBtn(){
        int saleStatus;
        if(validations()){
            String name=nameInput.getText();
            int rate=Integer.parseInt(rateInput.getText());
            int quantity=Integer.parseInt(quantityInput.getText());
            String expiry_date=yearCombo.getSelectedItem().toString()+"-"+monthCombo.getSelectedItem().toString()+"-"+dayCombo.getSelectedItem().toString();
            String salableStatus=yesNo.getSelectedItem().toString();
            System.out.println(salableStatus);
            if(salableStatus.toLowerCase().equals("yes")){
                saleStatus=1;
            }else{
                saleStatus=0;
            }
            db1=new DataBase();
            db1.insertInventoryData(name,quantity,rate,expiry_date,saleStatus);
            //JOptionPane.showMessageDialog(null,"Data Saved Successfully ");
            //infoTableModel.setRowCount(0);
            setClearBtn();
        }
    }

    //This function performs action on Update Button
    void setTransferFunction(){
        transferFrame=new JFrame("Transfer Inventory");
        transferFrame.setSize(450,300);
        transferFrame.setVisible(true);
        transferFrame.setDefaultCloseOperation(1);
        transferFrame.setLayout(null);

        JLabel itemToTransfer=new JLabel("Item ID : ");
        itemToTransfer.setBounds(100,25,50,25);
        transferFrame.add(itemToTransfer);
        JTextField itemToTransferIdInput=new JTextField();
        itemToTransferIdInput.setBounds(200,25,100,25);
        transferFrame.add(itemToTransferIdInput);

        JLabel branchTransfer=new JLabel("Branch : ");
        branchTransfer.setBounds(100,60,50,25);
        transferFrame.add(branchTransfer);

        String branches[]={"Kathmandu","PKR","MNR"};
        JComboBox branchesCombo=new JComboBox(branches);
        branchesCombo.setBounds(200,60,100,25);
        transferFrame.add(branchesCombo);


        JLabel quantity=new JLabel("Quantity : ");
        quantity.setBounds(100,90,60,25);
        transferFrame.add(quantity);
        JTextField itemQuantityIp=new JTextField();
        itemQuantityIp.setBounds(200,90,100,25);
        transferFrame.add(itemQuantityIp);

        JButton transfer=new JButton("Transfer");
        transfer.setBounds(120,120,150,25);
        transferFrame.add(transfer);

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase db1=new DataBase();
                db1.insertTransferData(Integer.parseInt(itemToTransferIdInput.getText()),Integer.parseInt(itemQuantityIp.getText()),branchesCombo.getSelectedItem().toString());
            }
        });

    }

    //This function performs action on Delete Button
    void setDeletebtn(){
        db1=new DataBase();
        db1.getAllInventoryData();
    }
    //This function performs action on Clear Button
    void setClearBtn(){
        nameInput.setText("");
        rateInput.setText("");
        idInput.setText("");
        quantityInput.setText("");
        yearCombo.setSelectedIndex(0);
        monthCombo.setSelectedIndex(0);
        dayCombo.setSelectedIndex(0);
        yesNo.setSelectedIndex(0);
    }
    void setUsersBtn(){
        f2=new JFrame("Users Account ");
        f2.setSize(450,300);
        f2.setVisible(true);
        f2.setDefaultCloseOperation(1);
        f2.setLayout(null);
        //usernameLabel,userAddressLabel,userContactLabel,userPasswordLabel,userconfirmPasswordLabel;
        //userNameInput,userAddressInput,userContactInput,userPasswordInput,userPasswordInput2;
        createUserBtn=new JButton("Create User");
        f2.add(createUserBtn);
        createUserBtn.setBounds(150,150,150,25);
        createUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUserFrameFunc();
            }
        });
        editUserBtn=new JButton("Edit User");
        f2.add(editUserBtn);
        editUserBtn.setBounds(150,180,150,25);
        editUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditFrame();
            }
        });

        deleteUserBtn=new JButton("Delete User");
        f2.add(deleteUserBtn);
        deleteUserBtn.setBounds(150,210,150,25);
        deleteUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUserFrame();
            }
        });
        //userNameInput=new JTextField();
        //f2.add(userNameInput);
    }
    void createUserFrameFunc(){
        createUserFrame=new JFrame("Create New User");
        createUserFrame.setSize(450,300);
        createUserFrame.setVisible(true);
        createUserFrame.setDefaultCloseOperation(1);
        createUserFrame.setLayout(null);

        //usernameLabel,userAddressLabel,userContactLabel,userPasswordLabel,userconfirmPasswordLabel;
        //userNameInput,userAddressInput,userContactInput,userPasswordInput,userPasswordInput2;
        usernameLabel=new JLabel("Username");
        createUserFrame.add(usernameLabel);
        usernameLabel.setBounds(50,20,150,25);
        userNameInput=new JTextField();
        createUserFrame.add(userNameInput);
        userNameInput.setBounds(200,20,150,25);
        userAddressLabel=new JLabel("Address");
        createUserFrame.add(userAddressLabel);
        userAddressLabel.setBounds(50,50,150,25);
        userAddressInput=new JTextField();
        createUserFrame.add(userAddressInput);
        userAddressInput.setBounds(200,50,150,25);

        userContactLabel=new JLabel("Contact No");
        createUserFrame.add(userContactLabel);
        userContactLabel.setBounds(50,80,150,25);
        userContactInput=new JTextField();
        createUserFrame.add(userContactInput);
        userContactInput.setBounds(200,80,150,25);

        userPasswordLabel=new JLabel("Password");
        createUserFrame.add(userPasswordLabel);
        userPasswordLabel.setBounds(50,110,150,25);
        userPasswordInput=new JPasswordField();
        createUserFrame.add(userPasswordInput);
        userPasswordInput.setBounds(200,110,150,25);
        userconfirmPasswordLabel=new JLabel("Confirm Password");
        createUserFrame.add(userconfirmPasswordLabel);
        userconfirmPasswordLabel.setBounds(50,140,150,25);
        userPasswordInput2=new JPasswordField();
        createUserFrame.add(userPasswordInput2);
        userPasswordInput2.setBounds(200,140,150,25);

        signUpBtn=new JButton("Sign Up");
        createUserFrame.add(signUpBtn);
        signUpBtn.setBounds(115,180,100,25);
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCreateUser();
            }
        });
    }
    void setCreateUser(){
        String pswd1=new String(userPasswordInput.getPassword());
        String pswd2=new String(userPasswordInput2.getPassword());
        System.out.println(pswd1+pswd2);
        if(userNameInput.getText().equals("")){
            JOptionPane.showMessageDialog(null,"UserName is Compulsory");
        }else if(userAddressInput.getText().equals("")){
            JOptionPane.showMessageDialog(null,"User Address is Compulsory");
        }else if(userContactInput.getText().equals("")){
            JOptionPane.showMessageDialog(null,"User Contact Number is Compulsory");
        }else if(userPasswordInput.getPassword().equals("")){
            JOptionPane.showMessageDialog(null,"Password is Compulsory");
        }else if (userPasswordInput.getPassword().length<8){
            JOptionPane.showMessageDialog(null,"Password Length should be greater than or equal to 8");
        }else if(!pswd1.equals(pswd2)){
            JOptionPane.showMessageDialog(null," Passwords Do not Match ");
        }else{
            db1=new DataBase();
            int rs=db1.insertUser(userNameInput.getText(),userAddressInput.getText(),userContactInput.getText(),pswd1);
            System.out.println(rs);
            if(rs==-1){
                JOptionPane.showMessageDialog(null,"Username Already Exists");
            }else if(rs==1 || rs==0){
                JOptionPane.showMessageDialog(null,"User Successfully Created");
                createUserFrame.dispatchEvent(new WindowEvent(createUserFrame, WindowEvent.WINDOW_CLOSING));
                f2.dispatchEvent(new WindowEvent(f2, WindowEvent.WINDOW_CLOSING));
                LoginFrame.dispatchEvent(new WindowEvent(LoginFrame, WindowEvent.WINDOW_CLOSING));
                Login();
            }
        }

    }
    void setEditFrame(){
        editUserFrame=new JFrame("Edit User");
        editUserFrame.setSize(400,300);
        editUserFrame.setVisible(true);
        editUserFrame.setLayout(null);

        usernameLabel=new JLabel("Username");
        editUserFrame.add(usernameLabel);
        usernameLabel.setBounds(10,50,80,25);

        userNameInput=new JTextField();
        editUserFrame.add(userNameInput);
        userNameInput.setBounds(110,50,120,25);

        userPasswordLabel=new JLabel("Password");
        editUserFrame.add(userPasswordLabel);
        userPasswordLabel.setBounds(10,80,80,25);

        userPasswordInput=new JPasswordField();
        editUserFrame.add(userPasswordInput);
        userPasswordInput.setBounds(110,80,120,25);

        signInBtn=new JButton("SignIn");
        editUserFrame.add(signInBtn);
        signInBtn.setBounds(55,120,100,25);
        signInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInFunc2();
            }
        });
    }
    void SignInFunc2(){
        db1=new DataBase();
        int rs=db1.loginData(userNameInput.getText(),new String(userPasswordInput.getPassword()));
        if(rs==-1){
            loginStatus=true;
            UserModel us1=new UserModel();
            us1= db1.getDataByUserName(userNameInput.getText());
            System.out.println(us1.getUserName());
            editInterfaceFrame(us1);
        }else if(rs==1 || rs==0){
            JOptionPane.showMessageDialog(null,"Credentials Do not Match");
        }
    }
    void editInterfaceFrame(UserModel m1){
        editUserInterfaceFrame=new JFrame("Edit User User");
        editUserInterfaceFrame.setSize(450,300);
        editUserInterfaceFrame.setVisible(true);
        editUserInterfaceFrame.setDefaultCloseOperation(1);
        editUserInterfaceFrame.setLayout(null);

        //usernameLabel,userAddressLabel,userContactLabel,userPasswordLabel,userconfirmPasswordLabel;
        //userNameInput,userAddressInput,userContactInput,userPasswordInput,userPasswordInput2;
        usernameLabel=new JLabel("Username");
        editUserInterfaceFrame.add(usernameLabel);
        usernameLabel.setBounds(50,20,150,25);
        userNameInput=new JTextField();
        userNameInput.setText(m1.getUserName());
        editUserInterfaceFrame.add(userNameInput);
        userNameInput.setBounds(200,20,150,25);
        userAddressLabel=new JLabel("Address");
        editUserInterfaceFrame.add(userAddressLabel);
        userAddressLabel.setBounds(50,50,150,25);
        userAddressInput=new JTextField();
        userAddressInput.setText(m1.getAddress());
        editUserInterfaceFrame.add(userAddressInput);
        userAddressInput.setBounds(200,50,150,25);

        userContactLabel=new JLabel("Contact No");
        editUserInterfaceFrame.add(userContactLabel);
        userContactLabel.setBounds(50,80,150,25);
        userContactInput=new JTextField();
        userContactInput.setText(m1.getContact_No());
        editUserInterfaceFrame.add(userContactInput);
        userContactInput.setBounds(200,80,150,25);

        userPasswordLabel=new JLabel("Password");
        editUserInterfaceFrame.add(userPasswordLabel);
        userPasswordLabel.setBounds(50,110,150,25);
        userPasswordInput=new JPasswordField();
        userPasswordInput.setText(new String(m1.getPassword()));
        editUserInterfaceFrame.add(userPasswordInput);
        userPasswordInput.setBounds(200,110,150,25);
        userconfirmPasswordLabel=new JLabel("Confirm Password");
        editUserInterfaceFrame.add(userconfirmPasswordLabel);
        userconfirmPasswordLabel.setBounds(50,140,150,25);
        userPasswordInput2=new JPasswordField();
        editUserInterfaceFrame.add(userPasswordInput2);
        userPasswordInput2.setBounds(200,140,150,25);

        signUpBtn=new JButton("Update");
        editUserInterfaceFrame.add(signUpBtn);
        signUpBtn.setBounds(115,180,100,25);
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUpdateUser(m1.getId());
            }
        });
    }
    void setUpdateUser(int id){
        db1=new DataBase();
        int rs=db1.updateUser(id,userNameInput.getText(),userAddressInput.getText(),userContactInput.getText(),new String(userPasswordInput.getPassword()));
        JOptionPane.showMessageDialog(null,"User Updated Successfully");
        editUserInterfaceFrame.dispatchEvent(new WindowEvent(editUserInterfaceFrame, WindowEvent.WINDOW_CLOSING));
        f2.dispatchEvent(new WindowEvent(f2, WindowEvent.WINDOW_CLOSING));
        LoginFrame.dispatchEvent(new WindowEvent(LoginFrame, WindowEvent.WINDOW_CLOSING));
        Login();
    }
    void deleteUserFrame(){
        deleteUserFrame=new JFrame("Delete User");
        deleteUserFrame.setSize(400,300);
        deleteUserFrame.setVisible(true);
        deleteUserFrame.setLayout(null);

        usernameLabel=new JLabel("Username");
        deleteUserFrame.add(usernameLabel);
        usernameLabel.setBounds(10,50,80,25);

        userNameInput=new JTextField();
        deleteUserFrame.add(userNameInput);
        userNameInput.setBounds(110,50,120,25);

        userPasswordLabel=new JLabel("Password");
        deleteUserFrame.add(userPasswordLabel);
        userPasswordLabel.setBounds(10,80,80,25);

        userPasswordInput=new JPasswordField();
        deleteUserFrame.add(userPasswordInput);
        userPasswordInput.setBounds(110,80,120,25);

        signInBtn=new JButton("SignIn");
        deleteUserFrame.add(signInBtn);
        signInBtn.setBounds(55,120,100,25);
        signInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInFunc3();
            }
        });
    }
    void SignInFunc3(){
            db1=new DataBase();
            int rs=db1.loginData(userNameInput.getText(),new String(userPasswordInput.getPassword()));
            if(rs==-1){
                loginStatus=true;
                UserModel us1=new UserModel();
                us1= db1.getDataByUserName(userNameInput.getText());
                db1.DeleteUser(us1.getId());
                JOptionPane.showMessageDialog(null,"User Deleted Successfully");
                deleteUserFrame.dispatchEvent(new WindowEvent(deleteUserFrame, WindowEvent.WINDOW_CLOSING));
                f2.dispatchEvent(new WindowEvent(f2, WindowEvent.WINDOW_CLOSING));
                LoginFrame.dispatchEvent(new WindowEvent(LoginFrame, WindowEvent.WINDOW_CLOSING));
                Login();
            }else if(rs==1 || rs==0){
                JOptionPane.showMessageDialog(null,"Credentials Do not Match");
            }
    }
    //This function Updates the JTabel after CRUD starts
    void updateTable() {
        int total=0;
        double tax_amount;
        while (infoTableModel.getRowCount()>0)
        {
            infoTableModel.removeRow(0);
        }
        int i;
        for (i = 0; i < ModelList.size(); i++) {
            infoTableModel.addRow(new Object[]{
                    i+1,
                    ModelList.get(i).getName(),
                    ModelList.get(i).getRate(),
                    ModelList.get(i).getQuantity(),
                    ModelList.get(i).getSum()
            });
            total=total+ModelList.get(i).getSum();
        }
        tax_amount=0.13*total;
        infoTableModel.addRow(new Object[]{
                "",
                "",
                "",
                "Tax Amount",
                tax_amount
        });
        infoTableModel.addRow(new Object[]{
                "",
                "",
                "",
                "Total",
                total+tax_amount
        });
    }

    //This function is to check validations in the namefield,addressfield and buttongroup field
    boolean validations(){
        String name=nameInput.getText();
        String rate=rateInput.getText();
        String quantity=quantityInput.getText();
        String salableStatus=yesNo.getSelectedItem().toString();
        boolean valid=true;
        if(name.equals("") || rate.equals("") ||quantity.equals("")){
            JOptionPane.showMessageDialog(null, "Field is Required..");
            valid=false;
        }
        if(salableStatus=="No"){
            JOptionPane.showMessageDialog(null,"The item is not salable, Can't Insert in Database ");
            valid=false;
        }
        return valid;
    }

    public static void main(String[] args) {
        DataBase my_db=new DataBase();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SampleApp d1=new SampleApp();
            }
        });
        my_db.createDatabase();
        my_db.createTable();
    }
}
