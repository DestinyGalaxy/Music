package com.example.windowns81.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String num1 = firstNum.getText().toString();
                //String num2 = secondNum.getText().toString();

                Intent intent = new Intent();
                //intent.putExtra("one", num1);
                //intent.putExtra("two", num2);
                intent.setClass(Login.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
