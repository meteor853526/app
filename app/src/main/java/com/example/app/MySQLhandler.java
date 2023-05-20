package com.example.app;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
public class MySQLhandler {
    private String message = null;
    private int id = 0;

    private final String URL="jdbc:mysql://34.80.170.197:3306/app";

    private final String USERNAME="user3";

    private final String PASSWORD="123456789";


    public MySQLhandler(String message, int id) throws SQLException {
        this.message = message;
        this.id = id;
    }
    public MySQLhandler() throws SQLException {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","load drive success");
        }catch( ClassNotFoundException e) {
            Log.e("DB","load drive fail");
            return;
        }
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Log.v("DB","load drive success");
        }catch(SQLException e) {
            Log.e("DB","load drive fail");
            Log.e("DB", e.toString());
        }
    }



    public void IntoDB(int msgid,String message) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        String sql = "SELECT * FROM infor";
        String insert = "INSERT INTO infor(id,message)"
                +"values(?,?)";

        PreparedStatement ptmt = conn.prepareStatement(insert);

        ptmt.setInt(1,msgid);
        ptmt.setString(2,message);

        ptmt.executeUpdate();

    }
    public ResultSet getData() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String password = "123456789";
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        String sql = "SELECT * FROM food";
        Statement getData = conn.createStatement();
        ResultSet rs = getData.executeQuery(sql);
        String data = null;
        {
            String introduce = null;
            String price = null;
            String personality = null;
            String favorability = null;
//            while (rs.next()) {
//                //Retrieve by column name
//                int id = rs.getInt("id");
//                introduce = rs.getString("introduce");
//                price = rs.getString("price");
//                //Display values
//                System.out.print("ID: " + id);
//                System.out.print(", name: " + introduce);
//                System.out.print(", role: " + price);
//                data = "{ID:" + id + ",introduce:" + introduce + ", price:" + price + " }";
//                Log.v("DB",data);
//            }
        }
        return rs;
    }
    //建立使用者
    public void CreateUser(String RegisterName, String RegisterPassword) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO user_data(account,password) VALUES ('" + RegisterName + "','" + RegisterPassword + "')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
            Log.v("DB", "write into database" + RegisterName);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "fail write into database");
            Log.e("DB", e.toString());
        }
    }
    //檢查帳號密碼
    public Boolean CheckAccount(String account, String password) throws ClassNotFoundException, SQLException {
        boolean ret=false;
        Class.forName("com.mysql.jdbc.Driver");

        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "Select * From user_data Where account=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,account);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                //取出資料庫裡 的hash 密碼
                String hash_password=rs.getString("password");
                //確認密碼是否正確
                ret=CheckPasswordAPI.ckPasswd(password,hash_password);
            }


            Log.v("DB", "get date from database:" + ret);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "fail to get data from database");
            Log.e("DB", e.toString());
        }

        return ret;

    }

    //加入購物車
    public void toCart(String account,String meal, int number, int price) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        try {

            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String insert = "INSERT INTO cart(account,foodname,count,price)"
                    +"values(?,?,?,?)";

            PreparedStatement ptmt = con.prepareStatement(insert);
            ptmt.setString(1,account);
            ptmt.setString(2,meal);
            ptmt.setInt(3,number);
            ptmt.setInt(4,price);
            ptmt.executeUpdate();
            Log.v("DB", "write into databasedddd");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "fail write into databaseddddd");
            Log.e("DB", e.toString());
        }



    }

    public ResultSet checkFoodItemExistInShopping_car(String user_account, String food_name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        //SELECT 1 FROM table WHERE a = 1 AND b = 2 LIMIT 1
        String sql = "SELECT * FROM shopping_car WHERE user = '" + user_account + "'" + "AND food_name= '" + food_name + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet getUserCurrentOrder(String user_account) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        //SELECT 1 FROM table WHERE a = 1 AND b = 2 LIMIT 1
        String sql = "SELECT * FROM shopping_car WHERE user = '" + user_account + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public int addItemCountIntoShopping_car(String user_account, String food_name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ResultSet resultSet = checkFoodItemExistInShopping_car(user_account, food_name);
            int count;
            String sql = "";
            if(!resultSet.next()){ // empty
                sql = "INSERT INTO shopping_car(user,food_name,count) VALUES ('" + user_account + "','" + food_name + "'," + 1 + ")";
                return 1;
            }else{   // add
                int set = resultSet.getInt("count") + 1;
                sql = "UPDATE shopping_car SET count = " + set + " WHERE `user` = '" + user_account + "' AND food_name = '" + food_name + "'";
                count = set;
            }
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();

            Log.v("DB", "write into database" + user_account);
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "fail write into database");
            Log.e("DB", e.toString());
        }
        return 0;
    }

}




