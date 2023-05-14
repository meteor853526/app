package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
  MySQLhandler sqLhandler = new MySQLhandler();
  private Button MainBtn;

  public MainActivity() throws SQLException {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    new Thread(new Runnable(){
      @Override
      public void run(){

        sqLhandler.run();
        final ResultSet data;
        try {
          data = sqLhandler.getData();
        } catch (ClassNotFoundException e) {
          throw new RuntimeException(e);
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
        //Log.v("OK",data);
//        text_view.post(new Runnable() {
//          public void run() {
//            text_view.setText(data);
//          }
//        });

      }
    }).start();


    MainBtn = findViewById(R.id.mainpg_button);
  }
  public void onClickBtn(View v)
  {
    Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    Intent intent = new Intent(MainActivity.this, Login_page.class);
    startActivity(intent);
  }
}