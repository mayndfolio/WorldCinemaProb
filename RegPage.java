package com.example.worldcinemaprob;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

public class RegPage extends AppCompatActivity {

    private EditText edit_email;
    private EditText edit_password;
    private EditText edit_password_confirm;
    private EditText edit_firstName;
    private EditText edit_lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_page);

        setup();
    }

    private void setup() {
        edit_email           = findViewById(R.id.loginPageEditTextEmail);
        edit_password        = findViewById(R.id.loginPageEditTextPassword);
        edit_password_confirm = findViewById(R.id.loginPageEditTextPasswordConfim);
        edit_firstName = findViewById(R.id.loginPageEditTextFirstName);
        edit_lastName = findViewById(R.id.loginPageEditTextLastName);
    }

    public void back(View view) {
        Intent intent = new Intent(RegPage.this, LoginPage.class);
        startActivity(intent);
    }

    public void registration(View view) {
        String email    = edit_email.getText().toString();
        String password = edit_password.getText().toString();
        String firstName = edit_firstName.getText().toString();
        String lastName = edit_lastName.getText().toString();

        URL url = null;

        API_Auth_Register net = new API_Auth_Register(email, password, firstName, lastName);

        url = net.generateURL();

        net.start(url);
        String responseCode = net.getResponseCode();
    }
}
