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
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
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
        Connection conn = DriverManager.getConnection(url, username, password);
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
    //將使用者帳戶加入資料庫
    public void CreateUser(String Name, String Password)
            throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO user_data (account,password)"
                    +"values(?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1,Name);
            stmt.setString(2,Password);
            stmt.executeUpdate();

            Log.v("DB", "寫入資料完成：" + Name);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DB", "寫入資料失敗");
            Log.e("DB", e.toString());
        }
    }
}
