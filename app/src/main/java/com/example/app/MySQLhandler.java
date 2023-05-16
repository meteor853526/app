package com.example.app;

import android.util.Log;

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
            Log.v("DB","???�?�??????????");
        }catch( ClassNotFoundException e) {
            Log.e("DB","???�?�????失�??");
            return;
        }

        // ?????��?????�?
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Log.v("DB","???端�????��?????");
        }catch(SQLException e) {
            Log.e("DB","???端�????�失???");
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

        String url = "jdbc:mysql://34.80.170.197/app";
        String username = "user3";
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
    //

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
}
