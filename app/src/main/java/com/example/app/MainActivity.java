package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private Button MainBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);



    MainBtn = findViewById(R.id.mainpg_button);
  }
  public void onClickBtn(View v)
  {
    Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    Intent intent = new Intent(MainActivity.this, Login_page.class);
    startActivity(intent);
  }
}